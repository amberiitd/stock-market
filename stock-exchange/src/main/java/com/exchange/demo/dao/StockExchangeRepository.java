package com.exchange.demo.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.exchange.demo.entity.StockExchange;

public interface StockExchangeRepository extends CrudRepository<StockExchange, Long>{
	public List<StockExchange> findByName(String name);
}
