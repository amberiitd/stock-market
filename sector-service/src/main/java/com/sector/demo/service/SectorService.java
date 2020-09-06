package com.sector.demo.service;

import java.util.List;

import com.sector.demo.model.Company;

public interface SectorService {
	public List<Company> getCompanies(String sectorName);

}
