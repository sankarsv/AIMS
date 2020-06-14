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
import com.app.aims.beans.BatchAuditDetails;
import com.app.aims.beans.BillingFileData;
import com.app.aims.beans.ClarityFileData;
import com.app.aims.beans.FileData;
import com.app.aims.dao.BatchDao;
import com.app.aims.dao.EmployeeDao;
import com.app.aims.security.model.UploadXlsRequest;
import com.app.aims.service.UploadXlsService;


@Service
public  class UploadXlsServiceImpl implements UploadXlsService {

	@Autowired
    EmployeeDao userDao;
    
	@Autowired
	BatchDao batchDao;

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
			 long id = System.currentTimeMillis();
			 Date date = new Date();
			 fileData.setFileId(id);
			 fileData.setUploadTime(date);
			 fileData.setFileData(byteStream);
			 batchDao.updateStatusIfAlreadyExistHC();
			 boolean isSuccess = userDao.uploadFile(fileData);
			 if(isSuccess) {
				 BatchAuditDetails batchAuditDetails = new BatchAuditDetails();
				 batchAuditDetails.setLoadDate(date);
				 batchAuditDetails.setBatchStatus("U");
				 batchAuditDetails.setFileId(id);
				 batchAuditDetails.setFileType("HC");
				 batchDao.saveFileProcessDetails(batchAuditDetails);
			 }
		 }
		catch (Exception e) { 
			System.out.println("Error "+ e.getMessage());
		} 

	}

	@Override
	public void uploadBrXls(byte[] byteStream) {
		
		//if(null == uploadXlsRequest || StringUtils.isNullOrEmpty(uploadXlsRequest.getXlsBytes()) ) {
			//throw new InvalidParameterException("Please provide valid xls data.");
		//}
		
		 try {

			 BillingFileData billingFileData = new BillingFileData();
			 long id = System.currentTimeMillis();
			 Date date = new Date();
			 billingFileData.setFileId(id);
			 billingFileData.setUploadTime(date);
			 billingFileData.setFileData(byteStream);
			 batchDao.updateStatusIfAlreadyExistBR();
			 boolean isSuccess = userDao.uploadBrFile(billingFileData);
			 if(isSuccess) {
				 BatchAuditDetails batchAuditDetails = new BatchAuditDetails();
				 batchAuditDetails.setLoadDate(date);
				 batchAuditDetails.setBatchStatus("U");
				 batchAuditDetails.setFileId(id);
				 batchAuditDetails.setFileType("BR");
				 batchDao.saveFileProcessDetails(batchAuditDetails);
			 }
		 }
		catch (Exception e) { 
			System.out.println("Error "+ e.getMessage());
		} 

	}


	@Override
	public void uploadClXls(byte[] byteStream) {
		try {

			 ClarityFileData clarityFileData = new ClarityFileData();
			 long id = System.currentTimeMillis();
			 Date date = new Date();
			 clarityFileData.setFileId(id);
			 clarityFileData.setUploadTime(date);
			 clarityFileData.setFileData(byteStream);
			 batchDao.updateStatusIfAlreadyExistCl();
			 boolean isSuccess = userDao.uploadClFile(clarityFileData);
			 if(isSuccess) {
				 BatchAuditDetails batchAuditDetails = new BatchAuditDetails();
				 batchAuditDetails.setLoadDate(date);
				 batchAuditDetails.setBatchStatus("U");
				 batchAuditDetails.setFileId(id);
				 batchAuditDetails.setFileType("CL");
				 batchDao.saveFileProcessDetails(batchAuditDetails);
			 }
		 }
		catch (Exception e) { 
			System.out.println("Error "+ e.getMessage());
		} 
		
	}

	
}
