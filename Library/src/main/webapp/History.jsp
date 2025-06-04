<%@page import="com.java.library.model.History"%>
<%@page import="com.java.library.model.TranBook"%>
<%@page import="com.java.library.model.TranBook"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="Menu.jsp" />
	<jsp:useBean id="libraryDao" class="com.java.library.dao.LibraryDaoImpl" />
	<%
		String user = (String)session.getAttribute("user");
		List<History> booksIssued = libraryDao.historyDetails(user);
	%>
	<center><h3><b>Book Issued History</b></h3></center>
	<table border="3" align="center">
		<tr>
			<th>UserName</th>
			<th>Book Id</th>
			<th>Issued On</th>
			<th>Returned On</th>
		</tr>
		<%
			for(History tbook : booksIssued) {
		%>
			<tr>
				<td><%=tbook.getUserName() %> </td>
				<td><%=tbook.getBookId() %>  </td>
				<td><%=tbook.getFromDate() %> </td>
				<td><%=tbook.getReturnedDate() %> </td>
			
			</tr>
		<%
			}
		%>
	</table>
</body>
</html>