package com.incture.attendance.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.incture.attendance.dto.BaseDto;
import com.incture.attendance.entities.BaseDo;

public abstract class BaseDao<E extends BaseDo, D extends BaseDto> {

	@Autowired
	private SessionFactory sessionFactory;

	// Connection
	public Session getSession() {
		try {
			return sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			return sessionFactory.openSession();
		}
	}

	public StatelessSession getStatelessSession() {
		return sessionFactory.openStatelessSession();
	}

	protected abstract E importDto(D fromDto);

	protected abstract D exportDto(E entity);

}
