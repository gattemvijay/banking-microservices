package com.mybank.transaction.util;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtUtil {

	private final String SECRET = "my-secret-key";
	private final SecretKey key;
	private final long EXPIRATION = 1000 * 60 * 60;

	public JwtUtil(@Value("${jwt.secret}") String secret) {
		this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
	}

	/*
	 * public String generateToken(String username) {
	 * 
	 * return Jwts.builder() .subject(username) .issuedAt(new Date())
	 * .expiration(new Date(System.currentTimeMillis() + EXPIRATION)) .signWith(key)
	 * .compact(); }
	 */

	public String extractUsername(String token) {

		Claims claims = Jwts.parser()
				.verifyWith(key)
				.build()
				.parseSignedClaims(token)
				.getPayload();

		return claims.getSubject();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser()
			.verifyWith(key)
			.build()
			.parseSignedClaims(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/*
	 * public String extractEmail(String token) { return Jwts.parser()
	 * .verifyWith(key) .build() .parseSignedClaims(token) .getPayload()
	 * .getSubject(); }
	 */

	public String extractUserFromRequest(HttpServletRequest request) {
		String authHeader = request.getHeader("Authorization");
		System.out.println("Authorization header: " + authHeader);

		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			String token = authHeader.substring(7);
			return extractUsername(token);
		}
		return null;
	}

}
