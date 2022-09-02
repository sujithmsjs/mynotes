<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Promise Card</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js">
	<link rel="shortcut icon" type="image/x-icon" href="icon.png" />
</script>

<%
String name = request.getAttribute("name").toString();
String verseNo = request.getAttribute("verseNo").toString();
String verse = request.getAttribute("verse").toString();
String ref = request.getAttribute("ref").toString();
%>




<style>
body {
	background: rgb(0, 52, 114);
	background: linear-gradient(90deg, hsl(<%=verseNo%>, 100%, 30%) 0%,
		hsl(<%=verseNo%>, 100%, 50%) 50%, hsl(<%=verseNo%>, 100%, 30%)
		100%);
}
</style>
</head>
<body>
	<div class="container-fluid p-5 text-white text-center">
		<h4>
			Dear
			<%=name%>,
		</h4>
		<p>God is telling you...</p>
	</div>

	<div class="container-fluid p-8  text-white text-center">

		<h1><%=verse%></h1>
	</div>
	<div class="container-fluid p-5  text-white text-center">

		<h5>
			@<%=ref%></h5>
	</div>

	<div style="height: 100px"></div>

	<div class="container-fluid p-5 	 text-center">


		<div class="btn-group">
			<a href="home.html" class="btn btn-light">Check for Others</a> <a
				href="whatsapp://send?text= Hey there, Check your 2022 Promise Card here. Click the link and find out within half minitue. http://ec2-3-110-177-121.ap-south-1.compute.amazonaws.com:2022/BiblePromise/ "
				data-action="share/whatsapp/share" target="_blank"
				class="btn btn-primary" role="button" aria-disabled="true">Share
				Link</a>

			<button class="btn btn-light" onClick="window.location.reload();">
				Change Color</button>
		</div>

		<div class="container-fluid p-5 text-white text-center">
			<p>
				Thank you <b><%=name%></b> for your curiosity about Bible. Please
				share it with your friends and family members. 
			</p>
			<p>
				<b>Remember : </b>Every promise in the Bible is yours if you
				apply it to your life. You need to follow Jesus and do what he said,
				then only you can possess the promises. Start reading bible from
				this new year onwards.
			</p>

		</div>
</body>
</html>
