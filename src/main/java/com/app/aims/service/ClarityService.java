package com.app.aims.service;

import java.util.List;

import com.app.aims.beans.Clarity;

public interface ClarityService {

	public List<Clarity> getClarityDetailsByBillingVersionID(Integer versionID);
}
