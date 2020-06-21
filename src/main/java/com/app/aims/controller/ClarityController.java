package com.app.aims.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.app.aims.beans.BillingVersion;
import com.app.aims.beans.Clarity;
import com.app.aims.beans.ClarityResponse;
//import com.app.aims.beans.ClarityExportXlsRequest;
import com.app.aims.beans.Employee;
import com.app.aims.service.BillingService;
import com.app.aims.service.ClarityService;
import com.app.aims.service.EmployeeService;
import com.app.aims.service.ExportXlsService;
import com.app.aims.util.DateUtil;
import com.app.aims.vo.BillingDetailsReq;

@RestController
//@RequestMapping(value = { "/Tested" })
public class ClarityController {

	@Autowired
	ClarityService clarityService;

	@Autowired
	BillingService billingService;

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	ExportXlsService exportXlsService;

	@GetMapping(value = "/getClarityDetailsByBillingVersionID", headers = "Accept=application/json")
	public ResponseEntity<Object> getClarityDetailsByBillingVersionID(@Param("BillingVersionID") Integer versionID) {
		System.out.println("Entered in to Clarity Controller --->" + versionID);
		List<ClarityResponse> clarityDetails = clarityService.getClarityDetailsByBillingVersionID(versionID);
		System.out.println("Printing Clarity Details in Clarity Controller --->" + clarityDetails);
		return new ResponseEntity<Object>(clarityDetails, HttpStatus.OK);

	}

	@GetMapping(value = "/user/getClarityDetails/{month}/{year}/{brmId}", headers = "Accept=application/json")
	public ResponseEntity<Object> getClarityDetails(@PathVariable("month") String month, @PathVariable("year") String year,
			@PathVariable("brmId") String BrmId) {
		System.out.println("Entered in to getClarityDetails() of Clarity Controller --->" + BrmId);
		String clarityVersion = null;
		List<ClarityResponse> clarityDetailsList = null;
		List<Employee> employeeListByBrmId = null;
		List<BillingVersion> billingVersionList = null;
		List<ClarityResponse> employeeClarityListbyBrmId = new ArrayList<>();
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
		if (billingDetailReq != null) {
			billingVersionList = billingService.getBillingVersion(billingDetailReq);
			if (billingVersionList != null) {
				clarityVersion = billingVersionList.get(0).getClarityVersion();
				//System.out.println("Printing the Clarity Version in to getClarityDetails() of Clarity Controller --->"
						//+ clarityVersion);
			}
			if (clarityVersion != null) {
				Integer ClarityVersionInt = Integer.valueOf(clarityVersion);

				clarityDetailsList = clarityService.getClarityDetailsByBillingVersionID(ClarityVersionInt);
				//System.out.println(
						//"Printing the clarityDetailsList based on Clarity version in to getClarityDetails() of Clarity Controller --->"
								//+ clarityDetailsList);
			}

		}

		employeeListByBrmId = employeeService.getEmployeeListByBrmId(BrmId);
		//System.out.println("Printing the employeeListByBrmId of Clarity Controller --->" + employeeListByBrmId);
		for (Employee employeeObj : employeeListByBrmId) {
			String EmployeeBillingoffice_Id = employeeObj.getOfficeId();
			for (ClarityResponse clarityObj : clarityDetailsList) {
				String EmployeeClarityoffice_Id = clarityObj.getOfficeId();
				//System.out.println("Compare EmployeeBillingofficeId ---> " + EmployeeBillingoffice_Id
						//+ "with EmployeeClarityoffice_Id --->" + EmployeeClarityoffice_Id);
				if(StringUtils.hasText(EmployeeBillingoffice_Id) && StringUtils.hasText(EmployeeClarityoffice_Id)) {
				if (EmployeeBillingoffice_Id.equals(EmployeeClarityoffice_Id)) {
					//System.out.println("Inside office ID equavalent if block " + EmployeeClarityoffice_Id);
					employeeClarityListbyBrmId.add(clarityObj);

				}
			  }
			}

		}
		//System.out.println("Printing Complete clarity List in Clarity Controller --->" + clarityDetailsList);
		//System.out.println("Printing Filtered clarity List in Clarity Controller --->" + employeeClarityListbyBrmId);
		
		
		
		//return newJsonData;
		return new ResponseEntity<Object>(employeeClarityListbyBrmId, HttpStatus.OK);

	}
	
}
