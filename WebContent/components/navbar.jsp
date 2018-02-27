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
			<input type="text" size="30" placeholder="Search"/>
		</div>
		<div class="center">
			<button id="cravate" onclick="window.location.href='Home'">
				Cravate
			</button>
		</div>
		<div class="account-buttons">
			<%
				if (session.getAttribute("username") == null) {
			%>
			<button style="margin-right: 10px;" onclick="showSignup()">Sign Up</button>
			<button onclick="showLogin()">Log In</button>
			<%
				} else {
			%>
			<span style="margin-right: 10px;">
				Welcome,
				<a href="ViewPurchases">${sessionScope.username}</a>
			</span>
			<button onclick="window.location.href='Logout'">Log Out</button>
			 | 
			<button onclick="window.location.href='ViewCart'">Cart</button>
			<%
				}
			%>
		</div>
	</div>
</body>
</html>