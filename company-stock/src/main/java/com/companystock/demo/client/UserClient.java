package com.companystock.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.companystock.demo.model.CustomUser;


@FeignClient("user-service")
public interface UserClient {
	
	@RequestMapping(value="/user-service/get-user/{username}", method= RequestMethod.GET)
	public CustomUser getByUsername(@PathVariable("username") String username);

}