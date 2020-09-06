package com.companystock.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.companystock.demo.entity.CompanyStock;

public interface CompanyStockRepository extends CrudRepository<CompanyStock, Long> {
	@Query(value="select company_name from company_stock where stock_name= :exchangeName", nativeQuery=true)
	public List<String> getCompanyNamesByStock(String exchangeName);
	
	@Query(value="select stock_name from company_stock where company_name= :companyName", nativeQuery=true)
	public List<String> getStockNamesByCompany(String companyName);
	
	@Query(value="select code from company_stock where company_name= :companyName and stock_name= :exchangeName", nativeQuery=true)
	public List<String> getCode(String companyName, String exchangeName);
}
