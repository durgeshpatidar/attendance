package com.incture.attendance.dto;


public class EmployeeDto extends BaseDto {
	private String id;
	private String phone_no;
	private String password;
	//Face data needs to be added
	
	//Constructor
	public EmployeeDto() {
		super();
	
	}
	public EmployeeDto(String id, String phone_no, String password) {
		super();
		this.id = id;
		this.phone_no = phone_no;
		this.password = password;
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
