package com.companystock.demo.service;

import java.util.List;

public interface CompanyStockService {
	public List<String> getCompanyNamesByStock(String exchangeName);
	public List<String> getStockNamesByCompany(String companyName);
	public String getCode(String companyName, String exchangeName) throws Exception;
	public void addCompanyStock(String companyName, String exchangeName);

}
