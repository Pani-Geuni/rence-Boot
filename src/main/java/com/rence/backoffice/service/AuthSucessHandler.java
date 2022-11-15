//package com.rence.backoffice.service;
//
//import java.io.IOException;
//import java.time.LocalDateTime;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.json.simple.JSONObject;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.DefaultRedirectStrategy;
//import org.springframework.security.web.RedirectStrategy;
//import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//
//import lombok.RequiredArgsConstructor;
//
//@RequiredArgsConstructor
//@Component
//public class AuthSucessHandler extends SimpleUrlAuthenticationSuccessHandler {
//
//	private final BackOfficeRepository backOfficeRepository;
//	 private RedirectStrategy redirectStratgy = new DefaultRedirectStrategy();
//
//	@Override
//	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//			Authentication authentication) throws IOException, ServletException {
//
////	   backOfficeRepository.updateMemberLastLogin(authentication.getName(), LocalDateTime.now());
//
//		setDefaultTargetUrl("/test");
////		setDefaultTargetUrl("/loginSuccess");
////		
////		redirectStratgy.sendRedirect(request, response, getDefaultTargetUrl());
//
//		super.onAuthenticationSuccess(request, response, authentication);
//	}
//
//}
