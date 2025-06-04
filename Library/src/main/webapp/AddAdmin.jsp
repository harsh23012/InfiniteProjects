<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="AddAdmin.jsp">
	<center>
		User Name : 
		<input type="text" name="userName" /> <br/><br/>
		Password : 
		<input type="password" name="password" /> <br/><br/>
		Re-Type Password : 
		<input type="password" name="retypePassCode" /> <br/><br/> 
		<input type="submit" value="Create Account" />
	</center>
	</form>
	
	<%
		if (request.getParameter("userName")!=null && 
			request.getParameter("password")!=null
				) {
			String pwd = request.getParameter("password");
			String reType = request.getParameter("retypePassCode");
			if (pwd.equals(reType)) {
	%>
	<jsp:useBean id="libAdmins" class="com.java.library.model.LibAdmins" />
	<jsp:useBean id="libDao" class="com.java.library.dao.LibraryDaoImpl" />
	<jsp:setProperty property="*" name="libAdmins"/>
	<%=libDao.createAdmin(libAdmins) %>
	<%
			}
		}
	%>
	
</body>
</html>