package com.app.aims.service;

import com.app.aims.Exceptions.InvalidRequestException;
import com.app.aims.beans.Employee;
import com.app.aims.beans.ExportXlsRequest;

public interface ExportXlsService   {

	public byte[] downloadXlsReportOfEmployees ( ExportXlsRequest exportXlsRequest) throws InvalidRequestException;
	
}
