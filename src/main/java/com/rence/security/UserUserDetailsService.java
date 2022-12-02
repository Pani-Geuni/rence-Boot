/**
 * @author 강경석
 */
package com.rence.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.rence.user.model.UserVO;
import com.rence.user.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String user_id) throws UsernameNotFoundException {
		UserVO user = repository.findByUser_email(user_id);
		log.info("user:{}", user);

		if (user == null) {
			throw new UsernameNotFoundException("No user found with the given email");
		}

		return new UserUserDetails(user);
	}

}
