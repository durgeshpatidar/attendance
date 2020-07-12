//Address Master Class
package com.incture.attendance.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Table(name = "ADDRESS_MASTER")
@Data
public class AddressMasterDo {
private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "ID", columnDefinition = "NVARCHAR(36)")
	private String id;

	@Column(name = "EMPLOYEE_ID", columnDefinition = "NVARCHAR(36)")
	private String empId;
	
	@Column(name = "ADDRESS", columnDefinition = "VARCHAR(100)")
	private String address;
	
	@Column(name = "CITY", columnDefinition = "VARCHAR(100)")
	private String city;
	
	@Column(name = "STATE", columnDefinition = "VARCHAR(100)")
	private String state;
	
	@Column(name = "PIN_CODE", columnDefinition = "NVARCHAR(10)")
	private String pincode;
	
	@Column(name = "LOCATION_LAT", columnDefinition = "DOUBLE")
	private Double locationLat;
	
	@Column(name = "LOCATION_LON", columnDefinition = "DOUBLE")
	private Double locationLon;
	
	//Constructor
	public AddressMasterDo() {
		super();
	}

	public AddressMasterDo(String id, String empId,String address, String city, String state, String pincode, Double locationLat,
			Double locationLon) {
		super();
		this.empId=empId;
		this.id = id;
		this.address = address;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.locationLat = locationLat;
		this.locationLon = locationLon;
	}
	
	//Getters and Setters
	public String getId() {
		return id;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
