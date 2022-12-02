/**
 * @author 강경석
 * 인증관련 vo
 */

package com.rence.user.model;

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


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="AUTH")
public class UserAuthVO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_auth")
	@SequenceGenerator(sequenceName = "seq_auth",allocationSize = 1,name = "seq_auth")
	@Column(name="AUTH_NO")
	private String auth_no;
	
	@Column(name="USER_EMAIL")
	private String user_email;
	
	@Column(name="AUTH_CODE")
	private String auth_code;
	
	@Column(name="AUTH_STIME", insertable= false, updatable = false)
	@ColumnDefault(value="sysdate")
	private Date auth_stime;
	
}