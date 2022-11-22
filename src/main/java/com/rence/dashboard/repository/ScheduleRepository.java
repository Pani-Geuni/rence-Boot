/**
 * 
 * @author 최진실
 *
 */
package com.rence.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.rence.dashboard.model.ScheduleEntity;
import com.rence.dashboard.model.ScheduleListView;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Object> {

	// 휴무, 브레이크 타임 설정
	@Modifying
	@Transactional //YYYY-MM-DD HH24:MI:SS
	@Query(nativeQuery = true, value = "insert into roomschedule(schedule_no,not_sdate,not_edate,not_stime,not_etime,room_no,backoffice_no) "
			+ "values('SC'||SEQ_ROOMSCHEDULE.nextval, TO_DATE(?2,'YYYY-MM-DD'), TO_DATE(?3,'YYYY-MM-DD'), TO_DATE(?4,'HH24:MI:SS'), TO_DATE(?5,'HH24:MI:SS'), ?6, ?1 )")
	public int backoffice_schedueOK(String backoffice_no, String not_sdate, String not_edate, String not_stime,
			String not_etime, String room_no);

}
