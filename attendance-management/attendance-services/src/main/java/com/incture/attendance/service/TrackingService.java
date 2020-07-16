package com.incture.attendance.service;

import java.util.Date;
import com.incture.attendance.dto.TrackingDto;
import com.incture.attendance.utils.ResponseDto;

public interface TrackingService {
	ResponseDto addTracking(TrackingDto trackingDto);

	ResponseDto getTrackingDetails(String id, Date start, Date end);

	ResponseDto updateTracking(String id);
}
