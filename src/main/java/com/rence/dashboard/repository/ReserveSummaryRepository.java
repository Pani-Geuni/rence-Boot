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

	@Query(nativeQuery = true, value="  select reserve_no, TO_CHAR(reserve_sdate, 'YYYY-MM-DD HH24:MI:SS') as reserve_sdate , TO_CHAR(reserve_edate, 'YYYY-MM-DD HH24:MI:SS') as reserve_edate, room_name, user_name, actual_payment, reserve_state, backoffice_no  from(\r\n"
			+ "            select A.*, u.user_name from (\r\n"
			+ "            select ROW_NUMBER() OVER(PARTITION BY r.reserve_no ORDER BY r.reserve_no ASC ) no, r.reserve_no, r.reserve_sdate, r.reserve_edate, rm.room_name, r.user_no, p.actual_payment, r.reserve_state, r.backoffice_no \r\n"
			+ "			from reserveinfo r left outer join paymentinfo p on r.reserve_no=p.reserve_no \r\n"
			+ "			left outer join backofficeinfo b on p.backoffice_no = b.backoffice_no \r\n"
			+ "			left outer join roominfo rm on b.backoffice_no = rm.backoffice_no\r\n"
			+ "			where  r.reserve_state!='false')A left outer join userinfo u on A.user_no = u.user_no where A.no=1 and backoffice_no=?1)  where  ROWNUM <= 5\r\n"
			+ "			order by reserve_sdate desc")
	public List<ReserveSummaryView> reserve_summary_selectAll(String backoffice_no);

	

}
