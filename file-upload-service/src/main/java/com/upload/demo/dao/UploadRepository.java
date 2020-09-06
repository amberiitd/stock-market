package com.upload.demo.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.upload.demo.entity.StockPrice;

public interface UploadRepository extends CrudRepository<StockPrice, Long> {
	public List<StockPrice> findByFileName(String fileName);
	public void deleteByFileName(String fileName);
}
