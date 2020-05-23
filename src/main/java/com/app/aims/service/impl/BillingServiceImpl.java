package com.app.aims.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.app.aims.beans.BRMDetails;
import com.app.aims.beans.Billing;
import com.app.aims.beans.BillingVersion;
import com.app.aims.dao.BillingDao;
import com.app.aims.service.BillingService;
import com.app.aims.vo.BaseResponse;
import com.app.aims.vo.BillingDetailUpdateReq;
import com.app.aims.vo.BillingDetailsReq;
import com.app.aims.vo.BillingDetailsResp;
import com.app.aims.vo.DownloadXlsResponse;

@Service
@Transactional
public class BillingServiceImpl implements BillingService {
    
    @Autowired
    BillingDao billingDao;

	@Override
	public List<BRMDetails> getBRMDetails() {
		
		return billingDao.retrieveBRMInfo();
	}

	@Override
	public List<BillingDetailsResp> getBillingDetails(BillingDetailsReq req) {
		BillingDetailsResp resp = null;
		List<BillingDetailsResp> respList= null;
		try {
			BillingVersion versionDet = billingDao.getBillingVersion(req);
			if(versionDet != null) {
				int version = versionDet.getVersion();
				List<Billing> billingList = billingDao.getBillingDetails(version);
				respList = populateBillingDetailsList(billingList,versionDet);
			} else {
				resp = new BillingDetailsResp();
				respList = new ArrayList<BillingDetailsResp>();
				resp.addError("NOT FOUND");
				respList.add(resp);
			}
			
		}catch(NoSuchElementException ex) {
			ex.printStackTrace();
			resp = new BillingDetailsResp();
			respList = new ArrayList<BillingDetailsResp>();
			resp.addError("NOT FOUND");
			respList.add(resp);
			
		}catch(Exception e) {
		e.printStackTrace();
		resp = new BillingDetailsResp();
		respList = new ArrayList<BillingDetailsResp>();
		resp.addError("Exception Occurred");
		respList.add(resp);
		}
		return respList;
	}
	
	@Override
	public BaseResponse updateBillingDetails(BillingDetailUpdateReq req) {
		BaseResponse resp = null;
		try {
			billingDao.fetchAndUpdateBillingDetails(req);
		} catch(NoSuchElementException ex) {
			ex.printStackTrace();
			resp = new BillingDetailsResp();
			resp.addError("NOT FOUND");
			
		}catch(Exception e) {
		 e.printStackTrace();
		 resp = new BillingDetailsResp();
		 resp.addError("Exception Occurred");
		}
		return resp;
		
	}
	
	@Override
	public BaseResponse updateFreezeInd(BillingDetailsReq req) {
		BaseResponse resp = null;
		try {
			billingDao.updateFreezeInd(req);
		} catch(NoSuchElementException ex) {
			ex.printStackTrace();
			resp = new BillingDetailsResp();
			resp.addError("NOT FOUND");
			
		}catch(Exception e) {
		 e.printStackTrace();
		 resp = new BillingDetailsResp();
		 resp.addError("Exception Occurred");
		}
		return resp;
		
	}

	private List<BillingDetailsResp> populateBillingDetailsList(List<Billing> billingList, BillingVersion versionDet) {
		
		List<BillingDetailsResp> result = billingList.stream().map(bl -> {
			BillingDetailsResp resp = new BillingDetailsResp();
            resp.setBillableDays(bl.getBillableDays());
            resp.setBillableHrs(bl.getBillableHrs());
            resp.setBillingAmount(bl.getBillingAmount());
            resp.setBrmId(versionDet.getBrmId());
            resp.setBrmName(bl.getBrnname());
            resp.setDmId(bl.getDmId());
            resp.setDmName(bl.getDmName());
            resp.setEffortHrs(bl.getEffortHrs());
            resp.setEmpId(bl.getEmpId());
            resp.setEmpName(bl.getEmpName());
            resp.setExtraBilling(bl.getExtraBilling());
            resp.setExtraHrs(bl.getExtraHrs());
            resp.setFreezeInd(versionDet.getFreezeInd());
            resp.setVersion(versionDet.getVersion().toString());
            resp.setLocationId(bl.getLocationId());
            resp.setOfficeId(bl.getOfficeId());
            resp.setProjectId(bl.getProjectId());
            resp.setRemarks(addRemarks(bl.getRemarks1(),bl.getRemarks2()));
            resp.setStoName(bl.getStoName());
            resp.setWonNumber(bl.getWonNumber());
            return resp;
        }).collect(Collectors.toList());
		return result;
	}


	private String addRemarks(String remark1,String remark2) {
		if(StringUtils.hasText(remark1) && StringUtils.hasText(remark2)) {
			return remark1+" "+remark2;
		} else if(!StringUtils.hasText(remark1)) {
			return remark2;
		} else  {
			return remark1;
		}
	}

	@Override
	public DownloadXlsResponse downloadXlsBillingReport(BillingDetailsReq req) {
		Integer version = null;
		DownloadXlsResponse response = null;
		BillingVersion versionDet = null;
		versionDet = billingDao.getBillingVersion(req);
		if (versionDet != null) {
				version = versionDet.getVersion();
			}
		else {
			throw new NoSuchElementException();
		}
		List<Billing> billingList = billingDao.getBillingDetails(version);

		try {
			Resource resource = new ClassPathResource("BillingTemplate.xlsx");
			InputStream input = resource.getInputStream();
			Workbook workbook = WorkbookFactory.create(input);
			Sheet sheet = workbook.getSheetAt(0);
			CellStyle cs = workbook.createCellStyle();
			DataFormat df = workbook.createDataFormat();
			cs.setDataFormat(df.getFormat("$#,##0.00"));
			double totalBillingAmt = 0.00;

				int rowNum = 2;
				for (Billing billingDetails : billingList) {
					Row row = sheet.createRow(rowNum++);

					  int cellNum = 0;
					  row.createCell(cellNum).setCellValue(versionDet.getBrmId());
					  row.createCell(++cellNum).setCellValue(billingDetails.getDmName());
					  row.createCell(++cellNum).setCellValue(billingDetails.getLocationId());
					  row.createCell(++cellNum).setCellValue(billingDetails.getWonNumber());
					  row.createCell(++cellNum).setCellValue(billingDetails.getProjectId());//Project Name
					  row.createCell(++cellNum).setCellValue(billingDetails.getStoName());
					  row.createCell(++cellNum).setCellValue(billingDetails.getEmpId());
					  row.createCell(++cellNum).setCellValue(billingDetails.getOfficeId());
					  row.createCell(++cellNum).setCellValue(billingDetails.getEmpName());
					  row.createCell(++cellNum).setCellValue(billingDetails.getBilingRate().getBillRate());
					  
					  row.createCell(++cellNum).setCellValue(billingDetails.getBillableHrs()); 
					  row.createCell(++cellNum).setCellValue(billingDetails.getBillableDays()); 
					  row.createCell(++cellNum).setCellValue(billingDetails.getEffortHrs()); 
					  row.createCell(++cellNum).setCellValue(billingDetails.getExtraHrs());
					  Cell amountCell = row.createCell(++cellNum);
					  amountCell.setCellStyle(cs);
					  amountCell.setCellValue(billingDetails.getExtraBilling());
					  totalBillingAmt = totalBillingAmt + billingDetails.getBillingAmount();
					  row.createCell(++cellNum).setCellValue(billingDetails.getBillingAmount());
					  row.createCell(++cellNum).setCellValue(billingDetails.getRemarks1()); // PL
					  row.createCell(++cellNum).setCellValue(billingDetails.getRemarks2());
					 

				}
				Cell totalAmountCell = sheet.createRow(0).createCell(15);
				totalAmountCell.setCellStyle(cs);
				totalAmountCell.setCellValue(totalBillingAmt);

				// Resize all columns to fit the content size
				for (int i = 0; i < 20; i++) {
					sheet.autoSizeColumn(i);
				}

				File f = File.createTempFile("billingDetails", ".xls");
				FileOutputStream fileOut = new FileOutputStream(f);
				workbook.write(fileOut);
				fileOut.close();
				workbook.close();
				response = new DownloadXlsResponse();
				response.setByteArray(Files.readAllBytes(f.toPath()));
				return response;

		} catch(NoSuchElementException ex) {
			ex.printStackTrace();
			response = new DownloadXlsResponse();
			response.addError("NOT FOUND");
			
		}catch(Exception e) {
		e.printStackTrace();
		response = new DownloadXlsResponse();
		response.addError("Exception Occurred");
		}

		return response;
	}
	

	@Override
	public boolean updateFreeze(BillingVersion billingVer) {

		try {
		
			billingDao.updateFreezeInd(billingVer);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
	
	}
		return false;
	}
	
	
	

}
 	