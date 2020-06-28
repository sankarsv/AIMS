package com.app.aims.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "BILLRATE", schema = "AIMS")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })

public class BillingRate implements Serializable{

	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@EmbeddedId
	private BillingId id;
	
	@MapsId
	@JoinColumns({
        @JoinColumn(name="employee_id", referencedColumnName="employee_id"),
        @JoinColumn(name="version", referencedColumnName="version")
    })
	@OneToOne(cascade = CascadeType.ALL)
	private Billing billing;

	@Column(name = "BILLRATE")
	@NotNull
	private String billRate;

	@Column(name = "CURRENCR")
	private String currency;
	
	@Column(name = "STARTDATE")
	private Date startDate;
	
	@Column(name = "ENDDATE")
	private Date endDate;


	public String getBillRate() {
		return billRate;
	}

	public void setBillRate(String billRate) {
		this.billRate = billRate;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public BillingId getId() {
		return id;
	}

	public void setId(BillingId id) {
		this.id = id;
	}

	public Billing getBilling() {
		return billing;
	}

	public void setBilling(Billing billing) {
		this.billing = billing;
	}
	
}