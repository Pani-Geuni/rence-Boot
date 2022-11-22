/**
 * 
 * @author 최진실
 *
 */
package com.rence.dashboard.controller;

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
import com.rence.dashboard.model.ScheduleListView;
import com.rence.dashboard.model.reservationView;
import com.rence.dashboard.repository.ScheduleListRepository;
import com.rence.dashboard.service.DashboardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@Api(tags="대쉬보드 컨트롤러")
@RequestMapping("/backoffice")
public class DashBoardController {
	
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	@Autowired
	DashboardService service;
	
	@Autowired
	BackOfficeFileService fileService;
	
	@Autowired
	OperatingTime operatingTime;


	/**
	 * 대쉬보드 메인
	 */
	@ApiOperation(value="대쉬보드 메인", notes="대쉬보드 메인 페이지")
	@GetMapping("/main")
	public String dashboard_main(Model model, String backoffice_no) {

		List<ReserveSummaryView> rvos = service.reserve_summary_selectAll(backoffice_no);
		log.info("dashboard main rvos : {}",rvos);
		List<CommentSummaryView> cvos = service.comment_summary_selectAll(backoffice_no);
		log.info("dashboard main cvos : {}",cvos);
		SalesSettlementSummaryView svo = service.payment_summary_selectOne(backoffice_no);
		log.info("dashboard main svo : {}",svo);
		RoomSummaryView rmvo = service.room_summary_selectOne(backoffice_no);
		log.info("dashboard main rmvo : {}",rmvo);

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
	@ApiOperation(value="공간 리스트", notes="대쉬보드 공간 관리 페이지")
	@GetMapping("/room")
	public String dashboard_room_list(Model model, String backoffice_no) {
		List<RoomVO> rmvos = service.dashboard_room_list(backoffice_no);
		log.info("rmvos{}",rmvos);
		model.addAttribute("rm_vos", rmvos);

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
		log.info("backoffice_no : {}", backoffice_no);

		Map<String, List<String>> map = new HashMap<String,List<String>>();
		
		BackOfficeVO bvo = service.select_one_backoffice_info(backoffice_no);
		log.info("bvo : {}", bvo);
		
		RoomVO rmvo = new RoomVO();
		
		String type = bvo.getBackoffice_type().replace("meeting_room", "meeting_04,meeting_06,meeting_10");
		rmvo.setRoom_type(type);

		List<String> type_list = new ArrayList<String>();
		
		if (rmvo.getRoom_type() != null) {
			String[] type_split = rmvo.getRoom_type().split(",");
			
			for (int i=0; i < type_split.length; i++) {
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
	@ApiOperation(value="공간 추가 처리", notes="대쉬보드 공간 관리 페이지")
	@PostMapping("/insertOK_room")
	@ResponseBody
	
	public String backoffice_insertOK_room(RoomInsertVO rvo,String backoffice_no) {
		log.info("backoffice_insertOK_room ()...");
		log.info("{}", backoffice_no);
		log.info("rvo :::::::::::; {}", rvo);
		

		Map<String, String> map = new HashMap<String,String>();

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
	@ApiOperation(value="공간 수정 팝업", notes="대쉬보드 공간 관리 페이지")
	@GetMapping("/update_room")
	@ResponseBody
	public String backoffice_update_room(String backoffice_no, String room_no) {
		log.info("backoffice_update_room ()...");
		log.info("{}", backoffice_no);

		Map<String, Object> map3 = new HashMap<String,Object>();
		
		
		BackOfficeVO bvo = service.select_one_backoffice_info(backoffice_no);
		RoomVO rmvo = new RoomVO();
		
		String type = bvo.getBackoffice_type().replace("meeting_room", "meeting_04,meeting_06,meeting_10");
		rmvo.setRoom_type(type);
		
		List<String> type_list = new ArrayList<String>();

		if (rmvo.getRoom_type() != null) {
			String[] type_split = rmvo.getRoom_type().split(",");
			
			for (int i=0; i < type_split.length; i++) {
				type_list.add(type_split[i]);
			}
			
		} else {
			type_list.add("타입 없음");
		}
		
		rmvo = service.select_one_room_info(backoffice_no, room_no);
		log.info("rmvo : {}",rmvo);
		
		map3.put("rmvo", rmvo); 

		map3.put("room_type", type_list);
		
		String json = gson.toJson(map3);
		
		log.info("maaaaaaaaaaaaaaaaaaaap{}",map3);

		return json;
	}
	
	/**
	 * 공간 수정
	 */
	@ApiOperation(value="공간 수정 처리", notes="대쉬보드 공간 관리 페이지")
	@PostMapping("/updateOK_room")
	@ResponseBody
	public String backoffice_updateOK_room(RoomInsertVO rvo,String backoffice_no) {
		log.info("backoffice_updateOK_room ()...");
		log.info("{}", backoffice_no);

		Map<String, String> map = new HashMap<String,String>();

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
	@ApiOperation(value="공간 삭제", notes="대쉬보드 공간 관리 페이지")
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
			result=0;
		}

		if (result == 1) {
			log.info("successed...");
			map.put("result", "1");
		}

		else {
			log.info("failed...");
			map.put("result", "0");  //남은 예약이 있을 시
		}

		String json = gson.toJson(map);
		
		return json;
	}

	/**
	 * 문의(리스트) --> 프론트 id 에러
	 */
	@ApiOperation(value="문의 리스트", notes="대쉬보드 공간 관리 페이지 - 문의")
	@GetMapping("/qna")
	public String dashboard_qna(Model model, String backoffice_no, @RequestParam(value = "page", defaultValue = "1") Integer page) {
		log.info("backoffice_qna ()...");
		log.info("{}", backoffice_no);
		List<CommentListQView> qvos = service.backoffice_qna_selectAll(backoffice_no,page);
		log.info("qvos : {}",qvos);
		model.addAttribute("q_vos", qvos);
		model.addAttribute("cnt", qvos.size());
		
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
		
		CommentVO cvo2 = service.backoffice_insert_comment(backoffice_no,room_no,comment_no);
		
		map.put("cvo", cvo2);
		
		String json = gson.toJson(map);

		return json;
	}

	/**
	 * 답변 작성  -> backoffice_no 받아오는지 확인 해야함
	 */
	@ApiOperation(value="답변 작성 처리", notes="대쉬보드 공간 관리 페이지 - 문의(답변)")
	@PostMapping("/insertOK_comment")
	@ResponseBody
	public String backoffice_insertOK_comment(String backoffice_no, CommentInsertVO cvo, String comment_no) {
		log.info("backoffice_insertOK_comment_controller ()...");
		log.info("backoffice_no : {}", backoffice_no);
		log.info("cvo : {}",cvo);

		Map<String, String> map = new HashMap<String, String>();
		
		cvo.setMother_no(comment_no);
		cvo.setBackoffice_no(backoffice_no);
		cvo.setHost_no(backoffice_no);
		cvo.setWriter("관리자");
		cvo.setComment_state("T");
		cvo.setComment_date(new Date());
		log.info("cvo : {}",cvo);

		int result = service.backoffice_insertOK_comment(cvo);

		if (result == 1) {
			// 부모 no state T 변경
			service.update_comment_state_T(backoffice_no,comment_no);
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
	@ApiOperation(value="답변 삭제", notes="대쉬보드 공간 관리 페이지 - 문의(답변)")
	@PostMapping("/deleteOK_comment")
	@ResponseBody
	public String backoffice_deleteOK_comment(String backoffice_no, String comment_no, String mother_no) {
		log.info("backoffice_deleteOK_comment ()...");
		log.info("{}", backoffice_no);
		log.info("{}", comment_no);
		
		Map<String, String> map = new HashMap<String, String>();
		
		int result = service.backoffice_deleteOK_comment(backoffice_no, comment_no);
		
		if (result == 1) {
		 //부모 no state F 변경
			service.update_comment_state_F(backoffice_no,mother_no);
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
	 * 리뷰 (리스트) -- 프론트 에러
	 */
	@ApiOperation(value="리뷰 리스트", notes="대쉬보드 공간 관리 페이지 - 리뷰")
	@GetMapping("/review")
	public String dashboard_review(Model model, String backoffice_no, @RequestParam(value = "page", defaultValue = "1") Integer page) {
		log.info("backoffice_review ()...");
		log.info("{}", backoffice_no);
		List<ReviewListView> rvvos = service.backoffice_review_selectAll(backoffice_no,page);
		log.info("rvvos : {}", rvvos);
		model.addAttribute("rv_vos", rvvos);
		model.addAttribute("cnt", rvvos.size());
		
		model.addAttribute("content", "thymeleaf/html/backoffice/dashboard/review_list");
		model.addAttribute("title", "공간 관리");

		return "thymeleaf/layouts/backoffice/layout_dashboard";
	}

	/**
	 * 예약 관리(리스트) -- 프론트 에러
	 */
	@ApiOperation(value="예약 리스트", notes="대쉬보드 예약 관리 페이지 - 리스트")
	@GetMapping("/reserve")
	public String dashboard_reserve(Model model, String backoffice_no, String reserve_state, @RequestParam(value = "page", defaultValue = "1") Integer page) {
		log.info("backoffice_reserve ()...");
		log.info("{}", backoffice_no);
		List<ReserveListView> rvos = service.backoffice_reserve_selectAll(backoffice_no, reserve_state, page);
		model.addAttribute("r_vos", rvos);
		model.addAttribute("cnt", rvos.size());
		model.addAttribute("reserve_state", reserve_state);
		
		model.addAttribute("content", "thymeleaf/html/backoffice/dashboard/reserve_list");
		model.addAttribute("title", "예약 관리");

		return "thymeleaf/layouts/backoffice/layout_dashboard";
	}

	/**
	 * 예약 관리(리스트-검색) -- 프론트 에러
	 */
	@ApiOperation(value="예약 리스트 검색", notes="대쉬보드 예약 관리 페이지 - 리스트 검색")
	@GetMapping("/search_reserve")
	public String dashboard_reserve_search(Model model, String backoffice_no, String searchword, String reserve_state, @RequestParam(value = "page", defaultValue = "1") Integer page) {
		log.info("backoffice_search_reserve ()...");
		log.info("{}", backoffice_no);
		List<ReserveListView> rvos = service.backoffice_search_reserve(backoffice_no, searchword, reserve_state,page);
		model.addAttribute("r_vos", rvos);
		model.addAttribute("cnt", rvos.size());
		model.addAttribute("reserve_state", reserve_state);
		
		model.addAttribute("content", "thymeleaf/html/backoffice/dashboard/reserve_list");
		model.addAttribute("title", "예약 관리");

		return "thymeleaf/layouts/backoffice/layout_dashboard";
	}

	/**
	 * 정산 관리(리스트) -- 프론트 에러
	 */
	@ApiOperation(value="정산 관리 리스트", notes="대쉬보드 정산 관리 페이지 - 리스트")
	@GetMapping("/day_sales")
	public String dashboard_sales_day(Model model, String backoffice_no, String sales_date) {
		log.info("backoffice_day_sales()...");
		log.info("{}", backoffice_no);
		
		SalesSettlementDetailView svo = service.backoffice_sales_selectOne(backoffice_no, sales_date);
		model.addAttribute("svo", svo);
		model.addAttribute("sales_date",sales_date);
		log.info("svo:::{}",svo);
		
		List<SalesSettlementViewVO> svos = service.backoffice_sales_selectAll(backoffice_no);
		model.addAttribute("s_vos", svos);
		model.addAttribute("cnt", svos.size());
		log.info("svos:::{}",svos);
		
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

		int result = service.backoffice_updateOK_sales(backoffice_no, room_no, payment_no);
		log.info( "integerereretrer", Integer.toString(result));
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
	@ApiOperation(value="환경설정", notes="대쉬보드 환경설정 페이지")
	@GetMapping("/settings")
	public String backoffice_settings(BackOfficeVO bvo, Model model) {
		log.info("backoffice_settings()...");
		BackOfficeVO bvo2 = service.backoffice_setting_selectOne(bvo);
		log.info("result: {}.", bvo2);
		
		OptionEngToKorMap optionEngToKorMap = new OptionEngToKorMap();
		
		List<String> backoffice_option = optionEngToKorMap.splitOption(bvo2.getBackoffice_option());
		List<String> backoffice_around = optionEngToKorMap.splitAroundOption(bvo2.getBackoffice_around());
		
		
		model.addAttribute("backoffice_option", backoffice_option);
		model.addAttribute("backoffice_around", backoffice_around);
		model.addAttribute("vo", bvo2);

		model.addAttribute("content", "thymeleaf/html/backoffice/dashboard/settings");
		model.addAttribute("title", "환경설정");

		return "thymeleaf/layouts/backoffice/layout_dashboard";
	}

	/**
	 * 환경설정에서 비밀번호 수정 --> 에러 - 암호화 결과값 다름 
	 */
	@ApiOperation(value="비밀번호 변경", notes="대쉬보드 환경설정 페이지 - 비밀번호 변경")
	@GetMapping("/update_pw")
	@ResponseBody
	public String backoffice_update_pw(BackOfficeVO bvo) {
		log.info("backoffice_update_pw ()...");
		log.info("{}", bvo);
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
//		bvo.setBackoffice_pw(new BCryptPasswordEncoder().encode(bvo.getBackoffice_pw()));
//		log.info("{}", bvo);
		
		
		Map<String, String> map = new HashMap<String, String>();

		// 비밀번호 일치 여부 확인
		BackOfficeVO bvo2 = service.backoffice_select_pw(bvo);
		log.info("bvo2:::{}", bvo2);
		
		boolean result = encoder.matches(bvo.getBackoffice_pw(), bvo2.getBackoffice_pw());
		log.info("(비밀번호 확인부분)res: {}",result);
		
		if (result == true) {
//		if (bvo2 != null) {
			log.info("successed...");
			map.put("result", "1");
		}else {
			log.info("failed...");
			map.put("result", "0");
		}
		
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
	@ApiOperation(value="업체 정보 변경 폼", notes="대쉬보드 환경설정 페이지 - 업체 정보 변경")
	@GetMapping("/update_host")
	public String backoffice_update_host(BackOfficeVO bvo, Model model) {
		log.info("backoffice_update_host ()...");
		log.info("{}", bvo);
		
		BackOfficeVO bvo2 = service.backoffice_setting_selectOne(bvo);
		log.info("result: {}.", bvo2);
		
		OptionEngToKorMap optionEngToKorMap = new OptionEngToKorMap();

		model.addAttribute("vo", bvo2);
		
		model.addAttribute("content", "thymeleaf/html/backoffice/dashboard/update_host");
		model.addAttribute("title", "업체 정보 변경");

		return "thymeleaf/layouts/backoffice/layout_dashboard";
	}
	
	/**
	 * 환경설정에서 정보 변경
	 */
	@ApiOperation(value="업체 정보 변경 처리", notes="대쉬보드 환경설정 페이지 - 업체 정보 변경")
	@PostMapping("/updateOK_host")
	public String backoffice_updateOK_host(BackOfficeVO bvo,BackOfficeOperatingTimeVO ovo,MultipartHttpServletRequest mtfRequest, @RequestParam(value = "multipartFile_room") MultipartFile multipartFile_room,  Model model) {
		log.info("backoffice_updateOK_host ()...");
		log.info("{}", bvo);
		
		// 이미지 파일
		bvo = fileService.backoffice_image_upload(bvo, mtfRequest, multipartFile_room);
		log.info("filupload room:{}", bvo);

		// 운영시간
		BackOfficeOperatingTimeVO_datetype ovo2 = new BackOfficeOperatingTimeVO_datetype();
		ovo2 = operatingTime.operatingTime(ovo, ovo2);
		
		// 백오피스 업체 정보 업데이트
//		int update_host =  service.backoffice_updateOK_host(bvo);
		service.backoffice_updateOK_host(bvo);
		
		// 백오피스 운영 시간 업데이트
		ovo2.setBackoffice_no(bvo.getBackoffice_no());
//		int update_opt =  service.backoffice_updateOK_opt(ovo2);
		service.backoffice_updateOK_opt(ovo2);
	
		String rt = "";
//		if(update_host==1&&update_opt==1) {
			rt = "redirect:backoffice/settings";
//		}else {
//			rt = "redirect:backoffice/update_host";
//		}
		
		
		return rt;
	}
	
	
	/**
	 * 일정 관리 페이지 
	 */
	@ApiOperation(value="일정 관리", notes="대쉬보드 - 일정 관리")
	@GetMapping("/schedule")
	public String backoffice_schedule(String backoffice_no, Model model) { // backoffice_no를 받아야 하나..?
		log.info("backoffice_schedule controller()...");
		
		model.addAttribute("backoffic_no",backoffice_no);
		
		model.addAttribute("content", "thymeleaf/html/backoffice/dashboard/schedule");
		model.addAttribute("title", "일정 관리");

		return "thymeleaf/layouts/backoffice/layout_dashboard";
	}
	
	/**
	 * 일정 관리 - 날짜, 시간 선택 후
	 */
	@ApiOperation(value="일정 관리", notes="대쉬보드 - 일정 관리")
	@GetMapping("/schedule_research")
	@ResponseBody
	public String backoffice_schedule_research(String backoffice_no, String not_sdate, String not_edate, String not_stime, String not_etime, String off_type, Model model) {
		log.info("backoffice_schedule_research controller()...");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<ScheduleListView> sche = service.backoffice_schedule_list(backoffice_no,not_sdate,not_edate,not_stime,not_etime,off_type);
		log.info("result: {}.", sche);
		log.info("cnt: {}.", sche.size());
		
		map.put("sc_vos", sche);
		map.put("cnt", sche.size());
		
		String json = gson.toJson(map);
		
		return json;
	}
	
	/**
	 * 일정 관리 - 날짜, 시간 선택 후, 휴무, 브레이크타임 설정
	 */
	@ApiOperation(value="일정 관리 - 휴무, 브레이크타임 설정", notes="대쉬보드 - 일정 관리")
	@PostMapping("/scheduleOK")
	@ResponseBody
	public String backoffice_scheduleOK(String backoffice_no, String not_sdate, String not_edate, String not_stime, String not_etime, String room_no, String off_type, Model model) {
		log.info("backoffice_schedule_research controller()...");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if (off_type.equals("breaktime")) {
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			not_sdate = (formatter.format(date));
			not_edate = (formatter.format(date));
		}
		log.info("not_sdate : {} ",not_sdate);
		log.info("not_edate : {} ",not_edate);
		
		int result = service.backoffice_schedueOK(backoffice_no,not_sdate,not_edate,not_stime,not_etime,room_no);
		
		if (result==1) {
			log.info("successed...");
			map.put("result", "1");
		}else {
			log.info("falied...");
			map.put("result", "0");
		}
		
		String json = gson.toJson(map);
		
		return json;
	}
	
	/**
	 * 일정 관리 - 해당 날짜, 시간에 예약자 리스트 
	 */
	@ApiOperation(value="예약자 리스트", notes="대쉬보드 - 예약자 리스트")
	@GetMapping("/reservation")
	public String backoffice_reservation(String backoffice_no, String room_no, String not_sdate, String not_edate, String not_stime, String not_etime, String off_type, Model model) { 
		log.info("backoffice_schedule controller()...");
		
		String reserve_stime = (not_sdate+not_stime);
		log.info("reserve_stime : {} ",reserve_stime);
		
		String reserve_etime = (not_edate+not_etime);
		log.info("reserve_etime : {} ",reserve_etime);
		
		List<reservationView> rv_vos = service.backoffice_reservation(backoffice_no,not_sdate,not_edate,not_stime,not_etime,room_no,off_type);
		log.info("result: {}.", rv_vos);
		log.info("cnt: {}.", rv_vos.size());
		
		model.addAttribute("reserve_stime",reserve_stime);
		model.addAttribute("reserve_etime",reserve_etime);
		model.addAttribute("rv_vos",rv_vos);
		model.addAttribute("cnt",rv_vos.size());
		
		model.addAttribute("content", "thymeleaf/html/backoffice/dashboard/schedule");
		model.addAttribute("title", "일정 관리");

		return "thymeleaf/layouts/backoffice/layout_dashboard";
	}
}
