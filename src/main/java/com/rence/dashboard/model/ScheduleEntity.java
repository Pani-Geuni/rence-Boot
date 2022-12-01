/**
 * 
 * @author 최진실
 *
 */
package com.rence.dashboard.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="ROOMSCHEDULE")
public class ScheduleEntity implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_ROOMSCHEDULE")
	@SequenceGenerator(sequenceName = "SEQ_ROOMSCHEDULE",allocationSize = 1,name = "SEQ_ROOMSCHEDULE")
	@Column(name="schedule_no")
	private String schedule_no;
	
	@Column(name="not_stime")
	private Date not_stime;
	
	@Column(name="not_etime")
	private Date not_etime;
	
	@Column(name="room_no")
	private String room_no;
	
	@Column(name="backoffice_no")
	private String backoffice_no;
	
	@Transient
	@Column(name="room_name")
	private String room_name;
	@Transient
	@Column(name="sdate")
	private String sdate;
	@Transient
	@Column(name="edate")
	private String edate;
	@Transient
	@Column(name="stime")
	private String stime;
	@Transient
	@Column(name="etime")
	private String etime;
	@Transient
	@Column(name="schedule_type")
	private String schedule_type;
	
}
