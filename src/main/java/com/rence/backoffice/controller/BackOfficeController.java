/**
 * 
 * @author 최진실
 *
 */
package com.rence.backoffice.controller;

import java.security.Principal;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rence.backoffice.model.AuthVO;
import com.rence.backoffice.model.BackOfficeOperatingTimeVO;
import com.rence.backoffice.model.BackOfficeOperatingTimeVO_datetype;
import com.rence.backoffice.model.BackOfficeVO;
import com.rence.backoffice.model.EmailVO;
import com.rence.backoffice.service.BackOfficeFileService;
import com.rence.backoffice.service.BackOfficeSendEmail;
import com.rence.backoffice.service.BackOfficeService;
import com.rence.backoffice.service.OperatingTime;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@Api(tags="백오피스 컨트롤러")
@RequestMapping("/backoffice")
public class BackOfficeController {
	
	Gson gson = new GsonBuilder().setPrettyPrinting().create();

	@Autowired
	BackOfficeService service;

	@Autowired
	BackOfficeFileService fileService;
	
	@Autowired
	BackOfficeSendEmail authSendEmail;
	
	@Autowired
	OperatingTime operatingTime;
	

	@Autowired
	HttpSession session;
	


	/**
	 * 백오피스 랜딩
	 */
	@ApiOperation(value="백오피스 랜딩", notes="백오피스 랜딩 페이지입니다.")
	@GetMapping("/landing")
	public String backoffice_landing(Model model) {
		log.info("/backoffice_landing...");

		model.addAttribute("content", "thymeleaf/html/backoffice/landing/landing");
		model.addAttribute("title", "백오피스 랜딩");

		return "thymeleaf/layouts/backoffice/layout_backoffice";
	}

	/**
	 * 백오피스 신청 폼 출력
	 */
	@ApiOperation(value="호스트 신청", notes="호스트 신청 페이지입니다.")
	@GetMapping("/insert")
	public String backoffice_insert(Model model) {
		log.info("/backoffice_insert...");

		model.addAttribute("content", "thymeleaf/html/backoffice/landing/insert");
		model.addAttribute("title", "호스트 신청");

		return "thymeleaf/layouts/backoffice/layout_backoffice";
	}

	/**
	 * 백오피스 신청 처리
	 * 
	 * @throws ParseException
	 */
	@ApiOperation(value="호스트 신청 처리", notes="호스트 신청 처리입니다.")
	@PostMapping("/insertOK")
	public String backoffice_insertOK(BackOfficeVO vo, BackOfficeOperatingTimeVO ovo,
			MultipartHttpServletRequest mtfRequest, @RequestParam(value = "multipartFile_room") MultipartFile multipartFile_room, @RequestParam(value = "multipartFile_host") MultipartFile multipartFile_host) throws ParseException {

		BackOfficeOperatingTimeVO_datetype ovo2 = new BackOfficeOperatingTimeVO_datetype();
//		vo = new BackOfficeVO(null, "ss", "dd", "s", "s", "s", "s", "s", "s", "s", "s", "s", "s", "s", "s", "s", "s", "s", "s", null, "s");
		vo = fileService.backoffice_fileupload(vo, mtfRequest, multipartFile_room);
		log.info("filupload room:{}", vo);
		vo = fileService.host_fileupload(vo, multipartFile_host);
		log.info("filupload host:{}", vo);

		// 운영시간
		ovo2 = operatingTime.operatingTime(ovo, ovo2);

		// 백오피스 insert
		BackOfficeVO bvo2 = service.insertOK(vo);
		log.info("vo::::::::::::::::::::::::::{}", bvo2);

		// 운영시간 insert
		ovo2.setBackoffice_no(bvo2.getBackoffice_no());
		String result = service.backoffice_operating_insert(ovo2);
		log.info("ovo::::::::::::::::::::::::::{}", ovo2);

		String rt = "redirect:backoffice/landing";
		if (result == null || bvo2 == null) {
			return "redirect:backoffice/insert";
		}

		return rt;
	}
	
	/**
	 * 이메일 인증번호 요청
	 */
	@ApiOperation(value="이메일 인증번호 요청", notes="호스트 신청시, 이메일 인증번호 요청 페이지입니다.")
	@GetMapping("/auth")
	@ResponseBody
	public String backoffice_auth(AuthVO avo, BackOfficeVO bvo, EmailVO evo) {
		log.info("Welcome sendMailOK.do");
		log.info("{}", bvo);
		
//		JSONObject jsonObject = new JSONObject();
		
		Map<String, String> map = new HashMap<String,String>();

		// 이메일 중복 체크
		BackOfficeVO emailCheck = service.backoffice_email_check(bvo);
		if (emailCheck == null || emailCheck.getBackoffice_state().equals("X")
				|| emailCheck.getBackoffice_state().equals("N")) {

			avo.setUser_email(bvo.getBackoffice_email());

			// 이메일 전송
			avo = authSendEmail.sendEmail(avo, evo);
			if (avo != null) {

				// avo2 = auth 테이블에 정보 저장 후, select 해온 결과값
				AuthVO avo2 = service.backoffice_auth_insert(avo);
				log.info("successed...");
				log.info("=============avo2:{}", avo2);

				map.put("result", "1");
				map.put("auth_code", avo2.getAuth_code());
				map.put("backoffice_email", avo2.getUser_email());
				map.put("auth_no", avo2.getAuth_no());

			} else { // 전송 실패
				log.info("failed...");
				map.put("result", "0");
			}
		} else { // 중복체크 실패
			log.info("failed...");
			map.put("result", "0");
		}
		
		String json = gson.toJson(map);

		return json;
	}
	
	/**
	 * 이메일 인증번호 확인
	 */
	@ApiOperation(value="이메일 인증번호 확인", notes="호스트 신청시, 이메일 인증번호 확인입니다.")
	@PostMapping("/authOK")
	@ResponseBody
	@Transactional
	public String backoffice_authOK(AuthVO avo, String backoffice_email, String auth_code) {

		AuthVO avo2 = service.backoffice_authOK_select(backoffice_email, auth_code);

//		JSONObject jsonObject = new JSONObject();
		Map<String, String> map = new HashMap<String,String>();

		if (avo2 != null) {
			log.info("successed...");
			map.put("result", "1");
			service.backoffice_auth_delete(avo2);

		} else {
			log.info("failed...");
			map.put("result", "0");
		}
		
		String json = gson.toJson(map);
		return json;
	}
	
	/**
	 * 로그인 성공 처리
	 */
	@ApiOperation(value="로그인 성공", notes="로그인 성공")
	@PostMapping("/loginSuccess")
	@ResponseBody
	public String backoffice_loginOK(@RequestParam String username,HttpServletResponse response) {
		log.info("backoffice_loginOK()...");
		log.info("backoffice_id:{}",username);
		
		BackOfficeVO bvo = service.backoffice_login_info(username);

//		JSONObject jsonObject = new JSONObject();
		Map<String, String> map = new HashMap<String,String>();

			session.setAttribute("backoffice_id", bvo.getBackoffice_id());
			Cookie cookie_no = new Cookie("backoffice_no", bvo.getBackoffice_no());
			Cookie cookie_profile = new Cookie("host_image", bvo.getHost_image());
			session.setAttribute("backoffice_no", bvo.getBackoffice_no());
			session.setAttribute("host_image", bvo.getHost_image());
			map.put("result", "1");
			log.info("successed...");
			response.addCookie(cookie_no);
			response.addCookie(cookie_profile);
			
			String json = gson.toJson(map);
			
			return json;
		}
	
	/**
	 * 로그인 실패 처리
	 */
	@ApiOperation(value="로그인 실패", notes="로그인 실패")
	@PostMapping("/loginFail")
	@ResponseBody
	public String backoffice_loginfail(HttpServletResponse response) {
		log.info("backoffice_loginfail()...");
		
//		JSONObject jsonObject = new JSONObject();

		Map<String, String> map = new HashMap<String,String>();
		
		map.put("result", "0");
		log.info("failed...");
		
		String json = gson.toJson(map);

		return json;
	}

	
	/**
	 * 비밀번호 초기화(찾기), 이메일로 임시 비밀번호 전송
	 * @throws UnsupportedEncodingException 
	 */
	@ApiOperation(value="비밀번호 찾기", notes="비밀번호 찾기시, 이메일로 임시 비밀번호 전송")
	@GetMapping("/reset_pw")
	@ResponseBody
	public String backoffice_reset_pw(BackOfficeVO bvo, EmailVO evo){
		log.info("backoffice_reset_pw ()...");
		log.info("{}", bvo);

//		JSONObject jsonObject = new JSONObject();
		Map<String, String> map = new HashMap<String,String>();

		BackOfficeVO bvo2 = service.backoffice_id_email_select(bvo);

		log.info("bvo2 :: {}", bvo2);

		if (bvo2 != null) {
			bvo2 = authSendEmail.findPw(bvo2, evo);

			if (bvo2 != null) {
//				service.backoffice_settingOK_pw(bvo2); 
				service.backoffice_resetOK_pw(bvo2); 
				map.put("result", "1");

			} else {
				log.info("update failed...");
				map.put("result", "0");
			}
		} else {
			log.info("send failed...");
			map.put("result", "0");
		}
		
		String json = gson.toJson(map);

		return json;
	}
	
	/**
	 * 비밀번호 초기화 페이지
	 */
	@ApiOperation(value="비밀번호 초기화", notes="호스트 비밀번호 변경, 이메일로 전송된 비밀번호 재설정")
	@GetMapping("/setting_pw")
	public String backoffice_setting_pw(Model model, BackOfficeVO bvo) {
		model.addAttribute("vo", bvo.getBackoffice_no());
		
		model.addAttribute("content", "thymeleaf/html/backoffice/landing/setting_pw");
		model.addAttribute("title", "비밀번호 초기화");

		return "thymeleaf/layouts/backoffice/layout_backoffice";
	}
	
	/**
	 * 비밀번호 초기화 완료
	 */
	@ApiOperation(value="비밀번호 초기화 처리", notes="호스트 비밀번호 변경, 이메일로 전송된 비밀번호 재설정")
	@GetMapping("/settingOK_pw")
	@ResponseBody
	public String backoffice_settingOK_pw(BackOfficeVO bvo, HttpServletRequest request,
			HttpServletResponse response) {
		log.info("backoffice_settingOK_pw ()...");
		log.info("{}", bvo);

		session = request.getSession();

		bvo.setBackoffice_pw(new BCryptPasswordEncoder().encode(bvo.getBackoffice_pw()));
		int result = service.backoffice_settingOK_pw(bvo);

//		JSONObject jsonObject = new JSONObject();
		Map<String, String> map = new HashMap<String,String>();

		if (result == 1) {
			if (session.getAttribute("backoffice_id") != null) {
				// HOST 로그인 session이 존재할 때
				// Host 환경설정 > 비밀번호 수정
				log.info("succeed...");
				map.put("result", "1");
			} else {
				// 가입 신청이 완료되어
				// 신청자의 메일에서 링크 페이지를 열고 수정 했을 때
				log.info("succeed...");
				map.put("result", "1");

			}
		} else if (result == 0) {
			log.info("fail...");
			map.put("result", "0");
		}
		
		String json = gson.toJson(map);

		return json;
	}

}
