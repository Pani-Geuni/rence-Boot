/**
 * @author 전판근
 */

package com.rence.office.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="payment_view")
public class PaymentInfoVO {
	
	@Id
	@Column(name="reserve_no")
	private String reserve_no;
	
	@Column(name="reserve_stime")
	private String reserve_stime;
	
	@Column(name="reserve_etime")
	private String reserve_etime;
	
	@Column(name="room_no")
	private String room_no;
	
	@Column(name="user_no")
	private String user_no;
	
	@Column(name="backoffice_no")
	private String backoffice_no;
	
	@Column(name="room_name")
	private String room_name;
	
	@Column(name="room_type")
	private String room_type;
	
	@Column(name="room_price")
	private int room_price;
	
	@Column(name="user_name")
	private String user_name;
	
	@Column(name="user_tel")
	private String user_tel;
	
	@Column(name="user_email")
	private String user_email;
	
	@Column(name="backoffice_image")
	private String backoffice_image;
	
	@Column(name="company_name")
	private String company_name;
	
	@Column(name="owner_name")
	private String owner_name;
	
	@Column(name="roadname_address")
	private String roadname_address;
	
	@Column(name="detail_address")
	private String detail_address;
	
	@Column(name="backoffice_tel")
	private String backoffice_tel;
	
	@Column(name="backoffice_email")
	private String backoffice_email;
	
	@Column(name="mileage_total")
	private String mileage_total;
	
	
	
}
