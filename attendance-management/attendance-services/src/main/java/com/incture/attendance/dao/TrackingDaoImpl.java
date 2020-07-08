package com.incture.attendance.dao;


import org.springframework.stereotype.Repository;
import org.hibernate.Session;

import com.incture.attendance.dto.TrackingDto;
import com.incture.attendance.entities.AddressDo;
import com.incture.attendance.entities.EmployeeDo;
import com.incture.attendance.entities.TrackingDo;

@Repository("TrackingDaoImpl")
public class TrackingDaoImpl extends BaseDao<TrackingDo, TrackingDto> implements TrackingDao {

	@Override
	public void addTracking(TrackingDto trackingdto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateTracking(TrackingDto trackingdto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected TrackingDo importDto(TrackingDto fromDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected TrackingDto exportDto(TrackingDo entity) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
