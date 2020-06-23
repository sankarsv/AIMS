package com.app.aims.dao;

import java.util.Date;
import java.util.List;

import com.app.aims.beans.BillingFileData;
import com.app.aims.beans.ClarityFileData;
import com.app.aims.beans.EditRequest;
import com.app.aims.beans.Employee;
import com.app.aims.beans.FileData;
import com.app.aims.beans.VersionInfo;

public interface EmployeeDao {

	    public void addUser(Employee user);
	    public List<Employee> getUser();
	    public Employee findById(int id);
	  //  public boolean update(EditRequest editRequest);
	    public boolean delete(String empId);
	    public List<Employee> getEmployeeDetails();
	    public List<Employee> getEmployeeDetailsByBrmId(String brm);
	    public List<VersionInfo> getVersionInfo();
	    public boolean uploadFile(FileData  fileData);
		public boolean uploadBrFile(BillingFileData billingFileData);
		public void deleteDetails(List<Integer> empDelList);
		public boolean uploadClFile(ClarityFileData clarityFileData);
		public boolean updateOfficeId(String empId,String officeId);
		
}
