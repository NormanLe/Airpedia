<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>User Info</title>
 </head>
 <body>
 
    <jsp:include page="_header.jsp"></jsp:include>
    <jsp:include page="_menu.jsp"></jsp:include>
 
    <h3>Hello: ${customer.email}</h3>
 
    Email: <b>${customer.email}</b>
	Password: <b>${customer.password}</b>
    <br />
    
    <jsp:include page="_footer.jsp"></jsp:include>
 
 </body>
</html>