/**
 * @author 전판근
 */

package com.rence.office.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.rence.user.model.UserQuestionVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="review_list_view")
public class OfficeReviewVO {

	@Id
	@Column(name="review_no")
	private String review_no;
	
	@Column(name="room_no")
	private String room_no;
	
	@Column(name="backoffice_no")
	private String backoffice_no;
	
	@Column(name="user_no")
	private String user_no;
	
	@Column(name="review_content")
	private String review_content;
	
	@Column(name="review_point")
	private int review_point;
	
	@Column(name="review_date")
	private String review_date;

	@Column(name="user_image")
	private String user_image;
	
	@Column(name="user_name")
	private String user_name;
	
	
}
