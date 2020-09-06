package com.userservice.demo.service;

import com.userservice.demo.dto.CustomUserDto;
import com.userservice.demo.entity.CustomUser;

public interface UserService {
	public CustomUser findByUsername(String username);
	public void createUser(CustomUserDto userDto);
	
}
