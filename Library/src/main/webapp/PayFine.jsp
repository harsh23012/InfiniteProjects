<%@ page import="com.java.library.dao.LibraryDaoImpl" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Pay Fine</title>
</head>
<body>

<jsp:useBean id="libraryDao" class="com.java.library.dao.LibraryDaoImpl" />

<%
	String user = (String) session.getAttribute("user");
	String bookIdStr = request.getParameter("bookId");
	String daysStr = request.getParameter("days");

	if (request.getMethod().equalsIgnoreCase("GET") && bookIdStr != null && daysStr != null) {
		int bookId = Integer.parseInt(bookIdStr);
		int days = Integer.parseInt(daysStr);
		int fine = (days - 21) * 5;
%>

	<center>
		<h2>Pay Fine</h2>
		<p>Fine Amount: Rs <%=fine%></p>
		<form method="post">
			<input type="hidden" name="bookId" value="<%=bookId%>" />
			<input type="hidden" name="fine" value="<%=fine%>" />
			<input type="submit" value="Pay & Return Book" />
		</form>
	</center>

<%
	} else if (request.getMethod().equalsIgnoreCase("POST")) {
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		String result = libraryDao.returnBook(user, bookId);
%>
	<center>
		<h3>Fine Paid Successfully!</h3>
		<p><%= result %></p>
		<a href="Menu.jsp">Go to Home</a>
	</center>
<%
	}
%>

</body>
</html>
