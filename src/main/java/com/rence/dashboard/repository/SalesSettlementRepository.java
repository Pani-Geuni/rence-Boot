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

	// 정산 리스트
	@Query(nativeQuery = true, value = "select  * from (select ROWNUM as num, p.* from (select * from SALESSATTLEMENT_LIST_VIEW where backoffice_no=?1 and payment_no not in(select payment_no from paymentinfo p left outer join reserveinfo rv on p.reserve_no=rv.reserve_no where rv.reserve_state='cancel') order by reserve_sdate desc)p) where num between ?2 and ?3)")
	public List<SalesSettlementViewVO> backoffice_sales_selectAll(String backoffice_no, Integer start_row, Integer end_row);

	// 정산 리스트 갯수
	@Query(nativeQuery = true, value = "select count(*) from(select * from SALESSATTLEMENT_LIST_VIEW where backoffice_no=?1 and payment_no not in(select payment_no from paymentinfo p left outer join reserveinfo rv on p.reserve_no=rv.reserve_no where rv.reserve_state='cancel') order by reserve_sdate desc)")
	public long backoffice_sales_selectAll_cnt(String backoffice_no);
	
	// 정산 상태 변경
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update paymentinfo set sales_state='T', payment_date=sysdate where backoffice_no=?1 and room_no=?2 and payment_no=?3")
	public int backoffice_updateOK_sales_state_t(String backoffice_no, String room_no, String payment_no);

	// 마일리지 상태 변경 - 적립
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update mileage set mileage_state= 'T' where mileage_no in (select mileage_no from mileage where payment_no=?1 and mileage_state='W')")
	public void backoffice_updateOK_mileage_state_t(String payment_no);

	// 결제 취소 후, 마일리지 상태 변경 - 재적립
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update mileage set mileage_state='T' where payment_no in (select payment_no from paymentinfo where reserve_no=?1) and mileage_state='F'")
	public void backoffice_update_cancel_mileage_state_t(String reserve_no);

	// 결제 취소 후, 적립 예정이던 마일리지 삭제
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "delete from mileage where payment_no in (select payment_no from paymentinfo where reserve_no=?1) and mileage_state='W'")
	public void backoffice_delete_cancel_mileage_state_w(String reserve_no);


}
