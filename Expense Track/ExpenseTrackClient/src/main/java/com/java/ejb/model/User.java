package com.java.ejb.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class User implements Serializable{
	private int id;
	private String name;
	private String email;
	private String user_name;
	private String phone;
	private double balance;
	private UserType user_type;
	private Timestamp created_at;
	private Timestamp updated_at;
	private String password;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public UserType getUser_type() {
		return user_type;
	}
	public void setUser_type(UserType user_type) {
		this.user_type = user_type;
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
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", user_name=" + user_name + ", phone="
				+ phone + ", balance=" + balance + ", user_type=" + user_type + ", created_at=" + created_at
				+ ", updated_at=" + updated_at + ", password=" + password +"]";
	}
	public User(int id, String name, String email, String user_name, String phone, double balance, UserType user_type,
			Timestamp created_at, Timestamp updated_at, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.user_name = user_name;
		this.phone = phone;
		this.balance = balance;
		this.user_type = user_type;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.password = password;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
