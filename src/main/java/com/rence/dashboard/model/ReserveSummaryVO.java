package com.rence.dashboard.model;

import java.io.Serializable;
import java.util.Date;

public class ReserveSummaryVO implements Serializable{
	
	private String reserve_no;
	private String reserve_sdate;
	private String reserve_edate;
	private String room_name;
	private String user_name;
	private int actual_payment;
	private String reserve_state;
	
	public ReserveSummaryVO() {}

	public ReserveSummaryVO(String reserve_no, String reserve_sdate, String reserve_edate, String room_name,
			String user_name, int actual_payment, String reserve_state) {
		super();
		this.reserve_no = reserve_no;
		this.reserve_sdate = reserve_sdate;
		this.reserve_edate = reserve_edate;
		this.room_name = room_name;
		this.user_name = user_name;
		this.actual_payment = actual_payment;
		this.reserve_state = reserve_state;
	}

	public String getReserve_no() {
		return reserve_no;
	}

	public void setReserve_no(String reserve_no) {
		this.reserve_no = reserve_no;
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

	public String getRoom_name() {
		return room_name;
	}

	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public int getActual_payment() {
		return actual_payment;
	}

	public void setActual_payment(int actual_payment) {
		this.actual_payment = actual_payment;
	}

	public String getReserve_state() {
		return reserve_state;
	}

	public void setReserve_state(String reserve_state) {
		this.reserve_state = reserve_state;
	}

	@Override
	public String toString() {
		return "ReserveSummaryVO [reserve_no=" + reserve_no + ", reserve_sdate=" + reserve_sdate + ", reserve_edate="
				+ reserve_edate + ", room_name=" + room_name + ", user_name=" + user_name + ", actual_payment="
				+ actual_payment + ", reserve_state=" + reserve_state + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + actual_payment;
		result = prime * result + ((reserve_edate == null) ? 0 : reserve_edate.hashCode());
		result = prime * result + ((reserve_no == null) ? 0 : reserve_no.hashCode());
		result = prime * result + ((reserve_sdate == null) ? 0 : reserve_sdate.hashCode());
		result = prime * result + ((reserve_state == null) ? 0 : reserve_state.hashCode());
		result = prime * result + ((room_name == null) ? 0 : room_name.hashCode());
		result = prime * result + ((user_name == null) ? 0 : user_name.hashCode());
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
		ReserveSummaryVO other = (ReserveSummaryVO) obj;
		if (actual_payment != other.actual_payment)
			return false;
		if (reserve_edate == null) {
			if (other.reserve_edate != null)
				return false;
		} else if (!reserve_edate.equals(other.reserve_edate))
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
		if (room_name == null) {
			if (other.room_name != null)
				return false;
		} else if (!room_name.equals(other.room_name))
			return false;
		if (user_name == null) {
			if (other.user_name != null)
				return false;
		} else if (!user_name.equals(other.user_name))
			return false;
		return true;
	}
	
}
