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

public interface AllocationRepository extends JpaRepository<EmployeeAllocation,Integer>{
	
	
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query("DELETE FROM EmployeeAllocation ea WHERE ea.empId IN :employeeIds")
	int deleteEmployees(@Param("employeeIds") Collection<String> employeeIds);
}
