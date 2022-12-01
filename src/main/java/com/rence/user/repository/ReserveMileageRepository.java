package com.rence.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rence.user.model.ReserveMileageVO;

public interface ReserveMileageRepository extends JpaRepository<ReserveMileageVO, Object> {

	
	@Query(nativeQuery = true, value = "select * from reserve_info_payment where reserve_no=?1")
	public ReserveMileageVO select_one_reserve_mileage(String reserve_no);
}
