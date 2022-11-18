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
import javax.persistence.Transient;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Immutable;

import com.rence.backoffice.model.BackOfficeVO;
import com.rence.office.model.ListViewVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Entity
@Immutable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="RESERVE_LIST_B_VIEW")
@Slf4j
public class ReserveListView {
	
	@Column(name="reserve_no")
	private String reserve_no;
	
	@Column(name="reserve_sdate", insertable= false, updatable = false)
	private String reserve_sdate;
	
	@Column(name="reserve_edate", insertable= false, updatable = false)
	private String reserve_edate;
	
	@Column(name="reserve_state")
	private String reserve_state;
	
//	@Transient
//	@Column(name="room_no")
//	private String room_no;
	
//	@Transient
//	@Column(name="payment_no")
//	private String payment_no;
//	
	@Column(name="user_no")
	private String user_no;
	
	@Column(name="backoffice_no")
	private String backoffice_no;
	
	@Column(name="room_name")
	private String room_name;
	
	@Id
	@Column(name="user_name")
	private String user_name;
	
	@Column(name="user_email")
	private String user_email;
	
	@Column(name="user_tel")
	private String user_tel;
	
	@Column(name="actual_payment")
	private String actual_payment;
	
	@Column(name="payment_state")
	private String payment_state;

}
