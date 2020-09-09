package com.company.demo.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.company.demo.entity.IPODetail;

public interface IPODetailRepository extends CrudRepository<IPODetail, Long>{
	public List<IPODetail> findByCompanyName(String companyName);
	
}
