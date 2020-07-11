package com.incture.attendance.service;


import com.incture.attendance.dto.AddressDto;
import com.incture.attendance.utils.ResponseDto;

public interface AddressService {
	ResponseDto addAddress(AddressDto addressDto);
	ResponseDto getAddressDetails(String empId);
}
