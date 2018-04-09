<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="components/navbar.jsp"%>
	<h1 style="font-size: 2em;">Password Reset</h1>
	<div class="center">
		<form action="ResetFromEmail" method="POST">
			<label for="current-password">Current Password</label>
			<input style="margin-left: 10px;" type="password" id="current-pass" name="current-password">
			<label for="new-password">New Password</label>
			<input style="margin-left: 10px;" type="password" id="new-pass" name="new-password">
			<label for="new-password-retype">Retype New Password</label>
			<input style="margin-left: 10px;" type="password" id="new-pass-retype" name="new-password-retype">
		</form>
		<br>
		<button id="reset">Change Password</button>
	</div>
</body>
</html>