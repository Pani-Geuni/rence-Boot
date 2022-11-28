package com.rence.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.rence.user.model.UserVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserUserDetails implements UserDetails {
	
	private UserVO user;
	
	
	
	public UserUserDetails(UserVO user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
	      return null;

	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getUser_pw();
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
	      log.info("id::::::::::::::::::{}",user.getUser_id());
	      return user.getUser_id();

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
