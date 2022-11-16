/**
 * 
 * @author 최진실
 *
 */
package com.rence.backoffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rence.backoffice.model.AuthVO;

public interface AuthRepository extends JpaRepository<AuthVO, Object> {
	
	@Query(nativeQuery = true, value="SELECT auth_no, auth_code, user_email from (SELECT auth_no, auth_code, user_email from auth where user_email=?1 order by rownum desc) where rownum = 1")
	public AuthVO findbyAuth(String user_email);

	@Query(nativeQuery = true, value=" SELECT * from (SELECT * from (SELECT * from auth where user_email=?1 order by rownum desc) where rownum = 1) where auth_code=?2 and user_email=?1")
	public AuthVO findbyAuthOK(String backoffice_email, String auth_code);
	
	@Query(nativeQuery = true, value="DELETE FROM auth where auth_no = ?1")
	public int deleteByAuth_no(String auth_no);

	
}
