package com.rence.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rence.user.model.UserVO;

public interface UserRepository extends JpaRepository<UserVO, Object> {
	
	//로그인
	@Query(nativeQuery = true, value= "select * from userinfo where user_id = ?1 and user_pw= ?2")
	UserVO user_loginOK(String user_id, String string);

	//아이디 찾기
	@Query(nativeQuery = true, value= "select * from userinfo where user_state='Y' and user_email= ?1")
	UserVO user_email_select(String user_email);

	
	//비밀번호 찾기(아이디,이메일 가져오기)
	@Query(nativeQuery = true, value= "select * from userinfo where user_state='Y' and user_id=?1 and user_email= ?2")
	UserVO user_id_email_select(String user_id, String user_email);
	
	
	
}//end class
