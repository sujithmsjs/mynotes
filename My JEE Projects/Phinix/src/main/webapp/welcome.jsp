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
	 	Cookie[] cks = request.getCookies();
		String name = null;
		for(Cookie ck : cks){
			if( ck.getName().equalsIgnoreCase("username") ){
				name = ck.getValue();
			}
		}
	%>
	
	<h1> Welcome <%= name %></h1>

</body>
</html>