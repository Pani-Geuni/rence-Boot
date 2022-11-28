package com.rence.office.repo;

import java.util.List;

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
	
	
	@Query(nativeQuery = true, value = "select * from mileage where payment_no=?1")
	public List<OfficeMileageVO> select_all_mileage_cancel(String payment_no);
	
	@Query(nativeQuery = true, value = "select * from mileage where payment_no=?1 and mileage_state='F'")
	public OfficeMileageVO select_one_mileage_cancel(String payment_no, String mileage_state);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "delete from mileage where mileage_no=?1")
	public void delete_mileage_cancel(String mileage_no);
	
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update mileage set mileage_state='C' where mileage_no=?1")
	public int update_mileage_state(String mileage_no);
}
