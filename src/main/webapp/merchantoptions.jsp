<%@page import="com.jsp.shopping_cart.dto.Merchant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MERCHANT OPTIONS</title>
</head>
<body>

<% Merchant m = (Merchant) session.getAttribute("merchantinfo");%>
<% if(m!=null){ %>

	<h1 style="color: green">${msg}</h1>
	<h1>
		<a href = "addproduct">ADD PRODUCT</a>
	</h1>
	<h1>
		<a href = "viewallproducts.jsp">View All Products</a>
	</h1>
	
<% }
else { %>
	<h1> <a href = "merchantloginform.jsp">Please Login First</a></h1>
	<% } %>
	
</body>
</html>