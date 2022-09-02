<%@page import="com.suji.ctrl.AttrNames"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form action="signup" method="get">
		<input type="text" placeholder="Full name" name="fname" />
		<input type="text" placeholder="Username" name="uname" />
		<input type="password" placeholder="Passwrod" name="pwd" />
		<input type="submit" value="Signup" />
	</form>
	
	<%
		String msg = (String) request.getAttribute(AttrNames.MESSAGE);
		
		if(msg != null){
		
			 request.removeAttribute(AttrNames.MESSAGE);
		%>
		<h4 style="color: green"><%= msg %></h4>
		<%
		}
	 %>
	
	
</body>
</html>