package com.company.demo.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@JsonIgnoreProperties("company")
public class Remark {
	@Id
	@GeneratedValue
	private long id;
	
	private String descr;
	
	@JsonProperty("company")
	@ManyToOne(fetch=FetchType.LAZY )
	private Company company;

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public long getId() {
		return id;
	}
	
	
	
	

}
