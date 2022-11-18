/**
 * 
 * @author 최진실
 *
 */
package com.rence.backoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rence.backoffice.model.BackOfficeListVO;

public interface BackOfficeListRepository extends JpaRepository<BackOfficeListVO, Object> {

	// 마스터
	@Query(nativeQuery = true, value="select * from (select rownum as rnum, backoffice_no,TO_CHAR(apply_date, 'YYYY-MM-DD HH24:MI:SS') as apply_date,company_name,owner_name,backoffice_id,backoffice_name,backoffice_tel,backoffice_email from backofficeinfo where backoffice_state='W' order by apply_date desc) where rnum between ?1 and ?2")
	public List<BackOfficeListVO> selectAll_backoffice_apply(Integer start_row, Integer end_row);
	
	@Query(nativeQuery = true, value="select * from (select rownum as rnum,  backoffice_no,TO_CHAR(apply_date, 'YYYY-MM-DD HH24:MI:SS') as apply_date,company_name,owner_name,backoffice_id,backoffice_name,backoffice_tel,backoffice_email from backofficeinfo where backoffice_state='O' order by apply_date desc) where rnum between ?1 and ?2")
	public List<BackOfficeListVO> selectAll_backoffice_end(Integer start_row, Integer end_row);


	



	
}
