<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Item</title>
<link rel="stylesheet" type="text/css" href="css/stylesheet.css">
<script src="js/script.js"></script>
</head>
<body>
<%@ include file="components/navbar.jsp" %>
<a style="font-size: .80em;" href="ProductManagerControls">back to Product Manager Controls</a>
<h1 style="font-size: 2em;">Add New Product</h1>
	<form method="POST" enctype="multipart/form-data">
		Name
		<br>
		<input type="text" name="name" required/> 
		<br>
		Description
		<br>
		<input type="text" name="description" required/>
		<br>
		Quantity
		<br>
		<input type="text" name="quantity" required/>
		<br>
		In Stock
		<br>
		<input type="text" name="price" required/>
		<br>
		<br>
		<input type="file" name="image" value="Add Image" required/>
		<br>
		<br>
		<input type="submit" value="Add Product" formaction="/tie-novelty-shop/AddProduct"/>	
	</form>
</body>
</html>