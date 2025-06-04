package com.java.hms.model;

import java.sql.Date;
import java.util.List;

public class Patient {
//
//    PatientId varchar(30) primary key,
//    PatientName varchar(30) not null,
//    DoctorId varchar(30) REFERENCES Doctor(DoctorId),
//    DateOfBirth Date not null
	
	private String patientId;
	private String patientName;
	private String doctorId;
	private Date dateOfBirth;
	private String strDob;
	private List<MedicalHistory> medicalHistory;
	public List<MedicalHistory> getMedicalHistory() {
		return medicalHistory;
	}
	public void setMedicalHistory(List<MedicalHistory> medicalHistory) {
		this.medicalHistory = medicalHistory;
	}
	public String getStrDob() {
		return strDob;
	}
	public void setStrDob(String strDob) {
		this.strDob = strDob;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", patientName=" + patientName + ", doctorId=" + doctorId + ", dob="
				+ dateOfBirth + ", strDob=" + strDob + "]";
	}
	public Patient(String patientId, String patientName, String doctorId, Date dateOfBirth, String strDob) {
		super();
		this.patientId = patientId;
		this.patientName = patientName;
		this.doctorId = doctorId;
		this.dateOfBirth = dateOfBirth;
		this.strDob = strDob;
	}
	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}
}
