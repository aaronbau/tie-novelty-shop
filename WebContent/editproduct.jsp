<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Product</title>
</head>
<body>
	<% 
	Product product = (Product) session.getAttribute("product");
	%>
	
	<form method="POST">
		Name: <input type="text" name="name" value="<%=product.getName() %>"/> <br>
		Description: <input type="text" name="description" value="<%=product.getDescription() %>"/> <br>
		Quantity: <input type="text" name="quantity" value="<%=product.getQuantity() %>"/> <br>
		Price: <input type="text" name="price" value="<%=product.getPrice() %>"/> <br>
		<input type="submit" name="done" />
	</form>
	
	<form method="GET">		
		<input type="submit" value="Home" formaction="/tie-novelty-shop/Home" />
	</form>
</body>
</html>