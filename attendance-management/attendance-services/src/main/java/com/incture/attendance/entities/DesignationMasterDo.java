//Designation master entity.
package com.incture.attendance.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DESIGNATION_MASTER")
public class DesignationMasterDo {
	@Id
	@Column(name = "ID", columnDefinition = "NVARCHAR(36)")
	private String id;

	@Column(name = "EMPLOYEE_ID", columnDefinition = "NVARCHAR(36)")
	private String employeeId;

	@Column(name = "START_DATE", columnDefinition = "DATE")
	private String startDate;

	@Column(name = "END_DATE", columnDefinition = "DATE")
	private String endDate;

	@Column(name = "COMPETENCY", columnDefinition = "NVARCHAR(50)")
	private String competency;

	@Column(name = "DESIGNATION", columnDefinition = "NVARCHAR(50)")
	private String designation;

	@Column(name = "STATUS", columnDefinition = "NVARCHAR(20)")
	private String status;

	// Constructor
	public DesignationMasterDo() {
		super();
	}

	public DesignationMasterDo(String id, String employeeId, String startDate, String endDate, String competency,
			String designation, String status) {
		super();
		this.id = id;
		this.employeeId = employeeId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.competency = competency;
		this.designation = designation;
		this.status = status;
	}

	// Getters and setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getCompetency() {
		return competency;
	}

	public void setCompetency(String competency) {
		this.competency = competency;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
