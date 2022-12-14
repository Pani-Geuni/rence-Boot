/**
 * @author 전판근
 */

package com.rence.office.controller;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rence.backoffice.model.BackOfficeVO;
import com.rence.backoffice.service.CustomDateFormatter;
import com.rence.common.OptionEngToKorMap;
import com.rence.office.common.OfficeInfoMap;
import com.rence.office.model.Comment_EntityVO;
import com.rence.office.model.ListViewVO;
import com.rence.office.model.OfficeInfoVO;
import com.rence.office.model.OfficeMileageVO;
import com.rence.office.model.OfficeOperatingTimeVO;
import com.rence.office.model.OfficeOperatingTimeVO_date;
import com.rence.office.model.OfficePaymentVO;
import com.rence.office.model.OfficeQuestionVO;
import com.rence.office.model.OfficeReserveVO;
import com.rence.office.model.OfficeReserveVO_date;
import com.rence.office.model.OfficeReviewVO;
import com.rence.office.model.OfficeRoomVO;
import com.rence.office.model.PaymentInfoVO;
import com.rence.office.model.RoomScheduleVO;
import com.rence.office.service.OfficeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@Api(tags = "오피스 컨트롤러")
@RequestMapping("/office")
public class OfficeController {

	Gson gson = new GsonBuilder().setPrettyPrinting().create();

	@Autowired
	OfficeService service;

	@Autowired
	HttpSession session;

	/*
	 * 오피스(공간) 상세 페이지
	 */
	@ApiOperation(value = "공간 소개 페이지 로드 (데스크,회의실)", notes = "데스크 / 회의실 공간 소개 페이지 로드하는 컨트롤러")
	@GetMapping(value = "/space_introduce")
	public String space_intruduce(BackOfficeVO bvo, String introduce_menu,
			@RequestParam(value = "page", defaultValue = "1") Integer page, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();

		OptionEngToKorMap info_map = new OptionEngToKorMap();
		String backoffice_no = bvo.getBackoffice_no();

		// ******************
		// backoffice 기본 정보
		// ******************
		OfficeInfoVO ovo = service.select_one_office_info(backoffice_no);
		List<String> type_list = new ArrayList<String>();
		List<String> tag_list = new ArrayList<String>();
		List<String> img_list = new ArrayList<String>();
		List<String> option_list = new ArrayList<String>();
		List<String> around_option_list = new ArrayList<String>();

		ovo.setAvg_rating(Math.round(ovo.getAvg_rating() * 10) / 10.0);

		if (ovo.getBackoffice_type() != null) {
			type_list = info_map.splitType(ovo.getBackoffice_type());
		} else {
			type_list.add("타입 없음");
		}

		if (ovo.getBackoffice_tag() != null) {
			tag_list = info_map.splitTag(ovo.getBackoffice_tag());
		} else {
			tag_list.add("태그 없음");
		}

		img_list = info_map.splitImage(ovo.getBackoffice_image());

		if (ovo.getBackoffice_option() != null) {
			option_list = info_map.splitOption(ovo.getBackoffice_option());
		} else {
			option_list.add("옵션 없음");
		}

		if (ovo.getBackoffice_around() != null) {
			around_option_list = info_map.splitAroundOption(ovo.getBackoffice_around());
		} else {
			around_option_list.add("주변 시설 없음");
		}

		String short_roadname_address = info_map.makeShortAddress(ovo.getRoadname_address());

		// ******************
		// backoffice 운영 시간
		// ******************
		OfficeOperatingTimeVO_date dotvo = service.select_one_operating_time(backoffice_no);

		CustomDateFormatter df = new CustomDateFormatter();
		OfficeOperatingTimeVO otvo = df.showStringOfficeOperating(dotvo);

		// ************************
		// backoffice 운영 공간(Room)
		// ************************
		List<OfficeRoomVO> rvos = service.select_all_room(backoffice_no);

		for (OfficeRoomVO vo : rvos) {
			vo.setRoom_type(info_map.changeType(vo.getRoom_type()));
		}

		// **************
		// backoffice 문의
		// **************

		// 페이징 처리 로직
		// 리스트 수
		long total_rowCount_question_all = service.total_rowCount_question_all(backoffice_no);
		log.info("total_rowCount_question_all: {}", total_rowCount_question_all);

		// 총 페이징 되는 수
		long totalPageCnt = (long) Math.ceil(total_rowCount_question_all / 4.0);
		log.info("totalPageCnt: {}", totalPageCnt);

		long nowPage = page;

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

		// 페이징 처리 계산 로직 끝

		List<OfficeQuestionVO> cvos = service.select_all_comment(backoffice_no, page);

		String is_login = (String) session.getAttribute("user_id");

		if (cvos != null) {
			for (OfficeQuestionVO vo : cvos) {

				log.info("is_login :::::::::: {}", is_login);
				log.info("user_no :::::::::: {}", vo.getUser_id());

				OfficeQuestionVO vo2 = service.select_one_answer(vo.getComment_no());
				if (vo2 != null) {
					if (vo.getUser_id().equals(is_login)) {
						vo.setAnswer_content(vo2.getComment_content());
						vo.setAnswer_date(vo2.getComment_date());
						vo.setComment_state("Y");
					} else {
						if (vo.getIs_secret() == null || vo.getIs_secret().equals("F")) {
							vo.setAnswer_content(vo2.getComment_content());
							vo.setAnswer_date(vo2.getComment_date());
							vo.setComment_state("Y");
						} else {
							vo.setAnswer_content(null);
							vo.setAnswer_date(null);
						}
					}
				} else {
					vo.setComment_state("N");
				}

				// 이름 마스킹
				String originName = vo.getUser_name();
				String firstName = originName.substring(0, 1);
				String midName = originName.substring(1, originName.length() - 1);

				String maskingMidName = "";
				for (int i = 0; i < midName.length(); i++) {
					maskingMidName += "*";
				}

				String lastName = originName.substring(originName.length() - 1, originName.length());

				String maskingName = firstName + maskingMidName + lastName;

				vo.setUser_name(maskingName);
			}
		}

		// **************
		// backoffice 후기
		// **************

		// 페이징 처리 로직
		// 리스트 수
		long total_rowCount_review_all = service.total_rowCount_review_all(backoffice_no);

		// 총 페이징 되는 수
		long totalPageCnt2 = (long) Math.ceil(total_rowCount_review_all / 4.0);
		log.info("totalPageCnt: {}", totalPageCnt2);

		long nowPage2 = page;
		log.info("nowPage2: {}", nowPage2);

		long maxPage2 = 0;
		log.info("maxPage2 : {}", maxPage2);

		if (nowPage2 % 5 != 0) {
			if (nowPage2 == totalPageCnt2) {
				maxPage2 = nowPage2;
			} else if (((nowPage2 / 5) + 1) * 5 >= totalPageCnt2) {
				maxPage2 = totalPageCnt2;
			} else if (((nowPage2 / 5) + 1) * 5 < totalPageCnt2) {
				maxPage2 = ((nowPage2 / 5) + 1) * 5;
			}
		} else if (nowPage2 % 5 == 0) {
			if (nowPage2 <= totalPageCnt2) {
				maxPage2 = nowPage2;
			}
		}

		log.info("maxPage2 : {}", maxPage2);

		map.put("totalPageCnt2", totalPageCnt2);
		map.put("nowPage2", nowPage2);
		map.put("maxPage2", maxPage2);
		map.put("page", "space_introduce_detail");

		// 페이징 처리 계산 로직 끝

		List<OfficeReviewVO> revos = service.select_all_review(backoffice_no, page);

		for (OfficeReviewVO vo : revos) {

			// 이름 마스킹
			String originName = vo.getUser_name();
			String firstName = originName.substring(0, 1);
			String midName = originName.substring(1, originName.length() - 1);

			String maskingMidName = "";
			for (int i = 0; i < midName.length(); i++) {
				maskingMidName += "*";
			}

			String lastName = originName.substring(originName.length() - 1, originName.length());

			String maskingName = firstName + maskingMidName + lastName;

			vo.setUser_name(maskingName);
		}

		// backoffice 기본 정보
		model.addAttribute("res", map);
		model.addAttribute("ovo", ovo);
		model.addAttribute("short_roadname_address", short_roadname_address);
		model.addAttribute("type_list", type_list);
		model.addAttribute("tag_list", tag_list);
		model.addAttribute("img_list", img_list);
		model.addAttribute("option_list", option_list);
		model.addAttribute("around_option_list", around_option_list);

		model.addAttribute("introduce_menu", introduce_menu);

		// backoffice 운영 시간
		model.addAttribute("otvo", otvo);

		// backoffice 운영 공간
		model.addAttribute("rvos", rvos);

		// backoffice 문의
		model.addAttribute("is_login", is_login);
		model.addAttribute("cvos", cvos);
		model.addAttribute("cvos_cnt", total_rowCount_question_all);

		// backoffice 후기
		model.addAttribute("revos", revos);
		model.addAttribute("review_cnt", total_rowCount_review_all);

		model.addAttribute("page", "space_detail");
		model.addAttribute("content", "thymeleaf/html/office/space_detail/space_detail_introduce");
		model.addAttribute("title", "공간 상세 페이지");

		return "thymeleaf/layouts/office/layout_base";
	}

	@GetMapping("/introduce_q_paging")
	@ResponseBody
	public String introduce_q_paging(BackOfficeVO bvo, @RequestParam(value = "page", defaultValue = "1") Integer page,
			Model model) {

		// **************
		// backoffice 문의 페이징
		// **************
		String backoffice_no = bvo.getBackoffice_no();

		// 페이징 처리 로직
		// 리스트 수
		long total_rowCount_question_all = service.total_rowCount_question_all(backoffice_no);
		log.info("total_rowCount_question_all: {}", total_rowCount_question_all);

		// 총 페이징 되는 수
		long totalPageCnt = (long) Math.ceil(total_rowCount_question_all / 4.0);
		log.info("totalPageCnt: {}", totalPageCnt);

		long nowPage = page;

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

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalPageCnt", totalPageCnt);
		map.put("nowPage", nowPage);
		map.put("maxPage", maxPage);

		// 페이징 처리 계산 로직 끝

		List<OfficeQuestionVO> cvos = service.select_all_comment(backoffice_no, page);

		String is_login = (String) session.getAttribute("user_id");

		if (cvos != null) {
			for (OfficeQuestionVO vo : cvos) {

				log.info("is_login :::::::::: {}", is_login);
				log.info("user_no :::::::::: {}", vo.getUser_id());

				OfficeQuestionVO vo2 = service.select_one_answer(vo.getComment_no());
				if (vo2 != null) {
					if (vo.getUser_id().equals(is_login)) {
						vo.setAnswer_content(vo2.getComment_content());
						vo.setAnswer_date(vo2.getComment_date());
						vo.setComment_state("Y");
					} else {
						if (vo.getIs_secret() == null || vo.getIs_secret().equals("F")) {
							vo.setAnswer_content(vo2.getComment_content());
							vo.setAnswer_date(vo2.getComment_date());
							vo.setComment_state("Y");
						} else {
							vo.setAnswer_content(null);
							vo.setAnswer_date(null);
						}
					}
					
				} else {
					vo.setComment_state("N");
				}

				// 이름 마스킹
				String originName = vo.getUser_name();
				String firstName = originName.substring(0, 1);
				String midName = originName.substring(1, originName.length() - 1);

				String maskingMidName = "";
				for (int i = 0; i < midName.length(); i++) {
					maskingMidName += "*";
				}

				String lastName = originName.substring(originName.length() - 1, originName.length());

				String maskingName = firstName + maskingMidName + lastName;

				vo.setUser_name(maskingName);
			}
		}

		map.put("cvos", cvos);
		String introduce_q_paging = gson.toJson(map);

		return introduce_q_paging;
	}

	@GetMapping(value = "/introduce_r_paging")
	@ResponseBody
	public String introduce_r_paging(BackOfficeVO bvo, @RequestParam(value = "page", defaultValue = "1") Integer page,
			Model model) {
		// **************
		// backoffice 후기
		// **************

		String backoffice_no = bvo.getBackoffice_no();
		Map<String, Object> map = new HashMap<String, Object>();

		// 페이징 처리 로직
		// 리스트 수
		long total_rowCount_review_all2 = service.total_rowCount_review_all(backoffice_no);

		// 총 페이징 되는 수
		long totalPageCnt2 = (long) Math.ceil(total_rowCount_review_all2 / 4.0);
		log.info("totalPageCnt: {}", totalPageCnt2);

		long nowPage2 = page;
		log.info("nowPage2: {}", nowPage2);

		long maxPage2 = 0;
		log.info("maxPage2 : {}", maxPage2);

		if (nowPage2 % 5 != 0) {
			if (nowPage2 == totalPageCnt2) {
				maxPage2 = nowPage2;
			} else if (((nowPage2 / 5) + 1) * 5 >= totalPageCnt2) {
				maxPage2 = totalPageCnt2;
			} else if (((nowPage2 / 5) + 1) * 5 < totalPageCnt2) {
				maxPage2 = ((nowPage2 / 5) + 1) * 5;
			}
		} else if (nowPage2 % 5 == 0) {
			if (nowPage2 <= totalPageCnt2) {
				maxPage2 = nowPage2;
			}
		}

		log.info("maxPage2 : {}", maxPage2);

		map.put("totalPageCnt2", totalPageCnt2);
		map.put("nowPage2", nowPage2);
		map.put("maxPage2", maxPage2);
		map.put("page", "space_introduce_detail");

		// 페이징 처리 계산 로직 끝

		List<OfficeReviewVO> revos = service.select_all_review(backoffice_no, page);

		for (OfficeReviewVO vo : revos) {

			// 이름 마스킹
			String originName = vo.getUser_name();
			String firstName = originName.substring(0, 1);
			String midName = originName.substring(1, originName.length() - 1);

			String maskingMidName = "";
			for (int i = 0; i < midName.length(); i++) {
				maskingMidName += "*";
			}

			String lastName = originName.substring(originName.length() - 1, originName.length());

			String maskingName = firstName + maskingMidName + lastName;

			vo.setUser_name(maskingName);
		}

		map.put("revos", revos);
		String introduce_r_paging = gson.toJson(map);

		return introduce_r_paging;
	}

	// ***************
	// 예약 가능 여부 확인
	// ***************
	@SuppressWarnings("unlikely-arg-type")
	@ApiOperation(value = "예약 가능 확인 여부 (데스크,회의실)", notes = "선택한 공간과 시간에 예약이 존재하는지 확인하는 컨트롤러")
	@GetMapping(value = "/reserve_check")
	@ResponseBody
	public String reserve_check_rsu(String backoffice_no, String room_no, String reserve_date, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();

		List<OfficeReserveVO> vos = service.check_reserve(backoffice_no, room_no, reserve_date);

		List<RoomScheduleVO> rvos = service.select_all_room_schedule(backoffice_no, room_no, reserve_date);
		
		List<RoomScheduleVO> dvos = service.select_all_room_schedule_dayoff(backoffice_no, room_no);
		
		
		List<Integer> already_reserve_list = new ArrayList<>();

		// 예약 시간 설정
		for (OfficeReserveVO vo : vos) {
			int stime = Integer.parseInt(vo.getReserve_stime());
			int etime = Integer.parseInt(vo.getReserve_etime());

			for (int i = stime; i < etime; i++) {
				if (!Arrays.asList(already_reserve_list).contains(i)) {
					already_reserve_list.add(i);
				}
			}
		}

		// 브레이크 타임 시간 설정
		for (RoomScheduleVO vo : rvos) {
			String not_stime = vo.getNot_stime();
			String not_etime = vo.getNot_etime();
			
			String[] split_stime = not_stime.split(" ");
			String[] split_etime = not_etime.split(" ");
			
			int stime = 0;
			int etime = 0;
			
			if (split_stime[0].equals(split_etime[0])) {
				String stime_hour = split_stime[1].split(":")[0];
				String etime_hour = split_etime[1].split(":")[0];
				
				stime = Integer.parseInt(stime_hour);
				etime = Integer.parseInt(etime_hour);
			}
			
			
			for (int i = stime; i < etime; i++) {
				if (!Arrays.asList(already_reserve_list).contains(i)) {
					already_reserve_list.add(i);
				}
			}
		}
		

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		
		// 휴무 시간 설정
		for (RoomScheduleVO vo : dvos) {
			String not_sdate = vo.getNot_stime().split(" ")[0].replace("-", "/");
			String not_edate = vo.getNot_etime().split(" ")[0].replace("-", "/");
			
			if (!not_sdate.equals(not_edate)) {
				LocalDate sdate = LocalDate.parse(not_sdate, formatter);
				LocalDate edate = LocalDate.parse(not_edate, formatter);
				
				List<LocalDate> temp_date_list = getDatesBetweenTwoDates(sdate, edate);
				
				for (LocalDate d : temp_date_list) {
					
					if (d.format(formatter).equals(reserve_date)) {
						for (int i = 0; i < 23; i++) {
							if (!Arrays.asList(already_reserve_list).contains(i)) {
								already_reserve_list.add(i);
							}
						}
					}
				}
				
			}
			
		}
		
		map.put("reserve_list", already_reserve_list);

		String json = gson.toJson(map);

		return json;
	}
	
	public static List<LocalDate> getDatesBetweenTwoDates(LocalDate startDate, LocalDate endDate) {
		int numOfDaysBetween = (int) ChronoUnit.DAYS.between(startDate, endDate) + 1;
		return IntStream.iterate(0, i -> i + 1)
        	.limit(numOfDaysBetween)
        	.mapToObj(i -> startDate.plusDays(i))
		.collect(Collectors.toList());
	}

	@SuppressWarnings("unlikely-arg-type")
	@ApiOperation(value = "예약 가능 확인 여부 (오피스)", notes = "선택한 공간과 시간에 예약이 존재하는지 확인하는 컨트롤러")
	@GetMapping(value = "/office_reserve_check")
	@ResponseBody
	public String office_reserve_check_rsu(String backoffice_no, String room_no, String reserve_stime, String reserve_etime,
			Model model) throws ParseException {

		log.info("{} {} {} {}", backoffice_no, room_no, reserve_stime, reserve_etime);

		Map<String, Object> map = new HashMap<String, Object>();

		List<OfficeReserveVO> vos = service.check_reserve_office(backoffice_no, room_no);

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

		log.info("office reserve vos : {}", vos);
		Date reserve_sdate = formatter.parse(reserve_stime);
		Date reserve_edate = formatter.parse(reserve_etime);

		int reserve_flag = 0;

		for (OfficeReserveVO vo : vos) {
			log.info("first vo : {}", vo);
			vo.setReserve_sdate(vo.getReserve_sdate().split(" ")[0]);
			vo.setReserve_sdate(vo.getReserve_sdate().replace("-", "/"));
			vo.setReserve_edate(vo.getReserve_edate().split(" ")[0]);
			vo.setReserve_edate(vo.getReserve_edate().replace("-", "/"));

			Date sdate = formatter.parse(vo.getReserve_sdate());
			Date edate = formatter.parse(vo.getReserve_edate());

			int compareSdate1 = reserve_sdate.compareTo(sdate);
			int compareEdate1 = reserve_edate.compareTo(sdate);
			int compareSdate2 = reserve_sdate.compareTo(edate);
			int compareEdate2 = reserve_edate.compareTo(edate);

			log.info("compare : {} {} {}", reserve_sdate, sdate, edate);
			log.info("compare result : {} {}", compareSdate1, compareEdate1);
			log.info("compare result : {} {}", compareSdate2, compareEdate2);

			if ((compareSdate1 < 0 && compareEdate1 < 0) || (compareSdate2 > 0 && compareEdate2 > 0)) {
				reserve_flag = 1;
			} else {
				reserve_flag = 0;
				break;
			}
		}

		// 예약이 하나도 없을 때
		if (vos.isEmpty()) {
			reserve_flag = 1;
		}

		if (reserve_flag == 1) {
			map.put("result", "1");
		} else {
			map.put("result", "0");
		}

		String json = gson.toJson(map);

		return json;
	}

	// *******
	// 예약 하기
	// *******
	@ApiOperation(value = "예약 (데스크,회의실)", notes = "예약을 신청한 뒤, 결과값에 따라 결제 페이지로 이동")
	@GetMapping(value = "/reserve")
	@ResponseBody
	public String reserve_rsu(OfficeReserveVO rvo) throws ParseException {
		Map<String, Object> map = new HashMap<String, Object>();

		OfficeReserveVO_date date_vo = new OfficeReserveVO_date();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date reserve_stime = formatter.parse(rvo.getReserve_stime());
		Date reserve_etime = formatter.parse(rvo.getReserve_etime());

		date_vo.setBackoffice_no(rvo.getBackoffice_no());
		date_vo.setRoom_no(rvo.getRoom_no());
		date_vo.setRoom_type(rvo.getRoom_type());
		date_vo.setUser_no(rvo.getUser_no());
		date_vo.setReserve_stime(reserve_stime);
		date_vo.setReserve_etime(reserve_etime);
		date_vo.setReserve_sdate(reserve_stime);
		date_vo.setReserve_edate(reserve_etime);

		int result = service.confirm_reserve(date_vo);
		log.info("controller /backoffice/reserve result :: {}", result);

		if (result == 1) {
			String reserve_no = service.select_one_last_reserve(rvo.getUser_no());

			map.put("result", "1");
			map.put("reserve_no", reserve_no);
		} else {
			map.put("result", "0");
		}

		String json = gson.toJson(map);

		return json;
	}

	// **********************
	// space introduce OFFICE
	// **********************
	@ApiOperation(value = "공간 소개 페이지 로드 (오피스)", notes = "오피스 공간 소개 페이지 로드하는 컨트롤러")
	@GetMapping(value = "/space_introduce_office")
	public String space_intruduce_office(BackOfficeVO bvo, String introduce_menu,
			@RequestParam(value = "page", defaultValue = "1") Integer page, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();

		OfficeInfoMap info_map = new OfficeInfoMap();

		String backoffice_no = bvo.getBackoffice_no();

		// ******************
		// backoffice 기본 정보
		// ******************
		OfficeInfoVO ovo = service.select_one_office_info(backoffice_no);
		List<String> type_list = new ArrayList<String>();
		List<String> tag_list = new ArrayList<String>();
		List<String> img_list = new ArrayList<String>();
		List<String> option_list = new ArrayList<String>();
		List<String> around_option_list = new ArrayList<String>();

		if (ovo.getBackoffice_type() != null) {
			type_list = info_map.splitType(ovo.getBackoffice_type());
		} else {
			type_list.add("타입 없음");
		}

		if (ovo.getBackoffice_tag() != null) {
			tag_list = info_map.splitTag(ovo.getBackoffice_tag());
		} else {
			tag_list.add("태그 없음");
		}

		img_list = info_map.splitImage(ovo.getBackoffice_image());

		if (ovo.getBackoffice_option() != null) {
			option_list = info_map.splitOption(ovo.getBackoffice_option());
		} else {
			option_list.add("옵션 없음");
		}

		if (ovo.getBackoffice_around() != null) {
			around_option_list = info_map.splitAroundOption(ovo.getBackoffice_around());
		} else {
			around_option_list.add("주변 시설 없음");
		}

		String short_roadname_address = info_map.makeShortAddress(ovo.getRoadname_address());

		// ******************
		// backoffice 운영 시간
		// ******************
		OfficeOperatingTimeVO_date dotvo = service.select_one_operating_time(backoffice_no);

		CustomDateFormatter df = new CustomDateFormatter();
		OfficeOperatingTimeVO otvo = df.showStringOfficeOperating(dotvo);

		// ************************
		// backoffice 운영 공간(Room)
		// ************************
		List<OfficeRoomVO> rvos = service.select_all_room(backoffice_no);

		for (OfficeRoomVO vo : rvos) {
			vo.setRoom_type(info_map.changeType(vo.getRoom_type()));
		}

		// **************
		// backoffice 문의
		// **************

		// 페이징 처리 로직
		// 리스트 수
		long total_rowCount_question_all = service.total_rowCount_question_all(backoffice_no);
		log.info("total_rowCount_question_all: {}", total_rowCount_question_all);

		// 총 페이징 되는 수
		long totalPageCnt = (long) Math.ceil(total_rowCount_question_all / 4.0);
		log.info("totalPageCnt: {}", totalPageCnt);

		long nowPage = page;

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

		// 페이징 처리 계산 로직 끝

		List<OfficeQuestionVO> cvos = service.select_all_comment(backoffice_no, page);

		String is_login = (String) session.getAttribute("user_id");

		if (cvos != null) {
			for (OfficeQuestionVO vo : cvos) {

				log.info("is_login :::::::::: {}", is_login);
				log.info("user_no :::::::::: {}", vo.getUser_id());

				OfficeQuestionVO vo2 = service.select_one_answer(vo.getComment_no());
				if (vo2 != null) {
					if (vo.getUser_id().equals(is_login)) {
						vo.setAnswer_content(vo2.getComment_content());
						vo.setAnswer_date(vo2.getComment_date());
						vo.setComment_state("Y");
					} else {
						if (vo.getIs_secret() == null || vo.getIs_secret().equals("F")) {
							vo.setAnswer_content(vo2.getComment_content());
							vo.setAnswer_date(vo2.getComment_date());
							vo.setComment_state("Y");
						} else {
							vo.setAnswer_content(null);
							vo.setAnswer_date(null);
						}
					}
				} else {
					vo.setComment_state("N");
				}

				// 이름 마스킹
				String originName = vo.getUser_name();
				String firstName = originName.substring(0, 1);
				String midName = originName.substring(1, originName.length() - 1);

				String maskingMidName = "";
				for (int i = 0; i < midName.length(); i++) {
					maskingMidName += "*";
				}

				String lastName = originName.substring(originName.length() - 1, originName.length());

				String maskingName = firstName + maskingMidName + lastName;

				vo.setUser_name(maskingName);
			}
		}

		// **************
		// backoffice 후기
		// **************

		// 페이징 처리 로직
		// 리스트 수
		long total_rowCount_review_all = service.total_rowCount_review_all(backoffice_no);

		// 총 페이징 되는 수
		long totalPageCnt2 = (long) Math.ceil(total_rowCount_review_all / 4.0);

		long nowPage2 = page;

		long maxPage2 = 0;

		if (nowPage2 % 5 != 0) {
			if (nowPage2 == totalPageCnt2) {
				maxPage2 = nowPage2;
			} else if (((nowPage2 / 5) + 1) * 5 >= totalPageCnt2) {
				maxPage2 = totalPageCnt2;
			} else if (((nowPage2 / 5) + 1) * 5 < totalPageCnt2) {
				maxPage2 = ((nowPage2 / 5) + 1) * 5;
			}
		} else if (nowPage2 % 5 == 0) {
			if (nowPage2 <= totalPageCnt2) {
				maxPage2 = nowPage2;
			}
		}

		map.put("totalPageCnt2", totalPageCnt2);
		map.put("nowPage2", nowPage2);
		map.put("maxPage2", maxPage2);
		map.put("page", "space_detail_office");
		
		log.info("totalPageCnt2 : {}", totalPageCnt2);
		log.info("nowPage2 : {}", nowPage2);
		log.info("maxPage2 : {}", maxPage2);

		// 페이징 처리 계산 로직 끝

		List<OfficeReviewVO> revos = service.select_all_review(backoffice_no, page);

		for (OfficeReviewVO vo : revos) {

			// 이름 마스킹
			String originName = vo.getUser_name();
			String firstName = originName.substring(0, 1);
			String midName = originName.substring(1, originName.length() - 1);

			String maskingMidName = "";
			for (int i = 0; i < midName.length(); i++) {
				maskingMidName += "*";
			}

			String lastName = originName.substring(originName.length() - 1, originName.length());

			String maskingName = firstName + maskingMidName + lastName;

			vo.setUser_name(maskingName);
		}

		// backoffice 기본 정보
		model.addAttribute("res", map);
		model.addAttribute("ovo", ovo);
		model.addAttribute("type_list", type_list);
		model.addAttribute("tag_list", tag_list);
		model.addAttribute("img_list", img_list);
		model.addAttribute("option_list", option_list);
		model.addAttribute("around_option_list", around_option_list);
		model.addAttribute("short_roadname_address", short_roadname_address);
		model.addAttribute("page", "space_detail_office");

		// backoffice 운영 시간
		model.addAttribute("otvo", otvo);

		// backoffice 운영 공간
		model.addAttribute("rvos", rvos);

		// backoffice 문의
		model.addAttribute("is_login", is_login);
		model.addAttribute("cvos", cvos);
		model.addAttribute("cvos_cnt", total_rowCount_question_all);

		// backoffice 후기
		model.addAttribute("revos", revos);
		model.addAttribute("review_cnt", total_rowCount_review_all);

		model.addAttribute("content", "thymeleaf/html/office/space_detail/space_detail_introduce_office");
		model.addAttribute("title", "공간 상세 페이지");

		return "thymeleaf/layouts/office/layout_base";
	}

	// **********************
	// 공간 결제 페이지
	// **********************
	@ApiOperation(value = "결제 페이지 로드 컨트롤러", notes = "예약 및 결제하는 페이지 데이터 불러오는 컨트롤러")
	@GetMapping(value = "/payment")
	public String space_payment_rsu(OfficeReserveVO rvo, Model model) throws ParseException {

		String reserve_no = rvo.getReserve_no();

		PaymentInfoVO pvo = service.select_one_final_payment_info(reserve_no);
		OfficeInfoMap info_map = new OfficeInfoMap();

		pvo.setRoom_type(info_map.changeType(pvo.getRoom_type()));
		List<String> splitImage = info_map.splitImage(pvo.getBackoffice_image());
		String room_first_image = splitImage.get(0);
		pvo.setBackoffice_image(room_first_image);

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		Date sdate = formatter.parse(pvo.getReserve_stime());
		Date edate = formatter.parse(pvo.getReserve_etime());

		log.info("sdate : {}", sdate);
		log.info("edate : {}", edate);

		// diffTime : type이 desk, meeting 일 때, 시간 차이
		// office 일 때 일수 차이
		long diffTime = 0;
		int payment_all = 0;
		int earned_mileage = 0;

		// 사용자 총 예약 시간
		if (pvo.getRoom_type().equals("오피스")) {
			long diffSec = (edate.getTime() - sdate.getTime()) / 1000;
			// 일 계산
			diffTime = diffSec / (24 * 60 * 60);

			// 개월 계산
			diffTime /= 30;

		} else {
			diffTime = (edate.getTime() - sdate.getTime()) / 3600000;
		}

		// 전체 결제할 금액

		payment_all = (int) diffTime * pvo.getRoom_price();

		earned_mileage = (int) (payment_all * 0.05);
		log.info("diffTime ::::: {}", diffTime);

		model.addAttribute("pvo", pvo);
		model.addAttribute("payment_all", payment_all);
		model.addAttribute("earned_mileage", earned_mileage);

		model.addAttribute("content", "thymeleaf/html/office/reserve/payment_page");
		model.addAttribute("title", "결제 페이지");

		return "thymeleaf/layouts/office/layout_reserve";
	}

	@ApiOperation(value = "결제 컨트롤러", notes = "예약 및 결제하는 페이지 결제 로직 컨트롤러")
	@PostMapping(value = "/paymentOK")
	@ResponseBody
	public String reserve_paymentOK_rsu(OfficePaymentVO pvo, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();

		PaymentInfoVO pvo2 = service.select_one_final_payment_info(pvo.getReserve_no());
		pvo.setRoom_no(pvo2.getRoom_no());
		pvo.setBackoffice_no(pvo2.getBackoffice_no());
		pvo.setSales_state("F");

		if (pvo.getPayment_state().equals("F")) {
			int payment_total = (pvo.getActual_payment() + pvo.getUse_mileage()) * 5;
			pvo.setPayment_total(payment_total);
		}

		if (pvo.getUse_mileage() > 0) {
			pvo.setPayment_total(pvo.getActual_payment() + pvo.getUse_mileage());
		}

		int result_payment = service.insert_paymentOK(pvo);
		int result_update_reserve_state = service.update_reserve_state(pvo.getReserve_no());

		OfficeMileageVO mvo = service.select_one_recent_mileage(pvo.getUser_no());
		int mileage_change = 0;

		OfficeMileageVO mvo2 = new OfficeMileageVO();
		int mileage_total = mvo.getMileage_total();
		int result_mileage = 0; // 마일리지 추가 성공 flag

		if (pvo.getUse_mileage() == 0) {
			mvo2.setMileage_state("W");

			if (pvo.getPayment_state().equals("F")) {
				// 후결제 마일리지 적립 X
				mileage_change = 0;
			} else if (pvo.getPayment_state().equals("T")) {
				// 선결제만 마일리지 적립
				mileage_change = (int) ((pvo.getPayment_total()) * 0.05);
			}

			mvo2.setMileage_total(mileage_total);
			mvo2.setUser_no(pvo.getUser_no());
			mvo2.setMileage_change(mileage_change);

			OfficePaymentVO pvo3 = service.select_one_recent_payment(pvo.getUser_no());
			mvo2.setPayment_no(pvo3.getPayment_no());

			result_mileage = service.insert_mileage_changed(mvo2);

		} else {
			// 마일리지 사용
			mvo2.setMileage_state("F");

			mileage_change = pvo.getUse_mileage();
			mileage_total -= mileage_change;

			mvo2.setMileage_total(mileage_total);
			mvo2.setUser_no(pvo.getUser_no());
			mvo2.setMileage_change(mileage_change);

			OfficePaymentVO pvo3 = service.select_one_recent_payment(pvo.getUser_no());
			mvo2.setPayment_no(pvo3.getPayment_no());

			result_mileage = service.insert_mileage_changed(mvo2);

			// 마일리지 사용 후 해당 결제 건에 대한 마일리지 적립 로직
			mvo2.setMileage_state("W");

			if (pvo.getPayment_state().equals("F")) {
				mileage_change = 0;
			} else if (pvo.getPayment_state().equals("T")) {
				mileage_change = (int) ((pvo.getPayment_total()) * 0.05);
			}

			mvo2.setMileage_change(mileage_change);

			result_mileage = service.insert_mileage_changed(mvo2);
		}

		if (result_payment == 1 && result_update_reserve_state == 1 && result_mileage == 1) {
			map.put("result", "1");
		} else {
			map.put("result", "0");
		}

		String json = gson.toJson(map);

		return json;
	}

	@ApiOperation(value = "문의 추가 컨트롤러", notes = "문의 추가 로직 컨트롤러")
	@GetMapping(value = "/insert_question")
	@ResponseBody
	public String insert_question(Comment_EntityVO vo, Model model) {
		int result = service.insert_question(vo);

		log.info("insert_question()..");
		log.info("********** request :: {}", vo);

		Map<String, String> map = new HashMap<>();

		// Map -> Json 문자열
		Gson gson = new Gson();
		if (result == 1) {
			map.put("result", "1");
		} else {
			map.put("result", "0");
		}

		String jsonStr = gson.toJson(map);

		return jsonStr;
	}

	// 리스트 페이지
	@ApiOperation(value = "리스트 페이지 로드 컨트롤러", notes = "타입에 따른 리스트 페이지를 로드하는 컨트롤러")
	@GetMapping(value = "/list_page")
	public String list_page(String type, Integer page, String condition, Model model) {
		log.info("list_page()...");

		Map<String, Object> map = new HashMap<String, Object>();

		int total_cnt = service.list_totalCnt("%" + type + "%");

		List<ListViewVO> list = service.select_all_list(type, condition, 12 * (page - 1) + 1, 12 * (page));

		if (list == null)
			map.put("cnt", 0);
		else
			map.put("cnt", list.size());

		if (list != null) {
			for (ListViewVO vo : list) {
				DecimalFormat dc = new DecimalFormat("###,###,###,###");
				String ch = dc.format(Integer.parseInt(vo.getMin_room_price()));
				vo.setMin_room_price(ch);
				vo.setAvg_rating(Double.toString((Math.round(Double.parseDouble(vo.getAvg_rating()) * 100) / 100.0)));

				vo.setBackoffice_image(
						"https://rence.s3.ap-northeast-2.amazonaws.com/space/" + vo.getBackoffice_image());

				if (vo.getBackoffice_tag() != null) {
					String[] tags = vo.getBackoffice_tag().split(",");

					int i = 0;
					for (String tag : tags) {
						tag = "#" + tag;
						tags[i] = tag;
						i++;
					}

					vo.setBackoffice_tag(String.join(" ", tags));
				}

				if (vo.getRoadname_address().contains(" ")) {
					String road_name = vo.getRoadname_address().split(" ")[0] + " "
							+ vo.getRoadname_address().split(" ")[1];
					vo.setRoadname_address(road_name);
				}
			}
		}

		map.put("condition", condition);
		map.put("page", "list_page");
		map.put("nowCnt", 1);

		if (total_cnt > 0)
			map.put("maxCnt", total_cnt);
		else
			map.put("maxCnt", 0);

		map.put("list", list);
		model.addAttribute("res", map);

		model.addAttribute("content", "thymeleaf/html/office/list");

		return "thymeleaf/layouts/office/layout_base";
	}

	// 리스트 페이지 페이징
	@ApiOperation(value = "리스트 페이지 페이징 컨트롤러", notes = "타입에 따른 리스트 페이지를 페이징하는 컨트롤러")
	@GetMapping(value = "/list_paging")
	@ResponseBody
	public String list_paging(String type, Integer page, String condition) {
		log.info("list_paging()...");
		log.info("{}...{}...{}", type, page, condition);

		Map<String, Object> map = new HashMap<String, Object>();

		List<ListViewVO> list = service.select_all_list(type, condition, 12 * (page - 1) + 1, 12 * (page));

		if (list == null)
			map.put("cnt", 0);
		else
			map.put("cnt", list.size());

		if (list != null) {
			for (ListViewVO vo : list) {
				DecimalFormat dc = new DecimalFormat("###,###,###,###");
				String ch = dc.format(Integer.parseInt(vo.getMin_room_price()));
				vo.setMin_room_price(ch);
				vo.setAvg_rating(Double.toString((Math.round(Double.parseDouble(vo.getAvg_rating()) * 100) / 100.0)));

				vo.setBackoffice_image(
						"https://rence.s3.ap-northeast-2.amazonaws.com/space/" + vo.getBackoffice_image());

				if (vo.getBackoffice_tag() != null) {
					String[] tags = vo.getBackoffice_tag().split(",");

					int i = 0;
					for (String tag : tags) {
						tag = "#" + tag;
						tags[i] = tag;
						i++;
					}

					vo.setBackoffice_tag(String.join(" ", tags));
				}

				if (vo.getRoadname_address().contains(" ")) {
					String road_name = vo.getRoadname_address().split(" ")[0] + " "
							+ vo.getRoadname_address().split(" ")[1];
					vo.setRoadname_address(road_name);
				}
			}
		}

		map.put("list", list);

		Gson gson = new Gson();
		String jsonStr = gson.toJson(map);

		return jsonStr;
	}

	// 검색 리스트 페이지 페이징
	@ApiOperation(value = "검색 리스트 페이지 페이징 컨트롤러", notes = "검색 리스트 페이지를 페이징하는 컨트롤러")
	@GetMapping(value = "/search_list_paging")
	@ResponseBody
	public String search_list_paging(String type, String location, String searchWord, String condition, Integer page) {
		log.info("search_list_paging()...");
		log.info("search_list()...");

		Map<String, Object> map = new HashMap<String, Object>();

		List<ListViewVO> list = null;
		list = service.search_list(type, location, searchWord, condition, 12 * (page - 1) + 1, 12 * (page));

		if (list == null)
			map.put("cnt", 0);
		else
			map.put("cnt", list.size());

		if (list != null) {
			for (ListViewVO vo : list) {
				DecimalFormat dc = new DecimalFormat("###,###,###,###");
				String ch = dc.format(Integer.parseInt(vo.getMin_room_price()));
				vo.setMin_room_price(ch);
				vo.setAvg_rating(Double.toString((Math.round(Double.parseDouble(vo.getAvg_rating()) * 100) / 100.0)));

				vo.setBackoffice_image(
						"https://rence.s3.ap-northeast-2.amazonaws.com/space/" + vo.getBackoffice_image());

				if (vo.getBackoffice_tag() != null) {
					String[] tags = vo.getBackoffice_tag().split(",");

					int i = 0;
					for (String tag : tags) {
						tag = "#" + tag;
						tags[i] = tag;
						i++;
					}

					vo.setBackoffice_tag(String.join(" ", tags));
				}

				if (vo.getRoadname_address().contains(" ")) {
					String road_name = vo.getRoadname_address().split(" ")[0] + " "
							+ vo.getRoadname_address().split(" ")[1];
					vo.setRoadname_address(road_name);
				}
			}
		}

		map.put("list", list);

		Gson gson = new Gson();
		String jsonStr = gson.toJson(map);

		return jsonStr;
	}
}
