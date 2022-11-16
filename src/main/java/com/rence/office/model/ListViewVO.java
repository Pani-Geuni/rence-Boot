package com.rence.office.model;

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
public class ListViewVO {
	
	String backoffice_no;
	String company_name;
	String roadname_address;
	String backoffice_tag;
	String backoffice_image;
	String min_room_price;
	String avg_rating;
	
}
