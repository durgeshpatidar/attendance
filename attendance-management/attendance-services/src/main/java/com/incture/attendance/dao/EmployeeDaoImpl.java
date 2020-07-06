package com.incture.attendance.dao;

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
	public void verifyEmployeeData(EmployeeDto employeeDto) {
		// TODO Auto-generated method stub
		Query q=getSession().createNativeQuery("select *from employee_master where id="+employeeDto.getId());
		q.getFirstResult();
	}

}
