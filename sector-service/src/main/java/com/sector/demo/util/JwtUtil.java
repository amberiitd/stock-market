package com.sector.demo.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	
	@Autowired
	private Environment env;
	
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	public Date extractExpiration(String token) {
		Date date= extractClaim(token, Claims::getExpiration);
	//	System.out.println(date+" from Util call");
		return date;
		
	}
	
	public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
		final Claims claims= extractAllClaims(token);
		return claimResolver.apply(claims);
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(env.getProperty("api.security.jwt-SECRET_KEY")).parseClaimsJws(token).getBody();
	}
	
	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, userDetails.getUsername());
	}
	
	private String createToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
					.setExpiration(new Date(System.currentTimeMillis()+ 100*60*60*10))
					.signWith(SignatureAlgorithm.HS256, env.getProperty("api.security.jwt-SECRET_KEY")).compact();
	}
	
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username= extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

}
