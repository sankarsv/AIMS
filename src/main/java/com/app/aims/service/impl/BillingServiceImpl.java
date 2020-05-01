package com.app.aims.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.aims.Exceptions.InvalidRequestException;
import com.app.aims.beans.BRMDetails;
import com.app.aims.beans.BaseLine;
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

@Service
@Transactional
public class BillingServiceImpl implements BillingService {
    
    @Autowired
    BillingDao billingDao;

	@Override
	public List<BRMDetails> getBRMDetails() {
		
		return billingDao.retrieveBRMInfo();
	}


	

}
 	