/**
 * 
 * @author 최진실
 *
 */
package com.rence;

import java.util.Iterator;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

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

//	@Pointcut("execution(public * com..*Service**.*selectOne*(..))") // || 사용하면 두개도 가능
//	@Pointcut("execution(* *..controller.*.*(..))")
//	public void updatePointcut() {
//
//	}
//		
//	@Before("updatePointcut()")
//	public void reserveAutoUpdate() {
//		log.info("ReserveAutoUpdate()...");
//		service.reserve_state_auto_update();
//	}
	
	@Pointcut("execution(* com.rence.*.*.*SendEmail.*(..))")
	public void deletePointcut() {
		log.info("deletePointcut()...");
	}
	
	@After("deletePointcut()")
	public void authDelete() {
		log.info("authDelete()...");
		new Thread() {
			public void run() {
				try {
					log.info("sleep-----------start-------------");
		            Thread.sleep(121000);
		            log.info("sleep-------------end-----------");
		            b_service.auth_auto_delete();
		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }
			};
		}.start();
	}
	
}
