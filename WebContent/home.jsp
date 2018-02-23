<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="model.*,java.util.ArrayList"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<link rel="stylesheet" type="text/css" href="stylesheet.css">
</head>
<body>
	<div class="navbar">
		<div id="login-links">
			${sessionScope.username}
			<%
				if (session.getAttribute("username".toString()) == null) {
			%>
			<a href="/TieNoveltyShop/Signup">Sign Up</a> 
			<a href="/TieNoveltyShop/Login">Log In </a>
			<!-- 			<form method='GET'> -->
			<!-- 				<input type='submit' formaction='/TieNoveltyShop/Login' value='Log In' /> -->
			<!-- 				<input type='submit' formaction='/TieNoveltyShop/Signup' value='Sign Up' /> -->
			<!-- 			</form> -->
			<%
				} else {
			%>
			<form method='GET'>
				<input type='submit' formaction='/TieNoveltyShop/Logout'
					value='Log Out' />
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
		<form method='GET'>
			<input type='submit' name='productName'
				formaction='/TieNoveltyShop/ViewProduct'
				value="<%out.print(product.getName() + " - " + product.getPrice());%> ">
		</form>
		<%
			}
		%>
</body>
</html>