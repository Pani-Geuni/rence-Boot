
/**
	 * @author 강경석
	 
*/
package com.rence.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rence.office.model.ReserveInfo_ViewVO;
import com.rence.user.model.UserMileageVO;
import com.rence.user.model.UserMypageVO;
import com.rence.user.model.UserVO;
import com.rence.user.repository.MileageRepository;
import com.rence.user.repository.MypageMenuRepository;
import com.rence.user.repository.MypageRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MypageMenuSerivice {
	
	@Autowired
	MypageMenuRepository menuRepository;
	
	public MypageMenuSerivice() {
		log.info("UserSerivice().....");
	}
	
	
	/** ******************* **/
	/** 예약 상세 페이지 SECTION **/
	/** ******************* **/
	
	// 예약 상세 정보 불러오기
	public ReserveInfo_ViewVO select_one_reserve_info(String reserve_no){
		ReserveInfo_ViewVO vo = menuRepository.select_one_reserve_info(reserve_no);
		
		return vo;
	}
	
	// 예약자 정보 불러오기
	public UserVO select_one_user_info(String user_no){
		UserVO vo = menuRepository.select_one_user_info(user_no);
		
		return vo;
	}

}//end class
