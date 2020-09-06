package com.userservice.demo.util;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.userservice.demo.service.CustomUserDetailsService;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{

	@Autowired
	private Environment env;


	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		final String authHeader= request.getHeader("Authorization");
		String username= null;
		String jwt= null;
		
		if(authHeader != null && authHeader.startsWith(env.getProperty("api.security.jwt-prefix"))) {
			jwt = authHeader.substring(env.getProperty("api.security.jwt-prefix").length()+1);
			//System.out.println(jwt);
			username= jwtUtil.extractUsername(jwt);
		
		}
		
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()== null) {
			
			UserDetails userDetails= userDetailsService.loadUserByUsername(username);
			if(jwtUtil.validateToken(jwt, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=
							new UsernamePasswordAuthenticationToken( userDetails, null, userDetails.getAuthorities());
				//System.out.println(userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		
		filterChain.doFilter(request, response);
	}

}
