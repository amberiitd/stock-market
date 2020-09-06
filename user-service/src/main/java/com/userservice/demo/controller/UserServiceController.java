package com.userservice.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userservice.demo.dto.CustomUserDto;
import com.userservice.demo.entity.CustomUser;
import com.userservice.demo.model.UserLoginRequest;
import com.userservice.demo.model.UserLoginResponse;
import com.userservice.demo.service.CustomUserDetailsService;
import com.userservice.demo.service.UserService;
import com.userservice.demo.util.JwtUtil;

@RestController
@RequestMapping("/user-service")
public class UserServiceController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired 
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private UserService userService;
	
	@Autowired 
	private JwtUtil jwtUtil;
	
	@GetMapping("/")
	public String welcome() {
		return "Welcome to Service";
	}
	
	@GetMapping("/get-user/{username}")
	public CustomUser getUser(@PathVariable("username") String username) {
		return userService.findByUsername(username);
	}
	
//	@GetMapping("/get-companies/{sectorName}")
//	public ResponseEntity<List<Company>> getAllCompany(@PathVariable("sectorName") String sectorName) {
//		return new ResponseEntity<List<Company>>(companyClient.getCompanyBySector(sectorName), HttpStatus.OK);
//	}
	
	@GetMapping("/admin/")
	public String welcomeAdmin() {
		return "Welcome to Admin Service";
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserLoginResponse> getAuthToken(@RequestBody UserLoginRequest request) throws Exception{
		try {
			authManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword(), new ArrayList<>()));
		}catch(BadCredentialsException e) {
			throw new Exception("wrong username or password", e);
		}
		
		String token= jwtUtil.generateToken(userDetailsService.loadUserByUsername(request.getUsername()));
		return new ResponseEntity<UserLoginResponse>(new UserLoginResponse(token), HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/signup")
	public void createUser(@RequestBody CustomUserDto userDto) {
		userService.createUser(userDto);
	}
}
