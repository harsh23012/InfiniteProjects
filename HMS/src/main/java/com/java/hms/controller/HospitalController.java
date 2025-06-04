package com.java.hms.controller;

import java.text.ParseException;
import java.util.List;
import com.java.hms.dao.DoctorDao;
import com.java.hms.dao.DoctorDaoImpl;
import com.java.hms.dao.MedicalHistoryDao;
import com.java.hms.dao.PatientDao;
import com.java.hms.dao.PatientDaoImpl;
import com.java.hms.model.Doctor;
import com.java.hms.model.MedicalHistory;
import com.java.hms.model.Patient;

public class HospitalController {
	private DoctorDao doctorDao;
	private Doctor doctor;
	private PatientDao patientDao;
	private Patient patient;
	private MedicalHistoryDao medicalHistoryDao;
	private MedicalHistory medicalHistory;
	private String selectedCategory;
	private List<Doctor> filteredDoctors;
    private String searchId;
    private Doctor foundDoctor;
    private Patient foundPatient;
    private List<MedicalHistory> foundHistory;
    private boolean searchPerformed;
    private String successMessage;
    private List<Patient> filteredPatient;

    

	public List<MedicalHistory> getFoundHistory() {
		return foundHistory;
	}

	public void setFoundHistory(List<MedicalHistory> foundHistory) {
		this.foundHistory = foundHistory;
	}

	public MedicalHistoryDao getMedicalHistoryDao() {
		return medicalHistoryDao;
	}

	public void setMedicalHistoryDao(MedicalHistoryDao medicalHistoryDao) {
		this.medicalHistoryDao = medicalHistoryDao;
	}

	public MedicalHistory getMedicalHistory() {
		return medicalHistory;
	}

	public void setMedicalHistory(MedicalHistory medicalHistory) {
		this.medicalHistory = medicalHistory;
	}

	public List<Patient> getFilteredPatient() {
		return filteredPatient;
	}

	public void setFilteredPatient(List<Patient> filteredPatient) {
		this.filteredPatient = filteredPatient;
	}

	public Patient getFoundPatient() {
		return foundPatient;
	}

	public void setFoundPatient(Patient foundPatient) {
		this.foundPatient = foundPatient;
	}


	public void setDoctorDao(DoctorDao doctorDao) {
		this.doctorDao = doctorDao;
	}

	public void setPatientDao(PatientDao patientDao) {
		this.patientDao = patientDao;
	}

	public PatientDao getPatientDao() {
		return patientDao;
	}

	public void setPatientDao(PatientDaoImpl patientDao) {
		this.patientDao = patientDao;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public String getSuccessMessage() {
        return successMessage;
    }

	public String getSearchId() {
		return searchId;
	}

	public void setSearchId(String searchId) {
		this.searchId = searchId;
	}

	public Doctor getFoundDoctor() {
		return foundDoctor;
	}

	public void setFoundDoctor(Doctor foundDoctor) {
		this.foundDoctor = foundDoctor;
	}

	public boolean isSearchPerformed() {
		return searchPerformed;
	}

	public void setSearchPerformed(boolean searchPerformed) {
		this.searchPerformed = searchPerformed;
	}

	public String getSelectedCategory() {
		return selectedCategory;
	}

	public void setSelectedCategory(String selectedCategory) {
		this.selectedCategory = selectedCategory;
	}

	public List<Doctor> getFilteredDoctors() {
		return filteredDoctors;
	}

	public void setFilteredDoctors(List<Doctor> filteredDoctors) {
		this.filteredDoctors = filteredDoctors;
	}

	public DoctorDao getDoctorDao() {
		return doctorDao;
	}

	public void setDoctorDao(DoctorDaoImpl doctorDao) {
		this.doctorDao = doctorDao;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public List<Doctor> showDoctor(){
		return doctorDao.showDoctorDao();
	}
	public List<MedicalHistory> showMedicalHistory(){
		return medicalHistoryDao.showMedicalHistoryDao();
	}
	
	public List<Patient> showPatient(){
		return patientDao.showPatientDao();
	}

    // Filtering logic
    public String filterByCategory() {
        if (selectedCategory != null && !selectedCategory.isEmpty()) {
            filteredDoctors = doctorDao.findBySpecialization(selectedCategory);
        } else {
            filteredDoctors = null;
        }
        return null;
    }
    
    public String searchDoctorById() {
        searchPerformed = true;
        foundDoctor = doctorDao.findById(searchId);
        return null; 
    }
    public void searchMedHistoryById() {
    	searchPerformed = true;
    	foundHistory = medicalHistoryDao.findById(searchId);
    	
    }
    
    public String searchPatientById() {
    	searchPerformed = true;
    	foundPatient = patientDao.findById(searchId);
    	return null; 
    }
    
    public String searchPatientByDoctorId() {
    	
        filteredPatient = patientDao.findByDoctorId(searchId);
        return null;
    }
    public String addDoctor() {
        doctorDao.addDoctor(doctor);
        successMessage = "Doctor added successfully. Doctor ID: " + doctor.getDoctorId();
        doctor = new Doctor();
        return "Home.jsp?faces-redirect=true";
    }
    public String addMedicalHistory() {
    	medicalHistoryDao.addMedicalHistoryDao(medicalHistory);
    	successMessage = "Doctor added successfully. Medical ID: " + medicalHistory.getMedId();
    	medicalHistory = new MedicalHistory();
    	return "MedicalHistoryHome.jsp?faces-redirect=true";
    }
    
    public String addPatient() throws ParseException {
    	patientDao.addPatient(patient);
    	successMessage = "Patient added successfully. Patient ID: " + patient.getPatientId();
    	patient = new Patient();
    	return "PatientHome.jsp?faces-redirect=true";
    }
    
    public String showPatientTests(int searchId) {
    	return medicalHistoryDao.showPatientTestsDao(searchId);
    }

    
}
