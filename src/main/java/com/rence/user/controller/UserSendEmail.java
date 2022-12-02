/**
* @author 강경석
* 이메일 인증 및 아이디/비밀번호 찾기
*/

package com.rence.user.controller;

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
import com.rence.user.model.EmailVO;
import com.rence.user.model.UserVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserSendEmail {

	@Autowired
	JavaMailSender javaMailSender;

	// **********************
	// 회원가입 이메일 인증
	// **********************
	public AuthVO sendEmail(AuthVO vo, EmailVO evo) {
		log.info("User sendEmail");
		log.info("vo: {}", vo);

		// 이메일 제목, 내용 설정
		evo.setSubject("[rence] 회원가입 이메일 인증코드");

		// 인증코드 생성
		int RANDOM_BOUND = 100000;
		ThreadLocalRandom random = ThreadLocalRandom.current();
		vo.setAuth_code(String.valueOf(random.nextInt(RANDOM_BOUND, RANDOM_BOUND * 10)));
		log.info("RANDOM_BOUND: {}", RANDOM_BOUND);
		try {

			// 전송
			MimeMessage msg = javaMailSender.createMimeMessage();
			msg.setSubject(evo.getSubject());
			msg.setContent(
					"안녕하세요. 공간공유플랫폼" + "<font color=#009900 > RENCE</font>" + "입니다." + "<br><br>"
							+ "회원가입 인증코드는 다음과 같습니다." + "<br><br>" + "<strong>인증 코드</strong> : " + vo.getAuth_code(),
					"text/html; charset=utf-8");

			msg.setRecipient(RecipientType.TO, new InternetAddress(vo.getUser_email()));

			javaMailSender.send(msg);
		} catch (MessagingException e) {
			vo = null;
		}
		return vo;
	}

	// **********************
	// 아이디 찾기
	// **********************
	public UserVO findId(UserVO uvo, EmailVO evo) {
		log.info("User findId");
		log.info("uvo: {}", uvo);
		// 이메일 제목, 내용 설정
		evo.setSubject("[rence] User 아이디 재설정");

		// evo.setContent("귀하의 아이디는 다음과 같습니다.");

		try {
			// 전송
			MimeMessage msg = javaMailSender.createMimeMessage();
			msg.setSubject(evo.getSubject());
			msg.setContent("안녕하세요. 공간공유플랫폼" + "<font color=\"green\"> RENCE</font>" + "입니다." + "<br><br>"
					+ uvo.getUser_name() + " 회원님의 아이디는 다음과 같습니다" +

					"<br><br>" + "<strong>아이디 : </strong>" + uvo.getUser_id(), "text/html; charset=utf-8");
			msg.setRecipient(RecipientType.TO, new InternetAddress(uvo.getUser_email()));

			javaMailSender.send(msg);
		} catch (MessagingException e) {
			uvo = null;
		}
		return uvo;
	}

	// **********************
	// 비밀번호 찾기
	// **********************
	public UserVO findPw(UserVO uvo, EmailVO evo) {
		log.info("User findPw");
		log.info("uvo: {}", uvo);

		String random_pw = RandomStringUtils.randomAlphanumeric(10);
		log.info("Create random_pw: {}", random_pw);

		// 비밀번호 암호화
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		uvo.setUser_pw(encoder.encode(random_pw));
		log.info("uvo.getUser_pw: {}", uvo.getUser_pw());

		// 이메일 제목, 내용 설정
		evo.setSubject("[rence] 비밀번호 재설정");

		try {
			// 전송
			MimeMessage msg = javaMailSender.createMimeMessage();
			msg.setSubject(evo.getSubject());
			msg.setContent(
					"안녕하세요. 공간공유플랫폼" + "<font color=\"green\"> RENCE</font>" + "입니다." + "<br><br>" + uvo.getUser_name()
							+ " 회원님의 초기화된 비밀번호는 다음과 같습니다" + "<br><br>" + "<font color=\"red\">로그인후 재설정을 권장합니다 </font>"
							+ "<br><br>" + "<strong>초기화 비밀번호 : </strong>" + random_pw,
					"text/html; charset=utf-8");
			msg.setRecipient(RecipientType.TO, new InternetAddress(uvo.getUser_email()));

			javaMailSender.send(msg);
		} catch (MessagingException e) {
			uvo = null;
		}

		return uvo;
	}

}// end class
