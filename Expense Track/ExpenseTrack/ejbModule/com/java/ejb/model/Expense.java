package com.java.ejb.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Expense implements Serializable{
//	id INT AUTO_INCREMENT PRIMARY KEY,
//    group_id INT NOT NULL,
//    paid_by_user_id INT NOT NULL,
//    description VARCHAR(255) NOT NULL,
//    category VARCHAR(50),
//    total_amount DECIMAL(10, 2) NOT NULL CHECK (total_amount >= 0),
//    expense_date DATE NOT NULL,
//    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
//    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	
	private int id;
	private String description;
	private String category;
	private double total_amount;
	private Date expense_date;
	private Timestamp created_at;
	private Timestamp updated_at;
	
	private User user;
	private TripGroup tripGroup;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public double getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(double total_amount) {
		this.total_amount = total_amount;
	}
	public Date getExpense_date() {
		return expense_date;
	}
	public void setExpense_date(Date expense_date) {
		this.expense_date = expense_date;
	}
	public Timestamp getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
	public Timestamp getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public TripGroup getTripGroup() {
		return tripGroup;
	}
	public void setTripGroup(TripGroup tripGroup) {
		this.tripGroup = tripGroup;
	}
	@Override
	public String toString() {
		return "Expense [id=" + id + ", group_id=" + ", description=" + description + ", category=" + category + ", total_amount=" + total_amount
				+ ", expense_date=" + expense_date + ", created_at=" + created_at + ", updated_at=" + updated_at
				+ ", user=" + user + ", tripGroup=" + tripGroup + "]";
	}
	public Expense(int id, String description, String category, double total_amount,
			Date expense_date, Timestamp created_at, Timestamp updated_at, User user, TripGroup tripGroup) {
		super();
		this.id = id;
		this.description = description;
		this.category = category;
		this.total_amount = total_amount;
		this.expense_date = expense_date;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.user = user;
		this.tripGroup = tripGroup;
	}
	public Expense() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
