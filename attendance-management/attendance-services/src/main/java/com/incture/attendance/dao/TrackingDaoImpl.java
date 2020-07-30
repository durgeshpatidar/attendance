package com.incture.attendance.dao;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.hibernate.query.Query;
import com.incture.attendance.dto.TrackingDetailsDto;
import com.incture.attendance.dto.TrackingDto;
import com.incture.attendance.entities.AddressDo;
import com.incture.attendance.entities.EmployeeDo;
import com.incture.attendance.entities.EmployeeMasterDo;
import com.incture.attendance.entities.TrackingDo;

@SuppressWarnings("rawtypes")
@Repository("TrackingDaoImpl")
public class TrackingDaoImpl extends BaseDao<TrackingDo, TrackingDto> implements TrackingDao {

	@Override
	protected TrackingDo importDto(TrackingDto trackingDto) {
		TrackingDo entity = new TrackingDo();
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

	// For CheckIn.
	// Adding checkin information to tracking table.
	@Override
	public String addTracking(TrackingDto trackingdto) {

		// checking whether employee already checked in or not.
		String hql = "from TrackingDo where date =:date1 and employee =:employee";
		Query query = getSession().createQuery(hql);
		query.setParameter("date1", trackingdto.getDate());
		query.setParameter("employee", getSession().get(EmployeeDo.class, trackingdto.getEmpId()));
		try {
			TrackingDo track = (TrackingDo) query.getSingleResult();
			if (track != null)
				return null;
		} catch (Exception e) {
			trackingdto.setStatus("Pending");
			TrackingDo tdo = importDto(trackingdto);
			getSession().save(tdo);
			return tdo.getId();
		}
		return null;
	}

	// For getting tracking details
	@SuppressWarnings("unchecked")
	@Override
	public List<TrackingDetailsDto> getTrackingDetails(String id, Date start, Date end) {

		List<TrackingDo> tracks = new ArrayList<>();
		// Displaying details using filters.
		if (start != null && end != null) {

			String hql = "from TrackingDo where employee =:employee and date>=:start and date<=:end order by date desc";
			Query query = getSession().createQuery(hql);
			query.setParameter("employee", getSession().get(EmployeeDo.class, id));
			query.setParameter("start", start);
			query.setParameter("end", end);
			query.setMaxResults(30);
			tracks.addAll((Collection<? extends TrackingDo>) query.getResultList());

		}
		// Displaying details without filters.
		if (start == null && end == null) {
			String hql = "from TrackingDo where employee = :employee order by date desc";
			Query query = getSession().createQuery(hql);
			query.setParameter("employee", getSession().get(EmployeeDo.class, id));
			query.setMaxResults(7);
			tracks.addAll((Collection<? extends TrackingDo>) query.getResultList());

		}
		// Displaying details when only start date is given.
		if (start != null && end == null) {

			String hql = "from TrackingDo where employee =:employee and date>=:start order by date desc";
			Query query = getSession().createQuery(hql);
			query.setParameter("employee", getSession().get(EmployeeDo.class, id));
			query.setParameter("start", start);
			query.setMaxResults(30);
			tracks.addAll((Collection<? extends TrackingDo>) query.getResultList());

		}
		// Displaying only end date is given.
		if (start == null && end != null) {

			String hql = "from TrackingDo where employee =:employee and date<=:end order by date desc";
			Query query = getSession().createQuery(hql);
			query.setParameter("employee", getSession().get(EmployeeDo.class, id));
			query.setParameter("end", end);
			query.setMaxResults(30);
			tracks.addAll((Collection<? extends TrackingDo>) query.getResultList());

		}
		// To get the name of employee.
		String hql2 = "from EmployeeMasterDo where id =:id";
		Query query2 = getSession().createQuery(hql2);
		query2.setParameter("id", id);
		EmployeeMasterDo emp = (EmployeeMasterDo) query2.getSingleResult();

		List<TrackingDetailsDto> history = new ArrayList<>();

		TrackingDetailsDto newTracking = null;
		for (TrackingDo t : tracks) {
			newTracking = new TrackingDetailsDto();
			newTracking.setId(t.getId());
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

	// For updating tracking during checkout.
	@Override
	public void updateTracking(String id, Date checkOut, double totalHours) {

		String hql = "from TrackingDo where id =:id";
		Query query = getSession().createQuery(hql);
		query.setParameter("id", id);
		TrackingDo current = (TrackingDo) query.getSingleResult();
		current.setCheckOut(checkOut);
		current.setTotalHours(totalHours);
		// If total hours is in specified limit , set it as approved only.
		if (totalHours >= 7.0 && totalHours <= 9.0) {
			current.setStatus("Approved");
		}

	}

	// Tracking updation by admin.
	@Override
	public void updateTrackingByAdmin(TrackingDto trackingDto) {
		String hql = "UPDATE TrackingDo SET checkIn =:checkIn , checkOut =:checkOut , totalHours =:totalHours  , status =:status WHERE id =:id";
		Query query = getSession().createQuery(hql);
		query.setParameter("checkIn", trackingDto.getCheckIn());
		query.setParameter("checkOut", trackingDto.getCheckOut());
		query.setParameter("totalHours", trackingDto.getTotalHours());
		query.setParameter("status", trackingDto.getStatus());
		query.setParameter("id", trackingDto.getId());
		query.executeUpdate();
	}

	// Getting last checkin checkout detail.
	@Override
	public TrackingDto getLastTracking(String empId) {
		String hql = "from TrackingDo where employee=:employee order by date desc";
		Query query = getSession().createQuery(hql);
		query.setParameter("employee", getSession().get(EmployeeDo.class, empId));
		query.setMaxResults(1);
		if(query.getResultList()==null)
			return null;
		TrackingDo current = (TrackingDo) query.getResultList().get(0);
		if (current == null)
			return null;
		TrackingDto tdto = exportDto(current);
		return tdto;

	}

}
