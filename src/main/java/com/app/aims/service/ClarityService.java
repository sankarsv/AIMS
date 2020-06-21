package com.app.aims.service;

import java.util.List;

import com.app.aims.beans.ClarityResponse;

public interface ClarityService {

	public List<ClarityResponse> getClarityDetailsByBillingVersionID(Integer versionID);
}
