package com.app.aims.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import com.app.aims.beans.BRMDetails;
import com.app.aims.beans.BatchAuditDetails;
import com.app.aims.beans.Billing;
import com.app.aims.beans.BillingVersion;
import com.app.aims.dao.BillingDao;
import com.app.aims.repository.BillingDataRepository;
import com.app.aims.repository.BillingVersionRespository;
import com.app.aims.vo.BillingDetailsReq;
import com.app.aims.vo.BillingDetailsResp;

@Transactional
@Repository
public class BillingDaoImpl implements BillingDao {


	    @Autowired
	    private SessionFactory sessionFactory;
	    
	    @Autowired
	    private BillingVersionRespository billingVerRepo;
	    
	    @Autowired
	    private BillingDataRepository billingDataRepo;
	    

		@Override
		public List<BRMDetails> retrieveBRMInfo() {
		    // TODO Auto-generated method stub
	        Session session = sessionFactory.getCurrentSession();
	        @SuppressWarnings("unchecked")
	        Criteria crit = session.createCriteria(BillingVersion.class).setProjection(
	        	    Projections.distinct(Projections.projectionList()
	        	    .add(Projections.property("brmId"), "brmId") ))
	        	.setResultTransformer(Transformers.aliasToBean(BillingVersion.class)); 

	        List<BillingVersion> brmDetails = crit.list();
	        
	        return populateBRMInfo(brmDetails);
		}
		
		private List<BRMDetails> populateBRMInfo(List<BillingVersion> billingVersionList) {
			
			List<BRMDetails> brmDetails = new ArrayList<BRMDetails>();
			BRMDetails brmDetails2 = null;
			if (billingVersionList != null) {
				
				for (BillingVersion billingVersion : billingVersionList) {
					brmDetails2 = new BRMDetails();
					brmDetails2.setBrmId(billingVersion.getBrmId());
					brmDetails2.setBrmName("");
					brmDetails.add(brmDetails2);
				}
			}
			
			return brmDetails;
		}
	   
		@Override
		public BillingVersion getBillingVersion(BillingDetailsReq req) {
			BillingVersion billingVersion = new BillingVersion();
			billingVersion.setMonth(req.getMonth());
			billingVersion.setBrmId(req.getBrmId());
			billingVersion.setYear(req.getYear());
			Example<BillingVersion> billingVersionEx = Example.of(billingVersion);
			BillingVersion billingVersionRes = billingVerRepo.findOne(billingVersionEx).get();
			return billingVersionRes;

		}

		@Override
		public List<Billing> getBillingDetails(BillingVersion req) {
			return billingDataRepo.findByVersion(Integer.parseInt(req.getVersion()));
		}

		@Override
		public void updateBillingDetails(Billing billing) {
			Session session = sessionFactory.getCurrentSession();
			session.save(billing);
			
		}

		@Override
		public boolean updateFreezeInd(BillingVersion billingVer) throws Exception {
			Session session = sessionFactory.getCurrentSession();
	        String queryStr = "UPDATE BillingVersion set freezeInd = :freezeInd where brmId = :brmId and "
	        		+ "year = :year and month = :month and version = :version"; 
	        
	        Query query1 = session.createQuery(queryStr);
	        System.out.println("Before Updating the Table");
	        query1.setParameter("freezeInd", billingVer.getFreezeInd());
	        query1.setParameter("brmId", billingVer.getBrmId());
	        query1.setInteger("year",  Integer.parseInt(billingVer.getYear()));
	        query1.setParameter("month", billingVer.getMonth());
	        query1.setInteger("version", Integer.parseInt(billingVer.getVersion()));
	    	//user.setEmpName(val.getEmpName());
	        int status = query1.executeUpdate();

	        
		
			return (status > 0 ? true:false);
		}

		
}
