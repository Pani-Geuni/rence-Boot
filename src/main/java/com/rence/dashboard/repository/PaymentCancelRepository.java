package com.rence.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.rence.dashboard.model.BOPaymentVO;

public interface PaymentCancelRepository  extends JpaRepository<BOPaymentVO, Object>{
	
	// 결제 정보 상태 변경
	@Modifying
	@Transactional 
	@Query(nativeQuery = true, value="update paymentinfo set payment_state='C' where payment_no in (select payment_no from paymentinfo where reserve_no=?1) ")
	public void backoffice_update_payment_state_c(String reserve_no);

	// 결제 정보 
	@Query(nativeQuery = true, value = "select * from paymentinfo where reserve_no=?1")
	public BOPaymentVO select_paymentinfo(String reserve_no);
	
	// 결제 정보
	@Query(nativeQuery = true, value = "select * from paymentinfo where payment_no=?1")
	public BOPaymentVO select_paymentinfo_user_no(String payment_no);


}
