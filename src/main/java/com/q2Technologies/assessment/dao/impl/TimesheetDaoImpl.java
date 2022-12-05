package com.q2Technologies.assessment.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.q2Technologies.assessment.dao.TimesheetDao;
import com.q2Technologies.assessment.model.Timesheet;
import com.q2Technologies.assessment.util.DaoUtil;

@Repository
public class TimesheetDaoImpl extends  DaoUtil implements TimesheetDao{
	
	@PersistenceContext
	EntityManager em;
	
	public List<Timesheet> getAll() throws Exception {
		List<Timesheet> result = new ArrayList<Timesheet>();
		try {
			String[] required = {"Timesheet.IdKaryawan"};
			String hql = "SELECT a FROM Timesheet a ";
			Query q = (Query) em.createQuery(hql);
			if (q != null) {
            	result = q.getResultList();
            	lazyInit(required, result);
            }
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Timesheet> getByKaryawanId(int idKaryawan) throws Exception {
		List<Timesheet> result = new ArrayList<Timesheet>();
		try {
			String[] required = {"Timesheet.IdKaryawan"};
			String hql = "SELECT a FROM Timesheet a where a.idKaryawan.idKaryawan = :idKaryawan ";
			javax.persistence.Query q = em.createQuery(hql);
			q.setParameter("idKaryawan", idKaryawan);
			if (q != null) {
            	result = q.getResultList();
            	lazyInit(required, result);
            }
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
