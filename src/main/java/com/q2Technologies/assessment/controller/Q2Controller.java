package com.q2Technologies.assessment.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.q2Technologies.assessment.service.KaryawanService;
import com.q2Technologies.assessment.service.TimesheetService;

@RestController
@RequestMapping("/assessment-q2")
public class Q2Controller {
	@Autowired
	KaryawanService karyawanService;
	
	@Autowired
	TimesheetService timesheetService;
	
	@RequestMapping(value = "/list-karyawan", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getListKaryawan(){
		Map<String, Object> response = new HashMap<String, Object>();
		response = karyawanService.getList();
		return response;
	}
	
	@RequestMapping(value = "/list-timesheet", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getListTimesheet(){
		Map<String, Object> response = new HashMap<String, Object>();
		response = timesheetService.getList();
		return response;
	}
	
	@RequestMapping(value = "/summary-weekly-salary", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> summaryWeeklySalary(){
		Map<String, Object> response = new HashMap<String, Object>();
		response = karyawanService.getSummaryWeeklySalary();
		return response;
	}
}
