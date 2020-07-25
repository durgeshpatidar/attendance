package com.incture.attendance.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.incture.attendance.dto.EmployeeDto;
import com.incture.attendance.dto.EmployeeListDto;
import com.incture.attendance.dto.ManagerDetailsDto;
import com.incture.attendance.dto.ProfileDto;
import com.incture.attendance.entities.DesignationMasterDo;
import com.incture.attendance.entities.EmployeeDo;
import com.incture.attendance.entities.EmployeeMasterDo;
import com.incture.attendance.entities.ManagerMasterDo;

@Repository("EmployeeDaoImpl")
public class EmployeeDaoImpl extends BaseDao<EmployeeDo, EmployeeDto> implements EmployeeDao {
	@Autowired
	AddressDaoImpl addressDao;

	@Override
	protected EmployeeDo importDto(EmployeeDto employeeDto) {
		EmployeeDo entity = null;
		if (employeeDto != null) {
			entity = new EmployeeDo();
			entity.setId(employeeDto.getId());
			entity.setPhoneNo(employeeDto.getPhoneNo());
			entity.setPassword(employeeDto.getPassword());
			entity.setEmail(employeeDto.getEmail());
		}
		return entity;
	}

	@Override
	protected EmployeeDto exportDto(EmployeeDo employeeDo) {
		EmployeeDto dto = null;
		if (employeeDo != null) {
			dto = new EmployeeDto();
			dto.setId(employeeDo.getId());
			dto.setPhoneNo(employeeDo.getPhoneNo());
			dto.setPassword(employeeDo.getPassword());
			dto.setEmail(employeeDo.getEmail());
		}
		return dto;
	}

	// for save employee data and adding address from master after signup
	@Override
	public boolean saveEmployeeData(EmployeeDto employeeDto) {
		@SuppressWarnings("deprecation")
		Criteria crit = getSession().createCriteria(EmployeeDo.class);
		crit.add(Restrictions.eq("id", employeeDto.getId()));
		EmployeeDo ed = (EmployeeDo) crit.uniqueResult();
		if (ed != null) {
			return false;
		}
		getSession().save(importDto(employeeDto));
		try {
			addressDao.addMasterAddress(employeeDto);
		} catch (Exception e) {
		}
		return true;
	}

	// for verifying employee email and password
	@Override
	public boolean verifyIdPass(EmployeeDto employeeDto) {
		/*
		 * @SuppressWarnings("rawtypes") Query q =
		 * getSession().createNativeQuery("SELECT ID FROM EMPLOYEE WHERE EMAIL='" +
		 * employeeDto.getEmail() + "' AND PASSWORD='" + employeeDto.getPassword() +
		 * "';");
		 * 
		 * @SuppressWarnings("unchecked") List<Object> l = (List<Object>)
		 * q.getResultList(); try { employeeDto.setId((String) l.get(0)); } catch
		 * (Exception e) { System.out.println(e); } if (l.isEmpty()) return false;
		 * return true;
		 */
		String hql = "id FROM EmployeeDo WHERE email=:email AND password=:password";
		Query q = getSession().createQuery(hql);
		q.setParameter("email", employeeDto.getEmail());
		q.setParameter("password", employeeDto.getPassword());
		String id = (String) q.getSingleResult();
		if (id == null)
			return false;
		employeeDto.setId(id);
		return true;
	}

	// for checking employee email is exist or not and employee is active or not
	@Override
	public boolean isValidUser(EmployeeDto employeeDto) {
		/*
		 * @SuppressWarnings("rawtypes") Query q = getSession().createNativeQuery(
		 * "SELECT ID FROM EMPLOYEE_MASTER WHERE EMAIL='" + employeeDto.getEmail() +
		 * "' AND STATUS='ACTIVE';"); System.out.println("email of employee:" +
		 * employeeDto.getEmail());
		 * 
		 * @SuppressWarnings("unchecked") List<Object> l = (List<Object>)
		 * q.getResultList(); employeeDto.setId((String) l.get(0)); int size = l.size();
		 * System.out.println("size : " + size); if (size != 0) return true; else return
		 * false;
		 */
		String hql = "id FROM EmployeeMasterDo WHERE email=:email and status=:status";
		Query q = getSession().createQuery(hql);
		q.setParameter("email", employeeDto.getEmail());
		q.setParameter("status", "ACTIVE");
		String id = (String) q.getSingleResult();
		if (id == null)
			return false;
		employeeDto.setId(id);
		return true;

	}

	// for displaying profile details
	@Override
	public ProfileDto profileDetails(EmployeeDto employeeDto) {
		String id = employeeDto.getId();
		ProfileDto profileDto = new ProfileDto();
		EmployeeMasterDo empMasterDto = getSession().get(EmployeeMasterDo.class, id);
		profileDto.setId(empMasterDto.getId());
		profileDto.setFirstName(empMasterDto.getFirstName());
		profileDto.setLastName(empMasterDto.getLastName());
		profileDto.setGender(empMasterDto.getGender());
		profileDto.setBloodGroup(empMasterDto.getBloodGroup());
		profileDto.setEmailId(empMasterDto.getEmailId());
		profileDto.setProfileImg(empMasterDto.getProfileImg());
		try {
			@SuppressWarnings("deprecation")
			Criteria crit = getSession().createCriteria(DesignationMasterDo.class);
			crit.add(Restrictions.eq("employeeId", id));
			crit.add(Restrictions.eq("status", "ACTIVE"));
			DesignationMasterDo designation = (DesignationMasterDo) crit.uniqueResult();
			profileDto.setDesignation(designation.getDesignation());
		} catch (Exception e) {
			System.out.println(e);
		}
		return profileDto;

	}

	// For displaying manager details
	@Override
	public List<ManagerDetailsDto> managerDetails(EmployeeDto employeeDto) {
		String id = employeeDto.getId();
		@SuppressWarnings("deprecation")
		Criteria crit = getSession().createCriteria(ManagerMasterDo.class);
		crit.add(Restrictions.eq("employeeId", id));
		crit.add(Restrictions.eq("status", "ACTIVE"));
		@SuppressWarnings("unchecked")
		List<ManagerMasterDo> results = crit.list();
		System.out.println(results);
		List<ManagerDetailsDto> managerList = new ArrayList<>();
		for (ManagerMasterDo b : results) {
			ManagerDetailsDto manager = new ManagerDetailsDto();
			EmployeeMasterDo empMasterDo = getSession().get(EmployeeMasterDo.class, b.getManagerId());
			System.out.println(empMasterDo);
			manager.setFirstName(empMasterDo.getFirstName());
			manager.setLastName(empMasterDo.getLastName());
			manager.setManagerType(b.getManagerType());
			manager.setEmailId(empMasterDo.getEmailId());
			managerList.add(manager);

		}

		return managerList;
	}

	@Override
	public boolean verifyEmail(EmployeeDto employeeDto) {
		/*
		 * Criteria crit = getSession().createCriteria(EmployeeDo.class);
		 * crit.add(Restrictions.eq("email", employeeDto.getEmail())); EmployeeDo edo =
		 * (EmployeeDo) crit.uniqueResult(); if (edo == null) return false; return true;
		 */
		String hql = "FROM EmployeeDo WHERE email=:email";
		Query query = getSession().createQuery(hql);
		query.setParameter("email", employeeDto.getEmail());
		EmployeeDo edo = (EmployeeDo) query.getSingleResult();
		if (edo == null)
			return false;
		return true;
	}

	@Override
	public void updatePassword(EmployeeDto employeeDto) {
		String hql = "UPDATE EmployeeDo SET password=:password WHERE email=:email";

		Query query = getSession().createQuery(hql);
		query.setParameter("password", employeeDto.getPassword());
		query.setParameter("email", employeeDto.getEmail());
		query.executeUpdate();
	}

	// Checking whether employee is manager or not
	@Override
	public boolean verifyEmployeeType(String empId) {
		@SuppressWarnings("deprecation")
		Criteria crit = getSession().createCriteria(ManagerMasterDo.class);
		crit.add(Restrictions.eq("managerId", empId));
		@SuppressWarnings("unchecked")
		List<ManagerMasterDo> mdo = crit.list();
		if (mdo.isEmpty()) {
			return false;
		}
		return true;
	}

	// Getting list of employee under manager
	@Override
	public List<EmployeeListDto> getEmployeeList(String empId) {
		List<EmployeeListDto> employees = new ArrayList<>();
		// Adding details of manager also to the list
		EmployeeListDto employee1 = new EmployeeListDto();
		employee1.setId(empId);
		@SuppressWarnings("deprecation")
		Criteria crit2 = getSession().createCriteria(EmployeeMasterDo.class);
		crit2.add(Restrictions.eq("id", empId));
		EmployeeMasterDo ed2 = (EmployeeMasterDo) crit2.uniqueResult();
		employee1.setName(ed2.getFirstName() + " " + ed2.getLastName());
		employees.add(employee1);

		// Getting the list of employees under the manager
		@SuppressWarnings("deprecation")
		Criteria crit = getSession().createCriteria(ManagerMasterDo.class);
		crit.add(Restrictions.eq("managerId", empId));
		@SuppressWarnings("unchecked")
		List<ManagerMasterDo> mdo = crit.list();

		for (ManagerMasterDo m : mdo) {
			EmployeeListDto employee = new EmployeeListDto();
			employee.setId(m.getEmployeeId());
			@SuppressWarnings("deprecation")
			Criteria crit1 = getSession().createCriteria(EmployeeMasterDo.class);
			crit1.add(Restrictions.eq("id", m.getEmployeeId()));
			EmployeeMasterDo ed = (EmployeeMasterDo) crit1.uniqueResult();
			employee.setName(ed.getFirstName() + " " + ed.getLastName());
			employees.add(employee);
		}

		return employees;
	}

}
