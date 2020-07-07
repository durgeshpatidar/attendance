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
	protected TrackingDo importDto(TrackingDto trackingDto) {
		TrackingDo entity= null;
		entity.setId(trackingDto.getId());
		entity.setAddressTracking(session.get(AddressDo.class, trackingDto.getAddressId()));
		entity.setEmpTracking(session.get(EmployeeDo.class, trackingDto.getEmpId()));
		entity.setCheckIn(trackingDto.getCheckIn());
		entity.setCheckOut(trackingDto.getCheckIn());
		entity.setDate(trackingDto.getDate());
		entity.setTotalHours(trackingDto.getTotalHours());
		return entity;
		
		}
	

	@Override
	protected TrackingDto exportDto(TrackingDo entity) {
		TrackingDto dto = null;
		dto.setId(entity.getId());
		dto.setAddressId(entity.getAddressTracking().getId());
		dto.setEmpId(entity.getEmpTracking().getId());
		dto.setCheckIn(entity.getCheckIn());
		dto.setCheckOut(entity.getCheckOut());
		dto.setTotalHours(entity.getTotalHours());
		dto.setDate(entity.getDate());
		return dto;
	}
	//For adding tracking
	@Override
	public void addTracking(TrackingDto trackingdto) {
		getSession().save(importDto(trackingdto));
		
		
	}

	@Override
	public void updateTracking(TrackingDto trackingdto) {
		
		
	}

}
