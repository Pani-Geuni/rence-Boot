package com.rence.backoffice.service;

import java.text.ParseException;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rence.backoffice.model.AuthVO;
import com.rence.backoffice.model.BackOfficeOperatingTimeVO_datetype;
import com.rence.backoffice.model.BackOfficeVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BackOfficeService {

	@Autowired
	BackOfficeRepository repository;

	@Autowired
	BackOfficeOperatingTimeRepository oper_repository;

	@Autowired
	AuthRepository auth_repository;

	/**
	 * 백오피스 신청 처리
	 * 
	 */
	public BackOfficeVO insertOK(BackOfficeVO vo) {
		log.info("insertOK().....");

		BackOfficeVO bvo2 = null;
		int flag = repository.insert_backoffice(vo.getBackoffice_no(), vo.getOwner_name(), vo.getBackoffice_id(),
				vo.getBackoffice_name(), vo.getCompany_name(), vo.getBackoffice_tel(), vo.getBackoffice_email(),
				vo.getZipcode(), vo.getRoadname_address(), vo.getNumber_address(), vo.getDetail_address(),
				vo.getBackoffice_tag(), vo.getBackoffice_info(), vo.getBackoffice_option(), vo.getBackoffice_around(),
				vo.getBackoffice_image(), vo.getHost_image(), vo.getBackoffice_state(), vo.getApply_date(),
				vo.getBackoffice_type());

		if (flag == 1) {
			bvo2 = repository.select_backoffice_no(vo.getBackoffice_email());
		}

		return bvo2;
	}

	/**
	 * 백오피스 신청 처리 - 운영시간
	 * 
	 */
	public String backoffice_operating_insert(BackOfficeOperatingTimeVO_datetype ovo2) {
		log.info("backoffice_operating_insert().....");
		return oper_repository.save(ovo2).getOpetime_no();
	}

	/**
	 * 이메일 인증 요청 - 이메일 중복 체크
	 * 
	 */
	public BackOfficeVO backoffice_email_check(BackOfficeVO bvo) {
		log.info("backoffice_email_check().....");
		return repository.backoffice_email_check(bvo.getBackoffice_email());
	}

	/**
	 * 이메일 인증 요청 - 인증 번호 테이블 삽입, 삽입된 정보 가져오기
	 * 
	 */
	public AuthVO backoffice_auth_insert(AuthVO avo) {
		log.info("backoffice_auth_insert().....");

		AuthVO avo2 = null;
		avo = auth_repository.save(avo);

		if (avo != null) {
			avo2 = auth_repository.findbyAuth(avo.getUser_email());
		}

		return avo2;
	}

	/**
	 * 이메일 인증 요청 - 인증 번호 확인
	 * 
	 */
	public AuthVO backoffice_authOK_select(String backoffice_email, String auth_code) {
		log.info("backoffice_authOK_select().....");

		AuthVO avo = auth_repository.findbyAuthOK(backoffice_email,auth_code);

		return avo;
	}

	/**
	 * 이메일 인증 요청 - 인증된 번호 삭제
	 * 
	 */
	public int backoffice_auth_delete(AuthVO avo2) {
		log.info("deleteOK()....");
		return auth_repository.deleteByAuth_no(avo2.getAuth_no());
	}
}
