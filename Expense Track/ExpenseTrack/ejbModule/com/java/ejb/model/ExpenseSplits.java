package com.java.ejb.model;

import java.io.Serializable;
import java.sql.Timestamp;

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
	private TripGroup tripGroup;
	private boolean is_Settled;
	private Timestamp settled_at;
	
	public TripGroup getTripGroup() {
		return tripGroup;
	}
	public void setTripGroup(TripGroup tripGroup) {
		this.tripGroup = tripGroup;
	}
	public boolean isIs_Settled() {
		return is_Settled;
	}
	public void setIs_Settled(boolean is_Settled) {
		this.is_Settled = is_Settled;
	}
	public Timestamp getSettled_at() {
		return settled_at;
	}
	public void setSettled_at(Timestamp settled_at) {
		this.settled_at = settled_at;
	}
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
		return "ExpenseSplits [id=" + id + ", amount_owed=" + amount_owed + ", owedto=" + owedto + ", expense="
				+ expense + ", user=" + user + ", tripGroup=" + tripGroup + ", is_Settled=" + is_Settled
				+ ", settled_at=" + settled_at + "]";
	}
	public ExpenseSplits(int id, double amount_owed, int owedto, Expense expense, User user, TripGroup tripGroup,
			boolean is_Settled, Timestamp settled_at) {
		super();
		this.id = id;
		this.amount_owed = amount_owed;
		this.owedto = owedto;
		this.expense = expense;
		this.user = user;
		this.tripGroup = tripGroup;
		this.is_Settled = is_Settled;
		this.settled_at = settled_at;
	}
	public ExpenseSplits() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
