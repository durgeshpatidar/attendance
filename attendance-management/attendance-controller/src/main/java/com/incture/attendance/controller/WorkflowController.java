package com.incture.attendance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

}