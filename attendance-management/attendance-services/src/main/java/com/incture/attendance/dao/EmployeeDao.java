package com.incture.attendance.dao;

import com.incture.attendance.dto.EmployeeDto;

public interface EmployeeDao {

	
	void saveEmployeeData(EmployeeDto employeeDto);

	void verifyEmployeeData(EmployeeDto employeeDto);
}
