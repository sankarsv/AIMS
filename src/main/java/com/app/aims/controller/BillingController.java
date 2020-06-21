package com.app.aims.controller;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.aims.Exceptions.InvalidRequestException;
import com.app.aims.beans.BRMDetails;
import com.app.aims.beans.BillingDiscrepancy;
import com.app.aims.beans.BillingDiscrepancyResponse;
import com.app.aims.beans.BillingVersion;
import com.app.aims.beans.Clarity;
import com.app.aims.beans.ClarityResponse;
import com.app.aims.beans.DMDetails;
import com.app.aims.dao.BillingDiscrepancyDao;
import com.app.aims.service.BillingDiscrepancyService;
import com.app.aims.service.BillingService;
import com.app.aims.service.ClarityService;
import com.app.aims.service.ExportXlsService;
import com.app.aims.service.UpdateBillingService;
import com.app.aims.service.impl.BillingServiceImpl;
import com.app.aims.util.DateUtil;
import com.app.aims.util.ServiceUtil;
import com.app.aims.vo.BaseResponse;
import com.app.aims.vo.BillingDetailUpdateReq;
import com.app.aims.vo.BillingDetailsReq;
import com.app.aims.vo.BillingDetailsResp;
import com.app.aims.vo.DownloadXlsResponse;

@RestController
@RequestMapping(value={"/user"})
public class BillingController {

	@Autowired
	BillingService billingService;

	@Autowired
    UpdateBillingService updateBillingService;
	
    @Autowired
	BillingDiscrepancyService billingDiscrepancyingService;

	@Autowired
	ClarityService clarityService;

	@Autowired
	BillingServiceImpl billingServiceImpl;

	@Autowired
	ServiceUtil util;
	
	@Autowired
	BillingDiscrepancyDao billingDiscrepancyDao;
	
	@Autowired
    ExportXlsService exportXlsService;

	@GetMapping(value = "/getBillingDiscrepancy/{month}/{year}/{BrmId}", headers = "Accept=application/json")
	public ResponseEntity<Object> getBillingDiscrepancy(@PathVariable("month") String month,
			@PathVariable("year") String year, @PathVariable("BrmId") String BrmId) {
		String clarityVersion = null;
		String discrepancyVersion = null;
		Integer newDiscrepancyVersion = null;
		boolean isNewDiscrepancy = false;
		List<ClarityResponse> clarityDetailsList = null;
		List<BillingVersion> billingVersionList = null;
		List<BillingDiscrepancyResponse> billdecrresultset = null;
		List<BillingDetailsResp> mergedpopulateBillingDtlsResp = new ArrayList<BillingDetailsResp>();
		List<BillingDiscrepancy> billingDecsrList = new ArrayList<BillingDiscrepancy>();
		BillingDetailsReq billingDetailReq = new BillingDetailsReq();
		if(StringUtils.hasText(month)) {
			try {
			month = DateUtil.monthValueMap.get(Integer.parseInt(month));
			}catch(NumberFormatException e) {
				System.out.println("Entered incorrect value for month" + month);
				return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
			}
		}
		billingDetailReq.setMonth(month);
		billingDetailReq.setYear(year);
			billingVersionList = billingService.getBillingVersion(billingDetailReq);
			if (billingVersionList != null) {
				clarityVersion = billingVersionList.get(0).getClarityVersion();
				discrepancyVersion = billingVersionList.get(0).getDiscrerpancyVersion();
			}
			if (clarityVersion != null) {
				Integer ClarityVersionInt = Integer.valueOf(clarityVersion);

				clarityDetailsList = clarityService.getClarityDetailsByBillingVersionID(ClarityVersionInt);
			}
		
			mergedpopulateBillingDtlsResp = billingService.getBillingDetailsByMonth(billingDetailReq);
			if(mergedpopulateBillingDtlsResp.size() > 0) {
			if (!StringUtils.hasText(discrepancyVersion)) {
				isNewDiscrepancy = true;
				newDiscrepancyVersion = billingDiscrepancyDao.getMaxDescripancyVersion();
				if (newDiscrepancyVersion == null) {
					newDiscrepancyVersion = 1;
				} else {
					newDiscrepancyVersion = newDiscrepancyVersion + 1;
				}
			} else {
				 newDiscrepancyVersion = Integer.parseInt(discrepancyVersion);
			 }
			 
			for (ClarityResponse claritydtl : clarityDetailsList) {
				int testcheck = 0;
				for (BillingDetailsResp billingMasterdtl : mergedpopulateBillingDtlsResp) {
					if (claritydtl.getOfficeId().equals(billingMasterdtl.getOfficeId())) {
						boolean billRateDifference = false;
						double comparehours = Double.compare(Double.valueOf(claritydtl.getSumOfHours()),
								Double.valueOf(billingMasterdtl.getEffortHrs()));
						double clarityBillingRate = parseBills(claritydtl.getRateWithoutTax());
						double tsBillingRate = parseBills(billingMasterdtl.getBillRate());
						double compareBillingRate = Double.compare(clarityBillingRate,tsBillingRate);
						if (compareBillingRate > 0 || compareBillingRate < 0) {
							billRateDifference = true;
						}
						if (comparehours > 0 || comparehours < 0 || billRateDifference) {
							double actualdiff = Double.valueOf(claritydtl.getSumOfHours())
									- billingMasterdtl.getEffortHrs();
							String actualdiffstr = Double.toString(actualdiff);
							BillingDiscrepancy billingDiscrepancy = new BillingDiscrepancy();
							billingDiscrepancy.setVersion(newDiscrepancyVersion);
							billingDiscrepancy.setBillingVersionId(Integer.valueOf(billingMasterdtl.getVersion()));
							billingDiscrepancy.setFileName("");
							billingDiscrepancy.setBrm(billingMasterdtl.getBrmId());
							billingDiscrepancy.setDm(billingMasterdtl.getDmName());
							billingDiscrepancy.setLocation(billingMasterdtl.getLocationId());
							billingDiscrepancy.setCurrency("CAD");
							billingDiscrepancy.setProjectNo(billingMasterdtl.getWonNumber());
							billingDiscrepancy.setProjectName("");
							billingDiscrepancy.setEmployeeId(billingMasterdtl.getEmpId());
							billingDiscrepancy.setOfficeId(billingMasterdtl.getOfficeId());
							billingDiscrepancy.setEmployeeName(billingMasterdtl.getEmpName());
							billingDiscrepancy.setRateWithoutTax(claritydtl.getRateWithoutTax());
							String accruedHrs = "";
							if(billingMasterdtl.getEffortHrs() != null) {
								accruedHrs = billingMasterdtl.getEffortHrs().toString();
							}
							billingDiscrepancy.setAccruedHours(accruedHrs);
							billingDiscrepancy.setClarityHours(claritydtl.getSumOfHours());
							billingDiscrepancy.setDifference(actualdiffstr);
							billingDiscrepancy.setCurrentInvoiceHours(accruedHrs);
							if(billRateDifference) {
								billingDiscrepancy.setRemarks("Discrepancy in Bill Rate");
							}else {
								billingDiscrepancy.setRemarks(billingMasterdtl.getRemarks1());
							}
							billingDiscrepancy.setCleanupComments(billingMasterdtl.getRemarks2());
							billingDecsrList.add(billingDiscrepancy);
							testcheck = 1;
						}

					}

					if (testcheck > 0) {
						break;
					}

				}

			}

		}
		if(isNewDiscrepancy) {
			billingDiscrepancyDao.updateDiscrepancyVersionInBillingVersion(billingDetailReq, newDiscrepancyVersion.toString());
		}

		billingDiscrepancyingService.createBillingDiscrepancyDetailsList(billingDecsrList);

		billdecrresultset = billingDiscrepancyingService.getBillingDescrepancyByVersion(BrmId,newDiscrepancyVersion);

		return new ResponseEntity<Object>(billdecrresultset, HttpStatus.OK);

	}

	private double parseBills(String rates) {
		if(StringUtils.hasText(rates)) {
			if(rates.contains("$")) {
				rates = rates.replace("$", "");
				System.out.println(rates);
				try {
					double doubleBill = Double.valueOf(rates);
					return doubleBill;
				} catch (Exception e) {
					e.printStackTrace();
					return 0;
				}
			}
		}
		return 0;
	}

	@GetMapping(value = "/getBRMDetails", headers = "Accept=application/json")
	public ResponseEntity<Object> getBRMDetails() {

		List<BRMDetails> brmDetails = billingService.getBRMDetails();
		return new ResponseEntity<Object>(brmDetails, HttpStatus.OK);

	}
	
	@GetMapping(value = "/getDMDetails", headers = "Accept=application/json")
	public ResponseEntity<Object> getDmDetails() {

		List<DMDetails> dmDetails = billingService.getDMDetails();
		return new ResponseEntity<Object>(dmDetails, HttpStatus.OK);

	}

	@PostMapping(value = "/updateFreezeOld", headers = "Accept=application/json")
	public ResponseEntity<Object> updateFreezeInd(@RequestBody BillingVersion billingVersion)
			throws InvalidRequestException {

		if (validReq(billingVersion)) {

			boolean status = billingService.updateFreeze(billingVersion);
			return new ResponseEntity<Object>(status, HttpStatus.OK);
		} else
			return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);

	}

	@PostMapping(value = "/getBillingDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getBillingDetails(@RequestBody BillingDetailsReq billingDetailReq) {
		if (validReq(billingDetailReq)) {
			if(isValidMonth(billingDetailReq)) {
				List<BillingDetailsResp> billingDetails = null;
				if("brmid".equalsIgnoreCase(billingDetailReq.getFilterBy())) {
					billingDetails = billingService.getBillingDetailsByBrmId(billingDetailReq);
				} else if("all".equalsIgnoreCase(billingDetailReq.getFilterBy())){
					billingDetails = billingService.getBillingDetailsByMonth(billingDetailReq);
				} else if("other".equalsIgnoreCase(billingDetailReq.getFilterBy())){
					billingDetails = billingService.getBillingDetailsForOthers(billingDetailReq);
				}
			if (billingDetails != null && billingDetails.size()>0) {
				if (!(billingDetails.get(0).getErrorList().size() > 0)) {
					return new ResponseEntity<Object>(billingDetails, HttpStatus.OK);
				} else if ("NOT FOUND".equalsIgnoreCase(billingDetails.get(0).getErrorList().get(0))) {
					System.out.println("Value not in DB");
					return new ResponseEntity<>("Value not in DB", HttpStatus.BAD_REQUEST);
				} else {
					return new ResponseEntity<>("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
		  } else {
			  return new ResponseEntity<>("Only Details from current or previous months can be requested", HttpStatus.BAD_REQUEST);
		  }
		}
		return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping(value = "/updateBillingDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateBilingDetails(@RequestBody BillingDetailUpdateReq updateBillingDetailReq) {
		if (validReq(updateBillingDetailReq)) {
		BaseResponse resp = updateBillingService.updateBillingDetails(updateBillingDetailReq);
			if (resp == null) {
				return new ResponseEntity<Object>(HttpStatus.ACCEPTED);
			} else {
				if ("NOT FOUND".equalsIgnoreCase(resp.getErrorList().get(0))) {
					System.out.println("Value not in DB");
					return new ResponseEntity<>("Value not in DB", HttpStatus.BAD_REQUEST);
				} else {
					return new ResponseEntity<>("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
		}
		return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
	}

	@PostMapping(value = "/updateFreeze", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateFreezeInd(@RequestBody BillingDetailsReq billingDetailReq) {
		if (StringUtils.hasText(billingDetailReq.getMonth()) && StringUtils.hasText(billingDetailReq.getYear()) 
				&& StringUtils.hasText(billingDetailReq.getFreezeInd())&& billingDetailReq.getBrmId() != null ) {
			BaseResponse resp = billingService.updateFreezeInd(billingDetailReq);
			if (resp == null) {
				return new ResponseEntity<Object>(HttpStatus.ACCEPTED);
			} else {
				if ("NOT FOUND".equalsIgnoreCase(resp.getErrorList().get(0))) {
					System.out.println("Value not in DB");
					return new ResponseEntity<>("Value not in DB", HttpStatus.BAD_REQUEST);
				} else {
					return new ResponseEntity<>("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
		}
		return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
	}

	@PostMapping(value = "/downloadBilling")
	public ResponseEntity<byte[]> exportHCData(@RequestBody BillingDetailsReq billingDetailReq) throws Exception {
		if (!((StringUtils.hasText(billingDetailReq.getVersion()))
				|| (StringUtils.hasText(billingDetailReq.getMonth())
						&& StringUtils.hasText(billingDetailReq.getYear())))) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		DownloadXlsResponse response = billingService.downloadXlsBillingReport(billingDetailReq);
		if (response == null || response.getErrorList().size() > 0) {
			if ("NOT FOUND".equalsIgnoreCase(response.getErrorList().get(0))) {
				System.out.println("Value not in DB");
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		byte[] output = response.getByteArray();
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("charset", "utf-8");
		responseHeaders.setContentType(MediaType.valueOf("text/html"));
		responseHeaders.setContentLength(output.length);
		responseHeaders.set("Content-disposition", "attachment; filename=BillingDetails.xls");
		return new ResponseEntity<byte[]>(output, responseHeaders, HttpStatus.OK);

	}
	
	@GetMapping(value = "/downloadDiscrepancy/{month}/{year}/{BrmId}")
	public ResponseEntity<byte[]> exportDiscrepancyData(@PathVariable("month") String month,
			@PathVariable("year") String year, @PathVariable("BrmId") String BrmId) throws Exception {
		if (!StringUtils.hasText(month)
						|| !StringUtils.hasText(year) || !StringUtils.hasText(BrmId)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		BillingDetailsReq billingDetailReq = new BillingDetailsReq();
		billingDetailReq.setMonth(DateUtil.monthValueMap.get(Integer.parseInt(month)));
		billingDetailReq.setYear(year);
		billingDetailReq.setBrmId(Integer.valueOf(BrmId));
		DownloadXlsResponse response = exportXlsService.downloadXlsDiscrepancyReport(billingDetailReq);
		if (response == null || response.getErrorList().size() > 0) {
			if ("NOT FOUND".equalsIgnoreCase(response.getErrorList().get(0))) {
				System.out.println("Value not in DB");
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		byte[] output = response.getByteArray();
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("charset", "utf-8");
		responseHeaders.setContentType(MediaType.valueOf("text/html"));
		responseHeaders.setContentLength(output.length);
		responseHeaders.set("Content-disposition", "attachment; filename=Clarity_AccruedHrs_Discrepancy_report.xls");
		return new ResponseEntity<byte[]>(output, responseHeaders, HttpStatus.OK);

	}

	private boolean validReq(BillingDetailUpdateReq req) {
		try {
			if (req != null && req.getBillingDetailsList() != null
					&& req.getBillingDetailsList().size() > 0 && (req.getVersion() != null || 
							((StringUtils.hasText(req.getMonth())) && (StringUtils.hasText(req.getYear()))))) {
				System.out.println("Version To be updated --> " + req.getVersion());
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private boolean validReq(BillingDetailsReq billingDetailReq) {
		return (StringUtils.hasText(billingDetailReq.getFilterBy()) && StringUtils.hasText(billingDetailReq.getMonth()) && StringUtils.hasText(billingDetailReq.getYear()));
	}
	
	private boolean isValidMonth(BillingDetailsReq billingDetailReq) {
		Integer yearFromReq = Integer.parseInt(billingDetailReq.getYear());
		Integer monthFromReq = DateUtil.monthMap.get(billingDetailReq.getMonth());
		YearMonth reqYearMonth = YearMonth.of(yearFromReq, monthFromReq);
		int compare = reqYearMonth.compareTo(YearMonth.now());
		if (compare > 0) {
			return false;
		}
		return true;
	}

	private boolean validReq(BillingVersion billingVersion) {
		return (StringUtils.hasText(billingVersion.getBrm_EmpNo())
				&& StringUtils.hasText(billingVersion.getPeriodMonth())
				&& !StringUtils.isEmpty(billingVersion.getYear())
				&& StringUtils.hasText(billingVersion.getFreezeInd()));
	}

}
