package com.app.aims.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.aims.beans.BillingDiscrepancy;
import com.app.aims.beans.BillingDiscrepancyResponse;
import com.app.aims.beans.Clarity;
import com.app.aims.dao.BillingDiscrepancyDao;
import com.app.aims.dao.ClarityDao;
import com.app.aims.service.BillingDiscrepancyService;
import com.app.aims.service.ClarityService;
import com.app.aims.util.ServiceUtil;

@Service
@Transactional
public class BillingDiscrepancyServiceImpl implements BillingDiscrepancyService {

	@Autowired
	BillingDiscrepancyDao billingDescrepancyDao;

	@Autowired
	ServiceUtil util;
	
	@Override
	public List<BillingDiscrepancyResponse> getBillingDescrepancyByVersion(String brm,Integer version) {
		List<BillingDiscrepancyResponse> billingDiscrepancyRes = new ArrayList<BillingDiscrepancyResponse>();
		Map<Integer, String> portfolioMap = util.getPortfolioMap();
		List<BillingDiscrepancy>  billingDiscrepancyList=  billingDescrepancyDao.retriveBillingDescrepancyByBrmAndVersion(brm,version);
		if(billingDiscrepancyList != null && billingDiscrepancyList.size() > 0) {
			billingDiscrepancyRes = billingDiscrepancyList.stream().map(bd -> {
				BillingDiscrepancyResponse bdResp = new BillingDiscrepancyResponse();
				bdResp.setAccruedHours(bd.getAccruedHours());
				bdResp.setBrm(portfolioMap.get(Integer.valueOf(brm)));
				bdResp.setDm(bd.getDm());
				bdResp.setClarityHours(bd.getClarityHours());
				bdResp.setCleanupComments(bd.getCleanupComments());
				bdResp.setCurrency(bd.getCurrency());
				bdResp.setCurrentInvoiceHours(bd.getCurrentInvoiceHours());
				bdResp.setDifference(bd.getDifference());
				bdResp.setDiscrepancyId(bd.getDiscrepancyId());
				bdResp.setEmployeeId(bd.getEmployeeId());
				bdResp.setEmployeeName(bd.getEmployeeName());
				bdResp.setFileName(bd.getFileName());
				bdResp.setLocation(bd.getLocation());
				bdResp.setOfficeId(bd.getOfficeId());
				bdResp.setProjectName(bd.getProjectName());
				bdResp.setProjectNo(bd.getProjectNo());
				bdResp.setRateWithoutTax(bd.getRateWithoutTax());
				bdResp.setRemarks(bd.getRemarks());
				return bdResp;
			}).collect(Collectors.toList());
		}
		return billingDiscrepancyRes;
	}
	
	
	@Override
	public void createBillingDiscrepancyDetails(BillingDiscrepancy billingDiscrDetails) {

		billingDescrepancyDao.addBillingDiscrepancyDetails(billingDiscrDetails);;
	}
	
	@Override
	public void createBillingDiscrepancyDetailsList(List<BillingDiscrepancy>  billingDiscrDetailsList) {

		 billingDescrepancyDao.addBillingDiscrepancyDetailsList(billingDiscrDetailsList);
	}

}
