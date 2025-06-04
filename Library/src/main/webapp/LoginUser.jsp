<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="LoginUser.jsp">
<center>
<h2>Please Login (User) :</h2>
Username : <input type="text" name="userName" size="50"> <br/><br/>
Password : <input type="password" name="password" size="50"> <br/><br/>
<input type="submit" value="Login">
</center>
</form>
<%
	if (request.getParameter("userName")!=null && 
		request.getParameter("password")!=null) {
		String user = request.getParameter("userName");
		String pwd = request.getParameter("password");
%>
	<jsp:useBean id="libUsers" class="com.java.library.model.LibUsers" />
	<jsp:setProperty property="*" name="libUsers"/>
	<jsp:useBean id="libraryDao" class="com.java.library.dao.LibraryDaoImpl" />
	
<%
	int count = libraryDao.loginUser(libUsers);
	if (count==1) {
		session.setAttribute("user", request.getParameter("userName"));
%>
	<jsp:forward page="Menu.jsp" />
<%
	} else {
		out.println("Invalid Credentials...");
		%>
		<a href="LoginUser.jsp">Try Again</a> OR <a href="AddUser.jsp">Create Account</a>
		<%
	}
	}
%>
</body>
</html>