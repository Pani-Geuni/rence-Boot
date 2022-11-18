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

import com.rence.backoffice.model.BackOfficeOperatingTimeVO_datetype;
import com.rence.backoffice.model.BackOfficeVO;

public interface BackOfficeOperatingTimeRepository extends JpaRepository<BackOfficeOperatingTimeVO_datetype, Object> {

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value="INSERT INTO backofficeoperatingtime( opetime_no, mon_stime, mon_etime, tue_stime, tue_etime, wed_stime, wed_etime, thu_stime, thu_etime, fri_stime, fri_etime, sat_stime, sat_etime, sun_stime, sun_etime, backoffice_no, mon_dayoff, tue_dayoff, wed_dayoff, thu_dayoff , fri_dayoff, sat_dayoff, sun_dayoff ) "
			+ "values( 'O'||SEQ_BACKOFFICEOPERATINGTIME.nextval, :mon_stime, :mon_etime, :tue_stime, :tue_etime, :wed_stime, :wed_etime, :thu_stime, :thu_etime,"
			+ "	:fri_stime,:fri_etime, :sat_stime, :sat_etime, :sun_stime, :sun_etime, "
			+ "	:#{#vo?.backoffice_no}, :#{#vo?.mon_dayoff}, :#{#vo?.tue_dayoff}, :#{#vo?.wed_dayoff}, :#{#vo?.thu_dayoff}, :#{#vo?.fri_dayoff}, :#{#vo?.sat_dayoff}, :#{#vo?.sun_dayoff} )")
	public int insert_operating_time(@Param("vo") BackOfficeOperatingTimeVO_datetype ovo2, 
			@Param("mon_stime") Date mon_stime, 
			@Param("mon_etime") Date mon_etime , 
			@Param("tue_stime") Date tue_stime, 
			@Param("tue_etime") Date tue_etime, 
			@Param("wed_stime") Date wed_stime, 
			@Param("wed_etime") Date wed_etime, 
			@Param("thu_stime") Date thu_stime, 
			@Param("thu_etime") Date thu_etime, 
			@Param("fri_stime") Date fri_stime, 
			@Param("fri_etime") Date fri_etime, 
			@Param("sat_stime") Date sat_stime, 
			@Param("sat_etime") Date sat_etime, 
			@Param("sun_stime") Date sun_stime, 
			@Param("sun_etime") Date sun_etime);
	
	//@Param("apply_date") Date apply_date     :#{#vo?.owner_name}

	
}
