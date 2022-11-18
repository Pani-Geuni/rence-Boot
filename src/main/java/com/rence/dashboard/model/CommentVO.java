package com.rence.dashboard.model;

import java.io.Serializable;

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
import lombok.extern.slf4j.Slf4j;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Table(name="RESERVEINFO")
@Table(name="COMMENTS")
@Slf4j
public class CommentVO implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_COMMENTS")
	@SequenceGenerator(sequenceName = "SEQ_COMMENTS",allocationSize = 1,name = "SEQ_COMMENTS")
	@Column(name="comment_no")
	private String comment_no;
	
	@Column(name="mother_no")
	private String mother_no;
	
	@Column(name="comment_state")
	private String comment_state;
	
	@Column(name="room_name")
	private String room_name;
	
	@Column(name="comment_content")
	private String comment_content;
	
	@Column(name="comment_date")
	private String comment_date;
	
	@Column(name="room_no")
	private String room_no;
	
	@Column(name="backoffice_no")
	private String backoffice_no;
	
	@Column(name="user_no")
	private String user_no;
	
	@Column(name="host_no")
	private String host_no;
	
	@Column(name="writer")
	private String writer;
}
