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

	// Adding tracking.
	@Override
	public ResponseDto addTracking(TrackingDto trackingDto) {
		logger.info("TrackingServiceImpl | addTracking | Execution start input " + trackingDto);

		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatus(Boolean.TRUE);
		responseDto.setStatusCode(200);
		try {
			String trackingId = trackingDao.addTracking(trackingDto);
			if (trackingId == null) {
				responseDto.setStatus(Boolean.FALSE);
				responseDto.setStatusCode(500);
				responseDto.setMessage("You already checked in for today");
			} else {
				responseDto.setMessage("Tracking Details added Successfully!");
				responseDto.setData(trackingId);
			}

		} catch (Exception e) {

			logger.error("TrackingServiceImpl | addTracking | Exception " + e.getMessage());
			responseDto.setStatus(Boolean.FALSE);
			responseDto.setStatusCode(500);
			responseDto.setMessage(e.getMessage());

		}

		logger.info("TrackingServiceImpl | addTracking  | Execution end ouput " + responseDto);

		return responseDto;
	}

	// Getting tracking details.
	@Override
	public ResponseDto getTrackingDetails(String id, Date start, Date end) {

		logger.info("TrackingServiceImpl | getTrackingDetails | Execution start input " + id + " " + start + " " + end);

		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatus(Boolean.TRUE);
		responseDto.setStatusCode(200);
		try {
			List<TrackingDetailsDto> trackings = trackingDao.getTrackingDetails(id, start, end);
			int size = trackings.size();
			if (size != 0) {
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

	// Update tracking during checkout.
	@Override
	public ResponseDto updateTracking(String id, Date checkOut, double totalHours) {
		logger.info("TrackingServiceImpl | updateTracking | Execution start input " + id + " " + checkOut + " "
				+ totalHours);

		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatus(Boolean.TRUE);
		responseDto.setStatusCode(200);
		try {
			trackingDao.updateTracking(id, checkOut, totalHours);
			responseDto.setMessage("Checkout details added Successfully!");

		} catch (Exception e) {

			logger.error("TrackingServiceImpl | updateTracking | Exception " + e.getMessage());
			responseDto.setStatus(Boolean.FALSE);
			responseDto.setStatusCode(500);
			responseDto.setMessage(e.getMessage());

		}

		logger.info("TrackingServiceImpl | updateTracking | Execution end ouput " + responseDto);

		return responseDto;
	}

	// Update tracking by admin.
	@Override
	public ResponseDto updateTrackingByAdmin(TrackingDto trackingDto) {
		logger.info("TrackingServiceImpl | updateTrackingByAdmin | Execution start input " + trackingDto);
		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatus(Boolean.TRUE);
		responseDto.setStatusCode(200);
		try {
			trackingDao.updateTrackingByAdmin(trackingDto);
			responseDto.setMessage("Checkout details updated Successfully!");
		} catch (Exception e) {
			logger.error("TrackingServiceImpl | updateTrackingByAdmin | Exception " + e.getMessage());
			responseDto.setStatus(Boolean.FALSE);
			responseDto.setStatusCode(500);
			responseDto.setMessage(e.getMessage());
		}
		logger.info("TrackingServiceImpl | updateTrackingByAdmin | Execution end ouput " + responseDto);
		return responseDto;
	}

	//get Last tracking details for employee
	@Override
	public ResponseDto getLastTracking(String empId) {
		logger.info("TrackingServiceImpl | getLastTracking | Execution start input " + empId);
		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatus(Boolean.TRUE);
		responseDto.setStatusCode(200);
		try {
			TrackingDto tDto=trackingDao.getLastTracking(empId);
			if (tDto == null) {
				responseDto.setMessage("No Record found");
			} else {
				responseDto.setData(tDto);
				responseDto.setMessage("Last Tracking Displayed Successfully!");
			}
		} catch (Exception e) {
			logger.error("TrackingServiceImpl | getLastTracking | Exception " + e.getMessage());
			responseDto.setStatus(Boolean.FALSE);
			responseDto.setStatusCode(500);
			responseDto.setMessage(e.getMessage());
		}
		logger.info("TrackingServiceImpl | getLastTracking | Execution end ouput " + responseDto);
		return responseDto;
	}
}
