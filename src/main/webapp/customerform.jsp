<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MERCHANT FORM</title>
</head>
<body>
<form:form action="saveCustomer" modelAttribute="customerobj">
	Enter name : <form:input path="name"/>
	Enter mobile number : <form:input path="mobilenumber"/>
	Enter email : <form:input path="email"/>
	Enter password : <form:input path="password"/>
	Enter address: <form:textarea path="address"/>
	<input type="submit">
</form:form>
</body>
</html>