package com.app.aims.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.security.InvalidParameterException;
import java.util.Base64;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;

import com.mysql.cj.util.StringUtils;
import com.app.aims.security.model.UploadXlsRequest;
import com.app.aims.service.UploadXlsService;


@Service
public  class UploadXlsServiceImpl implements UploadXlsService {

	@Override
	public void uploadXls(UploadXlsRequest uploadXlsRequest) {
		
		if(null == uploadXlsRequest || StringUtils.isNullOrEmpty(uploadXlsRequest.getXlsBytes()) ) {
			//throw new InvalidParameterException("Please provide valid xls data.");
		}
		
		 try {
		
		   File savedFile = new File(getClass().getClassLoader().getResource("uploadedFiles/testing.xls").getFile());
		   System.out.println(savedFile.getName() +" file name");
		   byte[] bytesArray = null;		
		   bytesArray = Files.readAllBytes(savedFile.toPath());
		   
		 
		   
		   String encodedString = Base64.getEncoder().encodeToString(bytesArray);
		   
		   System.out.println(" Encoded");
		   
		   File file = new File("src/main/resources/uploadedFiles/nidhi4.xls");		
		   OutputStream os = new FileOutputStream(file);            
		   byte[] byteArr =  Base64.getDecoder().decode(encodedString);       
			os.write(byteArr); 
			os.close(); 
		   
		 }
		catch (Exception e) { 
			System.out.println("Error "+ e.getMessage());
		} 

	}


	
}
