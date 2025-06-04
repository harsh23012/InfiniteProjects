package com.java.ejb.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class TransHistory implements Serializable{
//    id INT AUTO_INCREMENT PRIMARY KEY,
//    from_user_id INT NOT NULL,
//    to_user_id INT NOT NULL,
//    amount DECIMAL(10, 2) NOT NULL CHECK (amount > 0),
//    transfer_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
//    note VARCHAR(255),
	
	private int id;
	private int fromUser;
	private int toUser;
	private double amount;
	private Timestamp transfer_date;
	private String note;
	private User user;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public int getFromUser() {
		return fromUser;
	}

	public void setFromUser(int fromUser) {
		this.fromUser = fromUser;
	}

	public int getToUser() {
		return toUser;
	}

	public void setToUser(int toUser) {
		this.toUser = toUser;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Timestamp getTransfer_date() {
		return transfer_date;
	}

	public void setTransfer_date(Timestamp transfer_date) {
		this.transfer_date = transfer_date;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "TransHistory [id=" + id + ", from_user_id=" + fromUser + ", to_user_id=" + toUser + ", amount="
				+ amount + ", transfer_date=" + transfer_date + ", note=" + note + ", user=" + user + "]";
	}

	public TransHistory(int id, int fromUser, int toUser, double amount, Timestamp transfer_date, String note,
			User user) {
		super();
		this.id = id;
		this.fromUser = fromUser;
		this.toUser = toUser;
		this.amount = amount;
		this.transfer_date = transfer_date;
		this.note = note;
		this.user = user;
	}

	public TransHistory() {
		super();
		// TODO Auto-generated constructor stub
	}

}
