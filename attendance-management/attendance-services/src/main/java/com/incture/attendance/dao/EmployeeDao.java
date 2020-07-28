package com.incture.attendance.dao;

import java.util.List;

import com.incture.attendance.dto.EmployeeDto;
import com.incture.attendance.dto.EmployeeListDto;
import com.incture.attendance.dto.ProfileDto;
import com.incture.attendance.dto.ManagerDetailsDto;

public interface EmployeeDao {

	boolean saveEmployeeData(EmployeeDto employeeDto);

	boolean verifyIdPass(EmployeeDto employeeDto);

	boolean isValidUser(EmployeeDto employeeDto);

	ProfileDto profileDetails(EmployeeDto employeeDto);

	List<ManagerDetailsDto> managerDetails(EmployeeDto employeeDto);

	void updatePassword(EmployeeDto employeeDto);

	boolean verifyEmail(EmployeeDto employeeDto);

	boolean verifyEmployeeType(String empId);

	List<EmployeeListDto> getEmployeeList(String empId);

	Object getAllEmployeeList(String empId);

	boolean verifyAdminId(String empId);
}
