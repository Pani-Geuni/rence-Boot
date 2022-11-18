package com.rence.dashboard.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rence.dashboard.repository.CommentAListRepository;
import com.rence.dashboard.repository.CommentQListRepository;
import com.rence.dashboard.repository.ReserveRepository;
import com.rence.dashboard.repository.ReviewRepository;
import com.rence.dashboard.repository.RoomInsertRepository;
import com.rence.dashboard.repository.UserNTERepository;
import com.rence.office.model.OfficeReserveVO;
import com.rence.office.model.OfficeReserveVO_date;
import com.rence.office.model.OfficeRoomVO;
import com.rence.office.model.PaymentInfoVO;
import com.rence.user.model.UserVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class DashBoardDAOImpl implements DashBoardDAO {
	
	@Autowired
	CommentQListRepository cq_repository;
	
	@Autowired
	CommentAListRepository ca_repository;
	
	@Autowired
	ReserveRepository rv_repository;
	
	@Autowired
	ReviewRepository r_repository;
	
	@Autowired
	UserNTERepository u_repository;
	
	@Autowired
	RoomInsertRepository rm_repository;

	@Override
	public List<CommentListQView> backoffice_qna_selectAll(String backoffice_no, Integer start_row, Integer end_row) {

		List<CommentListQView> qvos = cq_repository.select_all_q(backoffice_no, start_row, end_row);
		log.info("Question:{}",qvos);
		log.info("Question::::{}",qvos.size());
		if (qvos!=null) {
			for (int i = 0; i < qvos.size(); i++) {
				CommentListAView ca_vo = new CommentListAView();
				ca_vo.setMother_no(qvos.get(i).getComment_no());
				String mother_no = ca_vo.getMother_no();
				log.info("mother_no::::{}",mother_no);
				List<CommentListAView> avos = ca_repository.select_all_a(backoffice_no,mother_no);
				log.info("Answer:{}",avos);
				
				if (avos!=null) {
					for (int j = 0; j < avos.size(); j++) {
						
							qvos.get(i).setAnswer_no(avos.get(j).getComment_no());
							qvos.get(i).setAnswer_content(avos.get(j).getComment_content());
							qvos.get(i).setAnswer_date(avos.get(j).getComment_date());
					}
				}
			}
		} log.info("Question&+Answer:{}",qvos);
		
		
		return qvos;
	}

	@Override
	public List<ReserveVIEW> backoffice_reserve_selectAll(String backoffice_no, String reserve_state, Integer start_row, Integer end_row) {
		log.info("backoffice_reserve_selectAll().....");
		log.info("reserve_state: {}.....",reserve_state);
		log.info("start_row: {}.....",start_row);
		log.info("end_row: {}.....",end_row);
		
		List<ReserveVIEW> reserve = null;
		OfficeReserveVO r = new OfficeReserveVO();
		RoomVO rm = new RoomVO();
		BOPaymentVO p = new BOPaymentVO();
		
		if (reserve_state.equals("all")) {
			reserve = rv_repository.backoffice_reserve_selectAll(backoffice_no,start_row,end_row);
		}else if(reserve_state.equals("in_use")){
			reserve = rv_repository.backoffice_reserve_selectAll_inuse(backoffice_no,start_row,end_row);
		}else if(reserve_state.equals("end")){
			reserve = rv_repository.backoffice_reserve_selectAll_end(backoffice_no,start_row,end_row);
		}else if(reserve_state.equals("cancel")){
			reserve = rv_repository.backoffice_reserve_selectAll_cancel(backoffice_no,start_row,end_row);
		}
		
//		for (ReserveVIEW reserveVIEW : reserve) {
//			r.setUser_no(reserveVIEW.getUser_no());
//			r = rm_repository.select_reserve_info(r.getUser_no());
//			reserveVIEW.setReserve_sdate(r.getReserve_sdate());
//			reserveVIEW.setReserve_edate(r.getReserve_edate());
//			reserveVIEW.setReserve_state(r.getReserve_state());
//			
//			rm.setRoom_name(reserveVIEW.getRoom_name());
//			
//			p.setUser_no(reserveVIEW.getUser_no());
//			p = =_repository.select_payment_info(p.getUser_no());
//			p.setActual_payment(reserveVIEW.getActual_payment());
//			p.setPayment_state(reserveVIEW.getPayment_state());
//			
////		rm.room_name, u.user_name, u.user_tel, u.user_email, p.actual_payment, p.payment_state, 
//	}
		
		log.info("OOOOOOOOOOOOOOOOOOOOO{}",reserve);
		return reserve;
	}

	@Override
	public List<ReviewListView> backoffice_review_selectAll(String backoffice_no, Integer start_row, Integer end_row) {
		List<ReviewListView> review = r_repository.backoffice_review_selectAll(backoffice_no,start_row, end_row);
		log.info("REVIEW : : : : : {}",review);
		
		UserVO user = new UserVO();
		for (ReviewListView reviewListView : review) {
			user.setUser_no(reviewListView.getUser_no());
			user = u_repository.select_user_nte(user.getUser_no());
			reviewListView.setUser_image(user.getUser_image());
			reviewListView.setUser_name(user.getUser_name());
		}
		log.info("REVIEW : : : : : {}",review);
		return review;
	}

}