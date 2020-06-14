package com.app.aims.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.time.YearMonth;
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
import com.app.aims.beans.DMDetails;
import com.app.aims.beans.Employee;
import com.app.aims.beans.HCDetails;
import com.app.aims.beans.Portfolio;
import com.app.aims.dao.BaseLineDao;
import com.app.aims.dao.BillingDao;
import com.app.aims.dao.EmployeeDao;
import com.app.aims.service.BillingService;
import com.app.aims.util.DateUtil;
import com.app.aims.util.ServiceUtil;
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
	ServiceUtil util;

    @Autowired
    BaseLineDao baseLineDao;
    
	@Override
	public List<BRMDetails> getBRMDetails() {
		List<Portfolio> portfolioList = baseLineDao.getPortfolio();
		List<BRMDetails> brmDetails = new ArrayList<BRMDetails>();
		List<String> brmIdList = new ArrayList<String>();
		if(portfolioList != null && portfolioList.size() > 0) {
			portfolioList.forEach(p -> {
				if(!brmIdList.contains(String.valueOf(p.getBrmEmpId()))) {
				BRMDetails brmDetail = new BRMDetails();
				brmDetail.setBrmId(String.valueOf(p.getBrmEmpId()));
				brmDetail.setBrmName(p.getBrmname());
				brmDetails.add(brmDetail);
				brmIdList.add(brmDetail.getBrmId());
				}
			});
		}
		return brmDetails;

	}
	
	@Override
	public List<BillingVersion> getBillingVersionByClaritydescrepancyVersion(BillingDetailsReq req){
		
		return billingDao.getBillingVersionByClaritydescrepancyVersion(req);
		
	}

	@Override
	public List<BillingDetailsResp> getBillingDetailsByBrmId(BillingDetailsReq req) {
		BillingDetailsResp resp = null;
		List<BillingDetailsResp> respList = null;
		List<BillingVersion> versionDetList = null;
		try {
			versionDetList = billingDao.getBillingVersion(req);
			if (versionDetList != null && versionDetList.size() > 0) {
				BillingVersion versionDet = versionDetList.get(0);
				int version = versionDet.getVersion();
				Map<Integer, Employee> employeeDetailsMap = util.getEmployeeDetailMap();
				Map<Integer, String> portfolioMap = util.getPortfolioMap();

				List<Billing> billingList = billingDao.getBillingDetails(version);
				respList = populateBillingDetailsList(billingList, versionDet, employeeDetailsMap, portfolioMap);
			} else {
				versionDetList = billingDao.getBillingVersionByMonth(req,false);
				if(versionDetList != null && versionDetList.size() > 0) {
					resp = new BillingDetailsResp();
					respList = new ArrayList<BillingDetailsResp>();
					resp.addError("NOT FOUND");
					respList.add(resp);
				} else {
					replicateBillingPrevMonthToCurrentMonth(req);
					return getBillingDetailsByBrmId(req);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			resp = new BillingDetailsResp();
			respList = new ArrayList<BillingDetailsResp>();
			resp.addError("Exception Occurred");
			respList.add(resp);
		}
		return respList;
	}

	private void replicateBillingPrevMonthToCurrentMonth(BillingDetailsReq bdReq) {
		List<BillingVersion> newBillingVersionList = new ArrayList<BillingVersion>();
		List<Billing> newBillingList = new ArrayList<Billing>();
		BillingDetailsReq req = createReqForPrevMonth(bdReq);
		List<BillingVersion> versionDetList = null;
		versionDetList = billingDao.getBillingVersionByMonth(req,true);
		if(versionDetList != null && versionDetList.size() > 0) {
			Integer currMonVersionNo = versionDetList.get(0).getVersion() + 1;
			for(BillingVersion versionDet : versionDetList) {
				List<Billing> billingList = billingDao.getBillingDetails(versionDet.getVersion());
				BillingVersion versionDetNew = cloneVersionDetUpdVersion(versionDet,currMonVersionNo,bdReq);
				newBillingVersionList.add(versionDetNew);
				for(Billing billing : billingList) {
					Billing billingNew = cloneVersionDetUpdVersion(billing,currMonVersionNo);
					newBillingList.add(billingNew);
				}
				currMonVersionNo = currMonVersionNo + 1;
			}
			System.out.println("Size of newBillingVersionList -->" + newBillingVersionList.size());
			System.out.println("Size of newBillingList -->" + newBillingList.size());
			if(newBillingVersionList.size() > 0 && newBillingList.size() > 0) {
				billingDao.saveNewBillingDetails(newBillingVersionList, newBillingList);
			}
		}
		
	}

	private Billing cloneVersionDetUpdVersion(Billing billing, Integer currMonVersionNo) {
		Billing billingNew = new Billing();
		billingNew.setBilingRate(billing.getBilingRate());
		billingNew.setBillableDays(billing.getBillableDays());
		billingNew.setBillableHrs(billing.getBillableHrs());
		billingNew.setBillingAmount(billing.getBillingAmount());
		billingNew.setDmName(billing.getDmName());
		billingNew.setEffortHrs(billing.getEffortHrs());
		billingNew.setEmpId(billing.getEmpId());
		billingNew.setExtraBilling(billing.getExtraBilling());
		billingNew.setExtraHrs(billing.getExtraHrs());
		billingNew.setLocationId(billing.getLocationId());
		billingNew.setRemarks1(billing.getRemarks1());
		billingNew.setRemarks2(billing.getRemarks2());
		billingNew.setStoName(billing.getStoName());
		billingNew.setWonNumber(billing.getWonNumber());
		billingNew.setVersion(currMonVersionNo);
		return billingNew;
	}

	private BillingVersion cloneVersionDetUpdVersion(BillingVersion versionDetail, Integer currMonVersionNo,
			BillingDetailsReq bdReq) {
		BillingVersion newVersionDet = new BillingVersion();
		newVersionDet.setBillingComments(versionDetail.getBillingComments());
		newVersionDet.setBrm_EmpNo(versionDetail.getBrm_EmpNo());
		newVersionDet.setDraftIndicator("N");
		newVersionDet.setFreezeInd("N");
		newVersionDet.setLocation(versionDetail.getLocation());
		newVersionDet.setVersion(currMonVersionNo);
		newVersionDet.setPeriodMonth(bdReq.getMonth());
		newVersionDet.setYear(Integer.parseInt(bdReq.getYear()));
		
		return newVersionDet;
	}

	private BillingDetailsReq createReqForPrevMonth(BillingDetailsReq bdReq) {
		Integer reqMonthValue = DateUtil.monthMap.get(bdReq.getMonth());
		BillingDetailsReq newReq = new BillingDetailsReq();
		if (reqMonthValue == 1) {
			Integer year = Integer.parseInt(bdReq.getYear());
			year = year - 1;
			newReq.setMonth(DateUtil.monthValueMap.get(12));
			newReq.setYear(year.toString());
		} else {
			newReq.setMonth(DateUtil.monthValueMap.get(reqMonthValue -1));
			newReq.setYear(bdReq.getYear());
		}
		newReq.setBrmId(bdReq.getBrmId());
		return newReq;
	}

	@Override
	public BaseResponse updateFreezeInd(BillingDetailsReq req) {
		BaseResponse resp = null;
		try {
			billingDao.updateFreezeInd(req);
		} catch (NoSuchElementException ex) {
			ex.printStackTrace();
			resp = new BillingDetailsResp();
			resp.addError("NOT FOUND");

		} catch (Exception e) {
			e.printStackTrace();
			resp = new BillingDetailsResp();
			resp.addError("Exception Occurred");
		}
		return resp;

	}

	public List<BillingDetailsResp> populateBillingDetailsList(List<Billing> billingList, BillingVersion versionDet,
			Map<Integer, Employee> employeeDetailsMap, Map<Integer, String> portfolioMap) {

		List<BillingDetailsResp> result = billingList.stream().map(bl -> {
			BillingDetailsResp resp = new BillingDetailsResp();
			String empName = "";
			String dmId="";
			String officeId = "";
			if (employeeDetailsMap.get(Integer.parseInt(bl.getEmpId())) != null){
				dmId = employeeDetailsMap.get(Integer.parseInt(bl.getEmpId())).getDm();
				String lastName = StringUtils.hasText(employeeDetailsMap.get(Integer.parseInt(bl.getEmpId())).getLastName()) ? employeeDetailsMap.get(Integer.parseInt(bl.getEmpId())).getLastName():"";
				empName = employeeDetailsMap.get(Integer.parseInt(bl.getEmpId())).getFirstName() + " "+ lastName;
				officeId = employeeDetailsMap.get(Integer.parseInt(bl.getEmpId())).getOfficeId();
			}
			resp.setBillableDays(bl.getBillableDays());
			resp.setBillableHrs(bl.getBillableHrs());
			resp.setBillingAmount(bl.getBillingAmount());
			resp.setBrmId(versionDet.getBrm_EmpNo());
			if(StringUtils.hasText(versionDet.getBrm_EmpNo()))
				resp.setBrmName(portfolioMap.get(Integer.parseInt(versionDet.getBrm_EmpNo())));
			resp.setDmId(dmId);
			if(StringUtils.hasText(resp.getDmId()))
				resp.setDmName(portfolioMap.get(Integer.parseInt(resp.getDmId())));
			resp.setEffortHrs(bl.getEffortHrs());
			resp.setEmpId(bl.getEmpId());
			resp.setEmpName(empName);
			resp.setExtraBilling(bl.getExtraBilling());
			resp.setExtraHrs(bl.getExtraHrs());
			resp.setFreezeInd(versionDet.getFreezeInd());
			resp.setVersion(versionDet.getVersion().toString());
			resp.setLocationId(bl.getLocationId());
			resp.setOfficeId(officeId);
			resp.setProjectId(bl.getWonNumber());
			resp.setRemarks1(bl.getRemarks1());
			resp.setRemarks2(bl.getRemarks2());
			resp.setStoName(bl.getStoName());
			resp.setWonNumber(bl.getWonNumber());
			if(bl.getBilingRate() != null) {
				resp.setBillRate(bl.getBilingRate().getBillRate());
			}
			return resp;
		}).collect(Collectors.toList());
		return result;
	}

	@Override
	public DownloadXlsResponse downloadXlsBillingReport(BillingDetailsReq req) {
		DownloadXlsResponse response = null;
		List<BillingVersion> versionDetList = null;
		List<BillingDetailsResp> completeRespList= null;
		if(req.getBrmId() != null && req.getBrmId() !=0) {
			versionDetList = billingDao.getBillingVersion(req);
		} else {
			versionDetList = billingDao.getBillingVersionByMonth(req, false);
		}
		if (versionDetList != null && versionDetList.size() > 0) {
			completeRespList = new ArrayList<BillingDetailsResp>();
			Map<Integer, Employee> employeeDetailsMap = util.getEmployeeDetailMap();
			Map<Integer, String> portfolioMap = util.getPortfolioMap();
			for(BillingVersion billingVersion:versionDetList) {
				List<Billing> billingList = billingDao.getBillingDetails(billingVersion.getVersion());
				if (billingList != null && billingList.size() > 0) {
					List<BillingDetailsResp> respList = populateBillingDetailsList(billingList, billingVersion, employeeDetailsMap,
							portfolioMap);
					completeRespList.addAll(respList);
				}	
			}
			
				if(completeRespList != null && completeRespList.size() > 0) {
				
				if (req.getBillingDetailsFilter() != null) {
					completeRespList = filterBillingList(req, completeRespList);
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
					for (BillingDetailsResp billingDetails : completeRespList) {
						Row row = sheet.createRow(rowNum++);

						int cellNum = 0;
						row.createCell(cellNum).setCellValue(billingDetails.getBrmName());
						row.createCell(++cellNum).setCellValue(billingDetails.getDmName());
						row.createCell(++cellNum).setCellValue(billingDetails.getLocationId());
						row.createCell(++cellNum).setCellValue(billingDetails.getWonNumber());
						row.createCell(++cellNum).setCellValue(billingDetails.getProjectId());// Project Name
						row.createCell(++cellNum).setCellValue(billingDetails.getStoName());
						row.createCell(++cellNum).setCellValue(billingDetails.getEmpId());
						row.createCell(++cellNum).setCellValue(billingDetails.getOfficeId());
						row.createCell(++cellNum).setCellValue(billingDetails.getEmpName());
						row.createCell(++cellNum).setCellValue(billingDetails.getBillRate());

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

				} catch (NoSuchElementException ex) {
					ex.printStackTrace();
					response = new DownloadXlsResponse();
					response.addError("NOT FOUND");

				} catch (Exception e) {
					e.printStackTrace();
					response = new DownloadXlsResponse();
					response.addError("Exception Occurred");
				}
			} else {
				throw new NoSuchElementException();
			}
		} else {
			throw new NoSuchElementException();
		}
		
		return response;
	}

	private List<BillingDetailsResp> filterBillingList(BillingDetailsReq req, List<BillingDetailsResp> respList) {
		Predicate<BillingDetailsResp> dmIdFilter = b -> {
			if (StringUtils.hasText(req.getBillingDetailsFilter().getDmId())) {
				if (b.getDmId().contains(req.getBillingDetailsFilter().getDmId())) {
					return true;
				}

			}
			return false;
		};
		Predicate<BillingDetailsResp> dmNameFilter = b -> {
			if (StringUtils.hasText(req.getBillingDetailsFilter().getDmName())) {
				if (b.getDmName().contains(req.getBillingDetailsFilter().getDmName())) {
					return true;
				}

			}
			return false;
		};
		Predicate<BillingDetailsResp> wonNoFilter = b -> {
			if (StringUtils.hasText(req.getBillingDetailsFilter().getWonNumber())) {
				if (b.getWonNumber().contains(req.getBillingDetailsFilter().getWonNumber())) {
					return true;
				}

			}
			return false;
		};
		Predicate<BillingDetailsResp> stoNameFilter = b -> {
			if (StringUtils.hasText(req.getBillingDetailsFilter().getStoName())) {
				if (b.getStoName().contains(req.getBillingDetailsFilter().getStoName())) {
					return true;
				}

			}
			return false;
		};
		Predicate<BillingDetailsResp> billableHrsFilter = b -> {
			if (req.getBillingDetailsFilter().getBillableHrs() != null) {
				String reqBillableHrs = String.valueOf(req.getBillingDetailsFilter().getBillableHrs());
				String resBillableHrs = String.valueOf(b.getBillableHrs());
				if (resBillableHrs.startsWith(reqBillableHrs)) {
					return true;
				}
			}
			return false;
		};
		Predicate<BillingDetailsResp> billableDaysFilter = b -> {
			if (req.getBillingDetailsFilter().getBillableDays() != null) {
				String reqBillableDays = String.valueOf(req.getBillingDetailsFilter().getBillableDays());
				String resBillableDays = String.valueOf(b.getBillableDays());
				if (resBillableDays.startsWith(reqBillableDays)) {
					return true;
				}
			}
			return false;
		};
		Predicate<BillingDetailsResp> effortHrsFilter = b -> {
			if (req.getBillingDetailsFilter().getEffortHrs() != null) {
				String reqEffortHrs = String.valueOf(req.getBillingDetailsFilter().getEffortHrs());
				String resEffortHrs = String.valueOf(b.getEffortHrs());
				if (resEffortHrs.startsWith(reqEffortHrs)) {
					return true;
				}
			}
			return false;
		};
		Predicate<BillingDetailsResp> extraHrsFilter = b -> {
			if (req.getBillingDetailsFilter().getExtraHrs() != null) {
				String reqExtraHrs = String.valueOf(req.getBillingDetailsFilter().getExtraHrs());
				String resExtraHrs = String.valueOf(b.getExtraHrs());
				if (resExtraHrs.startsWith(reqExtraHrs)) {
					return true;
				}
			}
			return false;
		};
		Predicate<BillingDetailsResp> extraBillingFilter = b -> {
			if (req.getBillingDetailsFilter().getExtraBilling() != null) {
				String reqExtraBilling = String.valueOf(req.getBillingDetailsFilter().getExtraBilling());
				String resExtraBilling = String.valueOf(b.getExtraBilling());
				if (resExtraBilling.startsWith(reqExtraBilling)) {
					return true;
				}
			}
			return false;
		};
		Predicate<BillingDetailsResp> billingAmountFilter = b -> {
			if (req.getBillingDetailsFilter().getBillingAmount() != null) {
				String reqBillingAmount = String.valueOf(req.getBillingDetailsFilter().getBillingAmount());
				String resBillingAmount = String.valueOf(b.getBillingAmount());
				if (resBillingAmount.startsWith(reqBillingAmount)) {
					return true;
				}
			}
			return false;
		};
		respList = respList.stream().filter(dmIdFilter
												  .and(dmNameFilter)
												  .and(wonNoFilter)
												  .and(stoNameFilter)
												  .and(billableHrsFilter)
												  .and(billableDaysFilter)
												  .and(effortHrsFilter)
												  .and(extraHrsFilter)
												  .and(extraBillingFilter)
												  .and(billingAmountFilter)).collect(Collectors.toList());
		return respList;
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

	@Override
	public List<BillingDetailsResp> getBillingDetailsByMonth(BillingDetailsReq req) {

		BillingDetailsResp resp = null;
		List<BillingDetailsResp> respList= null;
		List<BillingDetailsResp> completeRespList= null;
		try {
			List<BillingVersion> versionDetList = billingDao.getBillingVersionByMonth(req,false);
			if (versionDetList != null && versionDetList.size() > 0) {
				completeRespList = new ArrayList<BillingDetailsResp>();
				Map<Integer, Employee> employeeDetailsMap = util.getEmployeeDetailMap();
				Map<Integer, String> portfolioMap = util.getPortfolioMap();
				for (BillingVersion versionDet : versionDetList) {
					int version = versionDet.getVersion();
					List<Billing> billingList = billingDao.getBillingDetails(version);
					respList = populateBillingDetailsList(billingList, versionDet, employeeDetailsMap, portfolioMap);
					completeRespList.addAll(respList);
				}
			} else {
				if(isCurrentMonthAndyear(req)) {
					replicateBillingPrevMonthToCurrentMonth(req);
					return getBillingDetailsByMonth(req);
				}
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
		return completeRespList;
	
	}

	private boolean isCurrentMonthAndyear(BillingDetailsReq req) {
		int monthValue = DateUtil.monthMap.get(req.getMonth().toUpperCase());
		int year = Integer.parseInt(req.getYear());
		YearMonth reqYearMonth = YearMonth.of(year, monthValue);
		int compare = reqYearMonth.compareTo(YearMonth.now());
		return (compare == 0);
	}

	@Override
	public List<BillingDetailsResp> getBillingDetailsForOthers(BillingDetailsReq req) {
		BillingDetailsResp resp = null;
		List<BillingDetailsResp> respList= new ArrayList<BillingDetailsResp>();;
		List<Integer> billingListEmpIds = new ArrayList<Integer>();
		List<Integer> versions = new ArrayList<Integer>();
		try {
			List<BillingVersion> versionDetList = billingDao.getBillingVersionByMonth(req,false);
			if (versionDetList != null && versionDetList.size() > 0) {
				Map<Integer,String> portfolioMap = util.getPortfolioMap();
				for (BillingVersion versionDet : versionDetList) {
					int version = versionDet.getVersion();
					versions.add(version);
				}
				List<Billing> billingList = billingDao.getBillingDetailsWithVersions(versions); //to test
				billingListEmpIds = billingList.stream().map(p -> {
					return (Integer.parseInt(p.getEmpId()));
				}).collect(Collectors.toList());
				
				//Getting HCVersion
				Integer version = baseLineDao.getMaxHacVersion();
				if(version != null && version > 0) {
					List<HCDetails> hcDetailsList = baseLineDao.getHCDetails(version);
					for(HCDetails hcDetail: hcDetailsList) {
						if(!billingListEmpIds.contains(hcDetail.getEmployeeId())) {
							resp  = populateBillResFrmHCDetail(resp,hcDetail,versionDetList,portfolioMap);
							respList.add(resp);
						}
					}
				}
			} else {
				if(isCurrentMonthAndyear(req)) {
					replicateBillingPrevMonthToCurrentMonth(req);
					return getBillingDetailsForOthers(req);
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			resp = new BillingDetailsResp();
			respList = new ArrayList<BillingDetailsResp>();
			resp.addError("Exception Occurred");
			respList.add(resp);
			}
		System.out.println("Size of the list containing other employees is" + respList.size());
		return respList;
	}

	private BillingDetailsResp populateBillResFrmHCDetail(BillingDetailsResp resp, HCDetails hcDetail, List<BillingVersion> versionDetList, Map<Integer, String> portfolioMap) {
		
		resp = new BillingDetailsResp();
        resp.setBillableDays(0.0d);
        resp.setBillableHrs(0);
        resp.setBillingAmount(0.0d);
        resp.setBrmId(hcDetail.getBrm());
        resp.setBrmName(portfolioMap.get(Integer.getInteger(hcDetail.getBrm())));
        resp.setDmId(hcDetail.getDm());
        resp.setDmName(portfolioMap.get(Integer.getInteger(hcDetail.getDm())));
        resp.setEffortHrs(0.0d);
        resp.setEmpId(String.valueOf(hcDetail.getEmployeeId()));
        resp.setEmpName(hcDetail.getEmpName());
        resp.setExtraBilling(0.0d);
        resp.setExtraHrs(0.0d);
        resp.setFreezeInd("N");
        resp.setVersion(String.valueOf(versionDetList.get(0).getVersion()));
        resp.setLocationId(hcDetail.getProjectLoc());
        resp.setOfficeId("");
        resp.setProjectId(hcDetail.getProjectID());
        resp.setRemarks1("");
        resp.setRemarks2("");
        resp.setStoName("");
        resp.setWonNumber(hcDetail.getProjectID());
        resp.setBillRate("0");
        return resp;
		
	}

	@Override
	public List<Billing> RetriveBillingDetailsListByBillingVersion(Integer billingVersion) {

		List<Billing> billingList = null;

		if (billingVersion != null) {

			billingList = billingDao.getBillingDetails(billingVersion);

		}

		return billingList;
	}

	@Override
	public List<BillingVersion> getBillingVersion(BillingDetailsReq req) {

		List<BillingVersion> billingVersion = null;

		if (req != null) {

			billingVersion = billingDao.getBillingVersion(req);

		}

		return billingVersion;
	}

	@Override
	public List<DMDetails> getDMDetails() {
		List<Portfolio> portfolioList = baseLineDao.getPortfolio();
		List<DMDetails> dmDetails = new ArrayList<DMDetails>();
		List<String> dmIdList = new ArrayList<String>();
		if(portfolioList != null && portfolioList.size() > 0) {
			portfolioList.forEach(p -> {
				if(!dmIdList.contains(String.valueOf(p.getDm_emp_id()))) {
				DMDetails dmDetail = new DMDetails();
				dmDetail.setDmId(String.valueOf(p.getDm_emp_id()));
				dmDetail.setDmName(p.getBrmname());
				dmDetails.add(dmDetail);
				dmIdList.add(dmDetail.getDmId());
				}
			});
		}
		return dmDetails;

	}

}
