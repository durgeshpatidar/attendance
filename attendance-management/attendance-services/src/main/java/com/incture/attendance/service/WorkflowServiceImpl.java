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
public class WorkflowServiceImpl implements WorkflowService{

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EmployeeDao employeeDao;

	// for checking employee id and password
	@Override
	public ResponseDto verifyIdPass(EmployeeDto employeeDto) {
		logger.info("EmployeeServiceImpl | verifyIdPass | Execution start input " + employeeDto);

		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatus(Boolean.TRUE);
		responseDto.setStatusCode(200);

		try {
			boolean status = employeeDao.verifyIdPass(employeeDto);
			if (status == true) {
				responseDto.setMessage("Id And Password Is Correct!");
			} else {
				responseDto.setStatus(Boolean.FALSE);
				responseDto.setStatusCode(500);
				responseDto.setMessage("Invalid credentials!");
			}

		} catch (Exception e) {

			logger.error("EmployeeServiceImpl | verifyIdPass | Exception " + e.getMessage());
			responseDto.setStatus(Boolean.FALSE);
			responseDto.setStatusCode(500);
			responseDto.setMessage(e.getMessage());

		}

		logger.info("EmployeeServiceImpl | verifyIdPass | Execution end ouput " + responseDto);

		return responseDto;

	}

	// for checking employee email is exist or not and employee is active or not
	@Override
	public ResponseDto isValidUser(EmployeeDto employeeDto) {

		logger.info("EmployeeServiceImpl | isValidUser | Execution start input " + employeeDto);

		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatus(Boolean.TRUE);
		responseDto.setStatusCode(200);
		boolean status = employeeDao.isValidUser(employeeDto);
		if (status == true) {
			responseDto.setMessage("Employee Is Valid");

		} else {
			responseDto.setStatus(Boolean.FALSE);
			responseDto.setStatusCode(500);
			responseDto.setMessage("Employee Does Not Exist In Company Or Entering Email Is Wrong");
		}
		logger.info("EmployeeServiceImpl | isValidUser | Execution end ouput " + responseDto);
		return responseDto;
	}

	// for save employee data
	@Override
	public ResponseDto saveEmployeeData(EmployeeDto employeeDto) {

		logger.info("EmployeeServiceImpl | saveEmployeeData | Execution start input " + employeeDto);

		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatus(Boolean.TRUE);
		responseDto.setStatusCode(200);
		employeeDao.isValidUser(employeeDto);
		try {
			employeeDao.saveEmployeeData(employeeDto);
			responseDto.setMessage("Employee Details Saved Successfully!");

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

