package com.app.aims.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.security.InvalidParameterException;
import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;

import com.mysql.cj.util.StringUtils;
import com.app.aims.beans.FileData;
import com.app.aims.dao.EmployeeDao;
import com.app.aims.security.model.UploadXlsRequest;
import com.app.aims.service.UploadXlsService;


@Service
public  class UploadXlsServiceImpl implements UploadXlsService {

	@Autowired
    EmployeeDao userDao;
    

	public EmployeeDao getUserDao() {
		return userDao;
	}


	public void setUserDao(EmployeeDao userDao) {
		this.userDao = userDao;
	}


	@Override
	public void uploadXls(byte[] byteStream) {
		
		//if(null == uploadXlsRequest || StringUtils.isNullOrEmpty(uploadXlsRequest.getXlsBytes()) ) {
			//throw new InvalidParameterException("Please provide valid xls data.");
		//}
		
		 try {

			 FileData fileData = new FileData();
			 fileData.setUploadTime(new Date());
			 fileData.setFileData(byteStream);
			 userDao.uploadFile(fileData);
		 }
		catch (Exception e) { 
			System.out.println("Error "+ e.getMessage());
		} 

	}


	
}
