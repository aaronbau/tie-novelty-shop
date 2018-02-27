<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.*,java.util.ArrayList" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>All Purchases</title>
<link rel="stylesheet" type="text/css" href="css/stylesheet.css">
<script src="js/script.js"></script>
</head>
<body>
	<%@ include file="components/navbar.jsp" %>	
	<%
		if(session.getAttribute("username".toString()) == null) 
			out.print("<form method='GET'><input type='submit' formaction='/tie-novelty-shop/Login' value='Log In' /><input type='submit' formaction='/tie-novelty-shop/Signup' value='Sign Up' /></form>");
		else
			out.print("<form method='GET'><input type='submit' formaction='/tie-novelty-shop/Logout' value='Log Out' /><input type='submit' formaction='/tie-novelty-shop/AddProduct' value='Add Item' /><input type='submit' formaction='/tie-novelty-shop/ViewCart' value='My Cart' /><input type='submit' formaction='/tie-novelty-shop/ViewPurchases' value='My Purchases' /><input type='submit' formaction='/tie-novelty-shop/AllPurchases' value='All Purchases' /></form>");
		
		ArrayList<Order> purchases = (ArrayList<Order>) session.getAttribute("allpurchases");
	
		for(Order order: purchases) {
			out.println("<form method='GET'>");
			out.println("<input type='hidden' name='username' value='"+ order.getUsername() +"' />");
			out.println("<input type='hidden' name='productName' value='"+ order.getProductName() +"' />");
			out.println("<input type='hidden' name='productQuantity' value='"+ order.getQuantity() +"' />");
			out.println(order.getUsername() + " " + order.getProductName() + " " + order.getQuantity());
			out.println("<input type='submit' formaction='/tie-novelty-shop/CancelPurchase' value='Override Purchase' />");
			out.println("</form>");
		}
	%>
	
	<form method="GET">
		<input type="submit" value="Home" formaction="/tie-novelty-shop/Home" />
	</form>
</body>
</html>