package com.userservice.demo.model;

public class UserLoginResponse {
	private final String jwt;

	public UserLoginResponse(String jwt) {
		super();
		this.jwt = jwt;
	}

	public String getJwt() {
		return jwt;
	}
	
	
}
