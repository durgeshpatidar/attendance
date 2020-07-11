package com.incture.attendance.dao;

import java.util.List;

import com.incture.attendance.dto.AddressDto;
import com.incture.attendance.utils.ResponseDto;


public interface AddressDao {
	void addAddress(AddressDto addressdto);
	List<AddressDto> getAddressDetails(String empId);
	String validateAddress(AddressDto addressDto);
}
