package com.exchange.demo.service;

import java.util.Optional;

import com.exchange.demo.entity.Remark;
import com.exchange.demo.entity.StockExchange;

public interface StockExchangeService {
	
	public Iterable<StockExchange> getAllStockExchange();
	public StockExchange getStockExchange(String exchangeName);
	public StockExchange addStockExchange(StockExchange stockExchange);
	public Remark addRemark(String exchangeName,Remark remark);
}
