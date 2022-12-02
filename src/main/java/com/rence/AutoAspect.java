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
import com.rence.dashboard.model.ReserveUpdateVO;
import com.rence.dashboard.model.ReserveUpdateVO;
import com.rence.dashboard.service.DashboardService;
import com.rence.office.model.OfficeReserveVO;
import com.rence.office.service.OfficeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
public class AutoAspect {
	
	@Autowired
	DashboardService service;
	
	@Autowired
	BackOfficeService b_service;

	//*******************************************//
	//*************예약 상태 자동 업데이트***************//
	//******************************************//
	@Pointcut("execution(* *..controller.*.*_rsu(..))")
	public void updatePointcut() {

	}
		
	@Before("updatePointcut()")
	public void reserveAutoUpdate() {
		log.info("ReserveAutoUpdate()...");
		service.reserve_state_auto_update();
	}
	
	//*******************************************//
	//************이메일 인증 시간 초과시 삭제*************//
	//******************************************//
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
	
	//*******************************************//
	//**********예약 상태 false 30분 뒤 삭제***********//
	//******************************************//
	@Pointcut("execution(* com.rence.office.controller.OfficeController.reserve(..))")
	public void falsePointcut() {
		log.info("falsePointcut()...");
	}
	
	@After("falsePointcut()")
	public void reserveFalse(JoinPoint jp) {
		log.info("reserveFalse()...");
		Object [] params = jp.getArgs(); 
		OfficeReserveVO reserve = (OfficeReserveVO)params[0];
		
		new Thread() {
			public void run() {
				try {
					log.info("sleep-----------start-------------");
					Thread.sleep(60000);
					log.info("sleep-------------end-----------");
					log.info("reserve::{}",reserve);
					ReserveUpdateVO reserve_no = service.select_one_false_reserve(reserve.getReserve_stime(), reserve.getReserve_etime(), reserve.getRoom_no());
					log.info("reserve::{}",reserve_no);
					service.reserve_auto_delete(reserve_no.getReserve_no());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
		}.start();
	}
	
}
