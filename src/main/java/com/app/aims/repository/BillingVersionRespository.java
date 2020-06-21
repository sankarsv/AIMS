package com.app.aims.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.aims.beans.BillingVersion;

@Repository
public interface BillingVersionRespository extends JpaRepository<BillingVersion, Integer> {
	
	@Modifying
    @Query("UPDATE BillingVersion b SET b.freezeInd = :freezeInd WHERE b.brm_EmpNo = :brm_EmpNo AND b.periodMonth = :periodMonth AND b.year = :year")
    public int updateFreezeIndicator(@Param("freezeInd") String freezeInd, @Param("brm_EmpNo") String brmId, @Param("periodMonth") String month, @Param("year") Integer year);
	
	@Modifying
    @Query("UPDATE BillingVersion b SET b.discrerpancyVersion=:discrepancyVersion WHERE b.periodMonth = :periodMonth AND b.year = :year")
	public int updateDiscrepancyVersion(@Param("discrepancyVersion")String discrepancyVersion, @Param("periodMonth") String periodMonth, @Param("year") int year);

}
