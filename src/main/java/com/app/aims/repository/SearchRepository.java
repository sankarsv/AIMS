package com.app.aims.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import com.app.aims.beans.Employee;
import com.app.aims.beans.EmployeeAllocation;

public interface SearchRepository extends JpaRepository<Employee,Integer>{
	
    
	@Query("SELECT e,pr,p,ea FROM Employee e,Project pr,Portfolio p,EmployeeAllocation ea WHERE e.employeeId = :empId")
	List<Employee> fetchDataLeftJoin(@Param("empId")  Integer empId);
	
	@Query("SELECT e,pr,p,ea FROM Employee e,Project pr,Portfolio p,EmployeeAllocation ea WHERE p.dmEmpId = :dmEmpId")
	List<Employee> fetchDataLeftJoinByDm(@Param("dmEmpId")  Integer dmEmpId);
	
	@Query("SELECT e,pr,p,ea FROM Employee e,Project pr,Portfolio p,EmployeeAllocation ea WHERE p.brmEmpId = :brmEmpId")
	List<Employee> fetchDataLeftJoinByBrm(@Param("brmEmpId")  Integer brmEmpId);
	

}
