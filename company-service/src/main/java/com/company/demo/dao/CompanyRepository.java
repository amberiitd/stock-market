package com.company.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.company.demo.entity.Company;

public interface CompanyRepository extends JpaRepository<Company,Long>{
	
	public List<Company> findByName(String companyName); 
	public List<Company> findBySectorName(String sectorName);
	
}
