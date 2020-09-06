package com.testservice.demo;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.testservice.demo.dao.TestRepository;

@SpringBootApplication
public class TestServiceApplication implements CommandLineRunner {

	@Autowired
	private TestRepository repo;
	
	public static void main(String[] args) {
		SpringApplication.run(TestServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		//List<String> companyNameList= repo.getCompanyNames("Electronics","Robert");
		//System.out.println(companyNameList);
		//System.out.println("myFilexlsx".matches(".*\\.xlsx$"));
		//System.out.println(UUID.randomUUID().toString());
		String str="2001-07-04"+"T"+ "12:08:56"+ ".000+0530";
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.ENGLISH);
		Date date = format.parse(str);
		
		System.out.println(date);
		Timestamp timestamp= new Timestamp(date.getTime());
		System.out.println(timestamp.getTime());
	}

}
