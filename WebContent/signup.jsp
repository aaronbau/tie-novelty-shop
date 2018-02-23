<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign Up</title>
</head>
<body>
	<form method="POST">
		Username: <input type="text" name="username" /> <br>
		Password: <input type="password" name="password" /> <br>		
		Email: <input type="email" name="email" /> <br>
		<input type="submit" />
	</form>
	
	<form method="GET">
		<input type="submit" value="Home" formaction="/TieNoveltyShop/Home" />
	</form>
</body>
</html>