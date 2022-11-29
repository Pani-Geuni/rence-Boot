/**
 * 
 * @author 최진실
 *
 */
package com.rence.dashboard.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rence.backoffice.model.BackOfficeOperatingTimeVO;
import com.rence.backoffice.model.BackOfficeOperatingTimeVO_datetype;
import com.rence.backoffice.model.BackOfficeVO;
import com.rence.backoffice.service.BackOfficeFileService;
import com.rence.backoffice.service.BackOfficeSendEmail;
import com.rence.backoffice.service.OperatingTime;
import com.rence.common.OptionEngToKorMap;
import com.rence.dashboard.model.BOPaymentVO;
import com.rence.dashboard.model.CommentInsertVO;
import com.rence.dashboard.model.CommentListQView;
import com.rence.dashboard.model.CommentSummaryView;
import com.rence.dashboard.model.CommentVO;
import com.rence.dashboard.model.ReserveSummaryView;
import com.rence.dashboard.model.ReserveListView;
import com.rence.dashboard.model.ReviewListView;
import com.rence.dashboard.model.RoomInsertVO;
import com.rence.dashboard.model.RoomSummaryView;
import com.rence.dashboard.model.RoomVO;
import com.rence.dashboard.model.SalesSettlementDetailView;
import com.rence.dashboard.model.SalesSettlementSummaryView;
import com.rence.dashboard.model.SalesSettlementViewVO;
import com.rence.dashboard.model.ScheduleEntity;
import com.rence.dashboard.model.ScheduleListView;
import com.rence.dashboard.model.ScheduleVO;
import com.rence.dashboard.model.ReservationView;
import com.rence.dashboard.repository.ScheduleListRepository;
import com.rence.dashboard.service.DashboardSendEmail;
import com.rence.dashboard.service.DashboardService;
import com.rence.dashboard.service.HostPaymentCancelService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@Api(tags = "대쉬보드 컨트롤러")
@RequestMapping("/backoffice")
public class DashBoardController {

	Gson gson = new GsonBuilder().setPrettyPrinting().create();

	@Autowired
	DashboardService service;

	@Autowired
	BackOfficeFileService fileService;

	@Autowired
	OperatingTime operatingTime;

	@Autowired
	DashboardSendEmail dashboardSendEmail;
	
	@Autowired
	HostPaymentCancelService cancelService;

	/**
	 * 대쉬보드 메인
	 */
	@ApiOperation(value = "대쉬보드 메인", notes = "대쉬보드 메인 페이지")
	@GetMapping("/main")
	public String dashboard_main(Model model, String backoffice_no) {

		List<ReserveSummaryView> rvos = service.reserve_summary_selectAll(backoffice_no);
		log.info("dashboard main rvos : {}", rvos);
		List<CommentSummaryView> cvos = service.comment_summary_selectAll(backoffice_no);
		log.info("dashboard main cvos : {}", cvos);
		SalesSettlementSummaryView svo = service.payment_summary_selectOne(backoffice_no);
		log.info("dashboard main svo : {}", svo);
		RoomSummaryView rmvo = service.room_summary_selectOne(backoffice_no);
		log.info("dashboard main rmvo : {}", rmvo);

		model.addAttribute("r_vos", rvos);
		model.addAttribute("r_cnt", rvos.size());
		model.addAttribute("c_vos", cvos);
		model.addAttribute("c_cnt", cvos.size());
		model.addAttribute("svo", svo);
		model.addAttribute("rmvo", rmvo);

		model.addAttribute("content", "thymeleaf/html/backoffice/dashboard/main");
		model.addAttribute("title", "대쉬보드 메인");

		return "thymeleaf/layouts/backoffice/layout_dashboard";
	}

	/**
	 * 공간 리스트
	 */
	@ApiOperation(value = "공간 리스트", notes = "대쉬보드 공간 관리 페이지")
	@GetMapping("/room")
	public String dashboard_room_list(Model model, String backoffice_no,
			@RequestParam(value = "page", defaultValue = "1") Integer page) {

		/////////////////////// 페이징/////////////////////////////////////////
		Map<String, Object> map = new HashMap<String, Object>();
		// 리스트 수
		long total_rowCount_all = service.dashboard_room_list_cnt(backoffice_no);
		log.info("total_rowCount_reserve_now: {}", total_rowCount_all);

		// 총 페이징되는 수
		long totalPageCnt = (long) Math.ceil(total_rowCount_all / 12.0);
		log.info("totalPageCnt: {}", totalPageCnt);

		// 현재페이지
		long nowPage = page;

		// 5page씩 끊으면 끝 페이지 번호( ex, 총 9페이지이고, 현재페이지가 6이면 maxpage = 9)
		long maxPage = 0;

		if (nowPage % 5 != 0) {
			if (nowPage == totalPageCnt) {
				maxPage = nowPage;
			} else if (((nowPage / 5) + 1) * 5 >= totalPageCnt) {
				maxPage = totalPageCnt;
			} else if (((nowPage / 5) + 1) * 5 < totalPageCnt) {
				maxPage = ((nowPage / 5) + 1) * 5;
			}
		} else if (nowPage % 5 == 0) {
			if (nowPage <= totalPageCnt) {
				maxPage = nowPage;
			}
		}
		log.info("maxPage: " + maxPage);

		map.put("totalPageCnt", totalPageCnt);
		map.put("nowPage", nowPage);
		map.put("maxPage", maxPage);
		////////////////////////////////////////////////////////////////////

		List<RoomVO> rmvos = service.dashboard_room_list(backoffice_no, page);
		log.info("rmvos{}", rmvos);
		model.addAttribute("rm_vos", rmvos);

		map.put("page", "room");
		model.addAttribute("res", map);

		model.addAttribute("content", "thymeleaf/html/backoffice/dashboard/room");
		model.addAttribute("title", "공간 관리");

		return "thymeleaf/layouts/backoffice/layout_dashboard";
	}

	/**
	 * 공간 추가 팝업
	 */
	@ApiOperation(value = "공간 추가", notes = "대쉬보드 공간 관리 페이지")
	@GetMapping("/insert_room")
	@ResponseBody
	public String backoffice_insert_room(String backoffice_no) {
		log.info("backoffice_insert_room ()...");
		log.info("backoffice_no : {}", backoffice_no);

		Map<String, List<String>> map = new HashMap<String, List<String>>();

		BackOfficeVO bvo = service.select_one_backoffice_info(backoffice_no);
		log.info("bvo : {}", bvo);

		RoomVO rmvo = new RoomVO();

		String type = bvo.getBackoffice_type().replace("meeting_room", "meeting_04,meeting_06,meeting_10");
		rmvo.setRoom_type(type);

		List<String> type_list = new ArrayList<String>();

		if (rmvo.getRoom_type() != null) {
			String[] type_split = rmvo.getRoom_type().split(",");

			for (int i = 0; i < type_split.length; i++) {
				type_list.add(type_split[i]);
			}

		} else {
			type_list.add("타입 없음");
		}

		map.put("room_type", type_list);

		String json = gson.toJson(map);

		return json;
	}

	/**
	 * 공간 추가
	 */
	@ApiOperation(value = "공간 추가 처리", notes = "대쉬보드 공간 관리 페이지")
	@PostMapping("/insertOK_room")
	@ResponseBody
	public String backoffice_insertOK_room(RoomInsertVO rvo, String backoffice_no) {
		log.info("backoffice_insertOK_room ()...");
		log.info("{}", backoffice_no);
		log.info("rvo :::::::::::; {}", rvo);

		Map<String, String> map = new HashMap<String, String>();

		int result = service.backoffice_insertOK_room(backoffice_no, rvo);

		if (result == 1) {
			log.info("successed...");
			map.put("result", "1");
		}

		else {
			log.info("failed...");
			map.put("result", "0");
		}

		String json = gson.toJson(map);

		return json;
	}

	/**
	 * 공간 수정 팝업
	 */
	@ApiOperation(value = "공간 수정 팝업", notes = "대쉬보드 공간 관리 페이지")
	@GetMapping("/update_room")
	@ResponseBody
	public String backoffice_update_room(String backoffice_no, String room_no) {
		log.info("backoffice_update_room ()...");
		log.info("{}", backoffice_no);

		Map<String, Object> map3 = new HashMap<String, Object>();

		BackOfficeVO bvo = service.select_one_backoffice_info(backoffice_no);
		RoomVO rmvo = new RoomVO();

		String type = bvo.getBackoffice_type().replace("meeting_room", "meeting_04,meeting_06,meeting_10");
		rmvo.setRoom_type(type);

		List<String> type_list = new ArrayList<String>();

		if (rmvo.getRoom_type() != null) {
			String[] type_split = rmvo.getRoom_type().split(",");

			for (int i = 0; i < type_split.length; i++) {
				type_list.add(type_split[i]);
			}

		} else {
			type_list.add("타입 없음");
		}

		rmvo = service.select_one_room_info(backoffice_no, room_no);
		log.info("rmvo : {}", rmvo);

		map3.put("rmvo", rmvo);

		map3.put("room_type", type_list);

		String json = gson.toJson(map3);

		log.info("maaaaaaaaaaaaaaaaaaaap{}", map3);

		return json;
	}

	/**
	 * 공간 수정
	 */
	@ApiOperation(value = "공간 수정 처리", notes = "대쉬보드 공간 관리 페이지")
	@PostMapping("/updateOK_room")
	@ResponseBody
	public String backoffice_updateOK_room(RoomInsertVO rvo, String backoffice_no) {
		log.info("backoffice_updateOK_room ()...");
		log.info("{}", backoffice_no);

		Map<String, String> map = new HashMap<String, String>();

		int result = service.backoffice_updateOK_room(backoffice_no, rvo);

		if (result == 1) {
			log.info("successed...");
			map.put("result", "1");
		}

		else {
			log.info("failed...");
			map.put("result", "0");
		}

		String json = gson.toJson(map);

		return json;
	}

	/**
	 * 공간 삭제
	 */
	@ApiOperation(value = "공간 삭제", notes = "대쉬보드 공간 관리 페이지")
	@PostMapping("/deleteOK_room")
	@ResponseBody
	@Transactional
	public String backoffice_deleteOK_room(String backoffice_no, String room_no) {
		log.info("backoffice_deleteOK_room ()...");
		log.info("{}", backoffice_no);

		Map<String, String> map = new HashMap<String, String>();

		int result = 1;
		try {
			service.backoffice_deleteOK_room(backoffice_no, room_no);

		} catch (Exception e) {
			result = 0;
		}

		if (result == 1) {
			log.info("successed...");
			map.put("result", "1");
		}

		else {
			log.info("failed...");
			map.put("result", "0"); // 남은 예약이 있을 시
		}

		String json = gson.toJson(map);

		return json;
	}

	/**
	 * 문의(리스트)
	 */
	@ApiOperation(value = "문의 리스트", notes = "대쉬보드 공간 관리 페이지 - 문의")
	@GetMapping("/qna")
	public String dashboard_qna(Model model, String backoffice_no,
			@RequestParam(value = "page", defaultValue = "1") Integer page) {
		log.info("backoffice_qna ()...");
		log.info("{}", backoffice_no);

		/////////////////////// 페이징/////////////////////////////////////////
		Map<String, Object> map = new HashMap<String, Object>();
		// 리스트 수
		long total_rowCount_all = service.backoffice_qna_selectAll_cnt(backoffice_no);
		log.info("total_rowCount_reserve_now: {}", total_rowCount_all);

		// 총 페이징되는 수
		long totalPageCnt = (long) Math.ceil(total_rowCount_all / 10.0);
		log.info("totalPageCnt: {}", totalPageCnt);

		// 현재페이지
		long nowPage = page;

		// 5page씩 끊으면 끝 페이지 번호( ex, 총 9페이지이고, 현재페이지가 6이면 maxpage = 9)
		long maxPage = 0;

		if (nowPage % 5 != 0) {
			if (nowPage == totalPageCnt) {
				maxPage = nowPage;
			} else if (((nowPage / 5) + 1) * 5 >= totalPageCnt) {
				maxPage = totalPageCnt;
			} else if (((nowPage / 5) + 1) * 5 < totalPageCnt) {
				maxPage = ((nowPage / 5) + 1) * 5;
			}
		} else if (nowPage % 5 == 0) {
			if (nowPage <= totalPageCnt) {
				maxPage = nowPage;
			}
		}
		log.info("maxPage: " + maxPage);

		map.put("totalPageCnt", totalPageCnt);
		map.put("nowPage", nowPage);
		map.put("maxPage", maxPage);
		////////////////////////////////////////////////////////////////////

		List<CommentListQView> qvos = service.backoffice_qna_selectAll(backoffice_no, page);
		log.info("qvos : {}", qvos);
		model.addAttribute("q_vos", qvos);
		model.addAttribute("cnt", qvos.size());

		map.put("page", "qna");
		model.addAttribute("res", map);

		model.addAttribute("content", "thymeleaf/html/backoffice/dashboard/qna_list");
		model.addAttribute("title", "공간 관리");

		return "thymeleaf/layouts/backoffice/layout_dashboard";
	}

	/**
	 * 답변 작성 팝업
	 */
	@ApiOperation(value = "답변 작성", notes = "대쉬보드 공간 관리 페이지 - 문의(답변)")
	@GetMapping("/insert_comment")
	@ResponseBody
	public String backoffice_insert_comment(String backoffice_no, String room_no, String comment_no) {
		log.info("backoffice_insert_comment ()...");
		log.info("{}", backoffice_no);
		log.info("{}", room_no);

		Map<String, Object> map = new HashMap<String, Object>();

		CommentVO cvo2 = service.backoffice_insert_comment(backoffice_no, room_no, comment_no);

		map.put("cvo", cvo2);

		String json = gson.toJson(map);

		return json;
	}

	/**
	 * 답변 작성 -> backoffice_no 받아오는지 확인 해야함
	 */
	@ApiOperation(value = "답변 작성 처리", notes = "대쉬보드 공간 관리 페이지 - 문의(답변)")
	@PostMapping("/insertOK_comment")
	@ResponseBody
	public String backoffice_insertOK_comment(String backoffice_no, CommentInsertVO cvo, String comment_no) {
		log.info("backoffice_insertOK_comment_controller ()...");
		log.info("backoffice_no : {}", backoffice_no);
		log.info("cvo : {}", cvo);

		Map<String, String> map = new HashMap<String, String>();

		cvo.setMother_no(comment_no);
		cvo.setBackoffice_no(backoffice_no);
		cvo.setHost_no(backoffice_no);
		cvo.setWriter("관리자");
		cvo.setComment_state("T");
		cvo.setComment_date(new Date());
		log.info("cvo : {}", cvo);

		int result = service.backoffice_insertOK_comment(cvo);

		if (result == 1) {
			// 부모 no state T 변경
			service.update_comment_state_T(backoffice_no, comment_no);
			log.info("successed...");
			map.put("result", "1");
		}

		else {
			log.info("failed...");
			map.put("result", "0");
		}

		String json = gson.toJson(map);

		return json;
	}

	/**
	 * 답변 삭제
	 */
	@ApiOperation(value = "답변 삭제", notes = "대쉬보드 공간 관리 페이지 - 문의(답변)")
	@PostMapping("/deleteOK_comment")
	@ResponseBody
	public String backoffice_deleteOK_comment(String backoffice_no, String comment_no, String mother_no) {
		log.info("backoffice_deleteOK_comment ()...");
		log.info("{}", backoffice_no);
		log.info("{}", comment_no);

		Map<String, String> map = new HashMap<String, String>();

		int result = service.backoffice_deleteOK_comment(backoffice_no, comment_no);

		if (result == 1) {
			// 부모 no state F 변경
			service.update_comment_state_F(backoffice_no, mother_no);
			log.info("successed...");
			map.put("result", "1");
		}

		else {
			log.info("failed...");
			map.put("result", "0");
		}

		String json = gson.toJson(map);

		return json;
	}

	/**
	 * 리뷰 (리스트)
	 */
	@ApiOperation(value = "리뷰 리스트", notes = "대쉬보드 공간 관리 페이지 - 리뷰")
	@GetMapping("/review")
	public String dashboard_review(Model model, String backoffice_no,
			@RequestParam(value = "page", defaultValue = "1") Integer page) {
		log.info("backoffice_review ()...");
		log.info("{}", backoffice_no);

		/////////////////////// 페이징/////////////////////////////////////////
		Map<String, Object> map = new HashMap<String, Object>();
		// 리스트 수
		long total_rowCount_all = service.backoffice_review_selectAll_cnt(backoffice_no);
		log.info("total_rowCount_reserve_now: {}", total_rowCount_all);

		// 총 페이징되는 수
		long totalPageCnt = (long) Math.ceil(total_rowCount_all / 6.0);
		log.info("totalPageCnt: {}", totalPageCnt);

		// 현재페이지
		long nowPage = page;

		// 5page씩 끊으면 끝 페이지 번호( ex, 총 9페이지이고, 현재페이지가 6이면 maxpage = 9)
		long maxPage = 0;

		if (nowPage % 5 != 0) {
			if (nowPage == totalPageCnt) {
				maxPage = nowPage;
			} else if (((nowPage / 5) + 1) * 5 >= totalPageCnt) {
				maxPage = totalPageCnt;
			} else if (((nowPage / 5) + 1) * 5 < totalPageCnt) {
				maxPage = ((nowPage / 5) + 1) * 5;
			}
		} else if (nowPage % 5 == 0) {
			if (nowPage <= totalPageCnt) {
				maxPage = nowPage;
			}
		}
		log.info("maxPage: " + maxPage);

		map.put("totalPageCnt", totalPageCnt);
		map.put("nowPage", nowPage);
		map.put("maxPage", maxPage);
		////////////////////////////////////////////////////////////////////

		List<ReviewListView> rvvos = service.backoffice_review_selectAll(backoffice_no, page);
		log.info("rvvos : {}", rvvos);
		model.addAttribute("rv_vos", rvvos);
		model.addAttribute("cnt", rvvos.size());
		
		
		map.put("page", "review");
		model.addAttribute("res", map);
		

		model.addAttribute("content", "thymeleaf/html/backoffice/dashboard/review_list");
		model.addAttribute("title", "공간 관리");

		return "thymeleaf/layouts/backoffice/layout_dashboard";
	}

	/**
	 * 예약 관리(리스트) 
	 */
	@ApiOperation(value = "예약 리스트", notes = "대쉬보드 예약 관리 페이지 - 리스트")
	@GetMapping("/reserve")
	public String dashboard_reserve(Model model, String backoffice_no, String reserve_state,
			@RequestParam(value = "page", defaultValue = "1") Integer page) {
		log.info("backoffice_reserve ()...");
		log.info("{}", backoffice_no);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		int total_cnt = service.backoffice_reserve_selectAll_cnt(backoffice_no, reserve_state);
		
		List<ReserveListView> rvos = service.backoffice_reserve_selectAll(backoffice_no, reserve_state, 13 * (page - 1) + 1, 13 * (page));
		if (rvos == null) {
			model.addAttribute("cnt", 0);
			map.put("cnt", 0);
		} else {
			model.addAttribute("cnt", rvos.size());
			map.put("cnt", rvos.size());
		}
		
		map.put("page", "reserve");
		map.put("nowCnt", 1);
		
		if(total_cnt > 0)
			map.put("maxCnt", total_cnt);
		else
			map.put("maxCnt", 0);
		
		model.addAttribute("res", map);
		
		
		model.addAttribute("r_vos", rvos);
		model.addAttribute("reserve_state", reserve_state);

		model.addAttribute("content", "thymeleaf/html/backoffice/dashboard/reserve_list");
		model.addAttribute("title", "예약 관리");

		return "thymeleaf/layouts/backoffice/layout_dashboard";
	}
	
	/**
	 * 예약 관리(리스트) ************페이징
	 */
	@ApiOperation(value = "예약 리스트 페이징", notes = "대쉬보드 예약 관리 페이지 - 리스트")
	@GetMapping("/reserve_paging")
	public String dashboard_reserve_paging(Model model, String backoffice_no, String reserve_state,
			@RequestParam(value = "page", defaultValue = "1") Integer page) {
		log.info("backoffice_reserve ()...");
		log.info("{}", backoffice_no);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		
		List<ReserveListView> rvos = service.backoffice_reserve_selectAll(backoffice_no, reserve_state, 13 * (page - 1) + 1, 13 * (page));
		if (rvos == null) {
			model.addAttribute("cnt", 0);
			map.put("cnt", 0);
		} else {
			model.addAttribute("cnt", rvos.size());
			map.put("cnt", rvos.size());
		}
		
		map.put("nowCnt", 1);
		
		model.addAttribute("res", map);
		
		model.addAttribute("r_vos", rvos);
		model.addAttribute("reserve_state", reserve_state);
		
		model.addAttribute("content", "thymeleaf/html/backoffice/dashboard/reserve_list");
		model.addAttribute("title", "예약 관리");
		
		return "thymeleaf/layouts/backoffice/layout_dashboard";
	}

	/**
	 * 예약 관리(리스트-검색)
	 */
	@ApiOperation(value = "예약 리스트 검색", notes = "대쉬보드 예약 관리 페이지 - 리스트 검색")
	@GetMapping("/search_reserve")
	public String dashboard_reserve_search(Model model, String backoffice_no, String searchword, String reserve_state,
			@RequestParam(value = "page", defaultValue = "1") Integer page) {
		log.info("backoffice_search_reserve ()...");
		log.info("{}", backoffice_no);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		int total_cnt = service.backoffice_search_reserve_cnt(backoffice_no, searchword, reserve_state);
		
		List<ReserveListView> rvos = service.backoffice_search_reserve(backoffice_no, searchword, reserve_state,  13 * (page - 1) + 1, 13 * (page));
		if (rvos == null) {
			model.addAttribute("cnt", 0);
			map.put("cnt", 0);
		} else {
			model.addAttribute("cnt", rvos.size());
			map.put("cnt", rvos.size());
		}
		
		map.put("page", "reserve_all");
		map.put("page", "reserve_inuse");
		map.put("page", "reserve_end");
		map.put("page", "reserve_cancel");
		map.put("nowCnt", 1);
		
		if(total_cnt > 0)
			map.put("maxCnt", total_cnt);
		else
			map.put("maxCnt", 0);
		
		model.addAttribute("res", map);
		
		model.addAttribute("r_vos", rvos);
		model.addAttribute("reserve_state", reserve_state);

		model.addAttribute("content", "thymeleaf/html/backoffice/dashboard/reserve_list");
		model.addAttribute("title", "예약 관리");

		return "thymeleaf/layouts/backoffice/layout_dashboard";
	}
	
	/**
	 * 예약 관리(리스트-검색) ************페이징********************8
	 */
	@ApiOperation(value = "예약 리스트 검색", notes = "대쉬보드 예약 관리 페이지 - 리스트 검색")
	@GetMapping("/search_reserve_paging")
	public String dashboard_reserve_search_paging(Model model, String backoffice_no, String searchword, String reserve_state,
			@RequestParam(value = "page", defaultValue = "1") Integer page) {
		log.info("backoffice_search_reserve_paging ()...");
		log.info("{}", backoffice_no);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		
		List<ReserveListView> rvos = service.backoffice_search_reserve(backoffice_no, searchword, reserve_state,  13 * (page - 1) + 1, 13 * (page));
		if (rvos == null) {
			model.addAttribute("cnt", 0);
			map.put("cnt", 0);
		} else {
			model.addAttribute("cnt", rvos.size());
			map.put("cnt", rvos.size());
		}
		
		map.put("nowCnt", 1);
		
		model.addAttribute("res", map);
		
		model.addAttribute("r_vos", rvos);
		model.addAttribute("reserve_state", reserve_state);

		model.addAttribute("content", "thymeleaf/html/backoffice/dashboard/reserve_list");
		model.addAttribute("title", "예약 관리");

		return "thymeleaf/layouts/backoffice/layout_dashboard";
	}

	/**
	 * 정산 관리(리스트) 
	 */
	@ApiOperation(value = "정산 관리 리스트", notes = "대쉬보드 정산 관리 페이지 - 리스트")
	@GetMapping("/day_sales")
	public String dashboard_sales_day(Model model, String backoffice_no, String sales_date, @RequestParam(value = "page", defaultValue = "1") Integer page) {
		log.info("backoffice_day_sales()...");
		log.info("{}", backoffice_no);

		SalesSettlementDetailView svo = service.backoffice_sales_selectOne(backoffice_no, sales_date);
		model.addAttribute("svo", svo);
		model.addAttribute("sales_date", sales_date);
		log.info("svo:::{}", svo);
		
		
		/////////////////////// 페이징/////////////////////////////////////////
		Map<String, Object> map = new HashMap<String, Object>();
		// 리스트 수
		long total_rowCount_all = service.backoffice_sales_selectAll_cnt(backoffice_no);
		log.info("total_rowCount_reserve_now: {}", total_rowCount_all);

		// 총 페이징되는 수
		long totalPageCnt = (long) Math.ceil(total_rowCount_all / 8.0);
		log.info("totalPageCnt: {}", totalPageCnt);

		// 현재페이지
		long nowPage = page;

		// 5page씩 끊으면 끝 페이지 번호( ex, 총 9페이지이고, 현재페이지가 6이면 maxpage = 9)
		long maxPage = 0;

		if (nowPage % 5 != 0) {
			if (nowPage == totalPageCnt) {
				maxPage = nowPage;
			} else if (((nowPage / 5) + 1) * 5 >= totalPageCnt) {
				maxPage = totalPageCnt;
			} else if (((nowPage / 5) + 1) * 5 < totalPageCnt) {
				maxPage = ((nowPage / 5) + 1) * 5;
			}
		} else if (nowPage % 5 == 0) {
			if (nowPage <= totalPageCnt) {
				maxPage = nowPage;
			}
		}
		log.info("maxPage: " + maxPage);

		map.put("totalPageCnt", totalPageCnt);
		map.put("nowPage", nowPage);
		map.put("maxPage", maxPage);
		////////////////////////////////////////////////////////////////////
		map.put("page", "sales_day");
		map.put("page", "sales_week");
		map.put("page", "sales_month");
		model.addAttribute("res", map);

		List<SalesSettlementViewVO> svos = service.backoffice_sales_selectAll(backoffice_no,page);
		model.addAttribute("s_vos", svos);
		model.addAttribute("cnt", svos.size());
		log.info("svos:::{}", svos);

		model.addAttribute("content", "thymeleaf/html/backoffice/dashboard/sales_day");
		model.addAttribute("title", "정산 관리");

		return "thymeleaf/layouts/backoffice/layout_dashboard";
	}

//	@RequestMapping(value = "/backoffice_week_sales", method = RequestMethod.GET)
//	public String dashboard_sales_week() {
//		return ".dash_board/sales_week";
//	}
//
//	@RequestMapping(value = "/backoffice_month_sales", method = RequestMethod.GET)
//	public String dashboard_sales_month() {
//		return ".dash_board/sales_month";
//	}

	/**
	 * 정산 상태 변경
	 */
	@ApiOperation(value = "정산 상태 변경", notes = "대쉬보드 정산 관리 페이지 - 정산 상태 변경")
	@PostMapping("/updateOK_sales")
	@ResponseBody
	public String backoffice_updateOK_sales(String backoffice_no, String room_no, String payment_no) {
		log.info("backoffice_updateOK_sales ()...");
		log.info("{}", backoffice_no);

		Map<String, String> map = new HashMap<String, String>();

		int result = service.backoffice_updateOK_sales(backoffice_no, room_no, payment_no);
		log.info("integerereretrer", Integer.toString(result));
		if (result == 1) {
			log.info("successed...");
			map.put("result", "1");
		}

		else {
			log.info("failed...");
			map.put("result", "0");
		}

		String json = gson.toJson(map);

		return json;
	}

	/**
	 * 환경설정 페이지 출력
	 */
	@ApiOperation(value = "환경설정", notes = "대쉬보드 환경설정 페이지")
	@GetMapping("/settings")
	public String backoffice_settings(BackOfficeVO bvo, Model model) {
		log.info("backoffice_settings()...");
		BackOfficeVO bvo2 = service.backoffice_setting_selectOne(bvo);
		log.info("result: {}.", bvo2);

		OptionEngToKorMap optionEngToKorMap = new OptionEngToKorMap();
		
		if(bvo2.getBackoffice_tag()!=null) {
		String[] backoffice_tag = bvo2.getBackoffice_tag().split(",");
			for (int i = 0; i < backoffice_tag.length; i++) {
				backoffice_tag[i] = "#" + backoffice_tag[i];
			}
			model.addAttribute("backoffice_tag", backoffice_tag);
		}else {
			model.addAttribute("backoffice_tag", "-");
		}
		
		if(bvo2.getBackoffice_option()!=null) {
		List<String> backoffice_option = optionEngToKorMap.splitOption(bvo2.getBackoffice_option());
			model.addAttribute("backoffice_option", backoffice_option);
		}else {
			model.addAttribute("backoffice_option", "-");
		}
		
		if (bvo2.getBackoffice_around()!=null) {
		List<String> backoffice_around = optionEngToKorMap.splitAroundOption(bvo2.getBackoffice_around());
			model.addAttribute("backoffice_around", backoffice_around);
		}else {
			model.addAttribute("backoffice_around", "-");
		}
		
		model.addAttribute("vo", bvo2);

		model.addAttribute("content", "thymeleaf/html/backoffice/dashboard/settings");
		model.addAttribute("title", "환경설정");

		return "thymeleaf/layouts/backoffice/layout_dashboard";
	}

	/**
	 * 환경설정에서 비밀번호 수정
	 */
	@ApiOperation(value = "비밀번호 변경", notes = "대쉬보드 환경설정 페이지 - 비밀번호 변경")
	@GetMapping("/update_pw")
	@ResponseBody
	public String backoffice_update_pw(BackOfficeVO bvo) {
		log.info("backoffice_update_pw ()...");
		log.info("{}", bvo);

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		Map<String, String> map = new HashMap<String, String>();

		// 비밀번호 일치 여부 확인
		BackOfficeVO bvo2 = service.backoffice_select_pw(bvo);
		log.info("bvo2:::{}", bvo2);

		boolean result = encoder.matches(bvo.getBackoffice_pw(), bvo2.getBackoffice_pw());
		log.info("(비밀번호 확인부분)res: {}", result);

		if (result == true) {
			log.info("successed...");
			map.put("result", "1");
		} else {
			log.info("failed...");
			map.put("result", "0");
		}

		String json = gson.toJson(map);

		return json;
	}

	/**
	 * 환경설정에서 업체 탈퇴 요청
	 */
	@ApiOperation(value = "업체 탈퇴 요청", notes = "대쉬보드 환경설정 페이지 - 업체 탈퇴 요청")
	@PostMapping("/setting_delete")
	@ResponseBody
	public String backoffice_setting_delete(BackOfficeVO bvo) {
		log.info("backoffice_setting_delete ()...");
		log.info("{}", bvo);

		Map<String, String> map = new HashMap<String, String>();

		int result = service.backoffice_setting_delete(bvo);

		if (result == 1) {
			service.backoffice_room_deleteAlL(bvo);

			log.info("successed...");
			map.put("result", "1");
		}

		else {
			log.info("failed...");
			map.put("result", "0"); // 남은 예약이 있을 시
		}

		String json = gson.toJson(map);

		return json;
	}

	/**
	 * 환경설정에서 정보 변경
	 */
	@ApiOperation(value = "업체 정보 변경 폼", notes = "대쉬보드 환경설정 페이지 - 업체 정보 변경")
	@GetMapping("/update_host")
	public String backoffice_update_host(BackOfficeVO bvo, Model model) {
		log.info("backoffice_update_host ()...");
		log.info("{}", bvo);
		
		BackOfficeVO bvo2 = service.backoffice_setting_selectOne(bvo);
		log.info("result: {}.", bvo2);
		BackOfficeOperatingTimeVO ovo = service.backoffice_setting_selectOne_operatingtime(bvo2.getBackoffice_no());
		log.info("result: {}.", ovo);

//		OptionEngToKorMap optionEngToKorMap = new OptionEngToKorMap();
//		List<String> tags = new ArrayList<String>();
//		if (bvo2.getBackoffice_tag() != null) {
//			tags.addAll(optionEngToKorMap.splitTag(bvo2.getBackoffice_tag()));
//		}
//		List<String> types = new ArrayList<String>();
//		if (bvo2.getBackoffice_type() != null) {
//			String[] type_split = bvo2.getBackoffice_type().split(",");
//			for (int i = 0; i < type_split.length; i++) {
//				types.add(type_split[i]);
//			}
//		}
//		List<String> options = new ArrayList<String>();
//		if (bvo2.getBackoffice_option() != null) {
//			String[] option_split = bvo2.getBackoffice_option().split(",");
//			for (int i = 0; i < option_split.length; i++) {
//				options.add(option_split[i]);
//			}
//		}
//		List<String> arounds = new ArrayList<String>();
//		if (bvo2.getBackoffice_around() != null) {
//			String[] around_split = bvo2.getBackoffice_around().split(",");
//			for (int i = 0; i < around_split.length; i++) {
//				arounds.add(around_split[i]);
//			}
//		}
//
		model.addAttribute("backoffice_tag", bvo2.getBackoffice_tag());
//		model.addAttribute("backoffice_type", types);
//		model.addAttribute("backoffice_option", options);
//		model.addAttribute("backoffice_around", arounds);
		model.addAttribute("vo", bvo2);
		model.addAttribute("ovo", ovo);

		model.addAttribute("content", "thymeleaf/html/backoffice/dashboard/update_host");
		model.addAttribute("title", "업체 정보 변경");

		return "thymeleaf/layouts/backoffice/layout_dashboard";
	}

	/**
	 * 환경설정에서 정보 변경
	 */
	@ApiOperation(value = "업체 정보 변경 처리", notes = "대쉬보드 환경설정 페이지 - 업체 정보 변경")
	@PostMapping("/updateOK_host")
	public String backoffice_updateOK_host(BackOfficeVO bvo, BackOfficeOperatingTimeVO ovo,
			MultipartHttpServletRequest mtfRequest,
			@RequestParam(value = "multipartFile_room") MultipartFile multipartFile_room, Model model) {
		log.info("backoffice_updateOK_host ()...");
		log.info("{}", bvo);
		
		BackOfficeVO bvo2 = service.backoffice_setting_selectOne(bvo);
		if(!bvo.getBackoffice_image().equals(bvo2.getBackoffice_image())) {
			// 이미지 파일
			bvo = fileService.backoffice_image_upload(bvo, mtfRequest, multipartFile_room);
			log.info("filupload room:{}", bvo);
		} 

		// 운영시간
		BackOfficeOperatingTimeVO_datetype ovo2 = new BackOfficeOperatingTimeVO_datetype();
		ovo2 = operatingTime.operatingTime(ovo, ovo2);

		// 백오피스 업체 정보 업데이트
		int update_host =  service.backoffice_updateOK_host(bvo);
//		service.backoffice_updateOK_host(bvo);

		// 백오피스 운영 시간 업데이트
		ovo2.setBackoffice_no(bvo.getBackoffice_no());
		int update_opt =  service.backoffice_updateOK_opt(ovo2);
		log.info("update_opt:::{}",update_opt);
//		service.backoffice_updateOK_opt(ovo2);

		String rt = "";
		if(update_host==1&&update_opt==1) {
		rt = "redirect:settings?backoffice_no="+bvo.getBackoffice_no();
		}else {
//			rt = "redirect:update_host?backoffice_no=B1001";
			rt = "redirect:update_host?backoffice_no="+bvo.getBackoffice_no();
		}

		return rt;
	}

	/**
	 * 일정 관리 페이지
	 */
	@ApiOperation(value = "일정 관리", notes = "대쉬보드 - 일정 관리")
	@GetMapping("/schedule")
	public String backoffice_schedule(String backoffice_no, Model model) { 
		log.info("backoffice_schedule controller()...");

		model.addAttribute("backoffice_no", backoffice_no);

		model.addAttribute("content", "thymeleaf/html/backoffice/dashboard/schedule");
		model.addAttribute("title", "일정 관리");

		return "thymeleaf/layouts/backoffice/layout_dashboard";
	}

	/**
	 * 일정 관리 - 날짜, 시간 선택 후
	 */
	@ApiOperation(value = "일정 관리", notes = "대쉬보드 - 일정 관리")
	@GetMapping("/schedule_research")
	@ResponseBody
	public String backoffice_schedule_research(String backoffice_no, String not_sdate, String not_edate,
			String not_stime, String not_etime, String off_type, Integer page, Model model) {
		log.info("backoffice_schedule_research controller()...");

		Map<String, Object> map = new HashMap<String, Object>();

		List<ScheduleListView> sche = service.backoffice_schedule_list(backoffice_no, not_sdate, not_edate, not_stime,
				not_etime, off_type);
		
		int total_cnt = sche.size();
		log.info("total_cnt::{}",total_cnt);
		if (total_cnt > 0)
			map.put("maxCnt", total_cnt);
		else
			map.put("maxCnt", 0);

		int min = 8 * (page - 1) + 1;
		int max = 8 * (page);
		if(total_cnt<max) {
			max = total_cnt;
		}
		log.info("min::{}",min);
		log.info("max::{}",max);

		List<ScheduleListView> schedule = sche.subList(min-1, max);

		log.info("result: {}.", schedule);
		log.info("cnt: {}.", schedule.size());

		map.put("sc_vos", schedule);
		if (schedule == null) {
			map.put("cnt", 0);
		} else {
			map.put("cnt", schedule.size());
		}

		map.put("page", "schedule");
		map.put("nowCnt", 1);


		String json = gson.toJson(map);

		return json;
	}

	/**
	 * 일정 관리 - 날짜, 시간 선택 후 ***********페이징
	 */
	@ApiOperation(value = "일정 관리", notes = "대쉬보드 - 일정 관리")
	@GetMapping("/schedule_research_paging")
	@ResponseBody
	public String backoffice_schedule_research_paging(String backoffice_no, String not_sdate, String not_edate,
			String not_stime, String not_etime, String off_type, Integer page, Model model) {
		log.info("backoffice_schedule_research controller()...");

		Map<String, Object> map = new HashMap<String, Object>();

		List<ScheduleListView> sche = service.backoffice_schedule_list(backoffice_no, not_sdate, not_edate, not_stime,
				not_etime, off_type);

		int total_cnt = sche.size();
		log.info("total_cnt::{}",total_cnt);
		if (total_cnt > 0)
			map.put("maxCnt", total_cnt);
		else
			map.put("maxCnt", 0);

		int min = 8 * (page - 1) + 1;
		int max = 8 * (page);
		if(total_cnt<max) {
			max = total_cnt;
		}
		log.info("min::{}",min);
		log.info("max::{}",max);

		List<ScheduleListView> schedule = sche.subList(min-1, max);

		log.info("result: {}.", schedule);
		log.info("cnt: {}.", schedule.size());

		map.put("sc_vos", schedule);
		if (schedule == null) {
			map.put("cnt", 0);
		} else {
			map.put("cnt", schedule.size());
		}

		String json = gson.toJson(map);

		return json;
	}

	/**
	 * 일정 관리 - 날짜, 시간 선택 후, 휴무, 브레이크타임 설정
	 */
	@ApiOperation(value = "일정 관리 - 휴무, 브레이크타임 설정", notes = "대쉬보드 - 일정 관리")
	@PostMapping("/scheduleOK")
	@ResponseBody
	public String backoffice_scheduleOK(String backoffice_no, String not_sdate, String not_edate, String not_stime,
			String not_etime, String room_no, String off_type, Model model, Integer page) throws ParseException {
		log.info("backoffice_scheduleOK controller()...");

		Map<String, Object> map = new HashMap<String, Object>();

		// 브레이크 타임
		if (off_type.equals("breaktime")) {
			log.info("브레이크 타임");
			not_edate = (not_sdate);
		} else if (off_type.equals("dayoff")) {
			log.info("휴무");
			not_stime = "00:00:00";
			not_etime = "00:00:00";
		}
		log.info("not_sdate : {} ", not_sdate);
		log.info("not_edate : {} ", not_edate);

		String not_s = not_sdate + not_stime;
		String not_e = not_edate + not_etime;
		log.info("not_stime : {} ", not_stime);
		log.info("not_etime : {} ", not_etime);

		int result = service.backoffice_schedueOK(backoffice_no, not_s, not_e, room_no);

		if (result == 1) {
			log.info("successed...");
			map.put("result", "1");
		} else {
			log.info("falied...");
			map.put("result", "0");
		}
		////////////////////////////////////////////////////////////////////////////////////////
		List<ScheduleListView> sche = service.backoffice_schedule_list(backoffice_no, not_sdate, not_edate, not_stime,
				not_etime, off_type);

		int total_cnt = sche.size();
		log.info("total_cnt::{}",total_cnt);
		if (total_cnt > 0)
			map.put("maxCnt", total_cnt);
		else
			map.put("maxCnt", 0);

		int min = 8 * (page - 1) + 1;
		int max = 8 * (page);
		if(total_cnt<max) {
			max = total_cnt;
		}
		log.info("min::{}",min);
		log.info("max::{}",max);

		List<ScheduleListView> schedule = sche.subList(min-1, max);

		log.info("result: {}.", schedule);
		log.info("cnt: {}.", schedule.size());

		map.put("sc_vos", schedule);
		if (schedule == null) {
			map.put("cnt", 0);
		} else {
			map.put("cnt", schedule.size());
		}
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		String json = gson.toJson(map);

		return json;
	}

	/**
	 * 일정 관리 - 해당 날짜, 시간에 예약자 리스트
	 */
	@ApiOperation(value = "예약자 리스트", notes = "대쉬보드 - 예약자 리스트")
	@GetMapping("/reservation")
	public String backoffice_reservation(String backoffice_no, String room_no, String not_sdate, String not_edate,
			String not_stime, String not_etime, String off_type, Model model, Integer page) {
		log.info("backoffice_reservation controller()...");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		int total_cnt = service.backoffice_reservation_cnt(backoffice_no, not_sdate, not_edate, not_stime,
				not_etime, room_no, off_type);

		if (off_type.equals("breaktime")) {
			log.info("브레이크 타임");
			not_edate = (not_sdate);
		} else if (off_type.equals("dayoff")) {
			log.info("휴무");
			not_stime = "00:00:00";
			not_etime = "00:00:00";
		}

		String reserve_stime = (not_sdate + " " + not_stime);
		log.info("reserve_stime : {} ", reserve_stime);

		String reserve_etime = (not_edate + " " + not_etime);
		log.info("reserve_etime : {} ", reserve_etime);

		List<ReservationView> rv_vos = service.backoffice_reservation(backoffice_no, not_sdate, not_edate, not_stime,
				not_etime, room_no, off_type, 8 * (page - 1) + 1, 8 * (page));
		log.info("result: {}.", rv_vos);
		log.info("cnt: {}.", rv_vos.size());
		
		if (rv_vos == null)
			map.put("cnt", 0);
		else
			map.put("cnt", rv_vos.size());
		
		map.put("page", "reservation");
		map.put("nowCnt", 1);
		
		if(total_cnt > 0)
			map.put("maxCnt", total_cnt);
		else
			map.put("maxCnt", 0);
		
		model.addAttribute("res", map);
		model.addAttribute("reserve_stime", reserve_stime);
		model.addAttribute("reserve_etime", reserve_etime);
		model.addAttribute("rv_vos", rv_vos);
//		model.addAttribute("cnt", rv_vos.size());

		model.addAttribute("content", "thymeleaf/html/backoffice/dashboard/reservation");
		model.addAttribute("title", "일정 관리 - 예약자");

		return "thymeleaf/layouts/backoffice/layout_dashboard";
	}
	
	/**
	 * 일정 관리 - 해당 날짜, 시간에 예약자 리스트 *************페이징**************
	 */
	@ApiOperation(value = "예약자 리스트", notes = "대쉬보드 - 예약자 리스트")
	@GetMapping("/reservation_paging")
	public String backoffice_reservation_paging(String backoffice_no, String room_no, String not_sdate, String not_edate,
			String not_stime, String not_etime, String off_type, Model model, Integer page) {
		log.info("backoffice_reservation controller_paging()...");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		String reserve_stime = (not_sdate + not_stime);
		log.info("reserve_stime : {} ", reserve_stime);
		
		String reserve_etime = (not_edate + not_etime);
		log.info("reserve_etime : {} ", reserve_etime);
		
		List<ReservationView> rv_vos = service.backoffice_reservation(backoffice_no, not_sdate, not_edate, not_stime,
				not_etime, room_no, off_type, 8 * (page - 1) + 1, 8 * (page));
		log.info("result: {}.", rv_vos);
		log.info("cnt: {}.", rv_vos.size());
		
		if (rv_vos == null)
			map.put("cnt", 0);
		else
			map.put("cnt", rv_vos.size());
		
//		map.put("page", "reservation");
//		map.put("nowCnt", 1);
		model.addAttribute("res", map);
		
		model.addAttribute("reserve_stime", reserve_stime);
		model.addAttribute("reserve_etime", reserve_etime);
		model.addAttribute("rv_vos", rv_vos);
		
		model.addAttribute("content", "thymeleaf/html/backoffice/dashboard/reservation");
		model.addAttribute("title", "일정 관리 - 예약자");
		
		return "thymeleaf/layouts/backoffice/layout_dashboard";
	}

	/**
	 * 일정 관리 - 예약취소
	 * @throws IOException 
	 */
	@ApiOperation(value = "일정 관리 - 예약 취소", notes = "대쉬보드 - 일정 관리")
	@PostMapping("/reservation_cancel")
	@ResponseBody
	public String backoffice_reservation_cancel(String backoffice_no, String reserve_no, String user_no,
			String user_email, String reserve_stime, String reserve_etime, Model model) throws ParseException, IOException {
		log.info("backoffice_reservation_cancel controller()...");

		Map<String, Object> map = new HashMap<String, Object>();

		// 에약 상태 cancel로 변경, 예약자에게 취소 메일 보내기, 결제 상태 false?? , 결제 테이블에서 사용한 마일리지와 돈 환불.

		BOPaymentVO pvo = service.backoffice_reservation_cancel(backoffice_no, reserve_no, user_no);

		if (pvo != null) {
			
			String token = cancelService.getToken();
			int amount = Integer.parseInt(pvo.getActual_payment());
	        cancelService.payMentCancel(token, pvo.getImp_uid(), amount, "관리자 취소");
	        
			BackOfficeVO bvo = service.backoffice_select_companyname(backoffice_no);
			String company_name = bvo.getCompany_name();
			int flag = dashboardSendEmail.reserve_cancel_mail(user_no, user_email, reserve_stime, reserve_etime,
					company_name);
			if (flag == 1) {
				log.info("successed...");
				map.put("pvo", pvo);
				map.put("result", "1");
			} else {
				log.info("mail falied...");
				map.put("result", "0");
			}
		} else {
			log.info("cancel falied...");
			map.put("result", "0");
		}

		String json = gson.toJson(map);

		return json;
	}

	/**
	 * 일정 관리 - 백오피스 휴무 일정
	 */
	@ApiOperation(value = "일정 관리 - 휴무 일정", notes = "대쉬보드 - 일정 관리")
	@GetMapping("/schedule_calander")
	@ResponseBody
	public String backoffice_schedule_calander(String backoffice_no, Model model) throws ParseException {
		log.info("backoffice_schedule_calander controller()...");

		Map<String, Object> map = new HashMap<String, Object>();

		List<ScheduleEntity> vo = service.backoffice_schedule_calander(backoffice_no);

		log.info("vos...{}", vo);
		map.put("vos", vo);

		String json = gson.toJson(map);

		return json;
	}

	/**
	 * 일정 관리 - 휴무/브레이크타임 취소
	 */
	@ApiOperation(value = "일정 관리 - 일정 취소", notes = "대쉬보드 - 일정 관리")
	@PostMapping("/schedule_cancel")
	@ResponseBody
	public String backoffice_schedule_cancel(String backoffice_no, String schedule_no, Model model)
			throws ParseException {
		log.info("backoffice_schedule_cancel controller()...");

		Map<String, Object> map = new HashMap<String, Object>();

		int flag = service.backoffice_schedule_cancel(backoffice_no, schedule_no);

		if (flag == 1) {
			log.info("successed...");
			map.put("result", "1");
		} else {
			log.info("falied...");
			map.put("result", "0");
		}

		String json = gson.toJson(map);

		return json;
	}

}
