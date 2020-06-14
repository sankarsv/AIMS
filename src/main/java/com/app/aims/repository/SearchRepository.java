package com.app.aims.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import com.app.aims.beans.Employee;
import com.app.aims.beans.EmployeeAllocation;

public interface SearchRepository extends JpaRepository<Employee,String>{
	
    
	@Query("SELECT DISTINCT e,pr,ea FROM Employee e,Project pr,EmployeeAllocation ea WHERE e.employeeId = :empId and e.employeeId=ea.empId and pr.projectId=ea.project")
	List<Employee> fetchDataLeftJoin(@Param("empId")  Integer empId);
	
	@Query("SELECT  DISTINCT e,pr,ea FROM Employee e,Project pr, EmployeeAllocation ea WHERE e.dm = :dmId and e.employeeId=ea.empId and pr.projectId=ea.project")
	List<Employee> fetchDataLeftJoinByDm(@Param("dmId")  String dmId);
	
	@Query("SELECT u FROM Employee u WHERE u.brm = :brm")
	List<Employee> getEmployeeListByBrmId(@Param("brm")  String brm);

	//@Query("SELECT e,pr,ea FROM Employee e,Project pr, EmployeeAllocation ea WHERE e.dm = :dmId")
	//List<Employee> fetchDataLeftJoinByDm(@Param("dmEmpId")  Integer dmId);
	
	@Query("SELECT e,pr,ea FROM Employee e,Project pr, EmployeeAllocation ea WHERE e.brm = :brmEmpId and e.employeeId=ea.empId and pr.projectId=ea.project")
	List<Employee> fetchDataLeftJoinByBrm(@Param("brmEmpId")  String brmEmpId);
	
	//@Query("SELECT e,pr,ea FROM Employee e,Project pr, EmployeeAllocation ea WHERE e.brm = :brmEmpId")
	//List<Employee> fetchDataLeftJoinByBrm(@Param("brmEmpId")  Integer brmEmpId);
	
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query("DELETE FROM Employee e WHERE e.employeeId IN :employeeIds")
	int deleteEmployees(@Param("employeeIds") Collection<String> employeeIds);
}
