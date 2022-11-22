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

public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Object> {

	// 휴무, 브레이크 타임 설정
	@Modifying
	@Transactional //YYYY-MM-DD HH24:MI:SS
	@Query(nativeQuery = true, value = "insert into roomschedule(schedule_no,not_stime,not_etime,room_no,backoffice_no) "
			+ "values('SC'||SEQ_ROOMSCHEDULE.nextval, TO_DATE(?2,'YYYY-MM-DD HH24:MI:SS'), TO_DATE(?3,'YYYY-MM-DD HH24:MI:SS'), ?4, ?1 )")
//	@Query(nativeQuery = true, value = "insert into roomschedule(schedule_no,not_sdate,not_edate,not_stime,not_etime,room_no,backoffice_no) "
//			+ "values('SC'||SEQ_ROOMSCHEDULE.nextval, TO_DATE(?2,'YYYY-MM-DD'), TO_DATE(?3,'YYYY-MM-DD'), TO_DATE(?4,'YYYY-MM-DD HH24:MI:SS'), TO_DATE(?5,'YYYY-MM-DD HH24:MI:SS'), ?6, ?1 )")
	public int backoffice_schedueOK(String backoffice_no, String not_stime,
			String not_etime, String room_no);

	// 특정 날짜/시간에 휴무, 브레이크 타임이 설정된 공간 리스트
	@Query(nativeQuery = true, value = "select * from roomschedule where backoffice_no=?1 and "
//			+ "(not_sdate between TO_DATE(?2,'YYYY-MM-DD') and TO_DATE(?3,'YYYY-MM-DD')) and "
//			+ "(not_edate between TO_DATE(?2,'YYYY-MM-DD') and TO_DATE(?3,'YYYY-MM-DD')) and "
			+ "(not_stime >= TO_DATE(?2,'YYYY-MM-DD HH24:MI:SS')) and "
			+ "(not_etime <= TO_DATE(?3,'YYYY-MM-DD HH24:MI:SS'))")
	public List<ScheduleEntity> backoffice_schedule_list_exist_off(String backoffice_no, String not_stime, String not_etime);

}
