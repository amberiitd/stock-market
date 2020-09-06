package com.upload.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.upload.demo.client.CompanyClient;
import com.upload.demo.service.UploadService;

@RestController
@RequestMapping("/upload-service")
public class UploadServiceController {
	@Autowired
	private UploadService service;
	
	@Autowired
	private CompanyClient companyClient;
	
	@GetMapping("/")
	public ResponseEntity<String> welcome(){
		return new ResponseEntity<String>("Welcome to File Upload Service", HttpStatus.OK);
	}
	
	@PostMapping("/upload")
	public ResponseEntity<String> uploadExcelFile(@RequestBody MultipartFile file) throws Exception {
		service.saveFileData(file);
		return new ResponseEntity<String>(file.getOriginalFilename()+" uploaded: Success", HttpStatus.OK);
	}
	
	@PostMapping("/commit/{fileName}")
	public ResponseEntity<String> commitFile(@PathVariable("fileName") String fileName) throws Exception{
		
		companyClient.pushData(service.getRepositoryData(fileName));
		service.deleteFile(fileName);
		service.deleteStockPrice(fileName);
		return new ResponseEntity<String>(fileName+" commited: Success", HttpStatus.CREATED);
	}
	
	@GetMapping("/remove-file/{fileName}")
	public ResponseEntity<String> removeFile(@PathVariable("fileName") String fileName) throws Exception{
		service.deleteFile(fileName);
		service.deleteStockPrice(fileName);
		return new ResponseEntity<String>(fileName+" deleted along with the data", HttpStatus.OK);
	}
	
}
