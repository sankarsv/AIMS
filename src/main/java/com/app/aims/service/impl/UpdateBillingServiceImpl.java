package com.app.aims.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.poi.ss.formula.functions.Now;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.app.aims.beans.Billing;
import com.app.aims.beans.BillingId;
import com.app.aims.beans.BillingRate;
import com.app.aims.beans.BillingVersion;
import com.app.aims.dao.BaseLineDao;
import com.app.aims.dao.BillingDao;
import com.app.aims.dao.EmployeeDao;
import com.app.aims.service.UpdateBillingService;
import com.app.aims.util.ServiceUtil;
import com.app.aims.vo.BaseResponse;
import com.app.aims.vo.BillingDetailUpdateReq;
import com.app.aims.vo.BillingDetails;
import com.app.aims.vo.BillingDetailsReq;
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
			List<Integer> empDelList = new ArrayList<Integer>();
			List<BillingDetails> billingDetailupdateList = new ArrayList<BillingDetails>();
			req.getBillingDetailsList().forEach(bd -> {
				if("A".equalsIgnoreCase(bd.getAction())) {
					Integer version = getBillingVersion(req,bd);
					if (version == null || version == 0) {
						version = createBillingVersion(req,bd);
					} 
					billingDetailNewList.add(populateBillingTableDetails(bd,version));
					if(StringUtils.hasText(bd.getOfficeId())) {
						employeeDao.updateOfficeId(bd.getEmpId(), bd.getOfficeId());
					}
				}
				if("D".equalsIgnoreCase(bd.getAction())) {
					if(StringUtils.hasText(bd.getEmpId())) {
					empDelList.add(Integer.parseInt(bd.getEmpId()));
					if(StringUtils.hasText(req.getVersion())){
					Billing billing = new Billing();
					int version = Integer.parseInt(req.getVersion());
					BillingRate billingRate = new BillingRate();
					BillingId id = new BillingId();
					id.setVersion(version);
					id.setEmployee_id(bd.getEmpId());
					billing.setId(id);
					billingRate.setBilling(billing);
					billingRate.setId(id);;
					billing.setBilingRate(billingRate);
					billingDetailDelList.add(billing);
					}
				   }
				}
				if("U".equalsIgnoreCase(bd.getAction())) {
					billingDetailupdateList.add(bd);
					if(StringUtils.hasText(bd.getOfficeId())) {
						employeeDao.updateOfficeId(bd.getEmpId(), bd.getOfficeId());
					}
				}
			});
			if(billingDetailupdateList.size() > 0) {
			billingDao.fetchAndUpdateBillingDetails(req.getVersion(),billingDetailupdateList);
			}
			if(empDelList.size() > 0) {
				employeeDao.deleteDetails(empDelList);
			}
			if(billingDetailDelList.size() > 0) {
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



	private Integer createBillingVersion(BillingDetailUpdateReq updateReq, BillingDetails bd) {
		BillingVersion billVer = new BillingVersion();
		BillingDetailsReq req = new BillingDetailsReq();
		req.setMonth(updateReq.getMonth());
		req.setYear(updateReq.getYear());
		List<BillingVersion> versionDetList = billingDao.getBillingVersionByMonth(req,false);
		if(versionDetList != null && versionDetList.size() > 0) {
			int maxVer = baseLineDao.getMaxBillingVersion();
			maxVer = maxVer+1;
			billVer.setBillingComments("");
			billVer.setBrm_EmpNo(String.valueOf(bd.getBrm()));
			billVer.setClarityVersion(versionDetList.get(0).getClarityVersion());
			billVer.setDiscrerpancyVersion(versionDetList.get(0).getDiscrerpancyVersion());
			billVer.setDraftIndicator(null);
			billVer.setFreezeInd("N");
			billVer.setLocation(null);
			billVer.setPeriodMonth(versionDetList.get(0).getPeriodMonth());
			billVer.setYear(versionDetList.get(0).getYear());
			billVer.setVersion(maxVer);
			billingDao.addBillingVersion(billVer);
		} else {
			throw new NoSuchElementException("No Version for the requested month");
		}
		return billVer.getVersion();
	}



	private Billing populateBillingTableDetails(BillingDetails bd, int version) {
		Billing billing = new Billing();
		BillingId id = new BillingId();
		BillingRate billingRate = new BillingRate();
		billing.setBillableDays(bd.getBillableDays());
		billing.setBillableHrs(bd.getBillableHrs());
		billing.setBillingAmount(bd.getBillingAmount());
		billing.setEffortHrs(bd.getEffortHrs());
		billing.setExtraBilling(bd.getExtraBilling());
		billing.setExtraHrs(bd.getExtraHrs());
		billing.setRemarks1(bd.getRemarks1());
		billing.setRemarks2(bd.getRemarks2());
		billing.setStoName(bd.getStoName());
		billing.setLocationId(bd.getLocationId());
		id.setEmployee_id(bd.getEmpId());
		id.setVersion(version);
		billing.setId(id);
		billing.setWonNumber(bd.getWonNumber());
		billingRate.setBillRate(bd.getBillRate());
		billingRate.setStartDate(new Date());
		billingRate.setCurrency("CAD");
		billingRate.setId(id);;
		billingRate.setBilling(billing);
		billing.setBilingRate(billingRate);
		return billing;
	}
	
	private Integer getBillingVersion(BillingDetailUpdateReq req,BillingDetails bd) {
		BillingDetailsReq billingDetailReq = new BillingDetailsReq();
		Integer version = 0;
		billingDetailReq.setBrmId(bd.getBrm());
		billingDetailReq.setMonth(req.getMonth());
		billingDetailReq.setYear(req.getYear());
		List<BillingVersion> versionDetList = billingDao.getBillingVersion(billingDetailReq);
		if (versionDetList != null && versionDetList.size() > 0) {
			BillingVersion versionDet = versionDetList.get(0);
			version = versionDet.getVersion();
		}
		return version;
	}
	
}
 	