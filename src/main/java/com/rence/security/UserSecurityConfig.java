package com.rence.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@EnableWebSecurity(debug = true)
@Configuration
@Order(1)
public class UserSecurityConfig {
	
	@Bean
	public UserDetailsService userUserDetailsService() {
		return new UserUserDetailsService();
	}

	// BCryptPasswordEncoder는 Spring Security에서 제공하는 비밀번호 암호화 객체 (BCrypt라는 해시 함수를
	// 이용하여 패스워드를 암호화 한다.)
	// 회원 비밀번호 등록시 해당 메서드를 이용하여 암호화해야 로그인 처리시 동일한 해시로 비교한다.
	@Bean
	public BCryptPasswordEncoder passwordEncoder1() {
		return new BCryptPasswordEncoder();// - 생성자의 인자 값(verstion, strength, SecureRandom instance)을 통해서 해시의 강도를
		// 조절할 수 있습니다.
	}
	
	
	public void configure(WebSecurity web) throws Exception {

		web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider1() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userUserDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder1());

		return authProvider;
	}
	
	@Bean
	public SecurityFilterChain filterChain1(HttpSecurity http) throws Exception {
		http.authenticationProvider(authenticationProvider1());
		
		
		
		
		http.authorizeRequests()
		.antMatchers("/").permitAll()
//		.antMatchers("/master/login").permitAll()
		.antMatchers("/backoffice/landing").permitAll() //백오피스 홈페이지	
		.antMatchers("/rence/user_auth").permitAll() //회원가입 - 이메일 인증
		.antMatchers("/rence/user_authOK").permitAll() //회원가입 - 이메일 완료
		.antMatchers("/rence/user_idCheckOK").permitAll() //회원가입 - 아이디 중복체크
		.antMatchers("/rence/joinOK").permitAll() //회원가입완료
		.antMatchers("/rence/find_id").permitAll() //아이디 찾기
		.antMatchers("/rence/find_pw").permitAll() // 비밀번호 찾기
		.antMatchers("/office/**").permitAll() 
		.antMatchers("/","/test/","/api/v2/**", "/v3/api-docs","/static/**",
                "/swagger*/**","/api/v1/auth/**","/h2-console/**","/favicon.ico","/swagger-ui.html","/swagger/**","/swagger-resources/**","webjars/**","/v2/api-docs"
                ,"/user/insertOK","/js/**","/css/**","/images/**","/error")
		.permitAll(); // 해당 경로들은 접근을 허용
			
		http
		.antMatcher("/rence/**")
		.authorizeRequests()
		.anyRequest()
		.authenticated()
		.and()
			.formLogin() // 로그인 폼은
			.loginPage("/") // 해당 주소로 로그인 페이지를 호출한다.
			.loginProcessingUrl("/rence/loginOK") // 해당 URL로 요청이 오면 스프링 시큐리티가 가로채서 로그인처리를 한다. -> loadUserByName
			.successForwardUrl("/rence/loginSuccess") // 성공시 요청을 처리할 핸들러
			.failureForwardUrl("/rence/loginFail") // 실패시 요청을 처리할 핸들러
			.permitAll()
				.and().
				logout().
				logoutRequestMatcher(new AntPathRequestMatcher("/rence/user_logoutOK")) // 로그아웃 URL																							
				.logoutSuccessUrl("/") // 성공시 리턴 URL
				.invalidateHttpSession(true) // 인증정보를 지우하고 세션을 무효화
				.deleteCookies("JSESSIONID","user_no","user_image") // JSESSIONID 쿠키 삭제
				.permitAll();
		
		http.csrf().disable();   // csrf 토큰을 활성화

		return http.build();
	}
}
