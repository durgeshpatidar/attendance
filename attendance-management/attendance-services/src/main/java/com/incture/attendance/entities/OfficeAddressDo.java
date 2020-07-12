package com.incture.attendance.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Table(name = "OFFICE_ADDRESS")
@Data
public class OfficeAddressDo implements BaseDo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "ID", columnDefinition = "NVARCHAR(36)")
	private String id;

	@Column(name = "ADDRESS", columnDefinition = "VARCHAR(100)")
	private String address;

	@Column(name = "CITY", columnDefinition = "VARCHAR(100)")
	private String city;

	@Column(name = "STATE", columnDefinition = "VARCHAR(100)")
	private String state;

	@Column(name = "PIN_CODE", columnDefinition = "NVARCHAR(10)")
	private String pincode;

	@Column(name = "STATUS", columnDefinition = "NVARCHAR(20)")
	private String status;

	@Column(name = "VALID_TO", columnDefinition = "DATE")
	private Date validTo;

	@Column(name = "VALID_FROM", columnDefinition = "DATE")
	private Date validFrom;

	@Column(name = "LOCATION_LAT", columnDefinition = "DOUBLE")
	private Double locationLat;

	@Column(name = "LOCATION_LON", columnDefinition = "DOUBLE")
	private Double locationLon;

	
	public OfficeAddressDo() {
		super();
	}
	

	public OfficeAddressDo(String id, String address, String city, String state, String pincode, String status,
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
