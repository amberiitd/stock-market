package com.company.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.demo.entity.Company;
import com.company.demo.entity.IPODetail;
import com.company.demo.entity.StockPrice;
import com.company.demo.model.StockExchange;
import com.company.demo.service.CompanyService;
import com.company.demo.wrapper.WrapTwo;

@RestController
@RequestMapping("/company")
public class CompanyServiceController {
	@Autowired
	private CompanyService companyService;
	
	@GetMapping("/")
	public ResponseEntity<String> welcome() {
		return new ResponseEntity<String>("Welcome to Company Service", HttpStatus.OK);
	}
	
	@GetMapping("/get-all")
	public List<Company> getAllCompany(){
		return companyService.getAllCompany();
	}
	
	@GetMapping("/{companyName}")
	public Company getCompany(@PathVariable("companyName") String companyName ){
		return companyService.getCompanyByName(companyName);
	}
	
	@GetMapping("/get-matches/{pattern}")
	public ResponseEntity<List<Company>> getMatchingCompany(@PathVariable("pattern") String pattern ){
		return new ResponseEntity<List<Company>>(companyService.getMatchingCompanies(pattern), HttpStatus.OK);
	}
	
	@GetMapping("/get-stock-exchanges/{companyName}")
	public List<StockExchange> getStockList(@PathVariable("companyName") String companyName ){
		return companyService.getStockExchanges(companyName);
	}
	
	@PostMapping("/add-company")
	public ResponseEntity<String> addCompany(@RequestBody Company company){
		companyService.addCompany(company);
		return new ResponseEntity<String>("Succes", HttpStatus.CREATED);
	}
	
	@PostMapping("/register-company")
	public ResponseEntity<String> registerCompany(@RequestBody WrapTwo<String,List<String>> wrap){
		companyService.registerCompany(wrap.getObj1(), wrap.getObj2());
		return new ResponseEntity<String>("Succes", HttpStatus.OK);
	}
	
	@GetMapping("/get-detail/{companyName}")
	public ResponseEntity<String> getCompanyDetails(@PathVariable("companyName") String companyName) {
		
		return new ResponseEntity<String>(companyService.getCompanyDetail(companyName), HttpStatus.FOUND);
	}
	
	@GetMapping("/get-company-in-sector/{sectorName}")
	public List<Company> getCompanyBySector(@PathVariable("sectorName") String sectorName){
		return companyService.getCompanyBySectorName(sectorName);
	}
	
	@PostMapping("/add-stock-price")
	public void pushData(@RequestBody StockPrice stockPrice) {
		companyService.addStockPrice(stockPrice);
	}
	
	
	@PostMapping("/add-stock-price-list")
	public void pushData(@RequestBody List<StockPrice> stockPriceList) {
		for(StockPrice stockPrice: stockPriceList) {
			companyService.addStockPrice(stockPrice);
		}
	}
	
	@GetMapping("/get-stock-price/{companyName}/{exchangeName}/{from}/{to}")
	public ResponseEntity<List<StockPrice>> retrieveStockPrice(@PathVariable("companyName") String companyName,
			@PathVariable("exchangeName") String exchangeName, @PathVariable("from") String from,
			@PathVariable("to") String to){
		
		return new ResponseEntity<List<StockPrice>>(companyService.retrieveStockPrice(companyName, exchangeName, from, to), HttpStatus.OK);
		
	}
	@GetMapping("/remove-stock-price/{companyName}/{exchangeName}/{from}/{to}")
	public ResponseEntity<String> deleteStockPrice(@PathVariable("companyName") String companyName,
			@PathVariable("exchangeName") String exchangeName, @PathVariable("from") String from,
			@PathVariable("to") String to){
		 int count=companyService.removeStockPrice(companyName, exchangeName, from, to);
		 return new ResponseEntity<String>(count+ " row(s) deleted", HttpStatus.OK);
	}
	
	@GetMapping("/get-IPO/{companyName}")
	public ResponseEntity<List<IPODetail>> getIPODetails(@PathVariable("companyName") String companyName ){
		return new ResponseEntity<List<IPODetail>>(companyService.getIPODetails(companyName), HttpStatus.OK);
	}
	
	@GetMapping("/add-IPO")
	public void addIPODetails(@RequestBody IPODetail ipo ){
		companyService.addIPODetail(ipo);
	}
	

}
