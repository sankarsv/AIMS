package com.app.aims.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.aims.beans.BillingDiscrepancy;
import com.app.aims.beans.Clarity;
import com.app.aims.dao.BillingDiscrepancyDao;
import com.app.aims.dao.ClarityDao;
import com.app.aims.service.BillingDiscrepancyService;
import com.app.aims.service.ClarityService;

@Service
@Transactional
public class BillingDiscrepancyServiceImpl implements BillingDiscrepancyService {

	@Autowired
	BillingDiscrepancyDao billingDescrepancyDao;

	@Override
	public List<BillingDiscrepancy> getBillingDescrepancyByBillingVersionID(String Brm) {

		return billingDescrepancyDao.retriveBillingDescrepancyByBillingVersionID(Brm);
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
