package com.incture.attendance.controller;

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

	@PostMapping
	public ResponseDto addTracking(@RequestBody TrackingDto trackingDto) {
		return trackingService.addTracking(trackingDto);

	}

	@GetMapping("/tracking-details")
	@ResponseBody
	public ResponseDto getTrackingDetails(@RequestParam String id, @RequestParam Date start, @RequestParam Date end) {
		System.out.println(id + " " + start + " " + end);
		return trackingService.getTrackingDetails(id, start, end);
	}
//
}
