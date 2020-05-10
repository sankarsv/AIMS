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
import com.app.aims.dao.BillingDao;
import com.app.aims.repository.BillingDataRepository;
import com.app.aims.repository.BillingVersionRespository;
import com.app.aims.vo.BillingDetailUpdateReq;
import com.app.aims.vo.BillingDetailsReq;

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
					brmDetails2.setBrmId("");
					brmDetails2.setBrmName(billingVersion.getBrmId());
					brmDetails.add(brmDetails2);
				}
			}
			
			return brmDetails;
		}
	   
		@Override
		public BillingVersion getBillingVersion(BillingDetailsReq req) {
			BillingVersion billingVersion = new BillingVersion();
			BillingVersion billingVersionRes = null;
			if(StringUtils.hasText(req.getMonth()))billingVersion.setMonth(req.getMonth());
			if(StringUtils.hasText(req.getBrmId()))billingVersion.setBrmId(req.getBrmId());
			if(StringUtils.hasText(req.getYear()))billingVersion.setYear(new Integer(req.getYear()).intValue());
			if(StringUtils.hasText(req.getVersion()))billingVersion.setVersion((new Integer(req.getVersion()).intValue()));
			Example<BillingVersion> billingVersionEx = Example.of(billingVersion);
			Optional<BillingVersion> optBillingVersion = billingVerRepo.findOne(billingVersionEx);
			if(optBillingVersion != null) {
				billingVersionRes = optBillingVersion.get();
			}
			
			return billingVersionRes;

		}

		@Override
		public List<Billing> getBillingDetails(int version) {
			return billingDataRepo.findByVersion(version);
		}

		@Override
		public void updateBillingDetails(Billing billing) {
			Session session = sessionFactory.getCurrentSession();
			session.update(billing);
			
		}
		
		@Override
		public void fetchAndUpdateBillingDetails(BillingDetailUpdateReq req) {
			int version = Integer.parseInt(req.getVersion());
			req.getBillingDetailsList().stream().forEach(bd -> {
				Billing billing = new Billing();
				billing.setVersion(version);
				billing.setEmpId(bd.getEmpId());
				Example<Billing> billingEx = Example.of(billing);
				Optional<Billing> optBilling = billingDataRepo.findOne(billingEx);
				if(optBilling != null) {
					Billing currBilling = optBilling.get();
					currBilling.setBillableHrs(bd.getBillableHrs());
					currBilling.setBillableDays(bd.getBillableDays());
					currBilling.setEffortHrs(bd.getEffortHrs());
					currBilling.setExtraBilling(bd.getExtraBilling());
					currBilling.setBillingAmount(bd.getBillingAmount());
					currBilling.setRemarks1(bd.getRemarks());
					updateBillingDetails(currBilling);
				} else {
					throw new NoSuchElementException();
				}
			});
		}
		
		@Override
		public void updateFreezeInd(BillingDetailsReq req) {
			int res = billingVerRepo.updateFreezeIndicator(req.getFreezeInd(), req.getBrmId(), req.getMonth(), req.getYear());
			if(!(res > 0)) {
				throw new NoSuchElementException();
			}
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
	        query1.setInteger("year",  billingVer.getYear());
	        query1.setParameter("month", billingVer.getMonth());
	        query1.setInteger("version", billingVer.getVersion());
	    	//user.setEmpName(val.getEmpName());
	        int status = query1.executeUpdate();

	        
		
			return (status > 0 ? true:false);
		}


}
