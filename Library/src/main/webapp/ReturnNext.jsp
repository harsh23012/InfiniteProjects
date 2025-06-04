<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<jsp:include page="Menu.jsp"/>
	 <jsp:useBean id="libraryDao" class="com.java.library.dao.LibraryDaoImpl"/>
	 <%
	 String[] bookList = request.getParameterValues("bookId");
	 String user = (String)session.getAttribute("user");
	 for(String s : bookList){
		 int bid = Integer.parseInt(s);
		 int days = libraryDao.countDaysOfIssuedBook(bid);
		 if(days <= 21){			 
		 	out.println(libraryDao.returnBook(user, bid));
		 }
		 else{
			 out.println("You are Returning Book After 21 Days. So You have to fine of Rs : " + (days-21)*5);
			 %>
			 <center>
			 	<a href="PayFine.jsp?bookId=<%=bid%>&days=<%=days%>"> <b>Pay Fine & Return</b></a>
			 </center>
			 <%
		 }
		 out.println("<br/>");
	 }
	 %>

</body>
</html>