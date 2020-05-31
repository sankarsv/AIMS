package com.app.aims.dao;

import java.util.List;

import com.app.aims.beans.BillingDiscrepancy;
import com.app.aims.beans.Employee;


public interface BillingDiscrepancyDao {
	public void addBillingDiscrepancyDetails(BillingDiscrepancy billingDiscrDetails);
	public List<BillingDiscrepancy> retriveBillingDescrepancyByBillingVersionID(String BillingVersionID);
	public void addBillingDiscrepancyDetailsList(List<BillingDiscrepancy> billingDiscrDetailsList);

}
