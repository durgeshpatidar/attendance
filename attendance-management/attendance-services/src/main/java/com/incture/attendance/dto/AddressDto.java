//Dto for address details.
package com.incture.attendance.dto;

import java.util.Date;

public class AddressDto extends BaseDto {

	private String id;
	private String empId;
	private String address;
	private String city;
	private String state;
	private String status;
	private String pincode;
	private Date validTo;
	private Date validFrom;
	private Double locationLat;
	private Double locationLon;

	// Constructor
	public AddressDto() {
		super();
	}

	public AddressDto(String id, String status, String empId, String address, String city, String state, String pincode,
			Date validTo, Date validFrom, Double locationLat, Double locationLon) {
		super();
		this.id = id;
		this.status = status;
		this.empId = empId;
		this.address = address;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.validTo = validTo;
		this.validFrom = validFrom;
		this.locationLat = locationLat;
		this.locationLon = locationLon;
	}
	// Getters and Setters

	public String getEmpId() {
		return empId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
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

	public Double getLocationLat() {
		return locationLat;
	}

	public void setLocationLat(Double locationLat) {
		this.locationLat = locationLat;
	}

	public Double getLocationLon() {
		return locationLon;
	}

	public void setLocationLon(Double locationLon) {
		this.locationLon = locationLon;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
