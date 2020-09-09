package com.company.demo.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class IPODetail {
	@Id
	@GeneratedValue
	private long id;
	private String companyName;
	private String exchangeName;
	private float pricePerShare;
	private long totalShare;
	private Date dateTime;
	
	@OneToMany(mappedBy="company")
	private List<Remark> remarks = new ArrayList<Remark>();
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getExchangeName() {
		return exchangeName;
	}
	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}
	public float getPricePerShare() {
		return pricePerShare;
	}
	public void setPricePerShare(float pricePerShare) {
		this.pricePerShare = pricePerShare;
	}
	public long getTotalShare() {
		return totalShare;
	}
	public void setTotalShare(long totalShare) {
		this.totalShare = totalShare;
	}
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	public List<Remark> getRemarks() {
		return remarks;
	}
	public void addRemark(Remark remark) {
		this.remarks.add(remark);
	}
	public long getId() {
		return id;
	}
	
	

}
