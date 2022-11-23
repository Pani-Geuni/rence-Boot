/**
 * 
 * @author 최진실
 *
 */
package com.rence.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.rence.dashboard.model.SalesSettlementViewVO;

public interface SalesSettlementRepository extends JpaRepository<SalesSettlementViewVO, Object> { // 정산 내역

	@Query(nativeQuery = true, value = "select * from SALESSATTLEMENT_LIST_VIEW where backoffice_no='B1001' order by reserve_sdate desc")
	public List<SalesSettlementViewVO> backoffice_sales_selectAll(String backoffice_no);

	// 정산 상태 변경
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update paymentinfo set sales_state='T', payment_date=sysdate where backoffice_no=?1 and room_no=?2 and payment_no=?3")
	public int backoffice_updateOK_sales_state_t(String backoffice_no, String room_no, String payment_no);

	// 마일리지 상태 변경 - 적립
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update mileage set mileage_state= 'T' where mileage_no in (select mileage_no from mileage where payment_no=?1 and sales_state='T' and mileage_state='W')")
	public void backoffice_updateOK_mileage_state_t(String payment_no);

	// 결제 취소 후, 마일리지 상태 변경 - 재적립
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update mileage set mileage_state='T' where payment_no in (select payment_no from paymentinfo where reserve_no=?1) and mileage_state='W'")
	public void backoffice_cancel_mileage_state_t(String reserve_no);

}
