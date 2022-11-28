package com.rence.user.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.rence.user.model.UserVO;

public interface UserRepository extends JpaRepository<UserVO, Object> {

	@Query(nativeQuery = true, value = "select * from userinfo where user_state='Y' and user_id= ?1")
	public UserVO findByUser_email(String user_id);

	// 아이디 찾기
	@Query(nativeQuery = true, value = "select * from userinfo where user_state='Y' and user_email= ?1")
	public UserVO user_email_select(String user_email);

	// 비밀번호 찾기(아이디,이메일 가져오기)
	@Query(nativeQuery = true, value = "select * from userinfo where user_state='Y' and user_id=?1 and user_email=?2")
	public UserVO user_id_email_select(String user_id, String user_email);

	// 현재 비밀번호 확인
	@Query(nativeQuery = true, value = "SELECT * from userinfo where user_no=?1")
	public UserVO check_now_pw_selectOne(String user_no);
	
	//	@Query(nativeQuery = true, value = "select count(*) from userinfo where user_no = ?1 and user_pw = ?2")
	//	public int check_now_pw(String user_no, String user_pw);


	// 비밀번호 변경
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "update userinfo set user_pw= ?1 where user_no = ?2")
	public int user_pw_updateOK(String user_pw, String user_no);

	// 초기화된 비밀번호 저장
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "update userinfo set user_pw=?1 where user_id=?2")
	public int user_pw_init(String user_pw, String user_id);

	// 이메일 중복체크
	@Query(nativeQuery = true, value = "select * from userinfo where user_email=?1")
	public UserVO emailCheckOK(String user_email);

//	//이메일 중복체크
//	@Query(value= "select t from UserVO t where t.user_email=?1")
//	public UserVO emailCheckOK(String user_email);

	// 아이디 중복체크
	@Query(nativeQuery = true, value = "select * from userinfo where user_id=?1")
	public UserVO idCheckOK(String user_id);

	// 회원가입 - 테이블에 저장
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "insert into\r\n"
			+ "	userinfo(user_no,user_id,user_pw,user_name,user_email,user_tel,user_birth,user_state)\r\n"
			+ "	values('U'||seq_user.nextval,?1,?2,?3,?4,?5,?6,'Y')")
	public int user_insertOK(String user_id, String user_pw, String user_name, String user_email, String user_tel,
			String user_birth);

	// 회원가입-마일리지테이블에 초기마일리지 세팅을 위해 유저번호 추출
	@Query(nativeQuery = true, value = "select * from( select * from userinfo order by user_no desc ) where rownum between 1 and 1")
	public UserVO user_select_userno();

	// 회원가입 - 회원가입 성공시 초기마일리지 mileage테이블에 저장
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "insert into mileage(mileage_no,mileage_total,mileage_change,mileage_state,user_no) values('M'||seq_mileage.nextval,0,0,'T',?1)")
	public int user_mileage_zero_insert(String user_no);

	@Query(nativeQuery = true, value = "SELECT * from userinfo where user_id=?1 and user_state !='N'")
	public UserVO user_login_info(String username);

	

}// end class
