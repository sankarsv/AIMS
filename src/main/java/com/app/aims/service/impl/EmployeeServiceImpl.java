package com.app.aims.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.aims.Exceptions.InvalidRequestException;
import com.app.aims.beans.BRMDetails;
import com.app.aims.beans.BaseLine;
import com.app.aims.beans.EditRequest;
import com.app.aims.beans.Employee;
import com.app.aims.beans.EmployeeAllocation;
import com.app.aims.beans.EmployeeMergedDetails;
import com.app.aims.beans.GenerateBaseLineRequest;
import com.app.aims.beans.SearchResponse;
import com.app.aims.beans.VersionInfo;
import com.app.aims.dao.BaseLineDao;
import com.app.aims.dao.BillingDao;
import com.app.aims.dao.EmployeeDao;
import com.app.aims.dao.EmployeeMergedDetailsDao;
import com.app.aims.repository.SearchRepository;
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
    
    @Autowired
    BillingDao billingDao;
    
    
    
    @Autowired
	private SearchRepository searchRepository;

	public List<SearchResponse> leftJoinData(Integer empId) {
		List<SearchResponse> searchEmpList = new ArrayList<SearchResponse>();
		List<Employee> emplist = searchRepository.fetchDataLeftJoin(empId);
		searchEmpList = prepareSearchResponse(emplist);
		
		return searchEmpList;

	}
    
    
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
    public List<Employee>  getEmployeeListByBrmId(String brm){
    	 List<Employee> emplistByBrmId = userDao.getEmployeeDetailsByBrmId(brm);
    	 return emplistByBrmId;
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
		//return userDao.update(editRequest);
		return false;
	}
//
//	@Override
//	public void generateBaseLine( GenerateBaseLineRequest generateBaseLineRequest) throws ParseException, InvalidRequestException {
//
//			if(generateBaseLineRequest == null || generateBaseLineRequest.getDate() == null || generateBaseLineRequest.getDate() == "") {
//				throw new InvalidRequestException("Please provide date.");
//			}
//			
//			Date baseLineDate = DateUtil.parseDate(generateBaseLineRequest.getDate());
//			List<Employee>	employeeDetails = userDao.getEmployeeDetailsByDate(baseLineDate);
//			
//			
//		
//			if(employeeDetails != null && employeeDetails.size() > 0 ) {
//				
//			BaseLine baseLine =	baseLineDao.getMaxBaseLineDetails(baseLineDate);
//			
//			
//			List<EmployeeMergedDetails> employeeMergedDetailsList = new ArrayList();
//			for(Employee employee: employeeDetails) {
//			
//				for( EmployeeAllocation employeeAllocation: employee.getEmployeeAllocations()) {
//				
//					EmployeeMergedDetails employeeMergedDetails = new EmployeeMergedDetails();
//					
//					System.out.println("Nidhi");
//					System.out.println(employeeAllocation.getPortfolioId());
//					
//					employeeMergedDetails.setEmployeeId(employee.getEmployeeId());
//					employeeMergedDetails.setEmployeeType(employee.getEmployeeType());
//					employeeMergedDetails.setCurrentLocation(employee.getCurrentLocation());
//					employeeMergedDetails.setFirstName(employee.getFirstName());
//					employeeMergedDetails.setLastName(employee.getLastName());
//					employeeMergedDetails.setBaseBranch (employee.getBaseBranch());
//					employeeMergedDetails.setDob  (employee.getDob());
//					employeeMergedDetails.setGender (employee.getGender());
//					employeeMergedDetails.setOverallExp (employee.getOverallExp());
//					employeeMergedDetails.setAimsExp (employee.getAimsExp());
//					employeeMergedDetails.setBaseCountry  (employee.getBaseCountry());
//					employeeMergedDetails.setBaseDc (employee.getBaseDc());
//					employeeMergedDetails.setCategoryName(employee.getCategoryName());
//					employeeMergedDetails.setGrade  (employee.getGrade());
//					//employeeMergedDetails.setWonId (employeeAllocation.getWon().getWonId());
//					employeeMergedDetails.setPortfolioId  (employeeAllocation.getPortfolioId().getPortfolioId());
//					employeeMergedDetails.setStartDate (employeeAllocation.getStartDate());
//					employeeMergedDetails.setEndDate(employeeAllocation.getEndDate());
//					employeeMergedDetails.setTravelType (employeeAllocation.getTravelType());
//					employeeMergedDetails.setEmployeeTravelCountry(employeeAllocation.getEmployeeTravelCountry());
//					employeeMergedDetails.setTeamRole  (employeeAllocation.getTeamRole());
//					employeeMergedDetails.setEmployeeActiveClientId(employeeAllocation.getEmployeeActiveClientInd());
//					employeeMergedDetails.setDeputeBranch  (employeeAllocation.getDeputeBranch());
//					employeeMergedDetails.setDeputeDc (employeeAllocation.getDeputeDc());
//					employeeMergedDetails.setEmployeeLocationId(employeeAllocation.getEmployeeLocationId());
//					
//					System.out.println(employeeAllocation.getPortfolioId().getPortfolioName());
//					System.out.println(employeeAllocation.getPortfolioId().getPortfolioType());
//					System.out.println(employeeAllocation.getPortfolioId().getBrmEmpId());
//					System.out.println(employeeAllocation.getPortfolioId().getOnsiteLeadEmpId());
//					System.out.println(employeeAllocation.getPortfolioId().getDmEmpId());
//					System.out.println(employeeAllocation.getPortfolioId().getOffshoreLeadEmpId());
//					System.out.println(employeeAllocation.getPortfolioId().getBillingEmpId());
//					
//					employeeMergedDetails.setPortfolioName  (employeeAllocation.getPortfolioId().getPortfolioName());
//					employeeMergedDetails.setPortfolioType  (employeeAllocation.getPortfolioId().getPortfolioType());
//				    employeeMergedDetails.setBrmEmpId (employeeAllocation.getPortfolioId().getBrmEmpId());
//				    employeeMergedDetails.setOnsiteLeadEmpId (employeeAllocation.getPortfolioId().getOnsiteLeadEmpId());
//					employeeMergedDetails.setDmEmpId (employeeAllocation.getPortfolioId().getDmEmpId());
//					employeeMergedDetails.setOffshoreLeadEmpId (employeeAllocation.getPortfolioId().getOffshoreLeadEmpId());
//					employeeMergedDetails.setBillingEmpId (employeeAllocation.getPortfolioId().getBillingEmpId());
//					//employeeMergedDetails.setDescription  (employeeAllocation.getPortfolioId().getDescription());
//					employeeMergedDetails.setProjectId (employeeAllocation.getProject().getProjectId());
//					employeeMergedDetails.setProjectName (employeeAllocation.getProject().getProjectName());
//					employeeMergedDetails.setProjectLocation  (employeeAllocation.getProject().getProjectLocation());
//					employeeMergedDetails.setProjectChangeDate (employeeAllocation.getProject().getProjectChangeDate());
//					employeeMergedDetails.setWorkGeography (employeeAllocation.getProject().getWorkGeography());
//					employeeMergedDetails.setWorkCountry (employeeAllocation.getProject().getWorkCountry());
//					employeeMergedDetails.setWorkLocation  (employeeAllocation.getProject().getWorkLocation());
//					employeeMergedDetails.setClientGeography(employeeAllocation.getProject().getClientGeography());
//					employeeMergedDetails.setClientCountry(employeeAllocation.getProject().getClientCountry());
//					employeeMergedDetails.setIp  (employeeAllocation.getProject().getIp());
//					employeeMergedDetails.setCustomer(employeeAllocation.getProject().getCustomer());
//					employeeMergedDetails.setGroupCustomer (employeeAllocation.getProject().getGroupCustomer());
//					employeeMergedDetails.setProjectHash (employeeAllocation.getProject().getProjectHash());
//					employeeMergedDetails.setProjectLocationWrtIndia  (employeeAllocation.getProject().getProjectLocationWrtIndia());
//					employeeMergedDetails.setProjectType  (employeeAllocation.getProject().getProjectType());
//					employeeMergedDetails.setPureTurnkeyFlag (employeeAllocation.getProject().getPureTurnkeyFlag());
//					employeeMergedDetails.setSwonCategory  (employeeAllocation.getProject().getSwonCategory());
//					employeeMergedDetails.setProjectCluster  (employeeAllocation.getProject().getProjectCluster());
//					employeeMergedDetails.setIou  (employeeAllocation.getProject().getIou());
//					employeeMergedDetails.setSubIou  (employeeAllocation.getProject().getSubIou());
//					employeeMergedDetails.setParentIou (employeeAllocation.getProject().getParentIou());
//					employeeMergedDetails.setChildIou(employeeAllocation.getProject().getChildIou());
//					employeeMergedDetails.setBfsCluster  (employeeAllocation.getProject().getBfsCluster());
//					employeeMergedDetails.setMappDesignation ("");
//					employeeMergedDetails.setBaseLine (baseLine);
//					employeeMergedDetails.setPercentageAllocation (employee.getEmployeeAllocationPercentage().getPercentageAllocation());
//					
//					employeeMergedDetailsList.add(employeeMergedDetails);
//				}
//			}
//			
//			employeeMergedDetailsDao.addEmployeeMergedDetails(employeeMergedDetailsList);
//			System.out.println("completed");
//			
//			}
//			
//
//	}

	@Override
	public List<VersionInfo> getVersionInfo() {
		
		return userDao.getVersionInfo();
		
		
	}




	@Override
	public List<SearchResponse> leftJoinDataByDM(Integer dmId) {
		List<SearchResponse> searchEmpList = new ArrayList<SearchResponse>();
		List<Employee> emplist = null;//searchRepository.fetchDataLeftJoinByDm(dmId);
		searchEmpList = prepareSearchResponse(emplist);

		return searchEmpList;

	}
	

	
	@Override
	public List<SearchResponse> leftJoinDataByDM(String dmName) {
		List<SearchResponse> searchEmpList = new ArrayList<SearchResponse>();
		List<Employee> emplist = searchRepository.fetchDataLeftJoinByDm(dmName);
		searchEmpList = prepareSearchResponse(emplist);

		return searchEmpList;

	}
	



	@Override
	public List<SearchResponse> leftJoinDataByGL(Integer brmEmpId) {
		List<SearchResponse> searchEmpList = new ArrayList<SearchResponse>();
		List<Employee> emplist = null;// searchRepository.fetchDataLeftJoinByBrm(brmEmpId);
		searchEmpList = prepareSearchResponse(emplist);

		return searchEmpList;

	}
	
	@Override
	public List<SearchResponse> leftJoinDataByGL(String brmName) {
		List<SearchResponse> searchEmpList = new ArrayList<SearchResponse>();
		List<Employee> emplist = searchRepository.fetchDataLeftJoinByBrm(brmName);
		searchEmpList = prepareSearchResponse(emplist);

		return searchEmpList;

	}

	private List<SearchResponse>  prepareSearchResponse(List<Employee> emplist) {
		

		List<SearchResponse> searchEmpList = new ArrayList<SearchResponse>();
		
		List<Integer>  empExistList = new ArrayList<Integer>();

		
		for (Employee employee : emplist) {
			
			if(empExistList.contains(employee.getEmployeeId())) {
				continue;
			}
			for (EmployeeAllocation employeeAllocation : employee.getEmployeeAllocations()) {

				SearchResponse searchResponse = new SearchResponse();
				
				 searchResponse.setEmployeeId(employee.getEmployeeId());
	    		 System.out.println("employeeID value is " + employee.getEmployeeId());
	    		 searchResponse.setEmployeeType(employee.getEmployeeType());
	    		 searchResponse.setCurrentLocation(employee.getCurrentLocation());
	    		 searchResponse.setFirstName(employee.getFirstName());
	    		 System.out.println("FirstName value is " + employee.getFirstName());
	    		 searchResponse.setLastName(employee.getLastName());
	    		 searchResponse.setBaseBranch(employee.getBaseBranch());
	    		 searchResponse.setDob(employee.getDob());
	    		 searchResponse.setGender(employee.getGender());
	    		 searchResponse.setOverallExp(employee.getOverallExp());
	    		 searchResponse.setAimsExp(employee.getAimsExp());
	    		 searchResponse.setBaseCountry(employee.getBaseCountry());
	    		 searchResponse.setBaseDc(employee.getBaseDc());
	    		 searchResponse.setCategoryName(employee.getCategoryName());
	    		 searchResponse.setGrade(employee.getGrade());
	    		 searchResponse.setSeniorJunior(employee.getSeniorJunior());
	    		 searchResponse.setCcIndicator(employee.getCcIndicator());
	    		 searchResponse.setMappDesignation(employee.getMappDesignation());
	    		 searchResponse.setBrmName(employee.getBrm());
	    		 searchResponse.setDmName(employee.getDm());
	    		// searchResponse.setWonId(employeeAllocation.getWon().getWonId());
	    		// searchResponse.setPortfolioId(employeeAllocation.getPortfolioId().getPortfolioId());
	    		 searchResponse.setStartDate(employeeAllocation.getStartDate());
	    		 searchResponse.setEndDate(employeeAllocation.getEndDate());
	    		 searchResponse.setTravelType(employeeAllocation.getTravelType());
	    		 searchResponse.setEmployeeTravelCountry(employee.getEmpTravel());
	    		 searchResponse.setTeamRole(employee.getTeamRole());
			     //searchResponse.setEmployeeActiveBMOId(employee.getEmployeeActiveClientInd());
				 searchResponse.setDeputeBranch(employee.getDeputeBranch());
				 searchResponse.setDeputeDc(employee.getDeputeDC());
				 searchResponse.setEmployeeLocationId(employee.getEmpLocation());
				 
				 
				 //searchResponse.setPortfolioName(employeeAllocation.getPortfolioId().getPortfolioName());
				 //searchResponse.setPortfolioType(employeeAllocation.getPortfolioId().getPortfolioType());
				 //searchResponse.setBrmEmpId(employee.getBrmEmpId());
				 //searchResponse.setOnsiteLeadEmpId(employeeAllocation.getProject().getOnsiteLeadEmpId());
				 //searchResponse.setDmEmpId(employeeAllocation.getPortfolioId().getDmEmpId());
				 //searchResponse.setOffshoreLeadEmpId(employeeAllocation.getPortfolioId().getOffshoreLeadEmpId());
				 //searchResponse.setBillingEmpId(employeeAllocation.getPortfolioId().getBillingEmpId());
				 
				 
				 searchResponse.setProjectId(employeeAllocation.getProject().getProjectId());
				 searchResponse.setProjectName(employeeAllocation.getProject().getProjectName());
				 searchResponse.setProjectLocation(employeeAllocation.getProject().getProjectLocation());
				 searchResponse.setProjectChangeDate(employeeAllocation.getProject().getProjectChangeDate());
				 searchResponse.setWorkGeography(employeeAllocation.getProject().getWorkGeography());
				 searchResponse.setWorkCountry(employeeAllocation.getProject().getWorkCountry());
				 searchResponse.setWorkLocation(employeeAllocation.getProject().getWorkLocation());
				 searchResponse.setClientGeography(employeeAllocation.getProject().getClientGeography());
				 searchResponse.setClientCountry(employeeAllocation.getProject().getClientCountry());
				 searchResponse.setIp(employeeAllocation.getProject().getIp());
				 searchResponse.setCustomer(employeeAllocation.getProject().getCustomer());
				 searchResponse.setGroupCustomer(employeeAllocation.getProject().getGroupCustomer());
				 searchResponse.setProjectHash(employeeAllocation.getProject().getProjectHash());
				 searchResponse.setProjectLocationWrtIndia(employeeAllocation.getProject().getProjectLocWRTIndia());
				 searchResponse.setProjectType(employeeAllocation.getProject().getProjectType());
				 searchResponse.setPersonType(employee.getPersonType());
				 searchResponse.setPureTurnkeyFlag(employeeAllocation.getProject().getPureTurnkeyFlag());
				 searchResponse.setSwonCategory(employeeAllocation.getProject().getSwonCategory());
				 searchResponse.setProjectCluster(employeeAllocation.getProject().getProjectCluster());
				 searchResponse.setIou(employeeAllocation.getProject().getIou());
				 searchResponse.setSubIou(employeeAllocation.getProject().getSubIou());
				 searchResponse.setParentIou(employeeAllocation.getProject().getParentIou());
				 searchResponse.setChildIou(employeeAllocation.getProject().getChildIou());
				 searchResponse.setBfsCluster(employeeAllocation.getProject().getBfsCluster());

			//	 if (employeeAllocation.getEndDate() == null )
					 searchEmpList.add(searchResponse);	
				 empExistList.add(employee.getEmployeeId());

			}

		}
		
		return searchEmpList;

	}

	@Override
	public void generateBaseLine(GenerateBaseLineRequest generateBaseLineRequest)
			throws ParseException, InvalidRequestException {
		// TODO Auto-generated method stub
		
	}


}
 	