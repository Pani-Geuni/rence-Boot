package com.rence.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rence.dashboard.model.ReserveVO;
import com.rence.dashboard.model.ReserveSummaryVO;
import com.rence.dashboard.model.RoomVO;


public interface ReserveRepository extends JpaRepository<ReserveVO, Object> {

	

//	@Query(nativeQuery = true, value="select reserve_no, TO_CHAR(reserve_sdate, 'YYYY-MM-DD HH24:MI:SS') as reserve_sdate_var , TO_CHAR(reserve_edate, 'YYYY-MM-DD HH24:MI:SS') as reserve_edate_var, room_name, user_name, actual_payment, reserve_state \r\n"
//			+ "		from (\r\n"
//			+ "			select ROW_NUMBER() OVER(PARTITION BY r.reserve_no ORDER BY r.reserve_no ASC ) no, r.reserve_no, r.reserve_sdate, r.reserve_edate, rm.room_name, u.user_name, p.actual_payment, r.reserve_state \r\n"
//			+ "			from reserveinfo r left outer join userinfo u on r.user_no=u.user_no \r\n"
//			+ "			left outer join paymentinfo p on u.user_no=p.user_no \r\n"
//			+ "			left outer join backofficeinfo b on p.backoffice_no = b.backoffice_no \r\n"
//			+ "			left outer join roominfo rm on b.backoffice_no = rm.backoffice_no\r\n"
//			+ "			where R.BACKOFFICE_NO=?1 AND r.reserve_state!='false')A\r\n"
//			+ "		where A.no=1 and ROWNUM <= 6\r\n"
//			+ "		order by reserve_sdate desc")
//	List<ReserveEntity> selectAll_reserve_summary(String backoffice_no);

}
