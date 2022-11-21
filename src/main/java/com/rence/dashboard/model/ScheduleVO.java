/**
 * 
 * @author 최진실
 *
 */
package com.rence.dashboard.model;

import java.io.Serializable;
import java.util.Objects;

public class ScheduleVO implements Serializable{

	private String schedule_no;
	private String not_sdate;
	private String not_edate;
	private String not_stime;
	private String not_etime;
	private String room_no;
	private String backoffice_no;
	
	public ScheduleVO() {}

	public ScheduleVO(String schedule_no, String not_sdate, String not_edate, String not_stime, String not_etime,
			String room_no, String backoffice_no) {
		super();
		this.schedule_no = schedule_no;
		this.not_sdate = not_sdate;
		this.not_edate = not_edate;
		this.not_stime = not_stime;
		this.not_etime = not_etime;
		this.room_no = room_no;
		this.backoffice_no = backoffice_no;
	}

	public String getSchedule_no() {
		return schedule_no;
	}

	public void setSchedule_no(String schedule_no) {
		this.schedule_no = schedule_no;
	}

	public String getNot_sdate() {
		return not_sdate;
	}

	public void setNot_sdate(String not_sdate) {
		this.not_sdate = not_sdate;
	}

	public String getNot_edate() {
		return not_edate;
	}

	public void setNot_edate(String not_edate) {
		this.not_edate = not_edate;
	}

	public String getNot_stime() {
		return not_stime;
	}

	public void setNot_stime(String not_stime) {
		this.not_stime = not_stime;
	}

	public String getNot_etime() {
		return not_etime;
	}

	public void setNot_etime(String not_etime) {
		this.not_etime = not_etime;
	}

	public String getRoom_no() {
		return room_no;
	}

	public void setRoom_no(String room_no) {
		this.room_no = room_no;
	}

	public String getBackoffice_no() {
		return backoffice_no;
	}

	public void setBackoffice_no(String backoffice_no) {
		this.backoffice_no = backoffice_no;
	}

	@Override
	public String toString() {
		return "ScheduleVO [schedule_no=" + schedule_no + ", not_sdate=" + not_sdate + ", not_edate=" + not_edate
				+ ", not_stime=" + not_stime + ", not_etime=" + not_etime + ", room_no=" + room_no + ", backoffice_no="
				+ backoffice_no + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(backoffice_no, not_edate, not_etime, not_sdate, not_stime, room_no, schedule_no);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScheduleVO other = (ScheduleVO) obj;
		return Objects.equals(backoffice_no, other.backoffice_no) && Objects.equals(not_edate, other.not_edate)
				&& Objects.equals(not_etime, other.not_etime) && Objects.equals(not_sdate, other.not_sdate)
				&& Objects.equals(not_stime, other.not_stime) && Objects.equals(room_no, other.room_no)
				&& Objects.equals(schedule_no, other.schedule_no);
	}
	
	
}
