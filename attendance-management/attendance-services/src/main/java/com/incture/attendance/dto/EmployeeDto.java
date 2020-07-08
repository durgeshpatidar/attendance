package com.incture.attendance.dto;


public class EmployeeDto extends BaseDto {
	private String id;
	private String phoneNo;
	private String password;
	private String email;
	//Face data needs to be added
	
	//Constructor
	public EmployeeDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmployeeDto(String id, String phoneNo, String password, String email) {
		super();
		this.id = id;
		this.phoneNo = phoneNo;
		this.password = password;
		this.email = email;
	}
	//Gettters and Setters
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
	
	
}
