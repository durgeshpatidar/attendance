package com.incture.attendance.entities;

import java.sql.Time;
import java.sql.Timestamp;
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
@Table(name = "TRACKING")
@Data
public class TrackingDo implements BaseDo
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "ID")
	private String id;
	
	@ManyToOne
	@JoinColumn(name="EMPLOYEE_ID")
	private EmployeeDo empTracking;
	
	@ManyToOne
	@JoinColumn(name="ADDRESS_ID")
	private AddressDo addressTracking;
	
	@Column(name = "DATE", columnDefinition = "DATE")
	private Date date;
	
	@Column(name = "CHECKIN", columnDefinition = "TIMESTAMP")
	private Timestamp checkIn;
	
	@Column(name = "CHECKOUT", columnDefinition = "TIMESTAMP")
	private Timestamp checkOut;
	
	@Column(name = "TOTAL_HOURS", columnDefinition = "TIME")
	private Time totalHours;
	
	//Constructor
	public TrackingDo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TrackingDo(String id, EmployeeDo empTracking, AddressDo addressTracking, Date date, Timestamp checkIn,
			Timestamp checkOut, Time totalHours) {
		super();
		this.id = id;
		this.empTracking = empTracking;
		this.addressTracking = addressTracking;
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

	public EmployeeDo getEmpTracking() {
		return empTracking;
	}

	public void setEmpTracking(EmployeeDo empTracking) {
		this.empTracking = empTracking;
	}

	public AddressDo getAddressTracking() {
		return addressTracking;
	}

	public void setAddressTracking(AddressDo addressTracking) {
		this.addressTracking = addressTracking;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}