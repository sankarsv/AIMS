package com.app.aims.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Predicate;
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
import com.app.aims.beans.Employee;
import com.app.aims.dao.BillingDao;
import com.app.aims.dao.EmployeeDao;
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
    
    @Autowired
    EmployeeDao employeeDao;

	@Override
	public List<BRMDetails> getBRMDetails() {
		
		return billingDao.retrieveBRMInfo();
	}

	@Override
	public List<BillingDetailsResp> getBillingDetails(BillingDetailsReq req) {
		BillingDetailsResp resp = null;
		List<BillingDetailsResp> respList= null;
		try {
			List<BillingVersion> versionDetList = billingDao.getBillingVersion(req);
			if(versionDetList != null && versionDetList.size() > 0) {
				BillingVersion versionDet = versionDetList.get(0);
				int version = versionDet.getVersion();
				Map<Integer,Employee> employeeDetailsMap = getEmployeeDetailMap();
				List<Billing> billingList = billingDao.getBillingDetails(version);
				respList = populateBillingDetailsList(billingList,versionDet,employeeDetailsMap);
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
	
	private Map<Integer,Employee> getEmployeeDetailMap() {
		Map<Integer,Employee> employeeDetailMap = new HashMap<Integer,Employee>();
		List<Employee> employeeList = employeeDao.getEmployeeDetails();
		employeeList.stream().forEach(e -> {
			employeeDetailMap.put(e.getEmployeeId(), e);
		});
		return employeeDetailMap;
		
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

	private List<BillingDetailsResp> populateBillingDetailsList(List<Billing> billingList, BillingVersion versionDet, Map<Integer, Employee> employeeDetailsMap) {
		
		List<BillingDetailsResp> result = billingList.stream().map(bl -> {
			BillingDetailsResp resp = new BillingDetailsResp();
            resp.setBillableDays(bl.getBillableDays());
            resp.setBillableHrs(bl.getBillableHrs());
            resp.setBillingAmount(bl.getBillingAmount());
            resp.setBrmId(versionDet.getBrmId());
            resp.setBrmName(employeeDetailsMap.get(bl.getEmpId()).getBrm());
            resp.setDmId(bl.getDmId());
            resp.setDmName(bl.getDmName());
            resp.setEffortHrs(bl.getEffortHrs());
            resp.setEmpId(bl.getEmpId());
            String empName = employeeDetailsMap.get(bl.getEmpId()).getFirstName() +" "+employeeDetailsMap.get(bl.getEmpId()).getLastName();
            resp.setEmpName(empName);
            resp.setExtraBilling(bl.getExtraBilling());
            resp.setExtraHrs(bl.getExtraHrs());
            resp.setFreezeInd(versionDet.getFreezeInd());
            resp.setVersion(versionDet.getVersion().toString());
            resp.setLocationId(bl.getLocationId());
            resp.setOfficeId(employeeDetailsMap.get(bl.getEmpId()).getOfficeId());
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
		List<BillingVersion> versionDetList = billingDao.getBillingVersion(req);
		if(versionDetList != null && versionDetList.size() > 0) {
			   versionDet = versionDetList.get(0);
				version = versionDet.getVersion();
			}
		else {
			throw new NoSuchElementException();
		}
		List<Billing> billingList = billingDao.getBillingDetails(version);
		if(billingList != null && billingList.size() > 0) {
			if(req.getBillingDetailsFilter() != null) {
				billingList = filterBillingList(req, billingList);
			}
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
		}else {
			throw new NoSuchElementException();
		}
		return response;
	}

	private List<Billing> filterBillingList(BillingDetailsReq req, List<Billing> billingList) {
		Predicate<Billing> dmIdFilter = b -> {
			if (StringUtils.hasText(req.getBillingDetailsFilter().getDmId())) {
				if (b.getDmId().contains(req.getBillingDetailsFilter().getDmId())) {
					return true;
				}

			}
			return false;
		};
		Predicate<Billing> dmNameFilter = b -> {
			if (StringUtils.hasText(req.getBillingDetailsFilter().getDmName())) {
				if (b.getDmName().contains(req.getBillingDetailsFilter().getDmName())) {
					return true;
				}

			}
			return false;
		};
		Predicate<Billing> wonNoFilter = b -> {
			if (StringUtils.hasText(req.getBillingDetailsFilter().getWonNumber())) {
				if (b.getWonNumber().contains(req.getBillingDetailsFilter().getWonNumber())) {
					return true;
				}

			}
			return false;
		};
		Predicate<Billing> stoNameFilter = b -> {
			if (StringUtils.hasText(req.getBillingDetailsFilter().getStoName())) {
				if (b.getStoName().contains(req.getBillingDetailsFilter().getStoName())) {
					return true;
				}

			}
			return false;
		};
		Predicate<Billing> billableHrsFilter = b -> {
			if (req.getBillingDetailsFilter().getBillableHrs() != null) {
				String reqBillableHrs = String.valueOf(req.getBillingDetailsFilter().getBillableHrs());
				String resBillableHrs = String.valueOf(b.getBillableHrs());
				if (resBillableHrs.startsWith(reqBillableHrs)) {
					return true;
				}
			}
			return false;
		};
		Predicate<Billing> billableDaysFilter = b -> {
			if (req.getBillingDetailsFilter().getBillableDays() != null) {
				String reqBillableDays = String.valueOf(req.getBillingDetailsFilter().getBillableDays());
				String resBillableDays = String.valueOf(b.getBillableDays());
				if (resBillableDays.startsWith(reqBillableDays)) {
					return true;
				}
			}
			return false;
		};
		Predicate<Billing> effortHrsFilter = b -> {
			if (req.getBillingDetailsFilter().getEffortHrs() != null) {
				String reqEffortHrs = String.valueOf(req.getBillingDetailsFilter().getEffortHrs());
				String resEffortHrs = String.valueOf(b.getEffortHrs());
				if (resEffortHrs.startsWith(reqEffortHrs)) {
					return true;
				}
			}
			return false;
		};
		Predicate<Billing> extraHrsFilter = b -> {
			if (req.getBillingDetailsFilter().getExtraHrs() != null) {
				String reqExtraHrs = String.valueOf(req.getBillingDetailsFilter().getExtraHrs());
				String resExtraHrs = String.valueOf(b.getExtraHrs());
				if (resExtraHrs.startsWith(reqExtraHrs)) {
					return true;
				}
			}
			return false;
		};
		Predicate<Billing> extraBillingFilter = b -> {
			if (req.getBillingDetailsFilter().getExtraBilling() != null) {
				String reqExtraBilling = String.valueOf(req.getBillingDetailsFilter().getExtraBilling());
				String resExtraBilling = String.valueOf(b.getExtraBilling());
				if (resExtraBilling.startsWith(reqExtraBilling)) {
					return true;
				}
			}
			return false;
		};
		Predicate<Billing> billingAmountFilter = b -> {
			if (req.getBillingDetailsFilter().getBillingAmount() != null) {
				String reqBillingAmount = String.valueOf(req.getBillingDetailsFilter().getBillingAmount());
				String resBillingAmount = String.valueOf(b.getBillingAmount());
				if (resBillingAmount.startsWith(reqBillingAmount)) {
					return true;
				}
			}
			return false;
		};
		billingList = billingList.stream().filter(dmIdFilter
												  .and(dmNameFilter)
												  .and(wonNoFilter)
												  .and(stoNameFilter)
												  .and(billableHrsFilter)
												  .and(billableDaysFilter)
												  .and(effortHrsFilter)
												  .and(extraHrsFilter)
												  .and(extraBillingFilter)
												  .and(billingAmountFilter)).collect(Collectors.toList());
		return billingList;
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
 	