package com.rence.backoffice.controller;

import java.text.ParseException;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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
	
//	Gson gson = new GsonBuilder().setPrettyPrinting().create();

	@Autowired
	BackOfficeService service;

	@Autowired
	BackOfficeFileService fileService;
	
	@Autowired
	BackOfficeSendEmail authSendEmail;
	
	@Autowired
	OperatingTime operatingTime;

	/**
	 * 백오피스 랜딩
	 */
	@ApiOperation(value="백오피스 랜딩", notes="백오피스 랜딩 페이지입니다.")
	@GetMapping("/landing")
	public String backoffice_landing(Model model) {
		log.info("/backoffice_landing...");

		model.addAttribute("content", "thymeleaf/backoffice/th_landing");
		model.addAttribute("title", "백오피스 랜딩");

		return "thymeleaf/backoffice/th_layout_main";
	}

	/**
	 * 백오피스 신청 폼 출력
	 */
	@ApiOperation(value="호스트 신청", notes="호스트 신청 페이지입니다.")
	@GetMapping("/insert")
	public String backoffice_insert(Model model) {
		log.info("/backoffice_insert...");

		model.addAttribute("content", "thymeleaf/backoffice/th_insert");
		model.addAttribute("title", "호스트 신청");

		return "thymeleaf/backoffice/th_layout_main";
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

		String rt = "redirect:backoffice_landing";
		if (result == null || bvo2 == null) {
			return "redirect:backoffice_insert";
		}

		return rt;
	}
	
	/**
	 * 이메일 인증번호 요청
	 */
	@ApiOperation(value="이메일 인증번호 요청", notes="호스트 신청시, 이메일 인증번호 요청 페이지입니다.")
	@GetMapping("/auth")
	@ResponseBody
	public JSONObject backoffice_auth(AuthVO avo, BackOfficeVO bvo, EmailVO evo) {
		log.info("Welcome sendMailOK.do");
		log.info("{}", bvo);
		
		JSONObject jsonObject = new JSONObject();

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

				jsonObject.put("result", "1");
				jsonObject.put("auth_code", avo2.getAuth_code());
				jsonObject.put("backoffice_email", avo2.getUser_email());
				jsonObject.put("auth_no", avo2.getAuth_no());

			} else { // 전송 실패
				log.info("failed...");
				jsonObject.put("result", "0");
			}
		} else { // 중복체크 실패
			log.info("failed...");
			jsonObject.put("result", "0");
		}

		return jsonObject;
	}
	
	/**
	 * 이메일 인증번호 확인
	 */
	@ApiOperation(value="이메일 인증번호 확인", notes="호스트 신청시, 이메일 인증번호 확인입니다.")
	@PostMapping("/authOK")
	@ResponseBody
	@Transactional
	public JSONObject backoffice_authOK(AuthVO avo, String backoffice_email, String auth_code) {

		AuthVO avo2 = service.backoffice_authOK_select(backoffice_email, auth_code);

		JSONObject jsonObject = new JSONObject();

		if (avo2 != null) {
			log.info("successed...");
			jsonObject.put("result", "1");
			service.backoffice_auth_delete(avo2);

		} else {
			log.info("failed...");
			jsonObject.put("result", "0");
		}
		return jsonObject;
	}

}
