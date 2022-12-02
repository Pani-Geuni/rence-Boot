/**
 * @author 강경석
 */
package com.rence.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.rence.backoffice.model.BackOfficeVO;
import com.rence.backoffice.repository.BackOfficeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BackOfficeUserDetailsService implements UserDetailsService {
	@Autowired
	private BackOfficeRepository repository;

	@Override
	public UserDetails loadUserByUsername(String backoffice_id) throws UsernameNotFoundException {
		BackOfficeVO backoffice = repository.findByBackoffice_email(backoffice_id);
		log.info("backoffice:{}", backoffice);

		if (backoffice == null) {
			throw new UsernameNotFoundException("No user found with the given email");
		}

		return new BackOfficeUserDetails(backoffice);
	}

}
