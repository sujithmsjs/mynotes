<%@page import="com.suji.ctrl.AttrNames"%>
<%@page import="com.suji.mod.User"%>
<%@page import="com.suji.util.SessionUtil"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%
		boolean isValid = false;
		if(SessionUtil.isValid(session)){
		 	User user = SessionUtil.getUser(session);
			out.println("<h1>User Details: "+user+"</h1>");
			out.println("<h4>Session id : "+session.getId()+"</h4>");
			isValid = true;
		}else{
		
			session.invalidate();
			response.sendRedirect("login.jsp");
		}
	
	%>


<a href="profile.jsp">Profile</a>
|
<a href="walls.jsp">Walls</a>
|
<a href="post.jsp">Post</a>
|
<a href="logout">Logout</a>
<%
	if(isValid){
		
		String msg = (String) session.getAttribute(AttrNames.MESSAGE);
		if(msg != null){
			out.println("<h3>"+msg+"</h3>");
			session.removeAttribute(AttrNames.MESSAGE);
		}
	}

%>
	


<hr />