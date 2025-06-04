<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="employDao" class="com.java.lms.dao.EmployeeDAOImpl" />
	<c:set var="empId" value="${param.empId}" />
	<c:set var="mgrId" value="${param.mgrId}" />
	<c:set var="myInfo" value="${employDao.searchEmployeeDao(param.empId)}" />
	<c:set var="myManagerInfo" value="${employDao.searchEmployeeDao(param.mgrId)}" />
	<table border="3" align="center">
		<tr>
			<th>
				<p>Employee Id :<b>${myInfo.empId} </b> </p>
				<p>Employee Name <b>${myInfo.empName}</b> </p>
				<p>Email <b>${myInfo.empEmail}</b>
				<p>Mobile No : <b>${myInfo.mobileNo }</b> </p>
				<p>Department : <b>${myInfo.dept}</b> </p>
				<p>Date Of Join :<b>${myInfo.dateOfJoin}</b> </p>
				<p>Manager Id : <b>${myInfo.managerId}</b> </p>
				<p>Leave Available Balance <b>${myInfo.leaveAvail} </b> </p>
				<a href=ApplyLeave.jsp?empId=${myInfo.empId}>Apply Leave</a>
				
			</th>
			<th>
				<p>Manager Id : <b>${myManagerInfo.empId} </b> </p>
				<p>Manager Name <b>${myManagerInfo.empName}</b> </p>
				<p>Manager Email <b>${myManagerInfo.empEmail}</b>
				<p>Manager Mobile No : <b>${myManagerInfo.mobileNo }</b> </p>
				<p>Manager Department : <b>${myManagerInfo.dept}</b> </p>
				<p>Manager Date Of Join :<b>${myManagerInfo.dateOfJoin}</b> </p>
				<p>Manager Id : <b>${myManagerInfo.managerId}</b> </p>
				<p>Manager Leave Available Balance <b>${myManagerInfo.leaveAvail} </b> </p>
			</th>
		</tr>
	</table><br/>
	<center><h3>Leave History</h3></center>
	<jsp:useBean id="leaveDao" class="com.java.lms.dao.LeaveDAOImpl" />
	<c:set var="empId" value="${param.empId}" />
	<c:set var="leaveHistory" value="${leaveDao.leaveHistory(param.empId)}" />
		<table border="3" align="center">
		<tr>
			<th>Leave Id</th>
			<th>Employee Id</th>
			<th>Leave Start Date</th>
			<th>MLeave End Date</th>
			<th>No of Day</th>
			<th>Leave Status</th>
			<th>Leave Type</th>
			<th>Leave Reason</th>
			<th>Manager Comments</th>
			<th>Applied On</th>
		</tr>
		<c:forEach var="leave" items="${leaveHistory}">
			<tr>
				<td>${leave.leaveId}</td>
				<td>${leave.empId}</td>
				<td>${leave.leaveStartDate}</td>
				<td>${leave.leaveEndDate}</td>
				<td>${leave.noOfDays}</td>
				<td>${leave.leaveStatus}</td>
				<td>${leave.leaveType}</td>
				<td>${leave.leaveReason}</td>
				<td>${leave.managerComments}</td>
				<td>${leave.appliedOn}</td>
				
			</tr>
		</c:forEach>
	</table><br/>
	
<center><h3>Leave Applied By My Reporting Employees</h3></center>
	<c:set var="empId" value="${param.empId}" />
	<c:set var="leaveHistory" value="${leaveDao.leaveAppliedHistory(param.empId)}" />
	<c:set var="temp" value="0"/>
		<table border="3" align="center">
		<c:forEach var="leave" items="${leaveHistory}">
			<c:if test="${temp == leave.empId }">
			<tr>
				<td></td>
				<td>${leave.leaveId}</td>
				<td>${leave.noOfDays}</td>
				<td>${leave.leaveStartDate}</td>
				<td>${leave.leaveEndDate}</td>
				<td>${leave.leaveType}</td>
				<td>${leave.leaveStatus}</td>
				<td colspan="2">${leave.leaveReason}</td>
			</tr></c:if>
			<c:if test="${temp != leave.empId }">
			<c:set var="temp" value="${leave.empId}" />
			<c:set var="employ" value="${employDao.searchEmployeeDao(leave.empId)}" />
			
			<tr>
				<th colspan="2">Employee ID</th>
				<td>${ employ.empId}</td>
				<th colspan="2">Employee Name</th>
				<td>${ employ.empName}</td>
				<th colspan="2">Employee Leave Balance</th>
				<td>${ employ.leaveAvail}</td>
			</tr>
			<tr>
				<th></th>
				<th>LeaveId</th>
				<th>Number Of Days</th>
				<th>Start Date</th>
				<th>End Date</th>
				<th>Leave Type</th>
				<th>Status</th>
				<th colspan="2">Leave Reason</th>
			</tr>
			</c:if>
		</c:forEach>
	</table><br/>
</body>
</html>