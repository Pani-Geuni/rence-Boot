package com.rence.office.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Table(name="OFFICE_INFO_VIEW")
public class RoomScheduleVO {

	@Id
	@Column(name="schedule_no")
	private String schedule_no;
	
	@Column(name="not_sdate")
	private String not_sdate;
	
	@Column(name="not_edate")
	private String not_edate;
	
	@Column(name="not_stime")
	private String not_stime;
	
	@Column(name="not_etime")
	private String not_etime;
	
	@Column(name="room_no")
	private String room_no;
	
	@Column(name="backoffice_no")
	private String backoffice_no;
}
