
<%@
page import="com.suji.util.*" language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1 style="color:red" >Dispatche Test File</h1>

	<%
	Student s1 = (Student) request.getAttribute("s1");
	Student s2 = (Student) request.getAttribute("s2");
	Student s3 = (Student) request.getAttribute("s3");
	Student s4 = (Student) request.getAttribute("s4");
	%>
	
	<h1 style="color:red"><%= s1	 %></h1>
	<h1 style="color:blue;"><%= s2 %></h1>
	<h1 style="color:red"><%= s3	 %></h1>
	<h1 style="color:blue;"><%= s4 %></h1>

</body>
</html>