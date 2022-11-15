package com.rence;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags="HOME 컨트롤러")
@Controller
@RequestMapping("/")
public class HomeController {

	@ApiOperation(value="메인 화면 로드", notes="메인 페이지 띄우는 컨트롤러")
	@GetMapping("/")
	public String home(Model model) {
		
		model.addAttribute("content", "thymeleaf/html/office/home");
		
		return "thymeleaf/layouts/office/layout_base";
	}
}
