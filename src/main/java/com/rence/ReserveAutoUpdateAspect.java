package com.rence;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import com.rence.dashboard.service.DashboardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
public class ReserveAutoUpdateAspect {
	
	@Autowired
	DashboardService service;

//	@Pointcut("execution(public * com..*Service**.*selectOne*(..))") // || 사용하면 두개도 가능
	@Pointcut("execution(* *..controller.*.*(..))")
	public void updatePointcut() {

	}
	
	@Before("updatePointcut()")
	public void ReserveAutoUpdate() {
		log.info("ReserveAutoUpdate()...");
		service.reserve_state_auto_update();
	}
	
}
