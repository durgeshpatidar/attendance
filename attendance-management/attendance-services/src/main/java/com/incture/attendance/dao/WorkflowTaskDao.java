package com.incture.attendance.dao;

import com.incture.attendance.dto.WorkflowTaskDto;

public interface WorkflowTaskDao {
	
	void addWorkflowTask(WorkflowTaskDto workflowtaskdto);

	void updateWorkflowTask(WorkflowTaskDto workflowtaskdto);

}
