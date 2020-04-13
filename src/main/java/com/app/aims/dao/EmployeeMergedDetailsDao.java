package com.app.aims.dao;

import java.util.List;

import com.app.aims.beans.EmployeeMergedDetails;
import com.app.aims.beans.HCDetails;

public interface EmployeeMergedDetailsDao {
	  public  List<HCDetails>  findByVersionNo(int baseLine);
	  public void addEmployeeMergedDetails(List<EmployeeMergedDetails> employeeMergedDetails);
	 
}
