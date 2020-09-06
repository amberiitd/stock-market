package com.company.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.company.demo.entity.StockPrice;

public interface StockPriceRepository extends CrudRepository<StockPrice, Long>{
	@Modifying
	@Query(value="delete from stock_price where code= :code and date>= :from and date < :to", nativeQuery=true)
	public int removeStockPrice(String code, String from, String to);
	//
	
	@Query(value="select * from stock_price where code= :code and date>= :from and date < :to order by date", nativeQuery=true)
	public List<StockPrice> getStockPrice(String code, String from, String to);
	//
}
