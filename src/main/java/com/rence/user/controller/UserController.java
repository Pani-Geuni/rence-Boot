/**
 * @author 강경석
 * 로그인,로그아웃 
 * 회원 탈퇴
 * 아이디, 비밀번호 찾기
 */
package com.rence.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.rence.user.model.UserVO;
import com.rence.user.service.UserSerivice;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(tags="유저 컨트롤러")
@Slf4j
@Controller
public class UserController {
	
	@Autowired
	UserSerivice service;
	
	@Autowired
	HttpSession session;
	
	/**
	 * 로그인 완료
	 */
	@ApiOperation(value="로그인 완료", notes="로그인 완료 입니다")
	@PostMapping("/user_loginOK")
	@ResponseBody
	public String user_loginOK(UserVO uvo, HttpServletResponse response) {
		
		
		Gson gson = new Gson();
		Map<String, String> map = new HashMap<String, String>();

		UserVO uvo2 = service.User_loginOK(uvo);
		
		if (uvo2 != null) {
			session.setAttribute("user_id", uvo2.getUser_id());
			
			Cookie cookie = new Cookie("user_no", uvo2.getUser_no()); // 고유번호 쿠키 저장
			Cookie cookie2 = new Cookie("user_image", uvo2.getUser_image()); // 고유번호 쿠키 저장
			response.addCookie(cookie);
			response.addCookie(cookie2);

			log.info("User Login success.....");
			map.put("result", "1"); // 로그인 성공
		} else {
			log.info("User Login failed.....");
			map.put("result", "0"); // 로그인 실패
		}

		String jsonObject = gson.toJson(map);
		
		return jsonObject;
	}
	

	
	
}//end class
