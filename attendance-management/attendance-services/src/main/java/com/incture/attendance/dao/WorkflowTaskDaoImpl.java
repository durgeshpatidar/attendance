package com.incture.attendance.dao;

import org.springframework.stereotype.Repository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.incture.attendance.dto.TrackingDto;
import com.incture.attendance.dto.WorkflowTaskDto;
import com.incture.attendance.entities.ManagerMasterDo;
import com.incture.attendance.entities.TrackingDo;
import com.incture.attendance.entities.AddressDo;
import com.incture.attendance.entities.EmployeeDo;
import com.incture.attendance.entities.WorkflowTaskDo;
import javax.persistence.Query;

@Repository("WorkflowTaskDaoImpl")
public class WorkflowTaskDaoImpl extends BaseDao<WorkflowTaskDo, WorkflowTaskDto> implements WorkflowTaskDao {

	@Override
	protected WorkflowTaskDo importDto(WorkflowTaskDto workflowtaskDto) {
		WorkflowTaskDo entity = new WorkflowTaskDo();
		entity.setId(workflowtaskDto.getId());
		Criteria criteria=getSession().createCriteria(ManagerMasterDo.class);
		criteria.add(Restrictions.eq("employeeId",workflowtaskDto.getEmpId()));
		criteria.add(Restrictions.eq("managerType","PROJECT"));
//we have to restrictions on enddate
		ManagerMasterDo mdo=(ManagerMasterDo)criteria.uniqueResult();
		
		entity.setManager(getSession().get(ManagerMasterDo.class, mdo.getManagerId()));
		entity.setEmployee(getSession().get(EmployeeDo.class, workflowtaskDto.getEmpId()));
		entity.setComment(workflowtaskDto.getComment());
		entity.setDescription(workflowtaskDto.getDescription());
		entity.setRequestdate(workflowtaskDto.getRequestdate());
		entity.setStatus(workflowtaskDto.getStatus());
		return entity;

	}

	@Override
	protected WorkflowTaskDto exportDto(WorkflowTaskDo entity) {
		WorkflowTaskDto dto = new WorkflowTaskDto();
		dto.setId(entity.getId());
		dto.setEmpId(entity.getEmployee().getId());
		dto.setManagerId(entity.getManager().getId());
		dto.setDescription(entity.getDescription());
		dto.setStatus(entity.getStatus());
		dto.setRequestdate(entity.getRequestdate());
		dto.setComment(entity.getComment());
		return dto;
	}

	@Override
	public void addWorkflowTask(WorkflowTaskDto workflowtaskdto) {
		 getSession().save(importDto(workflowtaskdto));
		
	}

	@Override
	public void updateWorkflowTask(WorkflowTaskDto workflowtaskdto) {
		// TODO Auto-generated method stub
		
	}
}
