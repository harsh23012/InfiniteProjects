package com.java.hms.dao;

import java.util.List;

import javax.faces.context.FacesContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.Map;

import com.java.hms.model.Doctor;
import com.java.hms.model.MedicalHistory;
import com.java.hms.model.Status;
import com.java.hms.util.SessionHelper;

public class MedicalHistoryDaoImpl implements MedicalHistoryDao{

	SessionFactory sf;
	Session session;

	@Override
	public String addMedicalHistoryDao(MedicalHistory medicalHistory) {
		 	sf = SessionHelper.getConnection();
		    session = sf.openSession();
		    String generatedID = generateMedID(session);
		    medicalHistory.setMedId(generatedID);
		    Transaction tx = session.beginTransaction();
		    session.save(medicalHistory);
		    tx.commit();
		    session.close();
		    return "Added SuccessFully";
	}
	
	private String generateMedID(Session session) {
	    Query query = session.createQuery("select count(m) from MedicalHistory m");
	    long count = (long) query.uniqueResult();
	    String uniquePart = String.format("MHD%03d", count + 1); 
	    
	    return uniquePart;  
	}

	@Override
	public List<MedicalHistory> showMedicalHistoryDao() {
		sf = SessionHelper.getConnection();
		session = sf.openSession();
		Query query = session.createQuery("FROM MedicalHistory");
		List<MedicalHistory> medHistoryList = query.list();
		return medHistoryList;
	}

	@Override
	public List<MedicalHistory> findById(String patientId) {
		sf = SessionHelper.getConnection();
	    session = sf.openSession();
	    Query query = session.createQuery("FROM MedicalHistory WHERE patientId = :patientId");
		query.setParameter("patientId", patientId);
		List<MedicalHistory> result = query.list();
		if (result != null && !result.isEmpty()) {
	        return result;
	    } else {
	        return null; 
	    }
	}
	
	@Override
	public String showPatientTestsDao(int medId) {
		Map<String,Object> sessionMap = 
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sf = SessionHelper.getConnection();
		session = sf.openSession();
		 Query query = session.createQuery("FROM MedicalHistory WHERE medId = :medId");
		query.setParameter("medId", medId);   
		MedicalHistory medicalhistory = (MedicalHistory)query.uniqueResult();
		sessionMap.put("historyFound", medicalhistory);
		return "ShowPatientTests.jsp?faces-redirect=true";
	}

}
