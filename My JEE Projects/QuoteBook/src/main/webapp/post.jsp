<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%@include file="serviceLinks.jsp"%>

	<form action="postQuote">

		Quote: <br />
		<textarea rows="5" cols="30" name="quote"></textarea>
		<br />
		<br /> Author: <input type="text" name="author" /> <br />
		<br /> <input type="submit" value="Share Quote">

	</form>

</body>
</html>