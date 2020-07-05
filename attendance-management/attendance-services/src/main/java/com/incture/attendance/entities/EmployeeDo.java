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
	
	//Column for face data
	
	//Constructors
	public EmployeeDo(String id, String phone_no, String password) {
		super();
		this.id = id;
		this.phone_no = phone_no;
		this.password = password;
	}

	public EmployeeDo() {
		super();
		
	}
	
	//Getters and Setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhone_no() {
		return phone_no;
	}

	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
