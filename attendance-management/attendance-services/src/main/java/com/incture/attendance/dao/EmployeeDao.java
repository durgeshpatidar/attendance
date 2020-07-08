package com.incture.attendance.dao;

import com.incture.attendance.dto.EmployeeDto;
import com.incture.attendance.dto.ProfileDto;
import com.incture.attendance.dto.ManagerDetailsDto;

public interface EmployeeDao {

	void saveEmployeeData(EmployeeDto employeeDto);

	boolean verifyIdPass(EmployeeDto employeeDto);

	boolean isValidUser(EmployeeDto employeeDto);
	
	ProfileDto profileDetails(EmployeeDto employeeDto);
	
	ManagerDetailsDto managerDetails(EmployeeDto employeeDto);
}
