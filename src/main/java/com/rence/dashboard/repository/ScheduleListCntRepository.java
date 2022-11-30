///**
// * 
// * @author 최진실
// *
// */
//package com.rence.dashboard.repository;
//
//import java.util.List;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import com.rence.dashboard.model.ReserveCntVO;
//import com.rence.dashboard.model.ScheduleListView;
//
//public interface ScheduleListCntRepository extends JpaRepository<ReserveCntVO, Object> {
//
//	@Query(nativeQuery = true, value = "select reserve_cnt, room_no  from \r\n"
//			+ "(select ROW_NUMBER() OVER(PARTITION BY room_no ORDER BY room_no ASC ) no, count(reserve_no) OVER(PARTITION BY room_no) reserve_cnt, room_no from reserveinfo\r\n"
//			+ "where backoffice_no=?1 and reserve_state!='false' and reserve_state!='cancel' and reserve_state!='end' and ((reserve_stime between To_date(?2,'YYYY-MM-DD HH24:MI:SS') and To_date(?3,'YYYY-MM-DD HH24:MI:SS'))or(reserve_etime between To_date(?2,'YYYY-MM-DD HH24:MI:SS') and To_date(?3,'YYYY-MM-DD HH24:MI:SS')))\r\n"
//			+ ")A where A.no =1")
//	public List<ReserveCntVO> backoffice_schedule_list_cnt(String backoffice_no, String reserve_stime, String reserve_etime);
//	
//
//}
