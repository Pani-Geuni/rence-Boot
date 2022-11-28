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
	
	@Query(nativeQuery = true, value = "select NVL(sum(p.payment_total),0) as sales_total from paymentinfo p left outer join roominfo rm on p.room_no=rm.room_no where p.payment_state='T' and (((rm.room_type='desk') or (rm.room_type like 'meeting%')) and p.backoffice_no=?1  and trunc(payment_date)=trunc(current_date))")
	public Integer select_pay_before_desk_meeting_sum(String backoffice_no);

	@Query(nativeQuery = true, value = "select NVL(sum(p.payment_total*0.8),0) as sales_total from paymentinfo p left outer join roominfo rm on p.room_no=rm.room_no where p.payment_state='F' and p.sales_state='T' and ((rm.room_type='desk') or (rm.room_type like 'meeting%')and p.backoffice_no=?1  and trunc(payment_date)=trunc(current_date))")
	public Integer select_pay_after_desk_meeting_deposit_sum(String backoffice_no);

	@Query(nativeQuery = true, value = "select NVL(sum(payment_total*0.2),0) as sales_total from paymentinfo p left outer join roominfo rm on p.room_no=rm.room_no where p.payment_state='F' and ((rm.room_type='desk') or (rm.room_type like 'meeting%'))and p.backoffice_no=?1  and trunc(payment_date)=trunc(current_date)")
	public Integer select_pay_after_desk_meeting_balance_sum(String backoffice_no);

	@Query(nativeQuery = true, value = "select NVL(sum(payment_total),0) as sales_total from paymentinfo p left outer join roominfo rm on p.room_no=rm.room_no where p.sales_state='T' and rm.room_type='office' and p.backoffice_no=?1 and trunc(payment_date)=trunc(current_date)")
	public Integer select_pay_office_sum(String backoffice_no);

	@Query(nativeQuery = true, value = "select NVL(sum(cancel_amount+use_mileage),0) as sales_cancel from paymentinfo where backoffice_no=?1 and cancel_state='C' and trunc(payment_date)=trunc(current_date)")
	public Integer select_pay_cancel(String backoffice_no);


}
