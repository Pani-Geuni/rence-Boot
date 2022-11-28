package com.rence.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.rence.dashboard.model.BOMileageVO;

public interface SalesMileageRepository extends JpaRepository<BOMileageVO, Object> {

	// 사용자가 가진 현재 마일리지 
	@Query(nativeQuery = true, value = "select * from(select * from mileage where user_no=?1 order by rownum desc) where rownum = 1")
	BOMileageVO backoffice_select_mileage_total(String user_no);

	// 사용자가 사용한 마일리지
	@Query(nativeQuery = true, value = "select * from mileage where user_no=?1 and payment_no=?2 and mileage_state = 'F'")
	BOMileageVO backoffice_select_mileage_f(String user_no, String payment_no);

	// 마일리지 적립 (정산 완료, 예약 취소)
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "insert into mileage(mileage_no, mileage_total, mileage_state, user_no, mileage_change, payment_no) values('M'||seq_mileage.nextval, ?1, 'T', ?2, ?3, ?4) ")
	public void backoffice_insert_mileage_state_t(int mileage_total, String user_no, int mileage_change, String payment_no);
	
	// 적립 예정 마일리지
	@Query(nativeQuery = true, value = "select * from mileage where user_no=?1 and payment_no=?2 and mileage_state = 'W'")
	BOMileageVO backoffice_select_mileage_w(String user_no, String payment_no);

	
}