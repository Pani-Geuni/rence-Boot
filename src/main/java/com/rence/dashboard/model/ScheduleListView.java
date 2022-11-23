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

import org.hibernate.annotations.Immutable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Immutable
@Table(name="SCHEDULE_LIST_VIEW")
public class ScheduleListView implements Serializable{

	@Id
	@Column(name="room_no")
	private String room_no;
	
	@Column(name="backoffice_no")
	private String backoffice_no;
	
	@Column(name="room_type")
	private String room_type;
	
	@Column(name="room_name")
	private String room_name;
	
	@Column(name="reserve_cnt")
	private Integer reserve_cnt;
	
	@Transient
	@Column(name="reserve_stime")
	private Date reserve_stime;
	
	@Transient
	@Column(name="reserve_etime")
	private Date reserve_etime;
	
	@Transient
	@Column(name="reserve_is")
	private String reserve_is;
	
}
