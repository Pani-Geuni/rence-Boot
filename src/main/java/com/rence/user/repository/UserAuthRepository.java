/**
	 * @author 강경석
	 
*/
package com.rence.user.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rence.backoffice.model.AuthVO;


public interface UserAuthRepository extends JpaRepository<AuthVO, Object> {

	
	
	
	//auth테이블 정보 가져오기
	@Query(nativeQuery = true, 
	value="select * from(select * from auth where user_email=?1 order by auth_no desc) where rownum <= 1")
	public AuthVO auth_select(String user_email);
//	select * from auth where user_email=?1 rownum <= 1
//	select * from(select * from auth where user_email=?1 order by auth_no desc) where rownum <= 1;
//	"select auth_no, auth_code, user_email from "
//	+ "(select * from auth where user_email=?1 order by rownum desc)where rownum <= 1"
//	

	//인증번호 재전송 관련 테이블 컬럼 중복확인
	@Query(nativeQuery = true, 
	value="select count(*) from auth where user_email=?1 order by auth_no desc")
	public int user_auth_selectCnt(String user_email);
	


	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, 
			value="insert into auth(auth_no, user_email, auth_code) values('A'||seq_auth.nextval, ?1, ?2)")
	public int user_auth_insert(String user_email, String auth_code);
	
	//22-11-28 데이터베이스 전체 테이블 date 기본값(예약관련 날짜 정보제외) CURRENT_DATE로 설정함.
	
	
	
	//인증번호 확인
	@Query(nativeQuery = true, 
	value="select * from ( select * from (select * from auth where user_email=?1 order by auth_stime desc) where rownum between 1 and 1)where auth_code=?2")
	public AuthVO user_authOK_select(String user_email, String email_code);

	//인증완료후 인증정보 테이블에서 삭제
	@Transactional
	@Modifying
	@Query(nativeQuery = true, 
			value="delete from auth where user_email=?1 and auth_code=?2")
	public int user_auth_delete(String user_email, String email_code);

	


}//end class
