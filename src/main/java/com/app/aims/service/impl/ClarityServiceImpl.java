package com.app.aims.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.aims.beans.Clarity;
import com.app.aims.beans.ClarityResponse;
import com.app.aims.dao.ClarityDao;

import com.app.aims.service.ClarityService;

@Service
@Transactional
public class ClarityServiceImpl implements ClarityService {

	@Autowired
	ClarityDao clarityDao;

	@Override
	public List<ClarityResponse> getClarityDetailsByBillingVersionID(Integer versionID) {
		System.out.println("Entered in to ClarityServiceImpl --->" + versionID);
		 List<ClarityResponse> clarityResponseList = new ArrayList<ClarityResponse>();
		 List<Clarity> clarityList = clarityDao.retriveClarityDetailsByBillingVersionID(versionID);
		 if(clarityList != null && clarityList.size() > 0) {
			 clarityResponseList = clarityList.stream().map(cl -> {
				 ClarityResponse clr = new ClarityResponse();
				 clr.setAverageRate(cl.getAverageRate());
				 clr.setCccio(cl.getCccio());
				 clr.setCin(cl.getCin());
				 clr.setClarityId(cl.getClarityId());
				 clr.setLastNameFirstName(cl.getLastNameFirstName());
				 clr.setOfficeId(cl.getOfficeId());
				 clr.setRateWithoutTax(cl.getRateWithoutTax());
				 clr.setResourceId(cl.getResourceId());
				 clr.setResourceManager(cl.getResourceManager());
				 clr.setSumOfHours(cl.getSumOfHours());
				 clr.setTimesheetDepartment(cl.getTimesheetDepartment());
				 clr.setTransactionClass(cl.getTransactionClass());
				 clr.setVersion(cl.getVersion());
				 return clr;
			 }).collect(Collectors.toList());
		 }
		return clarityResponseList;
	}

}
