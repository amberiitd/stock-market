package com.exchange.demo.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@JsonIgnoreProperties("stockExchange")
public class Remark {
	@Id
	@GeneratedValue
	private long id;
	private String descr;
	
	@JsonProperty("stockExchange")
	@ManyToOne(fetch= FetchType.LAZY)
	private StockExchange stockExchange;
	
	
	
	public Remark() {
		super();
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}
	
	
	public StockExchange getStockExchange() {
		return stockExchange;
	}

	public void setStockExchange(StockExchange stockExchange) {
		this.stockExchange = stockExchange;
	}

	public long getId() {
		return id;
	}
	
	

}
