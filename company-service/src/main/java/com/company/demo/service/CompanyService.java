package com.company.demo.service;

import java.util.List;
import java.util.Optional;

import com.company.demo.entity.Company;
import com.company.demo.model.StockExchange;
import com.company.demo.entity.StockPrice;


public interface CompanyService {
	public List<Company> getMatchingCompanies(String pattern);
	public List<Company> getAllCompany();
	public Company getCompanyByName(String companyName);
	public List<Company> getCompanyBySectorName(String sectorName);
	public String getCompanyDetail(String companyName);
	public void addCompany(Company company);
	public void registerCompany(String companyName, List<String> exchangeNameList);
	public List<StockExchange> getStockExchanges( String companyName);
	public void addStockPrice(StockPrice stockPrice);
	public int removeStockPrice(String companyName, String exchangeName, String from, String to);
	public List<StockPrice> retrieveStockPrice(String companyName, String exchangeName, String from, String to);
	//public String getCompanyIPODetails(String companyName);

}
