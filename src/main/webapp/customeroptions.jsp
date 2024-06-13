<%@page import="com.jsp.shopping_cart.dto.Customer"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CUSTOMER OPTIONS</title>
</head>
<body>
	
	<% Customer c = (Customer)session.getAttribute("customerinfo") ; 
		if(c!=null){
	%>
	<h1 style="color: green">${msg}</h1> <br>
	<h1>
	<a href = "displayproducts">Display all products</a>
	</h1>
	
	<h1>
		<a href = "readbrandfromcustomer.jsp">Display Products By Brand</a>
	
	</h1>
	
	<% }
	else {%>
		<h1><a href="customerloginform.jsp"> PLEASE LOGIN FIRST </a> </h1>
	<% } %>
	
</body>
</html>