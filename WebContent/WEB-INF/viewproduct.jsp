<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.Product"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Product</title>
</head>
<body>
	<%
		Product product = (Product) request.getAttribute("product");
		
		out.print(product.getName() + " " + product.getDescription() + " " + product.getQuantity() + " " + product.getPrice() + " " + product.getImage());
	%>
	<form method="GET">
		<input type="submit" value="Home" formaction="/tie-novelty-shop/Home" />
	</form>
	
	<form method="POST">
		<input type='submit' value='Edit' formaction="/TieNoveltyShop/EditProduct">
		<input type='submit' value='Delete' formaction="/TieNoveltyShop/DeleteProduct">
	</form>
</body>
</html>