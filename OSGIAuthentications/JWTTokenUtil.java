package com.brixip.core.web.service.authentication;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTTokenUtil {

	private static final long JWT_TOKEN_VALIDITY = 60 * 60 * 50;

	private String secret = "JWTAuthentication";

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		//for retrieveing any information from token we will need the secret key
		final Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		return claimsResolver.apply(claims);
	}

	//check if the token has expired
	private Boolean isTokenExpired(String token) {
		//retrieve expiration date from jwt token
		final Date expiration = (Date) getClaimFromToken(token, Claims::getExpiration);
		return expiration.before(new Date());
	}

	//generate token for user
	//while creating the token -
		//1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
		//2. Sign the JWT using the HS512 algorithm and secret key.
		//compaction of the JWT to a URL-safe string 
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		String subject=userDetails.getUsername();
		String token = Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
		return token;
	}

	//validate token
	public Boolean validateToken(String token, String userName) {
		//retrieve username from jwt token
		final String username = getClaimFromToken(token, Claims::getSubject);
		return (username.equals(userName) && !isTokenExpired(token));

	}
}
