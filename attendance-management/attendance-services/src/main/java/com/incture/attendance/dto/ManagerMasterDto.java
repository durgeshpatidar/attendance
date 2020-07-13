package com.incture.attendance.dto;

public class ManagerMasterDto {

	private String id;
	private String employeeId;
	private String managerId;
	private String startDate;
	private String endDate;
	private String managerType;

	// Constructor
	public ManagerMasterDto() {
		super();
	}

	public ManagerMasterDto(String id, String employeeId, String managerId, String startDate, String endDate,
			String managerType) {
		super();
		this.id = id;
		this.employeeId = employeeId;
		this.managerId = managerId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.managerType = managerType;
	}

	// Getters and Setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
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
