package com.app.aims.beans;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BillingId implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5656532316206560404L;
	
	@Column(name="version")
	private Integer version;
	
	@Column(name="employee_id")
	private String employee_id;
	
	
	public String getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}
	
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	
	public BillingId() {
		super();
	}
	
	public BillingId(String empId, int version) {
		this.employee_id = empId;
		this.version = version;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == this ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
		BillingId id = (BillingId) o;
		return id.getEmployee_id().equalsIgnoreCase(this.employee_id) && (id.getVersion() == this.version);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getEmployee_id(), version);
	}
}
