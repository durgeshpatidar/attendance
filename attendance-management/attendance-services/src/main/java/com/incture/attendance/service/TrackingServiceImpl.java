package com.incture.attendance.service;

import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.incture.attendance.dao.TrackingDao;
import com.incture.attendance.dto.TrackingDto;
import com.incture.attendance.utils.ResponseDto;

@Service
@Transactional
public class TrackingServiceImpl implements TrackingService {
	@Autowired
	private TrackingDao trackingDao;

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

}
