//Employee Master Dto
package com.incture.attendance.dto;

import java.util.Date;

public class EmployeeMasterDto {
	private String id;
	private String firstName;
	private String lastName;
	private String gender;
	private Date birthDate;
	private String bloodGroup;
	private Date joiningDate;
	private Date resigningDate;
	private String emailId;
	private String phoneNo;
	private String profileImg;
	private String status;

	// Constructor
	public EmployeeMasterDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeMasterDto(String id, String firstName, String lastName, String gender, Date birthDate,
			String bloodGroup, Date joiningDate, Date resigningDate, String emailId, String phoneNo, String profileImg,
			String status) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.birthDate = birthDate;
		this.bloodGroup = bloodGroup;
		this.joiningDate = joiningDate;
		this.resigningDate = resigningDate;
		this.emailId = emailId;
		this.phoneNo = phoneNo;
		this.profileImg = profileImg;
		this.status = status;
	}

	// Getter and Setters
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
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	public Date getResigningDate() {
		return resigningDate;
	}

	public void setResigningDate(Date resigningDate) {
		this.resigningDate = resigningDate;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	//
}
