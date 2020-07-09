package com.incture.attendance.dao;


import org.springframework.stereotype.Repository;

import java.util.List;

import org.hibernate.Session;

import com.incture.attendance.dto.EmployeeDto;
import com.incture.attendance.dto.TrackingDetailsDto;
import com.incture.attendance.dto.TrackingDto;
import com.incture.attendance.entities.AddressDo;
import com.incture.attendance.entities.EmployeeDo;
import com.incture.attendance.entities.TrackingDo;


@Repository("TrackingDaoImpl")
public class TrackingDaoImpl extends BaseDao<TrackingDo, TrackingDto> implements TrackingDao {

	

	@Override
	protected TrackingDo importDto(TrackingDto trackingDto) {
		TrackingDo entity= new TrackingDo();
		entity.setAddress(getSession().get(AddressDo.class, trackingDto.getAddressId()));
		entity.setEmployee(getSession().get(EmployeeDo.class, trackingDto.getEmpId()));
		entity.setCheckIn(trackingDto.getCheckIn());
		entity.setCheckOut(trackingDto.getCheckIn());
		entity.setDate(trackingDto.getDate());
		entity.setTotalHours(trackingDto.getTotalHours());
		return entity;
		}
	

	@Override
	protected TrackingDto exportDto(TrackingDo entity) {
		TrackingDto dto = new TrackingDto();
		dto.setAddressId(entity.getAddress().getId());
		dto.setEmpId(entity.getEmployee().getId());
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


	@Override
	public List<TrackingDetailsDto> getTrackingDetails(EmployeeDto employeeDto) {
		String empId = employeeDto.getId();
		//List<TrackingDto> trackings = getSession().get(entityType, id);
		return null;
	}

}
