package com.rence.master.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rence.master.model.MasterEntity;
import com.rence.master.model.MasterVO;
import com.rence.master.service.MasterService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags="마스터 컨트롤러")
@RestController
@RequestMapping("/master")
public class MasterController {

	
	@Autowired
	MasterService service;
	
	/**
	 * 마스터 로그인 페이지
	 */
	@ApiOperation(value="마스터 로그인", notes="마스터 로그인 및 마스터 진입")
	@GetMapping("/master_login")
	public String master_login() {
		
		MasterVO vo = new MasterVO();
		vo.setMaster_id("master");
		vo.setMaster_pw("9bc7d305917ebe5a079e78c0e05bbe058192d9739678ec875e791fecd10d4642");
		
		MasterEntity entity = service.master_login(vo);
		
		log.info("entity : {}", entity);
		
		return "master/login";
	}
}
