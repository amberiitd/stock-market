package com.exchange.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exchange.demo.client.CompanyClient;
import com.exchange.demo.client.CompanyStockClient;
import com.exchange.demo.entity.Remark;
import com.exchange.demo.entity.StockExchange;
import com.exchange.demo.model.Company;
import com.exchange.demo.service.StockExchangeServiceImpl;

@RestController
@RequestMapping("/stock-exchange")
public class StockExchangeController {
	@Autowired
	private StockExchangeServiceImpl service;
	
	@Autowired
	private CompanyStockClient companyStockClient;
	
	@Autowired
	private CompanyClient companyClient;
	
	@GetMapping("/")
	public ResponseEntity<String> welcome(){
		return new ResponseEntity<String>("Welcome to Stock Exchange Service", HttpStatus.OK);
		
	}
	
	@GetMapping("/get-all")
	public ResponseEntity<Iterable<StockExchange>> getAllExchanges(){
		return new ResponseEntity<Iterable<StockExchange>> (service.getAllStockExchange(), HttpStatus.OK);
		
	}
	
	@GetMapping("/{exchangeName}")
	public StockExchange getExchange(@PathVariable("exchangeName") String exchangeName){
		return service.getStockExchange(exchangeName);
	}
	
	@PostMapping("/add-exchange")
	public ResponseEntity<StockExchange> addExchange(@RequestBody StockExchange stockExchange){
		return new ResponseEntity<StockExchange>(service.addStockExchange(stockExchange),HttpStatus.CREATED);
	}
	
	@PostMapping("/add-remark")
	public ResponseEntity<Remark> addRemark(@RequestBody WrapTwo<String, Remark> wrap){
		return new ResponseEntity<Remark>(service.addRemark(wrap.getObj1(), wrap.getObj2()),HttpStatus.CREATED);
	}
	
	@GetMapping("/get-companies/{exchangeName}")
	public ResponseEntity<List<Company>> getCompanyByStock(@PathVariable("exchangeName") String exchangeName){
		List<String> companyNameList= companyStockClient.getCompanyNames(exchangeName) ;
		List<Company> companies= new ArrayList<Company>();
		for(String companyName: companyNameList) {
			companies.add(companyClient.getCompany(companyName));
		}
		
		return new ResponseEntity<List<Company>>(companies, HttpStatus.OK);
		
	}
	
	
	
	
	
}
