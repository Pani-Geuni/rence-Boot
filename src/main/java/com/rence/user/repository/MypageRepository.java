/**
	 * @author 강경석
	 
*/
package com.rence.user.repository;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.rence.user.model.UserMileageVO;
import com.rence.user.model.UserMypageVO;

public interface MypageRepository extends JpaRepository<UserMypageVO, Object>  {

	//user_mileage_selectOne
	@Query(nativeQuery = true, value= "select * from USER_MYPAGE_VIEW where user_no = ?1")
	public UserMileageVO findByUser_no(String user_no);

	//비밀번호 변경
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value= "update USER_MYPAGE_VIEW set user_pw= ?1 where user_no = ?2")
	public int user_pw_updateOK(String user_pw, String user_no);

	//	현재 비밀번호 확인
	@Query(nativeQuery = true, value= "select count(*) from USER_MYPAGE_VIEW where user_no = ?1 and user_pw = ?2")
	public int check_now_pw(String user_no, String user_pw);

	//회원탈퇴에 따른 회원상태 수정
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value= "update USER_MYPAGE_VIEW set user_state='N' where user_no = ?1")
	public int user_secedeOK(String user_no);

	//프로필수정
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value= "update USER_MYPAGE_VIEW set user_image=?1 where user_no = ?2")
	public int user_img_updateOK(String user_image, String user_no);
	
	
	//마이페이지
	@Query(nativeQuery = true, value= "select * from (USER_MYPAGE_VIEW where user_no = ?1 order by mileage_no desc)WHERE ROWNUM between 1 and 1")
	public UserMypageVO user_mypage_select(String user_no);
	
	

	

}//end class
