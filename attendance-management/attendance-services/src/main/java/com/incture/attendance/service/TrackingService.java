package com.incture.attendance.service;


import com.incture.attendance.dto.EmployeeDto;
import com.incture.attendance.dto.TrackingDto;
import com.incture.attendance.utils.ResponseDto;

public interface TrackingService {
	ResponseDto addTracking(TrackingDto trackingDto);
	ResponseDto getTrackingDetails(EmployeeDto employeeDto);
}
