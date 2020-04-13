package com.app.aims.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.aims.Exceptions.InvalidRequestException;
import com.app.aims.beans.Employee;
import com.app.aims.beans.EmployeeMergedDetails;
import com.app.aims.beans.ExportXlsRequest;
import com.app.aims.beans.HCDetails;
import com.app.aims.dao.EmployeeMergedDetailsDao;
import com.app.aims.service.ExportXlsService;




@Service
@Transactional
public class ExportXlsServiceImpl implements ExportXlsService {

	@Autowired
	EmployeeMergedDetailsDao employeeMergedDetailsDao;
	
	@Override
	public byte[] downloadXlsReportOfEmployees( ExportXlsRequest exportXlsRequest) throws InvalidRequestException {
	
		if(exportXlsRequest == null || exportXlsRequest.getVersionNo() == null || exportXlsRequest.getVersionNo() < 0) {
			throw new InvalidRequestException("Please provide Version number.");
		}
		
		List<HCDetails> hcDetails = employeeMergedDetailsDao.findByVersionNo(exportXlsRequest.getVersionNo());

		if(hcDetails == null || hcDetails.size() < 1 ) {
			throw new InvalidRequestException("No records found with provided version number.");
		}
		
		
		Workbook workbook = new HSSFWorkbook();   
		Sheet sheet = workbook.createSheet("HC Details");
	

		
		
		
		
		String[] columns = {"VersionNo", "Employee No", "Work Geography", "Work Country", 
				"Work Location", "Client Geography", "Client Country", "SP", "Sub-SP" , "Customer", "Group Customer","RM#", "BRM#"
				, "GL#" , "AM#" , "AM" , "Project#", "PL", "Project Name" ,
				"Project Location" , "Project Type", "Pure Turnkey Flag" 
				, "Swon Category", "Project Cluster" , "IOU" , "Sub IOU" ,
				"Employee Name","BRM",  "BRM/EM", 
				"DM" , "EmployeeType", "Date Of Joining", "Depute Branch", "Depute DC",
				"Employee Location", "Employee Base Country", "Base Branch", "Base DC" ,
				"Employee Travel Country", "Travel Type", "Designation", "Grade", "Mapp Designation", 
				"Senior/Junior", "CC / Non CC", "Person Type", "Sub Person Type", "SOB Name", 
				"Company Experience", "Total Experience", "Allocation Start Date", "Allocation End Date",
				"Percentage Allocation", "Employee Cluster", "Parent IOU Name", "Child IOU Name", 
				"Team Role", "Platforms", "DC", "site"};
		Row headerRow = sheet.createRow(0);

		System.out.println(columns.length);
		
        // Create cells
        for(int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
        }
		
        
        int rowNum = 1;
        for(HCDetails employeeDetails : hcDetails) {
            Row row = sheet.createRow(rowNum++);

			

            row.createCell(0).setCellValue(employeeDetails.getVersionNo());
            row.createCell(1).setCellValue(employeeDetails.getEmployeeId());
            row.createCell(2).setCellValue(employeeDetails.getWorkGeography());
            row.createCell(3).setCellValue(employeeDetails.getWorkCountry());
            row.createCell(4).setCellValue(employeeDetails.getWorkLocation());
            row.createCell(5).setCellValue(employeeDetails.getClientGeography());
            row.createCell(6).setCellValue(employeeDetails.getClientCountry());
            row.createCell(7).setCellValue(employeeDetails.getSp());
            row.createCell(6).setCellValue(employeeDetails.getSubSP()); 
            row.createCell(8).setCellValue(employeeDetails.getCustomer());
            row.createCell(9).setCellValue(employeeDetails.getGroupCustomer());
            row.createCell(10).setCellValue(employeeDetails.getRm()); // RM# 
            row.createCell(11).setCellValue(employeeDetails.getBrm()); // BRM#
            row.createCell(12).setCellValue(employeeDetails.getGl()); // GL# 
            row.createCell(14).setCellValue(employeeDetails.getAmID()); // AM
            row.createCell(13).setCellValue(employeeDetails.getAm()); // AM#
           
			
            row.createCell(15).setCellValue(employeeDetails.getProjectID());
            row.createCell(16).setCellValue(employeeDetails.getPl()); // PL
            row.createCell(17).setCellValue(employeeDetails.getProjectName());
            row.createCell(18).setCellValue(employeeDetails.getProjectLoc());
            row.createCell(19).setCellValue(employeeDetails.getProjectType());
            row.createCell(20).setCellValue(employeeDetails.getTurnkey());
            row.createCell(23).setCellValue(employeeDetails.getSnowcategory()); 
            row.createCell(24).setCellValue(employeeDetails.getCluster());
            
          

            row.createCell(25).setCellValue(employeeDetails.getIou()); // Employee #
            row.createCell(26).setCellValue(employeeDetails.getSubiou());
            row.createCell(27).setCellValue(employeeDetails.getEmpName()); // BRM/EM
            row.createCell(28).setCellValue(employeeDetails.getBrm1()); // DM
            row.createCell(28).setCellValue(employeeDetails.getDm()); // DM
            row.createCell(29).setCellValue(employeeDetails.getEmployeeType()); // Expat/BA/Local/Tr
            row.createCell(30).setCellValue(employeeDetails.getDoj()); // Date Of Joining
            row.createCell(31).setCellValue(employeeDetails.getDeupteBranch());
            row.createCell(32).setCellValue(employeeDetails.getDeputeDC());
            row.createCell(33).setCellValue(employeeDetails.getWorkLocation()); // Employee Location
            row.createCell(34).setCellValue(employeeDetails.getBaseCountry()); // Employee base country
            row.createCell(35).setCellValue(employeeDetails.getBaseBranch());
            row.createCell(36).setCellValue(employeeDetails.getBaseDC());

            row.createCell(37).setCellValue(employeeDetails.getTravelCountry());
            row.createCell(38).setCellValue(employeeDetails.getTravelType());
            row.createCell(39).setCellValue(employeeDetails.getDesignation()); // designation
            row.createCell(40).setCellValue(employeeDetails.getGrade());
            row.createCell(41).setCellValue(employeeDetails.getMapDesignation());
            row.createCell(42).setCellValue(employeeDetails.getSeniorjunior()); // senior /junior
            row.createCell(43).setCellValue(employeeDetails.getCcInd()); // CC / Non CC
            row.createCell(44).setCellValue(employeeDetails.getSubPersonType()); // person type
            row.createCell(45).setCellValue(employeeDetails.getSdbname()); // sub person type
            row.createCell(46).setCellValue(employeeDetails.getExperience()); // sob name
            row.createCell(47).setCellValue(employeeDetails.getAllocStart());
            row.createCell(48).setCellValue(employeeDetails.getAllocEnd());
            row.createCell(51).setCellValue(employeeDetails.getPercAlloc());
            row.createCell(52).setCellValue(employeeDetails.getCluster()); // employee cluster
            row.createCell(53).setCellValue(employeeDetails.getParentiou());
            row.createCell(54).setCellValue(employeeDetails.getChildiou());
            
            row.createCell(56).setCellValue(employeeDetails.getTeamrole()); // platforms
            row.createCell(57).setCellValue(employeeDetails.getPlatform()); // DC
            row.createCell(58).setCellValue(employeeDetails.getDc()); // site
            row.createCell(55).setCellValue(employeeDetails.getSite());
        }
        
     // Resize all columns to fit the content size
        for(int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }
		 
        try {
				File f = File.createTempFile("employeeDetails", ".xls"); 
				FileOutputStream fileOut =	new FileOutputStream(f);
				workbook.write(fileOut);
				fileOut.close();
				workbook.close();
			return	Files.readAllBytes(f.toPath());
        	
        }catch(Exception e) {
        	System.out.println("Error Occoured");
        	throw new InternalError("Something went wrong please try again later.");
        }
	
	}

	
	
}
