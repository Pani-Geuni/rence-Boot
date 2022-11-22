/**
 * @author 전판근
 */

package com.rence.office.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

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
@Table(name="question_list_view")
public class OfficeQuestionVO {

	@Id
	@Column(name="comment_no")
	private String comment_no;
	
	@Column(name="mother_no")
	private String mother_no;
	
	@Column(name="host_no")
	private String host_no;
	
	@Column(name="backoffice_no")
	private String backoffice_no;
	
	@Column(name="comment_state")
	private String comment_state;
	
	@Column(name="comment_content")
	private String comment_content;
	
	@Column(name="comment_date")
	private String comment_date;
	
	@Column(name="user_no")
	private String user_no;
	
	@Column(name="user_name")
	private String user_name;
	
	@Column(name="user_image")
	private String user_image;
	
	@Column(name="room_no")
	private String room_no;
	
	@Column(name="room_name")
	private String room_name;
	
	@Column(name="writer")
	private String writer;
	
	@Column(name="is_secret")
	private String is_secret;
	
	@Transient
	String answer_content;
	
	@Transient
	String answer_date;
	
}
