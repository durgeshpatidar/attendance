package com.incture.attendance.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


import lombok.Data;

@Entity
@Table(name = "ADDRESS")
@Data
public class AddressDo implements BaseDo {

	/**
	 * author
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "ID", columnDefinition = "NVARCHAR(36)")
	private String id;
	
	@ManyToOne
	@JoinColumn(name="EMPLOYEE_ID") 
	private EmployeeDo emp_id;
	
	@Column(name = "ADDRESS", columnDefinition = "VARCHAR(100)")
	private String address;
	
	@Column(name = "CITY", columnDefinition = "VARCHAR(100)")
	private String city;
	
	@Column(name = "STATE", columnDefinition = "VARCHAR(100)")
	private String state;
	
	@Column(name = "PIN_CODE", columnDefinition = "NVARCHAR(10)")
	private String pincode;
	
	@Column(name = "VALID_TO", columnDefinition = "DATE")
	private Date validTo;
	
	@Column(name = "VALID_FROM", columnDefinition = "DATE")
	private Date validFrom;
	
	@Column(name = "LOCATION_LAT", columnDefinition = "DOUBLE")
	private Double locationLat;
	
	@Column(name = "LOCATION_LON", columnDefinition = "DOUBLE")
	private Double locationLon;
	
	@OneToMany(mappedBy="address_tracking")
	private List<TrackingDo> add_trackings=new ArrayList<TrackingDo>();
	
	//Constructor
	public AddressDo() {
		super();
		
	}

	public AddressDo(String id, EmployeeDo emp_id, String address, String city, String state, String pincode,
			Date validTo, Date validFrom, Double locationLat, Double locationLon) {
		super();
		this.id = id;
		this.emp_id = emp_id;
		this.address = address;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.validTo = validTo;
		this.validFrom = validFrom;
		this.locationLat = locationLat;
		this.locationLon = locationLon;
	}
	
	//Getters and Setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public EmployeeDo getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(EmployeeDo emp_id) {
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
