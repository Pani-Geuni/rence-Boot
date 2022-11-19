/**
 * 
 * @author 최진실
 *
 */
package com.rence.dashboard.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.rence.backoffice.model.BackOfficeVO;
import com.rence.backoffice.repository.BackOfficeRepository;
import com.rence.dashboard.model.CommentInsertVO;
import com.rence.dashboard.model.CommentListQView;
import com.rence.dashboard.model.CommentSummaryView;
import com.rence.dashboard.model.CommentVO;
import com.rence.dashboard.model.DashBoardDAO;
import com.rence.dashboard.model.ReserveSummaryView;
import com.rence.dashboard.model.ReserveListView;
import com.rence.dashboard.model.ReviewListView;
import com.rence.dashboard.model.RoomInsertVO;
import com.rence.dashboard.model.RoomSummaryView;
import com.rence.dashboard.model.RoomVO;
import com.rence.dashboard.model.SalesSettlementDetailView;
import com.rence.dashboard.model.SalesSettlementSummaryView;
import com.rence.dashboard.model.SalesSettlementViewVO;
import com.rence.dashboard.repository.CommentInsertRepository;
import com.rence.dashboard.repository.CommentQListRepository;
import com.rence.dashboard.repository.CommentRepository;
import com.rence.dashboard.repository.ReserveRepository;
import com.rence.dashboard.repository.ReserveSummaryRepository;
import com.rence.dashboard.repository.ReviewRepository;
import com.rence.dashboard.repository.RoomRepository;
import com.rence.dashboard.repository.SalesSettlementRepository;
import com.rence.dashboard.repository.CommentSummaryRepository;
import com.rence.user.model.UserVO;
import com.rence.dashboard.repository.RoomInsertRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DashboardService {

	@Autowired
	ReserveRepository rv_repository;
	
	@Autowired
	ReserveSummaryRepository r_summary_repository;

	@Autowired
	RoomRepository rm_repository;
	
	@Autowired
	RoomInsertRepository rm_repository2;

	@Autowired
	BackOfficeRepository b_repository;
//	
//	@Autowired
//	CommentQListRepository cl_repository;
	
	@Autowired
	CommentRepository c_repository;
	
	@Autowired
	CommentSummaryRepository c_summary_repository;
	
	@Autowired
	CommentInsertRepository c_insert_repository;
	
	@Autowired
	ReviewRepository r_repository;
	
	@Autowired
	SalesSettlementRepository s_repository;
	
	@Autowired
	DashBoardDAO dao;

	// Entity Manager
//	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");

	// 데시보드 메인 - 예약 요약
//	public List<ReserveSummaryVO> reserve_summary_selectAll(String backoffice_no) {
//		log.info("reserve_summary_selectAll().....");
//
//		String sql = "select reserve_no, TO_CHAR(reserve_sdate, 'YYYY-MM-DD HH24:MI:SS') as reserve_sdate , TO_CHAR(reserve_edate, 'YYYY-MM-DD HH24:MI:SS') as reserve_edate, room_name, user_name, actual_payment, reserve_state, backoffice_no  \r\n"
//				+ "		from (\r\n"
//				+ "			select ROW_NUMBER() OVER(PARTITION BY r.reserve_no ORDER BY r.reserve_no ASC ) no, r.reserve_no, r.reserve_sdate, r.reserve_edate, rm.room_name, u.user_name, p.actual_payment, r.reserve_state, r.backoffice_no \r\n"
//				+ "			from reserveinfo r left outer join userinfo u on r.user_no=u.user_no \r\n"
//				+ "			left outer join paymentinfo p on u.user_no=p.user_no \r\n"
//				+ "			left outer join backofficeinfo b on p.backoffice_no = b.backoffice_no \r\n"
//				+ "			left outer join roominfo rm on b.backoffice_no = rm.backoffice_no\r\n"
//				+ "			where r.backoffice_no=?1 AND r.reserve_state!='false')A\r\n"
//				+ "		where A.no=1 and ROWNUM <= 6\r\n" + "		order by reserve_sdate desc";
//
//		EntityManager entityManager = entityManagerFactory.createEntityManager();
//
//		entityManager.createNativeQuery(sql).setParameter(1, backoffice_no);
//
//		List<ReserveSummaryVO> vos = null;
//
//		try {
//
//			TypedQuery<ReserveSummaryVO> query = entityManager.createQuery(sql, ReserveSummaryVO.class);
//			vos = query.getResultList();
//
//		} catch (Exception e) {
//			log.info("{}", e);
//			vos = new ArrayList<ReserveSummaryVO>();
//		} finally {
//			entityManager.close();
//		}
//
////		return new ArrayList<ReserveSummaryVO>();
//		log.info("---------------vos : {}",vos);
//		return vos;
//
////		return rv_repository.selectAll_reserve_summary(backoffice_no);
//	}

	// 공간 관리 - 리스트
	public List<RoomVO> dashboard_room_list(String backoffice_no) {
		log.info("reserve_summary_selectAll().....");
		return rm_repository.selectAll_room_list(backoffice_no);
	}

	// 공간 관리 - 공간 추가 전 백오피스 정보(공간 타입) 가져오기
	public BackOfficeVO select_one_backoffice_info(String backoffice_no) {
		log.info("select_one_backoffice_info().....");
		return b_repository.select_one_backoffice_info(backoffice_no);
	}

	// 공간 관리 - 공간 추가 처리
	public int backoffice_insertOK_room(String backoffice_no, RoomInsertVO rvo) {
		log.info("backoffice_insertOK_room().....");
		log.info(backoffice_no);
		if (rvo.getRoom_type()=="desk") {
			rvo.setRoom_price(10000);
		}else if(rvo.getRoom_type()=="meeting_04"){
			rvo.setRoom_price(20000);
		}else if(rvo.getRoom_type()=="meeting_06"){
			rvo.setRoom_price(30000);
		}else if(rvo.getRoom_type()=="meeting_10"){
			rvo.setRoom_price(50000);
		}
		rvo.setBackoffice_no(backoffice_no);

	return rm_repository2.backoffice_insertOK_room(rvo,rvo.getRoom_price());
}

	// 공간 수정 - 팝업
	public RoomVO select_one_room_info(String backoffice_no, String room_no) {
		log.info("select_one_room_info().....");
		return rm_repository.select_one_room_info(backoffice_no,room_no);
	}

	//공간 수정 처리
	public int backoffice_updateOK_room(String backoffice_no, RoomInsertVO rvo) {
		log.info("backoffice_updateOK_room().....");
		rvo.setBackoffice_no(backoffice_no);
		if (rvo.getRoom_type()=="desk") {
			rvo.setRoom_price(10000);
		}else if(rvo.getRoom_type()=="meeting_04"){
			rvo.setRoom_price(20000);
		}else if(rvo.getRoom_type()=="meeting_06"){
			rvo.setRoom_price(30000);
		}else if(rvo.getRoom_type()=="meeting_10"){
			rvo.setRoom_price(50000);
		}
		return rm_repository2.backoffice_updateOK_room(rvo,rvo.getRoom_price());
	}
	
	//공간 삭제
	public void backoffice_deleteOK_room(String backoffice_no, String room_no) {
		log.info("backoffice_deleteOK_room().....");
		rm_repository.backoffice_deleteOK_room(backoffice_no,room_no);
	}

	// 공간 - 문의 리스트
	public List<CommentListQView> backoffice_qna_selectAll(String backoffice_no, Integer currentPage) {
		log.info("backoffice_qna_selectAll().....");
		log.info("currentpage:{}", currentPage);

		Integer row_count = 15;
		Integer start_row = (currentPage - 1) * row_count + 1;
		Integer end_row = currentPage * row_count;
		
		return dao.backoffice_qna_selectAll(backoffice_no,start_row, end_row);
	}

	// 공간 - 문의(답변 팝업)
	public CommentVO backoffice_insert_comment(String backoffice_no, String room_no, String comment_no) {
		log.info("backoffice_insert_comment().....");
		return c_repository.backoffice_insert_comment(backoffice_no,room_no,comment_no);
	}

	// 공간 - 문의(답변 작성)
	public int backoffice_insertOK_comment(CommentInsertVO cvo) {
		log.info("backoffice_insertOK_comment().....");
		return c_insert_repository.backoffice_insertOK_comment(cvo, cvo.getComment_date());
//		return c_insert_repository.save(cvo);
	}

	// 공간 문의(문의 상태 'T'변경)
	public void update_comment_state_T(String backoffice_no,String comment_no) {
		log.info("update_comment_state_T().....");
		c_insert_repository.update_comment_state_T(backoffice_no,comment_no);
	}

	// 공간 - 문의(답변 삭제)
	public int backoffice_deleteOK_comment(String backoffice_no, String comment_no) {
		log.info("backoffice_deleteOK_comment().....");
		return c_insert_repository.backoffice_deleteOK_comment(backoffice_no,comment_no);
	}

	// 공간 문의(문의 상태 'F'변경)
	public void update_comment_state_F(String backoffice_no, String mother_no) {
		log.info("update_comment_state_F().....");
		c_insert_repository.update_comment_state_F(backoffice_no,mother_no);
	}

	// 공간 후기
	public List<ReviewListView> backoffice_review_selectAll(String backoffice_no, Integer currentPage) {
		log.info("backoffice_review_selectAll().....");
		log.info("currentpage:{}", currentPage);

		Integer row_count = 10;
		Integer start_row = (currentPage - 1) * row_count + 1;
		Integer end_row = currentPage * row_count;
		
		return r_repository.backoffice_review_selectAll(backoffice_no,start_row, end_row);
	}

	// 예약 리스트
	public List<ReserveListView> backoffice_reserve_selectAll(String backoffice_no, String reserve_state, Integer currentPage) {
		log.info("backoffice_reserve_selectAll().....");
		log.info("currentpage:{}", currentPage);

		Integer row_count = 10;
		Integer start_row = (currentPage - 1) * row_count + 1;
		Integer end_row = currentPage * row_count;
		
		return dao.backoffice_reserve_selectAll(backoffice_no,reserve_state,start_row, end_row);
	}
	
	//예약 리스트 검색
	public List<ReserveListView> backoffice_search_reserve(String backoffice_no, String searchword, String reserve_state, Integer currentPage) {
		log.info("currentpage:{}", currentPage);

		Integer row_count = 10;
		Integer start_row = (currentPage - 1) * row_count + 1;
		Integer end_row = currentPage * row_count;
		
		return dao.backoffice_search_reserve(backoffice_no,reserve_state,searchword,start_row, end_row);
	}

	// 환경 설정 
	public BackOfficeVO backoffice_setting_selectOne(BackOfficeVO bvo) {
		log.info("backoffice_setting_selectOne().....");
		return b_repository.backoffice_setting_selectOne(bvo.getBackoffice_no());
	}

	// 환경 설정 - 비밀번호 일치
	public BackOfficeVO backoffice_select_pw(BackOfficeVO bvo) {
		log.info("backoffice_select_pw().....");
		log.info("{}.....",bvo);
		
		return b_repository.backoffice_select_pw(bvo.getBackoffice_no());
//		return b_repository.backoffice_select_pw(bvo.getBackoffice_no(),bvo.getBackoffice_pw());
	}

	// 업체 탈퇴 요청
	public int backoffice_setting_delete(BackOfficeVO bvo) {
		log.info("backoffice_setting_delete().....");
		return b_repository.update_backoffice_state_o(bvo.getBackoffice_no());
	}

	// 정산 리스트
	public List<SalesSettlementViewVO> backoffice_sales_selectAll(String backoffice_no) {
		log.info("backoffice_sales_selectAll().....");
		return s_repository.backoffice_sales_selectAll(backoffice_no);
	}
	
	//정산 상태 변경
	public int backoffice_updateOK_sales(String backoffice_no, String room_no, String payment_no) {
		log.info("backoffice_updateOK_sales().....");
		return b_repository.backoffice_updateOK_sales_state_t(backoffice_no,room_no,payment_no);
	}

	// main - 예약 요약
	public List<ReserveSummaryView> reserve_summary_selectAll(String backoffice_no) {
		log.info("reserve_summary_selectAll().....");
		return r_summary_repository.reserve_summary_selectAll(backoffice_no);
	}

	// main - 문의 요약
	public List<CommentSummaryView> comment_summary_selectAll(String backoffice_no) {
		log.info("comment_summary_selectAll().....");
		return c_summary_repository.comment_summary_selectAll(backoffice_no);
	}

	// main - 공간 요약
	public RoomSummaryView room_summary_selectOne(String backoffice_no) {
		log.info("room_summary_selectOne().....");
		return dao.room_summary_selectOne(backoffice_no);
	}

	// main - 일일 정산
	public SalesSettlementSummaryView payment_summary_selectOne(String backoffice_no) {
		log.info("payment_summary_selectOne().....");
		return dao.payment_summary_selectOne(backoffice_no);
	}

	// 정산 관리
	public SalesSettlementDetailView backoffice_sales_selectOne(String backoffice_no, String sales_date) {
		log.info("SalesSettlementDetailView().....");
		return dao.SalesSettlementDetailView(backoffice_no,sales_date);
	}

	
	
	// 예약 상태 자동 업데이트
	public void reserve_state_auto_update() {
		log.info("reserve_state_auto_update().....");
		dao.reserve_state_auto_update();
	}


}
