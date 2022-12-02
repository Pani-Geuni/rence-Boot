/**
 * 
 * @author 최진실
 *
 */
package com.rence.backoffice.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="AUTH")
public class AuthVO {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_auth")
	@SequenceGenerator(sequenceName = "seq_auth",allocationSize = 1,name = "seq_auth")
	@Column(name="auth_no")
	private String auth_no;
	
	@Column(name="user_email")
	private String user_email;
	
	@Column(name="auth_code")
	private String auth_code;
	
	@Column(name="auth_stime", insertable= false, updatable = false)
	@ColumnDefault(value="current_date")
	private Date auth_stime;

	
}
