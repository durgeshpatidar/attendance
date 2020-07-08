package com.incture.attendance.dto;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;


public class TrackingDto extends BaseDto {
	
private String id;
	
	private String empId;
	private String addressId;
	private Date date;
	private Timestamp checkIn;
	private Timestamp checkOut;
	private Time totalHours;
	//Constructor ha
	public TrackingDto() {
		super();
		
	}
	public TrackingDto(String id, String empId, String addressId, Date date, Timestamp checkIn, Timestamp checkOut,
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
	public Timestamp getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(Timestamp checkIn) {
		this.checkIn = checkIn;
	}
	public Timestamp getCheckOut() {
		return checkOut;
	}
	public void setCheckOut(Timestamp checkOut) {
		this.checkOut = checkOut;
	}
	public Time getTotalHours() {
		return totalHours;
	}
	public void setTotalHours(Time totalHours) {
		this.totalHours = totalHours;
	}
	

}
