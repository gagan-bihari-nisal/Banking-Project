package com.bms.authservice.filter;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class TokenGeneratorFilter {

	@Value("${jwt.secret}")
	private String secret;
	@Value("${jwt.expiration}")
	private int jwtExpiration;

	private String SECRET_KEY = "gO/lM50Wd3oVCLGIhARQsbMqYnYjIlELAnQ5x63wzkE=";

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, userDetails);
	}

	
//	private Key getSigningKey() {
//		  byte[] keyBytes = Decoders.BASE64.decode("gO/lM50Wd3oVCLGIhARQsbMqYnYjIlELAnQ5x63wzkE=");
//		  return Keys.hmacShaKeyFor("gO/lM50Wd3oVCLGIhARQsbMqYnYjIlELAnQ5x63wzkE=".getBytes(StandardCharsets.UTF_8));
//		}
	
	
	private String createToken(Map<String, Object> claims, UserDetails userDetails) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
		return Jwts.builder().setIssuer("Bank Management System").setSubject("JWT Token")
				.claim("username", userDetails.getUsername())
				.claim("authorities", populateAuthorities(userDetails.getAuthorities())).setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpiration))
				//.signWith(key)
				.signWith(key)
				.compact();
		
		//   YmFua3Byb2plY3Q=
	}

	private String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
		Set<String> authoritiesSet = new HashSet<>();
		for (GrantedAuthority authority : collection) {
			authoritiesSet.add(authority.getAuthority());
		}
		return String.join(",", authoritiesSet);
	}

}
