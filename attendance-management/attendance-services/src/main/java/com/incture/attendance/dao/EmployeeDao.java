package com.incture.attendance.dao;

import java.util.List;

import com.incture.attendance.dto.EmployeeDto;
import com.incture.attendance.dto.ProfileDto;
import com.incture.attendance.dto.ManagerDetailsDto;

public interface EmployeeDao {

	boolean saveEmployeeData(EmployeeDto employeeDto);

	boolean verifyIdPass(EmployeeDto employeeDto);

	boolean isValidUser(EmployeeDto employeeDto);

	ProfileDto profileDetails(EmployeeDto employeeDto);

	List<ManagerDetailsDto> managerDetails(EmployeeDto employeeDto);

	boolean forgotPassword(EmployeeDto employeeDto);
}
