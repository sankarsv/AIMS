package com.app.aims.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.aims.beans.BRMDetails;
import com.app.aims.beans.BillingReportBean;
import com.app.aims.beans.ReportBean;
import com.app.aims.beans.SearchResponse;
import com.app.aims.service.BillingService;
import com.app.aims.service.DashboardService;
import com.app.aims.service.EmployeeService;
import com.app.aims.vo.BillingDetailsReq;
import com.app.aims.vo.BillingDetailsResp;

public class DashboardServiceImpl implements DashboardService {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    BillingService billingService;
    
	@Override
	public List<Object> generateDashboardReport(ReportBean reportBean) {
		
		BillingDetailsReq billingReq = new BillingDetailsReq();
		billingReq.setMonth(reportBean.getMonth());
		billingReq.setYear(reportBean.getYear());
		List<BRMDetails> brmlist = billingService.getBRMDetails();
		List<SearchResponse> empList = null;
		List<BillingReportBean> billableReportList = new ArrayList();
		for (BRMDetails brmDetail : brmlist) {
			empList = employeeService.leftJoinDataByBrm(new Integer(brmDetail.getBrmId()).intValue());	
		
		
			if ("billable".equalsIgnoreCase(reportBean.getReportType())) {
				
				
				Map billableMap = getBillableCount(empList);
				
				BillingReportBean billBean = new BillingReportBean();
				billBean.setBrmName(brmDetail.getBrmName());;
				billBean.setBrmNo(brmDetail.getBrmId());
				billBean.setBillableCountTot(((Integer)billableMap.get("billabletotal")).toString());
				billBean.setNbCountTot(((Integer)billableMap.get("nbtotal")).toString());
				billBean.setBillableCountPerc(((Double)billableMap.get("billableperc")).toString());
				billBean.setNbCountPerc(((Double)billableMap.get("nbperc")).toString());
				billBean.setOnbillableCount(((Integer)billableMap.get("onsitebillablecnt")).toString());
				billBean.setOnbillabePerc(((Double)billableMap.get("onsitebillableperc")).toString());
				billBean.setOffbillabeCount(((Integer)billableMap.get("offsitenbillablecnt")).toString());
				billBean.setOffbillabePerc(((Double)billableMap.get("offsitebillableperc")).toString());
			
				billableReportList.add(billBean);
			}
			if ("srjrratio".equalsIgnoreCase(reportBean.getReportType())) {
				
			}
			if ("hcratio".equalsIgnoreCase(reportBean.getReportType())) {
				
				
			}
			if ("trnratio".equalsIgnoreCase(reportBean.getReportType())) {
				
			}
			if ("baratio".equalsIgnoreCase(reportBean.getReportType())) {
				
			}
			}
			return null;
	}


	private Map<String, Object> getBillableCount(List<SearchResponse> empList) {
		
		int onsiteBillableCount = 0;
		int onsiteNBillableCount = 0;
		int offsiteBillableCount = 0;
		int offsiteNBillableCount = 0;
		double onsiteBillablePerc;
		double offsiteBillablePerc;
		double totalBillablePerc;
		double totalNBillablePerc;
		int totalBillableCnt = 0;
		int totalNBillableCnt = 0;
		int totalCount;
		
		Map<String, Object> billableRepMap = new HashMap<>();
		for (SearchResponse searchResponse : empList) {
			
			if ("onsite".equalsIgnoreCase(searchResponse.getProjectLocation()))
			{
				if ("CC".equalsIgnoreCase(searchResponse.getCcIndicator())) {
					onsiteBillableCount++;
				} 
				else if ("NCC".equalsIgnoreCase(searchResponse.getCcIndicator())) {
					onsiteNBillableCount++;
				}
				
			}
			else if ("offsite".equalsIgnoreCase(searchResponse.getProjectLocation()))
			{
				if ("CC".equalsIgnoreCase(searchResponse.getCcIndicator())) {
					offsiteBillableCount++;
				} 
				else if ("NCC".equalsIgnoreCase(searchResponse.getCcIndicator())) {
					offsiteNBillableCount++;
				}
			}
		}
		
		totalBillableCnt = onsiteBillableCount+offsiteBillableCount;
		totalNBillableCnt = onsiteNBillableCount+offsiteNBillableCount;
		
		totalCount = totalBillableCnt + totalNBillableCnt;
		
		totalBillablePerc = totalBillableCnt/totalCount*100;
		totalNBillablePerc = totalNBillableCnt/totalCount*100;
		onsiteBillablePerc = onsiteBillableCount/(onsiteBillableCount+onsiteNBillableCount)*100;
		offsiteBillablePerc = offsiteBillableCount/(offsiteBillableCount+offsiteNBillableCount)*100;
		
		billableRepMap.put("billabletotal", totalBillableCnt);
		billableRepMap.put("nbtotal", totalNBillableCnt);
		billableRepMap.put("billableperc", totalBillablePerc);
		billableRepMap.put("nbperc", totalNBillablePerc);
		billableRepMap.put("onsitebillablecnt", onsiteBillableCount);
		billableRepMap.put("onsitebillableperc", onsiteBillablePerc);
		billableRepMap.put("onsitenbillablecnt", onsiteNBillableCount);
		billableRepMap.put("offsitenbillablecnt", offsiteNBillableCount);
		billableRepMap.put("offsitebillablecnt", offsiteBillableCount);		
		billableRepMap.put("offsitebillableperc", offsiteBillablePerc);
		return billableRepMap;
	}
	
	
}

