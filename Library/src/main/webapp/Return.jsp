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
		List<TranBook> booksIssued = libraryDao.accountDetails(user);
	%>
	<form action="ReturnNext.jsp" method="post">
	<table border="3" align="center">
		<tr>
			<th>Book Id</th>
			<th>UserName</th>
			<th>Issued On</th>
			<th>Return</th>
		</tr>
		<%
			for(TranBook tbook : booksIssued) {
		%>
			<tr>
				<td><%=tbook.getBookId() %>  </td>
				<td><%=tbook.getUserName() %> </td>
				<td><%=tbook.getFromDate() %> </td>
				<td>
					<input type="checkbox" name = "bookId" value = <%=tbook.getBookId() %>>	
				</td>
			</tr>
		<%
			}
		%>
	</table><br/><br/>
		<center>
		<input type="submit" value="Return Book(s)" />
		</center></form>
</body>
</html>