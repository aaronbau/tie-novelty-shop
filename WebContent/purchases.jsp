<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.*,java.util.ArrayList, dbhelper.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Purchases</title>
	<link rel="stylesheet" type="text/css" href="css/stylesheet.css">
<script src="js/script.js"></script>
</head>
<body>
<%
	if(session.getAttribute("usertype").toString() == null)
		response.sendRedirect("/tie-novelty-shop/Home");
%>
	<%@ include file="components/navbar.jsp" %>	
	<a style="font-size: .80em;" href="Home">back to Home</a>
	<%
// 		if(session.getAttribute("username".toString()) == null) 
// 			out.print("<form method='GET'><input type='submit' formaction='/tie-novelty-shop/Login' value='Log In' /><input type='submit' formaction='/tie-novelty-shop/Signup' value='Sign Up' /></form>");
// 		else
// 			out.print("<form method='GET'><input type='submit' formaction='/tie-novelty-shop/Logout' value='Log Out' /><input type='submit' formaction='/tie-novelty-shop/AddProduct' value='Add Item' /><input type='submit' formaction='/tie-novelty-shop/ViewCart' value='My Cart' /><input type='submit' formaction='/tie-novelty-shop/ViewPurchases' value='My Purchases' /><input type='submit' formaction='/tie-novelty-shop/AllPurchases' value='All Purchases' /></form>");
		
		ArrayList<Order> purchases = (ArrayList<Order>) session.getAttribute("purchases");
		DBUtilities db = new DBUtilities();
		
		if(purchases.size() == 0)
		{
			%>
			<h1 style="font-size: 2em;" align="center">No items purchased yet.</h1>
		<% } else { %>
			<h1 style="font-size: 2em;" align="center">Purchases</h1>
		<% 
		for(Order order: purchases) {
			Product p = db.getProduct(order.getProductName());
			double totalPrice = p.getPrice() * order.getQuantity();
		%>
		<div class="cart-item">
			<span>
				<%out.println(order.getProductName());%>
			</span>
			<span>
				Quantity: 
				<%out.println(order.getQuantity());%>
			</span>
			<span>
				Price:
				<%out.println(totalPrice);%>
			</span>
		</div>
<!-- 			out.println("<form method='GET'>"); -->
<!-- 			out.println("<input type='hidden' name='productName' value='"+ order.getProductName() +"' />"); -->
<!-- 			out.println("<input type='hidden' name='productQuantity' value='"+ order.getQuantity() +"' />"); -->
<!-- 			out.println(order.getProductName() + " " + order.getQuantity()); -->
<!-- 			out.println("</form>"); -->
	<%
		}}
	%>
	
<!-- 	<form method="GET"> -->
<!-- 		<input type="submit" value="Home" formaction="/tie-novelty-shop/Home" /> -->
<!-- 	</form> -->
</body>
</html>