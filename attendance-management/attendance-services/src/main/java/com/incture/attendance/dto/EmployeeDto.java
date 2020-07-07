package com.incture.attendance.dto;


public class EmployeeDto extends BaseDto {
	private String id;
	private String phone_no;
	private String password;
	private String email;
	//Face data needs to be added
	
	//Constructor
	public EmployeeDto() {
		super();
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public EmployeeDto(String id, String phone_no, String password, String email) {
		super();
		this.id = id;
		this.phone_no = phone_no;
		this.password = password;
		this.email = email;
	}

	@Override
	public String toString() {
		return "EmployeeDto [id=" + id + ", phone_no=" + phone_no + ", password=" + password + ", email=" + email + "]";
	}	
	
	
}
