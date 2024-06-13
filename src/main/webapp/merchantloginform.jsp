<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MERCHANT LOGIN FORM </title>
</head>
<body>
	<h1 style="color: red">${msg} </h1>
	<form action="loginvalidation" method="post">
		Enter email : <input type="email" name="email"> <br> 
		Enter password : <input type="password" name="password"> <br>
		<input type="submit" value="Login">
	</form>
</body>
</html>