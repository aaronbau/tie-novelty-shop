<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Product Manager</title>
<link rel="stylesheet" type="text/css" href="css/stylesheet.css">
<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/script.js"></script>
<script src="js/pm.js"></script>

</head>
<body>
<%
	if(session.getAttribute("usertype") == null || session.getAttribute("usertype").toString().compareToIgnoreCase("Administrator") != 0)
		response.sendRedirect("/tie-novelty-shop/Home");
%>
<%@ include file="components/navbar.jsp" %>
<a style="font-size: .80em;" href="AdminControls">back to Admin Controls</a>
<h1 style="font-size: 2em;">Create Product Manager</h1>
<br>	
		<form method="POST">
			<label for="username">Username</label>
			<br> 
			<input type="text" id="pm-username" name="username" required/> 
			<br>
			<div class="error" id="pm-username-error"> </div>
			<br>
			<label for="password">Password</label>
			<br>
			<input type="password" id="pm-password" name="password" required/>
			<br>
			<div class="error" id="pm-password-error"> </div>
			<br>
			<label for="email">Email</label>
			<br>
			<input type="email" id="pm-email" name="email" required/>
			<br>
			<div class="error" id="pm-email-error"> </div>
			<br>
			<input type="submit" id="pm-submit" value="Create Product Manager" disabled=true>
		</form>
</body>
</html>