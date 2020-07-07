package com.incture.attendance.service;

import com.incture.attendance.dto.EmployeeDto;
import com.incture.attendance.utils.ResponseDto;

public interface EmployeeService  {

	ResponseDto verifyEmployeeData(EmployeeDto employeeDto);
}
