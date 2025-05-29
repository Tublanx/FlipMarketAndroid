package com.lgh.FlipMarketBackend.config;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService { // 토큰 생성 및 검증을 담당

	// JWT 서명을 위한 비밀 키 *실제 서버 운영 시에는 .properties 파일에 따로 정의해야함
	private final String secretKey = "VoNP8e8GvbCPMJoXeZSCaHUSvJFk2Mav";
	
	public String generatedToken(String username) {
		SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
		
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date()) // 토큰 발급 시간 (현재 시간)
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 토큰 발급 시간은 1시간 유효로 설정
				.signWith(key, SignatureAlgorithm.HS256) // 서명 알고리즘은 SHA-256 알고리즘 사용
				.compact();
	}
	
	public String extractUsername(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(secretKey.getBytes()) // SigningKey를 지정해야 위조되지 않은 토큰임을 증명할 수 있음.
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}
	
	// 토큰의 유효성을 확인
	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(secretKey.getBytes()).build().parseClaimsJws(token);
			return true;
		} catch (JwtException e) {
			return false;
		}
	}
	
}
