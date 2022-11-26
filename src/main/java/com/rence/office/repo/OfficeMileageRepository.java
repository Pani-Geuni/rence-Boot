package com.rence.office.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.rence.office.model.OfficeMileageVO;

public interface OfficeMileageRepository extends JpaRepository<OfficeMileageVO, Object> {

	
	@Query(nativeQuery = true,
			value ="select * from ("
					+ "select *"
					+ "from mileage "
					+ "where user_no=?1 "
					+ "order by mileage_no desc "
					+ ") where rownum=1")
	public OfficeMileageVO select_one_recent_mileage(String user_no);
	
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true,
			value = "insert into "
					+ "mileage(mileage_no, mileage_total, mileage_state, user_no, mileage_change, payment_no) "
					+ "values('M'||seq_mileage.nextval, :#{#vo?.mileage_total}, :#{#vo?.mileage_state}, :#{#vo?.user_no}, :#{#vo?.mileage_change}, :#{#vo?.payment_no})")
	public int insert_mileage_changed(@Param("vo") OfficeMileageVO vo);
}
