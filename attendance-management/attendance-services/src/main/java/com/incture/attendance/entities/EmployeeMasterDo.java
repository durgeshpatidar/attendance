//Employee Master Table
package com.incture.attendance.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEE_MASTER")
public class EmployeeMasterDo {
	
	@Id
	@Column(name = "ID", columnDefinition = "NVARCHAR(36)")
	private String id;
	
	@Column(name = "FIRST_NAME", columnDefinition = "NVARCHAR(100)")
	private String firstName;
	
	@Column(name = "LAST_NAME", columnDefinition = "NVARCHAR(100)")
	private String lastName;
	
	@Column(name = "GENDER", columnDefinition = "NVARCHAR(10)")
	private String gender;
	
	@Column(name = "BIRTH_DATE", columnDefinition = "DATE")
	private Date birthDate;
	
	@Column(name = "BLOOD_GROUP", columnDefinition = "NVARCHAR(5)")
	private String bloodGroup;
	
	@Column(name = "JOINING_DATE", columnDefinition = "DATE")
	private Date joiningDate;
	
	@Column(name = "RESIGNING_DATE", columnDefinition = "DATE")
	private Date resigningDate;
	
	@Column(name = "EMAIL_ID", columnDefinition = "NVARCHAR(100)")
	private String emailId;
	
	@Column(name = "PHONE_NO", columnDefinition = "NVARCHAR(20)")
	private String phoneNo;
	
	@Column(name = "PROFILE_IMG", columnDefinition = "NVARCHAR(200)")
	private String profileImg;
	
	@Column(name = "STATUS", columnDefinition = "NVARCHAR(20)")
	private String status;
	
	//Constructor
	public EmployeeMasterDo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeMasterDo(String id, String firstName, String lastName, String gender, Date birthDate,
			String bloodGroup, Date joiningDate,Date resigningDate, String emailId, String phoneNo,
			String profileImg, String status) {
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
	
	//Getters and Setters
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
