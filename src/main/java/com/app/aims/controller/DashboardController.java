package com.app.aims.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.aims.Exceptions.InvalidRequestException;
import com.app.aims.beans.BRMDetails;
import com.app.aims.beans.BillingReportBean;
import com.app.aims.beans.BillingVersion;
import com.app.aims.beans.GenerateBaseLineRequest;
import com.app.aims.beans.ReportBean;
import com.app.aims.service.BillingService;
import com.app.aims.service.DashboardService;
import com.app.aims.vo.BillingDetailsReq;
import com.app.aims.vo.BillingDetailsResp;

@RestController
@RequestMapping(value={"/user"})
public class DashboardController {

	@Autowired
    DashboardService dashboardService;
	
	
	
	@PostMapping(value = "/dashboard", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getBillingDetailResponse(@RequestBody ReportBean reportBean) {
			List<Object> reportList = dashboardService.generateDashboardReport(reportBean);
			return new ResponseEntity<Object>(reportList, HttpStatus.OK);
			
	}
	
	
}
