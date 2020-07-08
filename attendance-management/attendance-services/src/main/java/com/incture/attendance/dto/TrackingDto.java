package com.incture.attendance.dto;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;


public class TrackingDto extends BaseDto {

	
	private String empId;
	private String addressId;
	private Date date;
	private Date checkIn;
	private Date checkOut;
	private double totalHours;
	//Constructor
	public TrackingDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TrackingDto(String empId, String addressId, Date date, Date checkIn, Date checkOut, double totalHours) {
		super();
		this.empId = empId;
		this.addressId = addressId;
		this.date = date;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.totalHours = totalHours;
	}
	//Getters and Setters
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
	
	
}
