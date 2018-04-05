<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/signup.js"></script>
</head>
<body>
	<div class="center">
		<button class="close-button" onclick="hideOverlay()">X</button>
		<div style="text-align: center;">
			Sign-up for Cravate
		</div>
		<br> 
		<form id="signup" action="Signup" method="POST">
			<label for="username">Username</label> 
			<br> 
			<input type="text" name="username" required/> 
			<br> 
			<label for="password">Password</label>
			<br>
			<input type="password" name="password" required/>
			<br>
			<label for="email">Email</label>
			<br>
			<input type="email" name="email" required/>
			<br>
			<br>
		</form>
		<div id="error"></div>
		<button id="signup-button">Sign-up</button>
	</div>
</body>
</html>