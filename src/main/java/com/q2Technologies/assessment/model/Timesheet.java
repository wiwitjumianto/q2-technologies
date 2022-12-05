package com.q2Technologies.assessment.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="timesheet")
public class Timesheet {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Karyawan idKaryawan;
	private Timestamp startKerja;
	private Timestamp finishKerja;
	private Integer istirahat;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_karyawan")
	public Karyawan getIdKaryawan() {
		return idKaryawan;
	}

	public void setIdKaryawan(Karyawan idKaryawan) {
		this.idKaryawan = idKaryawan;
	}
	
	
	@Column(name="start_kerja")
	public Timestamp getStartKerja() {
		return startKerja;
	}
	public void setStartKerja(Timestamp startKerja) {
		this.startKerja = startKerja;
	}
	
	
	@Column(name="finish_kerja")
	public Timestamp getFinishKerja() {
		return finishKerja;
	}
	public void setFinishKerja(Timestamp finishKerja) {
		this.finishKerja = finishKerja;
	}
	
	
	@Column(name="istirahat")
	public Integer getIstirahat() {
		return istirahat;
	}
	public void setIstirahat(Integer istirahat) {
		this.istirahat = istirahat;
	}
}
