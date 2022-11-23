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
import com.rence.dashboard.model.ScheduleVO;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Object> {

	// 휴무, 브레이크 타임 설정
	@Modifying
	@Transactional //YYYY-MM-DD HH24:MI:SS
	@Query(nativeQuery = true, value = "insert into roomschedule(schedule_no,not_stime,not_etime,room_no,backoffice_no) "
			+ "values('SC'||SEQ_ROOMSCHEDULE.nextval, TO_DATE(?2,'YYYY-MM-DD HH24:MI:SS'), TO_DATE(?3,'YYYY-MM-DD HH24:MI:SS'), ?4, ?1 )")
	public int backoffice_schedueOK(String backoffice_no, String not_stime,
			String not_etime, String room_no);

	// 특정 날짜/시간에 휴무, 브레이크 타임이 설정된 공간 리스트
	@Query(nativeQuery = true, value = "select * from roomschedule where backoffice_no=?1 and "
			+ "(not_stime >= TO_DATE(?2,'YYYY-MM-DD HH24:MI:SS')) and "
			+ "(not_etime <= TO_DATE(?3,'YYYY-MM-DD HH24:MI:SS'))")
	public List<ScheduleEntity> backoffice_schedule_list_exist_off(String backoffice_no, String not_stime, String not_etime);

	// 휴무, 브레이크 타임 취소
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "delete from roomschedule where backoffice_no=?1 and schedule_no=?2")
	public int backoffice_schedule_cancel(String backoffice_no, String schedule_no);

	// 휴무 일정
	@Query(nativeQuery = true, value = "select * from roomschedule where backoffice_no=?1")
	public List<ScheduleEntity> backoffice_schedule_calander(String backoffice_no);

}
