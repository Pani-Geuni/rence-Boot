/**
 * @author 강경석
 * user_review_view
 */

package com.rence.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


import org.springframework.data.annotation.Immutable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Immutable
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user_review_view")
public class UserReviewVO {

	@Id
	@Column(name="review_no")
	String review_no;
	@Column(name="review_content")
	String review_content;
	@Column(name="review_point")
	Integer review_point;
	@Column(name="review_date")
	String review_date;
	@Column(name="room_name")
	String room_name;
	@Column(name="company_name")
	String company_name;
	@Column(name="user_no")
	String user_no;
	
} //end class
