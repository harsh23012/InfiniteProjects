package com.java.lms.dao;

import java.sql.SQLException;
import java.util.List;

import com.java.lms.model.LeaveDetails;

public interface LeaveDetailsDAO {

	List<LeaveDetails> applyLeave(LeaveDetails leaveDetails) throws ClassNotFoundException, SQLException;
	List<LeaveDetails> leaveHistory(int empId) throws ClassNotFoundException, SQLException;
	List<LeaveDetails> leaveAppliedHistory(int empId) throws ClassNotFoundException, SQLException;
}