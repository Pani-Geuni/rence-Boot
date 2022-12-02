/**
 * 
 * @author 최진실
 *
 */
package com.rence.backoffice.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="BACKOFFICEINFO")

public class BackOfficeListVO implements Serializable{ 

	@Transient
	@Column(name="rnum")
	private int rnum;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_BACKOFFICE")
	@SequenceGenerator(sequenceName = "SEQ_BACKOFFICE",allocationSize = 1,name = "SEQ_BACKOFFICE")
	@Column(name="backoffice_no")
	private String backoffice_no;
	
	@Column(name="owner_name")
	private String owner_name;
	
	@Column(name="backoffice_id")
	private String backoffice_id;
	
	@Column(name="backoffice_name")
	private String backoffice_name;
	
	@Column(name="company_name")
	private String company_name;
	
	@Transient
	@Column(name="backoffice_pw")
	private String backoffice_pw;
	
	@Column(name="backoffice_tel")
	private String backoffice_tel;
	
	@Column(name="backoffice_email")
	private String backoffice_email;
	
	@Transient
	@Column(name="zipcode")
	private String zipcode;
	
	@Transient
	@Column(name="roadname_address")
	private String roadname_address;
	
	@Transient
	@Column(name="number_address")
	private String number_address;
	
	@Transient
	@Column(name="detail_address")
	private String detail_address;
	
	@Transient
	@Column(name="backoffice_tag")
	private String backoffice_tag;
	
	@Transient
	@Column(name="backoffice_info")
	private String backoffice_info;
	
	@Transient
	@Column(name="backoffice_option")
	private String backoffice_option;
	
	@Transient
	@Column(name="backoffice_around")
	private String backoffice_around;
	
	@Transient
	@Column(name="backoffice_image")
	private String backoffice_image;
	
	@Transient
	@Column(name="host_image")
	private String host_image;

	@Transient
	@Column(name="backoffice_state")
	private String backoffice_state;
	
	@Column(name="apply_date")
	private String apply_date;
	
	@Transient
	@Column(name="backoffice_type")
	private String backoffice_type;
	

}
