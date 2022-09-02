<%@ page import="javax.websocket.Session"%>
<%@ page import="com.suji.util.AttrNames"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body bgcolor="yellow">

	<jsp:include page="links.html" />

	<%
	// If session is not null then Redirect to Profile with a message.
	HttpSession session = request.getSession(false);
	if (request.getSession(false) != null) {
		session.setAttribute(AttrNames.MESSAGE, "You're session hasn't logged out yet.");
		response.sendRedirect("profile.jsp");
	}
	%>

	<form action="login">
		<fieldset>
			<legend>Login</legend>
			<input type="text" name="uname" /> <input type="password" name="pwd" />
			<input type="submit" value="Sing up" />
		</fieldset>
	</form>
	
	<%
	// If message is not null show it.
	String msg = (String) request.getAttribute(AttrNames.MESSAGE);
	if (msg != null) {
		out.println("<h4 style='color: fuchsia;'>" + msg + "</h4>");
	}
	%>


</body>
</html>