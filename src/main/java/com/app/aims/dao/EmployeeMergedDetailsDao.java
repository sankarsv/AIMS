package com.app.aims.dao;

import java.util.List;

import com.app.aims.beans.EmployeeMergedDetails;

public interface EmployeeMergedDetailsDao {
	  public  List<EmployeeMergedDetails>  findByBaseLine(int baseLine);
	  public void addEmployeeMergedDetails(List<EmployeeMergedDetails> employeeMergedDetails);
	 
}
