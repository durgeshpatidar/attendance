package com.incture.attendance.dao;

import java.util.List;

import com.incture.attendance.dto.AddressDto;

public interface AddressDao {
	void addAddress(AddressDto addressdto);

	List<AddressDto> getAddressDetails(String empId);

	String validateAddress(AddressDto addressDto);
	
	void updateAddressStatus(String workflowId,String status,String comment);
}
