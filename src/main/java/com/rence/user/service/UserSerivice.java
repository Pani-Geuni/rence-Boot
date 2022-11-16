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
	
	

}//end class
