package com.rence.office.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.rence.office.model.OfficeReserveVO;
import com.rence.office.model.OfficeReserveVO_date;

public interface OfficeReserveDateRepository extends JpaRepository<OfficeReserveVO_date, Object> {

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "insert into "
			+ "reserveinfo(reserve_no, reserve_stime, reserve_etime, reserve_sdate, reserve_edate, reserve_state, room_no, user_no, backoffice_no, room_type) "
			+ "values('RV'||seq_reserve.nextval, :#{#rvo?.reserve_stime}, :#{#rvo?.reserve_etime}, :#{#rvo?.reserve_sdate}, :#{#rvo?.reserve_edate}, 'false', :#{#rvo?.room_no}, :#{#rvo?.user_no}, :#{#rvo?.backoffice_no}, :#{#rvo?.room_type})")
	public int insert_reserve(@Param("rvo") OfficeReserveVO_date rvo);

}
