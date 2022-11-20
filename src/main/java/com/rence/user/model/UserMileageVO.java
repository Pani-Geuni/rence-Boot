/**
 * @author 강경석
 * 
 */

package com.rence.user.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Immutable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Immutable
@AllArgsConstructor
@NoArgsConstructor
@Table(name="USER_DETAIL_MILEAGE_VIEW")
public class UserMileageVO {
	
	
	@Column(name="user_no")
	private String user_no; // 
	@Column(name="mileage_total")
	private Integer mileage_total;
	@Id
	@Column(name="no")
	private String no; // mileage_no
	@Column(name="state")
	private String state; //mileage_state
	@Column(name="mileage")
	private String mileage; //mileage_change
	@Column(name="room")
	private String room; //company_name;
	@Column(name="date")
	private Date date; // payment_date
	
}
