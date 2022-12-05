package com.q2Technologies.assessment.service.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.q2Technologies.assessment.dao.KaryawanDao;
import com.q2Technologies.assessment.dao.TimesheetDao;
import com.q2Technologies.assessment.model.Karyawan;
import com.q2Technologies.assessment.model.Timesheet;
import com.q2Technologies.assessment.service.KaryawanService;

@Service
public class KaryawanServiceImpl implements KaryawanService {

	@Autowired
	KaryawanDao karyawanDao;
	
	@Autowired
	TimesheetDao timesheetDao;
	
	@Transactional
	public Map<String, Object> getList() {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			List<Karyawan> kar = new ArrayList<Karyawan>();
			kar = karyawanDao.getAll();
			
			if (kar.size() < 1) {
				result.put("outError", 1);
				result.put("outMessage", "No Data Faund");
			}else {
				result.put("outError", 0);
				result.put("outMessage", "success");
				result.put("outData", kar);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Map<String, Object> getSummaryWeeklySalary() {
		Map<String, Object> response = new HashMap<String, Object>();
		List<Map<String, Object>> arrResult = new ArrayList<Map<String,Object>>();
		try {
			List<Karyawan> kar = new ArrayList<Karyawan>();
			kar = karyawanDao.getAll();
			if(kar.size() > 1) {
				System.out.println("-------------------------------- Summary Gaji Karyawan Minggu Ini --------------------------------");
				List<Timesheet> timesheet = new ArrayList<Timesheet>();
				for (int i=0; i<kar.size(); i++) {
					Map<String, Object> result = new HashMap<String, Object>();
					timesheet = timesheetDao.getByKaryawanId(kar.get(i).getIdKaryawan());
					if (timesheet.size() < 1) {
						System.out.println("--------------------------------");
						System.out.println("ID Karyawan = "+kar.get(i).getIdKaryawan());
						System.out.println("Nama Karyawan = "+kar.get(i).getNamaKaryawan());
						System.out.println("Karyawan Tidak Bekerja Minggu Ini");
						result.put("id_karyawan", kar.get(i).getIdKaryawan());
						result.put("nama_karyawan", kar.get(i).getNamaKaryawan());
						result.put("gaji_karyawan_minggu_ini","karyawan tidak bekerja minggu ini");
						System.out.println("--------------------------------");
					}else {
							int totalHour = 0;
							int totalGaji = 0;
							for(int j = 0; j< timesheet.size(); j++){
								int jam = jamKerja(timesheet.get(j).getFinishKerja(), timesheet.get(j).getStartKerja(), timesheet.get(j).getIstirahat());
								totalHour = totalHour + jam;
							}
							if(totalHour < 40) {
								totalGaji = totalHour *  Math.round(kar.get(i).getUpahPerjam());
								System.out.println("--------------------------------");
								System.out.println("ID Karyawan = "+kar.get(i).getIdKaryawan());
								System.out.println("Nama Karyawan = "+kar.get(i).getNamaKaryawan());
								System.out.println("Gaji_Karyawan_Minggu_ini = "+totalGaji);
								result.put("id_karyawan", kar.get(i).getIdKaryawan());
								result.put("nama_karyawan", kar.get(i).getNamaKaryawan());
								result.put("gaji_karyawan_minggu_ini", totalGaji);
								System.out.println("--------------------------------");
							}else {
								int overtime = totalHour - 40;
								int upahOvertime = (Math.round(kar.get(i).getUpahPerjam() / 100) * 150) * overtime ;
								totalGaji = (40 * Math.round(kar.get(i).getUpahPerjam())) + upahOvertime;
								System.out.println("--------------------------------");
								System.out.println("ID Karyawan = "+kar.get(i).getIdKaryawan());
								System.out.println("Nama Karyawan = "+kar.get(i).getNamaKaryawan());
								System.out.println("Gaji_Karyawan_Minggu_ini = "+totalGaji);
								result.put("id_karyawan", kar.get(i).getIdKaryawan());
								result.put("nama_karyawan", kar.get(i).getNamaKaryawan());
								result.put("gaji_karyawan_minggu_ini", totalGaji);
								System.out.println("--------------------------------");
							}
					}
					arrResult.add(result);
				}
				response.put("outError", 0);
				response.put("outMessage", "success");
				response.put("outData", arrResult);
			}else {
				response.put("outError", 1);
				response.put("outMessage", "No Data Faund");
			}
			
		} catch (Exception e) {
			e.getMessage();
		}
		return response;
	}
	
	private static int jamKerja(Timestamp finish, Timestamp start, int istirahat) {
	    return (int) (finish.getHours() - start.getHours())-istirahat;
	}

}
