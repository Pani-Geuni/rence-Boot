package com.rence.dashboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rence.backoffice.repository.BackOfficeRepository;
import com.rence.dashboard.model.ReserveSummaryVO;
import com.rence.dashboard.repository.ReserveRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DashboardService {
	
	@Autowired
	ReserveRepository rv_repository;
	
	// 데시보드 메인 - 예약 요약
	public List<ReserveSummaryVO> reserve_summary_selectAll(String backoffice_no) {
		log.info("reserve_summary_selectAll().....");
		return rv_repository.selectAll_reserve_summary(backoffice_no);
	}

}
