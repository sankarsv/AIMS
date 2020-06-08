package com.app.aims.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.app.aims.beans.BRMDetails;
import com.app.aims.beans.Billing;
import com.app.aims.beans.BillingVersion;
import com.app.aims.beans.Clarity;
import com.app.aims.dao.BillingDao;
import com.app.aims.dao.ClarityDao;
import com.app.aims.repository.BillingDataRepository;
import com.app.aims.repository.BillingVersionRespository;
import com.app.aims.repository.ClarityDataRepository;
import com.app.aims.vo.BillingDetailUpdateReq;
import com.app.aims.vo.BillingDetailsReq;

@Transactional
@Repository
public class ClarityDaoImpl implements ClarityDao {


	    @Autowired
	    private SessionFactory sessionFactory;
	    
	    @Autowired
	    private ClarityDataRepository clarityDataRepository;
	    
		@Override
		public List<Clarity>  retriveClarityDetailsByBillingVersionID(Integer versionID){
		    // TODO Auto-generated method stub
	        Session session = sessionFactory.getCurrentSession();
	        List<Clarity> clarityDetails = clarityDataRepository.findByBillingVersionId(versionID);
	        return clarityDetails;
		}
		
		


}
