package com.incture.attendance.service;

import com.incture.attendance.dto.WorkflowTaskDto;
import com.incture.attendance.utils.ResponseDto;


public interface WorkflowService {
	ResponseDto addWorkflow(WorkflowTaskDto workflowtaskdto);

	ResponseDto getRequestDetails(String empId);
	
	ResponseDto getTaskDetails(String managerId);
	
}
