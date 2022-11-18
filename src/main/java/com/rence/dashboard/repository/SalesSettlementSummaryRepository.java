/**
 * 
 * @author 최진실
 *
 */
package com.rence.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rence.dashboard.model.SalesSettlementSummaryView;

public interface SalesSettlementSummaryRepository extends JpaRepository<SalesSettlementSummaryView, Object>{
	
	@Query(nativeQuery = true, value = "select NVL(sum(p.payment_total),0) as sales_total from paymentinfo p left outer join roominfo rm on p.room_no=rm.room_no where p.payment_state='T' and (((rm.room_type='desk') or (rm.room_type like 'meeting%')) and p.backoffice_no=?1  and trunc(payment_date)=trunc(SYSDATE))")
	public Integer select_pay_before_desk_meeting_sum(String backoffice_no);

	@Query(nativeQuery = true, value = "select NVL(sum(p.payment_total*0.8),0) as sales_total from paymentinfo p left outer join roominfo rm on p.room_no=rm.room_no where p.payment_state='F' and p.sales_state='T' and ((rm.room_type='desk') or (rm.room_type like 'meeting%')and p.backoffice_no=?1  and trunc(payment_date)=trunc(SYSDATE))")
	public Integer select_pay_after_desk_meeting_deposit_sum(String backoffice_no);

	@Query(nativeQuery = true, value = "select NVL(sum(payment_total*0.2),0) as sales_total from paymentinfo p left outer join roominfo rm on p.room_no=rm.room_no where p.payment_state='F' and ((rm.room_type='desk') or (rm.room_type like 'meeting%'))and p.backoffice_no=?1  and trunc(payment_date)=trunc(SYSDATE)")
	public Integer select_pay_after_desk_meeting_balance_sum(String backoffice_no);

	@Query(nativeQuery = true, value = "select NVL(sum(payment_total),0) as sales_total from paymentinfo p left outer join roominfo rm on p.room_no=rm.room_no where p.sales_state='T' and rm.room_type='office' and p.backoffice_no=?1 and trunc(payment_date)=trunc(SYSDATE)")
	public Integer select_pay_office_sum(String backoffice_no);

	@Query(nativeQuery = true, value = "select NVL(sum(payment_total),0) as sales_cancel from paymentinfo p left outer join reserveinfo rv on p.reserve_no=rv.reserve_no where payment_state='T' and (sysdate-payment_date)*24<=1 and rv.backoffice_no=?1  and rv.reserve_state='cancel' and trunc(payment_date)=trunc(SYSDATE)")
	public Integer select_pay_before_cancel(String backoffice_no);

	@Query(nativeQuery = true, value = "select NVL(sum(payment_total*0.2),0) as sales_cancel from paymentinfo p left outer join reserveinfo rv on p.reserve_no=rv.reserve_no where payment_state='F' and ((sysdate-payment_date)*24<=1) and rv.backoffice_no=?1 and rv.reserve_state='cancel' and trunc(payment_date)=trunc(SYSDATE)")
	public Integer select_pay_after_cancel(String backoffice_no);

	@Query(nativeQuery = true, value = "select NVL(sum(payment_total*0.8),0) from paymentinfo p left outer join reserveinfo rv on p.reserve_no=rv.reserve_no where ((sysdate-payment_date)*24>1) and rv.backoffice_no=?1 and rv.reserve_state='cancel' and trunc(payment_date)=trunc(SYSDATE)")
	public Integer select_pay_before_overdue_cancel(String backoffice_no);


}
