package com.upload.demo.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.upload.demo.entity.StockPrice;

@FeignClient("company-service")
public interface CompanyClient {
	@RequestMapping(value="/company/add-stock-price-list", method= RequestMethod.POST)
	public void pushData(@RequestBody List<StockPrice> stockpriceList);
}
