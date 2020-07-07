package com.incture.attendance.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.incture.attendance.dto.EmployeeDto;
import com.incture.attendance.entities.EmployeeDo;


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

	@Override
	public void saveEmployeeData(EmployeeDto employeeDto) {

		getSession().save(importDto(employeeDto));
	}

	@Override
	public boolean verifyEmployeeData(EmployeeDto employeeDto) {
		
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
	//for user login
	@Override
	public boolean isValidUser(EmployeeDto employeeDto)
	{
		
			Query q=getSession().createNativeQuery("SELECT ID FROM EMPLOYEE WHERE EMAIL='"+employeeDto.getEmail()+"' AND PASSWORD='"+employeeDto.getPassword()+"';");
			@SuppressWarnings("unchecked")
			List<Object> l=(List<Object>)q.getResultList();
			int size=l.size();
			if(size!=0)
				return true;
			else
				return false;
	}

}
