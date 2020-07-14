package com.incture.attendance.dto;

import java.util.Date;

public class WorkflowTaskDto extends BaseDto {

	private String id;
	private String empName;
	private String description;
	private String empId;
	private String managerId;
	private String managerName;
	private Date requestDate;
	private String comment;
	private String status;

	// Constructors

	public WorkflowTaskDto() {
		super();

	}

	public WorkflowTaskDto(String id, String empName, String description, String empId, String managerId,
			String managerName, Date requestDate, String comment, String status) {
		super();
		this.id = id;
		this.empName = empName;
		this.description = description;
		this.empId = empId;
		this.managerId = managerId;
		this.managerName = managerName;
		this.requestDate = requestDate;
		this.comment = comment;
		this.status = status;
	}
	//Getters and Setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}