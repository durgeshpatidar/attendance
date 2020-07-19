package com.incture.attendance.dao;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
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
		TrackingDo entity = new TrackingDo();
		// entity.setId(trackingDto.getId());
		entity.setAddress(getSession().get(AddressDo.class, trackingDto.getAddressId()));
		entity.setEmployee(getSession().get(EmployeeDo.class, trackingDto.getEmpId()));
		entity.setCheckIn(trackingDto.getCheckIn());
		entity.setCheckOut(trackingDto.getCheckOut());
		entity.setDate(trackingDto.getDate());
		entity.setTotalHours(trackingDto.getTotalHours());
		entity.setStatus(trackingDto.getStatus());
		return entity;
	}

	@Override
	protected TrackingDto exportDto(TrackingDo entity) {
		TrackingDto dto = new TrackingDto();
		dto.setId(entity.getId());
		dto.setAddressId(entity.getAddress().getId());
		dto.setEmpId(entity.getEmployee().getId());
		dto.setCheckIn(entity.getCheckIn());
		dto.setCheckOut(entity.getCheckOut());
		dto.setTotalHours(entity.getTotalHours());
		dto.setDate(entity.getDate());
		dto.setStatus(entity.getStatus());
		return dto;
	}

//For CheckIn
	@Override
	public String addTracking(TrackingDto trackingdto) {
		trackingdto.setStatus("PENDING");
		TrackingDo tdo = importDto(trackingdto);

		getSession().save(tdo);
		return tdo.getId();

	}

//For getting tracking details
	@Override
	public List<TrackingDetailsDto> getTrackingDetails(String id, Date start, Date end) {
		// Getting tracking details in between start and end date
		@SuppressWarnings("deprecation")
		Criteria criteria = getSession().createCriteria(TrackingDo.class);
		criteria.add(Restrictions.eq("employee", getSession().get(EmployeeDo.class, id)));
		criteria.addOrder(Order.desc("date"));
		criteria.setMaxResults(7);
		if (start != null && end != null) {

			criteria.add(Restrictions.between("date", start, end));
		} else if (start == null && end != null) {

			criteria.add(Restrictions.le("date", end));
		} else if (start != null && end == null) {
			criteria.add(Restrictions.ge("date", start));
		}
		
		@SuppressWarnings("unchecked")
		List<TrackingDo> trackings = criteria.list();
		// Getting employee name
		@SuppressWarnings("deprecation")
		Criteria crit = getSession().createCriteria(EmployeeMasterDo.class);
		crit.add(Restrictions.eq("id", id));
		EmployeeMasterDo emp = (EmployeeMasterDo) crit.uniqueResult();
		List<TrackingDetailsDto> history = new ArrayList<>();

		for (TrackingDo t : trackings) {

			TrackingDetailsDto newTracking = new TrackingDetailsDto();
			newTracking.setEmpId(id);
			newTracking.setEmpName(emp.getFirstName() + " " + emp.getLastName());
			newTracking.setDate(t.getDate());
			newTracking.setCheckIn(t.getCheckIn());
			newTracking.setCheckOut(t.getCheckOut());
			newTracking.setTotalHours(t.getTotalHours());
			newTracking.setStatus(t.getStatus());
			history.add(newTracking);

		}
		return history;
	}

	// for updating tracking or checkout
	@Override
	public void updateTracking(String id, Date checkOut, double totalHours) {

		@SuppressWarnings("deprecation")
		Criteria criteria = getSession().createCriteria(TrackingDo.class);
		criteria.add(Restrictions.eq("id", id));
		TrackingDo current = (TrackingDo) criteria.uniqueResult();
		current.setCheckOut(checkOut);
		current.setTotalHours(totalHours);
		if (totalHours >= 7.0 && totalHours <= 9.0) {
			current.setStatus("APPROVED");
		}

	}

}
