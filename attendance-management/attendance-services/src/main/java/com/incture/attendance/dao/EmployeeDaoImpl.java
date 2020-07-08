package com.incture.attendance.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


import com.incture.attendance.dto.EmployeeDto;
import com.incture.attendance.dto.EmployeeMasterDto;
import com.incture.attendance.dto.ManagerDetailsDto;
import com.incture.attendance.dto.ProfileDto;
import com.incture.attendance.entities.EmployeeDo;
import com.incture.attendance.entities.EmployeeMasterDo;
import com.incture.attendance.entities.ManagerMasterDo;
import com.incture.attendance.dto.ManagerMasterDto;

@Repository("EmployeeDaoImpl")
public class EmployeeDaoImpl extends BaseDao<EmployeeDo, EmployeeDto> implements EmployeeDao {

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
	//for save employee data
	@Override
	public void saveEmployeeData(EmployeeDto employeeDto) {

		getSession().save(importDto(employeeDto));
	}

	//for verifying employee email and password
	@Override
	public boolean verifyIdPass(EmployeeDto employeeDto) {
		
		Query q=getSession().createNativeQuery("SELECT ID FROM EMPLOYEE WHERE EMAIL='"+employeeDto.getEmail()+"' AND PASSWORD='"+employeeDto.getPassword()+"';");
		@SuppressWarnings("unchecked")
		List<Object> l=(List<Object>)q.getResultList();
		int size=l.size();
		if(size!=0)
			return true;
		else
			return false;
	}
	//for checking employee email is exist or not and employee is active or not
	@Override
	public boolean isValidUser(EmployeeDto employeeDto)
	{
		Query q=getSession().createNativeQuery("SELECT ID FROM EMPLOYEE_MASTER WHERE EMAIL='"+employeeDto.getEmail()+"' AND STATUS='ACTIVE';");
		System.out.println("email of employee:"+employeeDto.getEmail());
		@SuppressWarnings("unchecked")
		List<Object> l=(List<Object>)q.getResultList();
		employeeDto.setId((String)l.get(0));
		int size=l.size();
		System.out.println("size : "+size);
		if(size!=0)
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
		return profileDto;

	}
//For displaying manager details
	@Override
	public List<ManagerDetailsDto> managerDetails(EmployeeDto employeeDto) {
		String id = employeeDto.getId();
		Date d = new Date(9999,12,12);
		Criteria crit = getSession().createCriteria(ManagerMasterDo.class);
        crit.add(Restrictions.eq("employeeId",id));
        crit.add(Restrictions.eq("endDate", d));
        List<ManagerMasterDo> results = crit.list();
        System.out.println(results);
        List<ManagerDetailsDto> managerList = new ArrayList<>();
        for(ManagerMasterDo b: results) {
        	ManagerDetailsDto manager = new ManagerDetailsDto();
        	EmployeeMasterDo empMasterDto = getSession().get(EmployeeMasterDo.class, b.getManagerId());
        	System.out.println(empMasterDto);
        	manager.setFirstName(empMasterDto.getFirstName());
        	manager.setLastName(empMasterDto.getLastName());
        	manager.setManagerType(b.getManagerType());
        	managerList.add(manager);
        	
        	
        }
        
		return managerList;
	}

	
}
