package com.rence.office.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.rence.office.model.OfficePaymentVO;

public interface OfficePaymentRepository extends JpaRepository<OfficePaymentVO, Object> {
	
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true,
			value = "insert into "
					+ "paymentinfo(payment_no, payment_total, use_mileage, actual_payment, payment_state, payment_date, room_no, user_no, reserve_no, sales_state, backoffice_no, payment_method) "
					+ "values('P'||seq_payment.nextval, :#{#vo?.payment_total}, :#{#vo?.use_mileage}, :#{#vo?.actual_payment}, :#{#vo?.payment_state}, CURRENT_DATE, :#{#vo?.room_no}, :#{#vo?.user_no}, :#{#vo?.reserve_no}, :#{#vo?.sales_state}, :#{#vo?.backoffice_no}, :#{#vo?.payment_method})")
	public int insert_payment_info(@Param("vo") OfficePaymentVO vo);

	
	@Query(nativeQuery = true,
			value = "select * from ( "
					+ "select * "
					+ "from paymentinfo "
					+ "where user_no=?1 "
					+ "order by payment_no desc "
					+ ") where rownum=1")
	public OfficePaymentVO select_one_recent_payment(String user_no);
	
	
	
	@Query(nativeQuery = true, value = "select * from paymentinfo where reserve_no=?1")
	public OfficePaymentVO select_one_cancel_payment(String reserve_no);
	
	
}
