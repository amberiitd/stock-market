package com.sector.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sector.demo.client.CompanyClient;
import com.sector.demo.model.Company;

@RestController
@RequestMapping("/sector")
public class SectorServiceController {
	
	@Autowired
	private CompanyClient companyClient;
	
	@GetMapping("/")
	public String welcome() {
		return "Welcome To Sector Service";
	}
	
	@GetMapping("/get-companies/{sectorName}")
	public ResponseEntity<List<Company>> getAllCompany(@PathVariable("sectorName") String sectorName){
		return new ResponseEntity<List<Company>>(companyClient.getCompanyBySector(sectorName),HttpStatus.OK);
	}
	
	
}
