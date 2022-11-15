package com.rence.backoffice.service;

import java.util.Date;

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



	



	
}
