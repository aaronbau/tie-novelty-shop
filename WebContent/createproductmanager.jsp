<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Product Manager</title>
<link rel="stylesheet" type="text/css" href="css/stylesheet.css">
<script src="js/script.js"></script>
</head>
<body>
<%@ include file="components/navbar.jsp" %>
<a style="font-size: .80em;" href="AdminControls">back to Admin Controls</a>
<h1 style="font-size: 2em;">Create Product Manager</h1>
<br>
	<form method="POST">
		Username
		<br>
		<input type="text" name="username" required/> 
		<br>
		Password
		<br>
		<input type="password" name="password" required/>
		<br>		
		Email
		<br>
		<input type="email" name="email" required/>
		<br>
		<br>
		<input type="submit" value="Create"/>
	</form>
</body>
</html>