/**
 * @author 전판근
 */

package com.rence.office.model;

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
@Table(name="mileage")
public class OfficeMileageVO {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mileage")
	@SequenceGenerator(sequenceName = "seq_mileage", allocationSize = 1, name="seq_mileage")
	@Column(name="mileage_no")
	private String mileage_no;
	
	@Column(name="mileage_total")
	private int mileage_total;
	
	@Column(name="mileage_state")
	private String mileage_state;
	
	@Column(name="user_no")
	private String user_no;
	
	@Column(name="mileage_change")
	private int mileage_change;
	
	@Column(name="payment_no")
	private String payment_no;
	
	
}
