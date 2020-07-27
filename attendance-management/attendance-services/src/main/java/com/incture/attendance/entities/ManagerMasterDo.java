//Manager master entity.
package com.incture.attendance.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MANAGER_MASTER")
public class ManagerMasterDo {
	@Id
	@Column(name = "ID", columnDefinition = "NVARCHAR(36)")
	private String id;

	@Column(name = "EMPLOYEE_ID", columnDefinition = "NVARCHAR(36)")
	private String employeeId;

	@Column(name = "MANAGER_ID", columnDefinition = "NVARCHAR(36)")
	private String managerId;

	@Column(name = "START_DATE", columnDefinition = "DATE")
	private Date startDate;

	@Column(name = "END_DATE", columnDefinition = "DATE")
	private Date endDate;

	@Column(name = "MANAGER_TYPE", columnDefinition = "NVARCHAR(20)")
	private String managerType;

	@Column(name = "STATUS", columnDefinition = "VARCHAR(20)")
	private String status;

	// Constructor
	public ManagerMasterDo() {
		super();
	}

	public ManagerMasterDo(String id, String employeeId, String managerId, Date startDate, Date endDate,
			String managerType, String status) {
		super();
		this.id = id;
		this.employeeId = employeeId;
		this.managerId = managerId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.managerType = managerType;
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

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getManagerType() {
		return managerType;
	}

	public void setManagerType(String managerType) {
		this.managerType = managerType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
