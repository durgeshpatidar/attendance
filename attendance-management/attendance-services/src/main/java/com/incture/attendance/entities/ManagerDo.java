package com.incture.attendance.entities;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;



@Entity
@Table(name = "MANAGER")
public class ManagerDo {
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
	@JoinColumn(name="MANAGER_ID")
	private EmployeeDo employee1;
	
	@Column
	@CreationTimestamp
	private Date start_date;
	
	
	@Column
	@UpdateTimestamp
	private Date end_date;
	
	//haha
	
	/*managerdo*/
	
}
