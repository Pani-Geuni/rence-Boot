package com.rence.dashboard.model;

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
@Table(name="COMMENTLIST_Q_VIEW")
public class CommentListQView {

	@Id
	@Column(name="comment_no")
	private String comment_no;
	
	@Column(name="user_no")
	private String user_no;
	
	@Column(name="comment_content")
	private String comment_content;
	
	@Column(name="comment_date")
	private String comment_date;
	
	@Column(name="room_name")
	private String room_name;
	
	@Column(name="comment_state")
	private String comment_state;
	
	@Column(name="room_no")
	private String room_no;
	
	@Column(name="backoffice_no")
	private String backoffice_no;
	
	@Transient
	private String answer_no;
	
	@Transient
	private String answer_content;
	
	@Transient
	private String answer_date;
	

}
