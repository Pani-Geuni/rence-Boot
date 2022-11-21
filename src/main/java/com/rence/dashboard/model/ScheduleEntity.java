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
	
	@Column(name="not_sdate")
	private Date not_sdate;
	
	@Column(name="not_edate")
	private Date not_edate;
	
	@Column(name="not_stime")
	private Date not_stime;
	
	@Column(name="not_etime")
	private Date not_etime;
	
	@Column(name="room_no")
	private String room_no;
	
	@Column(name="backoffice_no")
	private String backoffice_no;
	
}
