package com.app.aims.dao;




import java.util.List;

import com.app.aims.beans.BillingVersion;
import com.app.aims.vo.BillingDetailsReq;

public interface BillingVersionDao {

	public String getClarityVersionByMonthYear(BillingDetailsReq req);

	public List <BillingVersion> getBillingVersionByMonthYear(BillingDetailsReq req);

}
