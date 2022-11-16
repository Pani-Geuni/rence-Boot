/**
 * @author 전판근, 최진실
 */

package com.rence.master.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rence.backoffice.model.BackOfficeVO;
import com.rence.backoffice.model.EmailVO;
import com.rence.master.model.MasterVO;
import com.rence.master.service.MasterSendEmail;
import com.rence.master.service.MasterService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "마스터 컨트롤러")
@Controller
@RequestMapping("/master")
public class MasterController {

	@Autowired
	MasterService service;

	@Autowired
	MasterSendEmail sendEmail;

	/**
	 * 마스터 로그인 페이지
	 */
	@ApiOperation(value = "마스터 로그인", notes = "마스터 로그인 및 마스터 진입")
	@GetMapping("/login")
	public String login() {

		return "thymeleaf/html/master/login";

	}

	/**
	 * 로그인 처리
	 */
	@ApiOperation(value = "마스터 로그인 처리", notes = "마스터 로그인 처리")
	@PostMapping("/loginOK")
	@ResponseBody
	public String loginOK(MasterVO mvo, HttpServletResponse res) {

		return "redirect:master/main";
	}

	/**
	 * 로그아웃 처리
	 */
	@ApiOperation(value = "로그아웃 처리", notes = "마스터 로그아웃 처리")
	@GetMapping("/logoutOK")
	public String logout(HttpServletRequest request, HttpServletResponse response) {

		log.info("master logout...");

		return "redirect:login";
	}

	/**
	 * 마스터 메인 페이지 (호스트 가입 신청 리스트)
	 */
	@ApiOperation(value = "마스터 메인/호스트 가입 신청 리스트", notes = "호스트 가입 신청 리스트")
	@GetMapping("/main")
	public String master_main(Model model, @RequestParam(value = "page", defaultValue = "1") Integer page) {
		log.info("page:{}", page);

		long total_rowCount = service.total_rowCount();
		model.addAttribute("total_rowCount", total_rowCount);
		
		List<BackOfficeVO> bvos = service.backoffice_applyList_selectAll(page);
		log.info("result: {}.", bvos.size());

		log.info("bvos : {}", bvos);

		model.addAttribute("vos", bvos);
		model.addAttribute("cnt", bvos.size());

		model.addAttribute("title", "마스터 메인/호스트 신청 리스트");
		model.addAttribute("content", "thymeleaf/html/master/master_main/main");

		return "thymeleaf/layouts/master/layout_master";
	}

	/**
	 * 마스터 메인 페이지 (호스트 가입 신청 리스트 - 승인)
	 * 
	 * @throws UnsupportedEncodingException
	 */
	@ApiOperation(value = "마스터 메인/호스트 가입 신청 승인 처리", notes = "호스트 가입 신청 승인 처리")
	@PostMapping("/grant")
	@ResponseBody
	public JSONObject master_grant(BackOfficeVO bvo, EmailVO evo) throws UnsupportedEncodingException {
		log.info("BackOfficeVO:{}", bvo);
		JSONObject jsonObject = new JSONObject();

		int flag = service.backoffice_grant(bvo);
		if (flag == 1) {
			// evo.setReciver(bvo.getBackoffice_email());
			BackOfficeVO bvo2 = sendEmail.settingPw(bvo, evo);
			if (bvo2 != null) {
				log.info("successed...");
				log.info("=============bvo2:{}", bvo2);

				jsonObject.put("result", "1");
			} else { // 이메일 전송 실패
				log.info("failed...");

				jsonObject.put("result", "0");
			}
		} else { // 백오피스 승인 실패
			log.info("failed...");

			jsonObject.put("result", "0");
		}

		return jsonObject;
	}
	
	/**
	 * 마스터 메인 페이지 (호스트 가입 신청 리스트 - 거절)
	 */
	@ApiOperation(value = "마스터 메인/호스트 가입 신청 거절 처리", notes = "호스트 가입 신청 거절 처리")
	@PostMapping("/refuse")
	@ResponseBody
	public JSONObject master_refuse(BackOfficeVO bvo, EmailVO evo) {
		log.info("BackOfficeVO:{}", bvo);
		JSONObject jsonObject = new JSONObject();

		int flag = service.backoffice_refuse(bvo);
		if (flag == 1) {

			BackOfficeVO bvo2 = sendEmail.result_refuse(bvo, evo);
			log.info("successed...");
			log.info("=============bvo2:{}", bvo2);

			jsonObject.put("result", "1");

		} else { // 백오피스 거절 실패
			log.info("failed...");

			jsonObject.put("result", "0");
		}

		return jsonObject;
	}

	/**
	 * 마스터 - 호스트 탈퇴 신청 리스트
	 */
	@ApiOperation(value = "마스터 - 호스트 탈퇴 신청 처리", notes = "호스트 탈퇴 신청 리스트")
	@GetMapping("/backoffice_end")
	public String backoffice_end(Model model, @RequestParam(value = "page", defaultValue = "1") Integer page) {
		log.info("page:{}", page);

		long total_rowCount = service.total_rowCount();
		model.addAttribute("total_rowCount", total_rowCount);
		
		List<BackOfficeVO> bvos = service.backoffice_endList_selectAll(page);
		log.info("result: {}.", bvos.size());
		
		model.addAttribute("vos", bvos);
		model.addAttribute("cnt", bvos.size());

		model.addAttribute("title", "호스트 탈퇴 신청 리스트");
		model.addAttribute("content", "thymeleaf/html/master/master_main/backoffice_end");

		return "thymeleaf/layouts/master/layout_master";
	}
	
	/**
	 * 마스터 - 호스트 탈퇴 승인
	 */
	@ApiOperation(value = "마스터 - 호스트 탈퇴 승인", notes = "호스트 탈퇴 승인")
	@PostMapping("/revoke")
	@ResponseBody
	public JSONObject master_revoke(BackOfficeVO bvo, EmailVO evo) {
		log.info("BackOfficeVO:{}", bvo);
		JSONObject jsonObject = new JSONObject();

		int flag = service.backoffice_revoke(bvo);
		if (flag == 1) {
			BackOfficeVO bvo2 = sendEmail.backoffice_revoke(bvo, evo);
			if (bvo2 != null) {
				log.info("successed...");
				log.info("=============bvo2:{}", bvo2);
				jsonObject.put("result", "1");
			} else { // 이메일 전송 실패
				log.info("failed...");
				jsonObject.put("result", "0");
			}
		} else { // 백오피스 승인 실패
			log.info("failed...");
			jsonObject.put("result", "0");
		}

		return jsonObject;
	}
	
	/**
	 * 백오피스 상세 페이지
	 */
	@ApiOperation(value = "백오피스 상세 페이지", notes = "백오피스 상세 페이지")
	@GetMapping("/backoffice_detail")
	public String master_backoffice_detail(BackOfficeVO bvo, Model model) {
		log.info("backoffice_setting()...");
		BackOfficeVO bvo2 = service.master_backoffice_detail_selectOne(bvo);
		log.info("result: {}.", bvo2);
		
		String rt="";
		if (bvo2!=null) {
			model.addAttribute("vo", bvo2);
			rt="backoffice/setting";
		}else { // 굳이 필요없지만 넣어봄,,
			rt="redirect:master_main";
		}

		return rt;
	}
	

}
