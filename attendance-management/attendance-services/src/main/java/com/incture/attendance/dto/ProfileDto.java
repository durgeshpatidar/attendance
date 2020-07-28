//Dto for profile information
package com.incture.attendance.dto;

import java.util.Date;
import java.util.List;

public class ProfileDto {
	private String id;
	private String firstName;
	private String LastName;
	private String gender;
	private Date birthDate;
	private String bloodGroup;
	private String emailId;
	private String phoneNo;
	private String profileImg;
	private String designation;
	private List<ManagerDetailsDto> manager;

	// Constructor

	public ProfileDto() {
		super();

	}

	public ProfileDto(String id, String firstName, String lastName, String gender, Date birthDate, String bloodGroup,
			String emailId, String phoneNo, String profileImg, String designation, List<ManagerDetailsDto> manager) {
		super();
		this.id = id;
		this.firstName = firstName;
		LastName = lastName;
		this.gender = gender;
		this.birthDate = birthDate;
		this.bloodGroup = bloodGroup;
		this.emailId = emailId;
		this.phoneNo = phoneNo;
		this.profileImg = profileImg;
		this.designation = designation;
		this.manager = manager;
	}

	// Getters and Setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public List<ManagerDetailsDto> getManager() {
		return manager;
	}

	public void setManager(List<ManagerDetailsDto> manager) {
		this.manager = manager;
	}

}
