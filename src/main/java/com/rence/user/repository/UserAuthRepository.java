/**
	 * @author 강경석
	 
*/
package com.rence.user.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.rence.user.model.UserAuthVO;


public interface UserAuthRepository extends JpaRepository<UserAuthVO, Object> {

	//auth테이블 정보 가져오기
	@Query(nativeQuery = true, 
	value="select * from (select auth_no, auth_code, user_email from AUTH where user_email=?1 order by rownum desc)where rownum <= 1")
	public UserAuthVO auth_select(String user_email);
	
//	//auth테이블 정보 가져오기
//	@Query(
//			value="SELECT t from (SELECT t.auth_no, t.auth_code, t.user_email from UserAuthVO t where t.user_email=?1 order by rownum desc) where rownum <= 1")
//	public UserAuthVO auth_select(String user_email);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, 
			value="insert into auth(auth_no, user_email, auth_code) values('a'||seq_auth.nextval, :user_email, :auth_code)")
	public int user_auth_insert(String user_email, String auth_code);
	
	
	
	
	//인증번호 확인
	@Query(nativeQuery = true, 
	value="select * from ( select * from (select user_email,auth_code,auth_no from auth where user_email='?1' order by auth_stime desc) where (rownum between 1 and 1))where auth_code='?2'")
	public UserAuthVO user_authOK_select(String user_email, String email_code);

	//인증완료후 인증정보 테이블에서 삭제
	@Query(nativeQuery = true, 
			value="delete from auth where auth_code=?1 and user_email=?2")
	public int user_auth_delete(String user_email, String email_code);


	
//	@Modifying
//	@Query(
//	  value = 
//	    "insert into Users (name, age, email, status) values (:name, :age, :email, :status)",
//	  nativeQuery = true)
//	void insertUser(@Param("names") String name, @Param("age") Integer age, 
//	  @Param("status") Integer status, @Param("email") String email);
	 

}//end class
