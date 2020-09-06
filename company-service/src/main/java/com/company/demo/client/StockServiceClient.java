package com.company.demo.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.company.demo.model.StockExchange;

@FeignClient("stock-exchange-service")
public interface StockServiceClient {
	@RequestMapping(value="/stock-exchange/{exchangeName}", method= RequestMethod.GET)
	public StockExchange getStockExchange(@PathVariable("exchangeName") String exchangeName);
}
