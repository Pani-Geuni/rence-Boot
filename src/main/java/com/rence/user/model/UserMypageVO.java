/**
 * @author 강경석
 * userinfo 테이블 - mileage 테이블 조인 뷰(user_mypage_view)
 * 유저 마이페이지필요 데이터 VO
 *  View - Spring JPA 사용 참조링크 : https://joomn11.tistory.com/107
 * 
 */

package com.rence.user.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Immutable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Immutable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user_mypage_view")
public class UserMypageVO {
	@Id
	@Column(name="user_no", insertable = false, updatable = false)
	private String user_no; //사용자 고유번호
	@Column(name="user_image")
	private String user_image; //프로필 이미지
	@Column(name="user_id")
	private String user_id; //아이디
	@Column(name="user_name")
	private String user_name; //실명
	@Column(name="user_email")
	private String user_email; //이메일
	@Column(name="user_tel")
	private String user_tel; //전화번호
	@Column(name="user_birth")
	private Date user_birth; //생년월일
	@Column(name="mileage_total")
	private String mileage_total; // 총 마일리지
	
	
	
}//end class
