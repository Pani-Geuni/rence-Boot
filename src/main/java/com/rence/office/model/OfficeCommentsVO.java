/**
 * @author 전판근
 */

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
public class OfficeCommentsVO {
	
	private String user_no;
	private String user_name;
	private String user_image;
	
	private String comment_no;
	private String comment_content;
	private String comment_date;
	
	private String room_no;
	private String room_name;
	private String backoffice_no;
	
	
}
