/**
 * @author 전판근
 */

package com.rence.office.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name="OFFICE_INFO_VIEW")
public class OfficeInfoVO {
	
	@Id
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
	
	@Column(name="backoffice_image")
	private String backoffice_image;
	
	@Column(name="backoffice_type")
	private String backoffice_type;
	
	@Column(name="backoffice_tag")
	private String backoffice_tag;
	
	@Column(name="backoffice_option")
	private String backoffice_option;
	
	@Column(name="backoffice_around")
	private String backoffice_around;
	
	@Column(name="backoffice_tel")
	private String backoffice_tel;
	
	@Column(name="backoffice_email")
	private String backoffice_email;
	
	@Column(name="backoffice_info")
	private String backoffice_info;
	
	@Column(name="zipcode")
	private String zipcode;
	
	@Column(name="roadname_address")
	private String roadname_address;
	
	@Column(name="number_address")
	private String number_address;
	
	@Column(name="detail_address")
	private String detail_address;
	
	@Column(name="avg_rating")
	private Double avg_rating;	
}
