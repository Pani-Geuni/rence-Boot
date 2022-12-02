/**
 * 
 * @author 최진실
 *
 */
package com.rence.backoffice.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="BACKOFFICEINFO")

@Slf4j
public class BackOfficeVO implements Serializable, UserDetails{  

	@Transient
	@Column(name="rnum")
	private int rnum;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_BACKOFFICE")
	@SequenceGenerator(sequenceName = "SEQ_BACKOFFICE",allocationSize = 1,name = "SEQ_BACKOFFICE")
	@Column(name="backoffice_no")
	private String backoffice_no;
	
	@Column(name="owner_name")
	private String owner_name;
	
	@Column(name="backoffice_id")
	private String backoffice_id;
	
	@Column(name="backoffice_name")
	private String backoffice_name;
	
	@Column(name="company_name")
	private String company_name;
	
	@Column(name="backoffice_pw")
	private String backoffice_pw;
	
	@Column(name="backoffice_tel")
	private String backoffice_tel;
	
	@Column(name="backoffice_email")
	private String backoffice_email;
	
	@Column(name="zipcode")
	private String zipcode;
	
	@Column(name="roadname_address")
	private String roadname_address;
	
	@Column(name="number_address")
	private String number_address;
	
	@Column(name="detail_address")
	private String detail_address;
	
	@Column(name="backoffice_tag")
	private String backoffice_tag;
	
	@Column(name="backoffice_info")
	private String backoffice_info;
	
	@Column(name="backoffice_option")
	private String backoffice_option;
	
	@Column(name="backoffice_around")
	private String backoffice_around;
	
	@Column(name="backoffice_image")
	private String backoffice_image;
	
	@Column(name="host_image")
	private String host_image;
	
	@Column(name="backoffice_state")
	@ColumnDefault(value="W")
	private String backoffice_state;
	
	@Column(name="apply_date", insertable= false, updatable = false)
	@ColumnDefault(value="current_date")
	private Date apply_date;
	
	@Column(name="backoffice_type")
	private String backoffice_type;
	
	@Override
	public String getPassword() {
		return this.getBackoffice_pw();
	}

	@Override
	public String getUsername() {
		return this.getBackoffice_id();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
		return roles;
	}

}
