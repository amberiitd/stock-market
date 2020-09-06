package com.userservice.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.userservice.demo.dao.UserRepository;
import com.userservice.demo.entity.CustomUser;

@SpringBootApplication
@EnableEurekaClient
public class UserServiceApplication implements CommandLineRunner{
	
	
	private PasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	@Autowired 
	private UserRepository userRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		
		// TODO Auto-generated method stub
		//bCryptPasswordEncoder.encode("demon")
		CustomUser user= new CustomUser("amber", "demon", "ADMIN" );
		userRepo.save(user);
	}
	
	

}
