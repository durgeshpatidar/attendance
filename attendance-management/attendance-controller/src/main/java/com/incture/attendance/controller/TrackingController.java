package com.incture.attendance.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.incture.attendance.dto.TrackingDto;
import com.incture.attendance.service.TrackingService;
import com.incture.attendance.utils.ResponseDto;

@RestController
@RequestMapping("/tracking")
public class TrackingController {

	@Autowired
	private TrackingService trackingService;

//Add tracking or CheckIn
	@PostMapping("/checkin")
	public ResponseDto addTracking(@RequestBody TrackingDto trackingDto) {
		return trackingService.addTracking(trackingDto);

	}

//Getting tracking details for an employee
	@GetMapping("/tracking-details")
	@ResponseBody
	public ResponseDto getTrackingDetails(@RequestParam String id, @RequestParam(defaultValue = "") String start,
			@RequestParam(defaultValue = "") String end) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

		System.out.println(id + " " + start + " " + end);
		Date startDate = null, endDate = null;
		try {
			startDate = formatter.parse(start);
			endDate = formatter.parse(end);
		} catch (Exception e) {
			System.out.println(e);
		}
		return trackingService.getTrackingDetails(id, startDate, endDate);
	}

//Updating tracking details or checkout
	@PatchMapping("/checkout")
	public ResponseDto updateTracking(@RequestParam String id, @RequestParam Date checkOut,
			@RequestParam double totalHours) {
		return trackingService.updateTracking(id, checkOut, totalHours);

	}
	//
}
