/**
 * @author 전판근
 */

package com.rence.office.service;

import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rence.office.model.ListViewVO;
import com.rence.office.model.OfficeInfoVO;
import com.rence.office.model.OfficeOperatingTimeVO_date;
import com.rence.office.model.OfficePaymentVO;
import com.rence.office.model.OfficeQuestionVO;
import com.rence.office.model.OfficeReserveVO;
import com.rence.office.model.OfficeReviewVO;
import com.rence.office.model.OfficeRoomVO;
import com.rence.office.model.PaymentInfoVO;
import com.rence.office.model.QuestionVO2;
//import com.rence.user.model.ReviewVO;
import com.rence.office.repo.OfficeInfoRepository;
import com.rence.office.repo.OfficeListRepository;

@Service
public class OfficeService {
	
	
	@Autowired
	OfficeInfoRepository repository;
	
	@Autowired
	OfficeListRepository list_repository;
//	
//	public OfficeService() {
//		logger.info("OfficeService()...");
//	}
//	
//	public OfficeInfoVO select_one_office_info(String backoffice_no) {
//		OfficeInfoVO vo = repository.select_one_office_info(backoffice_no);
//		
//		return vo; 
//	}
//	
//	public OfficeOperatingTimeVO_date select_one_operating_time(String backoffice_no) {
//		OfficeOperatingTimeVO_date vo = repository.select_one_operating_time(backoffice_no);
//		
//		return vo;
//	}
//	
//	public List<OfficeRoomVO> select_all_room(String backoffice_no) {
//		List<OfficeRoomVO> vos = repository.select_all_room(backoffice_no);
//		
//		return vos;
//	}
//	
//	public List<OfficeQuestionVO> select_all_comment(String backoffice_no) {
//		List<OfficeQuestionVO> vos = repository.select_all_comment(backoffice_no);
//		
//		return vos;
//	}
//	
//	public OfficeQuestionVO select_one_answer(String mother_no) {
//		OfficeQuestionVO vo = repository.select_one_answer(mother_no);
//		
//		return vo;
//	}
//	
//	public List<OfficeReviewVO> select_all_review(String backoffice_no) {
//		List<OfficeReviewVO> vos = repository.select_all_review(backoffice_no);
//		
//		return vos;
//	}
//	
//	public int check_reserve(OfficeReserveVO vo) throws ParseException {
//		int result = repository.check_reserve(vo);
//		
//		return result;
//	}
//	
//	public String select_one_last_reserve(String user_no) {
//		String reserve_no = repository.select_one_last_reserve(user_no);
//		
//		return reserve_no;
//	}
//	
//	public PaymentInfoVO select_one_final_payment_info(String reserve_no) {
//		PaymentInfoVO vo = repository.select_one_final_payment_info(reserve_no);
//		
//		return vo;
//	}
//	
//	public int reserve_paymentOK(OfficePaymentVO pvo) {
//		int result = repository.reserve_paymentOK(pvo);
//		
//		return result; 
//	}
//	
////	public int insert_review(ReviewVO vo) {
////		int result = repository.insert_review(vo);
////		
////		return result; 
////	}
//	
//	public int insert_question(QuestionVO2 vo) {
//		int result = repository.insert_question(vo);
//		
//		return result; 
//	}
	
	
	public List<ListViewVO> select_all_list(String type, String condition){
		List<ListViewVO> list = list_repository.select_all_list(condition, type);
		
		return list;
	}
	
//	public List<ListViewVO> search_list(String type, String location, String searchWord, String condition){
//		List<ListViewVO> list = list_repository.search_list(type, location, searchWord, condition);
//		
//		return list;
//	}
}
