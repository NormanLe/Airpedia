<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

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
    <h1>Hello, ${person.firstName} ${person.lastName}! </h1>
    
    
 	<c:if test="${!empty customer}">
    	Account Number: ${customer.accountNo} <br>
    	Email: ${customer.email} <br>
		Password: ${customer.password} <br>
		<c:set var="origNum" value="${customer.phone}" />
		<c:set var="newNum" value="${fn:replace(origNum, '.', '')}" />
		<c:set var="newNum" value="${fn:replace(newNum, 'E', '')}" />
    	Phone Number : ${newNum} <br>
		Date: ${customer.creationDate} <br>
		Rating: ${customer.rating} <br>
	</c:if>
	
	<c:if test="${!empty employee}">
		Started Working: ${employee.startDate} <br>
		Hourly Rate: $${employee.hourlyRate} <br>
	</c:if>
	
	
 </body>
</html>