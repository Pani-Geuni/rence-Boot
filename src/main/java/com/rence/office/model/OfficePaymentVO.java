/**
 * @author 전판근
 */

package com.rence.office.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
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
@Table(name="paymentinfo")
public class OfficePaymentVO {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_payment")
	@SequenceGenerator(sequenceName = "seq_payment", allocationSize = 1, name="seq_payment")
	@Column(name="payment_no")
	private String payment_no;
	
	@Column(name="payment_total")
	private int payment_total;
	
	@Column(name="use_mileage")
	private int use_mileage;
	
	@Column(name="actual_payment")
	private int actual_payment;
	
	@Column(name="payment_state")
	private String payment_state;
	
	@Column(name="payment_date")
	private Date payment_date;
	
	@Column(name="room_no")
	private String room_no;
	
	@Column(name="user_no")
	private String user_no;
	
	@Column(name="reserve_no")
	private String reserve_no;
	
	@Column(name="sales_state")
	private String sales_state;
	
	@Column(name="backoffice_no")
	private String backoffice_no;
	
	@Column(name="payment_method")
	private String payment_method;
	
	@Column(name="cancel_state")
	private String cancel_state;
	
	@Column(name="cancel_amount")
	private String cancel_amount;
	
	@Column(name="imp_uid")
	private String imp_uid;
}
