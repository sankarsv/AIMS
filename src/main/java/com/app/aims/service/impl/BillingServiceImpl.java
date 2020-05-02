package com.app.aims.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.app.aims.Exceptions.InvalidRequestException;
import com.app.aims.beans.BRMDetails;
import com.app.aims.beans.BaseLine;
import com.app.aims.beans.Billing;
import com.app.aims.beans.BillingVersion;
import com.app.aims.beans.EditRequest;
import com.app.aims.beans.Employee;
import com.app.aims.beans.EmployeeAllocation;
import com.app.aims.beans.EmployeeMergedDetails;
import com.app.aims.beans.GenerateBaseLineRequest;
import com.app.aims.beans.SearchResponse;
import com.app.aims.beans.VersionInfo;
import com.app.aims.dao.BaseLineDao;
import com.app.aims.dao.BillingDao;
import com.app.aims.dao.EmployeeDao;
import com.app.aims.dao.EmployeeMergedDetailsDao;
import com.app.aims.repository.SearchRepository;
import com.app.aims.service.BillingService;
import com.app.aims.service.EmployeeService;
import com.app.aims.util.DateUtil;
import com.app.aims.vo.BillingDetailsReq;
import com.app.aims.vo.BillingDetailsResp;

@Service
@Transactional
public class BillingServiceImpl implements BillingService {
    
    @Autowired
    BillingDao billingDao;

	@Override
	public List<BRMDetails> getBRMDetails() {
		
		return billingDao.retrieveBRMInfo();
	}

	@Override
	public List<BillingDetailsResp> getBillingDetails(BillingDetailsReq req) {
		BillingDetailsResp resp = null;
		List<BillingDetailsResp> respList= null;
		try {
			BillingVersion versionDet = billingDao.getBillingVersion(req);
			if(versionDet != null) {
				List<Billing> billingList = billingDao.getBillingDetails(versionDet);
				respList = populateBillingDetailsList(billingList,versionDet);
			} else {
				resp = new BillingDetailsResp();
				respList = new ArrayList<BillingDetailsResp>();
				resp.addError("NOT FOUND");
				respList.add(resp);
			}
			
		}catch(Exception e) {
		e.printStackTrace();
		resp = new BillingDetailsResp();
		respList = new ArrayList<BillingDetailsResp>();
		resp.addError("Exception Occurred");
		respList.add(resp);
		}
		return respList;
	}

	private List<BillingDetailsResp> populateBillingDetailsList(List<Billing> billingList, BillingVersion versionDet) {
		
		List<BillingDetailsResp> result = billingList.stream().map(bl -> {
			BillingDetailsResp resp = new BillingDetailsResp();
            resp.setBillableDays(bl.getBillableDays());
            resp.setBillableHrs(bl.getBillableHrs());
            resp.setBillingAmount(bl.getBillingAmount());
            resp.setBrmId(versionDet.getBrmId());
            resp.setBrnname(bl.getBrnname());
            resp.setDmId(bl.getDmId());
            resp.setDmName(bl.getDmName());
            resp.setEffortHrs(bl.getEffortHrs());
            resp.setEmpId(bl.getEmpId());
            resp.setEmpName(bl.getEmpName());
            resp.setExtraBilling(bl.getExtraBilling());
            resp.setExtraHrs(bl.getExtraHrs());
            resp.setFreezeInd(versionDet.getFreezeInd());
            resp.setLocationId(bl.getLocationId());
            resp.setOfficeId(bl.getOfficeId());
            resp.setProjectId(bl.getProjectId());
            resp.setRemarks(addRemarks(bl.getRemarks1(),bl.getRemarks2()));
            resp.setStoName(bl.getStoName());
            resp.setWonNumber(bl.getWonNumber());
            return resp;
        }).collect(Collectors.toList());
		return result;
	}


	private String addRemarks(String remark1,String remark2) {
		if(StringUtils.hasText(remark1) && StringUtils.hasText(remark2)) {
			return remark1+" "+remark2;
		} else if(!StringUtils.hasText(remark1)) {
			return remark2;
		} else  {
			return remark1;
		}
	}
	

}
 	