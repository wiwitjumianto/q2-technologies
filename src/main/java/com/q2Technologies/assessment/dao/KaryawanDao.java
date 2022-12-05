package com.q2Technologies.assessment.dao;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.q2Technologies.assessment.model.Karyawan;

public interface KaryawanDao extends Repository<Karyawan, Integer> {
	List<Karyawan> getAll() throws Exception;
}
