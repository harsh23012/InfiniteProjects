<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="AddBooks.jsp">
	<center>
		Book Name : 
		<input type="text" name="name" /> <br/><br/>
		Author Name : 
		<input type="text" name="author" /> <br/><br/>
		Edition : 
		<input type="text" name="edition" /> <br/><br/>
		Department : 
		<input type="text" name = "dept"/> <br/> <br/>
		<input type="submit" value="Add Book" />
	</center>
	</form>
	
	<%
		if (request.getParameter("name")!=null && 
			request.getParameter("author")!=null
				) {
			
	%>
	<jsp:useBean id="books" class="com.java.library.model.Books" />
	<jsp:useBean id="libDao" class="com.java.library.dao.LibraryDaoImpl" />
	<jsp:setProperty property="*" name="books"/>
	<%=libDao.addBooks(books)%>
	<%
			}
	%>
	
</body>
</html>