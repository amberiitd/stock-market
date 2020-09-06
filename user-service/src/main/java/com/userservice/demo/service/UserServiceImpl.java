package com.userservice.demo.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.userservice.demo.dao.UserRepository;
import com.userservice.demo.dto.CustomUserDto;
import com.userservice.demo.entity.CustomUser;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepo;
	
	private ModelMapper mapper= new ModelMapper();
	
	
	private PasswordEncoder bCryptPasswordEncoder= new BCryptPasswordEncoder();
	
	@Override
	public CustomUser findByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepo.findByUsername(username);
	}


	@Override
	@Transactional
	public void createUser(CustomUserDto userDto) {
		// TODO Auto-generated method stub
		CustomUser user= mapper.map(userDto, CustomUser.class);
		//user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setPassword(user.getPassword());
		user.setRole("USER");
		userRepo.save(user);
	}

}
