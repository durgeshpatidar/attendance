//Workflow transaction entity.
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
public class WorkflowTaskDo implements BaseDo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "WORKFLOW_ID")
	private String workflowId;

	@Column(name = "ID", columnDefinition = "NVARCHAR(40)")
	private String id;

	@ManyToOne
	@JoinColumn(name = "EMPLOYEE_ID")
	private EmployeeDo employee;

	@Column(name = "MANAGER_ID", columnDefinition = "NVARCHAR(20)")
	private String managerId;

	@Column(name = "REQUEST_DATE", columnDefinition = "DATE")
	private Date requestdate;

	@Column(name = "DESCRIPTION", columnDefinition = "NVARCHAR(200)")
	private String description;

	@Column(name = "STATUS", columnDefinition = "NVARCHAR(20)")
	private String status;

	@Column(name = "COMMENT", columnDefinition = "NVARCHAR(200)")
	private String comment;

	@Column(name = "QUERYTYPE", columnDefinition = "NVARCHAR(30)")
	private String querytype;

	// Constructor.
	public WorkflowTaskDo() {
		super();
	}

	public WorkflowTaskDo(String workflowId, String id, EmployeeDo employee, String managerId, Date requestdate,
			String description, String status, String comment, String querytype) {
		super();
		this.workflowId = workflowId;
		this.id = id;
		this.employee = employee;
		this.managerId = managerId;
		this.requestdate = requestdate;
		this.description = description;
		this.status = status;
		this.comment = comment;
		this.querytype = querytype;
	}

	// Getters and Setters.
	public String getWorkflowId() {
		return workflowId;
	}

	public void setWorkflowId(String workflowId) {
		this.workflowId = workflowId;
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

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
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

	public String getQuerytype() {
		return querytype;
	}

	public void setQuerytype(String querytype) {
		this.querytype = querytype;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}