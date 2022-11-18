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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Immutable
@Table(name="COMMENTLIST_A_VIEW")
public class CommentListAView {

	@Column(name="comment_date")
	private String comment_date;
	
	@Id
	@Column(name="comment_no")
	private String comment_no;
	
	@Column(name="backoffice_no")
	private String backoffice_no;
	
	@Column(name="comment_content")
	private String comment_content;
	
	@Column(name="mother_no")
	private String mother_no;
	
}
