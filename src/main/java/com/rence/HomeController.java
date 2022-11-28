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
import com.rence.user.model.EmailVO;
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

}
