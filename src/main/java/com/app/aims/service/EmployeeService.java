package com.app.aims.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.app.aims.Exceptions.InvalidRequestException;
import com.app.aims.beans.BRMDetails;
import com.app.aims.beans.EditRequest;
import com.app.aims.beans.Employee;
import com.app.aims.beans.GenerateBaseLineRequest;
import com.app.aims.beans.SearchResponse;
import com.app.aims.beans.VersionInfo;

public interface EmployeeService {

	public void createUser(Employee user);
    public List<Employee> getUser();
    public Employee findById(int id);
    public List<Employee>  getEmployeeListByBrmId(String brm);
    public boolean update(EditRequest editRequest);
    public boolean deleteUserById(String id);
    public void generateBaseLine( GenerateBaseLineRequest generateBaseLineRequest)  throws ParseException, InvalidRequestException;

    public List<VersionInfo> getVersionInfo();
    public List<SearchResponse> leftJoinData(Integer empId);

    public List<SearchResponse> leftJoinDataByDM(String dmEmpName);
    public List<SearchResponse> leftJoinDataByDM(Integer dmEmpId);
    public List<SearchResponse> leftJoinDataByGL(String brmEmpId);
    public List<SearchResponse> leftJoinDataByGL(Integer brmEmpName);
    


    
    

}
