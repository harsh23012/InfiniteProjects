<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
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
	</table>
	
	<a href=EmployShow.jsp>Employ</a>
</body>
</body>
</html>