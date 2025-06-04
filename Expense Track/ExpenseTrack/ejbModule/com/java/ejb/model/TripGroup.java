package com.java.ejb.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class TripGroup implements Serializable{
//	id INT AUTO_INCREMENT PRIMARY KEY,
//    group_name VARCHAR(100) NOT NULL,
//    description TEXT,
//    created_by INT,
//    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
//    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	
	private int id;
	private String name;
	private String description;
	private Timestamp created_at;
	private Timestamp updated_at;
	
	private User user;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

//	public int getCreated_by() {
//		return created_by;
//	}
//
//	public void setCreated_by(int created_by) {
//		this.created_by = created_by;
//	}

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

	@Override
	public String toString() {
		return "TripGroup [id=" + id + ", name=" + name + ", description=" + description
				+ ", created_at=" + created_at + ", updated_at=" + updated_at + ", user=" + user + "]";
	}

	public TripGroup(int id, String name, String description, int created_by, Timestamp created_at,
			Timestamp updated_at, User user) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
//		this.created_by = created_by;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.user = user;
	}

	public TripGroup() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
