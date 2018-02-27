<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.*,java.util.ArrayList"%>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="SearchProducts" method="POST">
		<select name="filter" onchange="this.form.submit()">
  			<option value="f1">A-Z</option>
  			<option value="f2">Z-A</option>
  			<option value="f3">Lowest Price to Highest Price</option>
  			<option value="f4">Highest Price to Lowest Price</option>
		</select>
	</form>
	<br>
	<div class="grid">
	<%
		ArrayList<Product> products = (ArrayList<Product>) request.getAttribute("productList");
		for (Product product : products) {
	%>
		<div class="grid-box" onclick="goToProduct('<%out.print(product.getName());%>')" style="background-image: url(<% out.print(product.getImage()); %>)">
			<div class="product-name-hover">
				<span class="product-name center">
					<%out.print(product.getName());%>
				</span>
				<span class="product-price center">
					<%out.print(product.getPrice());%>
				</span>
			</div>
		</div>
	<%
		}
	%>
	</div>
	<form id="view-product-form" action="ViewProduct" method="GET">
		<input type="text" name="productName" id="product-name">
	</form>
</body>
</html>