package com.app.aims.vo;

public class SearchRequest {

	private Integer empId;
	
	private Integer dmId;
	
	private Integer glId;
	
	private String empName;
	
	private String teamRole;
	
	private String baseBranch;

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public Integer getDmId() {
		return dmId;
	}

	public void setDmId(Integer dmId) {
		this.dmId = dmId;
	}

	public Integer getGlId() {
		return glId;
	}

	public void setGlId(Integer glId) {
		this.glId = glId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getTeamRole() {
		return teamRole;
	}

	public void setTeamRole(String teamRole) {
		this.teamRole = teamRole;
	}

	public String getBaseBranch() {
		return baseBranch;
	}

	public void setBaseBranch(String baseBranch) {
		this.baseBranch = baseBranch;
	}

	
	
}
