/**
 * @author 강경석
 * reserve 테이블 - backoffice 테이블 조인 뷰(USER_RESERVE_VIEW)
 * 유저 마일리지 상세페이지 데이터 VO 
 */


package com.rence.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Immutable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Immutable
@AllArgsConstructor
@NoArgsConstructor
@Table(name="USER_RESERVE_VIEW")
public class MyPageReserveListVO {
	
	@Id
	@Column(name="user_no", insertable = false, updatable = false)
	private String user_no; 
	
	@Column(name="reserve_no")
	private String reserve_no;
	
	@Column(name="reserve_sdate")
	private String reserve_sdate;
	
	@Column(name="reserve_edate")
	private String reserve_edate;
	
	@Column(name="company_name")
	private String company_name;
	
	@Column(name="roadname_address")
	private String roadname_address;
	
	@Column(name="backoffice_image")
	private String backoffice_image;
	
	@Column(name="payment_total")
	private String payment_total;
	
}//end class
