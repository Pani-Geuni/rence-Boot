package com.rence.dashboard.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

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
@Table(name="RESERVE_SUMMARY_VIEW")
@Slf4j
public class ReserveSummaryView implements Serializable{
	
	@Id
	@Column(name="reserve_no")
	private String reserve_no;
	
	@Column(name="reserve_sdate")
	private String reserve_sdate;
	
	@Column(name="reserve_edate")
	private String reserve_edate;
	
	@Column(name="room_name")
	private String room_name;
	
	@Column(name="user_name")
	private String user_name;
	
	@Column(name="actual_payment")
	private String actual_payment; //int
	
	@Column(name="reserve_state")
	private String reserve_state;
	

}
