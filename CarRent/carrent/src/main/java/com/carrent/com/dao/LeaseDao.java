package com.carrent.com.dao;


import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import com.carrent.com.model.Lease;


public interface LeaseDao {
	void addLease(Lease lease) throws ClassNotFoundException, SQLException; 
	double getDailyRateByVehicleId(int vehicleId) throws SQLException, ClassNotFoundException;
	void returnCar(int leaseId) throws ClassNotFoundException, SQLException;
	List<Lease> listActiveLeases() throws ClassNotFoundException, SQLException;
	List<Lease> listLeases() throws ClassNotFoundException, SQLException;
	boolean customerHasActiveLease(int customerId) throws ClassNotFoundException, SQLException;
	void extendLease(Lease lease) throws ClassNotFoundException, SQLException;
	Lease findLeaseById(int leaseId) throws SQLException, ClassNotFoundException;
}

