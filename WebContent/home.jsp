<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="model.*,java.util.ArrayList"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<link rel="stylesheet" type="text/css" href="css/stylesheet.css">
<script src="js/script.js"></script>
</head>
<body>
	<%@ include file="components/navbar.jsp" %>
	<%@ include file="components/productlisting.jsp" %>
	
	<div class="hidden overlay">
		<div class="hidden account-forms center" id="login-element">
			<%@ include file = "components/login.jsp" %>
		</div>
		<div class="hidden account-forms center" id="signup-element">
			<%@ include file = "components/signup.jsp" %>
		</div>
	</div>	
</body>
</html>
