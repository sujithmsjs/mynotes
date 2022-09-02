<%@page import="java.sql.ResultSet"%>
<%@page import="com.suji.mod.EndUser"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title> User Data</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

	<div class="container mt-3">
		<h2>Bordered Table</h2>
		<p>The .table-bordered class adds borders on all sides of the
			table and the cells:</p>
<%
// Name, DOB, IP, TIME
 ResultSet rs =	EndUser.getResultSet();
	while(rs.next()){
%>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th><%= rs.getString(1) %></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><%= rs.getString(2) %></td>
				</tr>
				
				<tr>
					<td><%= rs.getString(3) %></td>
				</tr>
			</tbody>
		</table>
<%
	}
	rs.close();
%>

	</div>

</body>
</html>
