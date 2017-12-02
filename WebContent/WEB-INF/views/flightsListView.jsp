<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>List of flights</title>
    <style>
        <%@include file="/WEB-INF/views/css/style.css"%>
    </style>
 </head>
 <body>
    <jsp:include page="_menu.jsp"></jsp:include>
 
    <h1>Flights List</h1>
 
    <p style="color: red;">${errorString}</p>
        

    <div class="flightsData">
    	<c:forEach items="${flightList}" var="flight">
    		<h2> ${flight.departAirport} to ${flight.arrivalAirport} </h2>
			$${flight.fare} <br>
			${flight.departDate} to ${flight.arrivalDate}
			<div class="chooseFlight"> <a href="">Choose Flight</a></div>
    	</c:forEach>
    
    </div>
 
 </body>
</html>