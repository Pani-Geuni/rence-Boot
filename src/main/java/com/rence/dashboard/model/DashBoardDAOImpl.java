/**
 * 
 * @author 최진실
 *
 */
package com.rence.dashboard.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.mapping.Embedded.Nullable;
import org.springframework.stereotype.Repository;

import com.rence.dashboard.repository.CommentAListRepository;
import com.rence.dashboard.repository.CommentQListRepository;
import com.rence.dashboard.repository.PaymentCancelRepository;
import com.rence.dashboard.repository.ReservationRepository;
import com.rence.dashboard.repository.ReserveAutoUpdateRepository;
import com.rence.dashboard.repository.ReserveRepository;
import com.rence.dashboard.repository.ReviewRepository;
import com.rence.dashboard.repository.RoomInsertRepository;
import com.rence.dashboard.repository.RoomSummaryRepository;
import com.rence.dashboard.repository.SalesMileageRepository;
import com.rence.dashboard.repository.SalesSettlementDetailRepository;
import com.rence.dashboard.repository.SalesSettlementRepository;
import com.rence.dashboard.repository.SalesSettlementSummaryRepository;
import com.rence.dashboard.repository.ScheduleListRepository;
import com.rence.dashboard.repository.ScheduleRepository;

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

	@Autowired
	SalesSettlementRepository s_repository;

	@Autowired
	ReserveAutoUpdateRepository reserveAutoUpdateRepository;

	@Autowired
	ScheduleListRepository sc_repository;

	@Autowired
	ReservationRepository reservation_repository;

	@Autowired
	ScheduleRepository schedule_repository;

	@Autowired
	PaymentCancelRepository p_repository;

	@Autowired
	SalesMileageRepository m_repository;

	// 공간 관리 - 문의 리스트
	@Override
	public List<CommentListQView> backoffice_qna_selectAll(String backoffice_no, Integer start_row, Integer end_row) {

		List<CommentListQView> qvos = cq_repository.select_all_q(backoffice_no, start_row, end_row);
		log.info("Question:{}", qvos);
		log.info("Question::::{}", qvos.size());
		if (qvos != null) {
			for (int i = 0; i < qvos.size(); i++) {
				CommentListAView ca_vo = new CommentListAView();
				ca_vo.setMother_no(qvos.get(i).getComment_no());
				String mother_no = ca_vo.getMother_no();
				log.info("mother_no::::{}", mother_no);
				List<CommentListAView> avos = ca_repository.select_all_a(backoffice_no, mother_no);
				log.info("Answer:{}", avos);

				if (avos != null) {
					for (int j = 0; j < avos.size(); j++) {

						qvos.get(i).setAnswer_no(avos.get(j).getComment_no());
						qvos.get(i).setAnswer_content(avos.get(j).getComment_content());
						qvos.get(i).setAnswer_date(avos.get(j).getComment_date());
					}
				}
			}
		}
		log.info("Question&+Answer:{}", qvos);

		return qvos;
	}

	// 예약 관리 - 리스트
	@Override
	public List<ReserveListView> backoffice_reserve_selectAll(String backoffice_no, String reserve_state,
			Integer start_row, Integer end_row) {
		log.info("backoffice_reserve_selectAll().....");
		log.info("reserve_state: {}.....", reserve_state);
		log.info("start_row: {}.....", start_row);
		log.info("end_row: {}.....", end_row);

		List<ReserveListView> reserve = null;

		if (reserve_state.equals("all")) {
			reserve = rv_repository.backoffice_reserve_selectAll(backoffice_no, start_row, end_row);
		} else if (reserve_state.equals("in_use")) {
			reserve = rv_repository.backoffice_reserve_selectAll_inuse(backoffice_no, start_row, end_row);
		} else if (reserve_state.equals("end")) {
			reserve = rv_repository.backoffice_reserve_selectAll_end(backoffice_no, start_row, end_row);
		} else if (reserve_state.equals("cancel")) {
			reserve = rv_repository.backoffice_reserve_selectAll_cancel(backoffice_no, start_row, end_row);
		}

		log.info("reserve : {}", reserve);
		return reserve;
	}

	// *******************페이징************************
	@Override
	public int backoffice_reserve_selectAll_cnt(String backoffice_no, String reserve_state) {
		log.info("backoffice_reserve_selectAll_cnt().....");
		log.info("reserve_state: {}.....", reserve_state);

		int total_cnt = 0;

		if (reserve_state.equals("all")) {
			total_cnt = rv_repository.backoffice_reserve_selectAll(backoffice_no);
		} else if (reserve_state.equals("in_use")) {
			total_cnt = rv_repository.backoffice_reserve_selectAll_inuse(backoffice_no);
		} else if (reserve_state.equals("end")) {
			total_cnt = rv_repository.backoffice_reserve_selectAll_end(backoffice_no);
		} else if (reserve_state.equals("cancel")) {
			total_cnt = rv_repository.backoffice_reserve_selectAll_cancel(backoffice_no);
		}

		return total_cnt;
	}

	// 예약 관리 - 리스트 (검색)
	@Override
	public List<ReserveListView> backoffice_search_reserve(String backoffice_no, String reserve_state,
			String searchword, Integer start_row, Integer end_row) {
		log.info("reserve_state: {}.....", reserve_state);
		log.info("start_row: {}.....", start_row);
		log.info("end_row: {}.....", end_row);
		log.info("searchword: {}.....", searchword);

		List<ReserveListView> reserve = null;

		if (reserve_state.equals("all")) {
			reserve = rv_repository.backoffice_reserve_selectAll_search(backoffice_no, start_row, end_row,
					"%" + searchword + "%");
			log.info("search list :: {}", reserve);
		} else if (reserve_state.equals("in_use")) {
			reserve = rv_repository.backoffice_reserve_selectAll_inuse_search(backoffice_no, start_row, end_row,
					"%" + searchword + "%");
		} else if (reserve_state.equals("end")) {
			reserve = rv_repository.backoffice_reserve_selectAll_end_search(backoffice_no, start_row, end_row,
					"%" + searchword + "%");
		} else if (reserve_state.equals("cancel")) {
			reserve = rv_repository.backoffice_reserve_selectAll_cancel_search(backoffice_no, start_row, end_row,
					"%" + searchword + "%");
		}

		log.info("reserve : {}", reserve);
		return reserve;

	}

	// *******************페이징************************
	@Override
	public int backoffice_search_reserve_cnt(String backoffice_no, String searchword, String reserve_state) {

		int total_cnt = 0;

		if (reserve_state.equals("all")) {
			total_cnt = rv_repository.backoffice_reserve_selectAll_search(backoffice_no, "%" + searchword + "%");
		} else if (reserve_state.equals("in_use")) {
			total_cnt = rv_repository.backoffice_reserve_selectAll_inuse_search(backoffice_no, "%" + searchword + "%");
		} else if (reserve_state.equals("end")) {
			total_cnt = rv_repository.backoffice_reserve_selectAll_end_search(backoffice_no, "%" + searchword + "%");
		} else if (reserve_state.equals("cancel")) {
			total_cnt = rv_repository.backoffice_reserve_selectAll_cancel_search(backoffice_no, "%" + searchword + "%");
		}
		log.info("total_cnt:::::{}",total_cnt);
		return total_cnt;
	}

	// main - 공간 요약
	@Override
	@Nullable
	public RoomSummaryView room_summary_selectOne(String backoffice_no) {

		RoomSummaryView rs = new RoomSummaryView();

		Float review_point = rm_summary_repository.select_avg_review_point(backoffice_no);
		if (review_point == null) {
			rs.setReview_point((float) 0.0);
		} else {
			rs.setReview_point(review_point);
		}
		Integer comment_no = rm_summary_repository.select_comment_cnt(backoffice_no);
		if (comment_no == null) {
			rs.setComment_no(0);
		} else {
			rs.setComment_no(comment_no);
		}
		Integer review_no = rm_summary_repository.select_review_cnt(backoffice_no);
		if (review_no == null) {
			rs.setReview_no(0);
		} else {
			rs.setReview_no(review_no);
		}
		Integer reserve_no = rm_summary_repository.select_reserve_cnt(backoffice_no);
		if (reserve_no == null) {
			rs.setReserve_no(0);
		} else {
			rs.setReserve_no(reserve_no);
		}

		return rs;
	}

	// main - 일일 정산
	@Override
	public SalesSettlementSummaryView payment_summary_selectOne(String backoffice_no) {

		SalesSettlementSummaryView ss = new SalesSettlementSummaryView();

		Integer pay_before_desk_meeting = ss_summary_repository.select_pay_before_desk_meeting_sum(backoffice_no);
		Integer pay_after_desk_meeting_deposit = ss_summary_repository
				.select_pay_after_desk_meeting_deposit_sum(backoffice_no);
		Integer pay_after_desk_meeting_balance = ss_summary_repository
				.select_pay_after_desk_meeting_balance_sum(backoffice_no);
		Integer pay_office = ss_summary_repository.select_pay_office_sum(backoffice_no);

		int sales_total = pay_before_desk_meeting + pay_after_desk_meeting_deposit + pay_after_desk_meeting_balance
				+ pay_office;

		ss.setSales_total(String.valueOf(sales_total));

		Integer pay_cancel = ss_summary_repository.select_pay_cancel(backoffice_no);

		int sales_cancel = pay_cancel;

		ss.setSales_cancel(String.valueOf(sales_cancel));

		ss.setSales_income(String.valueOf(sales_total - sales_cancel));

		return ss;
	}

	@Override
	public com.rence.dashboard.model.SalesSettlementDetailView SalesSettlementDetailView(String backoffice_no,
			String sales_date) {

		SalesSettlementDetailView ss = new SalesSettlementDetailView();

		if (sales_date.equals("day")) {
			// 금일
			Integer pay_before_desk_meeting = s_detail_repository.select_pay_before_desk_meeting_sum(backoffice_no);
			Integer pay_after_desk_meeting_deposit = s_detail_repository
					.select_pay_after_desk_meeting_deposit_sum(backoffice_no);
			Integer pay_after_desk_meeting_balance = s_detail_repository
					.select_pay_after_desk_meeting_balance_sum(backoffice_no);
			Integer pay_office = s_detail_repository.select_pay_office_sum(backoffice_no);

			int sales_total = pay_before_desk_meeting + pay_after_desk_meeting_deposit + pay_after_desk_meeting_balance
					+ pay_office;

			ss.setSales_total(String.valueOf(sales_total));

			Integer pay_cancel = s_detail_repository.select_pay_cancel(backoffice_no);

			int sales_cancel = pay_cancel;

			ss.setSales_cancel(String.valueOf(sales_cancel));

			ss.setSales_income(String.valueOf(sales_total - sales_cancel));

			// 전일
			Integer pay_before_desk_meeting_pre = s_detail_repository
					.select_pay_before_desk_meeting_sum_pre(backoffice_no);
			Integer pay_after_desk_meeting_deposit_pre = s_detail_repository
					.select_pay_after_desk_meeting_deposit_sum_pre(backoffice_no);
			Integer pay_after_desk_meeting_balance_pre = s_detail_repository
					.select_pay_after_desk_meeting_balance_sum_pre(backoffice_no);
			Integer pay_office_pre = s_detail_repository.select_pay_office_sum_pre(backoffice_no);

			int pre_sales_total = pay_before_desk_meeting_pre + pay_after_desk_meeting_deposit_pre
					+ pay_after_desk_meeting_balance_pre + pay_office_pre;

			ss.setPre_sales_total(String.valueOf(pre_sales_total));

			Integer pre_pay_cancel = s_detail_repository.select_pay_cancel_pre(backoffice_no);

			int pre_sales_cancel = pre_pay_cancel;

			ss.setPre_sales_cancel(String.valueOf(pre_sales_cancel));

			ss.setPre_sales_income(String.valueOf(pre_sales_total - pre_sales_cancel));

			ss.setSales_gap(String.valueOf((sales_total - sales_cancel) - (pre_sales_total - pre_sales_cancel)));
		} else if (sales_date.equals("week")) {
			// 금주
			Integer pay_before_desk_meeting = s_detail_repository
					.select_pay_before_desk_meeting_sum_week(backoffice_no);
			Integer pay_after_desk_meeting_deposit = s_detail_repository
					.select_pay_after_desk_meeting_deposit_sum_week(backoffice_no);
			Integer pay_after_desk_meeting_balance = s_detail_repository
					.select_pay_after_desk_meeting_balance_sum_week(backoffice_no);
			Integer pay_office = s_detail_repository.select_pay_office_sum_week(backoffice_no);

			int sales_total = pay_before_desk_meeting + pay_after_desk_meeting_deposit + pay_after_desk_meeting_balance
					+ pay_office;

			ss.setSales_total(String.valueOf(sales_total));

			Integer pay_cancel = s_detail_repository.select_pay_cancel_week(backoffice_no);

			int sales_cancel = pay_cancel;

			ss.setSales_cancel(String.valueOf(sales_cancel));

			ss.setSales_income(String.valueOf(sales_total - sales_cancel));

			// 전주
			Integer pay_before_desk_meeting_pre = s_detail_repository
					.select_pay_before_desk_meeting_sum_pre_week(backoffice_no);
			Integer pay_after_desk_meeting_deposit_pre = s_detail_repository
					.select_pay_after_desk_meeting_deposit_sum_pre_week(backoffice_no);
			Integer pay_after_desk_meeting_balance_pre = s_detail_repository
					.select_pay_after_desk_meeting_balance_sum_pre_week(backoffice_no);
			Integer pay_office_pre = s_detail_repository.select_pay_office_sum_pre_week(backoffice_no);

			int pre_sales_total = pay_before_desk_meeting_pre + pay_after_desk_meeting_deposit_pre
					+ pay_after_desk_meeting_balance_pre + pay_office_pre;

			ss.setPre_sales_total(String.valueOf(pre_sales_total));

			Integer pre_pay_cancel = s_detail_repository.select_pay_cancel_pre_week(backoffice_no);

			int pre_sales_cancel = pre_pay_cancel;

			ss.setPre_sales_cancel(String.valueOf(pre_sales_cancel));

			ss.setPre_sales_income(String.valueOf(pre_sales_total - pre_sales_cancel));

			ss.setSales_gap(String.valueOf((sales_total - sales_cancel) - (pre_sales_total - pre_sales_cancel)));
		} else if (sales_date.equals("month")) {
			// 당월
			Integer pay_before_desk_meeting = s_detail_repository
					.select_pay_before_desk_meeting_sum_month(backoffice_no);
			Integer pay_after_desk_meeting_deposit = s_detail_repository
					.select_pay_after_desk_meeting_deposit_sum_month(backoffice_no);
			Integer pay_after_desk_meeting_balance = s_detail_repository
					.select_pay_after_desk_meeting_balance_sum_month(backoffice_no);
			Integer pay_office = s_detail_repository.select_pay_office_sum_month(backoffice_no);

			int sales_total = pay_before_desk_meeting + pay_after_desk_meeting_deposit + pay_after_desk_meeting_balance
					+ pay_office;

			ss.setSales_total(String.valueOf(sales_total));

			Integer pay_cancel = s_detail_repository.select_pay_cancel_month(backoffice_no);

			int sales_cancel = pay_cancel;

			ss.setSales_cancel(String.valueOf(sales_cancel));

			ss.setSales_income(String.valueOf(sales_total - sales_cancel));

			// 전월
			Integer pay_before_desk_meeting_pre = s_detail_repository
					.select_pay_before_desk_meeting_sum_pre_month(backoffice_no);
			Integer pay_after_desk_meeting_deposit_pre = s_detail_repository
					.select_pay_after_desk_meeting_deposit_sum_pre_month(backoffice_no);
			Integer pay_after_desk_meeting_balance_pre = s_detail_repository
					.select_pay_after_desk_meeting_balance_sum_pre_month(backoffice_no);
			Integer pay_office_pre = s_detail_repository.select_pay_office_sum_pre_month(backoffice_no);

			int pre_sales_total = pay_before_desk_meeting_pre + pay_after_desk_meeting_deposit_pre
					+ pay_after_desk_meeting_balance_pre + pay_office_pre;

			ss.setPre_sales_total(String.valueOf(pre_sales_total));

			Integer pre_pay_cancel = s_detail_repository.select_pay_cancel_pre_month(backoffice_no);

			int pre_sales_cancel = pre_pay_cancel;

			ss.setPre_sales_cancel(String.valueOf(pre_sales_cancel));

			ss.setPre_sales_income(String.valueOf(pre_sales_total - pre_sales_cancel));

			ss.setSales_gap(String.valueOf((sales_total - sales_cancel) - (pre_sales_total - pre_sales_cancel)));
		}
		return ss;
	}

	// 예약 상태 업데이트
	@Override
	public ReserveUpdateVO reserve_state_auto_update() {
		// select
		// 분기문
		// 현재 날짜 : 예약 시작 날짜와 같거나 시작날짜-끝날짜 사이 - in_use 로 변경
		// 현재 날짜 : 예약 끝날자 보다 지나면 end
		List<ReserveUpdateVO> rv = reserveAutoUpdateRepository.selectAll_reserve();
//		log.info("reserve list : {} ", rv);

		Date sysdate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String ss = sdf.format(sysdate);

		for (ReserveUpdateVO rvo : rv) {

//			log.info("현재 날짜 및 시간 : {}", ss);
			Date stime = rvo.getReserve_stime();
//			log.info("에약 시작 날짜 및 시간 : {}", stime);
			Date etime = rvo.getReserve_etime();
//			log.info("예약 종료 날짜 및 시간 : {}", etime);

			int start = stime.compareTo(sysdate);
			int end = etime.compareTo(sysdate);

			if (start >= 0) {
				reserveAutoUpdateRepository.update_reserve_state_begin(rvo.getReserve_no());
			} else if (start <= 0 && end >= 0) {
				reserveAutoUpdateRepository.update_reserve_state_inuse(rvo.getReserve_no());
			} else if (end < 0) {
				reserveAutoUpdateRepository.update_reserve_state_end(rvo.getReserve_no());
			}
		}

		return null;
	}

	// 일정 관리 - 리스트
	@Override
	public List<ScheduleListView> backoffice_schedule_list(String backoffice_no, String not_sdate, String not_edate,
			String not_stime, String not_etime, String off_type) {

		ScheduleListView sc = new ScheduleListView();

		// 1, 2 날짜 형태 변환
		String reserve_stime = null;
		String reserve_etime = null;
		if (off_type.equals("dayoff")) { // 휴무일 때
			log.info("휴무");
			
			not_stime = "00:00:00";
			not_etime = "23:59:59";
			
			reserve_stime = (not_sdate + not_stime);
			log.info("reserve_stime : {} ", reserve_stime);
			reserve_etime = (not_edate + not_etime);
			log.info("reserve_etime : {} ", reserve_etime);
		} else { // 브레이크 타임일 때
			log.info("브레이크타임");

			log.info("not_sdate : {} ", not_sdate);
			reserve_stime = (not_sdate + not_stime);
			log.info("reserve_stime : {} ", reserve_stime);

			not_edate = (not_sdate);
			log.info("not_edate : {} ", not_edate);
			reserve_etime = (not_edate + not_etime);
			log.info("reserve_etime : {} ", reserve_etime);
		}

		// 1.해당 날짜, 시간에 예약이 있는 리스트
		List<ScheduleListView> sc_vos_o = sc_repository.backoffice_schedule_list(backoffice_no, reserve_stime, reserve_etime);
		log.info("sc_vos_o : {} ", sc_vos_o.size());

		// 2.백오피스가 가진 모든 공간 리스트
		List<ScheduleListView> sc_vos_x = sc_repository.backoffice_schedule_list_All(backoffice_no);
		log.info("sc_vos_x : {} ", sc_vos_x);
		log.info("sc_vos_x : {} ", sc_vos_x.size());
		
		// 3.휴무, 브레이크 타임이 설정된 공간 리스트 
		not_stime = not_sdate + not_stime;
		not_etime = not_edate + not_etime;
		log.info("not_stime : {} ", not_stime);
		log.info("not_etime : {} ", not_etime);
		List<ScheduleEntity> off_list = schedule_repository.backoffice_schedule_list_exist_off(backoffice_no, not_stime,
				not_etime);
		log.info("off_list : {} ", off_list);
		log.info("off_list : {} ", off_list.size());

		List<ScheduleListView> sc_vos = new ArrayList<ScheduleListView>();

		if (sc_vos_x != null) {
			// 2 - 1 (중복 제거)
			if (sc_vos_o != null) {

				for (int j = 0; j < sc_vos_o.size(); j++) {
					String room_no = sc_vos_o.get(j).getRoom_no();
					Predicate<ScheduleListView> condition = str -> str.getRoom_no().equals(room_no);
					sc_vos_x.removeIf(condition);
				}
			}
			log.info("sc_vos_x - sc_vos_o : {} ", sc_vos_x.size());

			// 공간 예약 상태 설정
			for (ScheduleListView scvo : sc_vos_o) {
				scvo.setReserve_is("O");
			}

			for (ScheduleListView scvo : sc_vos_x) {
				scvo.setReserve_is("X");
				scvo.setReserve_cnt(0);
			}

			// 2 -3(휴무, 브레이크 타임이 설정된 공간 제외)
			if (off_list != null && sc_vos_x != null) {
				for (int j = 0; j < off_list.size(); j++) {
					String room_no = off_list.get(j).getRoom_no(); 
					Predicate<ScheduleListView> condition = str -> str.getRoom_no().equals(room_no);
					sc_vos_x.removeIf(condition);
				}
			}

			log.info("sc_vos_x - off_list : {} ", sc_vos_x.size());

			sc_vos.addAll(sc_vos_o);
			sc_vos.addAll(sc_vos_x);

		} else
			sc_vos = null;

		log.info("sc_vos : {} ", sc_vos.size());

		return sc_vos;
	}

	// 일정 관리 - 예약자
	@Override
	public List<ReservationView> backoffice_reservation(String backoffice_no, String not_sdate, String not_edate,
			String not_stime, String not_etime, String room_no, String off_type, int min, int max) {

		String reserve_stime = null;
		String reserve_etime = null;
		if (off_type.equals("dayoff")) { // 휴무일 때
			log.info("휴무");
			
			not_stime = "00:00:00";
			not_etime = "23:59:59";
			
			reserve_stime = (not_sdate + not_stime);
			log.info("reserve_stime : {} ", reserve_stime);
			reserve_etime = (not_edate + not_etime);
			log.info("reserve_etime : {} ", reserve_etime);
		} else { // 브레이크 타임일 때
			log.info("브레이크 타임");
			
			log.info("not_sdate : {} ", not_sdate);
			reserve_stime = (not_sdate + not_stime);
			log.info("reserve_stime : {} ", reserve_stime);

			not_edate = (not_sdate);
			log.info("not_edate : {} ", not_edate);
			reserve_etime = (not_edate + not_etime);
			log.info("reserve_etime : {} ", reserve_etime);
		}

		List<ReservationView> rv_vos = reservation_repository.backoffice_reservation_list(backoffice_no, reserve_stime,
				reserve_etime, room_no, min, max);

		return rv_vos;
	}

	// ************** 페이징 - 예약자 ******************8
	@Override
	public int backoffice_reservation_cnt(String backoffice_no, String not_sdate, String not_edate, String not_stime,
			String not_etime, String room_no, String off_type) {
		String reserve_stime = null;
		String reserve_etime = null;
		if (off_type.equals("dayoff")) { // 휴무일 때
			log.info("휴무");
			
			not_stime = "00:00:00";
			not_etime = "23:59:59";
			
			reserve_stime = (not_sdate + not_stime);
			log.info("reserve_stime : {} ", reserve_stime);
			reserve_etime = (not_edate + not_etime);
			log.info("reserve_etime : {} ", reserve_etime);
		} else { // 브레이크 타임일 때
			log.info("브레이크 타임");
			
			log.info("not_sdate : {} ", not_sdate);
			reserve_stime = (not_sdate + not_stime);
			log.info("reserve_stime : {} ", reserve_stime);

			not_edate = (not_sdate);
			log.info("not_edate : {} ", not_edate);
			reserve_etime = (not_edate + not_etime);
			log.info("reserve_etime : {} ", reserve_etime);
		}

		int total_cnt = reservation_repository.backoffice_reservation_list_cnt(backoffice_no, reserve_stime,
				reserve_etime, room_no);
		return total_cnt;
	}

	// 일정 관리 - 예약 취소
	@Override
	public BOPaymentVO backoffice_reservation_cancel(String backoffice_no, String reserve_no, String user_no) {
		int flag = reserveAutoUpdateRepository.update_reserve_state_cancel(reserve_no);
		BOPaymentVO pvo = new BOPaymentVO();
		// 결제 취소,
		if (flag == 1) {
			// 결제정보 테이블의 상태 'C' 로 변경
			p_repository.backoffice_update_payment_state_host_cancel(reserve_no); // 환불 상태 'C', 환불 금액 = 실제 결제 금액, 결제일시 = 환불일시
			pvo = p_repository.select_paymentinfo(reserve_no); // 결제 정보
			String payment_no = pvo.getPayment_no();

			BOMileageVO mvo = m_repository.backoffice_select_mileage_total(user_no); // 1. 사용자의 마지막 mileage_total
			BOMileageVO mvo2 = m_repository.backoffice_select_mileage_f(user_no, payment_no); // 2. 사용자가 사용한 마일리지

			if (mvo2 != null) { // 사용한 마일리지가 있으면
				int mileage_change = mvo2.getMileage_change(); // 2
				int mileage_total = mvo.getMileage_total() + mileage_change; // 1 + 2

				m_repository.backoffice_insert_mileage_state_t(mileage_total, user_no, mileage_change, payment_no); // 마일리지 재적립
			}

			s_repository.backoffice_update_cancel_mileage_state_c(reserve_no); // w 상태의 마일리지 -> c 상태로 변경
		}
		return pvo;
	}

	// 정산 상태 변경
	@Override
	public int backoffice_updateOK_sales(String backoffice_no, String room_no, String payment_no) {
		int flag = 0;
		BOPaymentVO pvo = new BOPaymentVO();
		
		pvo = p_repository.select_paymentinfo_user_no(payment_no); // 결제정보 테이블에서 user_no 정보 얻기
		String user_no = pvo.getUser_no();

		BOMileageVO mvo = m_repository.backoffice_select_mileage_total(user_no); // 1. 사용자의 마지막 mileage_total
		BOMileageVO mvo2 = m_repository.backoffice_select_mileage_w(user_no, payment_no); // 2. 적립 예정 마일리지

		if (mvo2.getMileage_change() != 0) { // 선결제
			int mileage_change = mvo2.getMileage_change(); // 2
			int mileage_total = mvo.getMileage_total() + mileage_change; // 1+2

			flag = m_repository.backoffice_insert_mileage_state_t(mileage_total, user_no, mileage_change, payment_no); // 마일리지 적립
		} else { // 후결제
			flag = s_repository.backoffice_update_mileage_state_c(payment_no); // change 가 0인 mileage 는 C로 상태 변경
		}
		if (flag == 1) {
			s_repository.backoffice_updateOK_sales_state_t(backoffice_no, room_no, payment_no); // 결제 정보 테이블의 정산 상태 변경
		}
		return flag;
	}

}
