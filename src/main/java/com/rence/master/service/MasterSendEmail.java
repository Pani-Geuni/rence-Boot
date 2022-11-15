/**
 * 
 * @author 최진실
 *
 */
package com.rence.master.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rence.backoffice.model.BackOfficeVO;
import com.rence.backoffice.model.EmailVO;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

@Component
public class MasterSendEmail {

	@Autowired
	JavaMailSender javaMailSender;

	//////////////////////////////////////////////////
	// ******* (가입 승인) 비밀번호 초기화 링크 전송 *******//
	/////////////////////////////////////////////////
	public BackOfficeVO settingPw(BackOfficeVO bvo, EmailVO evo) throws UnsupportedEncodingException {

//		String originText = bvo.getBackoffice_no();
//
//		String encText = aes.encryptAES("0123456789abcdefghij0123456789ab", originText, false);
//        System.out.println("encText (encodeBase64) : " + encText);

        
        String target = bvo.getBackoffice_no();
        byte[] targetBytes = target.getBytes("UTF-8");
        
        Encoder encoder = Base64.getEncoder();
        
        String encodedString = encoder.encodeToString(targetBytes);

		// 이메일 제목, 내용 설정
		evo.setSubject("[rence] 호스트 비밀번호 설정");
		evo.setContent("아래의 링크에 접속하여 비밀번호를 재설정 해주시길 바랍니다.");

		// 비밀번호 재설정

		try {
			// 전송
			MimeMessage msg = javaMailSender.createMimeMessage();
			msg.setSubject(evo.getSubject());
			msg.setText("비밀번호 재설정 링크 : " + "http://localhost:8090/rence/backoffice_setting_pw?backoffice_no="
					+ encodedString);
			msg.setRecipient(RecipientType.TO, new InternetAddress(bvo.getBackoffice_email()));

			
			javaMailSender.send(msg);
		} catch (MessagingException e) {
			bvo = null;
		}
		return bvo;
	}



	///////////////////////////////
	// ******* (가입 거절) *******//
	//////////////////////////////
	public BackOfficeVO result_refuse(BackOfficeVO bvo, EmailVO evo) {
		evo.setSubject("[rence] 호스트 가입 신청 결과");
		evo.setContent("호스트 신청을 거절당하셨습니다.");
		
		try {
			// 전송
			MimeMessage msg = javaMailSender.createMimeMessage();
			msg.setSubject(evo.getSubject());
			msg.setText(evo.getContent());
			msg.setRecipient(RecipientType.TO, new InternetAddress(bvo.getBackoffice_email()));

			javaMailSender.send(msg);
		} catch (MessagingException e) {
			bvo = null;
		}
		return bvo;
	}

	
	
	///////////////////////////////
	// ******* (탈퇴 완료) *******//
	//////////////////////////////
	public BackOfficeVO backoffice_revoke(BackOfficeVO bvo, EmailVO evo) {
		evo.setSubject("[rence] 호스트 탈퇴 신청 결과");
		evo.setContent("호스트 탈퇴가 완료되었습니다.");
		
		try {
			// 전송
			MimeMessage msg = javaMailSender.createMimeMessage();
			msg.setSubject(evo.getSubject());
			msg.setText(evo.getContent());
			msg.setRecipient(RecipientType.TO, new InternetAddress(bvo.getBackoffice_email()));

			javaMailSender.send(msg);
		} catch (MessagingException e) {
			bvo = null;
		}
		return bvo;
	}
	

}
