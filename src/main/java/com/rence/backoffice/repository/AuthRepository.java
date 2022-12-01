/**
 * 
 * @author 최진실
 *
 */
package com.rence.backoffice.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.rence.backoffice.model.AuthVO;

public interface AuthRepository extends JpaRepository<AuthVO, Object> {
	
	@Query(nativeQuery = true, value="SELECT * from (SELECT * from auth where user_email=?1 order by rownum desc) where rownum = 1")
	public AuthVO findbyAuth(String user_email);

	@Query(nativeQuery = true, value=" SELECT * from (SELECT * from auth where user_email=?1 order by rownum desc) where rownum = 1 and (LOCALTIMESTAMP <= auth_stime + 2/(24*60)) and auth_code=?2 and user_email=?1")
	public AuthVO findbyAuthOK(String backoffice_email, String auth_code);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value="DELETE FROM auth where auth_no = ?1")
	public int deleteByAuth_no(String auth_no);

	@Modifying
	@Transactional 
	@Query(nativeQuery = true, value="insert into auth(auth_no, user_email, auth_code, auth_stime) values ('A'||SEQ_AUTH.NEXTVAL, :#{#avo?.user_email}, :#{#avo?.auth_code}, :auth_stime )")
	public int insert_auth_info(@Param("avo") AuthVO avo, @Param("auth_stime") Date auth_stime );

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value="delete from auth where LOCALTIMESTAMP > auth_stime + 2/(24*60) and user_email=?1")
	public void auth_auto_delete(String user_email);

	// 이메일 재전송 가능 여부
	@Query(nativeQuery = true, value="SELECT * from auth where user_email=?1")
	public AuthVO backoffice_auth_overlap(String backoffice_email);

	
}
