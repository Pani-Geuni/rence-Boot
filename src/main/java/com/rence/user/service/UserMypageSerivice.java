
/**
	 * @author 강경석
	 
*/
package com.rence.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rence.user.model.UserMileageVO;
import com.rence.user.model.UserVO;
import com.rence.user.repository.MileageRepository;
import com.rence.user.repository.MypageRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserMypageSerivice {
	
	@Autowired
	MypageRepository repository;
	
	@Autowired
	MileageRepository mileageRepository;
	
	public UserMypageSerivice() {
		log.info("UserSerivice().....");
	}
	
	//총마일리지 
	public UserMileageVO user_mileage_selectOne(UserVO uvo) {
		log.info("user_mileage_selectOne()...."+uvo.getUser_no());
		return repository.findByUser_no(uvo.getUser_no());

	}

	public List<UserMileageVO> user_mileage_selectAll(UserVO uvo) {
		log.info("user_mileage_selectOne()...."+uvo.getUser_no());
		return mileageRepository.mileage_selectAll(uvo.getUser_no());
		
	}
	
	//마일리지상세페이지에서
	public List<UserMileageVO> user_mileage_search_list(UserVO uvo, String searchKey) {
		log.info("user_mileage_search_list()....");
		log.info("uvo: {}",uvo);
		log.info("searchKey: {}",searchKey);
		
//		Native
		if(searchKey.equals("all")) {			
			return mileageRepository.mileage_search_list_all(uvo.getUser_no());			
		}
		else if(searchKey.equals("plus")) {			
			return mileageRepository.mileage_search_list_plus(uvo.getUser_no());			
		}
		else if(searchKey.equals("minus")) {			
			return mileageRepository.mileage_search_list_minus(uvo.getUser_no());			
		}
		return null;
		
	}
	
	//비밀번호 변경
	public int user_pw_updateOK(UserVO uvo) {
		log.info("user_pw_updateOK()....");
		log.info("uvo: {}",uvo);
		
		
		return repository.user_pw_updateOK(uvo.getUser_pw(),uvo.getUser_no());
	}
	
	//현재 비밀번호 확인
	public int check_now_pw(UserVO uvo) {
		log.info("user_pw_updateOK()....");
		log.info("uvo: {}",uvo);
		
		return repository.check_now_pw(uvo.getUser_no(),uvo.getUser_pw());
	}

	//회원탈퇴에 따른 회원상태 수정
	public int user_secedeOK(UserVO uvo) {
		log.info("user_pw_updateOK()....");
		log.info("uvo: {}",uvo);
		
		return repository.user_secedeOK(uvo.getUser_no());
	}
	
	
	//프로필 사진 수정
	public int user_img_updateOK(UserVO uvo) {
		log.info("user_pw_updateOK()....");
		log.info("uvo: {}",uvo);
		
		return repository.user_img_updateOK(uvo.getUser_image(),uvo.getUser_no());
	}

	
	
	
	
}//end class
