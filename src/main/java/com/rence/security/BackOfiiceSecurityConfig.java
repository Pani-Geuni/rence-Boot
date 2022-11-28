package com.rence.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity(debug = true)
@Configuration
@Order(2)
public class BackOfiiceSecurityConfig {

	@Bean
	public UserDetailsService customerUserDetailsService() {
		return new BackOfficeUserDetailsService();
	}

	// BCryptPasswordEncoder는 Spring Security에서 제공하는 비밀번호 암호화 객체 (BCrypt라는 해시 함수를
	// 이용하여 패스워드를 암호화 한다.)
	// 회원 비밀번호 등록시 해당 메서드를 이용하여 암호화해야 로그인 처리시 동일한 해시로 비교한다.
	@Bean
	public BCryptPasswordEncoder passwordEncoder2() {
		return new BCryptPasswordEncoder();// - 생성자의 인자 값(verstion, strength, SecureRandom instance)을 통해서 해시의 강도를
		// 조절할 수 있습니다.
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider2() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(customerUserDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder2());

		return authProvider;
	}

	@Bean
	public SecurityFilterChain filterChain2(HttpSecurity http) throws Exception {
		http.authenticationProvider(authenticationProvider2());
		
		http
		.authorizeRequests()
		.antMatchers("/backoffice/insert").permitAll()
		.antMatchers("/backoffice/auth").permitAll()
		.antMatchers("/backoffice/authOK").permitAll()
		.antMatchers("/backoffice/insertOK").permitAll();
		
		http
		 .antMatcher("/backoffice/**")
		.authorizeRequests() // 요청 URL에 따라 접근 권한을 설정
		.anyRequest()
		 .authenticated() // 요청 URL에 따라 접근 권한을 설정

//		http.antMatcher("/backoffice/dashbord/**")
//		.authorizeRequests().anyRequest().authenticated()
//		http
//		.antMatcher("/rence/**").authorizeRequests().anyRequest().authenticated()
		.and()
		.formLogin() // 로그인 폼은
		.loginPage("/backoffice/landing") // 해당 주소로 로그인 페이지를 호출한다.
        .loginProcessingUrl("/backoffice/loginOK") // 해당 URL로 요청이 오면 스프링 시큐리티가 가로채서 로그인처리를 한다. ->
        .successForwardUrl("/backoffice/loginSuccess") // 성공시 요청을 처리할 핸들러
        .failureForwardUrl("/backoffice/loginFail") // 실패시 요청을 처리할 핸들러
		.permitAll()
		.and()
        .logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/backoffice/logout")) // 로그아웃 URL
        .logoutSuccessUrl("/backoffice/logoutOK") // 성공시 리턴 URL
        .invalidateHttpSession(true) // 인증정보를 지우하고 세션을 무효화
        .deleteCookies("JSESSIONID","backoffice_no","host_image") // JSESSIONID 쿠키 삭제
        .permitAll();
		
		http.csrf().disable();
		

		return http.build();
	}
}
