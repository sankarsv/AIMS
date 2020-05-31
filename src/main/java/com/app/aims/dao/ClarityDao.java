package com.app.aims.dao;

import java.util.List;

import com.app.aims.beans.Clarity;

public interface ClarityDao {

	public List<Clarity> retriveClarityDetailsByBillingVersionID(Integer BillingVersionID);

}
