package com.incture.attendance.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.incture.attendance.dao.EmployeeDao;
import com.incture.attendance.dto.EmployeeDto;
import com.incture.attendance.utils.ResponseDto;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public ResponseDto saveEmployeeData(EmployeeDto employeeDto) {
		logger.info("EmployeeServiceImpl | saveEmployeeData | Execution start input " + employeeDto);

		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatus(Boolean.TRUE);
		responseDto.setStatusCode(200);

		try {
			employeeDao.saveEmployeeData(employeeDto);

			responseDto.setMessage("Employee Details Saved Successfully");

		} catch (Exception e) {

			logger.error("EmployeeServiceImpl | saveEmployeeData | Exception " + e.getMessage());
			responseDto.setStatus(Boolean.FALSE);
			responseDto.setStatusCode(500);
			responseDto.setMessage(e.getMessage());

		}

		logger.info("EmployeeServiceImpl | saveEmployeeData | Execution end ouput " + responseDto);

		return responseDto;
	}
	
	@Override
	public ResponseDto verifyEmployeeData(EmployeeDto employeeDto) 
	{
		logger.info("EmployeeServiceImpl | saveEmployeeData | Execution start input " + employeeDto);

		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatus(Boolean.TRUE);
		responseDto.setStatusCode(200);

		try {
			employeeDao.verifyEmployeeData(employeeDto);

			responseDto.setMessage("Employee Details verified!");

		} catch (Exception e) {

			logger.error("EmployeeServiceImpl | saveEmployeeData | Exception " + e.getMessage());
			responseDto.setStatus(Boolean.FALSE);
			responseDto.setStatusCode(500);
			responseDto.setMessage(e.getMessage());

		}

		logger.info("EmployeeServiceImpl | saveEmployeeData | Execution end ouput " + responseDto);

		return responseDto;
		
	}

}
