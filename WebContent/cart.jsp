<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.*,java.util.ArrayList, dbhelper.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cart</title>
<link rel="stylesheet" type="text/css" href="css/stylesheet.css">
<script src="js/script.js"></script>
</head>
<body>
<%@ include file="components/navbar.jsp" %>
	<a style="font-size: .80em;" href="Home">back to Home</a>
	<%
// 		if(session.getAttribute("username".toString()) == null) 
// 			out.print("<form method='GET'><input type='submit' formaction='/tie-novelty-shop/Login' value='Log In' /><input type='submit' formaction='/tie-novelty-shop/Signup' value='Sign Up' /></form>");
// 		else
// 			out.print("<form method='GET'><input type='submit' formaction='/tie-novelty-shop/Logout' value='Log Out' /><input type='submit' formaction='/tie-novelty-shop/AddProduct' value='Add Item' /><input type='submit' formaction='/tie-novelty-shop/ViewCart' value='My Cart' /><input type='submit' formaction='/tie-novelty-shop/ViewPurchases' value='My Purchases' /><input type='submit' formaction='/tie-novelty-shop/AllPurchases' value='All Purchases' /></form>");
		
		ArrayList<Order> orders = (ArrayList<Order>) session.getAttribute("cart");
		DBUtilities db = new DBUtilities();
		
		double totalTotalPrice = 0;
		
		if(orders.size() == 0)
		{
			%>
			<h1 style="font-size: 2em;" align="center">No items in cart.</h1>
			<% 
		} else { %>
			<h1 style="font-size: 2em;">Cart</h1>
		<%
			
		for(Order order: orders) {
			Product p = db.getProduct(order.getProductName());
			double totalPrice = p.getPrice() * order.getQuantity();
			totalTotalPrice += totalPrice;
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
			<form style="display: inline;"  method='GET'>
				<input type='hidden' name='productName' value='<%out.print(order.getProductName());%>' />
				<input type='hidden' name='productQuantity' value='<%out.print(order.getQuantity());%>' />
				<button title="Remove from Cart" id="remove-from-cart" type='submit' formaction='/tie-novelty-shop/RemoveFromCart'>
				<img src='assets/delete.png'> 
				</button>
			</form>
		</div>
		
	
<!-- 			out.println("<form method='GET'>"); -->
<!-- 			out.println("<input type='hidden' name='productName' value='"+ order.getProductName() +"' />"); -->
<!-- 			out.println("<input type='hidden' name='productQuantity' value='"+ order.getQuantity() +"' />"); -->
<!-- 			out.println(order.getProductName() + " " + order.getQuantity()); -->
<!-- 			out.println("<input type='submit' formaction='/tie-novelty-shop/RemoveFromCart' value='Remove from Cart' />"); -->
<!-- 			out.println("</form>"); -->
	<%
		} 
	%>
	<br>
		Total: <%out.println(totalTotalPrice); %>
	<br>
	<form style="float: right;"method="POST">
		<input id="checkout" type='submit' value='Checkout' formaction="/tie-novelty-shop/Checkout">
	</form>
	<%
		}
	%>
	
	
	<div class="hidden overlay">
		<div class="hidden account-forms center" id="login-element">
			<%@ include file = "components/login.jsp" %>
		</div>
		<div class="hidden account-forms center" id="signup-element">
			<%@ include file = "components/signup.jsp" %>
		</div>
	</div>	
</body>
</html>