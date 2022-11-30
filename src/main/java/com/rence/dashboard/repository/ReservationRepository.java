package com.rence.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rence.dashboard.model.ReservationView;

public interface ReservationRepository extends JpaRepository<ReservationView, Object>{

	@Query(nativeQuery = true, value = "select * from(select ROWNUM as num, r.* from (select * from RESERVATION_VIEW where backoffice_no=?1 and ( ((reserve_stime > To_date(?2,'YYYY-MM-DD HH24:MI:SS') and reserve_stime < To_date(?3,'YYYY-MM-DD HH24:MI:SS') )or( reserve_etime > To_date(?2,'YYYY-MM-DD HH24:MI:SS') and reserve_etime < To_date(?3,'YYYY-MM-DD HH24:MI:SS')))"
			+ "or (reserve_stime < To_date(?2,'YYYY-MM-DD HH24:MI:SS') and reserve_etime > To_date(?3,'YYYY-MM-DD HH24:MI:SS')) ) and room_no=?4 and (reserve_state='in_use' or reserve_state='begin') order by reserve_stime)r) where num between ?5 and ?6")
	public List<ReservationView> backoffice_reservation_list(String backoffice_no, String reserve_stime, String reserve_etime,
			String room_no, Integer min, Integer max);

	// 예약자 갯수
	@Query(nativeQuery = true, value = "select count(*) from (select * from RESERVATION_VIEW where backoffice_no=?1 and ((reserve_stime between To_date(?2,'YYYY-MM-DD HH24:MI:SS') and To_date(?3,'YYYY-MM-DD HH24:MI:SS'))or(reserve_etime between To_date(?2,'YYYY-MM-DD HH24:MI:SS') and To_date(?3,'YYYY-MM-DD HH24:MI:SS'))) and room_no=?4 and (reserve_state='in_use' or reserve_state='begin'))")
	public int backoffice_reservation_list_cnt(String backoffice_no, String reserve_stime,
			String reserve_etime, String room_no);

}
