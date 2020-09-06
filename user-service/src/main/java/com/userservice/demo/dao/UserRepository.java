package com.userservice.demo.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.userservice.demo.entity.CustomUser;

@Repository
public interface UserRepository extends CrudRepository<CustomUser, Long>  {
	
	public CustomUser findByUsername(String username);
}
