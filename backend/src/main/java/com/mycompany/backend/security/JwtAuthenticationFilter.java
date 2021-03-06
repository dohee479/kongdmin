package com.mycompany.backend.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.GenericFilterBean;

public class JwtAuthenticationFilter extends GenericFilterBean {
	private UserDetailsService userDetailsService;
	
	public JwtAuthenticationFilter(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// JWT 토큰이 요청 헤더로 전송된 경우
		// servlet request 는 http 관련 기능이 없으므로 형 변환
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String jwt = httpRequest.getHeader("authToken");
		if (jwt == null) {
			// JWT가 요청 파라미터로 전달된 경우
			// <img src="download?bno=3&authToken=xxx"/> ajax요청이 아니라 get 요청이므로 요청 파라미터로 auth를 넘긴다
			jwt = request.getParameter("authToken");
		}
		
		if (jwt != null) {
			if (JwtUtil.validateToken(jwt)) {
				// JWT에서 uid 얻기
				String uid = JwtUtil.getUid(jwt);
				
				// DB에서 uid에 해당하는 정보를 가져오기(이름, 권한들)
				UserDetails userDetails = userDetailsService.loadUserByUsername(uid);
				
				// 인증 객체 생성
				Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
				
				// Spring Security에 인증 객체 등록
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		// SecurityContextHolder 다음 필터를 실행해
		chain.doFilter(request, response);
	}

}
