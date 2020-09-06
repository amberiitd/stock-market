package com.exchange.demo.service;

import java.util.Iterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exchange.demo.dao.RemarkRepository;
import com.exchange.demo.dao.StockExchangeRepository;
import com.exchange.demo.entity.Remark;
import com.exchange.demo.entity.StockExchange;

@Service
public class StockExchangeServiceImpl implements StockExchangeService{
	
	@Autowired
	private StockExchangeRepository exchangeRepo;
	@Autowired
	private RemarkRepository remarkRepo;
	
	@Override
	public Iterable<StockExchange> getAllStockExchange() {
		// TODO Auto-generated method stub
		return exchangeRepo.findAll();
	}

	@Override
	public StockExchange getStockExchange(String exchangeName) {
		// TODO Auto-generated method stub
		Iterator<StockExchange> iter= exchangeRepo.findByName(exchangeName).listIterator(); 
		if (iter.hasNext())
			return iter.next();
		else
			return new StockExchange();
	}

	@Override
	public StockExchange addStockExchange(StockExchange stockExchange) {
		// TODO Auto-generated method stub
		exchangeRepo.save(stockExchange);
		return stockExchange;
	}

	@Override
	public Remark addRemark(String exchangeName, Remark remark) {
		// TODO Auto-generated method stub
		Iterator<StockExchange> iter= exchangeRepo.findByName(exchangeName).listIterator();
		if (iter.hasNext()) {
			StockExchange stockExchange= iter.next();
			remark.setStockExchange(stockExchange);
			stockExchange.addRemark(remark);
			remarkRepo.save(remark);
			exchangeRepo.save(stockExchange);
			
			return remark;
			
		}
		else
			return new Remark();
		
		
	}
	

}
