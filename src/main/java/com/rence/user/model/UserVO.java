/**
* @author 강경석
* 유저관련 VO
*/

package com.rence.user.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="userinfo")
public class UserVO implements Serializable,UserDetails {
	
	@Id //pk설정
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_user")
	@SequenceGenerator(sequenceName = "seq_user",allocationSize = 1,name= "seq_user")
	@Column(name="user_no", insertable = false, updatable = false)
	private String user_no; //사용자 고유번호
	
	@Column(name="user_image")
	private String user_image; //프로필 이미지
	
	@Column(name="user_id")	
	private String user_id; //아이디
	
	@Column(name="user_pw")	
	private String user_pw; //패스워드
	
	@Column(name="user_name")	
	private String user_name; //실명
	
	@Column(name="user_email")	
	private String user_email; //이메일
	
	@Column(name="user_tel")	
	private String user_tel; //전화번호
	
	//	@DateTimeFormat(pattern ="yyyyMMdd")
	@Column(name="user_birth")	
	private String user_birth; //생년월일
	
	@Column(name="user_state")	
	private String user_state; //회원상태
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		  Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
//	      for(String role : auth.split(",")) {
//	         roles.add(new SimpleGrantedAuthority(role));
//	      }
	      return roles;

	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.getUser_pw();
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
	      log.info("id::::::::::::::::::{}",this.getUser_id());
	      return this.getUser_id();

	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	
}//end class
