package com.incture.attendance.dao;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;
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

	// For CheckIn
	@Override
	public String addTracking(TrackingDto trackingdto) {
		// @SuppressWarnings("deprecation")
		// Criteria criteria = getSession().createCriteria(TrackingDo.class);
		// criteria.add(Restrictions.eq("date", trackingdto.getDate()));
		// criteria.add(Restrictions.eq("employee", getSession().get(EmployeeDo.class,
		// trackingdto.getEmpId())));

		// checking whether employee already checked in
		String hql = "from TrackingDo where date =:date1 and employee =:employee";
		Query query = getSession().createQuery(hql);
		query.setParameter("date1", trackingdto.getDate());
		query.setParameter("employee", getSession().get(EmployeeDo.class, trackingdto.getEmpId()));
		TrackingDo track = (TrackingDo) query.getSingleResult();
		if (track != null)
			return null;
		// If not checkedin
		trackingdto.setStatus("Pending");
		TrackingDo tdo = importDto(trackingdto);
		getSession().save(tdo);
		return tdo.getId();

	}

	// For getting tracking details
	@SuppressWarnings("unchecked")
	@Override
	public List<TrackingDetailsDto> getTrackingDetails(String id, Date start, Date end) {
		// Getting tracking details in between start and end date
		// @SuppressWarnings("deprecation")
		// Criteria criteria = getSession().createCriteria(TrackingDo.class);
		// criteria.add(Restrictions.eq("employee", getSession().get(EmployeeDo.class,
		// id)));
		List<TrackingDo> tracks = new ArrayList<>();
		;
		if (start != null && end != null) {

			String hql = "from TrackingDo where employee =:employee and date>=:start and date<=:end order by date desc";
			Query query = getSession().createQuery(hql);
			query.setParameter("employee", getSession().get(EmployeeDo.class, id));
			query.setParameter("start", start);
			query.setParameter("end", end);
			query.setMaxResults(30);
			tracks.addAll((Collection<? extends TrackingDo>) query.getResultList());
			// criteria.add(Restrictions.between("date", start, end));
			// criteria.setMaxResults(30);

		}

		if (start == null && end == null) {
			String hql = "from TrackingDo where employee = :employee order by date desc";
			Query query = getSession().createQuery(hql);
			query.setParameter("employee", getSession().get(EmployeeDo.class, id));
			query.setMaxResults(7);
			tracks.addAll((Collection<? extends TrackingDo>) query.getResultList());
			// criteria.setMaxResults(7);
		}
		// criteria.addOrder(Order.desc("date"));

		// Getting employee name
		// @SuppressWarnings("deprecation")
		// Criteria crit = getSession().createCriteria(EmployeeMasterDo.class);
		// crit.add(Restrictions.eq("id", id));
		// EmployeeMasterDo emp = (EmployeeMasterDo) crit.uniqueResult();
		String hql2 = "from EmployeeMasterDo where id =:id";
		Query query2 = getSession().createQuery(hql2);
		query2.setParameter("id", id);
		EmployeeMasterDo emp = (EmployeeMasterDo) query2.getSingleResult();

		List<TrackingDetailsDto> history = new ArrayList<>();

		TrackingDetailsDto newTracking = null;
		for (TrackingDo t : tracks) {
			newTracking = new TrackingDetailsDto();
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

		// @SuppressWarnings("deprecation")
		// Criteria criteria = getSession().createCriteria(TrackingDo.class);
		// criteria.add(Restrictions.eq("id", id));
		// TrackingDo current = (TrackingDo) criteria.uniqueResult();

		String hql = "from TrackingDo where id =:id";
		Query query = getSession().createQuery(hql);
		query.setParameter("id", id);
		TrackingDo current = (TrackingDo) query.getSingleResult();
		current.setCheckOut(checkOut);
		current.setTotalHours(totalHours);
		if (totalHours >= 7.0 && totalHours <= 9.0) {
			current.setStatus("Approved");
		}

	}

}
