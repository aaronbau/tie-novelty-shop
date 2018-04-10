<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Administrator</title>
<link rel="stylesheet" type="text/css" href="css/stylesheet.css">
<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/script.js"></script>
<script src="js/createadmin.js"></script>
</head>
<body>
<%
	if(session.getAttribute("usertype") == null || session.getAttribute("usertype").toString().compareToIgnoreCase("Administrator") != 0)
		response.sendRedirect("/tie-novelty-shop/Home");
%>
<%@ include file="components/navbar.jsp" %>
<a style="font-size: .80em;" href="AdminControls">back to Admin Controls</a>
<h1 style="font-size: 2em;">Create Administrator</h1>
<br>
<!--	<form method="POST">
		Username
		<br>
		<input type="text" name="username" /> <br>
		Password
		<br>
		<input type="password" name="password" /> <br>		
		Email
		<br>
		<input type="email" name="email" /> 
		<br>
		<br>
		<input type="submit" value="Create"/>
	</form> -->
	<form method="POST">
			<label for="username">Username</label>
			<br> 
			<input type="text" id="admin-username" name="username" required/> 
			<br>
			<div class="error" id="admin-username-error"> </div>
			<br>
			<label for="password">Password</label>
			<br>
			<input type="password" id="admin-password" name="password" required/>
			<br>
			<div class="error" id="admin-password-error"> </div>
			<br>
			<label for="email">Email</label>
			<br>
			<input type="email" id="admin-email" name="email" required/>
			<br>
			<div class="error" id="admin-email-error"> </div>
			<br>
			<input type="submit" id="admin-submit" value="Create Administrator" disabled=true>
		</form>
</body>
</html>