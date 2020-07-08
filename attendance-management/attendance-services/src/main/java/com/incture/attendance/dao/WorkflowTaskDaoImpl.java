package com.incture.attendance.dao;

import org.springframework.stereotype.Repository;
import org.hibernate.Session;

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
	Session session = getSession();


	@Override
	protected WorkflowTaskDo importDto(WorkflowTaskDto workflowtaskDto) {
		WorkflowTaskDo entity = null;
		entity.setId(workflowtaskDto.getId());
		entity.setManager_tracking(session.get(ManagerMasterDo.class,workflowtaskDto.getManagerId()));
		entity.setEmp_tracking(session.get(EmployeeDo.class, workflowtaskDto.getEmpId()));
		entity.setComment(workflowtaskDto.getComment());
		entity.setDescription(workflowtaskDto.getDescription());
		entity.setRequestdate(workflowtaskDto.getRequestdate());
		entity.setStatus(workflowtaskDto.getStatus());	
		return entity;
		
		}
	
	@Override
	protected WorkflowTaskDto exportDto(WorkflowTaskDo entity) {
		WorkflowTaskDto dto = null;
		dto.setId(entity.getId());
		dto.setEmpId(entity.getEmp_tracking().getId());
		dto.setManagerId(entity.getManger_tracking().getId());
		dto.setDescription(entity.getDescription());
		dto.setStatus(entity.getStatus());
		dto.setRequestdate(entity.getRequestdate());
		dto.setComment(entity.getComment());
		return dto;
	}
	@Override
	public void addworkflowtask(WorkflowTaskDto workflowtaskdto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateworklfowtask(WorkflowTaskDto workflowtaskdto) {
		// TODO Auto-generated method stub
		
		//
		
	}
}

