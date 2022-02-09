package com.example.template.demo_template.util.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;


@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);
    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {
    	
    	
    	logger.error("Responding with unauthorized error. Message - {}", e.getMessage());

    	if(e.getLocalizedMessage().equals("Bad credentials") || e.getLocalizedMessage().equals("UserDetailsService returned null, which is an interface contract violation")) {
    		
    		httpServletResponse.sendRedirect("/api/auth/bad-credentials");    		
    		
    	} else {
    		
    		httpServletResponse.sendRedirect("/expired-token");
    		
    	}
    	
    	
                

//        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,
//                "Sorry, You're not authorized to access this resource.");
    }
}