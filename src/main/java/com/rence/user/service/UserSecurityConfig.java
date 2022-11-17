//package com.rence.user.service;
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//import lombok.RequiredArgsConstructor;
//
//@SuppressWarnings("deprecation")
//@Configuration
//@RequiredArgsConstructor
//@EnableWebSecurity // 시큐리티 필터 등록
//@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 페이지에 특정 권한이 있는 유저만 접근을 허용할 경우 권한 및 인증을 미리 체크하겠다는 설정을 활성화한다.
//public class UserSecurityConfig extends WebSecurityConfigurerAdapter {
//	
//	private final UserService userService;
//
//	// BCryptPasswordEncoder는 Spring Security에서 제공하는 비밀번호 암호화 객체 (BCrypt라는 해시 함수를 이용하여 패스워드를 암호화 한다.)
//	// 회원 비밀번호 등록시 해당 메서드를 이용하여 암호화해야 로그인 처리시 동일한 해시로 비교한다.
//	@Bean
//	public BCryptPasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();//- 생성자의 인자 값(verstion, strength, SecureRandom instance)을 통해서 해시의 강도를 조절할 수 있습니다.
//	}
//	
//	// 시큐리티가 로그인 과정에서 password를 가로챌때 해당 해쉬로 암호화해서 비교한다.
//	//스프링 시큐리티(Spring Seurity) 프레임워크에서 제공하는 클래스 중 하나로 비밀번호를 암호화하는 데 사용할 수 있는 메서드를 가진 클래스입니다.
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
//	}
//	
//	@Override
//    protected void configure(HttpSecurity http) throws Exception {
//		/*
//		 csrf 토큰 활성화시 사용
//		 쿠키를 생성할 때 HttpOnly 태그를 사용하면 클라이언트 스크립트가 보호된 쿠키에 액세스하는 위험을 줄일 수 있으므로 쿠키의 보안을 강화할 수 있다.
//		*/
//		//http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//		
//        http.csrf().disable()	// csrf 토큰을 비활성화
//        	.authorizeRequests() // 요청 URL에 따라 접근 권한을 설정
//			.antMatchers("/","/test/","/api/v2/**", "/v3/api-docs", "/static/**",
//	                 "/swagger*/**","/api/v1/auth/**","/h2-console/**","/favicon.ico","/swagger-ui.html","/swagger/**","/swagger-resources/**","webjars/**","/v2/api-docs"
//	                 ,"/user/insert","/user/insertOK","/user/loginOK","/user/user_loginOK","/js/**","/css/**","/images/**").permitAll() // 해당 경로들은 접근을 허용
//			.anyRequest() // 다른 모든 요청은
//			.authenticated() // 인증된 유저만 접근을 허용
//		.and()
//			.formLogin() // 로그인 폼은
////			.loginPage("/login") // 해당 주소로 로그인 페이지를 호출한다.
//			.loginProcessingUrl("/user/loginOK") // 해당 URL로 요청이 오면 스프링 시큐리티가 가로채서 로그인처리를 한다. -> loadUserByName
//			.successForwardUrl("/user_loginOK") // 성공시 요청을 처리할 핸들러
//			.failureForwardUrl("/user_loginFail") // 실패시 요청을 처리할 핸들러
//		.and()
//			.logout()
//			.logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // 로그아웃 URL
//		    .logoutSuccessUrl("/login") // 성공시 리턴 URL
//		    .invalidateHttpSession(true) // 인증정보를 지우하고 세션을 무효화
//		    .deleteCookies("JSESSIONID") // JSESSIONID 쿠키 삭제
//			.permitAll()
//		.and()
//        	.sessionManagement()
//            .maximumSessions(1) // 세션 최대 허용 수 1, -1인 경우 무제한 세션 허용
//            .maxSessionsPreventsLogin(false) // true면 중복 로그인을 막고, false면 이전 로그인의 세션을 해제
//            .expiredUrl("/login?error=true&exception=Have been attempted to login from a new place. or session expired")  // 세션이 만료된 경우 이동 할 페이지를 지정
//        .and()
//	        .and().rememberMe() // 로그인 유지
//	        .alwaysRemember(false) // 항상 기억할 것인지 여부
//	        .tokenValiditySeconds(43200) // in seconds, 12시간 유지
//	        .rememberMeParameter("remember-me");
//    }//end configure(HttpSecurity http)
//	
//	
//	
//	
//	
//}