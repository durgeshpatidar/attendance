package com.incture.attendance.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.incture.attendance.dao.EmployeeDao;
import com.incture.attendance.dto.EmployeeDto;
import com.incture.attendance.dto.ProfileDto;
import com.incture.attendance.utils.ResponseDto;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EmployeeDao employeeDao;

	// For checking employee id and password during login and returns the user
	// information if the user logs in successfully.
	@Override
	public ResponseDto verifyIdPass(EmployeeDto employeeDto) {
		logger.info("EmployeeServiceImpl | verifyIdPass | Execution start input " + employeeDto);

		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatus(Boolean.TRUE);
		responseDto.setStatusCode(200);
		if (employeeDto.getPassword() == "") {
			responseDto.setStatus(Boolean.FALSE);
			responseDto.setStatusCode(500);
			responseDto.setMessage("Password Required");
		} else {
			try {
				boolean status = employeeDao.verifyIdPass(employeeDto);
				if (status == true) {
					responseDto.setMessage("Login successful");
					responseDto.setData(employeeDto);
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
		}

		logger.info("EmployeeServiceImpl | verifyIdPass | Execution end ouput " + responseDto);

		return responseDto;

	}

	// For checking employee email is exist or not and employee is active or not
	@Override
	public ResponseDto isValidUser(EmployeeDto employeeDto) {

		logger.info("EmployeeServiceImpl | isValidUser | Execution start input " + employeeDto);

		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatus(Boolean.TRUE);
		responseDto.setStatusCode(200);
		try {
			boolean status = employeeDao.isValidUser(employeeDto);
			if (status == true) {
				responseDto.setMessage("Employee Is Valid");

			} else {
				responseDto.setStatus(Boolean.FALSE);
				responseDto.setStatusCode(500);
				responseDto.setMessage("Employee Does Not Exist In Company Or Entering Email Is Wrong");
			}
		} catch (Exception e) {
			logger.error("EmployeeServiceImpl | isValidUser | Exception " + e.getMessage());
			responseDto.setStatus(Boolean.FALSE);
			responseDto.setStatusCode(500);
			responseDto.setMessage("Employee Does Not Exist In Company Or Entering Email Is Wrong");
		}
		logger.info("EmployeeServiceImpl | isValidUser | Execution end ouput " + responseDto);
		return responseDto;
	}

	// For saving employee data.
	@Override
	public ResponseDto saveEmployeeData(EmployeeDto employeeDto) {

		logger.info("EmployeeServiceImpl | saveEmployeeData | Execution start input " + employeeDto);

		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatus(Boolean.TRUE);
		responseDto.setStatusCode(200);
		try {
			boolean check = employeeDao.isValidUser(employeeDto);
			if (check == false) {
				responseDto.setStatus(Boolean.FALSE);
				responseDto.setStatusCode(500);
				responseDto.setMessage("Email id not exist!");
			} else {
				boolean status = employeeDao.saveEmployeeData(employeeDto);
				if (status == true)
					responseDto.setMessage("Profile Created Successfully!");
				else {
					responseDto.setStatus(Boolean.FALSE);
					responseDto.setStatusCode(500);
					responseDto.setMessage("Email Id Already Registered !");
				}
			}

		} catch (Exception e) {

			logger.error("EmployeeServiceImpl | saveEmployeeData | Exception " + e.getMessage());
			responseDto.setStatus(Boolean.FALSE);
			responseDto.setStatusCode(500);
			responseDto.setMessage(e.getMessage());

		}

		logger.info("EmployeeServiceImpl | saveEmployeeData | Execution end ouput " + responseDto);

		return responseDto;

	}

	// For displaying profile details of employee including manager details.
	@Override
	public ResponseDto profileDetails(EmployeeDto employeeDto) {
		logger.info("EmployeeServiceImpl | profileDetails| Execution start input " + employeeDto);

		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatus(Boolean.TRUE);
		responseDto.setStatusCode(200);
		try {
			ProfileDto profileData = employeeDao.profileDetails(employeeDto);
			profileData.setManagerList(employeeDao.managerDetails(employeeDto));
			responseDto.setData(profileData);
			responseDto.setMessage("Profile details displayed Successfully!");

		} catch (Exception e) {

			logger.error("EmployeeServiceImpl | profileDetails| Exception " + e.getMessage());
			responseDto.setStatus(Boolean.FALSE);
			responseDto.setStatusCode(500);
			responseDto.setMessage(e.getMessage());

		}

		logger.info("EmployeeServiceImpl | profileDetails | Execution end ouput " + responseDto);

		return responseDto;

	}

	// For updating password in forgot password cases.
	@Override
	public ResponseDto updatePassword(EmployeeDto employeeDto) {
		logger.info("EmployeeServiceImpl | updatePassword | Execution start input " + employeeDto);

		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatus(Boolean.TRUE);
		responseDto.setStatusCode(200);
		try {
			employeeDao.updatePassword(employeeDto);
			responseDto.setMessage("Your Password Has Been Reset!");

		} catch (Exception e) {
			logger.error("EmployeeServiceImpl | updatePassword | Exception " + e.getMessage());
			responseDto.setStatus(Boolean.FALSE);
			responseDto.setStatusCode(500);
			responseDto.setMessage(e.getMessage());
		}
		logger.info("EmployeeServiceImpl | updatePassword | Execution end ouput " + responseDto);
		return responseDto;
	}

	// Checking whether employee is manager or not.
	@Override
	public ResponseDto verifyEmployeeType(String empId) {
		logger.info("EmployeeServiceImpl | verifyEmployeeType | Execution start input " + empId);
		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatus(Boolean.TRUE);
		responseDto.setStatusCode(200);
		boolean status = employeeDao.verifyEmployeeType(empId);
		if (status == true)
			responseDto.setMessage("EmpId is manager");
		else {
			responseDto.setStatus(Boolean.FALSE);
			responseDto.setStatusCode(500);
			responseDto.setMessage("EmpId is not manager");
		}
		logger.info("EmployeeServiceImpl | verifyEmployeeType | Execution end ouput " + responseDto);
		return responseDto;
	}

	// Getting list of employee under a manager.
	@Override
	public ResponseDto getEmployeeList(String empId) {
		logger.info("EmployeeServiceImpl | getEmployeeList | Execution start input " + empId);
		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatus(Boolean.TRUE);
		responseDto.setStatusCode(200);
		try {
			responseDto.setData(employeeDao.getEmployeeList(empId));
		} catch (Exception e) {
			responseDto.setStatus(Boolean.FALSE);
			responseDto.setStatusCode(500);
			responseDto.setMessage(e.toString());
		}
		logger.info("EmployeeServiceImpl | getEmployeeList | Execution end ouput " + responseDto);
		return responseDto;
	}

	// Checking admin or not.
	@Override
	public ResponseDto verifyAdminId(String empId) {
		logger.info("EmployeeServiceImpl | verifyAdminId | Execution start input " + empId);
		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatus(Boolean.TRUE);
		responseDto.setStatusCode(200);
		boolean status = employeeDao.verifyAdminId(empId);
		if (status == true)
			responseDto.setMessage("EmpId is Admin");
		else {
			responseDto.setStatus(Boolean.FALSE);
			responseDto.setStatusCode(500);
			responseDto.setMessage("EmpId is not Admin");
		}
		logger.info("EmployeeServiceImpl | verifyAdminId | Execution end ouput " + responseDto);
		return responseDto;
	}

	// Getting list of all employee for admin.
	@Override
	public ResponseDto getAllEmployeeList(String empId) {
		logger.info("EmployeeServiceImpl | getAllEmployeeList | Execution start input " + empId);
		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatus(Boolean.TRUE);
		responseDto.setStatusCode(200);
		try {
			responseDto.setData(employeeDao.getAllEmployeeList(empId));
		} catch (Exception e) {
			responseDto.setStatus(Boolean.FALSE);
			responseDto.setStatusCode(500);
			responseDto.setMessage(e.toString());
		}
		logger.info("EmployeeServiceImpl | getAllEmployeeList | Execution end ouput " + responseDto);
		return responseDto;
	}
}
