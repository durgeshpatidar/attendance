package com.incture.attendance.dto;

import java.util.Date;

public class WorkflowTaskDto extends BaseDto {

	private String description;
	private String empId;
	private String managerId;
	private Date requestDate;
	private String comment;
	private String status;
	
	//Constructors
	
	public WorkflowTaskDto() {
		super();

	}
	public WorkflowTaskDto(String description, String empId, String mangerId, Date requestdate, String  comment, String status, String managerId) {
		super();
		
		this.description = description;
		this.empId = empId;
		this.managerId = managerId;
		this.requestDate = requestdate;
		this.comment = comment;
		this.status = status;
	}
	
  //getters and setters

	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String id) {
		this.empId = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public Date getRequestdate() {
		return requestDate;
	}
	public void setRequestdate(Date requestDate) {
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
