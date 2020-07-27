//A dto for displaying tracking details in history page.
package com.incture.attendance.dto;

import java.util.Date;

public class TrackingDetailsDto extends BaseDto {
	private String empId;
	private String empName;
	private Date date;
	private Date checkIn;
	private Date checkOut;
	private String id;
	private double totalHours;
	private String status;

	public TrackingDetailsDto() {
		super();
	}

	public TrackingDetailsDto(String id,String empId, String empName, Date date, Date checkIn, Date checkOut, double totalHours,
			String status) {
		super();
		this.id=id;
		this.empId = empId;
		this.empName = empName;
		this.date = date;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.totalHours = totalHours;
		this.status = status;
	}

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
