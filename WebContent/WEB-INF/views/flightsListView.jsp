<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
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
    		<h2> ${flight.depCity} to ${flight.arrCity} </h2>
    		${flight.departAirport} to ${flight.arrivalAirport} $${flight.fare} <br>
			Airline ${flight.airlineId}, Flight #${flight.flightNo}<br>
			From Stop ${flight.departLegNo} to Stop ${flight.arriveLegNo} <br>
			<fmt:formatDate value="${flight.departDate}" pattern="yyyy-MM-dd" />
			to 
			<fmt:formatDate value="${flight.arrivalDate}" pattern="yyyy-MM-dd" />
			<br>
			<fmt:formatDate value="${flight.departDate}" pattern="HH:mm:ss" />
			to
			<fmt:formatDate value="${flight.arrivalDate}" pattern="HH:mm:ss" /> 
			<br>
			<div class="customButton"> <a href="${pageContext.request.contextPath}/makeReservation
			?airline=${flight.airlineId}&flight=${flight.flightNo}&depart=${flight.departAirport}
			&arrive=${flight.arrivalAirport}">Choose Flight</a></div>
    	</c:forEach>
    
    </div>
 
 </body>
</html>