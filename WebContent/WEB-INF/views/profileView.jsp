<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <head>
    <title>Profile</title>
     <style>
        	<%@include file="/WEB-INF/views/css/style.css"%>
     </style>
 </head>
 <body>
 
    <jsp:include page="_menu.jsp"></jsp:include>
 
    <h3>Hello: ${customer.email}</h3>
 
    Email: ${customer.email}
	Password: ${customer.password}

 </body>
</html>