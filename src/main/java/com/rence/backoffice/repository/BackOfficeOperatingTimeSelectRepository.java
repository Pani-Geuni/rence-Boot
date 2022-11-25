/**
 * 
 * @author 최진실
 *
 */
package com.rence.backoffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rence.backoffice.model.BackOfficeOperatingTimeVO;

public interface BackOfficeOperatingTimeSelectRepository extends JpaRepository<BackOfficeOperatingTimeVO, Object> {

	@Query(nativeQuery = true, value = "select opetime_no,"
			+ "TO_CHAR(mon_stime,'HH24:MI') mon_stime,TO_CHAR(mon_etime,'HH24:MI') mon_etime,TO_CHAR(tue_stime,'HH24:MI') tue_stime,TO_CHAR(tue_etime,'HH24:MI') tue_etime,"
			+ "TO_CHAR(wed_stime,'HH24:MI') wed_stime,TO_CHAR(wed_etime,'HH24:MI') wed_etime,"
			+ "TO_CHAR(thu_stime,'HH24:MI') thu_stime,TO_CHAR(thu_etime,'HH24:MI') thu_etime,TO_CHAR(fri_stime,'HH24:MI') fri_stime,TO_CHAR(fri_etime,'HH24:MI') fri_etime,"
			+ "TO_CHAR(sat_stime,'HH24:MI') sat_stime,TO_CHAR(sat_etime,'HH24:MI') sat_etime,TO_CHAR(sun_stime,'HH24:MI') sun_stime,TO_CHAR(sun_etime,'HH24:MI') sun_etime,"
			+ "backoffice_no, mon_dayoff, tue_dayoff, wed_dayoff, thu_dayoff, fri_dayoff, sat_dayoff, sun_dayoff"
			+ " from backofficeoperatingtime where backoffice_no=?1")
	public BackOfficeOperatingTimeVO backoffice_setting_selectOne_operatingtime(String backoffice_no);

	
	
}
