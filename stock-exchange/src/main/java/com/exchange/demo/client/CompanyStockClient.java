package com.exchange.demo.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("company-stock-service")
public interface CompanyStockClient {
	@RequestMapping(value="/company-stock/get-company-names/{exchangeName}", method=RequestMethod.GET)
	public List<String> getCompanyNames(@PathVariable("exchangeName") String exchangeName);
}
