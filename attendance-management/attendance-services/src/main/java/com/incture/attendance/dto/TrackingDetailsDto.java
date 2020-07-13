package com.incture.attendance.dto;

import java.util.Date;

public class TrackingDetailsDto extends BaseDto {
	private String empId;
	private String empName;
	private Date date;
	private Date checkIn;
	private Date checkOut;
	private double totalHours;

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}

	public double getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(double totalHours) {
		this.totalHours = totalHours;
	}

	public TrackingDetailsDto(String empId, String empName, Date date, Date checkIn, Date checkOut, double totalHours) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.date = date;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.totalHours = totalHours;
	}

	public TrackingDetailsDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
