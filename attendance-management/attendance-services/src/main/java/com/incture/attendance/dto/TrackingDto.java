package com.incture.attendance.dto;

import java.sql.Time;
import java.util.Date;


public class TrackingDto {
	
private String id;
	
	private String empId;
	private String addressId;
	private Date date;
	private Time checkin;
	private Time checkout;
	private Time total_hours;
	
	//Constructors
	public TrackingDto() {
		super();

	}

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

	@Override
	public String toString() {
		return "TrackingDto [id=" + id + ", empId=" + empId + ", addressId=" + addressId + ", date=" + date
				+ ", checkin=" + checkin + ", checkout=" + checkout + ", total_hours=" + total_hours + "]";
	}

	public TrackingDto(String id, String empId, String addressId, Date date, Time checkin, Time checkout,
			Time total_hours) {
		super();
		this.id = id;
		this.empId = empId;
		this.addressId = addressId;
		this.date = date;
		this.checkin = checkin;
		this.checkout = checkout;
		this.total_hours = total_hours;
	}
		

}
