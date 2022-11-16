/**
 * @author 전판근
 */

package com.rence.office.model;

import java.io.Serializable;
import java.util.Date;

public class OfficePaymentVO implements Serializable {

	private String payment_no;
	private int payment_total;
	private int use_mileage;
	private int actual_payment;
	private String payment_state;
	private Date payment_date;
	private String room_no;
	private String user_no;
	private String reserve_no;
	private String sales_state;
	private String backoffice_no;
	private String payment_method;
	
	public OfficePaymentVO() {
		// TODO Auto-generated constructor stub
	}

	public OfficePaymentVO(String payment_no, int payment_total, int use_mileage, int actual_payment,
			String payment_state, Date payment_date, String room_no, String user_no, String reserve_no,
			String sales_state, String backoffice_no, String payment_method) {
		super();
		this.payment_no = payment_no;
		this.payment_total = payment_total;
		this.use_mileage = use_mileage;
		this.actual_payment = actual_payment;
		this.payment_state = payment_state;
		this.payment_date = payment_date;
		this.room_no = room_no;
		this.user_no = user_no;
		this.reserve_no = reserve_no;
		this.sales_state = sales_state;
		this.backoffice_no = backoffice_no;
		this.payment_method = payment_method;
	}

	public String getPayment_no() {
		return payment_no;
	}

	public void setPayment_no(String payment_no) {
		this.payment_no = payment_no;
	}

	public int getPayment_total() {
		return payment_total;
	}

	public void setPayment_total(int payment_total) {
		this.payment_total = payment_total;
	}

	public int getUse_mileage() {
		return use_mileage;
	}

	public void setUse_mileage(int use_mileage) {
		this.use_mileage = use_mileage;
	}

	public int getActual_payment() {
		return actual_payment;
	}

	public void setActual_payment(int actual_payment) {
		this.actual_payment = actual_payment;
	}

	public String getPayment_state() {
		return payment_state;
	}

	public void setPayment_state(String payment_state) {
		this.payment_state = payment_state;
	}

	public Date getPayment_date() {
		return payment_date;
	}

	public void setPayment_date(Date payment_date) {
		this.payment_date = payment_date;
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

	public String getReserve_no() {
		return reserve_no;
	}

	public void setReserve_no(String reserve_no) {
		this.reserve_no = reserve_no;
	}

	public String getSales_state() {
		return sales_state;
	}

	public void setSales_state(String sales_state) {
		this.sales_state = sales_state;
	}

	public String getBackoffice_no() {
		return backoffice_no;
	}

	public void setBackoffice_no(String backoffice_no) {
		this.backoffice_no = backoffice_no;
	}

	public String getPayment_method() {
		return payment_method;
	}

	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}

	@Override
	public String toString() {
		return "OfficePaymentVO [payment_no=" + payment_no + ", payment_total=" + payment_total + ", use_mileage="
				+ use_mileage + ", actual_payment=" + actual_payment + ", payment_state=" + payment_state
				+ ", payment_date=" + payment_date + ", room_no=" + room_no + ", user_no=" + user_no + ", reserve_no="
				+ reserve_no + ", sales_state=" + sales_state + ", backoffice_no=" + backoffice_no + ", payment_method="
				+ payment_method + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + actual_payment;
		result = prime * result + ((backoffice_no == null) ? 0 : backoffice_no.hashCode());
		result = prime * result + ((payment_date == null) ? 0 : payment_date.hashCode());
		result = prime * result + ((payment_method == null) ? 0 : payment_method.hashCode());
		result = prime * result + ((payment_no == null) ? 0 : payment_no.hashCode());
		result = prime * result + ((payment_state == null) ? 0 : payment_state.hashCode());
		result = prime * result + payment_total;
		result = prime * result + ((reserve_no == null) ? 0 : reserve_no.hashCode());
		result = prime * result + ((room_no == null) ? 0 : room_no.hashCode());
		result = prime * result + ((sales_state == null) ? 0 : sales_state.hashCode());
		result = prime * result + use_mileage;
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
		OfficePaymentVO other = (OfficePaymentVO) obj;
		if (actual_payment != other.actual_payment)
			return false;
		if (backoffice_no == null) {
			if (other.backoffice_no != null)
				return false;
		} else if (!backoffice_no.equals(other.backoffice_no))
			return false;
		if (payment_date == null) {
			if (other.payment_date != null)
				return false;
		} else if (!payment_date.equals(other.payment_date))
			return false;
		if (payment_method == null) {
			if (other.payment_method != null)
				return false;
		} else if (!payment_method.equals(other.payment_method))
			return false;
		if (payment_no == null) {
			if (other.payment_no != null)
				return false;
		} else if (!payment_no.equals(other.payment_no))
			return false;
		if (payment_state == null) {
			if (other.payment_state != null)
				return false;
		} else if (!payment_state.equals(other.payment_state))
			return false;
		if (payment_total != other.payment_total)
			return false;
		if (reserve_no == null) {
			if (other.reserve_no != null)
				return false;
		} else if (!reserve_no.equals(other.reserve_no))
			return false;
		if (room_no == null) {
			if (other.room_no != null)
				return false;
		} else if (!room_no.equals(other.room_no))
			return false;
		if (sales_state == null) {
			if (other.sales_state != null)
				return false;
		} else if (!sales_state.equals(other.sales_state))
			return false;
		if (use_mileage != other.use_mileage)
			return false;
		if (user_no == null) {
			if (other.user_no != null)
				return false;
		} else if (!user_no.equals(other.user_no))
			return false;
		return true;
	}
	
	
}
