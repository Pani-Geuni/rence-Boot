//package com.rence.backoffice.service;
//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.CredentialsExpiredException;
//import org.springframework.security.authentication.DisabledException;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
//import org.springframework.stereotype.Component;
//
//@Component
//public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {
//   
//   @Override
//   public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//      
//       String msg = "Invalid Email or Password";
//   
//       // exception 관련 메세지 처리
//       if (exception instanceof DisabledException) {
//           msg = "DisabledException account";
//        } else if(exception instanceof CredentialsExpiredException) {
//           msg = "CredentialsExpiredException account";//비번만료
//        } else if(exception instanceof BadCredentialsException ) {
//           msg = "BadCredentialsException account";//비번불일치
//        }else if(exception instanceof UsernameNotFoundException ) {
//           msg = "UsernameNotFoundException account";//계정불일치
//        }
//   
//       setDefaultFailureUrl("/backoffice/loginOK?error=true&exception=" + msg);
//   
//       super.onAuthenticationFailure(request, response, exception);
//   }
//}
//
