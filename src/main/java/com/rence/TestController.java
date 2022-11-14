package com.rence;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags="테스트 컨트롤러")
@RestController
@RequestMapping("/test")
public class TestController {

	@ApiOperation(value="테스트", notes="테스트 함수")
	@GetMapping("/")
	public String test() {
		
		log.info("test controller");
		return "test";
	}
}
