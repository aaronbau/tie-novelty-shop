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
	<%
		if(session.getAttribute("username".toString()) == null) 
			out.print("<form method='GET'><input type='submit' formaction='/tie-novelty-shop/Login' value='Log In' /><input type='submit' formaction='/tie-novelty-shop/Signup' value='Sign Up' /></form>");
		else
			out.print("<form method='GET'><input type='submit' formaction='/tie-novelty-shop/Logout' value='Log Out' /></form>");
	
		ArrayList<Product> products = (ArrayList<Product>) request.getAttribute("productList");
	
		for(Product product: products) {
			out.println("<form method='GET'><input type='submit' name='productName' formaction='/tie-novelty-shop/ViewProduct' value='" + product.getName() + "," + product.getPrice() + "'></form>");
		
		}
	%>
</body>
</html>