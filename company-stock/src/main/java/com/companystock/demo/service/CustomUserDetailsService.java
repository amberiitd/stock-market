package com.companystock.demo.service;

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

import com.companystock.demo.client.UserClient;
import com.companystock.demo.model.CustomUser;


@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private UserClient userClient;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("Sector Service called");
		CustomUser user= userClient.getByUsername(username);
		System.out.println(user.getUsername()+" "+user.getPassword()+" from Client call");
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(user.getRole()));
		return new User(user.getUsername(), user.getPassword(), authorities);
	}

}