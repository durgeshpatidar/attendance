package com.incture.attendance.dao;

import java.util.List;

import com.incture.attendance.dto.AddressDto;
import com.incture.attendance.dto.EmployeeDto;

public interface AddressDao {
	void addAddress(AddressDto addressdto);

	void addMasterAddress(EmployeeDto employeeDto);

	List<AddressDto> getAddressDetails(String empId);

	String validateAddress(AddressDto addressDto);

}
