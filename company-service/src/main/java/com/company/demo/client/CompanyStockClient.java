package com.company.demo.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("company-stock-service")
public interface CompanyStockClient {
	@RequestMapping(value="/company-stock/add-code/{companyName}/{exchangeName}", method= RequestMethod.GET)
	public void registerCompanyWithExchange(@PathVariable("companyName") String companyName,
				@PathVariable("exchangeName") String exchangeName );
	
	@RequestMapping(value="/company-stock/get-stock-names/{companyName}", method= RequestMethod.GET)
	public List<String> getExchangeNames(@PathVariable("companyName") String companyName);
	
	@RequestMapping(value="/company-stock/get-code/{companyName}/{exchangeName}", method= RequestMethod.GET)
	public String getCode(@PathVariable("companyName") String companyName, @PathVariable("exchangeName") String exchangeName);
}
