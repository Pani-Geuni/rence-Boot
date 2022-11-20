/**
	 * @author 강경석
	 
*/

package com.rence.user.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
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
@Table(name="REVIEW")
public class ReviewEntityVO {
	
	@Id	// PK 설정
	@Column(name="review_no")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_REVIEW")
	@SequenceGenerator(sequenceName = "SEQ_REVIEW", allocationSize = 1, name = "SEQ_REVIEW")
	String review_no;
	
	@Column(name="review_content")
	String review_content;
	
	@Column(name="review_point")
	Float review_point;
	
	@Column(name="review_date", insertable = false, updatable = false)
	@ColumnDefault(value="sysdate")
	Date review_date;
	
	@Column(name="room_no")
	String room_no;
	
	@Column(name="backoffice_no")
	String backoffice_no;
	
	@Column(name="user_no")
	String user_no;
	
}
