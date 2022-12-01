package com.rence.office.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rence.office.model.RoomScheduleVO;

public interface RoomScheduleRepository extends JpaRepository<RoomScheduleVO, Object> {

	
	@Query(nativeQuery = true, value = "select * from roomschedule where backoffice_no=?1 and room_no=?2 and trunc(not_stime)=to_date(?3, 'YYYY/MM/DD')")
	public List<RoomScheduleVO> select_all_room_schedule(String backoffice_no, String room_no, String reserve_stime);
	
	@Query(nativeQuery = true, value = "select * from roomschedule where backoffice_no=?1 and room_no=?2")
	public List<RoomScheduleVO> select_all_room_schedule_dayoff(String backoffice_no, String room_no);
}
