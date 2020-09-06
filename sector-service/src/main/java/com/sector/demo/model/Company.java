package com.sector.demo.model;

public class Company {
	private long id;
	
	private String name;
	private String turnover;
	private String ceoName;
	private String sectorName;
	private String brief;
	
	
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

	@Override
	public String toString() {
		return "Company["+this.name+" from sector: "+this.sectorName+" ]";
	}
}
