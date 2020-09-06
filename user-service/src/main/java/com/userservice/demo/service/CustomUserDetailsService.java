package com.userservice.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.userservice.demo.entity.CustomUser;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("Service called");
		CustomUser user= userService.findByUsername(username);
		
		List<GrantedAuthority> authorities= (new ArrayList<GrantedAuthority>());
		authorities.add(new SimpleGrantedAuthority(user.getRole()));
		return new User(user.getUsername(),user.getPassword(), authorities);
		 
	}

}
