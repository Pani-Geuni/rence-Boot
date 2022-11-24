/**
 * @author 전판근
 */

package com.rence.office.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.rence.backoffice.model.BackOfficeVO;
import com.rence.backoffice.service.CustomDateFormatter;
import com.rence.common.OptionEngToKorMap;
import com.rence.office.model.Comment_EntityVO;
import com.rence.office.model.ListViewVO;
import com.rence.office.model.OfficeInfoVO;
import com.rence.office.model.OfficeOperatingTimeVO;
import com.rence.office.model.OfficeOperatingTimeVO_date;
import com.rence.office.model.OfficeQuestionVO;
import com.rence.office.model.OfficeReviewVO;
import com.rence.office.model.OfficeRoomVO;
import com.rence.office.service.OfficeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@Api(tags = "오피스 컨트롤러")
@RequestMapping("/office")
public class OfficeController {

	@Autowired
	OfficeService service;

	@Autowired
	HttpSession session;

	/*
	 * 오피스(공간) 상세 페이지
	 */
	@ApiOperation(value = "공간 소개 페이지 로드 (데스크,회의실)", notes = "데스크 / 회의실 공간 소개 페이지 로드하는 컨트롤러")
	@RequestMapping(value = "/space_introduce", method = RequestMethod.GET)
	public String space_intruduce(BackOfficeVO bvo, String introduce_menu, Model model) {

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
		
		ovo.setAvg_rating(Math.round(ovo.getAvg_rating()*10)/10.0);
		
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
		List<OfficeQuestionVO> cvos = service.select_all_comment(backoffice_no);

		String is_login = (String) session.getAttribute("user_id");
		

		if (cvos != null) {
			for (OfficeQuestionVO vo : cvos) {
				
				log.info("is_login :::::::::: {}", is_login);
				log.info("user_no :::::::::: {}", vo.getUser_id());
				
				OfficeQuestionVO vo2 = service.select_one_answer(vo.getComment_no());
				if (vo2 != null) {
					if (vo.getIs_secret() == null) {

						vo.setAnswer_content(vo2.getComment_content());
						vo.setAnswer_date(vo2.getComment_date());						
						vo.setComment_state("Y");
					}
				} else {
					vo.setComment_state("N");
				}
				
				// 이름 마스킹
				String originName = vo.getUser_name();
				String firstName = originName.substring(0, 1);
				String midName = originName.substring(1, originName.length()-1);
				
				String maskingMidName = "";
				for (int i = 0; i < midName.length(); i++) {
					maskingMidName += "*";
				}
				
				String lastName = originName.substring(originName.length()-1, originName.length());
				
				String maskingName = firstName + maskingMidName + lastName;
				
				vo.setUser_name(maskingName);
			}
		}
		
		
		// **************
		// backoffice 후기
		// **************
		List<OfficeReviewVO> revos = service.select_all_review(backoffice_no);
		
		for (OfficeReviewVO vo : revos) {
			
			// 이름 마스킹
			String originName = vo.getUser_name();
			String firstName = originName.substring(0, 1);
			String midName = originName.substring(1, originName.length()-1);
			
			String maskingMidName = "";
			for (int i = 0; i < midName.length(); i++) {
				maskingMidName += "*";
			}
			
			String lastName = originName.substring(originName.length()-1, originName.length());
			
			String maskingName = firstName + maskingMidName + lastName;
			
			vo.setUser_name(maskingName);
		}
				
		
		// backoffice 기본 정보
		model.addAttribute("page", "space_introduce_detail");
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
		model.addAttribute("cvos_cnt", cvos.size());

		// backoffice 후기
		model.addAttribute("revos", revos);
		model.addAttribute("review_cnt", revos.size());

		model.addAttribute("content", "thymeleaf/html/office/space_detail/space_detail_introduce");
		model.addAttribute("title", "대쉬보드 메인");

		return "thymeleaf/layouts/office/layout_base";
	}
//	
//	
//	// **********************
//	// space introduce OFFICE
//	// **********************
//	@ApiOperation(value="공간 소개 페이지 로드 (오피스)", notes="오피스 공간 소개 페이지 로드하는 컨트롤러")
//	@RequestMapping(value = "/office/space_introduce_office", method = RequestMethod.GET)
//	public String space_intruduce_office(BackOfficeVO bvo, String introduce_menu, Model model) {
//		
//		OfficeInfoMap info_map = new OfficeInfoMap();
//		
//		String backoffice_no = bvo.getBackoffice_no();
//		
//		// ******************
//		// backoffice 기본 정보
//		// ******************
//		OfficeInfoVO ovo = service.select_one_office_info(backoffice_no);
//		List<String> type_list = new ArrayList<String>();
//		List<String> tag_list = new ArrayList<String>();
//		List<String> img_list = new ArrayList<String>();
//		List<String> option_list = new ArrayList<String>();
//		List<String> around_option_list = new ArrayList<String>();
//		
//		if (ovo.getBackoffice_type() != null) {
//			type_list = info_map.splitType(ovo.getBackoffice_type());			
//		} else {
//			type_list.add("타입 없음");
//		}
//		
//		if (ovo.getBackoffice_tag() != null) {
//			tag_list = info_map.splitTag(ovo.getBackoffice_tag());			
//		} else {
//			tag_list.add("옵션 없음");
//		}
//		
//		img_list = info_map.splitImage(ovo.getBackoffice_image());
//		
//		if (ovo.getBackoffice_option() != null) {
//			option_list = info_map.splitOption(ovo.getBackoffice_option());			
//		} else {
//			option_list.add("옵션 없음");
//		}
//		
//		if (ovo.getBackoffice_around() != null) {
//			around_option_list = info_map.splitAroundOption(ovo.getBackoffice_around());			
//		} else {
//			around_option_list.add("주변 시설 없음");
//		}
//		
//		ovo.setShort_roadname_address(info_map.makeShortAddress(ovo.getRoadname_address()));
//		
//		// ******************
//		// backoffice 운영 시간
//		// ******************
//		OfficeOperatingTimeVO_date dotvo = service.select_one_operating_time(backoffice_no);
//		
//		CustomDateFormatter df = new CustomDateFormatter();
//		OfficeOperatingTimeVO otvo = df.showStringOfficeOperating(dotvo);
//		
//		// ************************
//		// backoffice 운영 공간(Room)
//		// ************************
//		List<OfficeRoomVO> rvos = service.select_all_room(backoffice_no);
//		
//		for (OfficeRoomVO vo : rvos) {
//			vo.setRoom_name(info_map.changeType(vo.getRoom_type()));
//		}
//		
//		// **************
//		// backoffice 후기
//		// **************
//		List<OfficeReviewVO> revos = service.select_all_review(backoffice_no);
//		
//		
//		// backoffice 기본 정보
//		model.addAttribute("page", "space_introduce_detail_office");
//		model.addAttribute("ovo", ovo);
//		model.addAttribute("type_list", type_list);
//		model.addAttribute("tag_list", tag_list);
//		model.addAttribute("img_list", img_list);
//		model.addAttribute("option_list", option_list);
//		model.addAttribute("around_option_list", around_option_list);
//
//		// backoffice 운영 시간
//		model.addAttribute("otvo", otvo);
//		
//		// backoffice 운영 공간
//		model.addAttribute("rvos", rvos);
//		
//		return ".space/space_detail_introduce_office";
//	}

	// **********************
	// 공간 예약 체크
	// **********************
//	@ApiOperation(value="공간 예약 체크 컨트롤러", notes="해당 공간에 원하는 시간에 예약이 가능한지 확인해주는 컨트롤러")
//	@RequestMapping(value = "/office/reserve_checkOK", method = RequestMethod.POST)
//	@ResponseBody
//	public JSONObject reserve_checkOK(OfficeReserveVO rvo) throws ParseException {
//		
//		JSONObject jsonObject = new JSONObject();
//		
//		int result = service.check_reserve(rvo);
//		
//		String reserve_no = service.select_one_last_reserve(rvo.getUser_no());
//		
//		if (result == 1) {
//			jsonObject.put("result", "1");
//			jsonObject.put("reserve_no", reserve_no);
//		} else {
//			jsonObject.put("result", "0");
//		}
//		
//		return jsonObject;
//	}
//	
//	// **********************
//	// 공간 결제 페이지
//	// **********************
//	@ApiOperation(value="결제 페이지 로드 컨트롤러", notes="예약 및 결제하는 페이지 데이터 불러오는 컨트롤러")
//	@GetMapping(value = "/office/payment_page")
//	public String space_payment(OfficeReserveVO rvo, Model model) throws ParseException {
//		
//		String reserve_no = rvo.getReserve_no();
//		
//		PaymentInfoVO pvo = service.select_one_final_payment_info(reserve_no);
//		OfficeInfoMap info_map = new OfficeInfoMap();
//		
//		pvo.setRoom_type(info_map.changeType(pvo.getRoom_type()));
//		List<String> splitImage = info_map.splitImage(pvo.getBackoffice_image());
//		String room_first_image = splitImage.get(0);
//		pvo.setBackoffice_image(room_first_image);
//		
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//		
//		Date sdate = formatter.parse(pvo.getReserve_stime());
//		Date edate = formatter.parse(pvo.getReserve_etime());
//		
//		// 사용자 총 예약 시간
//		long diffHour = (edate.getTime() - sdate.getTime()) / 3600000;
//		
//		// 전체 결제할 금액
//		int payment_all = (int) diffHour * pvo.getRoom_price();
//		int earned_mileage = (int) (payment_all * 0.05);
//		
//		
//		model.addAttribute("pvo", pvo);
//		model.addAttribute("payment_all", payment_all);
//		model.addAttribute("earned_mileage", earned_mileage);
//		
//		
//		return ".payment_page";
//	}

//	@ApiOperation(value="결제 컨트롤러", notes="예약 및 결제하는 페이지 결제 로직 컨트롤러")
//	@PostMapping(value = "/office/reserve_paymentOK")
//	@ResponseBody
//	public JSONObject reserve_paymentOK(OfficePaymentVO pvo, Model model) {
//		JSONObject jsonObject = new JSONObject();
//		
//		PaymentInfoVO pvo2 = service.select_one_final_payment_info(pvo.getReserve_no());
//		pvo.setRoom_no(pvo2.getRoom_no());
//		pvo.setBackoffice_no(pvo2.getBackoffice_no());
//		pvo.setSales_state("F");
//		
//		
//		int result = service.reserve_paymentOK(pvo);
//		
//		if (result == 1) {
//			jsonObject.put("result", "1");			
//		} else {
//			jsonObject.put("result", "0");
//		}
//		
//		return jsonObject;
//	}

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
		
		List<ListViewVO> list = service.select_all_list(type, condition, 9 * (page - 1) + 1, 9 * (page));
		
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
				
				
				if(vo.getBackoffice_tag() != null) {
					String []tags = vo.getBackoffice_tag().split(",");
					
					int i = 0;
					for(String tag : tags) {
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
		
		if(total_cnt > 0)
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
		
		List<ListViewVO> list = service.select_all_list(type, condition, 9 * (page - 1) + 1, 9 * (page));
		
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
				
				vo.setBackoffice_image("https://rence.s3.ap-northeast-2.amazonaws.com/space/" + vo.getBackoffice_image());
				
				
				if(vo.getBackoffice_tag() != null) {
					String []tags = vo.getBackoffice_tag().split(",");
					
					int i = 0;
					for(String tag : tags) {
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
		list = service.search_list(type, location, searchWord, condition,  9 * (page - 1) + 1, 9 * (page));

		if (list == null)
			map.put("cnt", 0);
		else
			map.put("cnt", list.size());
		
		if(list != null) {
			for(ListViewVO vo : list) {
				DecimalFormat dc = new DecimalFormat("###,###,###,###");	
				String ch = dc.format(Integer.parseInt(vo.getMin_room_price()));
				vo.setMin_room_price(ch);
				vo.setAvg_rating(Double.toString((Math.round(Double.parseDouble(vo.getAvg_rating())*100)/100.0)));
				
				vo.setBackoffice_image("https://rence.s3.ap-northeast-2.amazonaws.com/space/" + vo.getBackoffice_image());
				
				if(vo.getBackoffice_tag() != null) {
					String []tags = vo.getBackoffice_tag().split(",");
					
					int i = 0;
					for(String tag : tags) {
						tag = "#" + tag;
						tags[i] = tag;
						i++;
					}
					
					vo.setBackoffice_tag(String.join(" ", tags));
				}
				
				if(vo.getRoadname_address().contains(" ")) {
					String road_name = vo.getRoadname_address().split(" ")[0] + " " + vo.getRoadname_address().split(" ")[1];
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
