package com.incture.attendance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.incture.attendance.dto.WorkflowTaskDto;
import com.incture.attendance.service.WorkflowService;
import com.incture.attendance.utils.ResponseDto;

@RestController
@RequestMapping("/workflow")
public class WorkflowController {

	@Autowired
	private WorkflowService workflowService;

	@GetMapping
	public String TestApi() {
		return "Done";
	}

	// Adding workflow.
	@PostMapping("/add")
	public ResponseDto addWorkflow(@RequestBody WorkflowTaskDto workflowtaskDto) {
		return workflowService.addWorkflow(workflowtaskDto);

	}

	// Getting workflow details for employee.
	@GetMapping("/request")
	public ResponseDto getRequestDetails(@RequestParam String empId) {
		return workflowService.getRequestDetails(empId);

	}

	// Getting workflow details for home page.
	@GetMapping("/details-home")
	public ResponseDto getWorkflowDetails(@RequestParam String empId) {
		return workflowService.getWorkflowDetails(empId);

	}

	// Getting workflow details for manager.
	@GetMapping("/task")
	public ResponseDto getTaskDetails(@RequestParam String managerId) {
		return workflowService.getTaskDetails(managerId);
	}

	// Updating status of workflow by manager.
	@PostMapping("/task/update")
	public ResponseDto updateStatus(@RequestBody WorkflowTaskDto wdto) {
		String status = wdto.getStatus();
		String comment = wdto.getComment();
		String workflowId = wdto.getId();
		return workflowService.updateStatus(status, comment, workflowId);
	}

}