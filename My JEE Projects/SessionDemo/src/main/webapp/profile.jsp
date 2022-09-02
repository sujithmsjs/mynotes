<%@page import="com.suji.util.AttrNames"%>
<%@ page session="false" language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>

<body>

	<jsp:include page="links.html" />

	<%
	// Declarations
	String msg = (String) request.getAttribute(AttrNames.MESSAGE);
	HttpSession session = request.getSession(false);

	if (msg != null) {
		out.println("<h3>" + msg + "</h3>");
	}

	/*

	if session is null then You must login first.
	else if (userName is null) then Session Broken, redirect to logout.
	else get usserName and show Welcome message.

	*/

	if (session == null) {
		out.println("<h1 style='color: red'>You must login first.</h1>");

	} else if (session.getAttribute(AttrNames.USER_NAME) == null) {
		out.println("<h1> Null Name session: " + session.getId() + " </h1>");
		out.println("<h1 style='color: red;'>Session has Expired or Broken.</h1>");

	} else {
		String userName = (String) session.getAttribute(AttrNames.USER_NAME);
		String msg2 = (String) session.getAttribute(AttrNames.MESSAGE);
		session.removeAttribute(AttrNames.MESSAGE);
		if(msg2 != null){
			out.println("<h4 style='blue: red'>Welcome "+msg2+"</h4>");
		}
		
		
		out.println("<h1 style='color: red'>Welcome "+userName+"</h1>");
	}
	%>


</body>
</html>