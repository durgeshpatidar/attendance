//Dto for returning list of employees to managers and admin.
package com.incture.attendance.dto;

public class EmployeeListDto {
	private String id;
	private String name;

	// Constructor
	public EmployeeListDto() {
		super();

	}

	public EmployeeListDto(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	// Getters and Setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
