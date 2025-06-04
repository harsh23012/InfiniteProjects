<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<center> <h2>Apply Leave</h2></center>
<form method="post" action="ApplyLeave.jsp">
		<center>
			Employ Id : 
			<input type="text" readonly="readonly" name="empId" value="<%= request.getParameter("empId") %>"/> <br/><br/>
			Leave Start Date : 
			<input type="date" name="leaveStartDate" /> <br/><br/>
			Leave End Date : 
			<input type="date" name="leaveEndDate" /> <br/><br/>
			Leave Type :
			<select name="leaveType">
		        <option value="EL">Earned Leave</option>
		        <option value="ML">Maternity Leave</option>
		        <option value="PL">Personal Leave</option>
		    </select><br/>
			Leave Reason : 
			<input type="text" name = "leaveReason"/>  <br/><br/>
			
			<input type="submit" value="Apply" />
		</center>
	</form>
	<jsp:useBean id="leaveDao" class="com.java.lms.dao.LeaveDAOImpl" />
	<c:if test="${param.empId!=null && param.leaveStartDate !=null}">
	<c:set var="date1" value="${leaveDao.convertToSqlDate(param.leaveStartDate)}" />
	<c:set var="date2" value="${leaveDao.convertToSqlDate(param.leaveEndDate)}" />
	<jsp:useBean id="leaveDetails" class="com.java.lms.model.LeaveDetails"/>
	<jsp:setProperty property="empId" name="leaveDetails" value="${param.empId}"/>
	<jsp:setProperty property="leaveStartDate" name="leaveDetails" value="${date1}"/>
	<jsp:setProperty property="leaveEndDate" name="leaveDetails" value="${date2}"/>
	<jsp:setProperty property="leaveType" name="leaveDetails" value="${param.leaveType}"/>
	<jsp:setProperty property="leaveReason" name="leaveDetails" value="${param.leaveReason}"/>
	<c:out value="${leaveDao.applyLeave(leaveDetails)}" />

	</c:if>



	
</body>
</html>