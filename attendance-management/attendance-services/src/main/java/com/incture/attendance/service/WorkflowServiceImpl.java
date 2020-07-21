package com.incture.attendance.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.incture.attendance.dao.WorkflowTaskDao;
import com.incture.attendance.dto.WorkflowTaskDto;
import com.incture.attendance.utils.ResponseDto;

@Service
@Transactional
public class WorkflowServiceImpl implements WorkflowService {
	@Autowired
	private WorkflowTaskDao workflowtaskDao;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public ResponseDto addWorkflow(WorkflowTaskDto worklfowtaskDto) {
		logger.info("WorkflowServiceImpl | addWorkflow | Execution start input " + worklfowtaskDto);

		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatus(Boolean.TRUE);
		responseDto.setStatusCode(200);
		try {
			workflowtaskDao.addWorkflowTask(worklfowtaskDto);
			responseDto.setMessage("Task Created Successfully For " + worklfowtaskDto.getDescription() + "!");

		} catch (Exception e) {

			logger.error("WorkflowServiceImpl | addWorkflow  | Exception " + e.getMessage());
			responseDto.setStatus(Boolean.FALSE);
			responseDto.setStatusCode(500);
			responseDto.setMessage(e.getMessage());

		}

		logger.info("WorkflowServiceImpl | addWorkflow   | Execution end ouput " + responseDto);

		return responseDto;
	}

	@Override
	public ResponseDto getRequestDetails(String empId) {
		logger.info("EmployeeServiceImpl | getRequestDetails | Execution start input " + empId);

		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatus(Boolean.TRUE);
		responseDto.setStatusCode(200);
		try {
			List<WorkflowTaskDto> workflow = workflowtaskDao.getRequestDetails(empId);
			responseDto.setData(workflow);
			if (workflow.isEmpty())
				responseDto.setMessage("Workflow Request Not Found!");
			else
				responseDto.setMessage("All Workflow Request Displayed Successfully!");

		} catch (Exception e) {

			logger.error("EmployeeServiceImpl | getRequestDetails | Exception " + e.getMessage());
			responseDto.setStatus(Boolean.FALSE);
			responseDto.setStatusCode(500);
			responseDto.setMessage(e.getMessage());

		}

		logger.info("EmployeeServiceImpl | getRequestDetails | Execution end ouput " + responseDto);

		return responseDto;

	}

	@Override
	public ResponseDto getTaskDetails(String managerId) {
		logger.info("WorklfowServiceImpl | getTaskDetails | Execution start input " + managerId);
		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatus(Boolean.TRUE);
		responseDto.setStatusCode(200);
		try {
			List<WorkflowTaskDto> workflow = workflowtaskDao.getTaskDetails(managerId);
			responseDto.setData(workflow);
			if (workflow.isEmpty())
				responseDto.setMessage("Workflow Task Not Found!");
			else
				responseDto.setMessage("All Workflow Task Displayed Successfully!");

		} catch (Exception e) {

			logger.error("WorklfowServiceImpl | getTaskDetails | Exception " + e.getMessage());
			responseDto.setStatus(Boolean.FALSE);
			responseDto.setStatusCode(500);
			responseDto.setMessage(e.getMessage());

		}

		logger.info("WorklfowServiceImpl | getTaskDetails | Execution end ouput " + responseDto);

		return responseDto;

	}

	@Override
	public ResponseDto updateStatus(String status, String comment, String workflowId) {
		logger.info("WorkflowServiceImpl | updateStatus | Execution start input " + status + " " + comment + " "
				+ workflowId);

		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatus(Boolean.TRUE);
		responseDto.setStatusCode(200);
		try {
			workflowtaskDao.updateStatus(status, comment, workflowId);
			responseDto.setMessage("Workflow task updated");

		} catch (Exception e) {

			logger.error("WorkflowServiceImpl | updateStatus | Exception " + e.getMessage());
			responseDto.setStatus(Boolean.FALSE);
			responseDto.setStatusCode(500);
			responseDto.setMessage(e.getMessage());

		}

		logger.info("WorkflowServiceImpl | updateStatus   | Execution end ouput " + responseDto);

		return responseDto;

	}

	@Override
	public ResponseDto getWorkflowDetails(String empId) {
		logger.info("EmployeeServiceImpl | getWorkflowDetails | Execution start input " + empId);

		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatus(Boolean.TRUE);
		responseDto.setStatusCode(200);
		try {
			List<WorkflowTaskDto> workflow = workflowtaskDao.getWorkflowDetails(empId);
			responseDto.setData(workflow);
			if (workflow.isEmpty())
				responseDto.setMessage("Workflow Request Not Found!");
			else
				responseDto.setMessage("All Workflow Request Displayed Successfully!");

		} catch (Exception e) {

			logger.error("EmployeeServiceImpl | getWorkflowDetails | Exception " + e.getMessage());
			responseDto.setStatus(Boolean.FALSE);
			responseDto.setStatusCode(500);
			responseDto.setMessage(e.getMessage());

		}

		logger.info("EmployeeServiceImpl | getWorkflowDetails | Execution end ouput " + responseDto);

		return responseDto;

	}
}
