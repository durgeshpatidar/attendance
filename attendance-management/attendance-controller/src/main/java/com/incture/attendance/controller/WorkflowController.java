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

	@PostMapping("/add")
	public ResponseDto addWorkflow(@RequestBody WorkflowTaskDto workflowtaskDto) {
		return workflowService.addWorkflow(workflowtaskDto);

	}

	@GetMapping("/request")
	public ResponseDto getRequestDetails(@RequestParam String empId) {
		return workflowService.getRequestDetails(empId);

	}

	@GetMapping("/task")
	public ResponseDto getTaskDetails(@RequestParam String managerId) {
		return workflowService.getTaskDetails(managerId);
	}

	@PostMapping("/task/update")
	public ResponseDto updateStatus(@RequestParam String status, String comment, String workflowId) {
		return workflowService.updateStatus(status, comment, workflowId);
	}

	@PostMapping("/task/update-address-status")
	public ResponseDto updateAddressStatus(@RequestParam String status, String comment, String workflowId) {
		return workflowService.updateAddressStatus(status, comment, workflowId);
	}
}