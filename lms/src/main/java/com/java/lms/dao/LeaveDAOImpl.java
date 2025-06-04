package com.java.lms.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import com.java.lms.model.LeaveDetails;
import com.java.lms.model.LeaveStatus;
import com.java.lms.model.LeaveType;
import com.java.lms.util.ConnectionHelper;

public class LeaveDAOImpl implements LeaveDetailsDAO {

	Connection connection;
	PreparedStatement pst;
	
	public int dateDiff(Date startDate, Date endDate) {
		long ms = endDate.getTime() - startDate.getTime();
		int diff = (int)(ms/(1000 * 60 * 60 * 24));
		diff++;
		return diff;
	}
	
	public Date convertToSqlDate(String str) throws ParseException {
		System.out.println("String is " +str);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = sdf.parse(str);
		Date sqlDate = new Date(date.getTime());
		System.out.println("Date is " +sqlDate);
		return sqlDate;
	}
	
	@Override
	public List<LeaveDetails> applyLeave(LeaveDetails leaveDetails) throws ClassNotFoundException, SQLException {
		System.out.println(leaveDetails);
		connection = ConnectionHelper.getConnection();
		int diff = dateDiff(leaveDetails.getLeaveStartDate(), leaveDetails.getLeaveEndDate());
		leaveDetails.setNoOfDays(diff);
		String cmd = "Insert into LEAVE_HISTORY(EMP_ID,LEAVE_START_DATE,LEAVE_END_DATE,"
				+ "LEAVE_NO_OF_DAYS,LEAVE_TYPE,LEAVE_REASON) values(?,?,?,?,?,?)";
		pst = connection.prepareStatement(cmd);
		pst.setInt(1, leaveDetails.getEmpId());
		pst.setDate(2, leaveDetails.getLeaveStartDate());
		pst.setDate(3, leaveDetails.getLeaveEndDate());
		pst.setInt(4, leaveDetails.getNoOfDays());
		pst.setString(5, leaveDetails.getLeaveType().toString());
		pst.setString(6, leaveDetails.getLeaveReason());
		pst.executeUpdate();
		cmd = "Update Employee set EMP_AVAIL_LEAVE_BAL = EMP_AVAIL_LEAVE_BAL - ? Where EMP_ID=?";
		pst = connection.prepareStatement(cmd);
		pst.setInt(1, diff);
		pst.setInt(2, leaveDetails.getEmpId());
		pst.executeUpdate();
		List<LeaveDetails> list = new ArrayList<>();
		list.add(leaveDetails);
		return list;
	}

	@Override
	public List<LeaveDetails> leaveHistory(int empId) throws ClassNotFoundException, SQLException {
		connection = ConnectionHelper.getConnection();
		String cmd = "select * from leave_history where emp_id = ?";
		pst = connection.prepareStatement(cmd);
		pst.setInt(1, empId);
		ResultSet rs = pst.executeQuery();
		LeaveDetails leaveDetails = null;
		List<LeaveDetails> leaveList = new ArrayList<LeaveDetails>();
		while(rs.next()) {
			leaveDetails = new LeaveDetails();
			leaveDetails.setLeaveId(rs.getInt("LEAVE_ID"));
			leaveDetails.setEmpId(rs.getInt("EMP_ID"));
			leaveDetails.setLeaveStartDate(rs.getDate("LEAVE_START_DATE"));
			leaveDetails.setLeaveEndDate(rs.getDate("LEAVE_END_DATE"));
			leaveDetails.setNoOfDays(rs.getInt("LEAVE_NO_OF_DAYS"));
			leaveDetails.setLeaveStatus(LeaveStatus.valueOf(rs.getString("LEAVE_STATUS")));
			leaveDetails.setLeaveType(LeaveType.valueOf( rs.getString("LEAVE_TYPE")));
			leaveDetails.setLeaveReason(rs.getString("LEAVE_REASON"));
			leaveDetails.setManagerComments(rs.getString("LEAVE_MNGR_COMMENTS"));
			leaveDetails.setAppliedOn(rs.getDate("LEAVE_APPLIED_ON"));
			leaveList.add(leaveDetails);
		}
		return leaveList;
	}

	@Override
	public List<LeaveDetails> leaveAppliedHistory(int empId) throws ClassNotFoundException, SQLException {
		connection = ConnectionHelper.getConnection();
		String cmd = "SELECT l.* FROM Leave_History l JOIN Employee e ON l.EMP_ID = e.EMP_ID WHERE e.EMP_MANAGER_ID = ? ORDER BY l.EMP_ID";
		pst = connection.prepareStatement(cmd);
		pst.setInt(1, empId);
		ResultSet rs = pst.executeQuery();
		LeaveDetails leaveDetails = null;
		List<LeaveDetails> leaveList = new ArrayList<LeaveDetails>();
		while(rs.next()) {
			leaveDetails = new LeaveDetails();
			leaveDetails.setLeaveId(rs.getInt("LEAVE_ID"));
			leaveDetails.setEmpId(rs.getInt("EMP_ID"));
			leaveDetails.setLeaveStartDate(rs.getDate("LEAVE_START_DATE"));
			leaveDetails.setLeaveEndDate(rs.getDate("LEAVE_END_DATE"));
			leaveDetails.setNoOfDays(rs.getInt("LEAVE_NO_OF_DAYS"));
			leaveDetails.setLeaveStatus(LeaveStatus.valueOf(rs.getString("LEAVE_STATUS")));
			leaveDetails.setLeaveType(LeaveType.valueOf(rs.getString("LEAVE_TYPE")));
			leaveDetails.setLeaveReason(rs.getString("LEAVE_REASON"));
			leaveDetails.setManagerComments(rs.getString("LEAVE_MNGR_COMMENTS"));
			leaveDetails.setAppliedOn(rs.getDate("LEAVE_APPLIED_ON"));
			leaveList.add(leaveDetails);
		}
		return leaveList;
	}
}