package com.exchange.demo.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.exchange.demo.model.Company;

@FeignClient("company-service")
public interface CompanyClient {
	
	@RequestMapping(value="/company/{companyName}", method= RequestMethod.GET)
	public Company getCompany(@PathVariable("companyName") String companyName);

}
