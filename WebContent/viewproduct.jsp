<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.Product"%>
    
<%
		Product product = (Product) session.getAttribute("product");
%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><% out.print(product.getName());%></title>
<link rel="stylesheet" type="text/css" href="css/stylesheet.css">
<script src="js/script.js"></script>
</head>
<body>
	<%@ include file="components/navbar.jsp" %>	
	<a style="font-size: .80em;" href="Home">back to Home</a>
	<br><br>
	<div class="grid">
		<div class="grid-box">
			<div class="product-info">
				<h1 style="font-size: 2em;"><%out.print(product.getName());%></h1>
				<span class="product-price" style="font-size: 1.2em;">
					<%out.print(product.getPrice());%>
				</span>
				<br><br>
				<span style="font-size: 1em;">
					<%out.print(product.getDescription());%>
				</span>
				<br><br><br><br>
				<% if (session.getAttribute("username") == null) {%>
				<button id="add-to-cart" onclick="showLogin()">Log In to Add To Cart</button>
				<% } else { %>
				<form method="POST">
					<label for="quantity">Quantity in Stock: <% out.print(product.getQuantity()); %></label>
					<br>
					<input style="font-size: 1.2em;" type='number' name='quantity' value=1 min=1 max="<%out.print(product.getQuantity()); %>" />
					<br><br><br><br>
					<input id="add-to-cart" type='submit' value='Add to Cart' formaction="/tie-novelty-shop/AddToCart">
				</form>
				<% } %>
			</div>
		</div>		
		<div class="grid-box" style="background-image: url(<% out.print(product.getImage()); %>)">
		</div>	
	</div>
	

<!-- 	<form method="GET"> -->
<!-- 		<input type="submit" value="Home" formaction="/tie-novelty-shop/Home" /> -->
<!-- 		<input type='submit' value='Edit' formaction="/tie-novelty-shop/EditProduct"> -->
<!-- 	</form> -->
	
<!-- 	<form method="POST"> -->
<!-- 		<input type='submit' value='Edit' formaction="/tie-novelty-shop/EditProduct"> -->
<!-- 		<input type='submit' value='Delete' formaction="/tie-novelty-shop/DeleteProduct"> -->
<!-- 		<input type='number' name='quantity' /> -->
<!-- 		<input type='submit' value='Add to Cart' formaction="/tie-novelty-shop/AddToCart"> -->
<!-- 	</form> -->

</body>
</html>