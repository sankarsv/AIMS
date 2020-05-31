package com.app.aims.service;

import java.util.List;

import com.app.aims.beans.BillingDiscrepancy;


public interface BillingDiscrepancyService {

	public List<BillingDiscrepancy> getBillingDescrepancyByBillingVersionID(String BillingVersionID);
	public void createBillingDiscrepancyDetails(BillingDiscrepancy billingDiscrDetails);
	public void createBillingDiscrepancyDetailsList(List<BillingDiscrepancy>  billingDiscrDetailsList);
}
