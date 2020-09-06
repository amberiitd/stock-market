package com.upload.demo.service;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.upload.demo.client.CompanyStockClient;
import com.upload.demo.dao.FileRepository;
import com.upload.demo.dao.UploadRepository;
import com.upload.demo.entity.ExcelFile;
import com.upload.demo.entity.StockPrice;

@Service
public class UploadServiceImpl implements UploadService{
	
	@Autowired
	private UploadRepository uploadRepo;
	
	@Autowired
	private FileRepository fileRepo;
	
	@Autowired
	private CompanyStockClient companyStockClient;

	@Override
	public void saveFileData(MultipartFile file) throws Exception{
		String fileName= file.getOriginalFilename();
		if (!fileRepo.findById(fileName).isEmpty())
			throw new Exception("duplicate file can't be uploaded");
		else if(fileName.matches(".*\\.xlsx$|.*\\.xls$|.*\\.xlsm}.*\\.csv$")) {
			InputStream fileStream= file.getInputStream();
			List<StockPrice> stockPriceList= getData(fileStream, fileName);
			for(StockPrice price: stockPriceList) {
				uploadRepo.save(price);
			}
		}
		else
			throw new Exception("Invalid File Type (Supported types: .xlsx, .xls, .xlsm, .csv)");
		
	}
	
	@Override
	@Transactional
	public List<StockPrice> getRepositoryData(String fileName) throws Exception{
		// TODO Auto-generated method stub
		Optional<ExcelFile> file= fileRepo.findById(fileName);
		if (!file.isEmpty()) {
			List<StockPrice> stockPriceList= uploadRepo.findByFileName(fileName);
			return stockPriceList;
		}
		else
			throw new Exception("File is not uploaded");
		
	}
	
	private List<StockPrice> getData(InputStream fileStream, String fileName) throws Exception{
		XSSFWorkbook workbook= new XSSFWorkbook(fileStream);
		XSSFSheet sheet= workbook.getSheet("StockPrice");
		ExcelFile excelFile= new ExcelFile();
		excelFile.setFileName(fileName);
		
		
		List<StockPrice> stockPriceList= new ArrayList<StockPrice>();
		
		
		for(int i=1; i<sheet.getPhysicalNumberOfRows(); i++) {
			try {
				try {
					StockPrice stockPrice= new StockPrice();
					stockPrice.setFileName(fileName);
					stockPrice.setCode(companyStockClient.getCode(sheet.getRow(i).getCell(0).getStringCellValue(), 
							sheet.getRow(i).getCell(1).getStringCellValue()));
					stockPrice.setCurrentPrice(sheet.getRow(i).getCell(2).getNumericCellValue());
					
					String str=sheet.getRow(i).getCell(3).getStringCellValue()
							+"T"+
							sheet.getRow(i).getCell(4).getStringCellValue()
							+ ".000+0530";
					DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.ENGLISH);
					Date date = format.parse(str);
					stockPrice.setDate(date);
					stockPriceList.add(stockPrice);
				}catch(Exception e){
					throw e;
					//throw new Exception("wrong date format");
				}
			}catch(Exception e) {
				throw e;
			}
			
		}
		fileRepo.save(excelFile);
		workbook.close();
		return stockPriceList;
		
		
	}

	@Override
	@Transactional
	public void deleteFile(String fileName) throws Exception {
		// TODO Auto-generated method stub
		if(fileRepo.findById(fileName).isEmpty())
			throw new Exception("file doesn't exist");
		else
			fileRepo.deleteById(fileName);
		
	}

	@Override
	@Transactional
	public void deleteStockPrice(String fileName) {
		// TODO Auto-generated method stub
		uploadRepo.deleteByFileName(fileName);
	}

}
