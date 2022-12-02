/**
 * @author 강경석
 */
package com.rence.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.rence.master.model.MasterEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MasterUserDetails implements UserDetails {

	private MasterEntity master;

	public MasterUserDetails(MasterEntity master) {
		this.master = master;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return null;

	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return master.getMaster_pw();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		log.info("id::::::::::::::::::{}", master.getMaster_id());
		return master.getMaster_id();

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

}// end class
