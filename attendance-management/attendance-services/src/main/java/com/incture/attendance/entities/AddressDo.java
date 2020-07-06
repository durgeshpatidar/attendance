package com.incture.attendance.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	@JoinColumn(name="	EMPLOYEE_ID") 
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
	
	//Column for LOCATION_LAT and LOCATION_LON needs to be added
}
