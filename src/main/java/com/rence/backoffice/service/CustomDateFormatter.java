/**
 * @author 전판근
 */
package com.rence.backoffice.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rence.office.model.OfficeOperatingTimeVO;
import com.rence.office.model.OfficeOperatingTimeVO_date;

//import test.com.rence.master.MasterController;
//import test.com.rence.office.model.OfficeOperatingTimeVO;
//import test.com.rence.office.model.OfficeOperatingTimeVO_date;

public class CustomDateFormatter {
	private static final Logger logger = LoggerFactory.getLogger(CustomDateFormatter.class);
	
	public CustomDateFormatter() {
		// TODO Auto-generated constructor stub
	}
	
	// st = String Time 
	// dt = Date Time
	
	// String으로 받은 시간 값 (HH:mm 형식)을
	// Date로 형변환 시켜주는 함수.
	// 단, 이 함수는 HH:mm 형식만 됨.
	public Date hourFormmatter(String st) {
		
		SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		Date dt = null;	// simple date
		
		try {
			// String st를 Date dt로 변환.
			dt = df.parse(st);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dt;
	}
	
	public String makeStringHourFormatter(Date time) {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
		
		String operating_time = formatter.format(time); 
		
		return operating_time;
	}
	
	public OfficeOperatingTimeVO showStringOfficeOperating(OfficeOperatingTimeVO_date dotvo) {

		OfficeOperatingTimeVO otvo = new OfficeOperatingTimeVO();
		
		otvo.setMon_dayoff(dotvo.getMon_dayoff());
		otvo.setTue_dayoff(dotvo.getTue_dayoff());
		otvo.setWed_dayoff(dotvo.getWed_dayoff());
		otvo.setThu_dayoff(dotvo.getThu_dayoff());
		otvo.setFri_dayoff(dotvo.getFri_dayoff());
		otvo.setSat_dayoff(dotvo.getSat_dayoff());
		otvo.setSun_dayoff(dotvo.getSun_dayoff());
		
		if (dotvo.getSun_dayoff().equals("F")) {
			otvo.setSun_stime(makeStringHourFormatter(dotvo.getSun_stime()));
			otvo.setSun_etime(makeStringHourFormatter(dotvo.getSun_etime()));
		}
		if (dotvo.getMon_dayoff().equals("F")) {
			otvo.setMon_stime(makeStringHourFormatter(dotvo.getMon_stime()));
			otvo.setMon_etime(makeStringHourFormatter(dotvo.getMon_etime()));
		}
		if (dotvo.getTue_dayoff().equals("F")) {
			otvo.setTue_stime(makeStringHourFormatter(dotvo.getTue_stime()));
			otvo.setTue_etime(makeStringHourFormatter(dotvo.getTue_etime()));
		}
		if (dotvo.getWed_dayoff().equals("F")) {
			otvo.setWed_stime(makeStringHourFormatter(dotvo.getWed_stime()));
			otvo.setWed_etime(makeStringHourFormatter(dotvo.getWed_etime()));
		}
		if (dotvo.getThu_dayoff().equals("F")) {
			otvo.setThu_stime(makeStringHourFormatter(dotvo.getThu_stime()));
			otvo.setThu_etime(makeStringHourFormatter(dotvo.getThu_etime()));
		}
		if (dotvo.getFri_dayoff().equals("F")) {
			otvo.setFri_stime(makeStringHourFormatter(dotvo.getFri_stime()));
			otvo.setFri_etime(makeStringHourFormatter(dotvo.getFri_etime()));
		}
		if (dotvo.getSat_dayoff().equals("F")) {
			otvo.setSat_stime(makeStringHourFormatter(dotvo.getSat_stime()));
			otvo.setSat_etime(makeStringHourFormatter(dotvo.getSat_etime()));
		}
		
		return otvo;
	}
}

