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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.Gson;
import com.rence.user.model.UserMileageVO;
import com.rence.user.model.UserVO;
import com.rence.user.service.UserFileuploadService;
import com.rence.user.service.UserMypageSerivice;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(tags="마이페이지 컨트롤러")
@Slf4j
@Controller

public class MypageController {
	
	@Autowired
	UserMypageSerivice service;
	
	@Autowired
	UserFileuploadService fileuploadService;

	@Autowired
	HttpSession session;
	
	/**
	 * 현재예약리스트 이동
	 */
	@ApiOperation(value="현재예약리스트", notes="현재예약리스트 페이지입니다.")
	@GetMapping("/go_now_reserve")
	public String go_now_reserve(Model model) {

		model.addAttribute("content", "thymeleaf/html/office/my_page/reserve-list");
		model.addAttribute("title", "현재예약리스트");
		
		
		return "thymeleaf/layouts/office/layout_myPage";
	}
	/**
	 * 과거예약리스트 이동
	 */
	@ApiOperation(value="과거예약리스트", notes="과거예약리스트 페이지입니다.")
	@GetMapping("/go_before_reserve")
	public String go_before_reserve(Model model) {
		
		model.addAttribute("content", "thymeleaf/html/office/my_page/reserve-list");
		model.addAttribute("title", "과거예약리스트");

		
		return "thymeleaf/layouts/office/layout_myPage";
	}
	
	/**
	 * 마일리지 리스트 페이지 이동
	 */
	@ApiOperation(value="마일리지 리스트", notes="마일리지 리스트 페이지입니다.")
	@GetMapping("/go_mileage")
	public String go_mileage(UserVO uvo ,Model model, HttpServletRequest request) {
		log.info("go_mileage()...");
		log.info("UserVO(사용자 고유번호): {}", uvo);
		
		//총 마일리지 부분
		UserMileageVO umvo = service.user_mileage_selectOne(uvo);
		log.info("umvo: {}", umvo);

		//마일리지 콤마단위로 변환
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
		
		model.addAttribute("content", "thymeleaf/html/office/my_page/mileage");
		model.addAttribute("title", "마일리지리스트");

		
		return "thymeleaf/layouts/office/layout_myPage";
		
	}
	
	@ApiOperation(value="마일리지 조건리스트", notes="마일리지 조건리스트 페이지입니다.")
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
		
		model.addAttribute("content", "thymeleaf/html/office/my_page/mileage");
		model.addAttribute("title", "마일리지리스트");
		
		return "thymeleaf/layouts/office/layout_myPage";
	}
	
	/**
	 * 문의 리스트 페이지 이동
	 */
	@ApiOperation(value="문의 리스트 페이지", notes="문의 리스트 페이지입니다.")
	@GetMapping("/go_question_list")
	public String go_question_list(Model model) {
		log.info("go_question_list()...");
		
		model.addAttribute("content", "thymeleaf/html/office/my_page/question-list");
		model.addAttribute("title", "현재예약리스트");
		
		return "thymeleaf/layouts/office/layout_myPage";
	}
	
	/**
	 * 마이페이지 - 비밀번호 수정
	 */
	@ApiOperation(value="비밀번호 수정", notes="비밀번호 수정입니다.")
	@PostMapping("/user_pw_updateOK")
	@ResponseBody
	public String user_pw_upddateOK(UserVO uvo) {
		
		log.info("user_pw_upddateOK()...");
		log.info("result: {}", uvo);
		
		
		Gson gson = new Gson();
		Map<String, String> map = new HashMap<String, String>();

		int result = service.user_pw_updateOK(uvo);

		if (result == 1) {
			log.info("user_pw_upddate successed...");
			map.put("result", "1");
		}

		else {
			log.info("user_pw_upddate failed...");
			map.put("result", "0");
		}

		
		//분기결과 map gson으로 json변환
		String jsonObject = gson.toJson(map);
		
		return jsonObject;
	}
	
	
	/**
	 * 마이페이지 - 비밀번호 수정 - 현재 비밀번호 확인(본인인증)
	 */
	@ApiOperation(value="현재 비밀번호 확인(본인인증)", notes="현재 비밀번호 확인(본인인증) 입니다.")
	@PostMapping("/check_now_pw")
	@ResponseBody
	public String check_now_pw(UserVO uvo) {
		log.info("check_now_pw()...");
		log.info("request: {}", uvo);
		
		Gson gson = new Gson();
		Map<String, String> map = new HashMap<String, String>();

		int result = service.check_now_pw(uvo);

		
		if (result == 1) {
			log.info("right now pw...");
			map.put("result", "1");
		} else {
			log.info("not now pw...");
			map.put("result", "0");
		}

		String jsonObject = gson.toJson(map);
		
		return jsonObject;
	}
	
	/**
	 * 마이페이지 -프로필 수정
	 */
	
	@ApiOperation(value="프로필 수정", notes="프로필 수정 입니다.")
	@RequestMapping(value = "/user_img_updateOK", method = RequestMethod.POST)
	public String user_img_updateOK(Model model, UserVO uvo, HttpServletRequest request, HttpServletResponse response,
			MultipartHttpServletRequest mtfRequest, @RequestParam(value = "multipartFile_img") MultipartFile multipartFile) {
		log.info("user_img_updateOK()...");
		log.info("result: {}", uvo);
		
		//uvo.setUser_no("U1004");
		String user_no = null;
		Cookie[] cookies = request.getCookies();
		for (Cookie c : cookies) {
			if (c.getName().equals("user_no")) {
				user_no = c.getValue();
			}
		}
		uvo.setUser_no(user_no);
		log.info("==result==: {}", uvo);
		
		
		// 사진(파일)업로드
		uvo = fileuploadService.FileuploadOK(uvo, mtfRequest, multipartFile);
		log.info("fileresult: {}", uvo);
		
		Cookie cookie2 = new Cookie("user_image", uvo.getUser_image()); // 고유번호 쿠키 저장
		response.addCookie(cookie2);

		int result = service.user_img_updateOK(uvo);

		
		return "redirect:/go_my_page";
	}
	
	
	/**
	 * 회원탈퇴
	 */
	@ApiOperation(value="회원탈퇴", notes="회원탈퇴 입니다.")
	@RequestMapping(value = "/secedeOK", method = RequestMethod.POST)
	@ResponseBody
	public String user_secedeOK(UserVO uvo, HttpServletRequest request, HttpServletResponse response) {
		log.info("user_secedeOK()...");
		log.info("result: {}", uvo);

		
		Gson gson = new Gson();
		Map<String, String> map = new HashMap<String, String>();
	

		int result = service.user_secedeOK(uvo);
		log.info("result: {}", uvo);
		if (result == 1) {
			session.invalidate();
			log.info("user_secede successed...");
			map.put("result", "1");
			
			Cookie[] cookies = request.getCookies(); // 모든 쿠키의 정보를 cookies에 저장
			if (cookies != null) { // 쿠키가 한개라도 있으면 실행
				for (int i = 0; i < cookies.length; i++) {
					cookies[i].setMaxAge(0); // 유효시간을 0으로 설정
					response.addCookie(cookies[i]); // 응답 헤더에 추가
				}
			}
			
			
		} else {
			log.info("user_secede failed...");
			map.put("result", "0");
		}
		
		String jsonObject = gson.toJson(map);
		return jsonObject;
	}
	
	
	
	
	
	
	
	
}//end class
