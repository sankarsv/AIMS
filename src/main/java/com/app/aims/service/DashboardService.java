package com.app.aims.service;

import java.util.List;

import com.app.aims.beans.ReportBean;

public interface DashboardService {
	
	public List<Object> generateDashboardReport(ReportBean reportBean);

}
