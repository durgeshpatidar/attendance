package com.incture.attendance.dao;

import java.util.List;

import com.incture.attendance.dto.EmployeeDto;
import com.incture.attendance.dto.TrackingDetailsDto;
import com.incture.attendance.dto.TrackingDto;

public interface TrackingDao {
	void addTracking(TrackingDto trackingdto);

	void updateTracking(TrackingDto trackingdto);
	
	List<TrackingDetailsDto> getTrackingDetails(EmployeeDto employeeDto);
	

}
