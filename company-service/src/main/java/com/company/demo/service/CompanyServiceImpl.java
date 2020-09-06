package com.company.demo.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.demo.client.CompanyStockClient;
import com.company.demo.client.StockServiceClient;
import com.company.demo.dao.CompanyRepository;
import com.company.demo.dao.StockPriceRepository;
import com.company.demo.entity.Company;
import com.company.demo.entity.StockPrice;
import com.company.demo.model.StockExchange;



@Service
public class CompanyServiceImpl implements CompanyService {
	@Autowired
	private CompanyRepository companyRepo;
	
	@Autowired
	private StockPriceRepository stockPriceRepo;
	
	@Autowired
	private CompanyStockClient companyStockClient;
	
	@Autowired
	private StockServiceClient stockServiceClient;
	
	
	@Override
	public List<Company> getMatchingCompanies(String pattern) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Company> getAllCompany() {
		// TODO Auto-generated method stub
		return companyRepo.findAll();
	}

	@Override
	public Company getCompanyByName(String companyName) {
		// TODO Auto-generated method stub
		Iterator<Company> iter= companyRepo.findByName(companyName).iterator();
		if(iter.hasNext())
			return iter.next();
		else
			return new Company();
		
	}
	
	@Override
	public List<Company> getCompanyBySectorName(String sectorName) {
		// TODO Auto-generated method stub
		return companyRepo.findBySectorName(sectorName);
	}
	
	@Override
	public String getCompanyDetail(String companyName) {
		Iterator<Company> iter= companyRepo.findByName(companyName).iterator();
		if(iter.hasNext()) {
			Company company= iter.next();
			return "Name: "+company.getName()+"\n"+
			"Sector: "+company.getSectorName()+"\n"+
			"CEO: "+company.getCeoName()+"\n"+
			"Introduction: "+company.getBrief()+"\n";
		}
		else
			return "null";
	}

	@Override
	public void addCompany(Company company) {
		companyRepo.save(company);
		
	}

	@Override
	public void registerCompany(String companyName, List<String> exchangeNameList) {
		// TODO Auto-generated method stub
		for(String exchangeName: exchangeNameList) {
			companyStockClient.registerCompanyWithExchange(companyName, exchangeName);
		}
		
	}

	@Override
	public List<StockExchange> getStockExchanges(String companyName) {
		// TODO Auto-generated method stub
		List<String> exchangeNameList= companyStockClient.getExchangeNames(companyName);
		List<StockExchange> stockExchanges= new ArrayList<StockExchange>();
		for(String exchangeName: exchangeNameList ) {
			stockExchanges.add(stockServiceClient.getStockExchange(exchangeName));
		}
		return stockExchanges;
	}

	@Override
	public void addStockPrice(StockPrice stockPrice) {
		// TODO Auto-generated method stub
		stockPriceRepo.save(stockPrice);
		
	}

	@Override
	@Transactional
	public int removeStockPrice(String companyName, String exchangeName, String from, String to) {
		// TODO Auto-generated method stub
		return stockPriceRepo.removeStockPrice(companyStockClient.getCode(companyName, exchangeName), from, to);
		
	}

	@Override
	public List<StockPrice> retrieveStockPrice(String companyName, String exchangeName, String from, String to) {
		// TODO Auto-generated method stub
		return stockPriceRepo.getStockPrice(companyStockClient.getCode(companyName, exchangeName), from, to);
		
	}

	
	
}
