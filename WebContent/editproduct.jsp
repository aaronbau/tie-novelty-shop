<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Product</title>
<link rel="stylesheet" type="text/css" href="css/stylesheet.css">
<script src="js/script.js"></script>
</head>
<body>
<%
	if(session.getAttribute("usertype") == null || session.getAttribute("usertype").toString().compareToIgnoreCase("Product Manager") != 0)
		response.sendRedirect("/tie-novelty-shop/Home");
%>
	<%@ include file="components/navbar.jsp" %>
	<a style="font-size: .80em;" href="ProductManagerControls">back to Product Manager Controls</a>
	<br>
	<br>
	<% 
	Product product = (Product) session.getAttribute("product");
	%>
	<div class="grid">
		<div class="grid-box">
			<div class="product-info">
				<div">
				<form method="POST">
					Name
					<br>
					<% out.print(product.getName()); %>
					<br><br>
					Description
					<br>
					<input type="text" name="description" value="<%=product.getDescription() %>"/> 
					<br><br>
					In Stock
					<br>
					<input type="text" name="quantity" value="<%=product.getQuantity() %>"/>
					<br><br>
					Price
					<br>
					<input type="text" name="price" value="<%=product.getPrice() %>"/>
					<br><br><br>
					<input type="submit" name="done" value="Edit Product"/>
				</form>
			</div>
		</div>		
		<div class="grid-box" style="background-image: url(<% out.print(product.getImage()); %>)">
		</div>	
	</div>
	</div>
</body>
</html>