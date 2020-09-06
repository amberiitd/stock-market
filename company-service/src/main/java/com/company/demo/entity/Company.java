package com.company.demo.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.company.demo.model.StockExchange;

@Entity
//@Table(name="company")
public class Company {
	@Id
	@GeneratedValue
	private long id;
	
	private String name;
	
	private String turnover;
	
	private String ceoName;
	private String sectorName;
	private String brief;
	
	//List<StockExchange> stockExchanges= null;
	
	
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getTurnover() {
		return turnover;
	}
	public void setTurnover(String turnover) {
		this.turnover = turnover;
	}

	public String getCeoName() {
		return ceoName;
	}
	public void setCeoName(String ceoName) {
		this.ceoName = ceoName;
	}
	public String getSectorName() {
		return sectorName;
	}
	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	
//	public List<String> getStockEcxhanges() {
//		return stockExchanges;
//	}
//	public void addStockExchange(String stock) {
//		stockExchanges.add(stock);
//	}
	
	@Override
	public String toString() {
		return "Company["+this.name+" from sector: "+this.sectorName+" ]";
	}
}
