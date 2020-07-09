package com.incture.attendance.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.incture.attendance.dao.AddressDao;
import com.incture.attendance.dto.AddressDto;
import com.incture.attendance.utils.ResponseDto;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AddressDao addressDao;
	@Override
	public ResponseDto addAddress(AddressDto addressDto) {
		// TODO Auto-generated method stub
		return null;
	}

}
