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
<%
	if(session.getAttribute("usertype") == null || session.getAttribute("usertype").toString() != "Administrator")
		response.sendRedirect("/tie-novelty-shop/Home");
%>

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
			<form>
				<input type="submit" formaction="AllPurchases"
					value="View All Purchases">
			</form>
		</div>
		<br>
		<div>
			<%
				DBUtilities db = new DBUtilities();
				ArrayList<User> users = db.getArrayListUsers();
				
				for(User u : users)
				{
			%>
				 <div class="cart-item">
					<span>
						<%out.println(u.getUsername());%>
					</span>
					<span>
						<%out.println(u.getEmail());%>
					</span>
					<span>
						<%out.println(u.getType());%>
					</span>
				</div>
			<%
				}
			%>
		</div>
	</div>
</body>
</html>