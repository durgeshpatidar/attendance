package com.incture.attendance.entities;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "TRACKING")
class TrackingDo implements BaseDo
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
	private EmployeeDo emp_tracking;
	
	@ManyToOne
	@JoinColumn(name="ADDRESS_ID")
	private AddressDo address_tracking;
	
	@Column(name = "DATE", columnDefinition = "DATE")
	private Date date;
	
	@Column(name = "CHECKIN", columnDefinition = "TIMESTAMP")
	private Time checkin;
	
	@Column(name = "CHECKOUT", columnDefinition = "TIMESTAMP")
	private Time checkout;
	
	@Column(name = "TOTAL_HOURS", columnDefinition = "TIME")
	private Time total_hours;
	
	//Constructor
	public TrackingDo() {
		super();
		
	}

	public TrackingDo(String id, EmployeeDo emp_tracking, AddressDo address_tracking, Date date, Time checkin,
			Time checkout, Time total_hours) {
		super();
		this.id = id;
		this.emp_tracking = emp_tracking;
		this.address_tracking = address_tracking;
		this.date = date;
		this.checkin = checkin;
		this.checkout = checkout;
		this.total_hours = total_hours;
	}
	
	//Getters and Setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public EmployeeDo getEmp_tracking() {
		return emp_tracking;
	}

	public void setEmp_tracking(EmployeeDo emp_tracking) {
		this.emp_tracking = emp_tracking;
	}

	public AddressDo getAddress_tracking() {
		return address_tracking;
	}

	public void setAddress_tracking(AddressDo address_tracking) {
		this.address_tracking = address_tracking;
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
	
	
}