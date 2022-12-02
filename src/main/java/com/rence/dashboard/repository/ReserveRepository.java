/**
 * 
 * @author 최진실
 *
 */
package com.rence.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rence.dashboard.model.ReserveListView;


public interface ReserveRepository extends JpaRepository<ReserveListView, Object> {

	// 예약 관리 - 리스트(전체)
	@Query(nativeQuery = true, value="select * from (select rownum as rnum,A.* from (select ROW_NUMBER() OVER(PARTITION BY reserve_no ORDER BY reserve_no ASC ) no, reserve_no, reserve_sdate, reserve_edate, room_name, user_no, user_name, user_tel, user_email, TO_CHAR(actual_payment) as actual_payment, payment_state, reserve_state, backoffice_no from "
			+ "( select B.*, user_name, user_tel, user_email  from (select no, reserve_no, reserve_sdate, reserve_edate, room_name, user_no, TO_CHAR(actual_payment) as actual_payment, payment_state, reserve_state, backoffice_no from "
			+ "(select ROW_NUMBER() OVER(PARTITION BY rv.reserve_no ORDER BY rv.reserve_no ASC ) no, rv.reserve_no, TO_CHAR(reserve_sdate, 'YYYY-MM-DD HH24:MI:SS') as reserve_sdate, TO_CHAR(reserve_edate, 'YYYY-MM-DD HH24:MI:SS') as reserve_edate, rm.room_name, rv.user_no,  p.actual_payment, p.payment_state, reserve_state, rv.backoffice_no from reserveinfo rv left outer join paymentinfo p on rv.reserve_no=p.reserve_no left outer join roominfo rm on p.room_no = rm.room_no )"
			+ ")B left outer join userinfo u on B.user_no=u.user_no where backoffice_no=?1 and reserve_state !='false' ))A where A.no=1 order by reserve_sdate desc)where rnum between ?2 and ?3")
	public List<ReserveListView> backoffice_reserve_selectAll(String backoffice_no, Integer start_row, Integer end_row);
	
	// 예약 관리 - 리스트(이용중) in_use
	@Query(nativeQuery = true, value="select * from (select rownum as rnum,A.* from (select ROW_NUMBER() OVER(PARTITION BY reserve_no ORDER BY reserve_no ASC ) no, reserve_no, reserve_sdate, reserve_edate, room_name, user_no, user_name, user_tel, user_email, TO_CHAR(actual_payment) as actual_payment, payment_state, reserve_state, backoffice_no from "
			+ "( select B.*, user_name, user_tel, user_email  from (select no, reserve_no, reserve_sdate, reserve_edate, room_name, user_no, TO_CHAR(actual_payment) as actual_payment, payment_state, reserve_state, backoffice_no from "
			+ "(select ROW_NUMBER() OVER(PARTITION BY rv.reserve_no ORDER BY rv.reserve_no ASC ) no, rv.reserve_no, TO_CHAR(reserve_sdate, 'YYYY-MM-DD HH24:MI:SS') as reserve_sdate, TO_CHAR(reserve_edate, 'YYYY-MM-DD HH24:MI:SS') as reserve_edate, rm.room_name, rv.user_no,  p.actual_payment, p.payment_state, reserve_state, rv.backoffice_no from reserveinfo rv left outer join paymentinfo p on rv.reserve_no=p.reserve_no left outer join roominfo rm on p.room_no = rm.room_no )"
			+ ")B left outer join userinfo u on B.user_no=u.user_no where backoffice_no=?1 and reserve_state !='false' and (reserve_state ='begin' or reserve_state ='in_use')))A where A.no=1 order by reserve_sdate desc)where rnum between ?2 and ?3")
	public List<ReserveListView> backoffice_reserve_selectAll_inuse(String backoffice_no, Integer start_row, Integer end_row);
	
	// 예약 관리 - 리스트(종료) end
	@Query(nativeQuery = true, value="select * from (select rownum as rnum,A.* from (select ROW_NUMBER() OVER(PARTITION BY reserve_no ORDER BY reserve_no ASC ) no, reserve_no, reserve_sdate, reserve_edate, room_name, user_no, user_name, user_tel, user_email, TO_CHAR(actual_payment) as actual_payment, payment_state, reserve_state, backoffice_no from "
			+ "( select B.*, user_name, user_tel, user_email  from (select no, reserve_no, reserve_sdate, reserve_edate, room_name, user_no, TO_CHAR(actual_payment) as actual_payment, payment_state, reserve_state, backoffice_no from "
			+ "(select ROW_NUMBER() OVER(PARTITION BY rv.reserve_no ORDER BY rv.reserve_no ASC ) no, rv.reserve_no, TO_CHAR(reserve_sdate, 'YYYY-MM-DD HH24:MI:SS') as reserve_sdate, TO_CHAR(reserve_edate, 'YYYY-MM-DD HH24:MI:SS') as reserve_edate, rm.room_name, rv.user_no,  p.actual_payment, p.payment_state, reserve_state, rv.backoffice_no from reserveinfo rv left outer join paymentinfo p on rv.reserve_no=p.reserve_no left outer join roominfo rm on p.room_no = rm.room_no )"
			+ ")B left outer join userinfo u on B.user_no=u.user_no where backoffice_no=?1 and reserve_state !='false' and reserve_state ='end' ))A where A.no=1 order by reserve_sdate desc)where rnum between ?2 and ?3")
	public List<ReserveListView> backoffice_reserve_selectAll_end(String backoffice_no, Integer start_row, Integer end_row);
	
	// 예약 관리 - 리스트(취소) cancel
	@Query(nativeQuery = true, value="select * from (select rownum as rnum,A.* from (select ROW_NUMBER() OVER(PARTITION BY reserve_no ORDER BY reserve_no ASC ) no, reserve_no, reserve_sdate, reserve_edate, room_name, user_no, user_name, user_tel, user_email, TO_CHAR(actual_payment) as actual_payment, payment_state, reserve_state, backoffice_no from "
			+ "( select B.*, user_name, user_tel, user_email  from (select no, reserve_no, reserve_sdate, reserve_edate, room_name, user_no, TO_CHAR(actual_payment) as actual_payment, payment_state, reserve_state, backoffice_no from "
			+ "(select ROW_NUMBER() OVER(PARTITION BY rv.reserve_no ORDER BY rv.reserve_no ASC ) no, rv.reserve_no, TO_CHAR(reserve_sdate, 'YYYY-MM-DD HH24:MI:SS') as reserve_sdate, TO_CHAR(reserve_edate, 'YYYY-MM-DD HH24:MI:SS') as reserve_edate, rm.room_name, rv.user_no,  p.actual_payment, p.payment_state, reserve_state, rv.backoffice_no from reserveinfo rv left outer join paymentinfo p on rv.reserve_no=p.reserve_no left outer join roominfo rm on p.room_no = rm.room_no )"
			+ ")B left outer join userinfo u on B.user_no=u.user_no where backoffice_no=?1 and reserve_state !='false' and reserve_state ='cancel' ))A where A.no=1 order by reserve_sdate desc)where rnum between ?2 and ?3")
	public List<ReserveListView> backoffice_reserve_selectAll_cancel(String backoffice_no, Integer start_row, Integer end_row);

	// 예약 관리 - 리스트(전체 검색) 
	@Query(nativeQuery = true, value="select * from (select rownum as rnum,A.* from (select ROW_NUMBER() OVER(PARTITION BY reserve_no ORDER BY reserve_no ASC ) no, reserve_no, reserve_sdate, reserve_edate, room_name, user_no, user_name, user_tel, user_email, TO_CHAR(actual_payment) as actual_payment, payment_state, reserve_state, backoffice_no from "
			+ "( select B.*, user_name, user_tel, user_email  from (select no, reserve_no, reserve_sdate, reserve_edate, room_name, user_no, TO_CHAR(actual_payment) as actual_payment, payment_state, reserve_state, backoffice_no from "
			+ "(select ROW_NUMBER() OVER(PARTITION BY rv.reserve_no ORDER BY rv.reserve_no ASC ) no, rv.reserve_no, TO_CHAR(reserve_sdate, 'YYYY-MM-DD HH24:MI:SS') as reserve_sdate, TO_CHAR(reserve_edate, 'YYYY-MM-DD HH24:MI:SS') as reserve_edate, rm.room_name, rv.user_no,  p.actual_payment, p.payment_state, reserve_state, rv.backoffice_no from reserveinfo rv left outer join paymentinfo p on rv.reserve_no=p.reserve_no left outer join roominfo rm on p.room_no = rm.room_no )"
			+ ")B left outer join userinfo u on B.user_no=u.user_no where backoffice_no=?1 and reserve_state !='false' ))A where A.no=1 and user_name like ?4 order by reserve_sdate desc)where rnum between ?2 and ?3 ")
	public List<ReserveListView> backoffice_reserve_selectAll_search(String backoffice_no, Integer start_row,
			Integer end_row, String searchword);

	// 예약 관리 - 리스트(이용중 검색) in_use
	@Query(nativeQuery = true, value="select * from (select rownum as rnum,A.* from (select ROW_NUMBER() OVER(PARTITION BY reserve_no ORDER BY reserve_no ASC ) no, reserve_no, reserve_sdate, reserve_edate, room_name, user_no, user_name, user_tel, user_email, TO_CHAR(actual_payment) as actual_payment, payment_state, reserve_state, backoffice_no from "
			+ "( select B.*, user_name, user_tel, user_email  from (select no, reserve_no, reserve_sdate, reserve_edate, room_name, user_no, TO_CHAR(actual_payment) as actual_payment, payment_state, reserve_state, backoffice_no from "
			+ "(select ROW_NUMBER() OVER(PARTITION BY rv.reserve_no ORDER BY rv.reserve_no ASC ) no, rv.reserve_no, TO_CHAR(reserve_sdate, 'YYYY-MM-DD HH24:MI:SS') as reserve_sdate, TO_CHAR(reserve_edate, 'YYYY-MM-DD HH24:MI:SS') as reserve_edate, rm.room_name, rv.user_no,  p.actual_payment, p.payment_state, reserve_state, rv.backoffice_no from reserveinfo rv left outer join paymentinfo p on rv.reserve_no=p.reserve_no left outer join roominfo rm on p.room_no = rm.room_no )"
			+ ")B left outer join userinfo u on B.user_no=u.user_no where backoffice_no=?1 and reserve_state !='false' and (reserve_state ='begin' or reserve_state ='in_use') ))A where A.no=1 and user_name like ?4 order by reserve_sdate desc)where rnum between ?2 and ?3")
	public List<ReserveListView> backoffice_reserve_selectAll_inuse_search(String backoffice_no, Integer start_row, Integer end_row, String searchword);
	
	// 예약 관리 - 리스트(종료 검색) end
	@Query(nativeQuery = true, value="select * from (select rownum as rnum,A.* from (select ROW_NUMBER() OVER(PARTITION BY reserve_no ORDER BY reserve_no ASC ) no, reserve_no, reserve_sdate, reserve_edate, room_name, user_no, user_name, user_tel, user_email, TO_CHAR(actual_payment) as actual_payment, payment_state, reserve_state, backoffice_no from "
			+ "( select B.*, user_name, user_tel, user_email  from (select no, reserve_no, reserve_sdate, reserve_edate, room_name, user_no, TO_CHAR(actual_payment) as actual_payment, payment_state, reserve_state, backoffice_no from "
			+ "(select ROW_NUMBER() OVER(PARTITION BY rv.reserve_no ORDER BY rv.reserve_no ASC ) no, rv.reserve_no, TO_CHAR(reserve_sdate, 'YYYY-MM-DD HH24:MI:SS') as reserve_sdate, TO_CHAR(reserve_edate, 'YYYY-MM-DD HH24:MI:SS') as reserve_edate, rm.room_name, rv.user_no,  p.actual_payment, p.payment_state, reserve_state, rv.backoffice_no from reserveinfo rv left outer join paymentinfo p on rv.reserve_no=p.reserve_no left outer join roominfo rm on p.room_no = rm.room_no )"
			+ ")B left outer join userinfo u on B.user_no=u.user_no where backoffice_no=?1 and reserve_state !='false' and reserve_state ='end' ))A where A.no=1 and user_name like ?4 order by reserve_sdate desc)where rnum between ?2 and ?3")
	public List<ReserveListView> backoffice_reserve_selectAll_end_search(String backoffice_no, Integer start_row, Integer end_row, String searchword);
	
	// 예약 관리 - 리스트(취소 검색) cancel
	@Query(nativeQuery = true, value="select * from (select rownum as rnum,A.* from (select ROW_NUMBER() OVER(PARTITION BY reserve_no ORDER BY reserve_no ASC ) no, reserve_no, reserve_sdate, reserve_edate, room_name, user_no, user_name, user_tel, user_email, TO_CHAR(actual_payment) as actual_payment, payment_state, reserve_state, backoffice_no from "
			+ "( select B.*, user_name, user_tel, user_email  from (select no, reserve_no, reserve_sdate, reserve_edate, room_name, user_no, TO_CHAR(actual_payment) as actual_payment, payment_state, reserve_state, backoffice_no from "
			+ "(select ROW_NUMBER() OVER(PARTITION BY rv.reserve_no ORDER BY rv.reserve_no ASC ) no, rv.reserve_no, TO_CHAR(reserve_sdate, 'YYYY-MM-DD HH24:MI:SS') as reserve_sdate, TO_CHAR(reserve_edate, 'YYYY-MM-DD HH24:MI:SS') as reserve_edate, rm.room_name, rv.user_no,  p.actual_payment, p.payment_state, reserve_state, rv.backoffice_no from reserveinfo rv left outer join paymentinfo p on rv.reserve_no=p.reserve_no left outer join roominfo rm on p.room_no = rm.room_no )"
			+ ")B left outer join userinfo u on B.user_no=u.user_no where backoffice_no=?1 and reserve_state !='false' and reserve_state ='cancel' ))A where A.no=1 and user_name like ?4 order by reserve_sdate desc)where rnum between ?2 and ?3")
	public List<ReserveListView> backoffice_reserve_selectAll_cancel_search(String backoffice_no, Integer start_row, Integer end_row, String searchword);

	//*************페이징*****************//
	// 예약 관리 - 리스트(전체) 갯수
	@Query(nativeQuery = true, value=" select count(*) from (select A.* from (select ROW_NUMBER() OVER(PARTITION BY reserve_no ORDER BY reserve_no ASC ) no, reserve_no, reserve_sdate, reserve_edate, room_name, user_no, user_name, user_tel, user_email, TO_CHAR(actual_payment) as actual_payment, payment_state, reserve_state, backoffice_no "
			+ "            from ( select * from reserve_list_b_view where backoffice_no=?1 and reserve_state !='false' order by reserve_sdate desc ))A where A.no=1)")
	public int backoffice_reserve_selectAll(String backoffice_no);

	// 예약 관리 - 리스트(이용중) in_use
	@Query(nativeQuery = true, value=" select count(*) from (select A.* from (select ROW_NUMBER() OVER(PARTITION BY reserve_no ORDER BY reserve_no ASC ) no, reserve_no, reserve_sdate, reserve_edate, room_name, user_no, user_name, user_tel, user_email, TO_CHAR(actual_payment) as actual_payment, payment_state, reserve_state, backoffice_no "
			+ "            from ( select * from reserve_list_b_view where backoffice_no=?1 and reserve_state !='false' and (reserve_state ='begin' or reserve_state ='in_use') order by reserve_sdate desc ))A where A.no=1)")
	public int backoffice_reserve_selectAll_inuse(String backoffice_no);

	// 예약 관리 - 리스트(종료) end
	@Query(nativeQuery = true, value=" select count(*) from (select A.* from (select ROW_NUMBER() OVER(PARTITION BY reserve_no ORDER BY reserve_no ASC ) no, reserve_no, reserve_sdate, reserve_edate, room_name, user_no, user_name, user_tel, user_email, TO_CHAR(actual_payment) as actual_payment, payment_state, reserve_state, backoffice_no "
			+ "            from ( select * from reserve_list_b_view where backoffice_no=?1 and reserve_state !='false' and reserve_state ='end' order by reserve_sdate desc ))A where A.no=1)")
	public int backoffice_reserve_selectAll_end(String backoffice_no);

	// 예약 관리 - 리스트(취소) cancel
	@Query(nativeQuery = true, value=" select count(*) from (select A.* from (select ROW_NUMBER() OVER(PARTITION BY reserve_no ORDER BY reserve_no ASC ) no, reserve_no, reserve_sdate, reserve_edate, room_name, user_no, user_name, user_tel, user_email, TO_CHAR(actual_payment) as actual_payment, payment_state, reserve_state, backoffice_no "
			+ "            from ( select * from reserve_list_b_view where backoffice_no=?1 and reserve_state !='false' and reserve_state ='cancel'order by reserve_sdate desc ))A where A.no=1)")
	public int backoffice_reserve_selectAll_cancel(String backoffice_no);

	// 예약 관리 - 리스트(전체 검색) 
	@Query(nativeQuery = true, value=" select count(*) from (select A.* from (select ROW_NUMBER() OVER(PARTITION BY reserve_no ORDER BY reserve_no ASC ) no, reserve_no, reserve_sdate, reserve_edate, room_name, user_no, user_name, user_tel, user_email, TO_CHAR(actual_payment) as actual_payment, payment_state, reserve_state, backoffice_no "
			+ "            from ( select * from reserve_list_b_view where backoffice_no=?1 and reserve_state !='false' order by reserve_sdate desc ))A where A.no=1)where user_name like ?2")
	public int backoffice_reserve_selectAll_search(String backoffice_no, String string);

	// 예약 관리 - 리스트(이용중 검색) in_use
	@Query(nativeQuery = true, value=" select count(*) from (select A.* from (select ROW_NUMBER() OVER(PARTITION BY reserve_no ORDER BY reserve_no ASC ) no, reserve_no, reserve_sdate, reserve_edate, room_name, user_no, user_name, user_tel, user_email, TO_CHAR(actual_payment) as actual_payment, payment_state, reserve_state, backoffice_no "
			+ "            from ( select * from reserve_list_b_view where backoffice_no=?1 and reserve_state !='false' and (reserve_state ='begin' or reserve_state ='in_use') order by reserve_sdate desc ))A where A.no=1)where user_name like ?2")
	public int backoffice_reserve_selectAll_inuse_search(String backoffice_no, String string);

	// 예약 관리 - 리스트(종료 검색) end
	@Query(nativeQuery = true, value=" select count(*) from (select A.* from (select ROW_NUMBER() OVER(PARTITION BY reserve_no ORDER BY reserve_no ASC ) no, reserve_no, reserve_sdate, reserve_edate, room_name, user_no, user_name, user_tel, user_email, TO_CHAR(actual_payment) as actual_payment, payment_state, reserve_state, backoffice_no "
			+ "            from ( select * from reserve_list_b_view where backoffice_no=?1 and reserve_state !='false' and reserve_state ='end' order by reserve_sdate desc ))A where A.no=1)where user_name like ?2")
	public int backoffice_reserve_selectAll_end_search(String backoffice_no, String string);

	// 예약 관리 - 리스트(취소 검색) cancel
	@Query(nativeQuery = true, value=" select count(*) from (select A.* from (select ROW_NUMBER() OVER(PARTITION BY reserve_no ORDER BY reserve_no ASC ) no, reserve_no, reserve_sdate, reserve_edate, room_name, user_no, user_name, user_tel, user_email, TO_CHAR(actual_payment) as actual_payment, payment_state, reserve_state, backoffice_no "
			+ "            from ( select * from reserve_list_b_view where backoffice_no=?1 and reserve_state !='false' and reserve_state ='cancel' order by reserve_sdate desc ))A where A.no=1)where user_name like ?2")
	public int backoffice_reserve_selectAll_cancel_search(String backoffice_no, String string);

	
}
