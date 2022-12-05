package com.q2Technologies.assessment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="karyawan")
public class Karyawan {
	private static final long serialVersionUID = 1L;
	
	private Integer idKaryawan;
	private String namaKaryawan;
	private String nomorRekenening;
	private Float upahPerjam;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_karyawan")
	public Integer getIdKaryawan() {
		return idKaryawan;
	}
	
	public void setIdKaryawan(Integer idKaryawan) {
		this.idKaryawan = idKaryawan;
	}
	
	@Column(name="nama_karyawan")
	public String getNamaKaryawan() {
		return namaKaryawan;
	}
	public void setNamaKaryawan(String namaKaryawan) {
		this.namaKaryawan = namaKaryawan;
	}
	
	@Column(name="nomor_rekening")
	public String getNomorRekenening() {
		return nomorRekenening;
	}
	public void setNomorRekenening(String nomorRekenening) {
		this.nomorRekenening = nomorRekenening;
	}
	
	@Column(name="upah_perjam")
	public Float getUpahPerjam() {
		return upahPerjam;
	}
	public void setUpahPerjam(Float upahPerjam) {
		this.upahPerjam = upahPerjam;
	}
	
	
}
