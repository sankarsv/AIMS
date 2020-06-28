package com.app.aims.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.aims.beans.BARatioBean;
import com.app.aims.beans.BRMDetails;
import com.app.aims.beans.BillTypeBean;
import com.app.aims.beans.BillingReportBean;
import com.app.aims.beans.EmployeeRatioBean;
import com.app.aims.beans.HCReportBean;
import com.app.aims.beans.LocationBean;
import com.app.aims.beans.ReportBean;
import com.app.aims.beans.SearchResponse;
import com.app.aims.beans.TraineeRatioBean;
import com.app.aims.service.BillingService;
import com.app.aims.service.DashboardService;
import com.app.aims.service.EmployeeService;
import com.app.aims.vo.BillingDetailsReq;


@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    BillingService billingService;
    
    DecimalFormat format = new DecimalFormat("##0.00");
    
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
		List<BillTypeBean> billTypeList = new ArrayList();
		List<BRMDetails> brmWiseLocationList = new ArrayList();
		
		
		for (BRMDetails brmDetail : brmlist) {
			empList = employeeService.leftJoinDataByGL(brmDetail.getBrmId());	
		
		if (empList != null && !empList.isEmpty()) {
			if ("billable".equalsIgnoreCase(reportBean.getReportType())) {
				
				
				Map billableMap = getBillableCount(empList);
				
				BillingReportBean billBean = new BillingReportBean();
				billBean.setBrmName(brmDetail.getBrmName());;
				billBean.setBrmNo(brmDetail.getBrmId());
				billBean.setBillableCountTot(format.format(((Double)billableMap.get("billabletotal"))));
				billBean.setNbCountTot(format.format(((Double)billableMap.get("nbtotal"))));
				billBean.setBillableCountPerc(format.format(((Double)billableMap.get("billableperc"))));
				billBean.setNbCountPerc(format.format(((Double)billableMap.get("nbperc"))));
				billBean.setOnbillableCount(format.format(((Double)billableMap.get("onsitebillablecnt"))));
				billBean.setOnbillabePerc(format.format(((Double)billableMap.get("onsitebillableperc"))));
				billBean.setOffbillabeCount(format.format(((Double)billableMap.get("offsitenbillablecnt"))));
				billBean.setOffbillabePerc(format.format(((Double)billableMap.get("offsitebillableperc"))));
			
				billableReportList.add(billBean);
			}
			if ("srjrratio".equalsIgnoreCase(reportBean.getReportType())) {
				
				Map empRationMap = getSrJrList(empList);
				EmployeeRatioBean empsrjrRatio = new EmployeeRatioBean();
				empsrjrRatio.setBrmName(brmDetail.getBrmName());;
				empsrjrRatio.setBrmNo(brmDetail.getBrmId());
				empsrjrRatio.setJrCountPerc(format.format(((Double)empRationMap.get("jrcountperc"))));
				empsrjrRatio.setSrCountPerc(format.format(((Double)empRationMap.get("srcountperc"))));
				empsrjrRatio.setOffjrCountPerc(format.format(((Double)empRationMap.get("offjrcountperc"))));
				empsrjrRatio.setOffsrCountPerc(format.format(((Double)empRationMap.get("offsrcountperc"))));
				empsrjrRatio.setOnjrCountPerc(format.format(((Double)empRationMap.get("onjrcountperc"))));
				empsrjrRatio.setOnsrCountPerc(format.format((Double)empRationMap.get("onsrcountperc")));
				
				
				empsrjrRatio.setOnjrCountTot(format.format(((Double)empRationMap.get("onjrcounttot"))));
				empsrjrRatio.setOnsrCountTot(format.format(((Double)empRationMap.get("onsrcounttot"))));
				empsrjrRatio.setOffjrCountTot(format.format(((Double)empRationMap.get("offjrcounttot"))));
				empsrjrRatio.setOffsrCountTot(format.format(((Double)empRationMap.get("offsrcounttot"))));
				
				empsrjrRatio.setJrCountTot(format.format(((Double)empRationMap.get("jrcounttot"))));
				empsrjrRatio.setSrCountTot(format.format(((Double)empRationMap.get("srcounttot"))));
				
				empSrJrList.add(empsrjrRatio);
				

}
			if ("hcratio".equalsIgnoreCase(reportBean.getReportType())) {
				
				Map hcReportMap = getHCList(empList);
				HCReportBean hcreportBean =new HCReportBean();
				hcreportBean.setBrmName(brmDetail.getBrmName());
				hcreportBean.setBrmNo(brmDetail.getBrmId());
				hcreportBean.setOffPerc(format.format(((Double)hcReportMap.get("offperc"))));
				hcreportBean.setOnsitePerc(format.format(((Double)hcReportMap.get("onperc"))));
				hcreportBean.setTotalCnt(format.format(((Double)hcReportMap.get("totalcnt"))));
				hcreportBean.setOnsiteTot(format.format(((Double)hcReportMap.get("ontot"))));
				hcreportBean.setOffTot(format.format(((Double)hcReportMap.get("offtot"))));
				hcList.add(hcreportBean);
			}
			if ("trnratio".equalsIgnoreCase(reportBean.getReportType())) {

				Map traineeRatioMap = getTRList(empList);
				TraineeRatioBean traineeRatioBean =new TraineeRatioBean();
				traineeRatioBean.setBrmName(brmDetail.getBrmName());
				traineeRatioBean.setBrmNo(brmDetail.getBrmId());
				traineeRatioBean.setTrCountPerc(format.format(((Double)traineeRatioMap.get("tottrcountperc"))));
				traineeRatioBean.setOfftrCountPerc(format.format(((Double)traineeRatioMap.get("offtrcountperc"))));
				traineeRatioBean.setOntrCountPerc(format.format(((Double)traineeRatioMap.get("ontrcountperc"))));
				traineeRatioBean.setOntrCountTot(format.format(((Double)traineeRatioMap.get("ontrcounttot"))));
				traineeRatioBean.setOfftrCountTot(format.format(((Double)traineeRatioMap.get("offtrcounttot"))));
				traineeRatioBean.setTrCountTot(format.format(((Double)traineeRatioMap.get("trcounttot"))));
				
				trList.add(traineeRatioBean);
			}
			if ("baratio".equalsIgnoreCase(reportBean.getReportType())) {
				
				
				Map baMap = getBAList(empList);
				BARatioBean baratioBean =new BARatioBean();
				baratioBean.setBrmName(brmDetail.getBrmName());
				baratioBean.setBrmNo(brmDetail.getBrmId());
				baratioBean.setBaCountPerc(format.format(((Double)baMap.get("bacountperc"))));
				baratioBean.setBaCountPerOff(format.format(((Double)baMap.get("bacountperoff"))));
				baratioBean.setBaCountPerOn(format.format(((Double)baMap.get("bacountperon"))));
				baratioBean.setBaCountTot(format.format(((Double)baMap.get("bacounttot"))));
				baratioBean.setBaCountTotOff(format.format(((Double)baMap.get("bacounttotoff"))));
				baratioBean.setBaCountTotOn(format.format(((Double)baMap.get("bacounttoton"))));
				
				baList.add(baratioBean);
			}
			if ("billingType".equalsIgnoreCase(reportBean.getReportType())) {
				
				
				Map billableMap = getBillTypeBean(empList);
			
				BillTypeBean billBean = new BillTypeBean();
				billBean.setBrmName(brmDetail.getBrmName());;
				billBean.setBrmNo(brmDetail.getBrmId());
				billBean.setFpCountTotal(format.format(((Double)billableMap.get("fpcounttotal"))));
				billBean.setTmCountTotal(format.format(((Double)billableMap.get("tmcounttotal"))));
				billBean.setOnFPCountTotal(format.format(((Double)billableMap.get("onfpcounttotal"))));
				billBean.setOnTMCountTotal(format.format(((Double)billableMap.get("ontmcounttotal"))));
				billBean.setOffFPCountTotal(format.format(((Double)billableMap.get("offfpcounttotal"))));
				billBean.setOffTMCountTotal(format.format(((Double)billableMap.get("offtmcounttotal"))));
				billBean.setOnfpCountPerc(format.format(((Double)billableMap.get("onfpPerc"))));
				billBean.setOfffpCountPerc(format.format(((Double)billableMap.get("offfpPerc"))));
				billBean.setOntmCountPerc(format.format(((Double)billableMap.get("ontmPerc"))));
				billBean.setOfftmCountPerc(format.format(((Double)billableMap.get("offtmPerc"))));
				billBean.setFpCountPerc(format.format(((Double)billableMap.get("fpcountperc"))));
				billBean.setTmCountPerc(format.format(((Double)billableMap.get("tmcountperc"))));
				
				billTypeList.add(billBean);
			}
		if ("locationwisecount".equalsIgnoreCase(reportBean.getReportType())) {
				
				
				Map locationWiseMap = getLocationWiseCount(empList);
				Map<String, Integer> onlocationMap = (Map)locationWiseMap.get("onsiteMap");
				Map<String, Integer> offlocationMap = (Map)locationWiseMap.get("offsiteMap");
				
				List<LocationBean> locationList = new ArrayList();
				BRMDetails brmDetails = new BRMDetails();
				brmDetails.setBrmId(brmDetail.getBrmId());
				brmDetails.setBrmName(brmDetail.getBrmName());
				
				if (onlocationMap != null && !onlocationMap.isEmpty())
				{
					for(Map.Entry<String, Integer> entry : onlocationMap.entrySet())
					{
						LocationBean locationBean = new LocationBean();
						locationBean.setGeography("Onsite");
						locationBean.setLocation(entry.getKey());
						locationBean.setCount(entry.getValue().toString());
						
						locationList.add(locationBean);
					}
				}
				if (offlocationMap != null && !offlocationMap.isEmpty())
				{
					for(Map.Entry<String, Integer> entry : offlocationMap.entrySet())
					{
						LocationBean locationBean = new LocationBean();
						locationBean.setGeography("Offsite");
						locationBean.setLocation(entry.getKey());
						locationBean.setCount(entry.getValue().toString());
						
						locationList.add(locationBean);
					}
				}
				brmDetails.setLocationDetails(locationList);
				brmWiseLocationList.add(brmDetails);
			}
			
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
		 case "billingType" :
			 return billTypeList;
			 
		 case "locationwisecount" :
			 return brmWiseLocationList;
			 
			 default :
				 return null;

		}
		
		 
	}

	
private Map<String, Object> getBAList(List<SearchResponse> empList) {
		
		double bacountperc  = 0.0;
		double bacountperoff = 0.0;
		double bacountperon = 0.0;

		double bacounttot = 0;
		double bacounttotoff = 0;
		double bacounttoton=0;
		double onsitecnt=0;
		double offsitecnt=0;
		
		
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
		
		if (offsitecnt > 0)
			bacountperoff = (bacounttotoff/offsitecnt)*100;
		
		if (onsitecnt > 0)
			bacountperon = (bacounttoton/onsitecnt)*100;
		if ((offsitecnt+onsitecnt) > 0)
			bacountperc = ((bacounttotoff+bacounttoton)/(offsitecnt+onsitecnt))*100;


		baRatioMap.put("bacountperc", bacountperc);
		baRatioMap.put("bacountperoff", bacountperoff);
		baRatioMap.put("bacountperon", bacountperon);
		baRatioMap.put("bacounttot", bacounttot);
		baRatioMap.put("bacounttotoff", bacounttotoff);
		baRatioMap.put("bacounttoton", bacounttoton);
	    
		return baRatioMap;
	
	}

private Map<String, Object> getTRList(List<SearchResponse> empList) {
	
	double trcountperc =0.0;
	double trcountperoff =0.0;
	double trcountperon =0.0;

	double trcounttot = 0;
	double trcounttotoff = 0;
	double trcounttoton=0;
	double onsitecnt=0;
	double offsitecnt=0;
	
	
	Map<String, Object> baRatioMap = new HashMap<>();
	for (SearchResponse searchResponse : empList) {
		
		if ("Onsite".equalsIgnoreCase(searchResponse.getProjectLocation()))
		{
			onsitecnt++;
			if ("Trainee".equalsIgnoreCase(searchResponse.getPersonType()))
			{
				trcounttoton++;
			}
		}
		else if ("Offsite".equalsIgnoreCase(searchResponse.getProjectLocation()))
		{
			offsitecnt++;
			if ("Trainee".equalsIgnoreCase(searchResponse.getPersonType()))
			{
				trcounttotoff++;
			}
		}
	}
	trcounttot = trcounttoton+trcounttotoff;
	
	if (offsitecnt > 0)
		trcountperoff = (trcounttotoff/offsitecnt)*100;
	if (onsitecnt > 0 )
		trcountperon = (trcounttoton/onsitecnt)*100;
	if ((offsitecnt+onsitecnt) > 0)
		trcountperc = ((trcounttotoff+trcounttoton)/(offsitecnt+onsitecnt))*100;

	baRatioMap.put("tottrcountperc", trcountperc);
	baRatioMap.put("offtrcountperc", trcountperoff);
	baRatioMap.put("ontrcountperc", trcountperon);
	baRatioMap.put("trcounttot", trcounttot);
	baRatioMap.put("offtrcounttot", trcounttotoff);
	baRatioMap.put("ontrcounttot", trcounttoton);
    
	return baRatioMap;

}


	private Map<String, Object> getHCList(List<SearchResponse> empList) {
		
		double offperc= 0.0;
		double onperc= 0.0;

		double ontot = 0;
		double offtot = 0;
		double totalcnt=0;
		
		
		Map<String, Object> hcRatioMap = new HashMap<>();
		for (SearchResponse searchResponse : empList) {
			
			if ("Onsite".equalsIgnoreCase(searchResponse.getProjectLocation()))
			{
				ontot++;
			}
			else if ("Offsite".equalsIgnoreCase(searchResponse.getProjectLocation()))
			{
				offtot++;
			}
		}
		totalcnt = ontot+offtot;
		
		if (totalcnt > 0) {
			offperc = (offtot/totalcnt)*100;
			onperc = (ontot/totalcnt)*100;
			
		}
		

		hcRatioMap.put("offperc", onperc);
		hcRatioMap.put("onperc", offperc);
		hcRatioMap.put("totalcnt", totalcnt);
		hcRatioMap.put("ontot", ontot);
		hcRatioMap.put("offtot", offtot);
	    
		return hcRatioMap;
	
	}
	
	private Map<String, Object> getSrJrList(List<SearchResponse> empList) {
		
		double jrcountperc = 0.0;
		double srcountperc = 0.0;
		double offjrcountperc = 0.0;
		double offsrcountperc = 0.0;

		double onjrcountperc = 0.0;
		double onsrcountperc = 0.0;

		double totalJrCount = 0;
		double totalSrCount = 0;
		double totalCount=0;
		double offsrCountTot=0;
		double offjrCountTot=0;
		double onsrCountTot=0;
		double onjrCountTot=0;
		Map<String, Object> empRatioMap = new HashMap<>();		
		if (empList != null && !empList.isEmpty()) {
		

			for (SearchResponse searchResponse : empList) {
				
				if ("Onsite".equalsIgnoreCase(searchResponse.getProjectLocation()))
				{
					if ("S".equalsIgnoreCase(searchResponse.getSeniorJunior())) {
						onsrCountTot++;
					} 
					else if ("J".equalsIgnoreCase(searchResponse.getSeniorJunior())) {
						onjrCountTot++;
					}
					
				}
				else if ("Offsite".equalsIgnoreCase(searchResponse.getProjectLocation()))
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
			if ((onjrCountTot+onsrCountTot) > 0)
				onjrcountperc = (onjrCountTot/(onjrCountTot+onsrCountTot))*100;
			if ((onjrCountTot+onsrCountTot) > 0)
				onsrcountperc = (onsrCountTot/(onjrCountTot+onsrCountTot))*100;
			if ((offjrCountTot+offsrCountTot) > 0)
				offjrcountperc = (offjrCountTot/(offjrCountTot+offsrCountTot))*100;
			if ((offjrCountTot+offsrCountTot) > 0)
				offsrcountperc = (offsrCountTot/(offjrCountTot+offsrCountTot))*100;
			
			if (totalCount > 0 ) {
				jrcountperc  = (totalJrCount/totalCount)*100;
			    srcountperc = (totalSrCount/totalCount)*100;
	
			}
		    


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

		}
				return empRatioMap;
	}

private Map<String, Object> getBillTypeBean(List<SearchResponse> empList) {
		
		
		double tmCountPerc = 0;
		double fpCountPerc = 0;
		double offTMCountTotal =0.0;
		double offFPCountTotal = 0.0;
		double onTMCountTotal = 0.0;
		double onFPCountTotal = 0.0;
		double tmCountTotal = 0;
		double fpCountTotal = 0;
		double totalCount = 0;
		double onfpPerc = 0;
		double ontmPerc = 0;
		double offfpPerc = 0;
		double offtmPerc = 0;
		
		Map<String, Object> billTypeMap = new HashMap<>();
		for (SearchResponse searchResponse : empList) {
			
			if ("Onsite".equalsIgnoreCase(searchResponse.getProjectLocation()))
			{
				if ("TurnKey".equalsIgnoreCase(searchResponse.getProjectType())) {
					onFPCountTotal++;
				} 
				if ("Time and Material".equalsIgnoreCase(searchResponse.getProjectType())) {
					onTMCountTotal++;
				} 
			}
			if ("Offsite".equalsIgnoreCase(searchResponse.getProjectLocation()))
			{
				if ("TurnKey".equalsIgnoreCase(searchResponse.getProjectType())) {
					offFPCountTotal++;
				} 
				if ("Time and Material".equalsIgnoreCase(searchResponse.getProjectType())) {
					offTMCountTotal++;
				} 
			}
		}
		
		fpCountTotal = onFPCountTotal+offFPCountTotal;
		tmCountTotal = onTMCountTotal+offTMCountTotal;
		
		totalCount = fpCountTotal + tmCountTotal;
		
		if (totalCount > 0) {
			fpCountPerc = (fpCountTotal/totalCount)*100;
			tmCountPerc = (tmCountTotal/totalCount)*100;
		}
		if ((onFPCountTotal+onTMCountTotal) > 0)
			onfpPerc = (onFPCountTotal/(onFPCountTotal+onTMCountTotal))*100;
		if ((offFPCountTotal+offTMCountTotal) > 0)
			offfpPerc = (offFPCountTotal/(offFPCountTotal+offTMCountTotal))*100;

		if ((onFPCountTotal+onTMCountTotal) > 0)
			ontmPerc = (onTMCountTotal/(onFPCountTotal+onTMCountTotal))*100;
		if ((offFPCountTotal+offTMCountTotal) > 0)
			offtmPerc = (offTMCountTotal/(offFPCountTotal+offTMCountTotal))*100;
		
		
		billTypeMap.put("fpcounttotal", fpCountTotal);
		billTypeMap.put("tmcounttotal", tmCountTotal);
		billTypeMap.put("fpcountperc", fpCountPerc);
		billTypeMap.put("tmcountperc", tmCountPerc);
		
		billTypeMap.put("onfpcounttotal", onFPCountTotal);
		billTypeMap.put("ontmcounttotal", onTMCountTotal);
		billTypeMap.put("offfpcounttotal", offFPCountTotal);
		billTypeMap.put("offtmcounttotal", offTMCountTotal);
		billTypeMap.put("onfpPerc", onfpPerc);		
		billTypeMap.put("offfpPerc", offfpPerc);
		billTypeMap.put("ontmPerc", ontmPerc);		
		billTypeMap.put("offtmPerc", offtmPerc);
		return billTypeMap;
	}
	
private Map<String, Object> getBillableCount(List<SearchResponse> empList) {
	
	double onsiteBillableCount = 0;
	double onsiteNBillableCount = 0;
	double offsiteBillableCount = 0;
	double offsiteNBillableCount = 0;
	double onsiteBillablePerc =0.0;
	double offsiteBillablePerc = 0.0;
	double totalBillablePerc = 0.0;
	double totalNBillablePerc = 0.0;
	double totalBillableCnt = 0;
	double totalNBillableCnt = 0;
	double totalCount;
	
	Map<String, Object> billableRepMap = new HashMap<>();
	for (SearchResponse searchResponse : empList) {
		
		if ("Onsite".equalsIgnoreCase(searchResponse.getProjectLocation()))
		{
			if ("CC".equalsIgnoreCase(searchResponse.getCcIndicator())) {
				onsiteBillableCount++;
			} 
			else if ("NCC".equalsIgnoreCase(searchResponse.getCcIndicator())) {
				onsiteNBillableCount++;
			}
			
		}
		else if ("Offsite".equalsIgnoreCase(searchResponse.getProjectLocation()))
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
	
	if (totalCount > 0) {
		totalBillablePerc = (totalBillableCnt/totalCount)*100;
		totalNBillablePerc = (totalNBillableCnt/totalCount)*100;
	}
	if ((onsiteBillableCount+onsiteNBillableCount) > 0)
		onsiteBillablePerc = (onsiteBillableCount/(onsiteBillableCount+onsiteNBillableCount))*100;
	if ((offsiteBillableCount+offsiteNBillableCount) > 0)
		offsiteBillablePerc = (offsiteBillableCount/(offsiteBillableCount+offsiteNBillableCount))*100;
	
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

private Map<String, Object> getLocationWiseCount(List<SearchResponse> empList) {
	
	Map onlocationMap = new HashMap<>();
	Map offLocationMap = new HashMap<>();
	
	Map<String, Object> locationMap = new HashMap<>();
	for (SearchResponse searchResponse : empList) {
		
		if ("Offsite".equalsIgnoreCase(searchResponse.getProjectLocation()))
		{
			
				if (offLocationMap.containsKey(searchResponse.getWorkLocation()))
				{
					Integer count = (Integer)offLocationMap.get(searchResponse.getWorkLocation());
					
					offLocationMap.put(searchResponse.getWorkLocation(), ++count);
				}
				else
				{
					offLocationMap.put(searchResponse.getWorkLocation(), new Integer(1));
				}
			

			
		}
		else if ("Onsite".equalsIgnoreCase(searchResponse.getProjectLocation()))
		{
			
			if (onlocationMap.containsKey(searchResponse.getWorkLocation()))
			{
				Integer count = (Integer)onlocationMap.get(searchResponse.getWorkLocation());
				onlocationMap.put(searchResponse.getWorkLocation(), ++count);
			}
			else
			{
				onlocationMap.put(searchResponse.getWorkLocation(), new Integer(1));
			}
		
	}
	
	}
	locationMap.put("onsiteMap", onlocationMap);
	locationMap.put("offsiteMap", offLocationMap);
	
		return locationMap;
}
}

	
