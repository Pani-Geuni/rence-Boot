/**
 * @author 전판근
 */

package com.rence.office.model;

public class PaymentInfoVO {
	
	private String reserve_no;	
	private String reserve_stime;
	private String reserve_etime;
	
	private String room_no;
	private String user_no;
	private String backoffice_no;
	
	private String room_name;
	private String room_type;
	private int room_price;
	
	private String user_name;
	private String user_tel;
	private String user_email;
	
	private String backoffice_image;
	private String company_name;
	private String owner_name;
	private String roadname_address;
	private String detail_address;
	private String backoffice_tel;
	private String backoffice_email;
	
	private String mileage_total;
	
	public PaymentInfoVO() {
		// TODO Auto-generated constructor stub
	}

	public PaymentInfoVO(String reserve_no, String reserve_stime, String reserve_etime, String room_no, String user_no,
			String backoffice_no, String room_name, String room_type, int room_price, String user_name, String user_tel,
			String user_email, String backoffice_image, String company_name, String owner_name, String roadname_address,
			String detail_address, String backoffice_tel, String backoffice_email, String mileage_total) {
		super();
		this.reserve_no = reserve_no;
		this.reserve_stime = reserve_stime;
		this.reserve_etime = reserve_etime;
		this.room_no = room_no;
		this.user_no = user_no;
		this.backoffice_no = backoffice_no;
		this.room_name = room_name;
		this.room_type = room_type;
		this.room_price = room_price;
		this.user_name = user_name;
		this.user_tel = user_tel;
		this.user_email = user_email;
		this.backoffice_image = backoffice_image;
		this.company_name = company_name;
		this.owner_name = owner_name;
		this.roadname_address = roadname_address;
		this.detail_address = detail_address;
		this.backoffice_tel = backoffice_tel;
		this.backoffice_email = backoffice_email;
		this.mileage_total = mileage_total;
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

	public String getRoom_name() {
		return room_name;
	}

	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}

	public String getRoom_type() {
		return room_type;
	}

	public void setRoom_type(String room_type) {
		this.room_type = room_type;
	}

	public int getRoom_price() {
		return room_price;
	}

	public void setRoom_price(int room_price) {
		this.room_price = room_price;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_tel() {
		return user_tel;
	}

	public void setUser_tel(String user_tel) {
		this.user_tel = user_tel;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getBackoffice_image() {
		return backoffice_image;
	}

	public void setBackoffice_image(String backoffice_image) {
		this.backoffice_image = backoffice_image;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getOwner_name() {
		return owner_name;
	}

	public void setOwner_name(String owner_name) {
		this.owner_name = owner_name;
	}

	public String getRoadname_address() {
		return roadname_address;
	}

	public void setRoadname_address(String roadname_address) {
		this.roadname_address = roadname_address;
	}

	public String getDetail_address() {
		return detail_address;
	}

	public void setDetail_address(String detail_address) {
		this.detail_address = detail_address;
	}

	public String getBackoffice_tel() {
		return backoffice_tel;
	}

	public void setBackoffice_tel(String backoffice_tel) {
		this.backoffice_tel = backoffice_tel;
	}

	public String getBackoffice_email() {
		return backoffice_email;
	}

	public void setBackoffice_email(String backoffice_email) {
		this.backoffice_email = backoffice_email;
	}

	public String getMileage_total() {
		return mileage_total;
	}

	public void setMileage_total(String mileage_total) {
		this.mileage_total = mileage_total;
	}

	@Override
	public String toString() {
		return "PaymentInfoVO [reserve_no=" + reserve_no + ", reserve_stime=" + reserve_stime + ", reserve_etime="
				+ reserve_etime + ", room_no=" + room_no + ", user_no=" + user_no + ", backoffice_no=" + backoffice_no
				+ ", room_name=" + room_name + ", room_type=" + room_type + ", room_price=" + room_price
				+ ", user_name=" + user_name + ", user_tel=" + user_tel + ", user_email=" + user_email
				+ ", backoffice_image=" + backoffice_image + ", company_name=" + company_name + ", owner_name="
				+ owner_name + ", roadname_address=" + roadname_address + ", detail_address=" + detail_address
				+ ", backoffice_tel=" + backoffice_tel + ", backoffice_email=" + backoffice_email + ", mileage_total="
				+ mileage_total + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((backoffice_email == null) ? 0 : backoffice_email.hashCode());
		result = prime * result + ((backoffice_image == null) ? 0 : backoffice_image.hashCode());
		result = prime * result + ((backoffice_no == null) ? 0 : backoffice_no.hashCode());
		result = prime * result + ((backoffice_tel == null) ? 0 : backoffice_tel.hashCode());
		result = prime * result + ((company_name == null) ? 0 : company_name.hashCode());
		result = prime * result + ((detail_address == null) ? 0 : detail_address.hashCode());
		result = prime * result + ((mileage_total == null) ? 0 : mileage_total.hashCode());
		result = prime * result + ((owner_name == null) ? 0 : owner_name.hashCode());
		result = prime * result + ((reserve_etime == null) ? 0 : reserve_etime.hashCode());
		result = prime * result + ((reserve_no == null) ? 0 : reserve_no.hashCode());
		result = prime * result + ((reserve_stime == null) ? 0 : reserve_stime.hashCode());
		result = prime * result + ((roadname_address == null) ? 0 : roadname_address.hashCode());
		result = prime * result + ((room_name == null) ? 0 : room_name.hashCode());
		result = prime * result + ((room_no == null) ? 0 : room_no.hashCode());
		result = prime * result + room_price;
		result = prime * result + ((room_type == null) ? 0 : room_type.hashCode());
		result = prime * result + ((user_email == null) ? 0 : user_email.hashCode());
		result = prime * result + ((user_name == null) ? 0 : user_name.hashCode());
		result = prime * result + ((user_no == null) ? 0 : user_no.hashCode());
		result = prime * result + ((user_tel == null) ? 0 : user_tel.hashCode());
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
		PaymentInfoVO other = (PaymentInfoVO) obj;
		if (backoffice_email == null) {
			if (other.backoffice_email != null)
				return false;
		} else if (!backoffice_email.equals(other.backoffice_email))
			return false;
		if (backoffice_image == null) {
			if (other.backoffice_image != null)
				return false;
		} else if (!backoffice_image.equals(other.backoffice_image))
			return false;
		if (backoffice_no == null) {
			if (other.backoffice_no != null)
				return false;
		} else if (!backoffice_no.equals(other.backoffice_no))
			return false;
		if (backoffice_tel == null) {
			if (other.backoffice_tel != null)
				return false;
		} else if (!backoffice_tel.equals(other.backoffice_tel))
			return false;
		if (company_name == null) {
			if (other.company_name != null)
				return false;
		} else if (!company_name.equals(other.company_name))
			return false;
		if (detail_address == null) {
			if (other.detail_address != null)
				return false;
		} else if (!detail_address.equals(other.detail_address))
			return false;
		if (mileage_total == null) {
			if (other.mileage_total != null)
				return false;
		} else if (!mileage_total.equals(other.mileage_total))
			return false;
		if (owner_name == null) {
			if (other.owner_name != null)
				return false;
		} else if (!owner_name.equals(other.owner_name))
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
		if (reserve_stime == null) {
			if (other.reserve_stime != null)
				return false;
		} else if (!reserve_stime.equals(other.reserve_stime))
			return false;
		if (roadname_address == null) {
			if (other.roadname_address != null)
				return false;
		} else if (!roadname_address.equals(other.roadname_address))
			return false;
		if (room_name == null) {
			if (other.room_name != null)
				return false;
		} else if (!room_name.equals(other.room_name))
			return false;
		if (room_no == null) {
			if (other.room_no != null)
				return false;
		} else if (!room_no.equals(other.room_no))
			return false;
		if (room_price != other.room_price)
			return false;
		if (room_type == null) {
			if (other.room_type != null)
				return false;
		} else if (!room_type.equals(other.room_type))
			return false;
		if (user_email == null) {
			if (other.user_email != null)
				return false;
		} else if (!user_email.equals(other.user_email))
			return false;
		if (user_name == null) {
			if (other.user_name != null)
				return false;
		} else if (!user_name.equals(other.user_name))
			return false;
		if (user_no == null) {
			if (other.user_no != null)
				return false;
		} else if (!user_no.equals(other.user_no))
			return false;
		if (user_tel == null) {
			if (other.user_tel != null)
				return false;
		} else if (!user_tel.equals(other.user_tel))
			return false;
		return true;
	}

	
	
}
