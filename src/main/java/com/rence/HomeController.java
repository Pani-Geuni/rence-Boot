package com.rence;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rence.user.controller.UserSendEmail;
import com.rence.user.model.UserVO;
import com.rence.user.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Api(tags="HOME 컨트롤러")
@Controller
@RequestMapping("/")
public class HomeController {
	
	
	@Autowired
	UserService service;

	@Autowired
	HttpSession session;

	@Autowired
	UserSendEmail authSendEmail;
	
	//자동 개행 및 줄 바꿈 (new Gson은 일자로 나옴)
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	
	@ApiOperation(value="메인 화면 로드", notes="메인 페이지 띄우는 컨트롤러")
	@GetMapping("/")
	public String home(Model model) {
		
		model.addAttribute("content", "thymeleaf/html/office/home");
		
		return "thymeleaf/layouts/office/layout_base";
	}
	
	/**
	 * 로그인 완료
	 */
	@ApiOperation(value = "로그인 성공", notes = "로그인 성공 입니다")
	@PostMapping("/loginSuccess")
	@ResponseBody
	public String user_loginOK(@RequestParam String username, HttpServletResponse response) {
		log.info("user_loginOK ()...");
		log.info("username: {}", username);
		
		UserVO uvo = service.user_login_info(username);
		
		Map<String, String> map = new HashMap<String, String>();

//		UserVO uvo2 = service.User_loginOK(uvo);
		session.setAttribute("user_id", uvo.getUser_id());

		Cookie cookie = new Cookie("user_no", uvo.getUser_no()); // 고유번호 쿠키 저장
		Cookie cookie2 = new Cookie("user_image", uvo.getUser_image()); // 고유번호 쿠키 저장
		response.addCookie(cookie);
		response.addCookie(cookie2);

		log.info("User Login success.....");
		map.put("result", "1"); // 로그인 성공

		String jsonObject = gson.toJson(map);

		return jsonObject;
	}

	/**
	 * 로그인 실패
	 */
	@ApiOperation(value = "로그인 실패", notes = "로그인 실패 입니다")
	@PostMapping("/loginFail")
	@ResponseBody
	public String user_loginFail(UserVO uvo, HttpServletResponse response) {
		log.info("user_loginFail ()...");
		log.info("result: {}", uvo);
		
		Map<String, String> map = new HashMap<String, String>();

		

		log.info("User Login failed.....");
		map.put("result", "0"); // 로그인 실패

		String jsonObject = gson.toJson(map);

		return jsonObject;
	}	
}
