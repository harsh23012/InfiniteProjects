package com.java.hms.dao;
import java.text.ParseException;
import java.util.List;
import com.java.hms.model.Patient;

public interface PatientDao {
	List<Patient> showPatientDao();
	Patient findById(String patientId);
	List<Patient> findByDoctorId(String docId);
	String addPatient(Patient patient) throws ParseException;
}
