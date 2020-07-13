package com.incture.attendance.dao;

import java.util.List;

import com.incture.attendance.dto.WorkflowTaskDto;

public interface WorkflowTaskDao {

	void addWorkflowTask(WorkflowTaskDto workflowtaskdto);

	void updateWorkflowTask(WorkflowTaskDto workflowtaskdto);

	List<WorkflowTaskDto> getRequestDetails(String empId);
	List<WorkflowTaskDto> getTaskDetails(String managerId);

}
