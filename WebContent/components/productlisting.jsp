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
	<div class="product-grid">
	<%
		ArrayList<Product> products = (ArrayList<Product>) request.getAttribute("productList");
		for (Product product : products) {
	%>
		<div class="product-image-box" onclick="" style="background-image: url(<% out.print(product.getImage()); %>)">
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
	
</body>
</html>