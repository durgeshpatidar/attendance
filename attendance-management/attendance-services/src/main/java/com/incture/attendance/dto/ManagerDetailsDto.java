package com.incture.attendance.dto;

public class ManagerDetailsDto {
	private String managerName;
	private String startDate;
	private String endDate;
	private String managerType;
	
	//Constructor
	public ManagerDetailsDto() {
		super();
	}
	public ManagerDetailsDto(String managerName, String startDate, String endDate, String managerType) {
		super();
		this.managerName = managerName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.managerType = managerType;
	}
	
	//Getters and Setters
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getManagerType() {
		return managerType;
	}
	public void setManagerType(String managerType) {
		this.managerType = managerType;
	}
	
	

}
