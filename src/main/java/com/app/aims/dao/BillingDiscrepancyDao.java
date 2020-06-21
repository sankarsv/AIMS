package com.app.aims.dao;

import java.util.List;

import com.app.aims.beans.BillingDiscrepancy;
import com.app.aims.beans.Employee;
import com.app.aims.vo.BillingDetailsReq;


public interface BillingDiscrepancyDao {
	public void addBillingDiscrepancyDetails(BillingDiscrepancy billingDiscrDetails);
	public List<BillingDiscrepancy> retriveBillingDescrepancyByBrmAndVersion(String BillingVersionID, Integer version);
	public void addBillingDiscrepancyDetailsList(List<BillingDiscrepancy> billingDiscrDetailsList);
	public Integer getMaxDescripancyVersion();
	public int updateDiscrepancyVersionInBillingVersion(BillingDetailsReq billingDetailReq, String discrepancyVersion);

}
