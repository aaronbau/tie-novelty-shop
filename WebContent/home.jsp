<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.*,java.util.ArrayList" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>
	${sessionScope.username }
	${sessionScope.usertype }
	<%
		if(session.getAttribute("username".toString()) == null) 
			out.print("<form method='GET'><input type='submit' formaction='/tie-novelty-shop/Login' value='Log In' /><input type='submit' formaction='/tie-novelty-shop/Signup' value='Sign Up' /></form>");
		else
			out.print("<form method='GET'><input type='submit' formaction='/tie-novelty-shop/Logout' value='Log Out' /><input type='submit' formaction='/tie-novelty-shop/AddProduct' value='Add Item' /><input type='submit' formaction='/tie-novelty-shop/ViewCart' value='My Cart' /><input type='submit' formaction='/tie-novelty-shop/ViewPurchases' value='My Purchases' /><input type='submit' formaction='/tie-novelty-shop/AllPurchases' value='All Purchases' /></form>");
		
		out.print("<form method='GET'><input type='text' name='search' /><input type='submit' formaction='/tie-novelty-shop/SearchProducts' value='Search' /></form>");
	
		out.print("<form method='POST'><select name='filter'><option selected disabled hidden>Select Filter</option><option value='f1'>A-Z</option><option value='f2'>Z-A</option><option value='f3'>Lowest Price</option><option value='f4'>Highest Price</option></select><input type='submit' formaction='/tie-novelty-shop/SearchProducts' value='Filter' /></form>");
	
		ArrayList<Product> products = (ArrayList<Product>) request.getAttribute("productList");
	
		for(Product product: products) {
			out.println("<form method='GET'>");
			out.println("<input type='hidden' name='productName' value='"+ product.getName() +"' />");
			out.println("<input type='hidden' name='productDescription' value='"+ product.getDescription() +"' />");
			out.println("<input type='hidden' name='productQuantity' value='"+ product.getQuantity() +"' />");
			out.println("<input type='hidden' name='productPrice' value='"+ product.getPrice() +"' />");
			out.println("<input type='hidden' name='productImage' value='"+ product.getImage() +"' />");
			out.println(product.getName() + " " + product.getPrice());
			out.println("<input type='submit' formaction='/tie-novelty-shop/ViewProduct' value='View Product' />");
			out.println("</form>");
		}		
	%>
	<form method='GET'>
		<input type='submit' formaction='/tie-novelty-shop/CreateProductManager' value='Create Product Manager' />
		<input type='submit' formaction='/tie-novelty-shop/CreateAdministrator' value='Create Administrator' />
	</form>
</body>
</html>
		