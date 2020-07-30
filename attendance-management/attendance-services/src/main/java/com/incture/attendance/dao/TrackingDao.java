package com.incture.attendance.dao;

import java.util.Date;
import java.util.List;

import com.incture.attendance.dto.TrackingDetailsDto;
import com.incture.attendance.dto.TrackingDto;

public interface TrackingDao {
	String addTracking(TrackingDto trackingdto);

	List<TrackingDetailsDto> getTrackingDetails(String id, Date start, Date end);

	void updateTracking(String id, Date checkOut, double totalHours);

	void updateTrackingByAdmin(TrackingDto trackingDto);

	TrackingDto getLastTracking(String empId);

}
