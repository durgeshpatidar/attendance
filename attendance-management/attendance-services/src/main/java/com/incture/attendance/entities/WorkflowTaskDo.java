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
@Table(name = "WorkflowTask")
@Data
public class WorkflowTaskDo implements BaseDo
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
	@JoinColumn(name="MANAGER_ID")
	private AddressDo address_tracking;
	
	@Column(name = "REQUEST_DATE", columnDefinition = "DATE")
	private Date requestdate;
	
	@Column(name = "DESCRIPTION", columnDefinition = "NVARCHAR(200)")
	private String description;
	
	@Column(name = "STATUS", columnDefinition = "VARCHAR(20)")
	private String status;
	
	@Column(name = "COMMENT", columnDefinition = "NVARCHAR(200)")
	private String comment;
	
	//Constructor
	//
	public WorkflowTaskDo() {
		super();
		
	}

	public WorkflowTaskDo(String id, EmployeeDo emp_tracking, AddressDo address_tracking, Date requestdate, String descrption,
			String status, String comment, String description) {
		super();
		this.id = id;
		this.emp_tracking = emp_tracking;
		this.address_tracking = address_tracking;
		this.requestdate = requestdate;
		this.description = description;
		this.status = status;
		this.comment = comment;
	
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

	public Date getRequestdate() {
		return requestdate;
	}

	public void setRequestdate(Date requestdate) {
		this.requestdate = requestdate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	
	
}