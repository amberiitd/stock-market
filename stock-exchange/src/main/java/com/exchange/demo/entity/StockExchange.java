package com.exchange.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class StockExchange {
	@Id
	@GeneratedValue
	private long id;
	
	private String name;
	private String contactAddress;
	private String brief;
	
	@OneToMany(mappedBy="stockExchange")
	private List<Remark> remarks= new ArrayList<Remark>();

	public StockExchange() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public long getId() {
		return id;
	}

	public List<Remark> getRemarks() {
		return remarks;
	}
	
	public void addRemark(Remark remark ) {
		remarks.add(remark);
	}

}
