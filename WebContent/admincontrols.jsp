<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page import="model.*, dbhelper.*, java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Controls</title>
<link rel="stylesheet" type="text/css" href="css/stylesheet.css">
<script src="js/script.js"></script>
</head>
<body>
	<%@ include file="components/navbar.jsp"%>
	<h1 style="font-size: 2em;">Accounts</h1>
	<br>
	<div>
		<div>
			<form>
				<input type="submit" formaction="CreateAdministrator"
					value="Create Administrator">
			</form>
			<form>
				<input type="submit" formaction="CreateProductManager"
					value="Create Product Manager">
			</form>
		</div>
		<br>
		<h1 style="font-size: 2em;">Products</h1>
			<form>
				<input type="submit" formaction="AddProduct" value="Add Product">
			</form>
		<br>
		<div>
			<%
				DBUtilities db = new DBUtilities();
				ArrayList<Product> products = db.getArrayListAlphabeticalProducts();
				
				for(Product p : products)
				{
			%>
				 <div class="cart-item">
					<span>
						<%out.println(p.getName());%>
					</span>
					<span>
						In stock: 
						<%out.println(p.getQuantity());%>
					</span>
					<span>
						Price:
						<%out.println(p.getPrice());%>
					</span>
					<form action='/tie-novelty-shop/EditProduct' style="display: inline;"  method='GET'>
						<input type='hidden' name='product' value='<%out.print(p.getName());%>'>
						<button type='submit'>
							<img src='assets/edit.png'> 
						</button>
					</form>
					</div>
			<%
				}
			%>
		</div>
	</div>
</body>
</html>