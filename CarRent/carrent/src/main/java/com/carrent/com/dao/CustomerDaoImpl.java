package com.carrent.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.carrent.com.exception.CustomerNotFoundException;
import com.carrent.com.model.Customer;
import com.carrent.com.util.ConnectionHelper;

public class CustomerDaoImpl implements CustomerDao{

	Connection connection;
	PreparedStatement psmt;
	
	@Override
	public void addCustomer(Customer customer) throws SQLException, ClassNotFoundException {
		connection = ConnectionHelper.getConnection();
		String cmd = "Insert into Customer(firstName, lastName, email, phoneNumber) values(?,?,?,?)";
		psmt = connection.prepareStatement(cmd);
		psmt.setString(1, customer.getFirstName());
		psmt.setString(2, customer.getLastName());
		psmt.setString(3, customer.getEmail());
		psmt.setString(4, customer.getPhoneNumber());
		
		psmt.executeUpdate();
	}

	@Override
	public void removeCustomer(int customerId) throws ClassNotFoundException, SQLException, CustomerNotFoundException {
		connection = ConnectionHelper.getConnection();
		List<Customer> customerList = findCustomerById(customerId);
		
		if (customerList.isEmpty()) {
	        throw new CustomerNotFoundException("Customer with ID " + customerId + " not found.");
	    }			
		String cmd = "Delete from Customer where customerID = ?";
		psmt = connection.prepareStatement(cmd);
		psmt.setInt(1, customerId);
		int rowsAffected = psmt.executeUpdate();
		
		if (rowsAffected == 0) { 
			throw new CustomerNotFoundException("Customer with Id " + customerId + "could not be deleted.");
		}
		
		
	}
	
	public List<Customer> findCustomerById(int customerId) throws SQLException, ClassNotFoundException, CustomerNotFoundException {
		 List<Customer> customerList = new ArrayList<>();
			String cmd = "Select * from customer where customerID=?";
			connection = ConnectionHelper.getConnection();
			psmt = connection.prepareStatement(cmd);
			psmt.setInt(1, customerId);
			ResultSet rs = psmt.executeQuery();
			 while(rs.next()) {
			        Customer customer = new Customer();
			        customer.setCustId(customerId);
			        customer.setFirstName(rs.getString("firstName"));
			        customer.setLastName(rs.getString("lastName"));
			        customer.setEmail(rs.getString("email"));
			        customer.setPhoneNumber(rs.getString("phoneNumber"));
			       customerList.add(customer);
			    } 
			 if (customerList.isEmpty()) {
			        throw new CustomerNotFoundException("Customer with ID " + customerId + " not found.");
			    }	
			 return customerList;
	}

	@Override
	public List<Customer> listCustomers() throws ClassNotFoundException, SQLException, CustomerNotFoundException {
		List<Customer> customerList = new ArrayList<>();
		String cmd = "Select * from customer";
		connection = ConnectionHelper.getConnection();
		psmt = connection.prepareStatement(cmd);
		ResultSet rs = psmt.executeQuery();
		while(rs.next()) {
			Customer customer = new Customer();
			customer.setCustId(rs.getInt("customerId"));
	        customer.setFirstName(rs.getString("firstName"));
	        customer.setLastName(rs.getString("lastName"));
	        customer.setEmail(rs.getString("email"));
	        customer.setPhoneNumber(rs.getString("phoneNumber"));
	       customerList.add(customer);
		}
		if (customerList.isEmpty()) {
	        throw new CustomerNotFoundException("Customer not found.");
	    }	
	 return customerList;
	}

	

}
