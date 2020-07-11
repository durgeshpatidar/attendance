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
	
	//Constructors
	
	public WorkflowTaskDto() {
		super();

	}
	public WorkflowTaskDto(String id,String empName,String managerName, String description, String empId, String mangerId, Date requestdate, String  comment, String status, String managerId) {
		super();
		this.id = id;
		this.managerName=managerName;
		this.empName=empName;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
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
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

}
