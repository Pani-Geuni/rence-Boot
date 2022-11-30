/**
 * 
 * @author 최진실
 *
 */
package com.rence;

import java.util.Iterator;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import com.rence.backoffice.model.AuthVO;
import com.rence.backoffice.service.BackOfficeService;
import com.rence.dashboard.service.DashboardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
public class AutoAspect {
	
	@Autowired
	DashboardService service;
	
	@Autowired
	BackOfficeService b_service;

	@Pointcut("execution(* *..controller.*.*(..))")
	public void updatePointcut() {

	}
		
	@Before("updatePointcut()")
	public void reserveAutoUpdate() {
		log.info("ReserveAutoUpdate()...");
		service.reserve_state_auto_update();
	}
	
	@Pointcut("execution(* com.rence.*.*.*SendEmail.sendEmail(..))")
	public void deletePointcut() {
		log.info("deletePointcut()...");
	}
	
	@After("deletePointcut()")
	   public void authDelete(JoinPoint jp) {
	      log.info("authDelete()...");
	      Object [] params = jp.getArgs(); 
	      AuthVO auth = (AuthVO)params[0];
	     
	      new Thread() {
	         public void run() {
	            try {
	               log.info("sleep-----------start-------------");
	                  Thread.sleep(120000);
	                  log.info("sleep-------------end-----------");
	                  log.info("auth::{}",auth);
	                  b_service.auth_auto_delete(auth.getUser_email());
	              } catch (InterruptedException e) {
	                  e.printStackTrace();
	              }
	         };
	      }.start();
	   }
	
}
