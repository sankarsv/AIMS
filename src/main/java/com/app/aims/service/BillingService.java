package com.app.aims.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.app.aims.Exceptions.InvalidRequestException;
import com.app.aims.beans.BRMDetails;
import com.app.aims.beans.BillingVersion;
import com.app.aims.beans.EditRequest;
import com.app.aims.beans.Employee;
import com.app.aims.beans.GenerateBaseLineRequest;
import com.app.aims.beans.SearchResponse;
import com.app.aims.beans.VersionInfo;
import com.app.aims.vo.BaseResponse;
import com.app.aims.vo.BillingDetailsReq;
import com.app.aims.vo.BillingDetailsResp;

public interface BillingService {

    public List<BRMDetails> getBRMDetails();   
    
    public List<BillingDetailsResp> getBillingDetails(BillingDetailsReq req);
    
    public boolean updateFreeze(BillingVersion req);
    
    //public BaseResponse updateBillingDetails(BillingDetailsUpdateReq req);
    

}
