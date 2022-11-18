package com.rence.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rence.user.model.UserVO;

public interface UserNTERepository extends JpaRepository<UserVO, Object>{

	@Query(nativeQuery = true, value = "select * from userinfo where user_no=?1")
	public UserVO select_user_nte(String user_no);

}
