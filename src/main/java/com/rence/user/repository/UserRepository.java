package com.rence.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rence.user.model.UserVO;

public interface UserRepository extends JpaRepository<UserVO, Object> {
	
	//로그인
	@Query(nativeQuery = true, value= "select t from UserVO t where t.user_id = ?1 and t.user_pw= ?2")
	UserVO user_loginOK(String user_id, String string);
	
	


	
	
}//end class
