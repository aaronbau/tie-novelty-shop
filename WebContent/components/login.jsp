<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>

<div class="center">
	<button class="close-button" onclick="hideOverlay()">X</button>
	<div style="text-align: center;">
		Log-in to CRAVATE
	</div>
	<br>
	<form action="Login" method="POST" >
		<label for="username">Username</label>
		<br>
		<input type="text" name="username" required>
		<br>
		<label for="password">Password</label>
		<br>
		<input type="password" name="password" required>
		<br>
		<br>
		<input type="submit" value="Log in">
	</form>
</div>
</body>
</html>