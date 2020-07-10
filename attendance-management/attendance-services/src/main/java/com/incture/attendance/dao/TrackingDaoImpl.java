package com.incture.attendance.dao;


import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.incture.attendance.dto.TrackingDetailsDto;
import com.incture.attendance.dto.TrackingDto;
import com.incture.attendance.entities.AddressDo;
import com.incture.attendance.entities.EmployeeDo;
import com.incture.attendance.entities.EmployeeMasterDo;
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
	public List<TrackingDetailsDto> getTrackingDetails(String id, Date start, Date end) {
		//Getting tracking details in between start and end date
		@SuppressWarnings("deprecation")
		Criteria criteria=getSession().createCriteria(TrackingDo.class);
		criteria.add(Restrictions.eq("employeeId",id));
		criteria.add(Restrictions.between("date", start, end));
		@SuppressWarnings("unchecked")
		List<TrackingDo> trackings = criteria.list();
		//Getting employee name
		@SuppressWarnings("deprecation")
		Criteria crit=getSession().createCriteria(EmployeeMasterDo.class);
		criteria.add(Restrictions.eq("employeeId",id));
		EmployeeMasterDo emp =(EmployeeMasterDo)crit.uniqueResult();
		List<TrackingDetailsDto> history = new ArrayList<>();
		
		for(TrackingDo t: trackings) {
			
			TrackingDetailsDto newTracking = new TrackingDetailsDto();
			newTracking.setEmpId(id);
			newTracking.setEmpName(emp.getFirstName()+" "+emp.getLastName());
			newTracking.setDate(t.getDate());
			newTracking.setCheckIn(t.getCheckIn());
			newTracking.setCheckOut(t.getCheckOut());
			newTracking.setTotalHours(t.getTotalHours());
			history.add(newTracking);
			
		}
		return history;
	}

}
