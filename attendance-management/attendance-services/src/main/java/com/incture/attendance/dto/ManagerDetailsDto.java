package com.incture.attendance.dto;

public class ManagerDetailsDto {
	
	private String firstName;
	private String lastName;
	private String managerType;
	
	//Constructor
	public ManagerDetailsDto() {
		super();
	}
	public ManagerDetailsDto(String firstName, String lastName, String managerType) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.managerType = managerType;
	}
	
	//Getters and Setters
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getManagerType() {
		return managerType;
	}
	public void setManagerType(String managerType) {
		this.managerType = managerType;
	}
	
	
	
	

}
