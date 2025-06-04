package com.java.hms.dao;

import java.util.List;

import com.java.hms.model.MedicalHistory;

public interface MedicalHistoryDao {
	String addMedicalHistoryDao(MedicalHistory medicalHistory);
	List<MedicalHistory> showMedicalHistoryDao();
	List<MedicalHistory> findById(String searchId);
	String showPatientTestsDao(int medId);

}
