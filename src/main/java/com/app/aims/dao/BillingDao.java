package com.app.aims.dao;

import java.util.List;

import com.app.aims.beans.BRMDetails;
import com.app.aims.beans.Billing;
import com.app.aims.beans.BillingVersion;
import com.app.aims.vo.BillingDetails;
import com.app.aims.vo.BillingDetailsReq;

public interface BillingDao {

	    public List<BRMDetails> retrieveBRMInfo();

		public List<BillingVersion> getBillingVersion(BillingDetailsReq req);
		
		public List<Billing> getBillingDetails(int version);
		
		public void updateBillingDetails (Billing billing);

		public boolean updateFreezeInd (BillingVersion billingVer) throws Exception;
		
		public void fetchAndUpdateBillingDetails(String ver, List<BillingDetails> billingDetailsList);

		public void updateFreezeInd(BillingDetailsReq req);

		public List<BillingVersion> getBillingVersionByMonth(BillingDetailsReq req, boolean sort);
		
		public void saveNewBillingDetails(List<BillingVersion> billingVersionList, List<Billing> billingList);
		
		public void addBillingDetails(List<Billing> billingList);
		
		public void deleteDetails(List<Billing> billingList);
		
		public List<Billing> getBillingDetailsWithVersions(List<Integer> versions);

		public List<BillingVersion> getBillingVersionByClaritydescrepancyVersion(BillingDetailsReq req);

}
