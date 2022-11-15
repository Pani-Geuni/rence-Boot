/**
 * @author 전판근
 */

package com.rence.master.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rence.master.model.MasterVO;
import com.rence.master.service.MasterService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags="마스터 컨트롤러")
@Controller
@RequestMapping("/master")
public class MasterController {

	
	@Autowired
	MasterService service;
	
	/**
	 * 마스터 로그인 페이지
	 */
	@ApiOperation(value="마스터 로그인", notes="마스터 로그인 및 마스터 진입")
	@GetMapping("/login")
	public String login() {
		
		return "thymeleaf/html/master/login";
		
	}
	
	/**
	 * 로그인 처리
	 */
	@ApiOperation(value="마스터 로그인 처리", notes="마스터 로그인 처리")
	@PostMapping("loginOK")
	@ResponseBody
	public String loginOK(MasterVO mvo, HttpServletResponse res) {
		
		return "redirect:master/main";
	}
	
	
	/**
	 * 로그아웃 처리
	 */
	@ApiOperation(value="로그아웃 처리", notes="마스터 로그아웃 처리")
	@GetMapping("logoutOK")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		
		log.info("master logout...");
		
		return "redirect:login";
	}
	
	/**
	 * 마스터 메인 화면 (호스트 신청 처리 리스트)
	 */
	@ApiOperation(value="마스터 메인/호스트 신청 처리", notes="호스트 신청 처리")
	@GetMapping("main")
	public String main(Model model) {
		log.info("master/main...");
		
		model.addAttribute("title", "마스터 메인/호스트 신청 수락 거절");
		model.addAttribute("content", "thymeleaf/html/master/master_main/main");
		
		return "thymeleaf/layouts/master/layout_master";
	}
	
	
	/**
	 * 마스터 탈퇴 신청 리스트
	 */
	@ApiOperation(value="마스터 메인/호스트 신청 처리", notes="호스트 신청 처리")
	@GetMapping("backoffice_end")
	public String backoffice_end(Model model) {
		log.info("master/backoffice_end...");
		
		model.addAttribute("title", "호스트 탈퇴 신청 리스트");
		model.addAttribute("content", "thymeleaf/html/master/master_main/backoffice_end");
		
		return "thymeleaf/layouts/master/layout_master";
	}
	
}
