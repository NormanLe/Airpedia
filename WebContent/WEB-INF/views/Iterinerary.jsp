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
<title>Iterinerary</title>
</head>
<body>
	<jsp:include page="_menu.jsp"></jsp:include>
	<h1>View Iterinerary for Reservation #${resrNo}</h1>
 	
 		Your AirlineId is ${list[0][0]}, Flight #${list[0][1]}. <br> 
 	 	<div class="flightsData">
 		<c:forEach items="${list}" var="detail">
 			
 			<h2>${detail[2]} -> ${detail[3]}</h2> 
 			
 			${detail[4]} to ${detail[5]} <br>
 			Seat number ${detail[6]} <br> ${detail[7]} class <br> Meal is ${detail[8]}  <br>
 		</c:forEach>
 		</div>

 	
</body>
</html>