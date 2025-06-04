package com.java.ejb.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class GroupMembers implements Serializable{
	
//	id INT AUTO_INCREMENT PRIMARY KEY,
//    group_id INT NOT NULL,
//    user_id INT NOT NULL,
//    role ENUM('ADMIN', 'MEMBER') DEFAULT 'MEMBER',
//    joined_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

	
	private int id;
	private Role role;
	private Timestamp joined_at;
	private double collectedAmount;
	private TripGroup tripGroup;
	private User user;
	
	public double getCollectedAmount() {
		return collectedAmount;
	}
	public void setCollectedAmount(double collectedAmount) {
		this.collectedAmount = collectedAmount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Timestamp getJoined_at() {
		return joined_at;
	}
	public void setJoined_at(Timestamp joined_at) {
		this.joined_at = joined_at;
	}
	public TripGroup getTripGroup() {
		return tripGroup;
	}
	public void setTripGroup(TripGroup tripGroup) {
		this.tripGroup = tripGroup;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "GroupMembers [id=" + id + ", role=" + role
				+ ", joined_at=" + joined_at + ", tripGroup=" + tripGroup + ", user=" + user + ",collectedAmount=" + collectedAmount + "]";
	}
	public GroupMembers(int id, Role role, Timestamp joined_at, TripGroup tripGroup,
			User user, double collectedAmount) {
		super();
		this.id = id;
		this.role = role;
		this.joined_at = joined_at;
		this.tripGroup = tripGroup;
		this.user = user;
		this.collectedAmount = collectedAmount;
	}
	public GroupMembers() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
