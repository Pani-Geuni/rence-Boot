package com.rence.office.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rence.office.model.OfficeReserveVO;
import com.rence.office.model.OfficeReserveVO_date;

public interface OfficeReserveRepository extends JpaRepository<OfficeReserveVO, Object> {

	@Query(nativeQuery = true, value = "select reserve_no, TO_CHAR(reserve_stime, 'HH24') reserve_stime, TO_CHAR(reserve_etime, 'HH24') reserve_etime, TO_CHAR(reserve_sdate, 'HH24') reserve_sdate, TO_CHAR(reserve_edate, 'HH24') reserve_edate, reserve_state, room_no, user_no, backoffice_no, room_type "
			+ "from reserveinfo "
			+ "where backoffice_no=?1 and room_no=?2 and trunc(reserve_stime)=to_date(?3, 'YYYY/MM/DD')")
	public List<OfficeReserveVO> select_all_reserve(String backoffice_no, String room_no, String reserve_stime);

	@Query(nativeQuery = true, value = "select * from (" + "select * " + "from reserveinfo "
			+ "where user_no=?1 order by reserve_no desc)" + "where rownum=1")
	public OfficeReserveVO select_one_reserve_no(String user_no);

}
