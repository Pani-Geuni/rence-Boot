package com.rence.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rence.dashboard.model.ReserveListView;


public interface ReserveRepository extends JpaRepository<ReserveListView, Object> {

	// 예약 관리 - 리스트(전체)
	@Query(nativeQuery = true, value=" select * from (select rownum as rnum, reserve_no, reserve_sdate, reserve_edate, room_name, user_no, user_name, user_tel, user_email, TO_CHAR(actual_payment) as actual_payment, payment_state, reserve_state, backoffice_no \r\n"
			+ "            from ( select * from reserve_list_b_view where backoffice_no=?1 and reserve_state !='false' order by reserve_sdate desc )A where A.no=1)where rnum between ?2 and ?3")
	public List<ReserveListView> backoffice_reserve_selectAll(String backoffice_no, Integer start_row, Integer end_row);
	
	// 예약 관리 - 리스트(이용중) in_use
	@Query(nativeQuery = true, value=" select * from (select rownum as rnum, reserve_no, reserve_sdate, reserve_edate, room_name, user_no, user_name, user_tel, user_email, TO_CHAR(actual_payment) as actual_payment, payment_state, reserve_state, backoffice_no \r\n"
			+ "            from ( select * from reserve_list_b_view where backoffice_no=?1 and reserve_state !='false' and (reserve_state ='begin' or reserve_state ='in_use') order by reserve_sdate desc )A where A.no=1)where rnum between ?2 and ?3")
	public List<ReserveListView> backoffice_reserve_selectAll_inuse(String backoffice_no, Integer start_row, Integer end_row);
	
	// 예약 관리 - 리스트(종료) end
	@Query(nativeQuery = true, value=" select * from (select rownum as rnum, reserve_no, reserve_sdate, reserve_edate, room_name, user_no, user_name, user_tel, user_email, TO_CHAR(actual_payment) as actual_payment, payment_state, reserve_state, backoffice_no \r\n"
			+ "            from ( select * from reserve_list_b_view where backoffice_no=?1 and reserve_state !='false' and reserve_state ='end' order by reserve_sdate desc )A where A.no=1)where rnum between ?2 and ?3")
	public List<ReserveListView> backoffice_reserve_selectAll_end(String backoffice_no, Integer start_row, Integer end_row);
	
	// 예약 관리 - 리스트(취소) cancel
	@Query(nativeQuery = true, value=" select * from (select rownum as rnum, reserve_no, reserve_sdate, reserve_edate, room_name, user_no, user_name, user_tel, user_email, TO_CHAR(actual_payment) as actual_payment, payment_state, reserve_state, backoffice_no \r\n"
			+ "            from ( select * from reserve_list_b_view where backoffice_no=?1 and reserve_state !='false' and reserve_state ='cancel'order by reserve_sdate desc )A where A.no=1)where rnum between ?2 and ?3")
	public List<ReserveListView> backoffice_reserve_selectAll_cancel(String backoffice_no, Integer start_row, Integer end_row);

	// 예약 관리 - 리스트(전체 검색) 
	@Query(nativeQuery = true, value=" select * from (select rownum as rnum, reserve_no, reserve_sdate, reserve_edate, room_name, user_no, user_name, user_tel, user_email, TO_CHAR(actual_payment) as actual_payment, payment_state, reserve_state, backoffice_no \r\n"
			+ "            from ( select * from reserve_list_b_view where backoffice_no=?1 and reserve_state !='false' and user_name like ?4 order by reserve_sdate desc )A where A.no=1)where rnum between ?2 and ?3")
	public List<ReserveListView> backoffice_reserve_selectAll_search(String backoffice_no, Integer start_row,
			Integer end_row, String searchword);

	// 예약 관리 - 리스트(이용중 검색) in_use
	@Query(nativeQuery = true, value=" select * from (select rownum as rnum, reserve_no, reserve_sdate, reserve_edate, room_name, user_no, user_name, user_tel, user_email, TO_CHAR(actual_payment) as actual_payment, payment_state, reserve_state, backoffice_no \r\n"
			+ "            from ( select * from reserve_list_b_view where backoffice_no=?1 and reserve_state !='false' and (reserve_state ='begin' or reserve_state ='in_use') and user_name like ?4 order by reserve_sdate desc )A where A.no=1)where rnum between ?2 and ?3")
	public List<ReserveListView> backoffice_reserve_selectAll_inuse_search(String backoffice_no, Integer start_row, Integer end_row, String searchword);
	
	// 예약 관리 - 리스트(종료 검색) end
	@Query(nativeQuery = true, value=" select * from (select rownum as rnum, reserve_no, reserve_sdate, reserve_edate, room_name, user_no, user_name, user_tel, user_email, TO_CHAR(actual_payment) as actual_payment, payment_state, reserve_state, backoffice_no \r\n"
			+ "            from ( select * from reserve_list_b_view where backoffice_no=?1 and reserve_state !='false' and reserve_state ='end' and user_name like ?4 order by reserve_sdate desc )A where A.no=1)where rnum between ?2 and ?3")
	public List<ReserveListView> backoffice_reserve_selectAll_end_search(String backoffice_no, Integer start_row, Integer end_row, String searchword);
	
	// 예약 관리 - 리스트(취소 검색) cancel
	@Query(nativeQuery = true, value=" select * from (select rownum as rnum, reserve_no, reserve_sdate, reserve_edate, room_name, user_no, user_name, user_tel, user_email, TO_CHAR(actual_payment) as actual_payment, payment_state, reserve_state, backoffice_no \r\n"
			+ "            from ( select * from reserve_list_b_view where backoffice_no=?1 and reserve_state !='false' and reserve_state ='cancel' and user_name like ?4 order by reserve_sdate desc )A where A.no=1)where rnum between ?2 and ?3")
	public List<ReserveListView> backoffice_reserve_selectAll_cancel_search(String backoffice_no, Integer start_row, Integer end_row, String searchword);

	

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
