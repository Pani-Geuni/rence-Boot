package com.rence.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class AuthorityChangeFilter extends GenericFilterBean {
	
	@Bean
	public FilterRegistrationBean authorityChangeFilter() {
		FilterRegistrationBean filter = new FilterRegistrationBean();

//		List<String> urlPattern = new ArrayList<>();
//		urlPattern.add("/user/*");   // */
//		urlPattern.add("/admin/*");   // */
//		filter.setUrlPatterns(urlPattern);
//
//		filter.setOrder(Ordered.LOWEST_PRECEDENCE - 2);
		filter.setFilter(getAuthorityChangeFilter());

		filter.setEnabled(false);  // disabled

		return filter;
	}

	@Bean
	public AuthorityChangeFilter getAuthorityChangeFilter() {
		return new AuthorityChangeFilter();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
	}

}