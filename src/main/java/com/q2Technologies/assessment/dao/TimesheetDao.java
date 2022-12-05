package com.q2Technologies.assessment.dao;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.q2Technologies.assessment.model.Timesheet;

public interface TimesheetDao extends Repository<Timesheet, Integer> {
	List<Timesheet> getAll() throws Exception;
	List<Timesheet> getByKaryawanId(int idKaryawan) throws Exception;
}
