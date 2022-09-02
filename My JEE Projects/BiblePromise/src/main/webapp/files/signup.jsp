<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
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
		<h2>Stacked form</h2>
		
		<form action="signup" method="post" enctype="multipart/form-data">
		
			<div class="mb-3">
				<label for="name">Username:</label> <input type="text"
					class="form-control" id="name" placeholder="Username" name="name"
					required>
			</div>

			<div class="mb-3">
				<label for="pwd">Password:</label> <input type="password"
					class="form-control" id="pwd" placeholder="Enter password"
					name="password" required>
			</div>


			<div class="mb-3">
				<label for="dob">Date of birth:</label> <input type="date"
					class="form-control" id="dob" name="dob" required>
			</div>


			<div class="mb-3">
				<label for="customRange" class="form-label">Marks</label> <input
					type="range" class="form-range" id="customRange" name="marks">
			</div>

			<div class="mb-3">
				<label for="formFile" class="form-label">Upload Profile Picture
				</label> <input class="form-control" name="file" type="file" id="formFile">
			</div>


			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>


</body>
</html>
