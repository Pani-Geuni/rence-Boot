/**
 * @author 전판근, 김예은
 */

package com.rence.office.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rence.office.model.Comment_EntityVO;
import com.rence.office.model.ListViewVO;
import com.rence.office.model.OfficeInfoVO;
import com.rence.office.model.OfficeMileageVO;
import com.rence.office.model.OfficeOperatingTimeVO_date;
import com.rence.office.model.OfficePaymentVO;
import com.rence.office.model.OfficeQuestionVO;
import com.rence.office.model.OfficeReserveVO;
import com.rence.office.model.OfficeReserveVO_date;
import com.rence.office.model.OfficeReviewVO;
import com.rence.office.model.OfficeRoomVO;
import com.rence.office.model.PaymentInfoVO;
import com.rence.office.model.RoomScheduleVO;
import com.rence.office.repo.OfficeDetailInfoRepository;
import com.rence.office.repo.OfficeInfoRepository;
import com.rence.office.repo.OfficeListRepository;
import com.rence.office.repo.OfficeMileageRepository;
import com.rence.office.repo.OfficeOperatingTimeRepository;
import com.rence.office.repo.OfficePaymentInfoRepository;
import com.rence.office.repo.OfficePaymentRepository;
import com.rence.office.repo.OfficeQuestionRepository;
import com.rence.office.repo.OfficeReserveDateRepository;
import com.rence.office.repo.OfficeReserveRepository;
import com.rence.office.repo.OfficeReviewRepository;
import com.rence.office.repo.OfficeRoomRepository;
import com.rence.office.repo.RoomScheduleRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OfficeService {
	
	
	@Autowired
	OfficeInfoRepository repository;
	
	@Autowired
	OfficeListRepository list_repository;
	
	@Autowired
	OfficeDetailInfoRepository detail_repository;
	
	@Autowired
	OfficeOperatingTimeRepository operating_repository;
	
	@Autowired
	OfficeRoomRepository room_repository;
	
	@Autowired
	OfficeQuestionRepository question_repository;
	
	@Autowired
	OfficeReviewRepository review_repository;
	
	@Autowired
	OfficeReserveRepository reserve_repository;
	
	@Autowired
	OfficeReserveDateRepository reserve_date_repository;
	
	@Autowired
	OfficePaymentInfoRepository payment_info_repository;
	
	@Autowired
	OfficePaymentRepository payment_repository;
	
	@Autowired
	OfficeMileageRepository mileage_repository;
	
	@Autowired
	RoomScheduleRepository room_schedule_repository;
	
	
	public OfficeService() {
		log.info("OfficeService()...");
	}
	
	public OfficeInfoVO select_one_office_info(String backoffice_no) {
		OfficeInfoVO vo = detail_repository.select_one_office_info(backoffice_no);
		
		return vo; 
	}
	
	public OfficeOperatingTimeVO_date select_one_operating_time(String backoffice_no) {
		OfficeOperatingTimeVO_date vo = operating_repository.select_one_operating_time(backoffice_no);
		
		return vo;
	}
	
	public List<OfficeRoomVO> select_all_room(String backoffice_no) {
		List<OfficeRoomVO> vos = room_repository.select_all_room_info(backoffice_no);
		
		return vos;
	}
	
	public List<OfficeQuestionVO> select_all_comment(String backoffice_no, Integer page) {
		Integer row_count = 4;
		Integer start_row = (page - 1) * row_count + 1;
		Integer end_row = page * row_count;
		
		List<OfficeQuestionVO> vos = question_repository.select_all_comment(backoffice_no, start_row, end_row);
		
		return vos;
	}
	
	public OfficeQuestionVO select_one_answer(String mother_no) {
		OfficeQuestionVO vo = question_repository.select_one_answer(mother_no);
		
		return vo;
	}
	
	public List<OfficeReviewVO> select_all_review(String backoffice_no, Integer page) {
		Integer row_count = 4;
		Integer start_row = (page - 1) * row_count + 1;
		Integer end_row = page * row_count;
		
		List<OfficeReviewVO> vos = review_repository.select_all_review(backoffice_no, start_row, end_row);
		
		return vos;
	}
	
	// 문의 총 개수 - (페이징) all
	public long total_rowCount_question_all(String backoffice_no) {
		return question_repository.total_rowCount_question_all(backoffice_no);
	}
	
	// 후기 총 개수 - (페이징) all
	public long total_rowCount_review_all(String backoffice_no) {
		return review_repository.total_rowCount_review_all(backoffice_no);
	}
	
	public List<OfficeReserveVO> check_reserve(String backoffice_no, String room_no, String reserve_stime) {
		List<OfficeReserveVO> vos = reserve_repository.select_all_reserve(backoffice_no, room_no, reserve_stime);
		
		return vos;
	}
	
	public List<OfficeReserveVO> check_reserve_office(String backoffice_no, String room_no) {
		List<OfficeReserveVO> vos = reserve_repository.select_all_reserve_office(backoffice_no, room_no);
		
		return vos;
	}
	
	public int confirm_reserve(OfficeReserveVO_date rvo) {
		int result = reserve_date_repository.insert_reserve(rvo);
				
		return result;
	}
	
	public String select_one_last_reserve(String user_no) {
		OfficeReserveVO vo = reserve_repository.select_one_reserve_no(user_no);
		
		String reserve_no = vo.getReserve_no();
		
		return reserve_no;
	}
	
	public PaymentInfoVO select_one_final_payment_info(String reserve_no) {
		PaymentInfoVO vo = payment_info_repository.select_one_final_payment_info(reserve_no);
		
		return vo;
	}
	
	public int insert_paymentOK(OfficePaymentVO pvo) {
		int result = payment_repository.insert_payment_info(pvo);
		
		return result; 
	}
	
	public int update_reserve_state(String reserve_no) {
		int result = reserve_repository.update_reserve_state(reserve_no);
		
		return result;
	}
	
	public OfficeMileageVO select_one_recent_mileage(String user_no) {
		OfficeMileageVO vo = mileage_repository.select_one_recent_mileage(user_no);
		
		return vo;
	}
	
	public OfficePaymentVO select_one_recent_payment(String user_no) {
		OfficePaymentVO vo = payment_repository.select_one_recent_payment(user_no);
		
		return vo;
	}
	
	public int insert_mileage_changed(OfficeMileageVO vo) {
		int result = mileage_repository.insert_mileage_changed(vo);
		
		return result;
	}
	
	
	// 결제 취소
	public int update_reserve_cancel(String reserve_no, String user_no) {
		int result = reserve_repository.update_reserve_cancel(reserve_no, user_no);
		
		return result;
	}
	
	public OfficePaymentVO select_one_cancel_payment(String reserve_no) {
		OfficePaymentVO vo = payment_repository.select_one_cancel_payment(reserve_no);
		
		return vo;
	}
	
	public int update_payment_cancel(String reserve_no, Integer cancel_amount) {
		int result = payment_repository.update_payment_cancel(reserve_no, cancel_amount);
		
		return result;
	}
	
	public List<OfficeMileageVO> select_all_mileage_cancel(String payment_no) {
		List<OfficeMileageVO> vos = mileage_repository.select_all_mileage_cancel(payment_no);
		
		return vos;
	}
	
	public OfficeMileageVO select_one_mileage_cancel(String payment_no, String mileage_state) {
		OfficeMileageVO vo = mileage_repository.select_one_mileage_cancel(payment_no, mileage_state);
		
		return vo;
	}
	
	public void delete_mileage_cancel(String mileage_no) {
		mileage_repository.delete_mileage_cancel(mileage_no);
		
	}
	
	public int update_mileage_state(String mileage_no) {
		int result = mileage_repository.update_mileage_state(mileage_no);
		
		return result;
	}


	public int insert_question(Comment_EntityVO vo) {
		int result = repository.insert_question(vo);
		
		return result; 
	}
	

	public int list_totalCnt(String type) {
		int cnt = list_repository.list_totalCnt(type);
		
		return cnt; 
	}
	
	public int search_list_totalCnt(String type, String location, String searchWord) {
		int cnt = list_repository.search_list_totalCnt("%"+type+"%", "%"+location+"%", "%"+searchWord+"%");
		
		return cnt; 
	}
	
	public List<ListViewVO> select_all_list(String type, String condition, int min, int max){
		List<ListViewVO> list = null;
		
		if(condition.equals("date")) {
			list = list_repository.selectAll_orderBy_date("%"+type+"%", min, max);
		}else if(condition.equals("rating")) {
			list = list_repository.selectAll_orderBy_rating("%"+type+"%", min, max);
		}else if(condition.equals("cheap")) {
			list = list_repository.selectAll_orderBy_cheap("%"+type+"%", min, max);
		}else if(condition.equals("expensive")) {
			list = list_repository.selectAll_orderBy_expensive("%"+type+"%", min, max);
		}
		
		return list;
	}
	
	public List<ListViewVO> search_list(String type, String location, String searchWord, String condition, int min, int max){
		List<ListViewVO> list = null;
		
		if(condition.equals("date")) {
			list = list_repository.searchAll_orderBy_date("%"+type+"%", "%"+location+"%", "%"+searchWord+"%", min, max);
		}else if(condition.equals("rating")) {
			list = list_repository.searchAll_orderBy_rating("%"+type+"%", "%"+location+"%", "%"+searchWord+"%", min, max);
		}else if(condition.equals("cheap")) {
			list = list_repository.searchAll_orderBy_cheap("%"+type+"%", "%"+location+"%", "%"+searchWord+"%", min, max);
		}else if(condition.equals("expensive")) {
			list = list_repository.searchAll_orderBy_expensive("%"+type+"%", "%"+location+"%", "%"+searchWord+"%", min, max);
		}
		
		return list;
	}
	
	public List<RoomScheduleVO> select_all_room_schedule(String backoffice_no, String room_no, String reserve_stime) {
		
		List<RoomScheduleVO> vos = room_schedule_repository.select_all_room_schedule(backoffice_no, room_no, reserve_stime);
		
		return vos;
	}
	
public List<RoomScheduleVO> select_all_room_schedule_dayoff(String backoffice_no, String room_no) {
		
		List<RoomScheduleVO> vos = room_schedule_repository.select_all_room_schedule_dayoff(backoffice_no, room_no);
		
		return vos;
	}
	
}
