package com.java.hms.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.java.hms.model.Patient;
import com.java.hms.util.SessionHelper;

public class PatientDaoImpl implements PatientDao{
	
	SessionFactory sf;
	Session session;
	
	@Override
	public String addPatient(Patient patient) throws ParseException {
		sf = SessionHelper.getConnection();
	    session = sf.openSession();
	    String generatedID = generatePatientID(session);
	    patient.setPatientId(generatedID);
	    java.sql.Date sqlDob = convertToSqlDate(patient.getStrDob());
        patient.setDateOfBirth(sqlDob);
	    Transaction tx = session.beginTransaction();
	    session.save(patient);
	    tx.commit();
	    session.close();
	    return "Added SuccessFully";
	}
	
	private java.sql.Date convertToSqlDate(String dateStr) throws ParseException {
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    java.util.Date utilDate = formatter.parse(dateStr);
	    return new java.sql.Date(utilDate.getTime());
	}
	
	private String generatePatientID(Session session) {
	    Query query = session.createQuery("select count(p) from Patient p");
	    long count = (long) query.uniqueResult();
	    
//	    String prefix = "PTH";	
	    String uniquePart = String.format("PTH%03d", count + 1); 
	    
	    return uniquePart;  
	}

	@Override
	public List<Patient> showPatientDao() {
		sf = SessionHelper.getConnection();
		session = sf.openSession();
		Query query = session.createQuery("FROM Patient");
		List<Patient> patientList = query.list();
		return patientList;
	}
	
	@Override
	public Patient findById(String patientId) {
		sf = SessionHelper.getConnection();
	    session = sf.openSession();
	    Query query = session.createQuery("FROM Patient WHERE patientId = :patientId");
		query.setParameter("patientId", patientId);
		List<Patient> result = query.list();
		if (result != null && !result.isEmpty()) {
	        return result.get(0);
	    } else {
	        return null; 
	    }
	}

	@Override
	public List<Patient> findByDoctorId(String docId) {
		sf = SessionHelper.getConnection();
		session = sf.openSession();
		Query query = session.createQuery("FROM Patient WHERE doctorId =:docId");
		query.setParameter("docId", docId);
		List<Patient> patientList = query.list();
		return patientList;
	}


}
