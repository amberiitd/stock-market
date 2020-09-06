package com.sector.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sector.demo.client.CompanyClient;
import com.sector.demo.model.Company;

public class SectorServiceImpl implements SectorService{

	@Autowired
	private CompanyClient companyClient;
	@Override
	public List<Company> getCompanies(String sectorName) {
		// TODO Auto-generated method stub
		return companyClient.getCompanyBySector(sectorName);
	}

}
