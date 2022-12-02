/**
 * @author 전판근, 최진실
 */

package com.rence.master.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
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
import com.rence.backoffice.model.BackOfficeListVO;
import com.rence.backoffice.model.BackOfficeVO;
import com.rence.backoffice.model.EmailVO;
import com.rence.common.OptionEngToKorMap;
import com.rence.master.model.MasterEntity;
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

	Gson gson = new GsonBuilder().setPrettyPrinting().create();

	@Autowired
	MasterService service;

	@Autowired
	MasterSendEmail sendEmail;

	@Autowired
	HttpSession session;

	/**
	 * 마스터 로그인 페이지
	 */
	@ApiOperation(value = "마스터 로그인", notes = "마스터 로그인 및 마스터 진입")
	@GetMapping("/login")
	public String login() {

		return "thymeleaf/html/master/login";

	}

	/**
	 * 로그인 성공 처리
	 */
	@ApiOperation(value = "로그인 성공", notes = "로그인 성공")
	@PostMapping("/loginSuccess")
	@ResponseBody
	public String master_loginOK(MasterEntity mvo, HttpServletResponse response) {
		log.info("master_loginOK()...");

		Map<String, String> map = new HashMap<String, String>();

		map.put("result", "1");
		log.info("successed...");

		String json = gson.toJson(map);

		return json;
	}

	/**
	 * 로그인 실패 처리
	 */
	@ApiOperation(value = "로그인 실패", notes = "로그인 실패")
	@PostMapping("/loginFail")
	@ResponseBody
	public String master_loginfail(HttpServletResponse response) {
		log.info("master_loginfail()...");

		Map<String, String> map = new HashMap<String, String>();

		map.put("result", "0");
		log.info("failed...");

		String json = gson.toJson(map);

		return json;
	}

	/**
	 * 마스터 메인 페이지 (호스트 가입 신청 리스트)
	 */
	@ApiOperation(value = "마스터 메인/호스트 가입 신청 리스트", notes = "호스트 가입 신청 리스트")
	@GetMapping("/main")
	public String master_main(Model model, @RequestParam(value = "page", defaultValue = "1") Integer page) {
		log.info("page:{}", page);

		List<BackOfficeListVO> bvos = service.backoffice_applyList_selectAll(page);
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
	public String master_grant(BackOfficeVO bvo, EmailVO evo) throws UnsupportedEncodingException {
		log.info("BackOfficeVO:{}", bvo);
		Map<String, String> map = new HashMap<String, String>();

		int flag = service.backoffice_grant(bvo);
		if (flag == 1) {
			BackOfficeVO bvo2 = sendEmail.settingPw(bvo, evo);
			if (bvo2 != null) {
				log.info("successed...");
				log.info("bvo2:{}", bvo2);

				map.put("result", "1");
			} else { // 이메일 전송 실패
				log.info("failed...");

				map.put("result", "0");
			}
		} else { // 백오피스 승인 실패
			log.info("failed...");

			map.put("result", "0");
		}

		String json = gson.toJson(map);

		return json;
	}

	/**
	 * 마스터 메인 페이지 (호스트 가입 신청 리스트 - 거절)
	 */
	@ApiOperation(value = "마스터 메인/호스트 가입 신청 거절 처리", notes = "호스트 가입 신청 거절 처리")
	@PostMapping("/refuse")
	@ResponseBody
	public String master_refuse(BackOfficeVO bvo, EmailVO evo) {
		log.info("BackOfficeVO:{}", bvo);
		Map<String, String> map = new HashMap<String, String>();

		int flag = service.backoffice_refuse(bvo);
		if (flag == 1) {

			BackOfficeVO bvo2 = sendEmail.result_refuse(bvo, evo);
			log.info("successed...");
			log.info("bvo2:{}", bvo2);

			map.put("result", "1");

		} else { // 백오피스 거절 실패
			log.info("failed...");

			map.put("result", "0");
		}

		String json = gson.toJson(map);

		return json;
	}

	/**
	 * 마스터 - 호스트 탈퇴 신청 리스트
	 */
	@ApiOperation(value = "마스터 - 호스트 탈퇴 신청 처리", notes = "호스트 탈퇴 신청 리스트")
	@GetMapping("/backoffice_end")
	public String backoffice_end(Model model, @RequestParam(value = "page", defaultValue = "1") Integer page) {
		log.info("page:{}", page);

		List<BackOfficeListVO> bvos = service.backoffice_endList_selectAll(page);
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
	public String master_revoke(BackOfficeVO bvo, EmailVO evo) {
		log.info("BackOfficeVO:{}", bvo);
		Map<String, String> map = new HashMap<String, String>();

		int flag = service.backoffice_revoke(bvo);
		if (flag == 1) {
			BackOfficeVO bvo2 = sendEmail.backoffice_revoke(bvo, evo);
			if (bvo2 != null) {
				log.info("successed...");
				log.info("bvo2:{}", bvo2);
				map.put("result", "1");
			} else { // 이메일 전송 실패
				log.info("failed...");
				map.put("result", "0");
			}
		} else { // 백오피스 승인 실패
			log.info("failed...");
			map.put("result", "0");
		}

		String json = gson.toJson(map);

		return json;
	}

	/**
	 * 백오피스 가입 상세 페이지
	 */
	@ApiOperation(value = "백오피스 가입 상세 페이지", notes = "백오피스 가입 상세 페이지")
	@GetMapping("/backoffice_apply_detail")
	public String master_backoffice_apply_detail(BackOfficeVO bvo, Model model, String page) {
		log.info("backoffice_setting()...");
		BackOfficeVO bvo2 = service.master_backoffice_detail_selectOne(bvo);
		log.info("result: {}.", bvo2);

		OptionEngToKorMap optionEngToKorMap = new OptionEngToKorMap();

		if (bvo2.getBackoffice_tag() != null) {
			String[] backoffice_tag = bvo2.getBackoffice_tag().split(",");
			for (int i = 0; i < backoffice_tag.length; i++) {
				backoffice_tag[i] = "#" + backoffice_tag[i];
			}
			model.addAttribute("backoffice_tag", backoffice_tag);
		} else {
			model.addAttribute("backoffice_tag", "-");
		}

		if (bvo2.getBackoffice_option() != null) {
			List<String> backoffice_option = optionEngToKorMap.splitOption(bvo2.getBackoffice_option());
			model.addAttribute("backoffice_option", backoffice_option);
		} else {
			model.addAttribute("backoffice_option", "-");
		}

		if (bvo2.getBackoffice_around() != null) {
			List<String> backoffice_around = optionEngToKorMap.splitAroundOption(bvo2.getBackoffice_around());
			model.addAttribute("backoffice_around", backoffice_around);
		} else {
			model.addAttribute("backoffice_around", "-");
		}

		List<String> backoffice_image = optionEngToKorMap.splitImage(bvo2.getBackoffice_image());
		List<String> backoffice_type = (optionEngToKorMap.splitType(bvo2.getBackoffice_type()));

		model.addAttribute("backoffice_image", backoffice_image);
		model.addAttribute("backoffice_type", backoffice_type);
		model.addAttribute("vo", bvo2);
		model.addAttribute("page", page);

		model.addAttribute("title", "신청 상세 보기");
		model.addAttribute("content", "thymeleaf/html/master/master_main/apply_detail");

		return "thymeleaf/layouts/master/layout_master";
	}

	/**
	 * 백오피스 탈퇴 상세 페이지
	 */
	@ApiOperation(value = "백오피스 탈퇴 상세 페이지", notes = "백오피스 탈퇴 상세 페이지")
	@GetMapping("/backoffice_end_detail")
	public String master_backoffice_end_detail(BackOfficeVO bvo, Model model, String page) {
		log.info("backoffice_setting()...");
		BackOfficeVO bvo2 = service.master_backoffice_detail_selectOne(bvo);
		log.info("result: {}.", bvo2);

		OptionEngToKorMap optionEngToKorMap = new OptionEngToKorMap();

		if (bvo2.getBackoffice_tag() != null) {
			String[] backoffice_tag = bvo2.getBackoffice_tag().split(",");
			for (int i = 0; i < backoffice_tag.length; i++) {
				backoffice_tag[i] = "#" + backoffice_tag[i];
			}
			model.addAttribute("backoffice_tag", backoffice_tag);
		} else {
			model.addAttribute("backoffice_tag", "-");
		}

		if (bvo2.getBackoffice_option() != null) {
			List<String> backoffice_option = optionEngToKorMap.splitOption(bvo2.getBackoffice_option());
			model.addAttribute("backoffice_option", backoffice_option);
		} else {
			model.addAttribute("backoffice_option", "-");
		}

		if (bvo2.getBackoffice_around() != null) {
			List<String> backoffice_around = optionEngToKorMap.splitAroundOption(bvo2.getBackoffice_around());
			model.addAttribute("backoffice_around", backoffice_around);
		} else {
			model.addAttribute("backoffice_around", "-");
		}

		List<String> backoffice_image = optionEngToKorMap.splitImage(bvo2.getBackoffice_image());
		List<String> backoffice_type = (optionEngToKorMap.splitType(bvo2.getBackoffice_type()));

		model.addAttribute("backoffice_image", backoffice_image);
		model.addAttribute("backoffice_type", backoffice_type);
		model.addAttribute("vo", bvo2);
		model.addAttribute("page", page);

		model.addAttribute("title", "신청 상세 보기");
		model.addAttribute("content", "thymeleaf/html/master/master_main/apply_detail");

		return "thymeleaf/layouts/master/layout_master";
	}

}
