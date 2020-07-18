package com.incture.attendance.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.incture.attendance.dto.EmployeeDto;
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

		Query q = getSession().createNativeQuery("SELECT ID FROM EMPLOYEE WHERE EMAIL='" + employeeDto.getEmail()
				+ "' AND PASSWORD='" + employeeDto.getPassword() + "';");
		@SuppressWarnings("unchecked")
		List<Object> l = (List<Object>) q.getResultList();
		try {
			employeeDto.setId((String) l.get(0));
		} catch (Exception e) {
			System.out.println(e);
		}
		if (l.isEmpty())
			return false;
		return true;
	}

	// for checking employee email is exist or not and employee is active or not
	@Override
	public boolean isValidUser(EmployeeDto employeeDto) {
		Query q = getSession().createNativeQuery(
				"SELECT ID FROM EMPLOYEE_MASTER WHERE EMAIL='" + employeeDto.getEmail() + "' AND STATUS='ACTIVE';");
		System.out.println("email of employee:" + employeeDto.getEmail());
		@SuppressWarnings("unchecked")
		List<Object> l = (List<Object>) q.getResultList();
		employeeDto.setId((String) l.get(0));
		int size = l.size();
		System.out.println("size : " + size);
		if (size != 0)
			return true;
		else
			return false;
	}

//for displaying profile details
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

//For displaying manager details
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

	static char[] getPassword() {
		String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String Small_chars = "abcdefghijklmnopqrstuvwxyz";
		String numbers = "0123456789";
		String symbols = "!@#$%^&*_=+-/.?<>)";

		String values = Capital_chars + Small_chars + numbers + symbols;

		// Using random method
		Random rndm_method = new Random();

		char[] password = new char[8];

		for (int i = 0; i < 8; i++) {
			password[i] = values.charAt(rndm_method.nextInt(values.length()));

		}
		return password;
	}

	@Override
	public boolean forgotPassword(EmployeeDto employeeDto) {
		@SuppressWarnings("deprecation")
		Criteria crit = getSession().createCriteria(EmployeeDo.class);
		crit.add(Restrictions.eq("email", employeeDto.getEmail()));
		EmployeeDo edo = (EmployeeDo) crit.uniqueResult();
		if (edo == null)
			return false;
		String newPassword = new String(getPassword());
		//code for send email and update new pasword in database 
		//for update password we will create another method updatePassword()
		return true;
	}

}
