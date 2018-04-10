<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reset Password</title>
<link rel="stylesheet" type="text/css" href="css/stylesheet.css">
<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/script.js"></script>
<script src="js/fromemail.js"></script>
</head>
<body>
	<%@ include file="components/navbar.jsp"%>
	<h1 style="font-size: 2em;">Password Reset</h1>
	<br>
	<%
	if((boolean)request.getAttribute("valid")) {
	%>
	<div class="center">
		<form id="password-reset-form"action="ResetFromEmail" method="POST">
			<input type="hidden" value="<% out.print(request.getParameter("id")); %>" name="id">
			<label for="current-password">Current Password</label>
			<input style="margin-left: 10px;" type="password" id="current-pass" name="current-password">
			<br>
			<div class="error" id="current-password-error"> </div>
			<br>
			<label for="new-password">New Password</label>
			<input style="margin-left: 10px;" type="password" id="new-pass" name="new-password">
			<br>
			<div class="error" id="new-password-error"> </div>
			<br>
		</form>
		<br>
		<button id="full-reset">Change Password</button>
		<div id="success" class="hidden">
			<hr>
			<p style="color: #33691E;">Password has been reset.</p>
			<a href="Home">Back to home</a>
		</div>
	</div>
	<%
		} else {
	%>
		This password reset link is invalid. <a href="Home">Click here to go to the homepage.</a>
	<%
		}
	%>
</body>
</html>