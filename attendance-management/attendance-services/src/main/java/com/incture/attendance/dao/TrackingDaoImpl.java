package com.incture.attendance.dao;


import org.springframework.stereotype.Repository;
import org.hibernate.Session;

import com.incture.attendance.dto.TrackingDto;
import com.incture.attendance.entities.AddressDo;
import com.incture.attendance.entities.EmployeeDo;
import com.incture.attendance.entities.TrackingDo;

@Repository("TrackingDaoImpl")
public class TrackingDaoImpl extends BaseDao<TrackingDo, TrackingDto> implements TrackingDao {

	Session session = getSession();
	@Override
	public void addTracking(TrackingDto trackingdto) {
		getSession().save(importDto(trackingdto));
		
		
	}

	@Override
	public void updateTracking(TrackingDto trackingdto) {
		
		
	}

	@Override
	protected TrackingDo importDto(TrackingDto trackingDto) {
		TrackingDo entity= null;
		entity.setId(trackingDto.getId());
		entity.setAddressTracking(session.get(AddressDo.class, trackingDto.getAddressId()));
		entity.setEmpTracking(session.get(EmployeeDo.class, trackingDto.getEmpId()));
		entity.setCheckIn(null);
		entity.setCheckOut(null);
		entity.setDate(null);
		entity.setTotalHours(null);
		return entity;
		
		}
	

	@Override
	protected TrackingDto exportDto(TrackingDo entity) {
		TrackingDto dto = null;
		dto.setId(entity.getId());
		dto.setAddressId(entity.getAddressTracking().getId());
		dto.setEmpId(entity.getEmpTracking().getId());
		dto.setCheckIn(null);
		dto.setCheckOut(null);
		dto.setTotalHours(null);
		dto.setDate(null);
		return dto;
	}
	//p


}
