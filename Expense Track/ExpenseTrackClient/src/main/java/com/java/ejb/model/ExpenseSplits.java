package com.java.ejb.model;

import java.io.Serializable;

public class ExpenseSplits implements Serializable{
//	 id INT AUTO_INCREMENT PRIMARY KEY,
//	    expense_id INT NOT NULL,
//	    user_id INT NOT NULL,
//	    amount_owed DECIMAL(10,2) NOT NULL,
	
	private int id;
	private double amount_owed;
	private int owedto;
	private Expense expense;
	private User user;
	
	public int getOwedto() {
		return owedto;
	}
	public void setOwedto(int owedto) {
		this.owedto = owedto;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getAmount_owed() {
		return amount_owed;
	}
	public void setAmount_owed(double amount_owed) {
		this.amount_owed = amount_owed;
	}
	public Expense getExpense() {
		return expense;
	}
	public void setExpense(Expense expense) {
		this.expense = expense;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "ExpenseSplits [id=" + id + ", amount_owed="
				+ amount_owed + ", expense=" + expense + ", user=" + user + "Owed To=" +owedto+"]";
	}
	public ExpenseSplits(int id, double amount_owed, Expense expense, User user, int owedto) {
		super();
		this.id = id;
		this.amount_owed = amount_owed;
		this.expense = expense;
		this.user = user;
		this.owedto = owedto;
	}
	public ExpenseSplits() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
