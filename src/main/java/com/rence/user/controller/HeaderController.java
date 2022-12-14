/**
 * @author 강경석, 김예은
 */

package com.rence.user.controller;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rence.office.model.ListViewVO;
import com.rence.office.service.OfficeService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@Api(tags="공통 컨트롤러")
@RequestMapping("/common")
public class HeaderController {
	
	@Autowired
	OfficeService office_service;


	/**
	 * 서치바 검색
	 */
	@RequestMapping(value = "/search_list", method = RequestMethod.GET)
	public String search_list(String type, String location, String searchWord, String condition, Integer page, Model model) {
		log.info("search_list()...");

		Map<String, Object> map = new HashMap<String, Object>();
		
		int total_cnt = office_service.search_list_totalCnt(type, location, searchWord);
		log.info("total_cnt : {}", total_cnt);

		List<ListViewVO> list = null;
		list = office_service.search_list(type, location, searchWord, condition, 9 * (page - 1) + 1, 9 * (page));

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

		map.put("condition", condition);
		map.put("page", "list_page");
		map.put("list", list);
		map.put("nowCnt", 1);

		if(total_cnt > 0)
			map.put("maxCnt", total_cnt);
		else
			map.put("maxCnt", 0);
		
		model.addAttribute("res", map);
		
		model.addAttribute("content", "thymeleaf/html/office/list");

		return "thymeleaf/layouts/office/layout_base";
	}

}// end class
