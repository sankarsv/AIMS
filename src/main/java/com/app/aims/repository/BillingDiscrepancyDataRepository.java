package com.app.aims.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.aims.beans.BillingDiscrepancy;
import com.app.aims.beans.Clarity;

@Repository
public interface BillingDiscrepancyDataRepository extends JpaRepository<Clarity, Integer> {
	
	@Query("SELECT u FROM BillingDiscrepancy u WHERE u.brm = :brm")
	public List<BillingDiscrepancy> findBillingDescrepancyVyBillingVersionId(@Param("brm") String BillingVersionId);
}
