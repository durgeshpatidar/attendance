package com.incture.attendance.dao;

import org.springframework.stereotype.Repository;
import org.hibernate.query.Query;

import com.incture.attendance.dto.WorkflowTaskDto;
import com.incture.attendance.entities.ManagerMasterDo;
import com.incture.attendance.entities.TrackingDo;
import com.incture.attendance.entities.AddressDo;
import com.incture.attendance.entities.EmployeeDo;
import com.incture.attendance.entities.EmployeeMasterDo;
import com.incture.attendance.entities.WorkflowTaskDo;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("rawtypes")
@Repository("WorkflowTaskDaoImpl")
public class WorkflowTaskDaoImpl extends BaseDao<WorkflowTaskDo, WorkflowTaskDto> implements WorkflowTaskDao {

	@Override
	protected WorkflowTaskDo importDto(WorkflowTaskDto workflowtaskDto) {
		WorkflowTaskDo entity = new WorkflowTaskDo();
		entity.setId(workflowtaskDto.getId());
		System.out.println("employee id : " + workflowtaskDto.getEmpId());

		// Taking manager details from master to send workflow details.
		String hql = "from ManagerMasterDo where employeeId =:employeeId and status =:status and managerType =:managerType";
		Query query = getSession().createQuery(hql);
		query.setParameter("employeeId", workflowtaskDto.getEmpId());
		query.setParameter("status", "ACTIVE");
		query.setParameter("managerType", "PROJECT");
		ManagerMasterDo mdo = null;
		try {
			mdo = (ManagerMasterDo) query.getSingleResult();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

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

	// Updating workflow by manager.
	@Override
	public void updateStatus(String status, String comment, String workflowId) {

		String hql = "from WorkflowTaskDo where id =:id";
		Query query = getSession().createQuery(hql);
		query.setParameter("id", workflowId);
		WorkflowTaskDo current = (WorkflowTaskDo) query.getSingleResult();
		current.setComment(comment);
		current.setStatus(status);

		// Setting status returned by manager in tracking table and address table also.
		// Checking in tracking table

		String hql1 = "from TrackingDo where id =:id";
		Query query1 = getSession().createQuery(hql1);
		query1.setParameter("id", workflowId);
		try {
			TrackingDo track = (TrackingDo) query1.getSingleResult();
			if (track != null) {
				track.setStatus(status);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// Checking in address table.

		String hql2 = "from AddressDo where id =:id";
		Query query2 = getSession().createQuery(hql2);
		query2.setParameter("id", workflowId);
		try {
			AddressDo add = (AddressDo) query2.getSingleResult();
			if (add != null) {
				add.setStatus(status);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	// Getting workflow details for employee.
	@Override
	public List<WorkflowTaskDto> getRequestDetails(String empId) {

		String hql = "from WorkflowTaskDo where employee =:employee order by requestdate desc";
		Query query = getSession().createQuery(hql);
		query.setParameter("employee", getSession().get(EmployeeDo.class, empId));
		query.setMaxResults(20);
		@SuppressWarnings("unchecked")
		List<WorkflowTaskDo> workflow = query.getResultList();

		// Getting employee name
		String hql1 = "from EmployeeMasterDo where id =:id";
		Query query1 = getSession().createQuery(hql1);
		query1.setParameter("id", empId);
		EmployeeMasterDo emp = null;
		try {
			emp = (EmployeeMasterDo) query1.getSingleResult();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		List<WorkflowTaskDto> request = new ArrayList<>();
		for (WorkflowTaskDo t : workflow) {
			WorkflowTaskDto newWorkflow = new WorkflowTaskDto();
			newWorkflow.setEmpId(empId);
			newWorkflow.setEmpName(emp.getFirstName() + " " + emp.getLastName());
			newWorkflow.setComment(t.getComment());
			newWorkflow.setDescription(t.getDescription());
			newWorkflow.setId(t.getId());
			newWorkflow.setQuerytype(t.getQuerytype());
			newWorkflow.setRequestDate(t.getRequestdate());
			newWorkflow.setStatus(t.getStatus());
			request.add(newWorkflow);
		}
		return request;
	}

	// Getting workflow details for manager.
	@Override
	public List<WorkflowTaskDto> getTaskDetails(String managerId) {
		// Getting all rows from workflowmasterdo with given manager id

		String hql = "from WorkflowTaskDo where managerId =:managerId and status =:status order by requestdate desc";
		Query query = getSession().createQuery(hql);
		query.setParameter("managerId", managerId);
		query.setParameter("status", "Pending");
		query.setMaxResults(20);
		@SuppressWarnings("unchecked")
		List<WorkflowTaskDo> workflow = query.getResultList();

		List<String> empList = new ArrayList<String>();
		for (WorkflowTaskDo wtd : workflow)
			empList.add(wtd.getEmployee().getId());

		// Getting employee for each workflow
		String hql1 = "from EmployeeMasterDo where id in(:id)";
		Query query1 = getSession().createQuery(hql1);
		query1.setParameterList("id", empList);
		@SuppressWarnings("unchecked")
		List<EmployeeMasterDo> emp = query1.list();

		List<WorkflowTaskDto> request = new ArrayList<>();
		for (WorkflowTaskDo t : workflow) {
			WorkflowTaskDto newWorkflow = new WorkflowTaskDto();
			String name = null;
			String id = null;
			EmployeeDo emp1 = t.getEmployee();
			for (EmployeeMasterDo e : emp) {
				if (e.getId().equals(emp1.getId())) {
					name = e.getFirstName() + " " + e.getLastName();
					id = e.getId();
					break;
				}
			}

			newWorkflow.setEmpName(name);
			newWorkflow.setEmpId(id);
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

	// Workflow details for home page.
	@Override
	public List<WorkflowTaskDto> getWorkflowDetails(String empId) {

		String hql = "from WorkflowTaskDo where employee =:employee order by requestdate desc";
		Query query = getSession().createQuery(hql);
		query.setParameter("employee", getSession().get(EmployeeDo.class, empId));
		query.setMaxResults(4);
		@SuppressWarnings("unchecked")
		List<WorkflowTaskDo> workflow = query.getResultList();

		// Getting employee name

		String hql1 = "from EmployeeMasterDo where id =:id";
		Query query1 = getSession().createQuery(hql1);
		query1.setParameter("id", empId);
		EmployeeMasterDo emp = null;
		try {
			emp = (EmployeeMasterDo) query1.getSingleResult();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		List<WorkflowTaskDto> request = new ArrayList<>();
		for (WorkflowTaskDo t : workflow) {
			WorkflowTaskDto newWorkflow = new WorkflowTaskDto();
			newWorkflow.setEmpId(empId);
			newWorkflow.setEmpName(emp.getFirstName() + " " + emp.getLastName());
			newWorkflow.setComment(t.getComment());
			newWorkflow.setDescription(t.getDescription());
			newWorkflow.setId(t.getId());
			newWorkflow.setQuerytype(t.getQuerytype());
			newWorkflow.setRequestDate(t.getRequestdate());
			newWorkflow.setStatus(t.getStatus());
			request.add(newWorkflow);
		}
		return request;
	}
}
