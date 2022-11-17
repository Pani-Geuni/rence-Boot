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

import com.rence.backoffice.model.BackOfficeVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="RESERVEINFO")
@Slf4j
public class ReserveVO {


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_RESERVE")
	@SequenceGenerator(sequenceName = "SEQ_RESERVE",allocationSize = 1,name = "SEQ_RESERVE")
	@Column(name="reserve_no")
	private String reserve_no;
	
//	@Column(name="reserve_stime", insertable= false, updatable = false)
//	private String reserve_stime;
//	
//	@Column(name="reserve_etime", insertable= false, updatable = false)
//	private String reserve_etime;
	
	@Column(name="reserve_sdate", insertable= false, updatable = false)
	private String reserve_sdate;
	
	@Column(name="reserve_edate", insertable= false, updatable = false)
	private String reserve_edate;
	
	@Column(name="reserve_state")
	private String reserve_state;
	
	@Column(name="room_no")
	private String room_no;
	
	@Column(name="user_no")
	private String user_no;
	
	@Column(name="backoffice_no")
	private String backoffice_no;
	
	@Column(name="room_type")
	private String room_type;
	
	@Transient
	private String room_name;
	@Transient
	private String user_name;
	@Transient
	private String user_email;
	@Transient
	private String user_tel;
	@Transient
	private String actual_payment;
	@Transient
	private String payment_state;
//	@Transient
//	private String reserve_state;
}
