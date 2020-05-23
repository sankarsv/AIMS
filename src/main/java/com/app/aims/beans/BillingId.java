package com.app.aims.beans;

import java.io.Serializable;
import java.util.Objects;

public class BillingId implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5656532316206560404L;
	
	private Integer version;
	private String empId;
	
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	
	public BillingId() {
		super();
	}
	
	public BillingId(String empId, int version) {
		this.empId = empId;
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
		return id.getEmpId().equalsIgnoreCase(this.empId) && (id.getVersion() == this.version);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(empId, version);
	}
}
