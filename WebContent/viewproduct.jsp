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
<%-- 	<% out.print(product.getName() + " " + product.getDescription() + " " + product.getQuantity() + " " + product.getPrice() + " " + product.getImage());--%>

<%-- 	%> --%>
	
	<div class="grid">
		<div class="grid-box">
			<div class="product-info">
				<div style="padding-top: 10%; font-size: 1.5em;">
					<%out.print(product.getName());%>
				</div>
				<br><br>
				<span class="product-price">
					<%out.print(product.getQuantity());%>
				</span>
				<br><br>
				<%out.print(product.getDescription());%>
				<br><br>
				<form method="POST">
					<label for="quantity">Quantity</label>
					<br>
					<input style="font-size: 1.2em;" type='number' name='quantity' value=1 max="<%out.print(product.getQuantity()); %>" />
					<br><br>
					<input id="add-to-cart" type='submit' value='Add to Cart' formaction="/tie-novelty-shop/AddToCart">
				</form>
			</div>
		</div>		
		<div class="grid-box" style="background-image: url(<% out.print(product.getImage()); %>)">
		</div>	
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