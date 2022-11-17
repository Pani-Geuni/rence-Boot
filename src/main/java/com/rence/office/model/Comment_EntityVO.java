/**
 * @author 전판근
 */

package com.rence.office.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.annotation.Immutable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Immutable
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Table(name="COMMENTS")
public class Comment_EntityVO {
	
	@Id	// PK 설정
	@Column(name="comment_no")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_comments")
	@SequenceGenerator(sequenceName = "seq_comments", allocationSize = 1, name = "seq_comments")
	String comment_no;
	
	@Column(name="mother_no")
	String mother_no;
	
	@Column(name="comment_content")
	String comment_content;
	
	@Column(name="comment_date")
	Date comment_date;
	
	@Column(name="room_no")
	String room_no;
	
	@Column(name="backoffice_no")
	String backoffice_no;
	
	@Column(name="user_no")
	String user_no;
	
	@Column(name="host_no")
	String host_no;
	
	@Column(name="comment_state")
	String comment_state;
	
	@Column(name="writer")
	String writer;
	
}