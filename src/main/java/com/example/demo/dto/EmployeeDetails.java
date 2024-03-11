package com.example.demo.dto;

public class EmployeeDetails {
	private String employeeName;
    private String rankDescription;
    private String departmentName;
    private String supervisorName;
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getRankDescription() {
		return rankDescription;
	}
	public void setRankDescription(String rankDescription) {
		this.rankDescription = rankDescription;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getSupervisorName() {
		return supervisorName;
	}
	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}
}
