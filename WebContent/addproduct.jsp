<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Item</title>
</head>
<body>
	<form method="POST" enctype="multipart/form-data">
		Name: <input type="text" name="name" /> <br>
		Description: <input type="text" name="description" /> <br>
		Quantity: <input type="text" name="quantity" /> <br>
		Price: <input type="text" name="price" /> <br>
		Image <input type="file" name="image" value="Choose File" /> <br>
		<input type="submit" value="Done" formaction="/tie-novelty-shop/AddProduct" />	
	</form>
</body>
</html>