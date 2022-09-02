<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		//String myname = application.getAttribute("myname").toString();
		String myname1 = application.getInitParameter("myname");
		String myname2 = application.getInitParameter("hey");
	%>

	<h1 style="color:red"><%= myname1	 %></h1>
	<h1 style="color:blue;"><%= myname2 %></h1>
</body>
</html>