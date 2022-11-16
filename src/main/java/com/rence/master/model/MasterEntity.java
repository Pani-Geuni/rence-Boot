/**
 * @author 전판근
 */

package com.rence.master.model;

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

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="MASTERINFO")
public class MasterEntity implements Serializable, UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_master")
	@SequenceGenerator(sequenceName = "seq_master", allocationSize = 1, name = "seq_master")
	@Column(name="master_no")
	private String master_no;
	
	@Column(name="master_id")
	private String master_id;
	
	@Column(name="master_pw")
	private String master_pw;
	
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.getMaster_pw();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.getMaster_id();
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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
//		for(String role : auth.split(",")) {
//			roles.add(new SimpleGrantedAuthority(role));
//		}
		return roles;
	}

}
