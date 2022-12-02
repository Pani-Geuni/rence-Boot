/**
 * @author 강경석
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
@Table(name="reserve_info_payment")
public class ReserveMileageVO {

	@Id
	@Column(name="reserve_no")
	private String reserve_no;
	
	@Column(name="payment_total")
	private String payment_total;
	
	@Column(name="use_mileage")
	private String use_mileage;
	
	@Column(name="actual_payment")
	private String actual_payment;
	
	@Column(name="mileage_state")
	private String mileage_state;
	
	@Column(name="mileage_change")
	private String mileage_change;
	
	@Column(name="mileage_no")
	private String mileage_no;
}
