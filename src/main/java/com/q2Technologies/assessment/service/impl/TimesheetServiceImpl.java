package com.q2Technologies.assessment.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.q2Technologies.assessment.dao.TimesheetDao;
import com.q2Technologies.assessment.model.Timesheet;
import com.q2Technologies.assessment.service.TimesheetService;

@Service
public class TimesheetServiceImpl  implements TimesheetService{

	@Autowired
	TimesheetDao timesheetDao;
	
	@Transactional
	public Map<String, Object> getList() {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			List<Timesheet> timesheet = new ArrayList<Timesheet>();
			timesheet = timesheetDao.getAll();
			
			if (timesheet.size() < 1) {
				result.put("outError", 1);
				result.put("outMessage", "No Data Faund");
			}else {
				for (Timesheet time : timesheet) {
					int idKar = time.getIdKaryawan().getIdKaryawan();
				}
				result.put("outError", 0);
				result.put("outMessage", "success");
				result.put("outData", timesheet);
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return result;
	}

}
