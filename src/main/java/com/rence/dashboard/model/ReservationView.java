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
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Immutable
@Table(name="RESERVATION_VIEW")
public class ReservationView implements Serializable{
	
	@Id
	@Column(name="user_no")
	private String user_no;
	
	@Column(name="user_name")
	private String user_name;
	
	@Column(name="user_email")
	private String user_email;
	
	@Column(name="user_tel")
	private String user_tel;
	
	@Column(name="reserve_stime")
	private String reserve_stime;
	
	@Column(name="reserve_etime")
	private String reserve_etime;
	
	@Column(name="backoffice_no")
	private String backoffice_no;
	
	@Column(name="room_no")
	private String room_no;
	
	@Column(name="reserve_no")
	private String reserve_no;

}
