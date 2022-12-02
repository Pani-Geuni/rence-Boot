/**
 * 
 * @author 최진실
 *
 */
package com.rence.dashboard.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Immutable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="ROOM_SUMMARY_VIEW")
public class RoomSummaryView {

	@Id
	@Column(name="review_point")
	private Float review_point;
	
	@Column(name="comment_no")
	private Integer comment_no;
	
	@Column(name="review_no")
	private Integer review_no;
	
	@Column(name="reserve_no")
	private Integer reserve_no;
}
