package com.rence.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rence.user.model.ReserveMileageVO;
import com.rence.user.repository.ReserveMileageRepository;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class ReserveMileageService {

	
	@Autowired
	ReserveMileageRepository reserve_mileage_repository;
	
	
	public ReserveMileageVO select_one_reserve_mileage(String reserve_no) {
		ReserveMileageVO vo = reserve_mileage_repository.select_one_reserve_mileage(reserve_no);
		
		return vo;
	}
}
