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
import com.app.aims.dao.EmployeeMergedDetailsDao;
import com.app.aims.service.ExportXlsService;




@Service
@Transactional
public class ExportXlsServiceImpl implements ExportXlsService {

	@Autowired
	EmployeeMergedDetailsDao employeeMergedDetailsDao;
	
	@Override
	public byte[] downloadXlsReportOfEmployees( ExportXlsRequest exportXlsRequest) throws InvalidRequestException {
	
		if(exportXlsRequest == null || exportXlsRequest.getBaseLine() == null || exportXlsRequest.getBaseLine() < 0) {
			throw new InvalidRequestException("Please provide baseline number.");
		}
		
		List<EmployeeMergedDetails> employeeMergedDetails = employeeMergedDetailsDao.findByBaseLine(exportXlsRequest.getBaseLine());

		if(employeeMergedDetails == null || employeeMergedDetails.size() < 1 ) {
			throw new InvalidRequestException("No records found with provided baseline number.");
		}
		
		
		Workbook workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet("Employee Details");
		String[] columns = {"Work Geography", "Work Country", "Work Location", "Client Geography", 
				"Client Country", "IP", "SP", "Sub-SP" , "Customer", "Group Customer","RM#", "BRM#"
				, "GL#" , "AM#" , "AM" , "Project#", "PL", "Project Name" ,
				"Project Location wrt India" , "Project Type", "Pure Turnkey Flag" 
				, "Swon Category", "Project Cluster" , "IOU" , "Sub IOU" ,
				"Employee #" , "Employee Name", "BRM/EM", 
				"DM" , "Expat/BA/Local/Tr", "Date Of Joining", "Depute Branch", "Depute DC",
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
        for(EmployeeMergedDetails employeeDetails : employeeMergedDetails) {
            Row row = sheet.createRow(rowNum++);

            row.createCell(0).setCellValue(employeeDetails.getWorkGeography());
            row.createCell(1).setCellValue(employeeDetails.getWorkCountry());
            row.createCell(2).setCellValue(employeeDetails.getWorkLocation());
            row.createCell(3).setCellValue(employeeDetails.getClientGeography());
            row.createCell(4).setCellValue(employeeDetails.getClientCountry());
            row.createCell(5).setCellValue(employeeDetails.getIp());
            row.createCell(6).setCellValue(""); // SP
            row.createCell(7).setCellValue(""); // SUB SP
            row.createCell(8).setCellValue(employeeDetails.getCustomer());
            row.createCell(9).setCellValue(employeeDetails.getGroupCustomer());
            row.createCell(10).setCellValue(""); // RM# 
            row.createCell(11).setCellValue(""); // BRM#
            row.createCell(12).setCellValue(""); // GL# 
            row.createCell(13).setCellValue(""); // AM#
            row.createCell(14).setCellValue(""); // AM
            row.createCell(15).setCellValue(employeeDetails.getProjectHash());
            row.createCell(16).setCellValue(""); // PL
            row.createCell(17).setCellValue(employeeDetails.getProjectName());
            row.createCell(18).setCellValue(employeeDetails.getProjectLocationWrtIndia());
            row.createCell(19).setCellValue(employeeDetails.getProjectType());
            row.createCell(20).setCellValue(employeeDetails.getPureTurnkeyFlag());
            row.createCell(21).setCellValue(""); // swon category  
            row.createCell(22).setCellValue(employeeDetails.getProjectCluster()); 
            row.createCell(23).setCellValue(employeeDetails.getIou()); 
            row.createCell(24).setCellValue(employeeDetails.getSubIou()); 
            row.createCell(25).setCellValue(""); // Employee #
            row.createCell(26).setCellValue(employeeDetails.getFirstName() +" "+employeeDetails.getLastName());
            row.createCell(27).setCellValue(employeeDetails.getBrmEmpId()); // BRM/EM
            row.createCell(28).setCellValue(employeeDetails.getDmEmpId()); // DM
            row.createCell(29).setCellValue(""); // Expat/BA/Local/Tr
            row.createCell(30).setCellValue(""); // Date Of Joining
            row.createCell(31).setCellValue(employeeDetails.getDeputeBranch());
            row.createCell(32).setCellValue(employeeDetails.getDeputeDc());
            row.createCell(33).setCellValue(employeeDetails.getEmployeeLocationId()); // Employee Location
            row.createCell(34).setCellValue(""); // Employee base country
            row.createCell(35).setCellValue(employeeDetails.getBaseBranch());
            row.createCell(36).setCellValue(employeeDetails.getBaseDc());
            row.createCell(37).setCellValue(employeeDetails.getEmployeeTravelCountry());
            row.createCell(38).setCellValue(employeeDetails.getTravelType());
            row.createCell(39).setCellValue(""); // designation
            row.createCell(40).setCellValue(employeeDetails.getGrade());
            row.createCell(41).setCellValue(employeeDetails.getMappDesignation());
            row.createCell(42).setCellValue(""); // senior /junior
            row.createCell(43).setCellValue(""); // CC / Non CC
            row.createCell(44).setCellValue(""); // person type
            row.createCell(45).setCellValue(""); // sub person type
            row.createCell(46).setCellValue(""); // sob name
            row.createCell(47).setCellValue(employeeDetails.getAimsExp());
            row.createCell(48).setCellValue(employeeDetails.getOverallExp());
            row.createCell(49).setCellValue(employeeDetails.getStartDate()+"");  // Allocation start date
            row.createCell(50).setCellValue(employeeDetails.getEndDate()+"");  // allocation end date
            row.createCell(51).setCellValue(employeeDetails.getPercentageAllocation());
            row.createCell(52).setCellValue(""); // employee cluster
            row.createCell(53).setCellValue(employeeDetails.getParentIou());
            row.createCell(54).setCellValue(employeeDetails.getChildIou());
            row.createCell(55).setCellValue(employeeDetails.getTeamRole());
            row.createCell(56).setCellValue(""); // platforms
            row.createCell(57).setCellValue(""); // DC
            row.createCell(58).setCellValue(""); // site
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
