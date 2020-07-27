package com.incture.attendance.dto;

import java.util.Date;

public class TrackingDto extends BaseDto {

	private String id;
	private String empId;
	private String addressId;
	private Date date;
	private Date checkIn;
	private Date checkOut;
	private double totalHours;
	private String status;

	// Constructor
	public TrackingDto() {
		super();
	}

	public TrackingDto(String id, String empId, String addressId, Date date, Date checkIn, Date checkOut,
			double totalHours, String status) {
		super();
		this.id = id;
		this.empId = empId;
		this.addressId = addressId;
		this.date = date;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.totalHours = totalHours;
		this.status = status;
	}

	// Getters and Setters.
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
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

}
