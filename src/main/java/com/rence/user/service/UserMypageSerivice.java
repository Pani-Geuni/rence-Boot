
/**
	 * @author 강경석
	 
*/
package com.rence.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rence.user.model.MyPageReserveListVO;
import com.rence.user.model.UserMileageVO;
import com.rence.user.model.UserMypageVO;
import com.rence.user.model.UserQuestionVO;
import com.rence.user.model.UserReviewVO;
import com.rence.user.model.UserVO;
import com.rence.user.repository.MileageRepository;
import com.rence.user.repository.MyQuestionRepository;
import com.rence.user.repository.MyReserveRepository;
import com.rence.user.repository.MypageRepository;
import com.rence.user.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserMypageSerivice {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	MypageRepository repository;
	
	@Autowired
	MileageRepository mileageRepository;
	
	@Autowired
	MyReserveRepository myReserveRepository;
	
	@Autowired
	MyQuestionRepository myQuestionRepository;
	
	@Autowired
	UserReviewRepository userReviewRepository;
	
	
	public UserMypageSerivice() {
		log.info("UserSerivice().....");
	}
	
	
	//마이페이지
	public UserMypageVO user_mypage_select(UserVO uvo) {
		log.info("user_mileage_search_list()....");
		log.info("uvo: {}",uvo);
		return repository.user_mypage_select(uvo.getUser_no());
//		return repository.user_mypage_select("U1001");
	}
	

	
	//총마일리지 
	public UserMileageVO totalMileage_selectOne(UserVO uvo) {
		log.info("totalMileage_selectOne()...."+uvo.getUser_no());
		return mileageRepository.totalMileage_selectOne(uvo.getUser_no());
//		return null;

	}

	public List<UserMileageVO> user_mileage_selectAll(UserVO uvo) {
		log.info("user_mileage_selectAll()...."+uvo.getUser_no());
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

	
	//현재 비밀번호 확인
	public int check_now_pw(UserVO uvo) {
		log.info("check_now_pw()....");
		log.info("(비밀번호 확인부분)uvo: {}",uvo);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		//평문과 비교를 하는것이기때문에 입력된값(평문)을 암호화 해서는 안됨!!!!
//		uvo.setUser_pw(encoder.encode(uvo.getPassword()));
//		log.info("(비밀번호 확인부분)암호화 PW: {}",uvo.getUser_pw());
//		log.info("(비밀번호 확인부분)암호화 uvo: {}",uvo);
		
		UserVO uvo2 = userRepository.check_now_pw_selectOne(uvo.getUser_no());
		log.info("(비밀번호 확인부분)uvo2: {}",uvo2);
		
		boolean match_res = encoder.matches(uvo.getPassword(), uvo2.getPassword()); 
		log.info("(비밀번호 확인부분)res: {}",match_res);
		int result;
		if(match_res == true) {
			result = 1;
		}else {
			result = 0;
		}
			
//		 uvo.setUser_pw("$2a$12$SkMR3vslquCDjRVoFWGtI.XoN8Bs8DsWfrYqHb.jSt6IL3EgXLVeC"); //test1234!	
		return result;
	}
	
	
	//비밀번호 변경
	public int user_pw_updateOK(UserVO uvo) {
		log.info("user_pw_updateOK()....");
		log.info("uvo: {}",uvo);
		
		//비밀번호 암호화 암호화
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		uvo.setUser_pw(encoder.encode(uvo.getUser_pw()));
		
		log.info("(비밀번호 변경부분)암호화 PW: {}",uvo.getUser_pw());
		
		return userRepository.user_pw_updateOK(uvo.getUser_pw(),uvo.getUser_no());
	}


	//회원탈퇴에 따른 회원상태 수정
	public int user_secedeOK(UserVO uvo) {
		log.info("user_secedeOK()....");
		log.info("uvo: {}",uvo);
		
		return repository.user_secedeOK(uvo.getUser_no());
	}
	
	
	//프로필 사진 수정
	public int user_img_updateOK(UserVO uvo) {
		log.info("user_img_updateOK()....");
		log.info("uvo: {}",uvo);
		
		return repository.user_img_updateOK(uvo.getUser_image(),uvo.getUser_no());
	}

	//마이페이지- 현재 예약현황 리스트
	public List<MyPageReserveListVO> select_all_now_reserve_list(String user_no) {
		log.info("select_all_now_reserve_list()....");
		log.info("user_no: {}",user_no);
		
		List<MyPageReserveListVO> vos = myReserveRepository.select_all_now_reserve_list(user_no);
		log.info("vos: {}",vos);
		
		return vos;
	}

	//마이페이지- 과거 예약현황 리스트
	public List<MyPageReserveListVO> select_all_before_reserve_list(String user_no) {
		log.info("select_all_before_reserve_list()....");
		log.info("user_no: {}",user_no);
		
		List<MyPageReserveListVO> vos = myReserveRepository.select_all_before_reserve_list(user_no);
		log.info("vos: {}",vos);
		
		return vos;
	}

	//마이페이지 - 문의내역리스트
	public List<UserQuestionVO> select_all_question(String user_no) {
		log.info("select_all_question()....");
		log.info("user_no: {}",user_no);
		return myQuestionRepository.select_all_question(user_no);
	}

	//마이페이지 - 문의내역리스트
	public UserQuestionVO select_one_answer(String comment_no) {
		log.info("select_all_question()....");
		log.info("comment_no: {}",comment_no);
		return myQuestionRepository.select_one_answer(comment_no);
	}

	//마이페이지 - 문의내역리스트 - 문의 삭제
	public int delete_comment(String comment_no) {
		log.info("delete_comment()....");
		log.info("comment_no: {}",comment_no);
		return myQuestionRepository.delete_comment(comment_no);
//		return 0;
	}


	

	//마이페이지 - 리뷰리스트
	public List<UserReviewVO> select_all_review(String user_no) {
		log.info("select_all_review()....");
		log.info("user_no: {}",user_no);
		return userReviewRepository.select_all_review(user_no);
	}


}//end class
