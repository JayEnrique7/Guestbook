<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>

	<title>The Guestbook</title>

		<link rel="stylesheet" href="index.css"/>

</head>

<body>
	<a href="register.jsp"><h2>Subscribe</h2></a>
		<form action="LoginServlet" method="post">
				<input type="text" placeholder="Enter name" name="name"/>
					<br>
				<input type="password" placeholder="Enter password" name="password"/>
			<input type="submit" name="Submit" value="Login"/>
		</form>
</body>
</html>
