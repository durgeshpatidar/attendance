package com.incture.attendance.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.incture.attendance.dao.TrackingDao;
import com.incture.attendance.dto.TrackingDetailsDto;
import com.incture.attendance.dto.TrackingDto;
import com.incture.attendance.utils.ResponseDto;

@Service
@Transactional
public class TrackingServiceImpl implements TrackingService {
	@Autowired
	private TrackingDao trackingDao;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	//Add tracking
	@Override
	public ResponseDto addTracking(TrackingDto trackingDto) {
		logger.info("TrackingServiceImpl | addTracking | Execution start input " + trackingDto);

		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatus(Boolean.TRUE);
		responseDto.setStatusCode(200);
		try {
			trackingDao.addTracking(trackingDto);
			responseDto.setMessage("Tracking Details added Successfully!");

		} catch (Exception e) {

			logger.error("TrackingServiceImpl | addTracking | Exception " + e.getMessage());
			responseDto.setStatus(Boolean.FALSE);
			responseDto.setStatusCode(500);
			responseDto.setMessage(e.getMessage());

		}

		logger.info("TrackingServiceImpl | addTracking  | Execution end ouput " + responseDto);

		return responseDto;
	}

//Getting tracking details
	@Override
	public ResponseDto getTrackingDetails(String id, Date start, Date end) {

		logger.info("TrackingServiceImpl | getTrackingDetails | Execution start input " + id + start + end);

		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatus(Boolean.TRUE);
		responseDto.setStatusCode(200);
		try {
			List<TrackingDetailsDto> trackings = trackingDao.getTrackingDetails(id,start,end);
			int size=trackings.size();
			if(size!=0) {
				responseDto.setData(trackings);
				responseDto.setMessage("Tracking details");
			}
			
			else {
				responseDto.setMessage("No details...");
			}
				
		} catch (Exception e) {

			logger.error("TrackingServiceImpl | getTrackingDetails | Exception " + e.getMessage());
			responseDto.setStatus(Boolean.FALSE);
			responseDto.setStatusCode(500);
			responseDto.setMessage(e.getMessage());

		}

		logger.info("TrackingServiceImpl | getTrackingDetails | Execution end ouput " + responseDto);

		return responseDto;

	}

}
