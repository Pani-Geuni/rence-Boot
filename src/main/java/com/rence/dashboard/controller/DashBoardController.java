package com.rence.dashboard.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rence.backoffice.model.BackOfficeVO;
import com.rence.dashboard.model.ReserveSummaryVO;
import com.rence.dashboard.service.DashboardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@Api(tags="대쉬보드 컨트롤러")
@RequestMapping("/dashboard")
public class DashBoardController {
	
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	@Autowired
	DashboardService service;

	/**
	 * 대쉬보드 메인
	 */
	@ApiOperation(value="대쉬보드 메인", notes="대쉬보드 메인 페이지")
	@GetMapping("/main")
	public String dashboard_main(Model model, String backoffice_no) {

		List<ReserveSummaryVO> rvos = service.reserve_summary_selectAll(backoffice_no);
//		List<CommentSummaryVO> cvos = service.comment_summary_selectAll(backoffice_no);
//		SalesSettlementSummaryVO svo = service.payment_summary_selectOne(backoffice_no);
//		RoomSummaryVO rmvo = service.room_summary_selectOne(backoffice_no);
//
//		model.addAttribute("r_vos", rvos);
//		model.addAttribute("r_cnt", rvos.size());
//		model.addAttribute("c_vos", cvos);
//		model.addAttribute("c_cnt", cvos.size());
//		model.addAttribute("svo", svo);
//		model.addAttribute("rmvo", rmvo);

		model.addAttribute("content", "thymeleaf/html/backoffice/dashboard/main");
		model.addAttribute("title", "대쉬보드 메인");

		return "thymeleaf/layouts/backoffice/layout_dashboard";
	}

	/**
	 * 공간 리스트
	 */
	@ApiOperation(value="공간 리스트", notes="대쉬보드 공간 관리 페이지")
	@GetMapping("/room")
	public String dashboard_room_list(Model model, String backoffice_no) {
//		List<RoomVO> rmvos = service.dashboard_room_list(backoffice_no);
//		model.addAttribute("rm_vos", rmvos);

		model.addAttribute("content", "thymeleaf/html/backoffice/dashboard/room");
		model.addAttribute("title", "공간 관리");

		return "thymeleaf/layouts/backoffice/layout_dashboard";
	}

	/**
	 * 공간 추가 팝업
	 */
	@ApiOperation(value="공간 추가", notes="대쉬보드 공간 관리 페이지")
	@GetMapping("/insert_room")
	@ResponseBody
	public String backoffice_insert_room(String backoffice_no) {
		log.info("backoffice_insertOK_room ()...");
		log.info("{}", backoffice_no);

		Map<String, List<String>> map = new HashMap<String,List<String>>();
		
//		BackOfficeVO bvo = service.select_one_backoffice_info(backoffice_no);
//		RoomVO rmvo = new RoomVO();
//		
//		String type = bvo.getBackoffice_type().replace("meeting_room", "meeting_04,meeting_06,meeting_10");
//		rmvo.setRoom_type(type);
//
//		List<String> type_list = new ArrayList<String>();
//		
//		if (rmvo.getRoom_type() != null) {
//			String[] type_split = rmvo.getRoom_type().split(",");
//			
//			for (int i=0; i < type_split.length; i++) {
//				type_list.add(type_split[i]);
//			}
//			
//		} else {
//			type_list.add("타입 없음");
//		}
//
//		map.put("room_type", type_list);
		
		String json = gson.toJson(map);

		return json;
	}

	/**
	 * 공간 추가
	 */
//	@ApiOperation(value="공간 추가 처리", notes="대쉬보드 공간 관리 페이지")
//	@PostMapping("/insertOK_room")
//	@ResponseBody
//	public String backoffice_insertOK_room(RoomVO rvo,String backoffice_no) {
//		log.info("backoffice_insertOK_room ()...");
//		log.info("{}", backoffice_no);
//
//		Map<String, String> map = new HashMap<String,String>();
//
//		int result = service.backoffice_insertOK_room(backoffice_no, rvo);
//
//		if (result == 1) {
//			log.info("successed...");
//			map.put("result", "1");
//		}
//
//		else {
//			log.info("failed...");
//			map.put("result", "0");
//		}
//		
//		String json = gson.toJson(map);
//
//		return json;
//	}
//
	/**
	 * 공간 수정 팝업
	 */
//	@ApiOperation(value="공간 수정", notes="대쉬보드 공간 관리 페이지")
//	@GetMapping("/update_room")
//	@ResponseBody
//	public String backoffice_update_room(String backoffice_no, String room_no) {
//		log.info("backoffice_update_room ()...");
//		log.info("{}", backoffice_no);
//
//		Map<String, Object> map1 = new HashMap<String,Object>();
//		Map<String, List<String>> map2 = new HashMap<String,List<String>>();
//		Map<Map<String, Object>, Map<String, List<String>>> map3 = new HashMap<Map<String,Object>, Map<String,List<String>>>();
//		
//		
//		BackOfficeVO bvo = service.select_one_backoffice_info(backoffice_no);
//		RoomVO rmvo = new RoomVO();
//		
//		String type = bvo.getBackoffice_type().replace("meeting_room", "meeting_04,meeting_06,meeting_10");
//		rmvo.setRoom_type(type);
//		
//		List<String> type_list = new ArrayList<String>();
//
//		if (rmvo.getRoom_type() != null) {
//			String[] type_split = rmvo.getRoom_type().split(",");
//			
//			for (int i=0; i < type_split.length; i++) {
//				type_list.add(type_split[i]);
//			}
//			
//		} else {
//			type_list.add("타입 없음");
//		}
//		
//		rmvo = service.select_one_room_info(backoffice_no, room_no);
//		map1.put("rmvo", rmvo); 
//
//		map2.put("room_type", type_list);
//		map3.put(map1, map2);
//		String json = gson.toJson(map3);
//
//		return json;
//	}
	
//	/**
//	 * 공간 수정
//	 */
//	@RequestMapping(value = "/backoffice_updateOK_room ", method = RequestMethod.POST)
//	@ResponseBody
//	public JSONObject backoffice_updateOK_room(RoomVO rvo,String backoffice_no) {
//		logger.info("backoffice_updateOK_room ()...");
//		logger.info("{}", backoffice_no);
//
//		JSONObject jsonObject = new JSONObject();
//
//		int result = service.backoffice_updateOK_room(backoffice_no, rvo);
//
//		if (result == 1) {
//			logger.info("successed...");
//			jsonObject.put("result", "1");
//		}
//
//		else {
//			logger.info("failed...");
//			jsonObject.put("result", "0");
//		}
//
//		return jsonObject;
//	}

	/**
	 * 공간 삭제
	 */
	@ApiOperation(value="공간 삭제", notes="대쉬보드 공간 관리 페이지")
	@PostMapping("/deleteOK_room")
	@ResponseBody
	public String backoffice_deleteOK_room(String backoffice_no, String room_no) {
		log.info("backoffice_deleteOK_room ()...");
		log.info("{}", backoffice_no);

		Map<String, String> map = new HashMap<String, String>();

//		int result = service.backoffice_deleteOK_room(backoffice_no, room_no);
//
//		if (result == 1) {
//			log.info("successed...");
//			map.put("result", "1");
//		}
//
//		else {
//			log.info("failed...");
//			map.put("result", "0");  //남은 예약이 있을 시
//		}

		String json = gson.toJson(map);
		
		return json;
	}

	/**
	 * 문의(리스트)
	 */
	@ApiOperation(value="문의 리스트", notes="대쉬보드 공간 관리 페이지 - 문의")
	@GetMapping("/qna")
	public String dashboard_qna(Model model, String backoffice_no) {
		log.info("backoffice_qna ()...");
		log.info("{}", backoffice_no);
//		List<CommentListVO> qvos = service.backoffice_qna_selectAll(backoffice_no);
//		model.addAttribute("q_vos", qvos);
//		model.addAttribute("cnt", qvos.size());
		
		model.addAttribute("content", "thymeleaf/html/backoffice/dashboard/qna_list");
		model.addAttribute("title", "공간 관리");

		return "thymeleaf/layouts/backoffice/layout_dashboard";
	}

	/**
	 * 답변 작성 팝업
	 */
	@ApiOperation(value="답변 작성", notes="대쉬보드 공간 관리 페이지 - 문의(답변)")
	@GetMapping("/insert_comment")
	@ResponseBody
	public String backoffice_insert_comment(String backoffice_no, String room_no, String comment_no) {
		log.info("backoffice_insert_comment ()...");
		log.info("{}", backoffice_no);
		log.info("{}", room_no);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
//		CommentVO cvo2 = service.backoffice_insert_comment(backoffice_no,room_no,comment_no);
//		
//		map.put("cvo", cvo2);
		
		String json = gson.toJson(map);

		return json;
	}

	/**
	 * 답변 작성
	 */
//	@ApiOperation(value="답변 작성 처리", notes="대쉬보드 공간 관리 페이지 - 문의(답변)")
//	@PostMapping("/insertOK_comment")
//	@ResponseBody
//	public String backoffice_insertOK_comment(String backoffice_no, CommentVO cvo, String comment_no) {
//		log.info("backoffice_insertOK_comment ()...");
//		log.info("{}", backoffice_no);
//
//		Map<String, String> map = new HashMap<String, String>();
//		
//		cvo.setMother_no(comment_no);
//
//		int result = service.backoffice_insertOK_comment(backoffice_no, cvo);
//
//		if (result == 1) {
//			log.info("successed...");
//			map.put("result", "1");
//		}
//
//		else {
//			log.info("failed...");
//			map.put("result", "0");
//		}
//		
//		String json = gson.toJson(map);
//
//		return json;
//	}
	
	/**
	 * 답변 삭제
	 */
	@ApiOperation(value="답변 삭제", notes="대쉬보드 공간 관리 페이지 - 문의(답변)")
	@PostMapping("/deleteOK_comment")
	@ResponseBody
	public String backoffice_deleteOK_comment(String backoffice_no, String comment_no, String mother_no) {
		log.info("backoffice_deleteOK_comment ()...");
		log.info("{}", backoffice_no);
		log.info("{}", comment_no);
		
		Map<String, String> map = new HashMap<String, String>();
		
//		int result = service.backoffice_deleteOK_comment(backoffice_no, comment_no, mother_no);
//		
//		if (result == 1) {
//			log.info("successed...");
//			map.put("result", "1");
//		}
//		
//		else {
//			log.info("failed...");
//			map.put("result", "0");
//		}
		
		String json = gson.toJson(map);
		
		return json;
	}

	/**
	 * 리뷰 (리스트)
	 */
	@ApiOperation(value="리뷰 리스트", notes="대쉬보드 공간 관리 페이지 - 리뷰")
	@GetMapping("/review")
	public String dashboard_review(Model model, String backoffice_no) {
		log.info("backoffice_review ()...");
		log.info("{}", backoffice_no);
//		List<ReviewVO> rvvos = service.backoffice_review_selectAll(backoffice_no);
//		model.addAttribute("rv_vos", rvvos);
//		model.addAttribute("cnt", rvvos.size());
		
		model.addAttribute("content", "thymeleaf/html/backoffice/dashboard/review_list");
		model.addAttribute("title", "공간 관리");

		return "thymeleaf/layouts/backoffice/layout_dashboard";
	}

	/**
	 * 예약 관리(리스트)
	 */
	@ApiOperation(value="예약 리스트", notes="대쉬보드 예약 관리 페이지 - 리스트")
	@GetMapping("/reserve")
	public String dashboard_reserve(Model model, String backoffice_no, String reserve_state) {
		log.info("backoffice_reserve ()...");
		log.info("{}", backoffice_no);
//		List<ReserveVO> rvos = service.backoffice_reserve_selectAll(backoffice_no, reserve_state);
//		model.addAttribute("r_vos", rvos);
//		model.addAttribute("cnt", rvos.size());
		model.addAttribute("reserve_state", reserve_state);
		
		model.addAttribute("content", "thymeleaf/html/backoffice/dashboard/reserve_list");
		model.addAttribute("title", "예약 관리");

		return "thymeleaf/layouts/backoffice/layout_dashboard";
	}

	/**
	 * 예약 관리(리스트-검색)
	 */
	@ApiOperation(value="예약 리스트 검색", notes="대쉬보드 예약 관리 페이지 - 리스트 검색")
	@GetMapping("/search_reserve")
	public String dashboard_reserve_search(Model model, String backoffice_no, String searchword, String reserve_state) {
		log.info("backoffice_search_reserve ()...");
		log.info("{}", backoffice_no);
//		List<ReserveVO> rvos = service.backoffice_search_reserve(backoffice_no, searchword, reserve_state);
//		model.addAttribute("r_vos", rvos);
//		model.addAttribute("cnt", rvos.size());
		model.addAttribute("reserve_state", reserve_state);
		
		model.addAttribute("content", "thymeleaf/html/backoffice/dashboard/reserve_list");
		model.addAttribute("title", "예약 관리");

		return "thymeleaf/layouts/backoffice/layout_dashboard";
	}

	/**
	 * 정산 관리(리스트)
	 */
	@ApiOperation(value="정산 관리 리스트", notes="대쉬보드 정산 관리 페이지 - 리스트")
	@GetMapping("/day_sales")
	public String dashboard_sales_day(Model model, String backoffice_no, String sales_date) {
		log.info("backoffice_day_sales()...");
		log.info("{}", backoffice_no);
		
//		SalesSettlementPreVO svo = service.backoffice_sales_selectOne(backoffice_no, sales_date);
//		model.addAttribute("svo", svo);
//		model.addAttribute("sales_date",sales_date);
//		
//		List<SalesSettlementVO> svos = service.backoffice_sales_selectAll(backoffice_no);
//		model.addAttribute("s_vos", svos);
//		model.addAttribute("cnt", svos.size());
//		log.info("svossvossvos:::{}",svos);
		
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
	@ApiOperation(value="정산 상태 변경", notes="대쉬보드 정산 관리 페이지 - 정산 상태 변경")
	@PostMapping("/updateOK_sales")
	@ResponseBody
	public String backoffice_updateOK_sales(String backoffice_no, String room_no, String payment_no) {
		log.info("backoffice_updateOK_sales ()...");
		log.info("{}", backoffice_no);

		Map<String, String> map = new HashMap<String, String>();

//		int result = service.backoffice_updateOK_sales(backoffice_no, room_no, payment_no);
//		log.info( "integerereretrer", Integer.toString(result));
//		if (result == 1) {
//			log.info("successed...");
//			map.put("result", "1");
//		}
//
//		else {
//			log.info("failed...");
//			map.put("result", "0");
//		}
		
		String json = gson.toJson(map);

		return json;
	}

	/**
	 * 환경설정 페이지 출력
	 */
	@ApiOperation(value="환경설정", notes="대쉬보드 환경설정 페이지")
	@GetMapping("/settings")
	public String backoffice_settings(BackOfficeVO bvo, Model model) {
		log.info("backoffice_settings()...");
//		BackOfficeVO bvo2 = service.backoffice_setting_selectOne(bvo);
//		log.info("result: {}.", bvo2);
//
//		model.addAttribute("vo", bvo2);

		model.addAttribute("content", "thymeleaf/html/backoffice/dashboard/settings");
		model.addAttribute("title", "정산 관리");

		return "thymeleaf/layouts/backoffice/layout_dashboard";
	}

	/**
	 * 환경설정에서 비밀번호 수정 --> jsonObject 
	 */
	@ApiOperation(value="비밀번호 변경", notes="대쉬보드 환경설정 페이지 - 비밀번호 변경")
	@GetMapping("/update_pw")
	@ResponseBody
	public String backoffice_update_pw(BackOfficeVO bvo) {
		log.info("backoffice_update_pw ()...");
		log.info("{}", bvo);
		
		Map<String, String> map = new HashMap<String, String>();

		// 비밀번호 일치 여부 확인
//		BackOfficeVO bvo2 = service.backoffice_select_pw(bvo);
//
//		if (bvo2 != null) {
//			log.info("successed...");
//			map.put("result", "1");
//		}
//
//		else {
//			log.info("failed...");
//			map.put("result", "0");
//		}
		
		String json = gson.toJson(map);

		return json;
	}

	/**
	 * 환경설정에서 업체 탈퇴 요청
	 */
	@ApiOperation(value="업체 탈퇴 요청", notes="대쉬보드 환경설정 페이지 - 업체 탈퇴 요청")
	@PostMapping("/setting_delete")
	@ResponseBody
	public String backoffice_setting_delete(BackOfficeVO bvo) {
		log.info("backoffice_setting_delete ()...");
		log.info("{}", bvo);

		Map<String, String> map = new HashMap<String, String>();

//		int result = service.backoffice_setting_delete(bvo);
//
//		if (result == 1) {
//			log.info("successed...");
//			map.put("result", "1");
//		}
//
//		else {
//			log.info("failed...");
//			map.put("result", "0"); // 남은 예약이 있을 시
//		}
		
		String json = gson.toJson(map);

		return json;
	}
}
