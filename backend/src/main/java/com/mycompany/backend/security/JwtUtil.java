package com.mycompany.backend.security;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {
	private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);
	// 비밀키(누출되면 안됨)
	private static final String secretkey = "secret";
	
	// JWT 생성: 사용자 아이디 포함
	public static String createToken(String uid) {
		String token = null;
		try {
			token = Jwts.builder()
					// 헤더
					// 토큰의 종류
					.setHeaderParam("typ", "JWT")
					// 암호화 알고리즘 종류
					.setHeaderParam("alg", "HS256")
					
					// 페이로드
					// 토큰의 유효기간
					.setExpiration(new Date(new Date().getTime() + 1000 * 60 * 60 * 12))
					// 토큰의 저장되는 데이터
					.claim("uid", uid)
					
					// 서명(해시 알고리즘, 앞서 정한 key의 byte)
					// 비밀키
					.signWith(SignatureAlgorithm.HS256, secretkey.getBytes("UTF-8"))
					// 모든 내용을 묶기
					.compact();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return token;
	}
	
	// JWT를 파싱해서 uid 얻기
	public static String getUid(String token) {
		String uid = null;
		try {
			JwtParser parser = Jwts.parser();
			parser.setSigningKey(secretkey.getBytes("UTF-8"));
			Jws<Claims> jws = parser.parseClaimsJws(token);
			Claims claims = jws.getBody();
			uid = claims.get("uid", String.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return uid;
	}
	
	// JWT 유효성 검사: 유효기간 확인
	public static boolean validateToken(String token) {
		boolean validate = false;
		try {
			JwtParser parser = Jwts.parser();
			parser.setSigningKey(secretkey.getBytes("UTF-8"));
			// JWS : JSON 데이터 구조를 사용하는 서명 표준
			Jws<Claims> jws = parser.parseClaimsJws(token);
			Claims claims = jws.getBody();
			System.out.println(jws);
			System.out.println(claims);
			// 현재 시간보다 뒤인가
			validate = claims.getExpiration().after(new Date());
			if (validate) {
				long remainMillSecs = claims.getExpiration().getTime() - new Date().getTime();
				logger.info("" + remainMillSecs / 1000 + "초 남앗음");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return validate;
	}
	
	// 테스트
//	public static void main(String[] args) {
//		// 토큰 생성
//		String jwt = createToken("user1");
//		logger.info(jwt);
//		
//		// 5초 지연
//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		
//		// 토큰 정보 얻기
//		if (validateToken(jwt)) {
//			String uid = getUid(jwt);
//			logger.info(uid);
//			
//		}
//	}
}
