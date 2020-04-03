package com.app.aims.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aims")
public class AimsController {
	
	@GetMapping("/getDetails")
	public String getAimsDetails() {
		return "Success Folks!!!";
	}
	
	@GetMapping("/getAssociatesDetails")
	public String getAssociateDetails() {
		return "{\"totalAssociates\":123,\"monthlyActiveBillable\":103,\"activeAccessTokens\":90,\"issuesOutstanding\":13,\"Expansions\":{\"total\":123,\"lastMonth\":111},\"Attrition\":{\"total\":23,\"lastMonth\":13},\"Requirements\":{\"new\":23,\"expansions\":13}}";
	}

	@GetMapping("/getAccountGrowth")
	public String getAccountGowth() {
	return "[{\"labels\" :[\"January\", \"February\", \"March\", \"April\", \"May\", \"June\"]},{\"datasets\":{\"label\":\"Actual\",\"data\":[10, 5, 20, 15, 40, 25,19,30]}},{\"datasets\":{\"label\":\"Growth\",\"data\":[20,10,40,30,80,15,25,60]}}]";	
	}
	
	@GetMapping("/getFinanceDetails")
	public String getFinanceDetails() {
	return "{\"January\":{\"accountsReceivable\":212,\"accountsPayable\":250,\"netMonthlyBurn\":240},\"February\":{\"accountsReceivable\":300,\"accountsPayable\":250,\"netMonthlyBurn\":240},\"March\":{\"accountsReceivable\":300,\"accountsPayable\":25,\"netMonthlyBurn\":240}}";
	}
	
	@GetMapping("/getAccountSpending")
	public String getAccountSpending() {
		return "{\"January\":{\"total\":300,\"new\":1000},\"February\":{\"total\":500,\"new\":1000},\"March\":{\"total\":450,\"new\":1000}}";
	}
	
}
