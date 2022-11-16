/**
 * @author 전판근
 */

package com.rence.office.model;

import java.io.Serializable;

public class OfficeReserveVO implements Serializable {
	
	private String reserve_no;
	private String reserve_stime;
	private String reserve_etime;
	private String reserve_sdate;
	private String reserve_edate;
	
	private String reserve_state;
	private String room_no;
	private String user_no;
	private String backoffice_no;
	private String room_type;
	
	public OfficeReserveVO() {
		// TODO Auto-generated constructor stub
	}

	public OfficeReserveVO(String reserve_no, String reserve_stime, String reserve_etime, String reserve_sdate,
			String reserve_edate, String reserve_state, String room_no, String user_no, String backoffice_no,
			String room_type) {
		super();
		this.reserve_no = reserve_no;
		this.reserve_stime = reserve_stime;
		this.reserve_etime = reserve_etime;
		this.reserve_sdate = reserve_sdate;
		this.reserve_edate = reserve_edate;
		this.reserve_state = reserve_state;
		this.room_no = room_no;
		this.user_no = user_no;
		this.backoffice_no = backoffice_no;
		this.room_type = room_type;
	}

	public String getReserve_no() {
		return reserve_no;
	}

	public void setReserve_no(String reserve_no) {
		this.reserve_no = reserve_no;
	}

	public String getReserve_stime() {
		return reserve_stime;
	}

	public void setReserve_stime(String reserve_stime) {
		this.reserve_stime = reserve_stime;
	}

	public String getReserve_etime() {
		return reserve_etime;
	}

	public void setReserve_etime(String reserve_etime) {
		this.reserve_etime = reserve_etime;
	}

	public String getReserve_sdate() {
		return reserve_sdate;
	}

	public void setReserve_sdate(String reserve_sdate) {
		this.reserve_sdate = reserve_sdate;
	}

	public String getReserve_edate() {
		return reserve_edate;
	}

	public void setReserve_edate(String reserve_edate) {
		this.reserve_edate = reserve_edate;
	}

	public String getReserve_state() {
		return reserve_state;
	}

	public void setReserve_state(String reserve_state) {
		this.reserve_state = reserve_state;
	}

	public String getRoom_no() {
		return room_no;
	}

	public void setRoom_no(String room_no) {
		this.room_no = room_no;
	}

	public String getUser_no() {
		return user_no;
	}

	public void setUser_no(String user_no) {
		this.user_no = user_no;
	}

	public String getBackoffice_no() {
		return backoffice_no;
	}

	public void setBackoffice_no(String backoffice_no) {
		this.backoffice_no = backoffice_no;
	}

	public String getRoom_type() {
		return room_type;
	}

	public void setRoom_type(String room_type) {
		this.room_type = room_type;
	}

	@Override
	public String toString() {
		return "OfficeReserveVO [reserve_no=" + reserve_no + ", reserve_stime=" + reserve_stime + ", reserve_etime="
				+ reserve_etime + ", reserve_sdate=" + reserve_sdate + ", reserve_edate=" + reserve_edate
				+ ", reserve_state=" + reserve_state + ", room_no=" + room_no + ", user_no=" + user_no
				+ ", backoffice_no=" + backoffice_no + ", room_type=" + room_type + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((backoffice_no == null) ? 0 : backoffice_no.hashCode());
		result = prime * result + ((reserve_edate == null) ? 0 : reserve_edate.hashCode());
		result = prime * result + ((reserve_etime == null) ? 0 : reserve_etime.hashCode());
		result = prime * result + ((reserve_no == null) ? 0 : reserve_no.hashCode());
		result = prime * result + ((reserve_sdate == null) ? 0 : reserve_sdate.hashCode());
		result = prime * result + ((reserve_state == null) ? 0 : reserve_state.hashCode());
		result = prime * result + ((reserve_stime == null) ? 0 : reserve_stime.hashCode());
		result = prime * result + ((room_no == null) ? 0 : room_no.hashCode());
		result = prime * result + ((room_type == null) ? 0 : room_type.hashCode());
		result = prime * result + ((user_no == null) ? 0 : user_no.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OfficeReserveVO other = (OfficeReserveVO) obj;
		if (backoffice_no == null) {
			if (other.backoffice_no != null)
				return false;
		} else if (!backoffice_no.equals(other.backoffice_no))
			return false;
		if (reserve_edate == null) {
			if (other.reserve_edate != null)
				return false;
		} else if (!reserve_edate.equals(other.reserve_edate))
			return false;
		if (reserve_etime == null) {
			if (other.reserve_etime != null)
				return false;
		} else if (!reserve_etime.equals(other.reserve_etime))
			return false;
		if (reserve_no == null) {
			if (other.reserve_no != null)
				return false;
		} else if (!reserve_no.equals(other.reserve_no))
			return false;
		if (reserve_sdate == null) {
			if (other.reserve_sdate != null)
				return false;
		} else if (!reserve_sdate.equals(other.reserve_sdate))
			return false;
		if (reserve_state == null) {
			if (other.reserve_state != null)
				return false;
		} else if (!reserve_state.equals(other.reserve_state))
			return false;
		if (reserve_stime == null) {
			if (other.reserve_stime != null)
				return false;
		} else if (!reserve_stime.equals(other.reserve_stime))
			return false;
		if (room_no == null) {
			if (other.room_no != null)
				return false;
		} else if (!room_no.equals(other.room_no))
			return false;
		if (room_type == null) {
			if (other.room_type != null)
				return false;
		} else if (!room_type.equals(other.room_type))
			return false;
		if (user_no == null) {
			if (other.user_no != null)
				return false;
		} else if (!user_no.equals(other.user_no))
			return false;
		return true;
	}

	
	
	
}
