<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New users</title>
</head>
	<body>
		<a href="login.jsp">Login</a>
		<form action="registerServlet" method="post">
			<input type="text" placeholder="Enter name" name="name"/>
	<br>
			<input type="password" placeholder="Enter password" name="password"/>
		<input type="submit" name="Submit" value="Subscribe"/>
	</body>
</html>