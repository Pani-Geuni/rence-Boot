/**
	 * @author 강경석
	 *  회원가입 처리 컨트롤러
	 */

package com.rence.user.controller;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rence.user.model.UserAuthVO;
import com.rence.user.model.EmailVO;
import com.rence.user.model.UserVO;
import com.rence.user.service.UserFileuploadService;
import com.rence.user.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;




@Api(tags = "유저회원가입 컨트롤러")
@Slf4j
@Controller
@RequestMapping("/rence")
public class UserJoinController {
	private static final Logger logger = LoggerFactory.getLogger(UserJoinController.class);

	@Autowired
	UserService service;
	@Autowired
	UserFileuploadService fileuploadService;
	@Autowired
	ServletContext context;
	@Autowired
	UserSendEmail authSendEmail;
	
	//자동 개행 및 줄 바꿈 (new Gson은 일자로 나옴)
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, 
				new CustomDateEditor(dateFormat, true));
	}
	

	
	/**
	 * 이메일 인증번호 요청
	 * 이메일 중복 체크
	 */
	@ApiOperation(value="이메일 인증번호요청", notes="이메일 인증번호요청 입니다.")
	@PostMapping("/user_auth")
	@ResponseBody
	@Transactional
	public String user_auth(UserAuthVO avo, UserVO uvo, EmailVO evo) {
		logger.info("Welcome user_auth");
		logger.info("{}", uvo);
		
		Map<String, String> map = new HashMap<String, String>();

		
		//이메일 중복 체크
		UserVO emailCheck = service.emailCheckOK(uvo);
		logger.info("emailCheck: {}", emailCheck);
		
		
		// 탈퇴한 회원의 이메일로 재가입 가능
//		if(emailCheck==null || emailCheck.getUser_state() == "N   ") {
		if(emailCheck==null || emailCheck.getUser_state().equalsIgnoreCase("N   ")) {
			avo.setUser_email(uvo.getUser_email());
			logger.info("avo :   {}", avo);
			//이메일 전송
			avo = authSendEmail.sendEmail(avo,evo);
			logger.info("메일이 전송되었습니다.C_avo: {}",avo);
			if (avo !=null) {
				//avo2 = auth 테이블에 정보 저장 후, select 해온 결과값
				UserAuthVO avo2 = service.user_auth_insert(avo);
				logger.info("user_auth successed...");
				logger.info("avo2:{}",avo2);
				
				map.put("authNum", "1");
				
			}else {
				logger.info("user_auth failed...");
				map.put("authNum", "0");
			}
		}
		//이메일 중복체크시 이메일이 있으면 2
		else {
			logger.info("user_auth failed...(email check NOT OK)");
			map.put("authNum", "2");
		}
		String jsonObject = gson.toJson(map);
		return jsonObject;
	}
	

	/**
	 * 이메일 인증번호 확인
	 */
	@ApiOperation(value="이메일 인증번호확인", notes="이메일 인증번호확인 입니다.")
	@PostMapping("/user_authOK")
	@ResponseBody
	@Transactional
	public String user_authOK(String user_email, String email_code) {
		
		logger.info("Welcome user_authOK");
		logger.info("{}", email_code);
		 
		UserAuthVO avo = service.user_authOK_select(user_email, email_code);
		logger.info("avo: {}", avo);
		Map<String, String> map = new HashMap<String, String>();

	    if(avo != null){
	    	logger.info("successed...");
	    	int result = service.user_auth_delete(user_email, email_code);
	    	map.put("result", "1");
	    }else{
	    	logger.info("failed...");
	    	map.put("result", "0");
	    }
	    
	    
	    String jsonObject = gson.toJson(map);
		return jsonObject;
	}

	// 아이디 중복 체크
	@ApiOperation(value="아이디 중복 체크", notes="아이디 중복 체크 입니다.")
	@PostMapping("/user_idCheckOK")
	@ResponseBody
	public String user_idCheckOK(UserVO uvo) {
		logger.info("Welcome! user_idCheckOK");
		logger.info("result: {}", uvo);
		
		
		Map<String, String> map = new HashMap<String, String>();
		

		UserVO idCheck = service.idCheckOK(uvo);
		logger.info("idlCheck: {}", idCheck);

		if(idCheck==null || idCheck.getUser_state().equalsIgnoreCase("N   ")) {
			map.put("result", "1"); // 아이디 사용가능("OK")			
		}else {
			// uvo가 null이 아니면 아이디 존재
			map.put("result", "0"); // 아이디 존재("NOT OK")
			
		}

		
		String jsonObject = gson.toJson(map);
		return jsonObject;
	}

	// 회원가입완료
	@ApiOperation(value="회원가입 성공", notes="회원가입 성공 입니다.")
	@PostMapping("/joinOK")
	@ResponseBody
	public String user_joinOK(UserVO uvo) {
		logger.info("Welcome! user_joinOK");
		logger.info("result: {}", uvo);

		Map<String, String> map = new HashMap<String, String>();
		// insert(성공시 1)
		int result = service.user_insertOK(uvo);
		logger.info("result: {}", result);
		
		//회원가입 실패시
		if(result==0) {
			//회원가입실패
			map.put("result", "0"); 
			
		}
		else {
			UserVO uvo2 = service.user_select_userno();
			logger.info("uvo2: {}", uvo2);
			int result2 = service.user_mileage_zero_insert(uvo2);
			if(result2 == 0) {
				//회원가입은 했지만 마일리지 데이터가 안들어갔으므로 실패
//				jsonObject.put("result", "0");
			}
			logger.info("result2: {}", result2);
			map.put("result", "1"); 
		}

		
		String jsonObject = gson.toJson(map);
		return jsonObject;
	}

}// end class
