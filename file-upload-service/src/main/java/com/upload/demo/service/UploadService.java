package com.upload.demo.service;

import java.io.InputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.upload.demo.entity.StockPrice;

public interface UploadService {
	public void saveFileData(MultipartFile file ) throws Exception;
	public List<StockPrice> getRepositoryData(String fileName) throws Exception;
	public void deleteFile(String fileName) throws Exception;
	public void deleteStockPrice(String fileName);
}
