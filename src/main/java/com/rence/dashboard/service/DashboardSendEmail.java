/**
 * 
 * @author 최진실
 *
 */
package com.rence.dashboard.service;

import java.util.concurrent.ThreadLocalRandom;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.rence.backoffice.model.AuthVO;
import com.rence.backoffice.model.BackOfficeVO;
import com.rence.backoffice.model.EmailVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DashboardSendEmail {

	@Autowired
	JavaMailSender javaMailSender;

	///////////////////////////////
	// ******* 예약 취소 이메일 전송 *******//
	//////////////////////////////
	public int reserve_cancel_mail(String user_no, String user_email, String reserve_stime, String reserve_etime, String company_name) {
		
		int flag = 1;
		
		try {
			MimeMessage msg = javaMailSender.createMimeMessage();
			msg.setSubject("[rence] 예약 취소 안내");
			msg.setContent("안녕하세요."+ company_name +"입니다."+"<br><br>"+reserve_stime+"~"+reserve_etime+"<br><br>"+"예정된 예약이 내부 사정으로 인해 불가피하게 취소 됨을 알려드립니다."+"<br><br>"+"빠른 시일 내로 환불 처리를 진행해 드리겠습니다."+"<br><br>"+"불편을 드려 대단히 죄송합니다.","text/html; charset=utf-8");
			msg.setRecipient(RecipientType.TO, new InternetAddress(user_email));

			javaMailSender.send(msg);
		} catch (MessagingException e) {
			flag = 0;
		}
		return flag;

	}

}
