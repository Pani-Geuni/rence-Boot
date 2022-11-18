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
import com.rence.dashboard.repository.RoomSummaryRepository;
import com.rence.dashboard.repository.SalesSettlementDetailRepository;
import com.rence.dashboard.repository.SalesSettlementSummaryRepository;
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
	RoomInsertRepository rm_repository;
	
	@Autowired
	RoomSummaryRepository rm_summary_repository;
	
	@Autowired
	SalesSettlementSummaryRepository ss_summary_repository;
	
	@Autowired
	SalesSettlementDetailRepository s_detail_repository;

	// 공간 관리 - 문의 리스트
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

	// 예약 관리  - 리스트
	@Override
	public List<ReserveListView> backoffice_reserve_selectAll(String backoffice_no, String reserve_state, Integer start_row, Integer end_row) {
		log.info("backoffice_reserve_selectAll().....");
		log.info("reserve_state: {}.....",reserve_state);
		log.info("start_row: {}.....",start_row);
		log.info("end_row: {}.....",end_row);
		
		List<ReserveListView> reserve = null;
		
		if (reserve_state.equals("all")) {
			reserve = rv_repository.backoffice_reserve_selectAll(backoffice_no,start_row,end_row);
		}else if(reserve_state.equals("in_use")){
			reserve = rv_repository.backoffice_reserve_selectAll_inuse(backoffice_no,start_row,end_row);
		}else if(reserve_state.equals("end")){
			reserve = rv_repository.backoffice_reserve_selectAll_end(backoffice_no,start_row,end_row);
		}else if(reserve_state.equals("cancel")){
			reserve = rv_repository.backoffice_reserve_selectAll_cancel(backoffice_no,start_row,end_row);
		}
		
		log.info("OOOOOOOOOOOOOOOOOOOOO{}",reserve);
		return reserve;
	}

	// 예약 관리  - 리스트 (검색)
	@Override
	public List<ReserveListView> backoffice_search_reserve(String backoffice_no, String reserve_state, String searchword,
			Integer start_row, Integer end_row) {
		log.info("reserve_state: {}.....",reserve_state);
		log.info("start_row: {}.....",start_row);
		log.info("end_row: {}.....",end_row);
		log.info("end_row: {}.....",searchword);
		
		List<ReserveListView> reserve = null;
		
		if (reserve_state.equals("all")) {
			reserve = rv_repository.backoffice_reserve_selectAll_search(backoffice_no,start_row,end_row,"%"+searchword+"%");
		}else if(reserve_state.equals("in_use")){
			reserve = rv_repository.backoffice_reserve_selectAll_inuse_search(backoffice_no,start_row,end_row,"%"+searchword+"%");
		}else if(reserve_state.equals("end")){
			reserve = rv_repository.backoffice_reserve_selectAll_end_search(backoffice_no,start_row,end_row,"%"+searchword+"%");
		}else if(reserve_state.equals("cancel")){
			reserve = rv_repository.backoffice_reserve_selectAll_cancel_search(backoffice_no,start_row,end_row,"%"+searchword+"%");
		}
		
		log.info("OOOOOOOOOOOOOOOOOOOOO{}",reserve);
		return reserve;

	}

	// main - 공간 요약
	@Override
	public RoomSummaryView room_summary_selectOne(String backoffice_no) {

		RoomSummaryView rs = new RoomSummaryView();
		
		float review_point = rm_summary_repository.select_avg_review_point(backoffice_no);
		Integer comment_no = rm_summary_repository.select_comment_cnt(backoffice_no);
		Integer review_no = rm_summary_repository.select_review_cnt(backoffice_no);
		Integer reserve_no = rm_summary_repository.select_reserve_cnt(backoffice_no);
		
		rs.setReview_point(review_point);
		rs.setComment_no(comment_no);
		rs.setReview_no(review_no);
		rs.setReserve_no(reserve_no);
		
		return rs;
	}

	// main - 일일 정산
	@Override
	public SalesSettlementSummaryView payment_summary_selectOne(String backoffice_no) {
		
		SalesSettlementSummaryView ss = new SalesSettlementSummaryView();
		
		Integer pay_before_desk_meeting = ss_summary_repository.select_pay_before_desk_meeting_sum(backoffice_no);
		Integer pay_after_desk_meeting_deposit = ss_summary_repository.select_pay_after_desk_meeting_deposit_sum(backoffice_no);
		Integer pay_after_desk_meeting_balance = ss_summary_repository.select_pay_after_desk_meeting_balance_sum(backoffice_no);
		Integer pay_office = ss_summary_repository.select_pay_office_sum(backoffice_no);
		
		int sales_total = pay_before_desk_meeting+pay_after_desk_meeting_deposit+pay_after_desk_meeting_balance+pay_office;
		
		ss.setSales_total(String.valueOf(sales_total));
		
		Integer pay_before_cancel = ss_summary_repository.select_pay_before_cancel(backoffice_no);
		Integer pay_after_cancel = ss_summary_repository.select_pay_after_cancel(backoffice_no);
		Integer pay_before_overdue_cancel = ss_summary_repository.select_pay_before_overdue_cancel(backoffice_no);
		
		int sales_cancel = pay_before_cancel+pay_after_cancel+pay_before_overdue_cancel;
		
		ss.setSales_cancel(String.valueOf(sales_cancel));
		
		ss.setSales_income(String.valueOf(sales_total-sales_cancel));
		
		return ss;
	}

	@Override
	public com.rence.dashboard.model.SalesSettlementDetailView SalesSettlementDetailView(String backoffice_no,
			String sales_date) {
		
		SalesSettlementDetailView ss = new SalesSettlementDetailView();
		
		if (sales_date.equals("day")) {
			// 금일
			Integer pay_before_desk_meeting = s_detail_repository.select_pay_before_desk_meeting_sum(backoffice_no);
			Integer pay_after_desk_meeting_deposit = s_detail_repository.select_pay_after_desk_meeting_deposit_sum(backoffice_no);
			Integer pay_after_desk_meeting_balance = s_detail_repository.select_pay_after_desk_meeting_balance_sum(backoffice_no);
			Integer pay_office = s_detail_repository.select_pay_office_sum(backoffice_no);
			
			int sales_total = pay_before_desk_meeting+pay_after_desk_meeting_deposit+pay_after_desk_meeting_balance+pay_office;
			
			ss.setSales_total(String.valueOf(sales_total));
			
			Integer pay_before_cancel = s_detail_repository.select_pay_before_cancel(backoffice_no);
			Integer pay_after_cancel = s_detail_repository.select_pay_after_cancel(backoffice_no);
			Integer pay_before_overdue_cancel = s_detail_repository.select_pay_before_overdue_cancel(backoffice_no);
			
			int sales_cancel = pay_before_cancel+pay_after_cancel+pay_before_overdue_cancel;
			
			ss.setSales_cancel(String.valueOf(sales_cancel));
			
			ss.setSales_income(String.valueOf(sales_total-sales_cancel));
			
			//전일
			Integer pay_before_desk_meeting_pre = s_detail_repository.select_pay_before_desk_meeting_sum_pre(backoffice_no);
			Integer pay_after_desk_meeting_deposit_pre = s_detail_repository.select_pay_after_desk_meeting_deposit_sum_pre(backoffice_no);
			Integer pay_after_desk_meeting_balance_pre = s_detail_repository.select_pay_after_desk_meeting_balance_sum_pre(backoffice_no);
			Integer pay_office_pre = s_detail_repository.select_pay_office_sum_pre(backoffice_no);
			
			int pre_sales_total = pay_before_desk_meeting+pay_after_desk_meeting_deposit+pay_after_desk_meeting_balance+pay_office;
			
			ss.setPre_sales_total(String.valueOf(pre_sales_total));
			
			Integer pay_before_cancel_pre = s_detail_repository.select_pay_before_cancel_pre(backoffice_no);
			Integer pay_after_cancel_pre = s_detail_repository.select_pay_after_cancel_pre(backoffice_no);
			Integer pay_before_overdue_cancel_pre = s_detail_repository.select_pay_before_overdue_cancel_pre(backoffice_no);
			
			int pre_sales_cancel = pay_before_cancel+pay_after_cancel+pay_before_overdue_cancel;
			
			ss.setPre_sales_cancel(String.valueOf(pre_sales_cancel));
			
			ss.setSales_income(String.valueOf(pre_sales_total-pre_sales_cancel));
			
			ss.setSales_gap(String.valueOf((sales_total-sales_cancel)-(pre_sales_total-pre_sales_cancel)));
		}else if (sales_date.equals("week")) {
			// 금주
			Integer pay_before_desk_meeting = s_detail_repository.select_pay_before_desk_meeting_sum_week(backoffice_no);
			Integer pay_after_desk_meeting_deposit = s_detail_repository.select_pay_after_desk_meeting_deposit_sum_week(backoffice_no);
			Integer pay_after_desk_meeting_balance = s_detail_repository.select_pay_after_desk_meeting_balance_sum_week(backoffice_no);
			Integer pay_office = s_detail_repository.select_pay_office_sum_week(backoffice_no);
			
			int sales_total = pay_before_desk_meeting+pay_after_desk_meeting_deposit+pay_after_desk_meeting_balance+pay_office;
			
			ss.setSales_total(String.valueOf(sales_total));
			
			Integer pay_before_cancel = s_detail_repository.select_pay_before_cancel_week(backoffice_no);
			Integer pay_after_cancel = s_detail_repository.select_pay_after_cancel_week(backoffice_no);
			Integer pay_before_overdue_cancel = s_detail_repository.select_pay_before_overdue_cancel_week(backoffice_no);
			
			int sales_cancel = pay_before_cancel+pay_after_cancel+pay_before_overdue_cancel;
			
			ss.setSales_cancel(String.valueOf(sales_cancel));
			
			ss.setSales_income(String.valueOf(sales_total-sales_cancel));
			
			//전주
			Integer pay_before_desk_meeting_pre = s_detail_repository.select_pay_before_desk_meeting_sum_pre_week(backoffice_no);
			Integer pay_after_desk_meeting_deposit_pre = s_detail_repository.select_pay_after_desk_meeting_deposit_sum_pre_week(backoffice_no);
			Integer pay_after_desk_meeting_balance_pre = s_detail_repository.select_pay_after_desk_meeting_balance_sum_pre_week(backoffice_no);
			Integer pay_office_pre = s_detail_repository.select_pay_office_sum_pre_week(backoffice_no);
			
			int pre_sales_total = pay_before_desk_meeting+pay_after_desk_meeting_deposit+pay_after_desk_meeting_balance+pay_office;
			
			ss.setPre_sales_total(String.valueOf(pre_sales_total));
			
			Integer pay_before_cancel_pre = s_detail_repository.select_pay_before_cancel_pre_week(backoffice_no);
			Integer pay_after_cancel_pre = s_detail_repository.select_pay_after_cancel_pre_week(backoffice_no);
			Integer pay_before_overdue_cancel_pre = s_detail_repository.select_pay_before_overdue_cancel_pre_week(backoffice_no);
			
			int pre_sales_cancel = pay_before_cancel+pay_after_cancel+pay_before_overdue_cancel;
			
			ss.setPre_sales_cancel(String.valueOf(pre_sales_cancel));
			
			ss.setSales_income(String.valueOf(pre_sales_total-pre_sales_cancel));
			
			ss.setSales_gap(String.valueOf((sales_total-sales_cancel)-(pre_sales_total-pre_sales_cancel)));
		}else if (sales_date.equals("month")) {
			// 당월
			Integer pay_before_desk_meeting = s_detail_repository.select_pay_before_desk_meeting_sum_month(backoffice_no);
			Integer pay_after_desk_meeting_deposit = s_detail_repository.select_pay_after_desk_meeting_deposit_sum_month(backoffice_no);
			Integer pay_after_desk_meeting_balance = s_detail_repository.select_pay_after_desk_meeting_balance_sum_month(backoffice_no);
			Integer pay_office = s_detail_repository.select_pay_office_sum_month(backoffice_no);
			
			int sales_total = pay_before_desk_meeting+pay_after_desk_meeting_deposit+pay_after_desk_meeting_balance+pay_office;
			
			ss.setSales_total(String.valueOf(sales_total));
			
			Integer pay_before_cancel = s_detail_repository.select_pay_before_cancel_month(backoffice_no);
			Integer pay_after_cancel = s_detail_repository.select_pay_after_cancel_month(backoffice_no);
			Integer pay_before_overdue_cancel = s_detail_repository.select_pay_before_overdue_cancel_month(backoffice_no);
			
			int sales_cancel = pay_before_cancel+pay_after_cancel+pay_before_overdue_cancel;
			
			ss.setSales_cancel(String.valueOf(sales_cancel));
			
			ss.setSales_income(String.valueOf(sales_total-sales_cancel));
			
			//전월
			Integer pay_before_desk_meeting_pre = s_detail_repository.select_pay_before_desk_meeting_sum_pre_month(backoffice_no);
			Integer pay_after_desk_meeting_deposit_pre = s_detail_repository.select_pay_after_desk_meeting_deposit_sum_pre_month(backoffice_no);
			Integer pay_after_desk_meeting_balance_pre = s_detail_repository.select_pay_after_desk_meeting_balance_sum_pre_month(backoffice_no);
			Integer pay_office_pre = s_detail_repository.select_pay_office_sum_pre_month(backoffice_no);
			
			int pre_sales_total = pay_before_desk_meeting+pay_after_desk_meeting_deposit+pay_after_desk_meeting_balance+pay_office;
			
			ss.setPre_sales_total(String.valueOf(pre_sales_total));
			
			Integer pay_before_cancel_pre = s_detail_repository.select_pay_before_cancel_pre_month(backoffice_no);
			Integer pay_after_cancel_pre = s_detail_repository.select_pay_after_cancel_pre_month(backoffice_no);
			Integer pay_before_overdue_cancel_pre = s_detail_repository.select_pay_before_overdue_cancel_pre_month(backoffice_no);
			
			int pre_sales_cancel = pay_before_cancel+pay_after_cancel+pay_before_overdue_cancel;
			
			ss.setPre_sales_cancel(String.valueOf(pre_sales_cancel));
			
			ss.setSales_income(String.valueOf(pre_sales_total-pre_sales_cancel));
			
			ss.setSales_gap(String.valueOf((sales_total-sales_cancel)-(pre_sales_total-pre_sales_cancel)));
		}
		return ss;
	}



}
