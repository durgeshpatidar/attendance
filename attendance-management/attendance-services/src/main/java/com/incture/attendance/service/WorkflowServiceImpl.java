package com.incture.attendance.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.incture.attendance.dao.WorkflowTaskDao;
import com.incture.attendance.dto.ManagerDetailsDto;
import com.incture.attendance.dto.ProfileDto;
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
			responseDto.setMessage("Worklfow Details added Successfully!");

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
			List<WorkflowTaskDto> workflow = WorkflowTaskDao.getRequestDetails(empId);
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

		logger.info("EmployeeServiceImpl | profileDetails | Execution end ouput " + responseDto);

		return responseDto;

		return null;
	}

}
