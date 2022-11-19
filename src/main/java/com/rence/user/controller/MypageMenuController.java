/**
 * @author 김예은
 */

package com.rence.user.controller;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rence.office.model.ListViewVO;
import com.rence.user.model.ReserveInfo_ViewVO;
import com.rence.user.model.UserDTO;
import com.rence.user.service.MypageMenuSerivice;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Api(tags="마이페이지 메뉴 내 기능 관련 컨트롤러")
@Slf4j
@Controller
@RequestMapping("/rence")
public class MypageMenuController {

	@Autowired
	HttpSession session;
	
	@Autowired
	MypageMenuSerivice service;

	
	/**
	 * 상세 예약 페이지 이동 - 현재
	 */
	@GetMapping(value = "/reserve_info")
	public String reserve_info(String reserve_no, Model model, HttpServletRequest request) {
		String user_no = null;
		
		String is_login = (String)session.getAttribute("user_id");
		Cookie[] cookies = request.getCookies();
		
		if(is_login != null && cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals("user_no")) {
					user_no = c.getValue();
				}
			}
			
			Map<String, Object> map = new HashMap<String, Object>();
			
			ReserveInfo_ViewVO vo = service.select_one_reserve_info(reserve_no);
			map.put("reserve_no", reserve_no);
			map.put("info_obj", vo);
			
			if(vo != null) {
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
			
			int is_review = service.is_write_review(vo.getRoom_no(), vo.getBackoffice_no());
			if(is_review == 0) map.put("is_write_review", false);
			else map.put("is_write_review", true);
			
			UserDTO vo2 = service.select_one_user_info(user_no);
			map.put("user_obj", vo2);
			model.addAttribute("res", map);
			
			
			model.addAttribute("content", "thymeleaf/html/office/reserve/reserve_detail_now");
			
			return "thymeleaf/layouts/office/layout_reserve";
			
		}
		else {
			return "redirect:/";
		}
			
	}
	
	/**
	 * 상세 예약 페이지 이동 - 과거
	 */
	@GetMapping(value = "/reserved_info")
	public String reserved_info(String reserve_no, Model model, HttpServletRequest request) {
		String user_no = null;
		
		String is_login = (String)session.getAttribute("user_id");
		Cookie[] cookies = request.getCookies();
		
		if(is_login != null && cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals("user_no")) {
					user_no = c.getValue();
				}
			}
			
			Map<String, Object> map = new HashMap<String, Object>();
			
			ReserveInfo_ViewVO vo = service.select_one_reserve_info(reserve_no);
			map.put("reserve_no", reserve_no);
			map.put("info_obj", vo);
			
			
			if(vo != null) {
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
			
			UserDTO vo2 = service.select_one_user_info(user_no);
			log.info("UserDTO | {}", vo2);
			map.put("user_obj", vo2);
			model.addAttribute("res", map);
			
			log.info("reserved_info : {}", map);
			
			model.addAttribute("content", "thymeleaf/html/office/reserve/reserve_detail_before");
			
			return "thymeleaf/layouts/office/layout_reserve";
		}else {
			return "redirect:/";
		}
	}
	
	/**
	 * 후기 내역 페이지 - 댓글 삭제
	 */
	@GetMapping(value = "/delete_review")
	public String delete_review(String user_no, String review_no, Model model) {
		String is_login = (String)session.getAttribute("user_id");
		
		if(is_login != null) {
			int result = service.delete_review(review_no);
			
			return "redirect:/rence/review_list?user_no="+user_no;
		}
		else {
			return "redirect:/";
		}
	}

	
	/**
	 * 문의 리스트 페이지 - 문의 삭제
	 */
	@GetMapping(value = "/delete_comment")
	public String delete_comment(String user_no, String comment_no) {
		String is_login = (String)session.getAttribute("user_id");
		
		if(is_login != null) {
			int result = service.delete_comment(comment_no);
			
			return "redirect:/rence/question_list?user_no="+user_no;
		}
		else {
			return "redirect:/";
		}
	}
	
}
