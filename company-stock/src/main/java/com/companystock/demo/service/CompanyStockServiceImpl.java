package com.companystock.demo.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.companystock.demo.dao.CompanyStockRepository;
import com.companystock.demo.entity.CompanyStock;

@Service
public class CompanyStockServiceImpl implements CompanyStockService {
	
	@Autowired
	private CompanyStockRepository repo;
	
	@Override
	public List<String> getCompanyNamesByStock(String exchangeName) {
		// TODO Auto-generated method stub
		return repo.getCompanyNamesByStock(exchangeName);
	}

	@Override
	public List<String> getStockNamesByCompany(String companyName) {
		// TODO Auto-generated method stub
		return repo.getStockNamesByCompany(companyName);
	}

	@Override
	public String getCode(String companyName, String exchangeName) throws Exception {
		// TODO Auto-generated method stub
		Iterator<String> iter= repo.getCode(companyName, exchangeName).iterator();
		if(iter.hasNext())
			return iter.next();
		else
			throw new Exception(companyName+" or "+exchangeName+" is not registered");
	}

	@Override
	public void addCompanyStock(String companyName, String exchangeName) {
		CompanyStock entity= new CompanyStock();
		entity.setCompanyName(companyName);
		entity.setStockName(exchangeName);
		entity.setCode(companyName+exchangeName);
		
		repo.save(entity);
		
	}
	
	
	

}
