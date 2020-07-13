package com.incture.attendance.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "EMPLOYEE")
@Data
public class EmployeeDo implements BaseDo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ID", columnDefinition = "NVARCHAR(36)")
	private String id;

	@Column(name = "PHONE_NO", columnDefinition = "NVARCHAR(20)")
	private String phoneNo;

	@Column(name = "PASSWORD", columnDefinition = "NVARCHAR(200)")
	private String password;

	@Column(name = "EMAIL", columnDefinition = "NVARCHAR(100)")
	private String email;
	// Column for face data

	@OneToMany(mappedBy = "employee")
	private List<AddressDo> address = new ArrayList<AddressDo>();

	@OneToMany(mappedBy = "employee")
	private List<TrackingDo> empTrackings = new ArrayList<TrackingDo>();

	@OneToMany(mappedBy = "employee")
	private List<WorkflowTaskDo> workflowTrackings = new ArrayList<WorkflowTaskDo>();

	public EmployeeDo() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<AddressDo> getAddress() {
		return address;
	}

	public void setAddress(List<AddressDo> address) {
		this.address = address;
	}

	public List<TrackingDo> getEmpTrackings() {
		return empTrackings;
	}

	public void setEmpTrackings(List<TrackingDo> empTrackings) {
		this.empTrackings = empTrackings;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public EmployeeDo(String id, String phoneNo, String password, String email, List<AddressDo> address,
			List<TrackingDo> empTrackings) {
		super();
		this.id = id;
		this.phoneNo = phoneNo;
		this.password = password;
		this.email = email;
		this.address = address;
		this.empTrackings = empTrackings;
	}

}
