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
			entity.setPhone_no(employeeDto.getPhone_no());
			entity.setPassword(employeeDto.getPassword());

		}
		return entity;
	}

	@Override
	protected EmployeeDto exportDto(EmployeeDo employeeDo) {
		EmployeeDto dto = null;
		if (employeeDo != null) {
			dto = new EmployeeDto();
			dto.setId(employeeDo.getId());
			dto.setPhone_no(employeeDo.getPhone_no());
			dto.setPassword(employeeDo.getPassword());
		}
		return dto;
	}

	@Override
	public void saveEmployeeData(EmployeeDto employeeDto) {

		getSession().save(importDto(employeeDto));
	}

	@Override
	public boolean verifyEmployeeData(EmployeeDto employeeDto) {
		
		Query q=getSession().createNativeQuery("SELECT *FROM EMPLOYEE_MASTER WHERE ID='"+employeeDto.getId()+"' AND STATUS='ACTIVE';");
		System.out.println("empid of employee:"+employeeDto.getId());
		@SuppressWarnings("unchecked")
		List<Object> l=(List<Object>)q.getResultList();
		int size=l.size();
		System.out.println("size : "+size);
		if(size!=0)
			return true;
		else
			return false;
	}
	//for user login
	public boolean isValidUser(EmployeeDto employeeDto)
	{
		Query q=getSession().createNativeQuery("SELECT *FROM EMPLOYEE_MASTER WHERE ID='"+employeeDto.getId()+"' AND STATUS='ACTIVE';");
		//if id and status will be active in master data table then we will verify id and password in employee table
		System.out.println("empid of employee:"+employeeDto.getId());
		@SuppressWarnings("unchecked")
		List<Object> l=(List<Object>)q.getResultList();
		int size=l.size();
		System.out.println("size : "+size);
		if(size!=0)
			return true;
		else
			return false;
	}

}
