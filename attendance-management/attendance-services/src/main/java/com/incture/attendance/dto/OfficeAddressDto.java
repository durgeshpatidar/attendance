package com.incture.attendance.dto;

import java.util.Date;


public class OfficeAddressDto extends BaseDto {

	private String id;
	private String address;
	private String city;
	private String state;
	private String pincode;
	private String status;
	private Date validTo;
	private Date validFrom;
	private Double locationLat;
	private Double locationLon;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public OfficeAddressDto() {
		super();
	}
	public OfficeAddressDto(String id, String address, String city, String state, String pincode, String status,
			Date validTo, Date validFrom, Double locationLat, Double locationLon) {
		super();
		this.id = id;
		this.address = address;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.status = status;
		this.validTo = validTo;
		this.validFrom = validFrom;
		this.locationLat = locationLat;
		this.locationLon = locationLon;
	}
	
}
