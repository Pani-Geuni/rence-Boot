package com.rence.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rence.dashboard.model.ReserveVIEW;


public interface ReserveRepository extends JpaRepository<ReserveVIEW, Object> {

	//d예약 관리 - 리스트(전체)
	@Query(nativeQuery = true, value=" select * from (select rownum as rnum, reserve_no, reserve_sdate, reserve_edate, room_name, user_no, user_name, user_tel, user_email, TO_CHAR(actual_payment) as actual_payment, payment_state, reserve_state \r\n"
			+ "            from ( select * from reserve_list_b_view where backoffice_no=?1 and reserve_state !='false' order by reserve_sdate desc )A where A.no=1)where rnum between ?2 and ?3")
//	@Query(nativeQuery = true, value=" select * from (select rownum as rnum, reserve_no, reserve_sdate, reserve_edate, room_name, user_name, user_tel, user_email, TO_CHAR(actual_payment) as actual_payment, payment_state, reserve_state from (\r\n"
//			+ "			select ROW_NUMBER() OVER(PARTITION BY rv.reserve_no ORDER BY rv.reserve_no ASC ) no, rv.reserve_no, TO_CHAR(reserve_sdate, 'YYYY-MM-DD HH24:MI:SS') as reserve_sdate, TO_CHAR(reserve_edate, 'YYYY-MM-DD HH24:MI:SS') as reserve_edate, rm.room_name, u.user_name, u.user_tel, u.user_email, p.actual_payment, p.payment_state, reserve_state\r\n"
//			+ "			from reserveinfo rv left outer join roominfo rm on rv.room_no=rm.room_no \r\n"
//			+ "			left outer join paymentinfo p on rm.room_no=p.room_no\r\n"
//			+ "			left outer join userinfo u on p.user_no=u.user_no \r\n"
//			+ "			where rv.backoffice_no=?1 and rv.reserve_state !='false' order by reserve_sdate desc )A where A.no=1)where rnum between ?2 and ?3")
	public List<ReserveVIEW> backoffice_reserve_selectAll(String backoffice_no, Integer start_row, Integer end_row);
	
	//d예약 관리 - 리스트(이용중) in_use
	@Query(nativeQuery = true, value="select * from (select rownum as rnum, reserve_no, reserve_sdate, reserve_edate, room_name, user_name, user_tel, user_email, TO_CHAR(actual_payment) as actual_payment, payment_state, reserve_state from (\r\n"
			+ "		select ROW_NUMBER() OVER(PARTITION BY rv.reserve_no ORDER BY rv.reserve_no ASC ) no, rv.reserve_no, TO_CHAR(reserve_sdate, 'YYYY-MM-DD HH24:MI:SS') as reserve_sdate, TO_CHAR(reserve_edate, 'YYYY-MM-DD HH24:MI:SS') as reserve_edate, rm.room_name, u.user_name, u.user_tel, u.user_email, p.actual_payment, p.payment_state, reserve_state\r\n"
			+ "		from reserveinfo rv left outer join roominfo rm on rv.room_no=rm.room_no \r\n"
			+ "		left outer join paymentinfo p on rm.room_no=p.room_no\r\n"
			+ "		left outer join userinfo u on p.user_no=u.user_no \r\n"
			+ "		where rv.backoffice_no=?1 and rv.reserve_state !='false' and (reserve_state ='begin' or reserve_state ='in_use') order by reserve_sdate desc )A where A.no=1)where rnum between ?2 and ?3")
	public List<ReserveVIEW> backoffice_reserve_selectAll_inuse(String backoffice_no, Integer start_row, Integer end_row);
	
	//d예약 관리 - 리스트(종료) end
	@Query(nativeQuery = true, value="select * from (select rownum as rnum, reserve_no, reserve_sdate, reserve_edate, room_name, user_name, user_tel, user_email, TO_CHAR(actual_payment) as actual_payment, payment_state, reserve_state from (\r\n"
			+ "		select ROW_NUMBER() OVER(PARTITION BY rv.reserve_no ORDER BY rv.reserve_no ASC ) no, rv.reserve_no, TO_CHAR(reserve_sdate, 'YYYY-MM-DD HH24:MI:SS') as reserve_sdate, TO_CHAR(reserve_edate, 'YYYY-MM-DD HH24:MI:SS') as reserve_edate, rm.room_name, u.user_name, u.user_tel, u.user_email, p.actual_payment, p.payment_state, reserve_state\r\n"
			+ "		from reserveinfo rv left outer join roominfo rm on rv.room_no=rm.room_no \r\n"
			+ "		left outer join paymentinfo p on rm.room_no=p.room_no\r\n"
			+ "		left outer join userinfo u on p.user_no=u.user_no \r\n"
			+ "		where rv.backoffice_no=?1 and rv.reserve_state !='false' and reserve_state ='end' order by reserve_sdate desc )A where A.no=1)where rnum between ?2 and ?3")
	public List<ReserveVIEW> backoffice_reserve_selectAll_end(String backoffice_no, Integer start_row, Integer end_row);
	
	//d예약 관리 - 리스트(취소)cancel
	@Query(nativeQuery = true, value="select * from (select rownum as rnum, reserve_no, reserve_sdate, reserve_edate, room_name, user_name, user_tel, user_email, TO_CHAR(actual_payment) as actual_payment, payment_state, reserve_state from (\r\n"
			+ "		select ROW_NUMBER() OVER(PARTITION BY rv.reserve_no ORDER BY rv.reserve_no ASC ) no, rv.reserve_no, TO_CHAR(reserve_sdate, 'YYYY-MM-DD HH24:MI:SS') as reserve_sdate, TO_CHAR(reserve_edate, 'YYYY-MM-DD HH24:MI:SS') as reserve_edate, rm.room_name, u.user_name, u.user_tel, u.user_email, p.actual_payment, p.payment_state, reserve_state\r\n"
			+ "		from reserveinfo rv left outer join roominfo rm on rv.room_no=rm.room_no \r\n"
			+ "		left outer join paymentinfo p on rm.room_no=p.room_no\r\n"
			+ "		left outer join userinfo u on p.user_no=u.user_no \r\n"
			+ "		where rv.backoffice_no=?1 and rv.reserve_state !='false' and reserve_state ='cancel' order by reserve_sdate desc )A where A.no=1)where rnum between ?2 and ?3")
	public List<ReserveVIEW> backoffice_reserve_selectAll_cancel(String backoffice_no, Integer start_row, Integer end_row);


	

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
