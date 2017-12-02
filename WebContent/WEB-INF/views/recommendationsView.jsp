<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Recommendations</title>
    <style>
        <%@include file="/WEB-INF/views/css/style.css"%>
    </style>
 </head>
 <body id="recommendations">
    <jsp:include page="_menu.jsp"></jsp:include>
 
    <h1>Best Seller</h1>
 
    <p style="color: red;">${errorString}</p>
 	
    <div class="flightsData">
    	<h2> ${bestSeller.departAirport} to ${bestSeller.arrivalAirport} </h2>
		$${bestSeller.fare} <br>
		${bestSeller.departDate} to ${bestSeller.arrivalDate}
		<div class="chooseFlight"> <a href="">Choose Flight</a></div>
	</div>
           
    <h1> Personalized Flight Suggestions </h1>       
    <div class="flightsData">
    	<c:forEach items="${personalizedFlights}" var="flight">
    		<h2> ${flight.departAirport} to ${flight.arrivalAirport} </h2>
			$${flight.fare} <br>
			${flight.departDate} to ${flight.arrivalDate}
			<div class="chooseFlight"> <a href="">Choose Flight</a></div>
    	</c:forEach>
    
    </div>
    <%--<a href="createReservation?code=${bestSeller.flightNo}">Make a reservation</a>
          
    
 
 --%>
 </body>
</html>