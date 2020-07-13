package com.incture.attendance.service;

import java.util.List;

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
		logger.info("AddressServiceImpl | addAddress | Execution start input " + addressDto);

		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatus(Boolean.TRUE);
		responseDto.setStatusCode(200);
		try {
			addressDao.addAddress(addressDto);
			responseDto.setMessage("Address Send For Approval");

		} catch (Exception e) {

			logger.error("AddressServiceImpl | addAddress | Exception " + e.getMessage());
			responseDto.setStatus(Boolean.FALSE);
			responseDto.setStatusCode(500);
			responseDto.setMessage(e.getMessage());

		}

		logger.info("AddressServiceImpl | addAddress  | Execution end ouput " + responseDto);

		return responseDto;

	}

	public ResponseDto getAddressDetails(String empId) {
		logger.info("AddressServiceImpl | getAddressDetails | Execution start input " + empId);

		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatus(Boolean.TRUE);
		responseDto.setStatusCode(200);
		try {
			List<AddressDto> address = addressDao.getAddressDetails(empId);
			responseDto.setData(address);
			if (address.isEmpty())
				responseDto.setMessage("Address Request Not Found!");
			else
				responseDto.setMessage("All Address Request Displayed Successfully!");

		} catch (Exception e) {

			logger.error("AddressServiceImpl | getAddressDetails | Exception " + e.getMessage());
			responseDto.setStatus(Boolean.FALSE);
			responseDto.setStatusCode(500);
			responseDto.setMessage(e.getMessage());

		}

		logger.info("AddressServiceImpl | getAddressDetails | Execution end ouput " + responseDto);

		return responseDto;

	}

	@Override
	public ResponseDto validateAddress(AddressDto addressDto) {
		logger.info("AddressServiceImpl | validateAddress | Execution start input " + addressDto);

		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatus(Boolean.TRUE);
		responseDto.setStatusCode(200);
		try {
			String address = addressDao.validateAddress(addressDto);
			if (address != null) {
				responseDto.setData(address);
				responseDto.setMessage("Address Verified!");
			} else {
				responseDto.setMessage("Address Invalid!");
			}

		} catch (Exception e) {

			logger.error("AddressServiceImpl | validateAddress | Exception " + e.getMessage());
			responseDto.setStatus(Boolean.FALSE);
			responseDto.setStatusCode(500);
			responseDto.setMessage(e.getMessage());

		}

		logger.info("AddressServiceImpl | validateAddress | Execution end ouput " + responseDto);

		return responseDto;

	}

}
