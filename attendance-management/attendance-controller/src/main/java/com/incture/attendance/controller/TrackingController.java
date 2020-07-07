package com.incture.attendance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.incture.attendance.dto.EmployeeDto;
import com.incture.attendance.service.EmployeeService;
import com.incture.attendance.service.TrackingService;
import com.incture.attendance.utils.ResponseDto;

@RestController
@RequestMapping("/tracking")
public class TrackingController {

	@Autowired
	private TrackingService trackingService;

	@GetMapping
	public String TestApi() {
		return "Done";
	}


}
