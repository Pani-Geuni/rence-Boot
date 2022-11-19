/**
 * @author 김예은
*/
package com.rence.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rence.user.model.ReserveInfo_ViewVO;
import com.rence.user.model.UserDTO;
import com.rence.user.repository.MypageMenuRepository;

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
	public UserDTO select_one_user_info(String user_no){
		UserDTO vo = menuRepository.select_one_user_info(user_no);
		
		return vo;
	}
	
	/** ******************* **/
	/** 후기 내역 페이지 SECTION **/
	/** ******************* **/
	
	public int is_write_review(String room_no, String backoffice_no) {
		int result = menuRepository.is_write_review(room_no, backoffice_no);
		
		return result;
	}
	
	public int delete_review(String review_no) {
		int result = menuRepository.delete_review(review_no);
		
		return result;
	}
	
	
	/** ******************* **/
	/** 문의 내역 페이지 SECTION **/
	/** ******************* **/
	
	public int delete_comment(String comment_no) {
		int result = menuRepository.delete_comment(comment_no);
		
		return result;
	}

}//end class
