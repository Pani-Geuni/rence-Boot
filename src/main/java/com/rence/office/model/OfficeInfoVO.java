/**
 * @author 전판근
 */

package com.rence.office.model;

import java.io.Serializable;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class OfficeInfoVO implements Serializable {
	
	private String backoffice_no;
	private String company_name;
	private String backoffice_info;
	private String backoffice_type;
	private int avg_rating;
	
	private String zipcode;
	private String roadname_address;
	private String number_address;
	private String detail_address;
	private String short_roadname_address;
	 
	private String backoffice_tag;
	private String backoffice_option;
	private String backoffice_around;
	private String backoffice_image;
	
}
