package com.incture.attendance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.incture.attendance.dto.EmployeeDto;
import com.incture.attendance.service.EmailService;
import com.incture.attendance.service.OtpService;
import com.incture.attendance.utils.ResponseDto;

@RestController
@RequestMapping
public class OtpController {
	
	@Autowired
	private OtpService otpService;
	@Autowired
	private EmailService emailService;
	@GetMapping
	public String TestApi() {
		return "Done";
	}
	
	@PostMapping("/send-mail")
	public ResponseDto sendMail(@RequestBody EmployeeDto employeeDto){
		return emailService.sendMail(employeeDto);
	}
	
	@GetMapping("/validate-otp")
	public ResponseDto validateOtp(@RequestParam int otp,@RequestParam String email)
	{
		return otpService.validateOtp(otp,email);
	}
	
}
