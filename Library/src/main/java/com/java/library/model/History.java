package com.java.library.model;

import java.sql.Date;

import lombok.Data;

@Data
public class History {
	private String userName;
	private int bookId;
	private Date fromDate;
	private Date returnedDate;
}
