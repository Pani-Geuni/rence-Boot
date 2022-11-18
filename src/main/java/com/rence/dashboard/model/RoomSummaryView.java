/**
 * 
 * @author 최진실
 *
 */
package com.rence.dashboard.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Entity
@Immutable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="ROOM_SUMMARY_VIEW")
@Slf4j
public class RoomSummaryView {

	@Id
	@Column(name="review_point")
	private float review_point;
	
	@Column(name="comment_no")
	private int comment_no;
	
	@Column(name="review_no")
	private int review_no;
	
	@Column(name="reserve_no")
	private int reserve_no;
}
