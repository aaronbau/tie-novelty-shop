<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<div class="navbar">
		<div id="search-bar">
			<form action="SearchProducts" method="GET">
				<input type="text" name="search" size="30" placeholder="Search" value="<%
					if(session.getAttribute("search") != null)
					{
						out.print(session.getAttribute("search").toString());
					}
				%>" />
			</form>
		</div>
		<div class="center">
			<button id="cravate" onclick="window.location.href='Home'">
				Cravate</button>
		</div>
		<div class="account-buttons">
			<%
				if (session.getAttribute("username") == null) {
			%>
			<button style="margin-right: 10px;" onclick="showSignup()">
				Sign Up</button>
			<button onclick="showLogin()">Log In</button>
			<%
				} else {
					if (session.getAttribute("usertype").equals("User")) {
			%>
			<span style="margin-right: 10px;"> Welcome, <a
				href="ViewPurchases">${sessionScope.username}</a>
			</span>
			<%
				} else if (session.getAttribute("usertype").equals("Administrator")) {
			%>
			<span style="margin-right: 10px;"> Welcome, <a
				href="AdminControls">${sessionScope.username}</a>
			</span>
			<%
				} else {
			%>
			<span style="margin-right: 10px;"> Welcome, <a
				href="ProductManagerControls">${sessionScope.username}</a>
			</span>
			<%
				}
			%>
			<button onclick="window.location.href='Logout'">Log Out</button>
			|
			<button title="View Cart" onclick="window.location.href='ViewCart'">
				<img src="assets/shopping-cart.png">
			</button>
			<%
				}
			%>
		</div>
	</div>
	<div class="hidden overlay">
		<div class="hidden account-forms center" id="login-element">
			<%@ include file = "login.jsp" %>
		</div>
		<div class="hidden account-forms center" id="signup-element">
			<%@ include file = "signup.jsp" %>
		</div>
	</div>	
</body>
</html>