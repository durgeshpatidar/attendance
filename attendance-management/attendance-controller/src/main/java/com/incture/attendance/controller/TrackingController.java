package com.incture.attendance.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

	// Add tracking during CheckIn.
	@PostMapping("/checkin")
	public ResponseDto addTracking(@RequestBody TrackingDto trackingDto) {
		return trackingService.addTracking(trackingDto);

	}

	// Getting tracking details for an employee.
	@GetMapping("/tracking-details")
	@ResponseBody
	public ResponseDto getTrackingDetails(@RequestParam String id, @RequestParam(defaultValue = "") String start,
			@RequestParam(defaultValue = "") String end) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		System.out.println(id + " " + start + " " + end);
		Date startDate = null, endDate = null;
		try {
			startDate = formatter.parse(start);

		} catch (Exception e) {
			System.out.println(e);
			startDate = null;
		}
		try {
			endDate = formatter.parse(end);
		} catch (Exception e) {
			System.out.println(e);
			endDate = null;
		}
		return trackingService.getTrackingDetails(id, startDate, endDate);
	}

	// Updating tracking details during checkout.
	@PostMapping("/checkout")
	public ResponseDto updateTracking(@RequestBody TrackingDto trackingdto) {
		String id = trackingdto.getId();
		Date checkOut = trackingdto.getCheckOut();
		double totalHours = trackingdto.getTotalHours();
		return trackingService.updateTracking(id, checkOut, totalHours);

	}

	// Update tracking by admin.
	@PostMapping("/update")
	public ResponseDto updateTrackingByAdmin(@RequestBody TrackingDto trackingDto) {
		return trackingService.updateTrackingByAdmin(trackingDto);
	}

	// Getting tracking details for an employee.
	@GetMapping("/last-tracking")
	public ResponseDto getLastTracking(@RequestParam String empId) {
		return trackingService.getLastTracking(empId);
	}

}
