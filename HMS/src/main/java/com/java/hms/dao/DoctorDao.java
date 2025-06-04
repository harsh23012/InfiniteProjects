package com.java.hms.dao;

import java.util.List;
import com.java.hms.model.Doctor;

public interface DoctorDao {
	
	List<Doctor> showDoctorDao();
	List<Doctor> findBySpecialization(String category);
	Doctor findById(String docId);
	String addDoctor(Doctor doctor);

}
