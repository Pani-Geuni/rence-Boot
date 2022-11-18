package com.rence.dashboard.model;

import java.io.Serializable;
import java.util.Date;

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
@Table(name="ROOMINFO")
public class RoomInsertVO implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_ROOM")
	@SequenceGenerator(sequenceName = "SEQ_ROOM",allocationSize = 1,name = "SEQ_ROOM")
	@Column(name="room_no")
	private String room_no;
	
	@Column(name="room_type")
	private String room_type;
	
	@Column(name="room_name")
	private String room_name;
	
	@Column(name="room_price")
	private Integer room_price;

	
	@Column(name="backoffice_no")
	private String backoffice_no;
}
