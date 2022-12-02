/**
 * 
 * @author 최진실
 *
 */
package com.rence.dashboard.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Immutable
@Table(name="SALESSETTLEMENT_LIST_VIEW")
public class SalesSettlementViewVO {

	@Column(name="reserve_sdate")
	private String reserve_sdate;
	
	@Column(name="reserve_edate")
	private String reserve_edate;
	
	@Column(name="room_name")
	private String room_name;
	
	@Column(name="actual_payment")
	private String actual_payment;
	
	@Column(name="payment_state")
	private String payment_state;
	
	@Column(name="sales_state")
	private String sales_state;
	
	@Id
	@Column(name="payment_no")
	private String payment_no;
	
	@Column(name="room_no")
	private String room_no;
}
