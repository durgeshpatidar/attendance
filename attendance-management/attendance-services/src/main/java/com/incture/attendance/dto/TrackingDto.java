package com.incture.attendance.dto;

import java.sql.Time;
import java.util.Date;


public class TrackingDto {
	
private String id;
	
	private int empId;
	private int addressId;
	private Date date;
	private Time checkin;
	private Time checkout;
	private Time total_hours;
	
	//Constructors
	public TrackingDto() {
		super();

	}
	public TrackingDto(String id, int empId, int addressId, Date date, Time checkin, Time checkout, Time total_hours) {
		super();
		this.id = id;
		this.empId = empId;
		this.addressId = addressId;
		this.date = date;
		this.checkin = checkin;
		this.checkout = checkout;
		this.total_hours = total_hours;
	}
	
	//Getters and Setters
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Time getCheckin() {
		return checkin;
	}
	public void setCheckin(Time checkin) {
		this.checkin = checkin;
	}
	public Time getCheckout() {
		return checkout;
	}
	public void setCheckout(Time checkout) {
		this.checkout = checkout;
	}
	public Time getTotal_hours() {
		return total_hours;
	}
	public void setTotal_hours(Time total_hours) {
		this.total_hours = total_hours;
	}
	
	
	

}
