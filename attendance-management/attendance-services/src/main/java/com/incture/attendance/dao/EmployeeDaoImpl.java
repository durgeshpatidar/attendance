package com.incture.attendance.dao;

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
			entity.setName(employeeDto.getName());
		}
		return entity;
	}

	@Override
	protected EmployeeDto exportDto(EmployeeDo employeeDo) {
		EmployeeDto dto = null;
		if (employeeDo != null) {
			dto = new EmployeeDto();
			dto.setId(employeeDo.getId());
			dto.setName(employeeDo.getName());
		}
		return dto;
	}

	@Override
	public void saveEmployeeData(EmployeeDto employeeDto) {

		getSession().save(importDto(employeeDto));
	}

}
