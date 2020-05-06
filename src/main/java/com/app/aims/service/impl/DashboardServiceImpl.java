package com.app.aims.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.aims.beans.BARatioBean;
import com.app.aims.beans.BRMDetails;
import com.app.aims.beans.BillingReportBean;
import com.app.aims.beans.EmployeeRatioBean;
import com.app.aims.beans.HCReportBean;
import com.app.aims.beans.ReportBean;
import com.app.aims.beans.SearchResponse;
import com.app.aims.beans.TraineeRatioBean;
import com.app.aims.service.BillingService;
import com.app.aims.service.DashboardService;
import com.app.aims.service.EmployeeService;
import com.app.aims.vo.BillingDetailsReq;
import com.app.aims.vo.BillingDetailsResp;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    BillingService billingService;
    
	@Override
	public List generateDashboardReport(ReportBean reportBean) {
		
		BillingDetailsReq billingReq = new BillingDetailsReq();
		billingReq.setMonth(reportBean.getMonth());
		billingReq.setYear(reportBean.getYear());
		List<BRMDetails> brmlist = billingService.getBRMDetails();
		List<SearchResponse> empList = null;
		List<BillingReportBean> billableReportList = new ArrayList();
		List<EmployeeRatioBean> empSrJrList = new ArrayList();
		List<HCReportBean> hcList = new ArrayList();
		List<TraineeRatioBean> trList = new ArrayList();
		List<BARatioBean> baList = new ArrayList();
		
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
				
				Map empRationMap = getSrJrList(empList);
				EmployeeRatioBean empsrjrRatio = new EmployeeRatioBean();
				empsrjrRatio.setBrmName(brmDetail.getBrmName());;
				empsrjrRatio.setBrmNo(brmDetail.getBrmId());
				empsrjrRatio.setJrCountPerc(((Double)empRationMap.get("jrcountperc")).toString());
				empsrjrRatio.setSrCountPerc(((Double)empRationMap.get("srcountperc")).toString());
				empsrjrRatio.setOffjrCountPerc(((Double)empRationMap.get("offjrcountperc")).toString());
				empsrjrRatio.setOffsrCountPerc(((Double)empRationMap.get("offsrcountperc")).toString());
				empsrjrRatio.setOnjrCountPerc(((Double)empRationMap.get("onjrcountperc")).toString());
				empsrjrRatio.setOnsrCountPerc(((Double)empRationMap.get("onsrcountperc")).toString());
				
				
				empsrjrRatio.setOnjrCountTot(((Integer)empRationMap.get("onjrcounttot")).toString());
				empsrjrRatio.setOnsrCountTot(((Double)empRationMap.get("onsrcounttot")).toString());
				empsrjrRatio.setOffjrCountTot(((Integer)empRationMap.get("offjrcounttot")).toString());
				empsrjrRatio.setOffsrCountTot(((Double)empRationMap.get("offsrcounttot")).toString());
				
				empsrjrRatio.setJrCountTot(((Integer)empRationMap.get("jrcounttol")).toString());
				empsrjrRatio.setSrCountTot(((Integer)empRationMap.get("srcounttot")).toString());
				
				empSrJrList.add(empsrjrRatio);
				

}
			if ("hcratio".equalsIgnoreCase(reportBean.getReportType())) {
				
				Map hcReportMap = getHCList(empList);
				HCReportBean hcreportBean =new HCReportBean();
				hcreportBean.setBrmName(brmDetail.getBrmName());
				hcreportBean.setBrmNo(brmDetail.getBrmId());
				hcreportBean.setOffPerc(((Double)hcReportMap.get("offperc")).toString());
				hcreportBean.setOnsitePerc(((Double)hcReportMap.get("onperc")).toString());
				hcreportBean.setTotalCnt(((Integer)hcReportMap.get("totalcnt")).toString());
				hcreportBean.setOnsiteTot(((Integer)hcReportMap.get("ontot")).toString());
				hcreportBean.setOffTot(((Integer)hcReportMap.get("offtot")).toString());
				hcList.add(hcreportBean);
			}
			if ("trnratio".equalsIgnoreCase(reportBean.getReportType())) {

				Map traineeRatioMap = getTRList(empList);
				TraineeRatioBean traineeRatioBean =new TraineeRatioBean();
				traineeRatioBean.setBrmName(brmDetail.getBrmName());
				traineeRatioBean.setBrmNo(brmDetail.getBrmId());
				traineeRatioBean.setTrCountPerc(((Double)traineeRatioMap.get("tottrcountperc")).toString());
				traineeRatioBean.setOfftrCountTot(((Integer)traineeRatioMap.get("offtrcountperc")).toString());
				traineeRatioBean.setOntrCountPerc(((Double)traineeRatioMap.get("ontrcountperc")).toString());
				traineeRatioBean.setOntrCountTot(((Integer)traineeRatioMap.get("ontrcounttot")).toString());
				traineeRatioBean.setOfftrCountTot(((Integer)traineeRatioMap.get("offtrcounttot")).toString());
				traineeRatioBean.setTrCountTot(((Integer)traineeRatioMap.get("trcounttot")).toString());
				
				trList.add(traineeRatioBean);
			}
			if ("baratio".equalsIgnoreCase(reportBean.getReportType())) {
				
				
				Map baMap = getBAList(empList);
				BARatioBean baratioBean =new BARatioBean();
				baratioBean.setBrmName(brmDetail.getBrmName());
				baratioBean.setBrmNo(brmDetail.getBrmId());
				baratioBean.setBaCountPerc(((Double)baMap.get("bacountperc")).toString());
				baratioBean.setBaCountPerOff(((Integer)baMap.get("bacountperoff")).toString());
				baratioBean.setBaCountPerOn(((Double)baMap.get("bacountperon")).toString());
				baratioBean.setBaCountTot(((Integer)baMap.get("bacounttot")).toString());
				baratioBean.setBaCountTotOff(((Integer)baMap.get("bacounttotoff")).toString());
				baratioBean.setBaCountTotOn(((Integer)baMap.get("bacounttoton")).toString());
				
				baList.add(baratioBean);
			}
			}
		
		 switch (reportBean.getReportType()) {
		 
		 case "srjrratio": 
			 return empSrJrList;
		 case "hcratio":
			 return hcList;
		 case "trnratio":
			 return  trList;
		 case "billable":
			 return billableReportList;
		 
		 case "baratio" :
			 return baList;
			 
			 default :
				 return null;
		 }
		
		 
	}

private Map<String, Object> getBAList(List<SearchResponse> empList) {
		
		double bacountperc;
		double bacountperoff;
		double bacountperon;

		int bacounttot = 0;
		int bacounttotoff = 0;
		int bacounttoton=0;
		int onsitecnt=0;
		int offsitecnt=0;
		
		
		Map<String, Object> baRatioMap = new HashMap<>();
		for (SearchResponse searchResponse : empList) {
			
			if ("onsite".equalsIgnoreCase(searchResponse.getProjectLocation()))
			{
				onsitecnt++;
				if ("Business Associate".equalsIgnoreCase(searchResponse.getPersonType()))
				{
					bacounttoton++;
				}
			}
			else if ("offsite".equalsIgnoreCase(searchResponse.getProjectLocation()))
			{
				offsitecnt++;
				if ("Business Associate".equalsIgnoreCase(searchResponse.getPersonType()))
				{
					bacounttotoff++;
				}
			}
		}
		bacounttot = bacounttoton+bacounttotoff;
		
		bacountperoff = bacounttotoff/(offsitecnt)*100;
		bacountperon = bacounttoton/(onsitecnt)*100;
		bacountperc = (bacounttotoff+bacounttoton)/(offsitecnt+onsitecnt);


		baRatioMap.put("bacountperc", bacountperc);
		baRatioMap.put("bacountperoff", bacountperoff);
		baRatioMap.put("bacountperon", bacountperon);
		baRatioMap.put("bacounttot", bacounttot);
		baRatioMap.put("bacounttotoff", bacounttotoff);
		baRatioMap.put("bacounttoton", bacounttoton);
	    
		return baRatioMap;
	
	}

private Map<String, Object> getTRList(List<SearchResponse> empList) {
	
	double trcountperc;
	double trcountperoff;
	double trcountperon;

	int trcounttot = 0;
	int trcounttotoff = 0;
	int trcounttoton=0;
	int onsitecnt=0;
	int offsitecnt=0;
	
	
	Map<String, Object> baRatioMap = new HashMap<>();
	for (SearchResponse searchResponse : empList) {
		
		if ("onsite".equalsIgnoreCase(searchResponse.getProjectLocation()))
		{
			onsitecnt++;
			if ("Trainee".equalsIgnoreCase(searchResponse.getPersonType()))
			{
				trcounttoton++;
			}
		}
		else if ("offsite".equalsIgnoreCase(searchResponse.getProjectLocation()))
		{
			offsitecnt++;
			if ("Trainee".equalsIgnoreCase(searchResponse.getPersonType()))
			{
				trcounttotoff++;
			}
		}
	}
	trcounttot = trcounttoton+trcounttotoff;
	
	trcountperoff = trcounttotoff/(offsitecnt)*100;
	trcountperon = trcounttoton/(onsitecnt)*100;
	trcountperc = (trcounttotoff+trcounttoton)/(offsitecnt+onsitecnt);

	baRatioMap.put("tottrcountperc", trcountperc);
	baRatioMap.put("offtrcountperc", trcountperoff);
	baRatioMap.put("ontrcountperc", trcountperon);
	baRatioMap.put("trcounttot", trcounttot);
	baRatioMap.put("offtrcounttot", trcounttotoff);
	baRatioMap.put("ontrcounttot", trcounttoton);
    
	return baRatioMap;

}


	private Map<String, Object> getHCList(List<SearchResponse> empList) {
		
		double offperc;
		double onperc;

		int ontot = 0;
		int offtot = 0;
		int totalcnt=0;
		
		
		Map<String, Object> hcRatioMap = new HashMap<>();
		for (SearchResponse searchResponse : empList) {
			
			if ("onsite".equalsIgnoreCase(searchResponse.getProjectLocation()))
			{
				ontot++;
			}
			else if ("offsite".equalsIgnoreCase(searchResponse.getProjectLocation()))
			{
				offtot++;
			}
		}
		totalcnt = ontot+offtot;
		
		offperc = offtot/(totalcnt)*100;
		onperc = ontot/(totalcnt)*100;
	

		hcRatioMap.put("offperc", onperc);
		hcRatioMap.put("onperc", offperc);
		hcRatioMap.put("totalcnt", totalcnt);
		hcRatioMap.put("ontot", ontot);
		hcRatioMap.put("offtot", offtot);
	    
		return hcRatioMap;
	
	}
	
	private Map<String, Object> getSrJrList(List<SearchResponse> empList) {
		
		double jrcountperc;
		double srcountperc;
		double offjrcountperc;
		double offsrcountperc;

		double onjrcountperc;
		double onsrcountperc;

		int totalJrCount = 0;
		int totalSrCount = 0;
		int totalCount=0;
		int offsrCountTot=0;
		int offjrCountTot=0;
		int onsrCountTot=0;
		int onjrCountTot=0;
		
		Map<String, Object> empRatioMap = new HashMap<>();
		for (SearchResponse searchResponse : empList) {
			
			if ("onsite".equalsIgnoreCase(searchResponse.getProjectLocation()))
			{
				if ("S".equalsIgnoreCase(searchResponse.getSeniorJunior())) {
					onsrCountTot++;
				} 
				else if ("J".equalsIgnoreCase(searchResponse.getSeniorJunior())) {
					onjrCountTot++;
				}
				
			}
			else if ("offsite".equalsIgnoreCase(searchResponse.getProjectLocation()))
			{
				if ("S".equalsIgnoreCase(searchResponse.getSeniorJunior())) {
					offsrCountTot++;
				} 
				else if ("J".equalsIgnoreCase(searchResponse.getSeniorJunior())) {
					offjrCountTot++;
				}			}
		}
		
		totalJrCount = onjrCountTot+offjrCountTot;
		totalSrCount = onsrCountTot+offsrCountTot;
		
		totalCount = totalJrCount+totalSrCount;
		
		onjrcountperc = onjrCountTot/(onjrCountTot+onsrCountTot)*100;
		onsrcountperc = onsrCountTot/(onjrCountTot+onsrCountTot)*100;
		offjrcountperc = offjrCountTot/(offjrCountTot+offsrCountTot)*100;
		offsrcountperc = offsrCountTot/(offjrCountTot+offsrCountTot)*100;
		
	    jrcountperc  = totalJrCount/totalCount*100;
	    srcountperc = totalSrCount/totalCount*100;



	    empRatioMap.put("offjrcountperc", offjrcountperc);
	    empRatioMap.put("offsrcountperc", offsrcountperc);
	    empRatioMap.put("onjrcountperc", onjrcountperc);
	    empRatioMap.put("onsrcountperc", onsrcountperc);
	    empRatioMap.put("offjrcounttot", onjrCountTot);
	    empRatioMap.put("offsrcounttot", onsrCountTot);
	    empRatioMap.put("onjrcounttot", onjrCountTot);
	    empRatioMap.put("onsrcounttot", onsrCountTot);
		
	    empRatioMap.put("srcounttot", totalSrCount);
		empRatioMap.put("jrcounttot", totalJrCount);		
		empRatioMap.put("srcountperc", srcountperc);
		empRatioMap.put("jrcountperc", jrcountperc);
		return empRatioMap;
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

