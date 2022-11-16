/**
 * @author 전판근
 */

package com.rence.office.model;

import java.io.Serializable;

public class OfficeMileageVO implements Serializable {

	private String mileage_no;
	private int mileage_total;
	private String mileage_state;
	private String user_no;
	
	private int mileage_change;
	private String payment_no;
	
	public OfficeMileageVO() {
		// TODO Auto-generated constructor stub
	}

	public OfficeMileageVO(String mileage_no, int mileage_total, String mileage_state, String user_no,
			int mileage_change, String payment_no) {
		super();
		this.mileage_no = mileage_no;
		this.mileage_total = mileage_total;
		this.mileage_state = mileage_state;
		this.user_no = user_no;
		this.mileage_change = mileage_change;
		this.payment_no = payment_no;
	}

	public String getMileage_no() {
		return mileage_no;
	}

	public void setMileage_no(String mileage_no) {
		this.mileage_no = mileage_no;
	}

	public int getMileage_total() {
		return mileage_total;
	}

	public void setMileage_total(int mileage_total) {
		this.mileage_total = mileage_total;
	}

	public String getMileage_state() {
		return mileage_state;
	}

	public void setMileage_state(String mileage_state) {
		this.mileage_state = mileage_state;
	}

	public String getUser_no() {
		return user_no;
	}

	public void setUser_no(String user_no) {
		this.user_no = user_no;
	}

	public int getMileage_change() {
		return mileage_change;
	}

	public void setMileage_change(int mileage_change) {
		this.mileage_change = mileage_change;
	}

	public String getPayment_no() {
		return payment_no;
	}

	public void setPayment_no(String payment_no) {
		this.payment_no = payment_no;
	}

	@Override
	public String toString() {
		return "OfficeMileageVO [mileage_no=" + mileage_no + ", mileage_total=" + mileage_total + ", mileage_state="
				+ mileage_state + ", user_no=" + user_no + ", mileage_change=" + mileage_change + ", payment_no="
				+ payment_no + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + mileage_change;
		result = prime * result + ((mileage_no == null) ? 0 : mileage_no.hashCode());
		result = prime * result + ((mileage_state == null) ? 0 : mileage_state.hashCode());
		result = prime * result + mileage_total;
		result = prime * result + ((payment_no == null) ? 0 : payment_no.hashCode());
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
		OfficeMileageVO other = (OfficeMileageVO) obj;
		if (mileage_change != other.mileage_change)
			return false;
		if (mileage_no == null) {
			if (other.mileage_no != null)
				return false;
		} else if (!mileage_no.equals(other.mileage_no))
			return false;
		if (mileage_state == null) {
			if (other.mileage_state != null)
				return false;
		} else if (!mileage_state.equals(other.mileage_state))
			return false;
		if (mileage_total != other.mileage_total)
			return false;
		if (payment_no == null) {
			if (other.payment_no != null)
				return false;
		} else if (!payment_no.equals(other.payment_no))
			return false;
		if (user_no == null) {
			if (other.user_no != null)
				return false;
		} else if (!user_no.equals(other.user_no))
			return false;
		return true;
	}
	
	
	
	
}
