<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reset Password</title>
<link rel="stylesheet" type="text/css" href="css/stylesheet.css">
<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/reset.js"></script>
<script src="js/script.js"></script>
</head>
<body>
	<%@ include file="components/navbar.jsp"%>
	<h1 style="font-size: 2em;">Password Reset</h1>
	<div class="center">
		<form action="ResetPassword" method="POST">
			<label for="username">Enter your username</label>
			<input style="margin-left: 10px;" type="text" id="reset-username" name="username">
			<span class="error" id="reset-username"></span>
		</form>
		<br>
		<button id="reset">Reset Password</button>
		<br>
		<br>
		<div id="success" class="hidden">
			<hr>
			<p style="color: #33691E;">An email has been set to your account's email address to reset your password.</p>
			<a href="Home">Back to home</a>
		</div>
	</div>
</body>
</html>