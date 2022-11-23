/**
 * 
 * @author 최진실
 *
 */
package com.rence.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rence.dashboard.model.ScheduleListView;

public interface ScheduleListRepository extends JpaRepository<ScheduleListView, Object> {

	// 일정 관리 - 리스트 해당 날짜에 예약이 있는 공간 -> vo에는 reserve_state 없음
	@Query(nativeQuery = true, value = "select room_no, backoffice_no, room_type, room_name, reserve_cnt from(SCHEDULE_LIST_VIEW)A where A.no=1 and backoffice_no=?1 and ((reserve_stime between To_date(?2,'YYYY-MM-DD HH24:MI:SS') and To_date(?3,'YYYY-MM-DD HH24:MI:SS'))or(reserve_etime between To_date(?2,'YYYY-MM-DD HH24:MI:SS') and To_date(?3,'YYYY-MM-DD HH24:MI:SS')))")
	public List<ScheduleListView> backoffice_schedule_list(String backoffice_no, String reserve_stime, String reserve_etime);
	
	// 일정 관리 - 백오피스가 가진 모든 공간 리스트
	@Query(nativeQuery = true, value = "select * from (select ROW_NUMBER() OVER(PARTITION BY rm.room_no ORDER BY rm.room_no ASC ) no, rm.room_no, rm.room_type, rm.room_name, rm.backoffice_no, reserve_stime, reserve_etime,  count(rv.reserve_no) OVER(PARTITION BY rm.room_no) as reserve_cnt from roominfo rm left outer join reserveinfo rv on rm.room_no=rv.room_no where rm.backoffice_no=?1)A where A.no=1")
	public List<ScheduleListView> backoffice_schedule_list_All(String backoffice_no);

	

}
