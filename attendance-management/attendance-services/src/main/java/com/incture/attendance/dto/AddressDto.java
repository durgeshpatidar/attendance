package com.incture.attendance.dto;

import java.util.Date;

public class AddressDto {
	private String id;
	private int emp_id;
	private String address;
	private String city;
	private String state;
	private String pincode;
	private Date validTo;
	private Date validFrom;
	
	//constructor
	public AddressDto() {
		super();
		
	}
	
	public AddressDto(String id, int emp_id, String address, String city, String state, String pincode, Date validTo,
			Date validFrom) {
		super();
		this.id = id;
		this.emp_id = emp_id;
		this.address = address;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.validTo = validTo;
		this.validFrom = validFrom;
	}
	
	//Getters and Setters
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public Date getValidTo() {
		return validTo;
	}
	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}
	public Date getValidFrom() {
		return validFrom;
	}
	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}
	
	

}
