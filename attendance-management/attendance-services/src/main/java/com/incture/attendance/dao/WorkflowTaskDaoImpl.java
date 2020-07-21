package com.incture.attendance.dao;

import org.springframework.stereotype.Repository;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.incture.attendance.dto.WorkflowTaskDto;
import com.incture.attendance.entities.ManagerMasterDo;
import com.incture.attendance.entities.TrackingDo;
import com.incture.attendance.entities.AddressDo;
import com.incture.attendance.entities.EmployeeDo;
import com.incture.attendance.entities.EmployeeMasterDo;
import com.incture.attendance.entities.WorkflowTaskDo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Repository("WorkflowTaskDaoImpl")
public class WorkflowTaskDaoImpl extends BaseDao<WorkflowTaskDo, WorkflowTaskDto> implements WorkflowTaskDao {

	@Override
	protected WorkflowTaskDo importDto(WorkflowTaskDto workflowtaskDto) {
		WorkflowTaskDo entity = new WorkflowTaskDo();
		entity.setId(workflowtaskDto.getId());
		System.out.println("employee id : " + workflowtaskDto.getEmpId());
		@SuppressWarnings("deprecation")
		Criteria criteria = getSession().createCriteria(ManagerMasterDo.class);
		criteria.add(Restrictions.eq("employeeId", workflowtaskDto.getEmpId()));
		criteria.add(Restrictions.eq("status", "ACTIVE"));
		criteria.add(Restrictions.eq("managerType", "PROJECT"));
		ManagerMasterDo mdo = (ManagerMasterDo) criteria.uniqueResult();
		System.out.println("manager id : " + mdo.getManagerId());
		entity.setManagerId(mdo.getManagerId());
		entity.setEmployee(getSession().get(EmployeeDo.class, workflowtaskDto.getEmpId()));
		entity.setComment(workflowtaskDto.getComment());
		entity.setDescription(workflowtaskDto.getDescription());
		entity.setRequestdate(workflowtaskDto.getRequestDate());
		entity.setStatus(workflowtaskDto.getStatus());
		entity.setQuerytype(workflowtaskDto.getQuerytype());
		return entity;

	}

	@Override
	protected WorkflowTaskDto exportDto(WorkflowTaskDo entity) {
		WorkflowTaskDto dto = new WorkflowTaskDto();
		dto.setId(entity.getId());
		dto.setEmpId(entity.getEmployee().getId());
		dto.setManagerId(entity.getManagerId());
		dto.setDescription(entity.getDescription());
		dto.setStatus(entity.getStatus());
		dto.setRequestDate(entity.getRequestdate());
		dto.setComment(entity.getComment());
		dto.setQuerytype(entity.getQuerytype());
		return dto;
	}

	// Adding workflow
	@Override
	public void addWorkflowTask(WorkflowTaskDto workflowtaskdto) {
		getSession().save(importDto(workflowtaskdto));

	}

	// Updating workflow
	@Override
	public void updateStatus(String status, String comment, String workflowId) {
		@SuppressWarnings("deprecation")
		Criteria criteria = getSession().createCriteria(WorkflowTaskDo.class);
		criteria.add(Restrictions.eq("id", workflowId));
		WorkflowTaskDo current = (WorkflowTaskDo) criteria.uniqueResult();
		current.setComment(comment);
		current.setStatus(status);
		// Setting status returned by manager in tracking table and address table also.
		// Checking in tracking table
		@SuppressWarnings("deprecation")
		Criteria criteria1 = getSession().createCriteria(TrackingDo.class);
		criteria.add(Restrictions.eq("id", workflowId));
		TrackingDo track = (TrackingDo) criteria1.uniqueResult();
		if (track != null) {
			track.setStatus(status);
		}

		// Checking in address table.
		@SuppressWarnings("deprecation")
		Criteria criteria2 = getSession().createCriteria(AddressDo.class);
		criteria.add(Restrictions.eq("id", workflowId));
		AddressDo add = (AddressDo) criteria2.uniqueResult();
		if (add != null) {
			add.setStatus(status);
		}
	}

	// Getting workflow details for employee
	@Override
	public List<WorkflowTaskDto> getRequestDetails(String empId) {
		@SuppressWarnings("deprecation")
		Criteria criteria = getSession().createCriteria(WorkflowTaskDo.class);
		criteria.add(Restrictions.eq("employee", getSession().get(EmployeeDo.class, empId)));
		criteria.addOrder(Order.desc("requestdate"));
		criteria.setMaxResults(20);
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
			newWorkflow.setQuerytype(t.getQuerytype());
			try {
				newWorkflow.setRequestDate(formatter.parse(t.getRequestdate().toString()));
			} catch (ParseException e) {
				newWorkflow.setRequestDate(t.getRequestdate());
				e.printStackTrace();
			}
			newWorkflow.setStatus(t.getStatus());
			request.add(newWorkflow);
		}
		return request;
	}

	// Getting workflow details for manager
	@Override
	public List<WorkflowTaskDto> getTaskDetails(String managerId) {
		// Getting all rows from workflowmasterdo with given manager id
		@SuppressWarnings("deprecation")
		Criteria criteria = getSession().createCriteria(WorkflowTaskDo.class);
		criteria.add(Restrictions.eq("managerId", managerId));
		criteria.add(Restrictions.eq("status", "PENDING"));
		criteria.addOrder(Order.desc("requestdate"));
		criteria.setMaxResults(20);
		@SuppressWarnings("unchecked")
		List<WorkflowTaskDo> workflow = criteria.list();
		List<WorkflowTaskDto> request = new ArrayList<>();
		for (WorkflowTaskDo t : workflow) {
			WorkflowTaskDto newWorkflow = new WorkflowTaskDto();
			// Getting employee for each workflow
			@SuppressWarnings("deprecation")
			Criteria crit = getSession().createCriteria(EmployeeMasterDo.class);
			crit.add(Restrictions.eq("id", t.getEmployee().getId()));
			EmployeeMasterDo emp = (EmployeeMasterDo) crit.uniqueResult();
			newWorkflow.setEmpName(emp.getFirstName() + " " + emp.getLastName());
			newWorkflow.setEmpId(emp.getId());
			newWorkflow.setManagerId(managerId);
			newWorkflow.setRequestDate(t.getRequestdate());
			newWorkflow.setComment(t.getComment());
			newWorkflow.setDescription(t.getDescription());
			newWorkflow.setQuerytype(t.getQuerytype());
			newWorkflow.setStatus(t.getStatus());
			newWorkflow.setId(t.getId());
			request.add(newWorkflow);
		}
		return request;
	}

	@Override
	public List<WorkflowTaskDto> getWorkflowDetails(String empId) {
		@SuppressWarnings("deprecation")
		Criteria criteria = getSession().createCriteria(WorkflowTaskDo.class);
		criteria.add(Restrictions.eq("employee", getSession().get(EmployeeDo.class, empId)));
		criteria.addOrder(Order.desc("requestdate"));
		criteria.setMaxResults(4);
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
			newWorkflow.setQuerytype(t.getQuerytype());
			try {
				newWorkflow.setRequestDate(formatter.parse(t.getRequestdate().toString()));
			} catch (ParseException e) {
				newWorkflow.setRequestDate(t.getRequestdate());
				e.printStackTrace();
			}
			newWorkflow.setStatus(t.getStatus());
			request.add(newWorkflow);
		}
		return request;
	}
}
