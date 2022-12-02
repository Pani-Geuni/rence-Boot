/**
 * @author 강경석
 */
package com.rence.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.rence.master.model.MasterEntity;
import com.rence.master.repository.MasterRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MasterUserDetailsService implements UserDetailsService {
	@Autowired
	private MasterRepository repository;

	@Override
	public UserDetails loadUserByUsername(String master_id) throws UsernameNotFoundException {
		MasterEntity master = repository.findByMaster_id(master_id);
		log.info("master:{}", master);

		if (master == null) {
			throw new UsernameNotFoundException("No user found with the given email");
		}

		return new MasterUserDetails(master);
	}

}
