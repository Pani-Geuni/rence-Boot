/**
 * 
 * @author 최진실
 *
 */
package com.rence.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rence.dashboard.model.ReserveSummaryView;


public interface ReserveSummaryRepository extends JpaRepository<ReserveSummaryView, Object> { // 예약 요약

	@Query(nativeQuery = true, value="select reserve_no, TO_CHAR(reserve_sdate, 'YYYY-MM-DD HH24:MI:SS') as reserve_sdate , TO_CHAR(reserve_edate, 'YYYY-MM-DD HH24:MI:SS') as reserve_edate, room_name, user_name, actual_payment, reserve_state, backoffice_no  \r\n"
			+ "from ( SELECT * FROM RESERVE_SUMMARY_VIEW WHERE BACKOFFICE_NO=?1)A\r\n"
			+ "where A.no=1 and ROWNUM <= 6\r\n"
			+ "order by reserve_sdate desc")
	public List<ReserveSummaryView> reserve_summary_selectAll(String backoffice_no);

	

}
