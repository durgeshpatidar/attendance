package com.incture.attendance.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

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
	private String phone_no;
	
	@Column(name = "PASSWORD", columnDefinition = "NVARCHAR(200)")
	private String password;
	
	@Column(name = "STATUS", columnDefinition = "NVARCHAR(20)")
	private String status;

	@OneToMany( mappedBy="employee")
	private ManagerDo manager;
	
	@OneToMany( mappedBy="employee1")
	private ManagerDo manager1;
	
	/*employee*/
	
}
