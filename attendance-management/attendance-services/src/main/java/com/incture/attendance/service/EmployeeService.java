package com.incture.attendance.service;

import com.incture.attendance.dto.EmployeeDto;
import com.incture.attendance.utils.ResponseDto;

public interface EmployeeService {

	ResponseDto verifyIdPass(EmployeeDto employeeDto);

	ResponseDto isValidUser(EmployeeDto employeeDto);

	ResponseDto saveEmployeeData(EmployeeDto employeeDto);

	ResponseDto profileDetails(EmployeeDto employeeDto);

	ResponseDto managerDetails(EmployeeDto employeeDto);

	ResponseDto forgotPassword(EmployeeDto employeeDto);

	ResponseDto updatePassword(EmployeeDto employeeDto);
}
