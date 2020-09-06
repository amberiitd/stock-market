package com.companystock.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.companystock.demo.service.CompanyStockServiceImpl;

@RestController
@RequestMapping("/company-stock")
public class CompanyStockController {
	
	@Autowired
	private CompanyStockServiceImpl service;
	
	@GetMapping("/")
	public ResponseEntity<String> welcome(){
		return new ResponseEntity<String>("Welcome to Company-Stock DB", HttpStatus.OK);
		
	}
	
	@GetMapping("/get-company-names/{exchangeName}")
	public List<String> getCompanyNameList(@PathVariable("exchangeName") String exchangeName){
		return service.getCompanyNamesByStock(exchangeName);
		
	}
	
	@GetMapping("/get-stock-names/{companyName}")
	public List<String> getStockNameList(@PathVariable("companyName") String companyName){
		return service.getStockNamesByCompany(companyName);
		
	}
	
	@GetMapping("/get-code/{companyName}/{exchangeName}")
	public String getCode(@PathVariable("companyName") String companyName, @PathVariable("exchangeName") String exchangeName) throws Exception{
		return service.getCode(companyName, exchangeName);
		
	}
	
	@GetMapping("/add-code/{companyName}/{exchangeName}")
	public void addCode(@PathVariable("companyName") String companyName, @PathVariable("exchangeName") String exchangeName){
		service.addCompanyStock(companyName, exchangeName);
		
	}
	

}
