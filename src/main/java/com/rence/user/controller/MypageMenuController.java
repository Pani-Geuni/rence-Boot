/**
 * @author 김예은, 전판근
 */

package com.rence.user.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rence.office.common.OfficeInfoMap;
import com.rence.office.model.OfficeMileageVO;
import com.rence.office.model.OfficePaymentVO;
import com.rence.office.service.OfficeService;
import com.rence.user.model.ReserveInfo_ViewVO;
import com.rence.user.model.ReserveMileageVO;
import com.rence.user.model.ReviewEntityVO;
import com.rence.user.model.UserDTO;
import com.rence.user.service.MypageMenuSerivice;
import com.rence.user.service.PaymentCancelService;
import com.rence.user.service.ReserveMileageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "마이페이지 메뉴 내 기능 관련 컨트롤러")
@Slf4j
@Controller
@RequestMapping("/rence")
public class MypageMenuController {

	Gson gson = new GsonBuilder().setPrettyPrinting().create();

	@Autowired
	HttpSession session;

	@Autowired
	MypageMenuSerivice service;

	@Autowired
	OfficeService officeService;

	@Autowired
	PaymentCancelService paymentService;

	@Autowired
	ReserveMileageService reserveMileageService;

	/**
	 * 상세 예약 페이지 이동 - 현재
	 */
	@GetMapping(value = "/reserve_info")
	public String reserve_info_rsu(String reserve_no, Model model, HttpServletRequest request) {
		String user_no = null;

		OfficeInfoMap info_map = new OfficeInfoMap();

		String is_login = (String) session.getAttribute("user_id");
		Cookie[] cookies = request.getCookies();

		if (is_login != null && cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals("user_no")) {
					user_no = c.getValue();
				}
			}

			Map<String, Object> map = new HashMap<String, Object>();

			ReserveInfo_ViewVO vo = service.select_one_reserve_info(reserve_no);

			List<String> splitImage = info_map.splitImage(vo.getBackoffice_image());
			String room_first_image = splitImage.get(0);
			vo.setBackoffice_image(room_first_image);

			vo.setRoom_type(info_map.changeType(vo.getRoom_type()));

			map.put("reserve_no", reserve_no);
			map.put("info_obj", vo);

			if (vo != null) {
				DecimalFormat dc = new DecimalFormat("###,###,###,###");

				String ch1 = dc.format(Integer.parseInt(vo.getActual_payment()));
				vo.setActual_payment(ch1);

				String ch2 = dc.format(Integer.parseInt(vo.getMileage_change()));
				vo.setMileage_change(ch2);

				String ch3 = dc.format(Integer.parseInt(vo.getPayment_total()));
				vo.setPayment_total(ch3);

				String ch4 = dc.format(Integer.parseInt(vo.getRoom_price()));
				vo.setRoom_price(ch4);
			}

			ReserveMileageVO mvo = reserveMileageService.select_one_reserve_mileage(reserve_no);

			if (mvo != null) {
				DecimalFormat dc = new DecimalFormat("###,###,###,###");

				String ch1 = dc.format(Integer.parseInt(mvo.getActual_payment()));
				mvo.setActual_payment(ch1);

				String ch2 = dc.format(Integer.parseInt(mvo.getPayment_total()));
				mvo.setPayment_total(ch2);

				if (mvo.getMileage_state().equals("F")) {
					mvo.setMileage_change(
							Integer.toString((int) Math.round(Integer.parseInt(mvo.getActual_payment()) * 0.05)));
				}

				String ch3 = dc.format(Integer.parseInt(mvo.getMileage_change()));
				mvo.setMileage_change(ch3);

				String ch4 = dc.format(Integer.parseInt(mvo.getUse_mileage()));
				mvo.setUse_mileage(ch4);
			}

			map.put("mvo", mvo);

			// 버튼 section
			OfficePaymentVO pvo = officeService.select_one_cancel_payment(reserve_no);
			map.put("pvo", pvo);

			UserDTO vo2 = service.select_one_user_info(user_no);
			map.put("user_obj", vo2);
			model.addAttribute("res", map);

			model.addAttribute("content", "thymeleaf/html/office/reserve/reserve_detail_now");

			return "thymeleaf/layouts/office/layout_reserve";

		} else {
			return "redirect:/";
		}

	}

	/**
	 * 상세 예약 페이지 - 예약 취소
	 */
	@GetMapping(value = "/reserve_cancel")
	@ResponseBody
	public String reserve_cancel_rsu(String reserve_no, String user_no) {
		Map<String, String> map = new HashMap<String, String>();

		int result = officeService.update_reserve_cancel(reserve_no, user_no);
		int mileage_result = 0;

		OfficePaymentVO pvo = officeService.select_one_cancel_payment(reserve_no);

		List<OfficeMileageVO> mvo = officeService.select_all_mileage_cancel(pvo.getPayment_no());

		log.info("menu mvo :: {}", mvo);

		for (OfficeMileageVO vo : mvo) {
			String mileage_no = vo.getMileage_no();

			log.info("menu vo :: {}", vo);

			// 마일리지 사용 취소
			if (vo.getMileage_state().equals("F")) {
				OfficeMileageVO temp_vo = officeService.select_one_mileage_cancel(pvo.getPayment_no(), "F");

				temp_vo.setMileage_no(null);
				temp_vo.setMileage_state("T");
				temp_vo.setMileage_total(temp_vo.getMileage_total() + temp_vo.getMileage_change());

				log.info("temp_Vo :: {}", temp_vo);

				mileage_result = officeService.insert_mileage_changed(temp_vo);
			}

			// 예약 취소 시, 마일리지 상태를 적립 예정(W)에서 적립 예정 취소(C)로 변경
			if (vo.getMileage_state().equals("W")) {
				mileage_result = officeService.update_mileage_state(mileage_no);
			}
		}

		if (result == 1 && mileage_result == 1) {
			map.put("result", "1");
		} else {
			map.put("result", "0");
		}

		String json = gson.toJson(map);

		return json;
	}

	/**
	 * 예약 취소 후 결제 취소
	 * 
	 * @throws IOException
	 */
	@PostMapping(value = "/payment_cancel")
	@ResponseBody
	public String payment_cancel_rsu(String reserve_no, Integer cancel_amount, String reason) throws IOException {
		Map<String, Object> map = new HashMap<>();

		OfficePaymentVO pvo = officeService.select_one_cancel_payment(reserve_no);

		String token = paymentService.getToken();
		int amount = pvo.getActual_payment();
		paymentService.payMentCancel(token, pvo.getImp_uid(), amount, "사용자 예약 취소");

		map.put("reserve_no", reserve_no);

		Integer update_cancel_amount = pvo.getActual_payment();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date payment_date = pvo.getPayment_date();
		Date current_date = new Date();

		@SuppressWarnings("deprecation")
		long diffHour = (current_date.getTime() - payment_date.getTime()) / 3600000;

		if (diffHour <= 1) {
			update_cancel_amount = pvo.getActual_payment();
		} else {
			Integer deposit = (int) (pvo.getPayment_total() * 0.2);

			update_cancel_amount = pvo.getActual_payment() - deposit;
		}

		int result = officeService.update_payment_cancel(reserve_no, update_cancel_amount);

		if (result == 1) {
			map.put("result", "1");
		} else {
			map.put("result", "0");
		}

		String json = gson.toJson(map);

		return json;
	}

	/**
	 * 상세 예약 페이지 이동 - 과거
	 */
	@GetMapping(value = "/reserved_info")
	public String reserved_info(String reserve_no, Model model, HttpServletRequest request) {
		String user_no = null;

		OfficeInfoMap info_map = new OfficeInfoMap();

		String is_login = (String) session.getAttribute("user_id");
		Cookie[] cookies = request.getCookies();

		if (is_login != null && cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals("user_no")) {
					user_no = c.getValue();
				}
			}

			Map<String, Object> map = new HashMap<String, Object>();

			ReserveInfo_ViewVO vo = service.select_one_reserve_info(reserve_no);

			List<String> splitImage = info_map.splitImage(vo.getBackoffice_image());
			String room_first_image = splitImage.get(0);
			vo.setBackoffice_image(room_first_image);

			vo.setRoom_type(info_map.changeType(vo.getRoom_type()));

			map.put("reserve_no", reserve_no);
			map.put("info_obj", vo);

			if (vo != null) {
				DecimalFormat dc = new DecimalFormat("###,###,###,###");

				String ch1 = dc.format(Integer.parseInt(vo.getActual_payment()));
				vo.setActual_payment(ch1);

				String ch2 = dc.format(Integer.parseInt(vo.getMileage_change()));
				vo.setMileage_change(ch2);

				String ch3 = dc.format(Integer.parseInt(vo.getPayment_total()));
				vo.setPayment_total(ch3);

				String ch4 = dc.format(Integer.parseInt(vo.getRoom_price()));
				vo.setRoom_price(ch4);
			}

			ReserveMileageVO mvo = reserveMileageService.select_one_reserve_mileage(reserve_no);

			if (mvo != null) {
				DecimalFormat dc = new DecimalFormat("###,###,###,###");

				String ch1 = dc.format(Integer.parseInt(mvo.getActual_payment()));
				mvo.setActual_payment(ch1);

				String ch2 = dc.format(Integer.parseInt(mvo.getPayment_total()));
				mvo.setPayment_total(ch2);

				if (mvo.getMileage_state().equals("F")) {
					mvo.setMileage_change(
							Integer.toString((int) Math.round(Integer.parseInt(mvo.getActual_payment()) * 0.05)));
				}

				String ch3 = dc.format(Integer.parseInt(mvo.getMileage_change()));
				mvo.setMileage_change(ch3);

				String ch4 = dc.format(Integer.parseInt(mvo.getUse_mileage()));
				mvo.setUse_mileage(ch4);
			}

			map.put("mvo", mvo);

			UserDTO vo2 = service.select_one_user_info(user_no);
			map.put("user_obj", vo2);

			int is_review = service.is_write_review(vo.getRoom_no(), vo.getBackoffice_no());
			if (is_review == 0)
				map.put("is_write_review", false);
			else
				map.put("is_write_review", true);

			// 버튼 section
			OfficePaymentVO pvo = officeService.select_one_cancel_payment(reserve_no);
			map.put("pvo", pvo);

			model.addAttribute("res", map);

			model.addAttribute("content", "thymeleaf/html/office/reserve/reserve_detail_before");

			return "thymeleaf/layouts/office/layout_reserve";
		} else {
			return "redirect:/";
		}
	}

	@ApiOperation(value = "후기 추가 컨트롤러", notes = "후기 추가 로직 컨트롤러")
	@GetMapping(value = "/insert_review")
	@ResponseBody
	public String insert_review(ReviewEntityVO vo, Model model) {
		int result = service.insert_review(vo);

		Map<String, String> map = new HashMap<>();

		// Map -> Json 문자열
		Gson gson = new Gson();
		if (result != -1) {
			map.put("result", "1");
		} else {
			map.put("result", "0");
		}

		String jsonStr = gson.toJson(map);

		return jsonStr;
	}

	/**
	 * 후기 내역 페이지 - 댓글 삭제
	 */
	@GetMapping(value = "/delete_review")
	public String delete_review(String user_no, String review_no, Model model) {
		String is_login = (String) session.getAttribute("user_id");

		if (is_login != null) {
			int result = service.delete_review(review_no);

			return "redirect:/rence/review_list?user_no=" + user_no;
		} else {
			return "redirect:/";
		}
	}

	/**
	 * 문의 리스트 페이지 - 문의 삭제
	 */
	@GetMapping(value = "/delete_comment")
	public String delete_comment(String user_no, String comment_no) {
		String is_login = (String) session.getAttribute("user_id");

		if (is_login != null) {
			int result = service.delete_comment(comment_no);

			return "redirect:/rence/question_list?user_no=" + user_no;
		} else {
			return "redirect:/";
		}
	}

}
