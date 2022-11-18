/**
 * 
 * @author 최진실
 *
 */
package com.rence.backoffice.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.rence.backoffice.model.AuthVO;
import com.rence.backoffice.model.BackOfficeOperatingTimeVO_datetype;
import com.rence.backoffice.model.BackOfficeVO;
import com.rence.backoffice.repository.AuthRepository;
import com.rence.backoffice.repository.BackOfficeOperatingTimeRepository;
import com.rence.backoffice.repository.BackOfficeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BackOfficeService implements UserDetailsService {//

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
		
		vo.setBackoffice_pw(new BCryptPasswordEncoder().encode(vo.getBackoffice_pw()));
		
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

		log.info("avo {} :",avo);
		
		avo.setAuth_stime(new Date());
		int result = auth_repository.insert_auth_info(avo, avo.getAuth_stime());
		log.info("avo insertOK {} :",avo);
//		avo = auth_repository.save(avo);

		AuthVO avo2 = new AuthVO();
		if (result != 0) {
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
		log.info("backoffice_auth_delete()....");
		return auth_repository.deleteByAuth_no(avo2.getAuth_no());
	}

	/**
	 * 비밀번호 찾기 - 입력한 정보로 가입된 계정 찾기
	 * 
	 */
	public BackOfficeVO backoffice_id_email_select(BackOfficeVO bvo) {
		log.info("backoffice_id_email_select()....");
		return repository.select_backoffice_by_id_email(bvo.getBackoffice_id(), bvo.getBackoffice_email());
	}

	/**
	 * 비밀번호 찾기 - 임시 비밀번호를 새 비밀번호로 저장
	 * 
	 */
	public int backoffice_resetOK_pw(BackOfficeVO bvo2) {
		log.info("backoffice_resetOK_pw()....");
		return repository.update_backoffice_temp_pw(bvo2.getBackoffice_pw(), bvo2.getBackoffice_no());
	}

	/**
	 * 비밀번호 초기화 - 사용자가 입력한 비밀번호를 새 비밀번호로 저장
	 */
	public int backoffice_settingOK_pw(BackOfficeVO bvo) {
		log.info("backoffice_settingOK_pw()....");
		return repository.update_backoffice_temp_pw(bvo.getBackoffice_pw(), bvo.getBackoffice_no());
	}

	@Override
	public UserDetails loadUserByUsername(String backoffice_id) throws UsernameNotFoundException {
		log.info("backoffice_id : {}",backoffice_id);
		BackOfficeVO member = repository.findByBackoffice_email(backoffice_id); //username = email
		log.info("member : {}",member);
		
		if (member==null) throw new UsernameNotFoundException("Not founc account.");
		
		return member;
	}

	public BackOfficeVO backoffice_login_info(String username) {
		log.info("backoffice_login_info()....");
		return repository.backoffice_login_info(username);
	}
}
