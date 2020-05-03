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
    @Query("UPDATE BillingVersion b SET b.freezeInd = :freezeInd WHERE b.brmId = :brmId AND b.month = :month AND b.year = :year")
    public int updateFreezeIndicator(@Param("freezeInd") String freezeInd, @Param("brmId") String brmId, @Param("month") String month, @Param("year") String year);

}
