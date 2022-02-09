package com.example.template.demo_template.util.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.example.template.demo_template.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("sisint_application")
    private String jwtSecret;

    @Value((1000 * 60 * 60) + "")
    private int jwtExpirationInMs;

    public String generateToken(Authentication authentication) {    	    

    	Date now = new Date();        
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);
    	
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
    		    		
    		
        return Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getId()))                    
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();                 

    }
    
    public String generateToken(User user) {    	    

    	Date now = new Date();        
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs); 	
    		
        return Jwts.builder()
                .setSubject(Long.toString(user.getUserId()))                    
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();                        

    }

    public Integer getUserIdFromJWT(String token) {

        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return Integer.parseInt(claims.getSubject());        
    }

    public boolean validateToken(String authToken) {
    	
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
            ex.printStackTrace();
        // } catch (ExpiredJwtException ex) {
        //     logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
        }
        return false;
    }
}