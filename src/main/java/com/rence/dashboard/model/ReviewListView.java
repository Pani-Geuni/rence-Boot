/**
 * 
 * @author 최진실
 *
 */
package com.rence.dashboard.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Immutable
@Table(name="REVIEW_LIST_B_VIEW")
public class ReviewListView implements Serializable{

	@Id
	@Column(name="review_no")
	private String review_no;
	
	@Column(name="review_content")
	private String review_content;
	
	@Column(name="review_point")
	private float review_point;
	
	@Column(name="review_date")
	private String review_date;
	
	@Column(name="user_no")
	private String user_no;
	
//	@Transient
	@Column(name="user_image")
	private String user_image;
	
//	@Transient
	@Column(name="user_name")
	private String user_name;
}
