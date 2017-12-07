<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<style>
        <%@include file="/WEB-INF/views/css/style.css"%>
       </style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Help Menu</title>
</head>
<body>
	<jsp:include page="_menu.jsp"></jsp:include>
	<h1> Help Menu </h1>
	<div style="width: 60%;margin: 0 auto">
	
	<h2> Programmer's guide <a href="https://docs.google.com/document/d/1pD3wcJVcnnze8jBs30vMZfv-M5n-iRX0upcQesxCxlA/edit" target="_blank"> here </a>
	</h2>
	<p>
	
	You can search for flights on the front page (or if you click Airpedia at the time).
	
	<h2> Customers </h2>
	If you are a customer, the login for customers is an email address and your password that you made
	during registration. You may click on profile to view your personal details and reservations to see the 
	reservations you booked. You may also bid on flights through the auctions link at the top. 
	
	<h2> Employees </h2>
	If you are an employee, your username is your lastname + id, and your password is your SSN. 
	Your profile contains personal information about you and the reservations you are able to view 
	is based on the people you help with. If you are a manager, you have access to all reservations.
	In addition to that, managers have access to sales reports that others cannot view. 
	</div>
 	
</body>
</html>