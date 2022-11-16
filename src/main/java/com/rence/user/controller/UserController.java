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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.rence.backoffice.service.BackOfficeSendEmail;
import com.rence.user.model.EmailVO;
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
	
	@Autowired
	UserSendEmail authSendEmail;
	
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
	
	
	/**
	 * 로그아웃 완료
	 */
	@ApiOperation(value="로그아웃 완료", notes="로그아웃 입니다")
	@PostMapping("/user_logoutOK")
	public String user_logout(HttpServletRequest request, HttpServletResponse response) {
		log.info("user_logoutOK()...");
		session.invalidate();

		Cookie[] cookies = request.getCookies(); // 모든 쿠키의 정보를 cookies에 저장
		if (cookies != null) { // 쿠키가 한개라도 있으면 실행
			for (int i = 0; i < cookies.length; i++) {
				cookies[i].setMaxAge(0); // 유효시간을 0으로 설정
				response.addCookie(cookies[i]); // 응답 헤더에 추가
			}
		}

		return "redirect:/"; // 홈페이지로 이동
	}
	
	
	/**
	 * 아이디 찾기
	 */
	@ApiOperation(value="아이디 찾기", notes="아이디 찾기 입니다")
	@PostMapping("/find_id")
	@ResponseBody
	public String user_find_id(UserVO uvo, EmailVO evo) {
		log.info("user_find_id ()...");
		log.info("result: {}", uvo);

		Gson gson = new Gson();
		Map<String, String> map = new HashMap<String, String>();
		
		UserVO uvo2 = service.user_email_select(uvo);
		log.info("uvo2: {}", uvo2);
		if (uvo2 != null) {
			uvo2 = authSendEmail.findId(uvo2, evo); // 유저의 메일로 아이디 전송

			if (uvo2 != null) {
				log.info("user_fine_id successed...");
				map.put("result", "1");

			} else {
				log.info("user_fine_id failed...");
				map.put("result", "0");
			}
		}
		
		String jsonObject = gson.toJson(map);

		return jsonObject;
	}
	
	/**
	 * 비밀번호 찾기
	 */
	@ApiOperation(value="비밀번호 찾기", notes="비밀번호 찾기 입니다")
	@PostMapping("/find_pw")
	@ResponseBody
	public JSONObject user_find_pw(UserVO uvo, EmailVO evo) {
		log.info("user_find_pw ()...");
		log.info("result{}", uvo); // 넘어오는 값 출력

		UserVO uvo2 = service.user_id_email_select(uvo); // 아이디 이메일 체크

		JSONObject jsonObject = new JSONObject();

		if (uvo2 != null) {
			uvo2 = authSendEmail.findPw(uvo2, evo); // uvo2가 null이 아니면(테이블에 데이터가 존재하면) 메일을 통해 수정링크 제공
			int result = service.user_pw_init(uvo2);

			if (result != 0) {
				log.info("넣기전에 uvo2: {}", uvo2);
				log.info("user_fine_pw successed...");
				jsonObject.put("result", "1");
			} else {
				log.info("user_fine_pw failed...");
				jsonObject.put("result", "0");
			}

		}

		return jsonObject;
	}

	
	
}//end class
