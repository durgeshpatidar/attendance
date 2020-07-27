package com.incture.attendance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.incture.attendance.dto.EmployeeDto;
import com.incture.attendance.service.EmployeeService;
import com.incture.attendance.utils.ResponseDto;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping
	public String TestApi() {
		return "Done";
	}

	// For signup for non registered user.
	@PostMapping("/signup")
	public ResponseDto saveEmployeeData(@RequestBody EmployeeDto employeeDto) {
		return employeeService.saveEmployeeData(employeeDto);
	}

	// For login.
	@PostMapping("/login")
	public ResponseDto verifyIdPass(@RequestBody EmployeeDto employeeDto) {
		return employeeService.verifyIdPass(employeeDto);
	}

	// To validate the mail id of user in signup and login to check whether its
	// valid or not and active or not.
	@PostMapping("/validate")
	public ResponseDto isValidUser(@RequestBody EmployeeDto employeeDto) {
		return employeeService.isValidUser(employeeDto);
	}

	// Displaying profile details of employee.
	@PostMapping("/profile")
	public ResponseDto profileDetails(@RequestBody EmployeeDto employeeDto) {
		return employeeService.profileDetails(employeeDto);
	}

	// Updating password in forgot password cases.
	@PostMapping("/update-password")
	public ResponseDto updatePassword(@RequestBody EmployeeDto employeeDto) {
		return employeeService.updatePassword(employeeDto);
	}

	// Checking employee type for providing views accordingly.
	@GetMapping("/employee-type")
	public ResponseDto verifyEmployeeType(@RequestParam String empId) {
		return employeeService.verifyEmployeeType(empId);
	}

	// Getting list of employees under a manager.
	@GetMapping("/employee-list")
	public ResponseDto getEmployeeList(@RequestParam String empId) {
		return employeeService.getEmployeeList(empId);
	}

	// Verifying admin or not.
	@GetMapping("/verify-admin")
	public ResponseDto verifyAdminId(@RequestParam String empId) {
		return employeeService.verifyAdminId(empId);
	}

	// Getting list of all employees for admin.
	@GetMapping("/all-employee")
	public ResponseDto getAllEmployeeList(@RequestParam String empId) {
		return employeeService.getAllEmployeeList(empId);
	}
}
