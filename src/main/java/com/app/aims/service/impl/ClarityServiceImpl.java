package com.app.aims.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.aims.beans.Clarity;

import com.app.aims.dao.ClarityDao;

import com.app.aims.service.ClarityService;

@Service
@Transactional
public class ClarityServiceImpl implements ClarityService {

	@Autowired
	ClarityDao clarityDao;

	@Override
	public List<Clarity> getClarityDetailsByBillingVersionID(Integer versionID) {
		System.out.println("Entered in to ClarityServiceImpl --->" + versionID);
		return clarityDao.retriveClarityDetailsByBillingVersionID(versionID);
		
	}

}
