package com.rence.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rence.user.model.UserAuthVO;
import com.rence.user.model.UserVO;
import com.rence.user.repository.UserAuthRepository;
import com.rence.user.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService implements UserDetailsService {

	@Autowired
	UserRepository repository;

	@Autowired
	UserAuthRepository authRepository;

	@Override
	public UserDetails loadUserByUsername(String user_id) throws UsernameNotFoundException {
		UserVO member = repository.findByUser_email(user_id);
		log.info("member:{}", member);

		if (member == null)
			throw new UsernameNotFoundException("Not founc account.");

		return member;
	}

	// 로그인
	public UserVO User_loginOK(UserVO uvo) {
		log.info("User_loginOK()....");
		log.info("uvo: {}", uvo);
		return repository.user_loginOK(uvo.getUser_id(), uvo.getUser_pw());
	}

	// 아이디 찾기
	public UserVO user_email_select(UserVO uvo) {
		log.info("backoffice_id_email_select()....");
		log.info("uvo: {}", uvo);
		return repository.user_email_select(uvo.getUser_email());
	}

	// 비밀번호 찾기
	public UserVO user_id_email_select(UserVO uvo) {
		log.info("user_id_email_select()....");
		log.info("uvo: {}", uvo);
		return repository.user_id_email_select(uvo.getUser_id(), uvo.getUser_email());
	}

	// 비밀번호 저장
	public int user_pw_init(UserVO uvo) {
		log.info("user_pw_init()....");
		log.info("uvo: {}", uvo);
		// 비밀번호 암호화
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		uvo.setUser_pw(encoder.encode(uvo.getPassword()));
		log.info("uvo: {}", uvo);
		
		return repository.user_pw_init(uvo.getUser_pw(), uvo.getUser_id());
	}

	// 이메일 중복 체크
	public UserVO emailCheckOK(UserVO uvo) {
		log.info("emailCheckOK()....");
		log.info("uvo: {}", uvo);

		return repository.emailCheckOK(uvo.getUser_email());
	}

	// 이메일 인증번호 auth테이블에 저장
	public UserAuthVO user_auth_insert(UserAuthVO avo) {
		log.info("user_auth_insert()....");
		log.info("avo: {}", avo);
		int result = authRepository.user_auth_insert(avo.getUser_email(), avo.getAuth_code());
		log.info("result: {}", result);
		log.info("avo(이후): {}", avo);
		UserAuthVO avo2 = new UserAuthVO();

		if (result == 1) {
			log.info("===avo===: {}", avo);
			avo2 = authRepository.auth_select(avo.getUser_email());
			log.info("avo2: {}", avo2);
		}

		return avo2;
	}
	
	//인증번호 재전송 관련 테이블 컬럼 중복확인
	public int user_auth_selectCnt(UserAuthVO avo) {
		log.info("user_auth_selectAll()....");
		log.info("avo: {}", avo);
		
		return authRepository.user_auth_selectCnt(avo.getUser_email());
	}

	// 이메일 인증번호 확인
	public UserAuthVO user_authOK_select(String user_email, String email_code) {
		log.info("user_authOK_select()....");
		log.info("user_email: {}", user_email);
		log.info("email_code(인증코드): {}", email_code);

		return authRepository.user_authOK_select(user_email, email_code);
	}

	// 인증을 완료후 auth테이블에서 인증정보 컬럼삭제
	public int user_auth_delete(String user_email, String email_code) {
		log.info("user_auth_delete()....");
		log.info("user_email: {}", user_email);
		log.info("email_code(인증코드): {}", email_code);
		return authRepository.user_auth_delete(user_email, email_code);

	}

	// 아이디 중복체크
	public UserVO idCheckOK(UserVO uvo) {
		log.info("idCheckOK()....");
		log.info("uvo: {}", uvo);

		return repository.idCheckOK(uvo.getUser_id());

	}

	// 회원가입 - 테이블에 저장
	public int user_insertOK(UserVO uvo) {
		log.info("user_insertOK()....");
		log.info("uvo: {}", uvo);
		// 비밀번호 암호화
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		uvo.setUser_pw(encoder.encode(uvo.getPassword()));
		return repository.user_insertOK(uvo.getUser_id(), uvo.getUser_pw(), uvo.getUser_name(), uvo.getUser_email(),
				uvo.getUser_tel(), uvo.getUser_birth());
	}

	// 회원가입-마일리지테이블에 초기마일리지 세팅을 위해 유저번호 추출
	public UserVO user_select_userno() {
		log.info("user_insertOK()....");

		return repository.user_select_userno();
	}

	// 회원가입 - 회원가입 성공시 초기마일리지 mileage테이블에 저장
	public int user_mileage_zero_insert(UserVO uvo2) {
		log.info("user_insertOK()....");
		log.info("uvo: {}", uvo2);
		return repository.user_mileage_zero_insert(uvo2.getUser_no());
	}

	public UserVO user_login_info(String username) {
		log.info("user_login_info()....");
		return repository.user_login_info(username);
	}

	

}// end class
