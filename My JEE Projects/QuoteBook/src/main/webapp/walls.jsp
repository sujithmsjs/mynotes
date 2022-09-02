<%@page import="com.suji.ctrl.AttrNames"%>
<%@page import="com.suji.mod.Quote"%>
<%@page import="java.util.List"%>
<%@page import="com.suji.mod.QuotePagition"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@include file="serviceLinks.jsp" %>
	
	<%
	List<Quote> quotes = QuotePagition.getRecords(1);
	
	for(int i =0; i< quotes.size();i++){
	%>
	<table style="width: 100%">
		<tr>
			<td><%= quotes.get(i).getUid() %></td>
		</tr>
		<tr>
			<td><%= quotes.get(i).getQuote() %></td>
		</tr>
		<tr>
			<td><%= quotes.get(i).getAuthar() %></td>
		</tr>
	</table>

	<%} %>
	
</body>
</html>