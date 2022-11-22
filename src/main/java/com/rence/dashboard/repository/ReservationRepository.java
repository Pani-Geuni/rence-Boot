package com.rence.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rence.dashboard.model.ReservationView;

public interface ReservationRepository extends JpaRepository<ReservationView, Object>{

	@Query(nativeQuery = true, value = "select * from RESERVATION_VIEW where backoffice_no=?1 and ((reserve_stime between To_date(?2,'YYYY-MM-DD HH24:MI:SS') and To_date(?3,'YYYY-MM-DD HH24:MI:SS'))or(reserve_etime between To_date(?2,'YYYY-MM-DD HH24:MI:SS') and To_date(?3,'YYYY-MM-DD HH24:MI:SS'))) and room_no=?4 and (reserve_state='in_use' or reserve_state='begin')")
	public List<ReservationView> backoffice_reservation_list(String backoffice_no, String reserve_stime, String reserve_etime,
			String room_no);

}
