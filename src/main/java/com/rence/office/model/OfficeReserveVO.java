/**
 * @author 전판근
 */

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
@Table(name="RESERVEINFO")
public class OfficeReserveVO {
	
	@Id
	@Column(name="reserve_no")
	private String reserve_no;
	
	@Column(name="reserve_stime")
	private String reserve_stime;
	
	@Column(name="reserve_etime")
	private String reserve_etime;
	
	@Column(name="reserve_sdate")
	private String reserve_sdate;
	
	@Column(name="reserve_edate")
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
	
	
}
