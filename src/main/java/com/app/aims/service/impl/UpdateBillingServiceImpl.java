package com.app.aims.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.aims.beans.Billing;
import com.app.aims.beans.BillingRate;
import com.app.aims.dao.BaseLineDao;
import com.app.aims.dao.BillingDao;
import com.app.aims.dao.EmployeeDao;
import com.app.aims.service.UpdateBillingService;
import com.app.aims.util.ServiceUtil;
import com.app.aims.vo.BaseResponse;
import com.app.aims.vo.BillingDetailUpdateReq;
import com.app.aims.vo.BillingDetails;
import com.app.aims.vo.BillingDetailsResp;

@Service
@Transactional
public class UpdateBillingServiceImpl implements UpdateBillingService{
    
    @Autowired
    BillingDao billingDao;
    
    @Autowired
    ServiceUtil util ;
    
    @Autowired
    BaseLineDao baseLineDao;
    
    @Autowired
    EmployeeDao employeeDao;
    
	

	@Override
	public BaseResponse updateBillingDetails(BillingDetailUpdateReq req) {
		BaseResponse resp = null;
		try {
			List<Billing> billingDetailNewList = new ArrayList<Billing>();
			List<Billing> billingDetailDelList = new ArrayList<Billing>();
			List<String> empDelList = new ArrayList<String>();
			List<BillingDetails> billingDetailupdateList = new ArrayList<BillingDetails>();
			int version = Integer.parseInt(req.getVersion());
			req.getBillingDetailsList().forEach(bd -> {
				if("A".equalsIgnoreCase(bd.getAction())) {
					billingDetailNewList.add(populateBillingTableDetails(bd,version));
				}
				if("D".equalsIgnoreCase(bd.getAction())) {
					empDelList.add(bd.getEmpId());
					Billing billing = new Billing();
					billing.setVersion(version);
					billing.setEmpId(bd.getEmpId());
					BillingRate billingRate = new BillingRate();
					billingRate.setBilling(billing);
					billing.setBilingRate(billingRate);
					billingDetailDelList.add(billing);
					
				}
				if("U".equalsIgnoreCase(bd.getAction())) {
					billingDetailupdateList.add(bd);
				}
			});
			if(billingDetailupdateList.size() > 0) {
			billingDao.fetchAndUpdateBillingDetails(req.getVersion(),billingDetailupdateList);
			}
			if(billingDetailDelList.size() > 0) {
				employeeDao.deleteDetails(empDelList);
				billingDao.deleteDetails(billingDetailDelList);
			}
			if(billingDetailNewList.size() > 0) {
				billingDao.addBillingDetails(billingDetailNewList);
			}
		} catch(NoSuchElementException ex) {
			ex.printStackTrace();
			resp = new BillingDetailsResp();
			resp.addError("NOT FOUND");
			
		}catch(Exception e) {
		 e.printStackTrace();
		 resp = new BillingDetailsResp();
		 resp.addError("Exception Occurred");
		}
		return resp;
		
	}



	private Billing populateBillingTableDetails(BillingDetails bd, int version) {
		Billing billing = new Billing();
		BillingRate billingRate = new BillingRate();
		billing.setBillableDays(bd.getBillableDays());
		billing.setBillableHrs(bd.getBillableHrs());
		billing.setBillingAmount(bd.getBillingAmount());
		billing.setEffortHrs(bd.getEffortHrs());
		billing.setEmpId(bd.getEmpId());
		billing.setExtraBilling(bd.getExtraBilling());
		billing.setExtraHrs(bd.getExtraHrs());
		billing.setRemarks1(bd.getRemarks1());
		billing.setRemarks2(bd.getRemarks2());
		billing.setStoName(bd.getStoName());
		billing.setLocationId(bd.getLocationId());
		billing.setVersion(version);
		billing.setWonNumber(bd.getWonNumber());
		billingRate.setBillRate(bd.getBillRate());
		billingRate.setStartDate(new Date());
		billing.setBilingRate(billingRate);
		return null;
	}
	
	
}
 	