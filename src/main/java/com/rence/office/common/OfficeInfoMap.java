/**
 * @author 전판근
 */

package com.rence.office.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OfficeInfoMap {
	Map<String, String> type_map = new HashMap<String, String>();
	Map<String, String> option_map = new HashMap<String, String>();
	Map<String, String> around_option_map = new HashMap<String, String>();
	
	public OfficeInfoMap() {
		
		// BackOffice Type Map
		type_map.put("desk", "데스크");
		type_map.put("meeting_room", "미팅룸");
		type_map.put("meeting_04", "4인 미팅룸");
		type_map.put("meeting_06", "6인 미팅룸");
		type_map.put("meeting_10", "10인 미팅룸");
		type_map.put("office", "오피스");
		
		// BackOffice Option Map
		option_map.put("chair-desk", "의자/테이블");
		option_map.put("internet-wifi", "인터넷/WI-FI");
		option_map.put("restroom", "내부 화장실");
		option_map.put("no-smoking", "금연");
		option_map.put("smoking-room", "흡연실");
		option_map.put("terrace-rooftop", "테라스/루프탑");
		option_map.put("tv-projector", "TV/프로젝터");
		option_map.put("whiteboard", "화이트보드");
		option_map.put("sound-microphone", "음향/마이크");
		option_map.put("parking", "주차");
		option_map.put("pc-laptop", "PC/노트북");
		option_map.put("printer", "복사/인쇄기");
		option_map.put("lounge", "공용 라운지");
		option_map.put("kitchen", "공용 주방");
		option_map.put("water-purifier", "정수기");
		option_map.put("can-food", "음식물 반입 가능");
		option_map.put("can-drink", "주류 반입 가능");
		option_map.put("air-conditioner", "에어컨");
		option_map.put("fitting-room", "탈의실");
		option_map.put("shower", "샤워시설");
		option_map.put("body-mirror", "전신거울");
		option_map.put("door-lock", "도어락");
		option_map.put("outlet-multitap", "콘센트/멀티탭");
		option_map.put("personal-locker", "개인락커");
		option_map.put("fax", "팩스");
		
		// BackOffice Nearby Option Map
		around_option_map.put("public-parking", "공영 주차장");
		around_option_map.put("paid-parking", "유료 주차장");
		around_option_map.put("pharmacy", "약국");
		around_option_map.put("hospital", "병원");
		around_option_map.put("convenience-store", "편의점");
		around_option_map.put("cafe", "카페");
	}
	
	// 타입
	public String changeType(String type) {
		return type_map.get(type);
	}
	
	public List<String> splitType(String types) {
		List<String> type_list = new ArrayList<String>();
		String[] type_split = types.split(",");
		
		for (int i=0; i < type_split.length; i++) {
			type_list.add(type_map.get(type_split[i]));
		}
		
		return type_list;
	}
	
	// 태그
	public List<String> splitTag(String tags) {
		List<String> tag_list = new ArrayList<String>();
		String[] tag_split = tags.split(",");
		
		for (int i = 0; i < tag_split.length; i++) {
			tag_list.add(tag_split[i]);
		}
		
		return tag_list;
	}
	
	// 옵션
	public List<String> splitOption(String options) {
		List<String> option_list = new ArrayList<String>();
		String[] option_split = options.split(",");
		
		for (int i = 0; i < option_split.length; i++) {
			option_list.add(option_map.get(option_split[i]));
		}
		
		return option_list;
	}
	
	// 공간 사진
		public List<String> splitImage(String images) {
			List<String> option_list = new ArrayList<String>();
			String[] image_split = images.split(",");
			
			for (int i = 0; i < image_split.length; i++) {
				option_list.add(image_split[i]);
			}
			
			return option_list;
		}
	
	// 주변 시설
	public List<String> splitAroundOption(String options) {
		List<String> option_list = new ArrayList<String>();
		String[] option_split = options.split(",");
		
		for (int i = 0; i < option_split.length; i++) {
			option_list.add(around_option_map.get(option_split[i]));
		}
		
		return option_list;
	}
	
	// 짧은 주소
	public String makeShortAddress(String address) {
		String short_address;
		
		String[] address_split = address.split(" ");
		
		if (address_split.length > 1) {
			short_address = address_split[0] + " " + address_split[1];			
		} else {
			short_address = address;
		}
		
		return short_address;
	}
	
	
}
