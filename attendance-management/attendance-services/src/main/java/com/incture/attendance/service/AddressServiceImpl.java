package com.incture.attendance.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.incture.attendance.dto.AddressDto;
import com.incture.attendance.utils.ResponseDto;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

	@Override
	public ResponseDto addAddress(AddressDto addressDto) {
		// TODO Auto-generated method stub
		return null;
	}

}
