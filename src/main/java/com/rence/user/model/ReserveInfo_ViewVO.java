/**
 * @author 김예은
*/
package com.rence.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Immutable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Immutable
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Table(name="ReserveInfoView")
public class ReserveInfo_ViewVO {
	
	@Id	// PK 설정
	@Column(name="reserve_no")
	String reserve_no;
	
	@Column(name="backoffice_no")
	String backoffice_no;
	
	@Column(name="company_name")
	String company_name;
	
	@Column(name="room_no")
	String room_no;
	
	@Column(name="room_type")
	String room_type;
	
	@Column(name="reserve_sdate")
	String reserve_sdate;
	
	@Column(name="reserve_edate")
	String reserve_edate;
	
	@Column(name="room_name")
	String room_name;
	
	@Column(name="room_price")
	String room_price;
	
	@Column(name="backoffice_name")
	String backoffice_name;
	
	@Column(name="backoffice_image")
	String backoffice_image;
	
	@Column(name="full_address")
	String full_address;
	
	@Column(name="backoffice_tel")
	String backoffice_tel;
	
	@Column(name="backoffice_email")
	String backoffice_email;
	
	@Column(name="payment_total")
	String payment_total;
	
	@Column(name="actual_payment")
	String actual_payment;
	
	@Column(name="mileage_change")
	String mileage_change;
	
}
