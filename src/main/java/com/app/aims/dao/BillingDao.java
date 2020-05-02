package com.app.aims.dao;

import java.util.Date;
import java.util.List;

import com.app.aims.beans.BRMDetails;
import com.app.aims.beans.Billing;
import com.app.aims.beans.BillingFileData;
import com.app.aims.beans.BillingVersion;
import com.app.aims.beans.EditRequest;
import com.app.aims.beans.Employee;
import com.app.aims.beans.FileData;
import com.app.aims.beans.VersionInfo;
import com.app.aims.vo.BillingDetailsReq;
import com.app.aims.vo.BillingDetailsResp;

public interface BillingDao {

	    public List<BRMDetails> retrieveBRMInfo();

		public BillingVersion getBillingVersion(BillingDetailsReq req);
		
		public List<Billing> getBillingDetails(BillingVersion req);
		
		public void updateBillingDetails (Billing billing);
		
		public boolean updateFreezeInd (BillingVersion billingVer) throws Exception;
		
}
