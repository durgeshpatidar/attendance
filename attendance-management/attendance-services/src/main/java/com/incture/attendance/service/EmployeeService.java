package com.incture.attendance.service;

import com.incture.attendance.dto.EmployeeDto;
import com.incture.attendance.utils.ResponseDto;

public interface EmployeeService {

	ResponseDto verifyIdPass(EmployeeDto employeeDto);

	ResponseDto isValidUser(EmployeeDto employeeDto);

	ResponseDto saveEmployeeData(EmployeeDto employeeDto);

	ResponseDto profileDetails(EmployeeDto employeeDto);

	//ResponseDto managerDetails(EmployeeDto employeeDto);

	ResponseDto updatePassword(EmployeeDto employeeDto);

	ResponseDto verifyEmployeeType(String empId);

	ResponseDto getEmployeeList(String empId);

	ResponseDto verifyAdminId(String empId);

	ResponseDto getAllEmployeeList(String empId);
}
