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
	<%@ include file="components/navbar.jsp"%>
	<a style="font-size: .80em;" href="AdminControls">back to Admin Controls</a>
	<h1 style="font-size: 2em;">Purchases</h1>
	<%
// 		if(session.getAttribute("username".toString()) == null) 
// 			out.print("<form method='GET'><input type='submit' formaction='/tie-novelty-shop/Login' value='Log In' /><input type='submit' formaction='/tie-novelty-shop/Signup' value='Sign Up' /></form>");
// 		else
// 			out.print("<form method='GET'><input type='submit' formaction='/tie-novelty-shop/Logout' value='Log Out' /><input type='submit' formaction='/tie-novelty-shop/AddProduct' value='Add Item' /><input type='submit' formaction='/tie-novelty-shop/ViewCart' value='My Cart' /><input type='submit' formaction='/tie-novelty-shop/ViewPurchases' value='My Purchases' /><input type='submit' formaction='/tie-novelty-shop/AllPurchases' value='All Purchases' /></form>");
		
		ArrayList<Order> purchases = (ArrayList<Order>) session.getAttribute("allpurchases");
	
		for(Order order: purchases) {
			%>
			<div class="cart-item">
					<span>
					
						<%out.println(order.getUsername());%>
					</span>
					<span>
						<%out.println(order.getProductName());%>
					</span>
					<span>
						Quantity: 
						<%out.println(order.getQuantity());%>
					</span>
					<form action="CancelPurchase" style="display: inline;" method='GET'>
					<input type='hidden' name='username' value='<%=order.getUsername() %>' />
					<input type='hidden' name='productName' value='<%=order.getProductName() %>' />
					<input type='hidden' name='productQuantity' value='<%=order.getQuantity() %>' />
					<button title="Override Purchase" id="remove-from-cart" type='submit'>
							<img src='assets/garbage.png'> 
						</button>
					</form>
				</div>
				<% 
		}
	%>
</body>
</html>