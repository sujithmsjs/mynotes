<%@page import="com.suji.ctrl.AttrNames"%>
<%@page import="com.suji.util.SessionUtil"%>
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
		if(SessionUtil.isValid(session)){
			response.sendRedirect("profile.jsp");
		}
		
	%>	
	
	<a href="signup.jsp">Sign up</a>


	<form action="login">
		<fieldset>
			<legend>Login</legend>
			<input type="text" name="uname" /> <input type="password" name="pwd" />
			<input type="submit" value="Login" />
		</fieldset>
	</form>

	<%
	String message = (String) request.getAttribute(AttrNames.MESSAGE);
	if (message != null) {
		out.println("<h3>" + message + "</h3> ");
	}
	%>

</body>
</html>