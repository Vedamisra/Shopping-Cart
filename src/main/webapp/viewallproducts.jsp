<%@page import="com.jsp.shopping_cart.dto.Product"%>
<%@page import="com.jsp.shopping_cart.dto.Merchant"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% Merchant m = (Merchant) session.getAttribute("merchantinfo"); 
	List<Product> products = m.getProducts();
%>
<table cellPadding="5px" cellSpacing="2px" border="1">
	<tr align="center">
		<th>Id</th>
		<th>Brand</th>
		<th>Model</th>
		<th>Category</th>
		<th>Price</th>
		<th>Stock</th>	
		<th>Update</th>
		<th>Delete</th>
	</tr>
	<% for(Product p : products){ %>
		<tr align="center">
			<td><%= p.getId() %></td>
			<td><%= p.getBrand() %></td>
			<td><%= p.getModel() %></td>
			<td><%= p.getCategory() %></td>
			<td><%= p.getPrice() %></td>
			<td><%= p.getStock() %></td>
			<td><a href="updateproduct?id=<%= p.getId() %>">Update</a></td>
			<td><a href="deleteproduct?id=<%= p.getId() %>">Delete</a></td>
		</tr>
	<% } %>
</table>
</body>
</html>