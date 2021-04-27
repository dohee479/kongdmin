package com.mycompany.backend.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.backend.security.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {
	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/login")
	// {"uid":"user1", "upassword":"12345"}
	// dto로 user를 만들어 user로 받아도 되고 Map으로 받아도 된다.
	public Map<String, String> login(@RequestBody Map<String, String> user) {
		logger.info("컨트롤러 들어옴");
		
		// 인증 데이터 얻기
		String uid = user.get("uid");
		String upassword = user.get("upassword");
		
		logger.info(uid);
		logger.info(upassword);
		// 사용자 인증
		UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(uid, upassword);
		Authentication authentication = authenticationManager.authenticate(upat); // 인증을 테스트한다
		
		// Spring Security에 인증 객체 등록
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		// JWT 생성
		String jwt = JwtUtil.createToken(uid);
		
		// JSON 응답 보내기
		Map<String, String> map = new HashMap<>();
		map.put("uid", uid);
		map.put("authToken", jwt);
		logger.info(map+"");
		return map;
		
	}
}
