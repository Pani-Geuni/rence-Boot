package com.rence.dashboard.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Immutable
@Table(name="COMMENTS_SUMMARY_VIEW")
@Slf4j
public class CommentSummaryView {

	@Id
	@Column(name="comment_no")
	private String comment_no;
	
	@Column(name="room_name")
	private String room_name;
	
	@Column(name="comment_date")
	private String comment_date;
	
	@Column(name="comment_content")
	private String comment_content;
	
}
