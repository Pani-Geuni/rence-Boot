package com.rence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class AOPConfig {

	@Bean
	public ReserveAutoUpdateAspect testPointcutAspect() {
		log.info("ReserveAutoUpdateAspect()...");
		return new ReserveAutoUpdateAspect();
	}
	
}