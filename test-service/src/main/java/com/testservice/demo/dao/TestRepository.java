package com.testservice.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.testservice.demo.entity.Company;

public interface TestRepository extends JpaRepository<Company, Long> {
	@Query(value = "SELECT name from company where sector_name= :sectorName and ceo_name= :ceoName", nativeQuery = true)
	public List<String> getCompanyNames( String sectorName, @Param("ceoName") String ceoName);
}
