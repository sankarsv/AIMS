package com.app.aims.dao;

import java.util.List;

import com.app.aims.beans.BRMDetails;
import com.app.aims.beans.Billing;
import com.app.aims.beans.BillingVersion;
import com.app.aims.vo.BillingDetailUpdateReq;
import com.app.aims.vo.BillingDetailsReq;

public interface BillingDao {

	    public List<BRMDetails> retrieveBRMInfo();

		public BillingVersion getBillingVersion(BillingDetailsReq req);
		
		public List<Billing> getBillingDetails(int version);
		
		public void updateBillingDetails (Billing billing);

		public boolean updateFreezeInd (BillingVersion billingVer) throws Exception;
		
		public void fetchAndUpdateBillingDetails(BillingDetailUpdateReq req);

		public void updateFreezeInd(BillingDetailsReq req);

}
