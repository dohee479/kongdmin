package com.mycompany.backend.security;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 폼 인증을 비활성화
		http.httpBasic().disable();
		
		// 서버 세션 비활성화
		// 세션을 이용해서 상태 정보를 저장하지 않겠다
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	
		// 사이트간 요청 위조 방지 비활성화
		http.csrf().disable();
		
		// CORS 설정(다른 도메인에서 요청 허가)
		// 실행되면 CorsConfigurationSource 객체를 찾는다
		http.cors();
		
		// JWT 인증 필터 추가
		// 특정 필터 이전에 추가해
		http.addFilterBefore(new JwtAuthenticationFilter(userDetailsService), UsernamePasswordAuthenticationFilter.class);
		// 요청 경로 권한 설정
		http.authorizeRequests()
			// 권한 계층 설정
			.expressionHandler(securityExpressionHandler()) 
			// 요청 경로 권한 설정
			.antMatchers("/").permitAll()
			// USER라고 줘도 대지만 DB에는 ROLE_USER 형식으로 저장되어야 한다
//			.antMatchers(HttpMethod.POST, "/boards").hasAuthority("ROLE_USER")
			.antMatchers(HttpMethod.POST, "/boards").hasAnyRole("USER")
			.antMatchers(HttpMethod.PUT, "/boards").hasAnyRole("USER")
			.antMatchers(HttpMethod.DELETE, "/boards/*").hasAnyRole("USER")
			
			.antMatchers(HttpMethod.POST, "/products").hasAnyRole("ADMIN")
			.antMatchers(HttpMethod.PUT, "/products").hasAnyRole("ADMIN")
			.antMatchers(HttpMethod.DELETE, "/products/*").hasAnyRole("ADMIN")
			// 그 이외의 모든 경로 허가
			.antMatchers("/**").permitAll();
		

	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
        .dataSource(dataSource) 
        .usersByUsernameQuery("select user_id as username, user_password as password, user_enabled as enabled from users where user_id=?")
        .authoritiesByUsernameQuery("select user_id as username, user_authority as authority from users where user_id=?")
        .passwordEncoder(new BCryptPasswordEncoder());
	}
	
	// 사용자의 상세 정보를 가져오는 서비스 객체를 관리 객체로 등록
	// JwtAuthenticationFilter에서 사용
	// 위에 sql 문을 통해 가져온다.
	@Bean
	@Override
	public UserDetailsService userDetailsServiceBean() throws Exception {
		return super.userDetailsServiceBean();
	}
	
	// 인증된 정보를 관리하는 객체를 Spring 관리 객체로 등록
	// AuthController에서 사용
	// 인증된 것을 관리를 해야하는 데 이놈이다
	@Bean 
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	// 권한 계층 객체 생성
	public RoleHierarchyImpl roleHierarchyImpl() {
		RoleHierarchyImpl roleHierarchyImpl = new RoleHierarchyImpl();
		roleHierarchyImpl.setHierarchy("ROLE_ADMIN > ROLE_MANAGER > ROLE_USER");
		return roleHierarchyImpl;
	}
	public SecurityExpressionHandler<FilterInvocation> securityExpressionHandler() {
		DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler = new DefaultWebSecurityExpressionHandler();
        defaultWebSecurityExpressionHandler.setRoleHierarchy(roleHierarchyImpl());
        return defaultWebSecurityExpressionHandler;
	}
	
	// 다른 도메인의 접근을 허용하는 객체를 Spring 관리 객체로 등록
	@Bean // 메소드를 자동 실행해서 리턴된 객체를 Spring 관리 객체로 등록
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		
		// 모든 요청 사이트 허용
		configuration.addAllowedOrigin("*");
		
		// 모든 요청 방식 허용
		configuration.addAllowedMethod("*");
		
		// 모든 요청 헤더 허용
		configuration.addAllowedHeader("*");
		
		// 요청 헤더의 Authorization을 이용해서 사용자 인증(로그인 처리)하지 않음
		// 폼 형태로 아이디와 비밀번호를 받지 않아도 Authorization에 담겨있는 정보로 로그인 가능한 것을 막는다
		configuration.setAllowCredentials(false);

		// 모든 URL 요청에 대해서 위의 내용을 적용하겠다
		UrlBasedCorsConfigurationSource ccs =  new UrlBasedCorsConfigurationSource();
		ccs.registerCorsConfiguration("/**", configuration);
		return ccs;
	}
}
