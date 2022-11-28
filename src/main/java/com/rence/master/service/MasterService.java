/**
 * @author 전판근, 최진실
 */

package com.rence.master.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rence.backoffice.model.BackOfficeListVO;
import com.rence.backoffice.model.BackOfficeVO;
import com.rence.backoffice.repository.BackOfficeListRepository;
import com.rence.backoffice.repository.BackOfficeRepository;
import com.rence.master.model.MasterEntity;
import com.rence.master.model.MasterVO;
import com.rence.master.repository.MasterRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MasterService{

	@Autowired
	MasterRepository m_repository;

	@Autowired
	BackOfficeRepository b_repository;
	
	@Autowired
	BackOfficeListRepository b_list_repository;

	public MasterService() {
		log.info("MasterService()...");
	}

//	public MasterEntity master_login(MasterVO vo) {
//
//		return m_repository.findByMaster_id(vo.getMaster_id(), vo.getMaster_pw());
//	}

	/*
	 * 마스터 메인 화면 (호스트 신청 리스트)
	 * 
	 */
	public long total_rowCount() {
		log.info("total_rowCount()....");

		return b_repository.count();
	}

	public List<BackOfficeListVO> backoffice_applyList_selectAll(Integer currentPage) {
		log.info("backoffice_applyList_selectAll().....");
		log.info("currentpage:{}", currentPage);

		Integer row_count = 15;
		Integer start_row = (currentPage - 1) * row_count + 1;
		Integer end_row = currentPage * row_count;

		return b_list_repository.selectAll_backoffice_apply(start_row, end_row);
	}

	/*
	 * 마스터 메인 화면 (호스트 신청 승인 처리)
	 * 
	 */
	public int backoffice_grant(BackOfficeVO bvo) {
		log.info("backoffice_grant().....");
		return b_repository.update_backoffice_state_y(bvo.getBackoffice_no(),bvo.getBackoffice_email());
	}

	/*
	 * 마스터 메인 화면 (호스트 신청 거절 처리)
	 * 
	 */
	public int backoffice_refuse(BackOfficeVO bvo) {
		log.info("backoffice_refuse().....");
		return b_repository.update_backoffice_state_N(bvo.getBackoffice_no(),bvo.getBackoffice_email());
	}

	/*
	 * 마스터 - 호스트 탈퇴 신청 리스트
	 * 
	 */
	public List<BackOfficeListVO> backoffice_endList_selectAll(Integer page) {
		log.info("backoffice_applyList_selectAll().....");
		log.info("currentpage:{}", page);

		Integer row_count = 15;
		Integer start_row = (page - 1) * row_count + 1;
		Integer end_row = page * row_count;

		return b_list_repository.selectAll_backoffice_end(start_row, end_row);
	}

	/*
	 * 마스터 - 호스트 탈퇴 승인
	 * 
	 */
	public int backoffice_revoke(BackOfficeVO bvo) {
		log.info("backoffice_refuse().....");
		return b_repository.update_backoffice_state_X(bvo.getBackoffice_no(),bvo.getBackoffice_email());
	}

	/*
	 * 마스터 - 백오피스 상세
	 * 
	 */
	public BackOfficeVO master_backoffice_detail_selectOne(BackOfficeVO bvo) {
		log.info("backoffice_refuse().....");
		return b_repository.selectOne_backoffice_detail_m(bvo.getBackoffice_no());
	}
	



}
