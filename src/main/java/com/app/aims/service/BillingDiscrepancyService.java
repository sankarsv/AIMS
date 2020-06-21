package com.app.aims.service;

import java.util.List;

import com.app.aims.beans.BillingDiscrepancy;
import com.app.aims.beans.BillingDiscrepancyResponse;


public interface BillingDiscrepancyService {

	public List<BillingDiscrepancyResponse> getBillingDescrepancyByVersion(String BillingVersionID, Integer discrepancyVersion);
	public void createBillingDiscrepancyDetails(BillingDiscrepancy billingDiscrDetails);
	public void createBillingDiscrepancyDetailsList(List<BillingDiscrepancy>  billingDiscrDetailsList);
}
