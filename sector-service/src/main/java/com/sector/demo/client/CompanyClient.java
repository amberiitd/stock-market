package com.sector.demo.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sector.demo.model.Company;

@FeignClient("company-service")
public interface CompanyClient {
	
	@RequestMapping(value="/company/get-company-in-sector/{sectorName}", method= RequestMethod.GET )
	public List<Company> getCompanyBySector(@PathVariable("sectorName") String sectorName);
}
