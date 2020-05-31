package com.app.aims.service;

import java.util.List;

import com.app.aims.beans.BRMDetails;
import com.app.aims.beans.Billing;
import com.app.aims.beans.BillingVersion;
import com.app.aims.vo.BaseResponse;
import com.app.aims.vo.BillingDetailUpdateReq;
import com.app.aims.vo.BillingDetailsReq;
import com.app.aims.vo.BillingDetailsResp;
import com.app.aims.vo.DownloadXlsResponse;

public interface BillingService {

    public List<BRMDetails> getBRMDetails();   
    
    public List<BillingDetailsResp> getBillingDetails(BillingDetailsReq req);
    
    public boolean updateFreeze(BillingVersion req);

    public BaseResponse updateBillingDetails(BillingDetailUpdateReq req);

	public BaseResponse updateFreezeInd(BillingDetailsReq req);

	public DownloadXlsResponse downloadXlsBillingReport(BillingDetailsReq billingDetailReq);
	
	//it gives BillingDetailsList for Clarity descrepancy
	public List<Billing> RetriveBillingDetailsListByBillingVersion(Integer billingVersion);
	
	//it gives BillingVersion for Clarity descrepancy
	public List<BillingVersion> getBillingVersion(BillingDetailsReq req);
    
}
