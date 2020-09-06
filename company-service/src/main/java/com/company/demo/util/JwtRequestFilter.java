package com.company.demo.util;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.company.demo.service.CustomUserDetailsService;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{
	
	@Autowired
	private Environment env;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authHeader= request.getHeader("Authorization");
		
		String jwt= null;
		String username= null;
	
		if(authHeader!=null && authHeader.startsWith(env.getProperty("api.security.jwt-prefix"))) {
			jwt= authHeader.substring(env.getProperty("api.security.jwt-prefix").length()+1);
			username= jwtUtil.extractUsername(jwt);
			System.out.println(username+" from Filter call");

		}
		
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			
			if(jwtUtil.validateToken(jwt, userDetails)) {
				System.out.println(" validated  call");
				UsernamePasswordAuthenticationToken authentication= 
						new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		filterChain.doFilter(request, response);
	}

}
