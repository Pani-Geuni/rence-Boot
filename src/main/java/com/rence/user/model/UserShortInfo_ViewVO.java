/**
	 * @author 김예은
	 * 유저관련 VO
*/

package com.rence.user.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Immutable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="userinfo")
public class UserShortInfo_ViewVO implements Serializable{
	
	@Id //pk설정
	@Column(name="user_no", insertable = false, updatable = false)
	private String user_no; //사용자 고유번호
	
	@Column(name="user_name")	
	private String user_name; //실명
	
	@Column(name="user_email")	
	private String user_email; //이메일
	
	@Column(name="user_tel")	
	private String user_tel; //전화번호
	
	
}//end class
