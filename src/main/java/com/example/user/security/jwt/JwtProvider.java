package com.example.user.security.jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${test.app.jwtSecret}")
    private String jwtSecret;

    @Value("${test.app.jwtExpiration}")
    private int jwtExpiration;

    public String generateJwtToken(Authentication authentication) {

        JwtUser userPrincipal = (JwtUser) authentication.getPrincipal();

        return Jwts.builder()
		                .setSubject((userPrincipal.getUsername()))
		                .setIssuedAt(new Date())
		                .setExpiration(new Date((new Date()).getTime() + jwtExpiration*1000))
		                .signWith(SignatureAlgorithm.HS512, jwtSecret)
		                .compact();
    }
    
    public Claims getClaimsJws(String authToken){
    	
    	try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
        } catch (io.jsonwebtoken.SignatureException e) {
            logger.error("Invalid JWT signature -> Message: {} ", e);
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token -> Message: {}", e);
        } catch (ExpiredJwtException e) {
        	return e.getClaims();
         
            
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token -> Message: {}", e);
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty -> Message: {}", e);
        }
    	return null;
    }
	/*
	 * public String doGenerateRefreshToken(Map<String, Object> claims, String
	 * subject) {
	 * 
	 * return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new
	 * Date(System.currentTimeMillis())) .setExpiration(new
	 * Date(System.currentTimeMillis() + refreshExpirationDate*1000))
	 * .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	 * 
	 * }
	 */
	
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (io.jsonwebtoken.SignatureException e) {
            logger.error("Invalid JWT signature -> Message: {} ", e);
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token -> Message: {}", e);
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token -> Message: {}", e);
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token -> Message: {}", e);
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty -> Message: {}", e);
        }
        
        return false;
    }
    
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
			                .setSigningKey(jwtSecret)
			                .parseClaimsJws(token)
			                .getBody().getSubject();
    }
}