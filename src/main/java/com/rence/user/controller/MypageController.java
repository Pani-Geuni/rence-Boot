/**
 * @author 강경석
 * 마이페이지에 관련된 전반적 기술을 처리하는 컨트롤러
 * 마이페이지의 페이징
 * 회원 탈퇴
 * 
 */
package com.rence.user.controller;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.rence.user.model.UserMileageVO;
import com.rence.user.model.UserMypageVO;
import com.rence.user.model.UserVO;
import com.rence.user.service.UserMypageSerivice;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Api(tags="마이페이지 컨트롤러")
@Slf4j
@Controller
public class MypageController {
	
	@Autowired
	UserMypageSerivice service;
	
//	@Autowired
//	UserFileuploadService fileuploadService;

	@Autowired
	HttpSession session;
	
	/**
	 * 현재예약리스트 이동
	 */
	@GetMapping("/go_now_reserve")
	public String go_now_reserve() {

		return ".my_page/now-reserve-list";
	}
	/**
	 * 과거예약리스트 이동
	 */
	@GetMapping("/go_before_reserve")
	public String go_before_reserve() {

		return ".my_page/before-reserve-list";
	}
	
	/**
	 * 마일리지 리스트 페이지 이동
	 */
	@GetMapping("/go_mileage")
	public String go_mileage(UserVO uvo ,Model model, HttpServletRequest request) {
		log.info("go_mileage()...");
		log.info("UserVO(사용자 고유번호): {}", uvo);
		
		//총 마일리지 부분
		UserMileageVO umvo = service.user_mileage_selectOne(uvo);
		log.info("umvo: {}", umvo);

//		마일리지 콤마단위로 변환
		DecimalFormat dc = new DecimalFormat("###,###,###,###,###");
	
		String mileage_total = dc.format(umvo.getMileage_total());
		log.info("mileage_total: "+ mileage_total);
		
		
		List<UserMileageVO> vos = service.user_mileage_selectAll(uvo);
		log.info("vos: {}"+ vos);

		for (int i = 0; i < vos.size(); i++) {
			vos.get(i).setMileage(dc.format(Integer.parseInt(vos.get(i).getMileage())));
		}
		log.info("Type change vos: {}"+ vos);

		Map<String, List<UserMileageVO>> map2 = new HashMap<String, List<UserMileageVO>>();

		map2.put("list", vos);
		model.addAttribute("res", map2);
		model.addAttribute("mileage_total", mileage_total);
		model.addAttribute("searchKey", "all");
		
		return ".my_page/mileage";
	}
	
	@GetMapping("/mileage_search_list")
	public String go_mileage_search_list(UserVO uvo ,Model model, HttpServletRequest request, String searchKey ) {
		
		log.info("mileage_search_list()...");
		
		log.info("검색 키워드: "+searchKey);
		log.info("UserVO(사용자 고유번호): {}", uvo);
		
		//총 마일리지 부분
		UserMileageVO umvo = service.user_mileage_selectOne(uvo);
		log.info("umvo: {}", umvo);

//		마일리지 콤마단위로 변환
		DecimalFormat dc = new DecimalFormat("###,###,###,###,###");
	
		String mileage_total = dc.format(umvo.getMileage_total());
		log.info("mileage_total: "+ mileage_total);
		
		List<UserMileageVO> vos = service.user_mileage_search_list(uvo,searchKey);
		log.info("vos: {}"+ vos);
		
		
		for (int i = 0; i < vos.size(); i++) {
			vos.get(i).setMileage(dc.format(Integer.parseInt(vos.get(i).getMileage())));
		}
		log.info("Type change vos: {}"+ vos);
	

		
		Map<String, List<UserMileageVO>> map2 = new HashMap<String, List<UserMileageVO>>();

		map2.put("list", vos);
		model.addAttribute("res", map2);
		model.addAttribute("mileage_total", mileage_total);
		model.addAttribute("searchKey", searchKey);
		
		return ".my_page/mileage";
	}
	
	/**
	 * 문의 리스트 페이지 이동
	 */
	@GetMapping("/go_question_list")
	public String go_question_list() {

		return ".my_page/question-list";
	}
	
	
	
	
	
	
}//end class
