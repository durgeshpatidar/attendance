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
	private EmployeeDo employee;
	
	@ManyToOne
	@JoinColumn(name="ADDRESS_ID")
	private AddressDo address;
	
	@Column(name = "DATE", columnDefinition = "DATE")
	private Date date;
	
	@Column(name = "CHECKIN", columnDefinition = "TIMESTAMP")
	private Date checkIn;
	
	@Column(name = "CHECKOUT", columnDefinition = "TIMESTAMP")
	private Date checkOut;
	
	@Column(name = "TOTAL_HOURS", columnDefinition = "TIME")
	private double totalHours;
	
	//Constructor
	public TrackingDo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public EmployeeDo getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeDo employee) {
		this.employee = employee;
	}

	public AddressDo getAddress() {
		return address;
	}

	public void setAddress(AddressDo address) {
		this.address = address;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}

	public double getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(double totalHours) {
		this.totalHours = totalHours;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public TrackingDo(String id, EmployeeDo employee, AddressDo address, Date date, Date checkIn, Date checkOut,
			double totalHours) {
		super();
		this.id = id;
		this.employee = employee;
		this.address = address;
		this.date = date;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.totalHours = totalHours;
	}

}