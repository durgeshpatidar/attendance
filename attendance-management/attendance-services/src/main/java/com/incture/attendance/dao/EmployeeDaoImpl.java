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
	@Override
	public boolean isValidUser(EmployeeDto employeeDto)
	{
		Query q=getSession().createNativeQuery("SELECT *FROM EMPLOYEE_MASTER WHERE ID='"+employeeDto.getId()+"' AND STATUS='ACTIVE';");
		@SuppressWarnings("unchecked")
		List<Object> l=(List<Object>)q.getResultList();
		int size=l.size();
		System.out.println("size : "+size);
		if(size!=0)
		{
			Query q2=getSession().createNativeQuery("SELECT *FROM EMPLOYEE WHERE ID='"+employeeDto.getId()+"' AND PASSWORD='"+employeeDto.getPassword()+"';");
			@SuppressWarnings("unchecked")
			List<Object> l1=(List<Object>)q2.getResultList();
			int sizeL1=l1.size();
			if(sizeL1!=0)
			return true;
		}
		return false;
	}

}
