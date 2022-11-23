package com.rence.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rence.dashboard.model.BOPaymentVO;

public interface PaymentCancelRepository  extends JpaRepository<BOPaymentVO, Object>{

	@Query(nativeQuery = true, value = "select * from paymentinfo where reserve_no=?1")
	public BOPaymentVO select_paymentinfo(String reserve_no);

}
