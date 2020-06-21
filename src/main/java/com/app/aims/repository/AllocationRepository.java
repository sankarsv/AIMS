package com.app.aims.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.app.aims.beans.Employee;
import com.app.aims.beans.EmployeeAllocation;

@Repository
public interface AllocationRepository extends JpaRepository<EmployeeAllocation,Integer>{
	
	
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query("DELETE FROM EmployeeAllocation ea WHERE ea.empId.employeeId IN :employeeIds")
	int deleteEmployees(@Param("employeeIds") Collection<Integer> employeeIds);
	
//	@Modifying(clearAutomatically = true, flushAutomatically = true)
//	@Query("DELETE FROM EmployeeAllocation ea WHERE ea.empId.employeeId=:employeeId")
//	int deleteEmployee(@Param("employeeId") Integer employeeId);
}
