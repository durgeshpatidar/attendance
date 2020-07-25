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

	@PostMapping("/signup")
	public ResponseDto saveEmployeeData(@RequestBody EmployeeDto employeeDto) {
		return employeeService.saveEmployeeData(employeeDto);
	}

	@PostMapping("/login")
	public ResponseDto verifyIdPass(@RequestBody EmployeeDto employeeDto) {
		return employeeService.verifyIdPass(employeeDto);
	}

	@PostMapping("/validate")
	public ResponseDto isValidUser(@RequestBody EmployeeDto employeeDto) {
		return employeeService.isValidUser(employeeDto);
	}

	@PostMapping("/profile")
	public ResponseDto profileDetails(@RequestBody EmployeeDto employeeDto) {
		return employeeService.profileDetails(employeeDto);
	}

	@PostMapping("/update-password")
	public ResponseDto updatePassword(@RequestBody EmployeeDto employeeDto) {
		return employeeService.updatePassword(employeeDto);
	}

	@GetMapping("/employee-type")
	public ResponseDto verifyEmployeeType(@RequestParam String empId) {
		return employeeService.verifyEmployeeType(empId);
	}

	@GetMapping("/employee-list")
	public ResponseDto getEmployeeList(@RequestParam String empId) {
		return employeeService.getEmployeeList(empId);
	}
}
