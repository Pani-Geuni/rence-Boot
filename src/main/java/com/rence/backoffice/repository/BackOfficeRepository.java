/**
 * 
 * @author 최진실
 *
 */
package com.rence.backoffice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rence.backoffice.model.BackOfficeVO;

public interface BackOfficeRepository extends JpaRepository<BackOfficeVO, Object> {



	@Query(nativeQuery = true, value="SELECT * from backofficeinfo where backoffice_email=?1 and backoffice_state !='X'")
	public BackOfficeVO select_backoffice_no(String backoffice_email);

	@Query(nativeQuery = true, value="INSERT INTO backofficeinfo(backoffice_no, owner_name, backoffice_id, backoffice_name, company_name, backoffice_tel, backoffice_email,zipcode, roadname_address, number_address, detail_address, backoffice_tag, backoffice_info, backoffice_option, backoffice_around, backoffice_image, backoffice_state, backoffice_type) \r\n"
			+ "  	values('B'||seq_backoffice.nextval, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10, ?11, ?12, ?13, ?14, ?15, ?16,'W', ?18)")
	public int insert_backoffice(String backoffice_no, String owner_name, String backoffice_id, String backoffice_name,
			String company_name, String backoffice_tel, String backoffice_email, String zipcode,
			String roadname_address, String number_address, String detail_address, String backoffice_tag,
			String backoffice_info, String backoffice_option, String backoffice_around, String backoffice_image,
			String host_image, String backoffice_state, Date apply_date, String backoffice_type);

	@Query(nativeQuery = true, value="SELECT * from backofficeinfo where backoffice_email=?1 and backoffice_state !='X'")
	public BackOfficeVO backoffice_email_check(String backoffice_email);

	@Query(nativeQuery = true, value="select * from backofficeinfo where backoffice_id=?1 and backoffice_email=?2 and backoffice_state !='X'")
	public BackOfficeVO select_backoffice_by_id_email(String backoffice_id, String backoffice_email);

	@Query(nativeQuery = true, value="UPDATE backofficeinfo SET backoffice_pw=?1 where backoffice_no=?2")
	public int update_backoffice_temp_pw(String backoffice_pw, String backoffice_no);

	@Query(nativeQuery = true, value="select * from backofficeinfo where backoffice_id=?1 and (backoffice_state='Y' or backoffice_state='O')")
	public BackOfficeVO findByBackoffice_email(String backoffice_id); 


	@Query(nativeQuery = true, value="select * from (select rownum as rnum, backoffice_no,TO_CHAR(apply_date, 'YYYY-MM-DD HH24:MI:SS') as apply_date,company_name,owner_name,backoffice_id,backoffice_name,backoffice_tel,backoffice_email from backofficeinfo where backoffice_state='W' order by apply_date desc) where rnum between ?1 and ?2")
	public List<BackOfficeVO> selectAll_backoffice_apply(Integer start_row, Integer end_row);

	@Query(nativeQuery = true, value="UPDATE backofficeinfo SET backoffice_state='Y' where backoffice_no=?1 and backoffice_email=?2")
	public int update_backoffice_state_y(String backoffice_no, String backoffice_email);

	@Query(nativeQuery = true, value="UPDATE backofficeinfo SET backoffice_state='N' where backoffice_no=?1 and backoffice_email=?2")
	public int update_backoffice_state_N(String backoffice_no, String backoffice_email);

	@Query(nativeQuery = true, value="select * from (select rownum as rnum,  backoffice_no,TO_CHAR(apply_date, 'YYYY-MM-DD HH24:MI:SS') as apply_date,company_name,owner_name,backoffice_id,backoffice_name,backoffice_tel,backoffice_email from backofficeinfo where backoffice_state='O' order by apply_date desc) where rnum between ?1 and ?2")
	public List<BackOfficeVO> selectAll_backoffice_end(Integer start_row, Integer end_row);

	@Query(nativeQuery = true, value="UPDATE backofficeinfo SET backoffice_state='X' where backoffice_no=?1 and backoffice_email=?2")
	public int update_backoffice_state_X(String backoffice_no, String backoffice_email);

	@Query(nativeQuery = true, value="SELECT * from backofficeinfo where backoffice_no=?1")
	public BackOfficeVO selectOne_backoffice_detail_m(String backoffice_no);

	@Query(nativeQuery = true, value="SELECT * from backofficeinfo where backoffice_id=?1 and backoffice_state !='X'")
	public BackOfficeVO backoffice_login_info(String username);

	
	//공간 관리(추가) - 백오피스 타입
	@Query(nativeQuery = true, value="select * from backofficeinfo where backoffice_no=?1")
	public BackOfficeVO select_one_backoffice_info(String backoffice_no);
	
	



	
}
