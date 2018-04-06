<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="model.*, dbhelper.*, java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product Manager Controls</title>
<link rel="stylesheet" type="text/css" href="css/stylesheet.css">
<script src="js/script.js"></script>
</head>
<body>
<%
	if(session.getAttribute("usertype") == null || session.getAttribute("usertype").toString().compareToIgnoreCase("Product Manager") != 0)
		response.sendRedirect("/tie-novelty-shop/Home");
%>
	<%@ include file="components/navbar.jsp"%>
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
					<div>
					<form action='/tie-novelty-shop/EditProduct' style="display: inline;"  method='GET'>
						<input type='hidden' name='product' value='<%out.print(p.getName());%>'>
						<button title="Edit Product" type='submit'>
							<img src='assets/edit.png'> 
						</button>
					</form>
					<form action='/tie-novelty-shop/DeleteProduct' style="display: inline;"  method='POST'>
						<input type='hidden' name='product' value='<%out.print(p.getName());%>'>
						<button title="Delete Product" id="remove-from-cart" type='submit'>
							<img src='assets/garbage.png'> 
						</button>
					</form>
					</div>
					</div>
			<%
				}
			%>
		</div>
</body>
</html>