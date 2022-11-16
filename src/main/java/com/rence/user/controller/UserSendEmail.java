package com.rence.user.controller;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.rence.backoffice.model.AuthVO;
import com.rence.user.model.EmailVO;
import com.rence.user.model.UserVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserSendEmail {
	
	@Autowired
	JavaMailSender javaMailSender;
	
	
	// ******* 이메일 인증 *******//
		public AuthVO sendEmail(AuthVO vo, EmailVO evo) {
			log.info("User sendEmail");
			log.info("vo: {}", vo);

			// 이메일 제목, 내용 설정
			evo.setSubject("[rence] 이메일 인증코드");
			evo.setContent("해당 코드를 인증번호 란에 기입 후, 인증확인을 마쳐주세요.");

			// 인증코드 생성
			int RANDOM_BOUND = 100000;
			ThreadLocalRandom random = ThreadLocalRandom.current();
			vo.setAuth_code(String.valueOf(random.nextInt(RANDOM_BOUND, RANDOM_BOUND * 10)));
			log.info("RANDOM_BOUND: {}", RANDOM_BOUND);
			try {

				// 전송
				MimeMessage msg = javaMailSender.createMimeMessage();
				msg.setSubject(evo.getSubject());
				msg.setText("인증 코드 : " + vo.getAuth_code());
				msg.setRecipient(RecipientType.TO, new InternetAddress(vo.getUser_email()));

				javaMailSender.send(msg);
			} catch (MessagingException e) {
				vo = null;
			}
			return vo;
		}
		
		
		// ******* 아이디 찾기 *******//

		public UserVO findId(UserVO uvo, EmailVO evo) {
			log.info("User findId");
			log.info("uvo: {}", uvo);
			// 이메일 제목, 내용 설정
			evo.setSubject("[rence] User 아이디 재설정");
			//evo.setContent("귀하의 아이디는 다음과 같습니다.");


			try {
				// 전송
				MimeMessage msg = javaMailSender.createMimeMessage();
				msg.setSubject(evo.getSubject());
				msg.setText("아이디 : " + uvo.getUser_id());
				msg.setRecipient(RecipientType.TO, new InternetAddress(uvo.getUser_email()));

				javaMailSender.send(msg);
			} catch (MessagingException e) {
				uvo = null;
			}
			return uvo;
		}
		
		// ******* 비밀번호 찾기 *******//
		public UserVO findPw(UserVO uvo, EmailVO evo) {
			log.info("User findPw");
			log.info("uvo: {}", uvo);

			// 10자리 int형 랜덤난수 생성
			Random random = new Random(); // 랜덤 함수 선언
			int createNum = 0; // 1자리 난수
			String ranNum = ""; // 1자리 난수 형변환 변수
			int len = 10; // 난수 자릿수
			String random_pw = ""; // 결과 난수

			for (int i = 0; i < len; i++) {

				createNum = random.nextInt(9); // 0부터 9까지 올 수 있는 1자리 난수 생성
				ranNum = Integer.toString(createNum); // 1자리 난수를 String으로 형변환
				random_pw += ranNum; // 생성된 난수(문자열)을 원하는 수(len)만큼 더하며 나열
			}
			log.info("Create random_pw: {}", random_pw);
			
			uvo.setUser_pw(random_pw);
			log.info("uvo.getUser_pw: {}", uvo.getUser_pw());

			// 이메일 제목, 내용 설정
			evo.setSubject("[rence] 비밀번호 재설정");
//			evo.setContent("아래의 링크에 접속하여 비밀번호를 재설정 해주시길 바랍니다."); //아직 무슨기능인지 몰라 일단은 주석처리

			// 비밀번호 재설정

			try {
				// 전송
				MimeMessage msg = javaMailSender.createMimeMessage();
				msg.setSubject(evo.getSubject());
				msg.setText(
						"초기화된 비밀번호 입니다. 로그인후 재설정을 권장합니다" + "                       " + "초기화 비밀번호  : " + uvo.getUser_pw());
				msg.setRecipient(RecipientType.TO, new InternetAddress(uvo.getUser_email()));

				javaMailSender.send(msg);
			} catch (MessagingException e) {
				uvo = null;
			}
			return uvo;
		}
	
	
	

}//end class
