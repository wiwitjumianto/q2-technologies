package com.q2Technologies.assessment.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.q2Technologies.assessment.dao.KaryawanDao;
import com.q2Technologies.assessment.model.Karyawan;
import com.q2Technologies.assessment.util.DaoUtil;

@Repository
public class KaryawanDaoImpl extends DaoUtil implements KaryawanDao {

	@PersistenceContext
	EntityManager em;
	
	public List<Karyawan> getAll() throws Exception {
		List<Karyawan> result = new ArrayList<Karyawan>();
		try {
			String hql = "SELECT a FROM Karyawan a ";
			Query q = (Query) em.createQuery(hql);
			if (q != null) {
            	result = q.getResultList();
            }
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
