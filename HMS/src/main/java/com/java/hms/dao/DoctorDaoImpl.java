package com.java.hms.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.java.hms.model.Doctor;
import com.java.hms.model.Specialization;
import com.java.hms.model.Status;
import com.java.hms.util.SessionHelper;

public class DoctorDaoImpl implements DoctorDao{
	
	SessionFactory sf;
	Session session;

	@Override
	public List<Doctor> showDoctorDao() {
		sf = SessionHelper.getConnection();
		session = sf.openSession();
		Query query = session.createQuery("FROM Doctor");
		List<Doctor> doctorList = query.list();
		return doctorList;
	}

	@Override
	public List<Doctor> findBySpecialization(String category) {
		sf = SessionHelper.getConnection();
		session = sf.openSession();
		Specialization specialization = Specialization.valueOf(category);
		Query query = session.createQuery("FROM Doctor WHERE specialization = :category");
		query.setParameter("category", specialization);
		List<Doctor> doctorList = query.list();
		return doctorList;
	}

	@Override
	public Doctor findById(String docId) {
		sf = SessionHelper.getConnection();
	    session = sf.openSession();
	    Query query = session.createQuery("FROM Doctor WHERE doctorId = :docId");
		query.setParameter("docId", docId);
		List<Doctor> result = query.list();
		if (result != null && !result.isEmpty()) {
	        return result.get(0);
	    } else {
	        return null; 
	    }
	}
	
	@Override
	public String addDoctor(Doctor doctor) {
	    sf = SessionHelper.getConnection();
	    session = sf.openSession();
	    String generatedID = generateDoctorID(session);
	    doctor.setDoctorId(generatedID);
	    doctor.setIsStatus(Status.ACTIVE);
	    Transaction tx = session.beginTransaction();
	    session.save(doctor);
	    tx.commit();
	    session.close();
	    return "Added SuccessFully";
	}
	
	private String generateDoctorID(Session session) {
	    Query query = session.createQuery("select count(d) from Doctor d");
	    long count = (long) query.uniqueResult();
	    
	    String prefix = "HSK";
	    String uniquePart = String.format("DS%03d", count + 1); 
	    
	    return prefix + uniquePart;  
	}

}
