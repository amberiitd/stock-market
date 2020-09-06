package com.upload.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("company-stock-service")
public interface CompanyStockClient {
	@RequestMapping(value="/company-stock/get-code/{companyName}/{exchangeName}", method= RequestMethod.GET)
	public String getCode(@PathVariable("companyName") String companyName, @PathVariable("exchangeName") String exchangeName );

}
