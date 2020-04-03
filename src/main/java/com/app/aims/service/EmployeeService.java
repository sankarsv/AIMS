package com.app.aims.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.app.aims.Exceptions.InvalidRequestException;
import com.app.aims.beans.EditRequest;
import com.app.aims.beans.Employee;
import com.app.aims.beans.GenerateBaseLineRequest;

public interface EmployeeService {

	public void createUser(Employee user);
    public List<Employee> getUser();
    public Employee findById(int id);
    public boolean update(EditRequest editRequest);
    public boolean deleteUserById(String id);
    public void generateBaseLine( GenerateBaseLineRequest generateBaseLineRequest)  throws ParseException, InvalidRequestException;
    
    

}
