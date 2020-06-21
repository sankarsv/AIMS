package com.app.aims.service;

import com.app.aims.beans.ExportXlsRequest;
import com.app.aims.vo.BillingDetailsReq;
import com.app.aims.vo.DownloadXlsResponse;

public interface ExportXlsService   {

	public byte[] downloadXlsReportOfEmployees ( ExportXlsRequest exportXlsRequest) throws Exception;
	
	public DownloadXlsResponse downloadXlsDiscrepancyReport(BillingDetailsReq req);
	
}
