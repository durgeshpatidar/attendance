package com.incture.attendance.dto;

import java.sql.Time;
import java.util.Date;


public class TrackingDto extends BaseDto {
	
private String id;
	
	private String empId;
	private String addressId;
	private Date date;
	private Time checkIn;
	private Time checkOut;
	private Time totalHours;
	//Constructor
	public TrackingDto() {
		super();
		
	}
	public TrackingDto(String id, String empId, String addressId, Date date, Time checkIn, Time checkOut,
			Time totalHours) {
		super();
		this.id = id;
		this.empId = empId;
		this.addressId = addressId;
		this.date = date;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.totalHours = totalHours;
	}
	//Getters and Setters
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
	public Time getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(Time checkIn) {
		this.checkIn = checkIn;
	}
	public Time getCheckOut() {
		return checkOut;
	}
	public void setCheckOut(Time checkOut) {
		this.checkOut = checkOut;
	}
	public Time getTotalHours() {
		return totalHours;
	}
	public void setTotalHours(Time totalHours) {
		this.totalHours = totalHours;
	}
	
	//p
}
