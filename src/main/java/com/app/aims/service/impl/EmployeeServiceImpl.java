package com.app.aims.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.aims.Exceptions.InvalidRequestException;
import com.app.aims.beans.BaseLine;
import com.app.aims.beans.EditRequest;
import com.app.aims.beans.Employee;
import com.app.aims.beans.EmployeeAllocation;
import com.app.aims.beans.EmployeeMergedDetails;
import com.app.aims.beans.GenerateBaseLineRequest;
import com.app.aims.beans.VersionInfo;
import com.app.aims.dao.BaseLineDao;
import com.app.aims.dao.EmployeeDao;
import com.app.aims.dao.EmployeeMergedDetailsDao;
import com.app.aims.service.EmployeeService;
import com.app.aims.util.DateUtil;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeDao userDao;
    
    @Autowired
    BaseLineDao baseLineDao;

    @Autowired
    EmployeeMergedDetailsDao employeeMergedDetailsDao;
    
    @Override
    public List<Employee> getUser() {
        // TODO Auto-generated method stub
        return userDao.getUser();
    }
    
    @Override
    public Employee findById(int id) {
        // TODO Auto-generated method stub
        return userDao.findById(id);
    }

    @Override
    public void createUser(Employee user) {
        // TODO Auto-generated method stub
        userDao.addUser(user);
    }

    @Override
    public boolean deleteUserById(String empId) {
        // TODO Auto-generated method stub
        return userDao.delete(empId);
    }

	@Override
	public boolean update(EditRequest editRequest) {
		// TODO Auto-generated method stub
		return userDao.update(editRequest);
	}

	@Override
	public void generateBaseLine( GenerateBaseLineRequest generateBaseLineRequest) throws ParseException, InvalidRequestException {

			if(generateBaseLineRequest == null || generateBaseLineRequest.getDate() == null || generateBaseLineRequest.getDate() == "") {
				throw new InvalidRequestException("Please provide date.");
			}
			
			Date baseLineDate = DateUtil.parseDate(generateBaseLineRequest.getDate());
			List<Employee>	employeeDetails = userDao.getEmployeeDetailsByDate(baseLineDate);
			
			
		
			if(employeeDetails != null && employeeDetails.size() > 0 ) {
				
			BaseLine baseLine =	baseLineDao.getMaxBaseLineDetails(baseLineDate);
			
			
			List<EmployeeMergedDetails> employeeMergedDetailsList = new ArrayList();
			for(Employee employee: employeeDetails) {
			
				for( EmployeeAllocation employeeAllocation: employee.getEmployeeAllocations()) {
				
					EmployeeMergedDetails employeeMergedDetails = new EmployeeMergedDetails();
					
					System.out.println("Nidhi");
					System.out.println(employeeAllocation.getPortfolioId());
					
					employeeMergedDetails.setEmployeeId(employee.getEmployeeId());
					employeeMergedDetails.setEmployeeType(employee.getEmployeeType());
					employeeMergedDetails.setCurrentLocation(employee.getCurrentLocation());
					employeeMergedDetails.setFirstName(employee.getFirstName());
					employeeMergedDetails.setLastName(employee.getLastName());
					employeeMergedDetails.setBaseBranch (employee.getBaseBranch());
					employeeMergedDetails.setDob  (employee.getDob());
					employeeMergedDetails.setGender (employee.getGender());
					employeeMergedDetails.setOverallExp (employee.getOverallExp());
					employeeMergedDetails.setAimsExp (employee.getAimsExp());
					employeeMergedDetails.setBaseCountry  (employee.getBaseCountry());
					employeeMergedDetails.setBaseDc (employee.getBaseDc());
					employeeMergedDetails.setCategoryName(employee.getCategoryName());
					employeeMergedDetails.setGrade  (employee.getGrade());
					employeeMergedDetails.setWonId (employeeAllocation.getWon().getWonId());
					employeeMergedDetails.setPortfolioId  (employeeAllocation.getPortfolioId().getPortfolioId());
					employeeMergedDetails.setStartDate (employeeAllocation.getStartDate());
					employeeMergedDetails.setEndDate(employeeAllocation.getEndDate());
					employeeMergedDetails.setTravelType (employeeAllocation.getTravelType());
					employeeMergedDetails.setEmployeeTravelCountry(employeeAllocation.getEmployeeTravelCountry());
					employeeMergedDetails.setTeamRole  (employeeAllocation.getTeamRole());
					employeeMergedDetails.setEmployeeActiveClientId(employeeAllocation.getEmployeeActiveClientInd());
					employeeMergedDetails.setDeputeBranch  (employeeAllocation.getDeputeBranch());
					employeeMergedDetails.setDeputeDc (employeeAllocation.getDeputeDc());
					employeeMergedDetails.setEmployeeLocationId(employeeAllocation.getEmployeeLocationId());
					
					System.out.println(employeeAllocation.getPortfolioId().getPortfolioName());
					System.out.println(employeeAllocation.getPortfolioId().getPortfolioType());
					System.out.println(employeeAllocation.getPortfolioId().getBrmEmpId());
					System.out.println(employeeAllocation.getPortfolioId().getOnsiteLeadEmpId());
					System.out.println(employeeAllocation.getPortfolioId().getDmEmpId());
					System.out.println(employeeAllocation.getPortfolioId().getOffshoreLeadEmpId());
					System.out.println(employeeAllocation.getPortfolioId().getBillingEmpId());
					
					employeeMergedDetails.setPortfolioName  (employeeAllocation.getPortfolioId().getPortfolioName());
					employeeMergedDetails.setPortfolioType  (employeeAllocation.getPortfolioId().getPortfolioType());
				    employeeMergedDetails.setBrmEmpId (employeeAllocation.getPortfolioId().getBrmEmpId());
				    employeeMergedDetails.setOnsiteLeadEmpId (employeeAllocation.getPortfolioId().getOnsiteLeadEmpId());
					employeeMergedDetails.setDmEmpId (employeeAllocation.getPortfolioId().getDmEmpId());
					employeeMergedDetails.setOffshoreLeadEmpId (employeeAllocation.getPortfolioId().getOffshoreLeadEmpId());
					employeeMergedDetails.setBillingEmpId (employeeAllocation.getPortfolioId().getBillingEmpId());
					//employeeMergedDetails.setDescription  (employeeAllocation.getPortfolioId().getDescription());
					employeeMergedDetails.setProjectId (employeeAllocation.getProject().getProjectId());
					employeeMergedDetails.setProjectName (employeeAllocation.getProject().getProjectName());
					employeeMergedDetails.setProjectLocation  (employeeAllocation.getProject().getProjectLocation());
					employeeMergedDetails.setProjectChangeDate (employeeAllocation.getProject().getProjectChangeDate());
					employeeMergedDetails.setWorkGeography (employeeAllocation.getProject().getWorkGeography());
					employeeMergedDetails.setWorkCountry (employeeAllocation.getProject().getWorkCountry());
					employeeMergedDetails.setWorkLocation  (employeeAllocation.getProject().getWorkLocation());
					employeeMergedDetails.setClientGeography(employeeAllocation.getProject().getClientGeography());
					employeeMergedDetails.setClientCountry(employeeAllocation.getProject().getClientCountry());
					employeeMergedDetails.setIp  (employeeAllocation.getProject().getIp());
					employeeMergedDetails.setCustomer(employeeAllocation.getProject().getCustomer());
					employeeMergedDetails.setGroupCustomer (employeeAllocation.getProject().getGroupCustomer());
					employeeMergedDetails.setProjectHash (employeeAllocation.getProject().getProjectHash());
					employeeMergedDetails.setProjectLocationWrtIndia  (employeeAllocation.getProject().getProjectLocationWrtIndia());
					employeeMergedDetails.setProjectType  (employeeAllocation.getProject().getProjectType());
					employeeMergedDetails.setPureTurnkeyFlag (employeeAllocation.getProject().getPureTurnkeyFlag());
					employeeMergedDetails.setSwonCategory  (employeeAllocation.getProject().getSwonCategory());
					employeeMergedDetails.setProjectCluster  (employeeAllocation.getProject().getProjectCluster());
					employeeMergedDetails.setIou  (employeeAllocation.getProject().getIou());
					employeeMergedDetails.setSubIou  (employeeAllocation.getProject().getSubIou());
					employeeMergedDetails.setParentIou (employeeAllocation.getProject().getParentIou());
					employeeMergedDetails.setChildIou(employeeAllocation.getProject().getChildIou());
					employeeMergedDetails.setBfsCluster  (employeeAllocation.getProject().getBfsCluster());
					employeeMergedDetails.setMappDesignation ("");
					employeeMergedDetails.setBaseLine (baseLine);
					employeeMergedDetails.setPercentageAllocation (employee.getEmployeeAllocationPercentage().getPercentageAllocation());
					
					employeeMergedDetailsList.add(employeeMergedDetails);
				}
			}
			
			employeeMergedDetailsDao.addEmployeeMergedDetails(employeeMergedDetailsList);
			System.out.println("completed");
			
			}
			

	}

	@Override
	public List<VersionInfo> getVersionInfo() {
		
		return userDao.getVersionInfo();
		
		
	}


	

}
 	