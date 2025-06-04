<%@page import="com.java.library.model.Books"%>
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
	<jsp:useBean id="libraryDao" class="com.java.library.dao.LibraryDaoImpl" /><br/>
	<%
		String searchType = request.getParameter("searchtype");
		String searchValue = request.getParameter("searchvalue");
		List<Books> booksList = libraryDao.searchBooks(searchType, searchValue);
	%>
	<form action="Issue.jsp" method="post">
	<table border="3" align="center">
		<center><h3> <b> Book List </b> </h3></center>	
	<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Author</th>
			<th>Edition</th>
			<th>Department</th>
			<th>Total Books</th>
			<th>Select</th>
		</tr>
		<%
			for(Books book : booksList) {
				int bTotal = book.getNoOfBooks();
		%>
			<tr>
				<td> <%=book.getId() %> </td>
				<td> <%=book.getName() %> </td>
				<td> <%=book.getAuthor() %>  </td>
				<td><%=book.getEdition() %>  </td>
				<td> <%=book.getDept() %> </td>
				<td> <%=book.getNoOfBooks() %> </td>
				<td>
					<%
					 	if(bTotal > 0){
					 %>
					 
					 <input type="checkbox" name='bookid' value=<%=book.getId()%>>
					 <% 
					 	}
					%>
				</td>
			</tr>
		<%
			}
		%>
	</table><br/><br/>
		<center>
		<input type="submit" value="Issue Book(s)" />
		</center>
	</form>
</body>
</html>