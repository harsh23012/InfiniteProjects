package com.carrent.com.dao;

import java.sql.SQLException;
import java.util.List;

import com.carrent.com.model.Lease;
import com.carrent.com.model.Payment;

public interface PaymentDao {

	List<Payment> paymentDetailsCustomer(int custId) throws ClassNotFoundException, SQLException;
	double totalRevenue() throws ClassNotFoundException, SQLException;
	Payment findLease(int leaseId) throws ClassNotFoundException, SQLException;
	void payDues(double dues, int paymentId) throws ClassNotFoundException, SQLException;
	void updatePaymentAmount(int leaseId, double newAmount) throws SQLException, ClassNotFoundException;
}
