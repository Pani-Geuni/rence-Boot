/**
 * 
 * @author 최진실
 *
 */
package com.rence.dashboard.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="PAYMENTINFO")
public class BOPaymentVO {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_PAYMENT")
	@SequenceGenerator(sequenceName = "SEQ_PAYMENT",allocationSize = 1,name = "SEQ_PAYMENT")
	@Column(name="payment_no")
	private String payment_no;
	
	@Column(name="payment_total")
	private int payment_total;
	
	@Column(name="add_mileage")
	private int add_mileage;
	
	@Column(name="use_mileage")
	private int use_mileage;
	
	@Column(name="actual_payment")
	private String actual_payment;
	
	@Column(name="payment_state")
	private String payment_state;
	
	@Column(name="payment_date")
	private Date payment_date;
	
	@Column(name="room_no")
	private String room_no;
	
	@Column(name="user_no")
	private String user_no;
	
	@Column(name="mileage_no")
	private String mileage_no;
	
	@Column(name="reserve_no")
	private String reserve_no;
	
	@Column(name="sales_state")
	private String sales_state;
	
	@Column(name="backoffice_no")
	private String backoffice_no;
}
