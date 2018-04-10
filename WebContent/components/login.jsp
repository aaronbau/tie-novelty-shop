<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/login.js"></script>
</head>
<body>

<div class="center">
	<button class="close-button" onclick="hideOverlay()">X</button>
	<div style="text-align: center;">
		Log-in to Cravate
	</div>
	<br>
	<form action="Login" id="login-form" method="POST" >
		<label for="username">Username</label>
		<br>
		<input type="text" name="username" required>
		<br>
		<br>
		<label for="password">Password</label>
		<br>
		<input type="password" name="password" required>
		<br>
		<br>
	</form>
	<button id="login-button">Log-in</button><span style="margin-left: 10px;" class="error" id="login"></span>
	<br>
	<br>
	<a href="ResetPassword" style="font-size: 0.8em;">Forgot your password?</a>
</div>
</body>
</html>