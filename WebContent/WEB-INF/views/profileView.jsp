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
 <body id="profile">
 	
 	<!-- should differentiate between employee, manager, and customer -->
    <jsp:include page="_menu.jsp"></jsp:include>
 	<p style="color: red;">${errorString}</p>
    <h3>Hello, ${person.firstName} ${person.lastName}! </h3>
 	
    Account Number: ${customer.accountNo} <br>
    Email: ${customer.email} <br>
	Password: ${customer.password} <br>
	
	<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
	<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
	<c:set var = "origNum" value = "${customer.phone}"/>
    <c:set var = "newNum" value = "${fn:replace(origNum, '.', '')}" />
    <c:set var = "newNum" value = "${fn:replace(newNum, 'E', '')}" />
    Phone Number : ${newNum} <br>
	
	Date: ${customer.creationDate} <br>
	Rating: ${customer.rating} <br>
 </body>
</html>