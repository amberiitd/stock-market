package com.upload.demo.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.upload.demo.entity.ExcelFile;

public interface FileRepository extends CrudRepository<ExcelFile, String>{

}
