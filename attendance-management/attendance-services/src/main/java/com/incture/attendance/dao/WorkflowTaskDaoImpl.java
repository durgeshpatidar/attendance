package com.incture.attendance.dao;

import org.springframework.stereotype.Repository;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.incture.attendance.dto.WorkflowTaskDto;
import com.incture.attendance.entities.ManagerMasterDo;
import com.incture.attendance.entities.EmployeeDo;
import com.incture.attendance.entities.EmployeeMasterDo;
import com.incture.attendance.entities.WorkflowTaskDo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository("WorkflowTaskDaoImpl")
public class WorkflowTaskDaoImpl extends BaseDao<WorkflowTaskDo, WorkflowTaskDto> implements WorkflowTaskDao {

	@Override
	protected WorkflowTaskDo importDto(WorkflowTaskDto workflowtaskDto) {
		WorkflowTaskDo entity = new WorkflowTaskDo();
		entity.setId(workflowtaskDto.getId());
		@SuppressWarnings("deprecation")
		Criteria criteria = getSession().createCriteria(ManagerMasterDo.class);
		criteria.add(Restrictions.eq("employeeId", workflowtaskDto.getEmpId()));
		criteria.add(Restrictions.eq("managerType", "PROJECT"));
		// we have to add restrictions on enddate
		ManagerMasterDo mdo = (ManagerMasterDo) criteria.uniqueResult();

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

	@Override
	public List<WorkflowTaskDto> getRequestDetails(String empId) {
		@SuppressWarnings("deprecation")
		Criteria criteria = getSession().createCriteria(WorkflowTaskDo.class);
		criteria.add(Restrictions.eq("employee", getSession().get(EmployeeDo.class, empId)));
		@SuppressWarnings("unchecked")
		List<WorkflowTaskDo> workflow = criteria.list();
		// Getting employee name
		@SuppressWarnings("deprecation")
		Criteria crit = getSession().createCriteria(EmployeeMasterDo.class);
		crit.add(Restrictions.eq("id", empId));
		EmployeeMasterDo emp = (EmployeeMasterDo) crit.uniqueResult();
		List<WorkflowTaskDto> request = new ArrayList<>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		for (WorkflowTaskDo t : workflow) {
			WorkflowTaskDto newWorkflow = new WorkflowTaskDto();
			newWorkflow.setEmpId(empId);
			newWorkflow.setEmpName(emp.getFirstName() + " " + emp.getLastName());
			newWorkflow.setComment(t.getComment());
			newWorkflow.setDescription(t.getDescription());
			newWorkflow.setId(t.getId());
			try {
				newWorkflow.setRequestdate(formatter.parse(t.getRequestdate().toString()));
			} catch (ParseException e) {
				newWorkflow.setRequestdate(t.getRequestdate());
				e.printStackTrace();
			}
			newWorkflow.setStatus(t.getStatus());
			request.add(newWorkflow);
		}
		return request;
	}

	@Override
	public List<WorkflowTaskDto> getTaskDetails(String managerId) {
		@SuppressWarnings("deprecation")
		Criteria criteria = getSession().createCriteria(WorkflowTaskDo.class);
		criteria.add(Restrictions.eq("employee", getSession().get(EmployeeDo.class, managerId)));
		@SuppressWarnings("unchecked")
		List<WorkflowTaskDo> workflow = criteria.list();
		// Getting employee name
		@SuppressWarnings("deprecation")
		Criteria crit = getSession().createCriteria(EmployeeMasterDo.class);
		crit.add(Restrictions.eq("id", managerId));
		EmployeeMasterDo emp = (EmployeeMasterDo) crit.uniqueResult();
		List<WorkflowTaskDto> request = new ArrayList<>();
		for (WorkflowTaskDo t : workflow) {
			WorkflowTaskDto newWorkflow = new WorkflowTaskDto();
			newWorkflow.setManagerId(managerId);
			newWorkflow.setEmpName(emp.getFirstName() + " " + emp.getLastName());
			newWorkflow.setComment(t.getComment());
			newWorkflow.setDescription(t.getDescription());
			newWorkflow.setId(t.getId());
			newWorkflow.setStatus(t.getStatus());
			request.add(newWorkflow);
		}
		return request;
	}
}
