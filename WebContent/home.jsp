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
	<div class="navbar">
		<div id="search-bar">
			<input type="text" />
		</div>
		<div id="login-links">
			<%
				if (session.getAttribute("username".toString()) == null) {
			%>
			<button style="margin-right: 10px;" onclick="showSignup()">Sign Up</button>
			<button onclick="showLogin()">Log In</button>
			<%
				} else {
			%>
			<span style="margin-right: 10px;"> ${sessionScope.username} </span>
			<form>
				<input formaction="/tie-novelty-shop/Logout" type="submit"
					value="Log Out" />
			</form>
			<%
				}
			%>
		</div>
	</div>
	<%
		ArrayList<Product> products = (ArrayList<Product>) request.getAttribute("productList");
		for (Product product : products) {
	%>
	<img src="<%out.print(product.getImage());%>" onhover="showName()"
		onclick="toProduct(<%out.print(product.getName());%>)">
	<!-- 		<form method='GET'> -->
	<!-- 			<input type='submit' name='productName' -->
	<!-- 				formaction='/TieNoveltyShop/ViewProduct' -->
	<%-- 				value="<%out.print(product.getName() + " - " + product.getPrice());%> "> --%>
	<!-- 		</form> -->
	<%
		}
	%>
	<div class="hidden overlay" onclick="hideOverlay()">
		<div class="hidden" id="signup-element">
			<%@ include file = "signup.jsp" %>
		</div>
		<div class="hidden" id="login-element">
			<%@ include file = "login.jsp" %>
		</div>
	</div>
</body>
</html>