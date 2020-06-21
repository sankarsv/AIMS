package com.app.aims.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.aims.beans.BillingDiscrepancy;
import com.app.aims.beans.Clarity;

@Repository
public interface BillingDiscrepancyDataRepository extends JpaRepository<BillingDiscrepancy, Integer> {
	
	@Query("SELECT u FROM BillingDiscrepancy u WHERE u.brm = :brm and u.version = :version")
	public List<BillingDiscrepancy> findBillingDescrepancyByBillingVersionId(@Param("brm") String brm,@Param("version") Integer version);

	@Modifying
	@Query("Delete FROM BillingDiscrepancy bd WHERE bd.version = :version")
	public void deleteByVersion(Integer version);
}
