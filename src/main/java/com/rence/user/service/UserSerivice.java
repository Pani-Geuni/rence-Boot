package com.rence.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rence.user.model.UserVO;
import com.rence.user.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserSerivice {
	
	@Autowired
	UserRepository repository;

	
	//로그인
	public UserVO User_loginOK(UserVO uvo) {
		log.info("User_loginOK()....");
		log.info("uvo: {}",uvo);
		return repository.user_loginOK(uvo.getUser_id(),uvo.getUser_pw());
	}

	//아이디 찾기
	public UserVO user_email_select(UserVO uvo) {
		log.info("backoffice_id_email_select()....");
		log.info("uvo: {}",uvo);
		return repository.user_email_select(uvo.getUser_email());
	}

	//비밀번호 찾기
	public UserVO user_id_email_select(UserVO uvo) {
		log.info("backoffice_id_email_select()....");
		log.info("uvo: {}",uvo);
		return repository.user_id_email_select(uvo.getUser_id(),uvo.getUser_email());
	}

	//비밀번호 저장
	public int user_pw_init(UserVO uvo2) {
		// TODO Auto-generated method stub
		return 0;
	}
	

	

}//end class
