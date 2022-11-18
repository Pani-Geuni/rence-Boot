/**
 * 
 * @author 최진실
 *
 */
package com.rence.backoffice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.rence.backoffice.model.BackOfficeListVO;
import com.rence.backoffice.model.BackOfficeVO;

public interface BackOfficeRepository extends JpaRepository<BackOfficeVO, Object> {

	@Query(nativeQuery = true, value="SELECT * from backofficeinfo where backoffice_email=?1 and backoffice_state !='X'")
	public BackOfficeVO select_backoffice_no(String backoffice_email);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value="INSERT INTO backofficeinfo(backoffice_no, owner_name, backoffice_id, backoffice_name, company_name, backoffice_tel, backoffice_email,zipcode, roadname_address, number_address, detail_address, backoffice_tag, backoffice_info, backoffice_option, backoffice_around, backoffice_image, host_image, backoffice_state, apply_date, backoffice_type) "
			+ "values('B'||seq_backoffice.nextval, :#{#vo?.owner_name}, :#{#vo?.backoffice_id}, :#{#vo?.backoffice_name}, :#{#vo?.company_name}, :#{#vo?.backoffice_tel}, :#{#vo?.backoffice_email}, :#{#vo?.zipcode},:#{#vo?.roadname_address}, :#{#vo?.number_address}, :#{#vo?.detail_address}, :#{#vo?.backoffice_tag}, :#{#vo?.backoffice_info}, :#{#vo?.backoffice_option}, :#{#vo?.backoffice_around}, :#{#vo?.backoffice_image}, :#{#vo?.host_image}, :#{#vo?.backoffice_state}, :apply_date ,:#{#vo?.backoffice_type})")
	public int insert_backoffice(@Param("vo") BackOfficeVO vo, @Param("apply_date") Date apply_date);

	@Query(nativeQuery = true, value="SELECT * from backofficeinfo where backoffice_email=?1 and backoffice_state !='X'")
	public BackOfficeVO backoffice_email_check(String backoffice_email);

	@Query(nativeQuery = true, value="select * from backofficeinfo where backoffice_id=?1 and backoffice_email=?2 and backoffice_state !='X'")
	public BackOfficeVO select_backoffice_by_id_email(String backoffice_id, String backoffice_email);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value="UPDATE backofficeinfo SET backoffice_pw=?1 where backoffice_no=?2")
	public int update_backoffice_temp_pw(String backoffice_pw, String backoffice_no);

	@Query(nativeQuery = true, value="select * from backofficeinfo where backoffice_id=?1 and (backoffice_state='Y' or backoffice_state='O')")
	public BackOfficeVO findByBackoffice_email(String backoffice_id); 


	// 마스터
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value="UPDATE backofficeinfo SET backoffice_state='Y' where backoffice_no=?1 and backoffice_email=?2")
	public int update_backoffice_state_y(String backoffice_no, String backoffice_email);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value="UPDATE backofficeinfo SET backoffice_state='N' where backoffice_no=?1 and backoffice_email=?2")
	public int update_backoffice_state_N(String backoffice_no, String backoffice_email);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value="UPDATE backofficeinfo SET backoffice_state='X' where backoffice_no=?1 and backoffice_email=?2")
	public int update_backoffice_state_X(String backoffice_no, String backoffice_email);

	@Query(nativeQuery = true, value="SELECT * from backofficeinfo where backoffice_no=?1")
	public BackOfficeVO selectOne_backoffice_detail_m(String backoffice_no);

	@Query(nativeQuery = true, value="SELECT * from backofficeinfo where backoffice_id=?1 and backoffice_state !='X'")
	public BackOfficeVO backoffice_login_info(String username);

	// 대시보드
	// 공간 관리(추가) - 백오피스 타입
	@Query(nativeQuery = true, value="select * from backofficeinfo where backoffice_no=?1")
	public BackOfficeVO select_one_backoffice_info(String backoffice_no);

	// 환경 설정
	@Query(nativeQuery = true, value="select * from backofficeinfo where backoffice_no=?1")
	public BackOfficeVO backoffice_setting_selectOne(String backoffice_no);

	// 환경설정 - 비밀번호 변경을 위한 기존 비밀번호 확인
	@Query(nativeQuery = true, value="SELECT * from backofficeinfo where backoffice_no=?1")
	public BackOfficeVO backoffice_select_pw(String backoffice_no);
//	@Query(nativeQuery = true, value="SELECT * from backofficeinfo where backoffice_no=?1 and backoffice_pw=?2")
//	public BackOfficeVO backoffice_select_pw(String backoffice_no, String backoffice_pw);

	// 업체 탈퇴 요청
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value="UPDATE backofficeinfo SET backoffice_state='O',apply_date=sysdate where backoffice_no=?1 and backoffice_no not in (select backoffice_no from reserveinfo where backoffice_no=?1 and (reserve_state='begin' or reserve_state='in_use'))")
	public int update_backoffice_state_o(String backoffice_no);

	// 정산 상태 변경
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value="update paymentinfo set sales_state='T', payment_date=sysdate where backoffice_no=?1 and room_no=?2 and payment_no=?3")
	public int backoffice_updateOK_sales_state_t(String backoffice_no, String room_no, String payment_no);




	
}
