package com.incture.attendance.dto;

import java.util.Date;

public class TrackingInputDto {
	private String empId;
	private Date startDate;
	private Date endDate;

	// constructor
	public TrackingInputDto() {
		super();
	}

	public TrackingInputDto(String empId, Date startDate, Date endDate) {
		super();
		this.empId = empId;
		this.startDate = startDate;
		this.endDate = endDate;
	}

//Getter and Setter
	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
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

}
