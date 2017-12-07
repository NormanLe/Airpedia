<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
   <head>
   		<title> Auctions </title>
        <style>
        	<%@include file="/WEB-INF/views/css/style.css"%>
        </style>
    </head>
   <body>
      <jsp:include page="_menu.jsp"></jsp:include>
 
      <h1>Auctions</h1>
      <p style="color: red;">${errorString}</p>
 
    <c:if test="${!empty auctionFlights}">
    <div class="auctionData">
    <table>
    	<c:forEach items="${auctionFlights}" var="flight">
    	<tr>	
    		<td>
    		<h2> ${flight.depCity} to ${flight.arrCity} </h2>
    		${flight.departAirport} to ${flight.arrivalAirport}<br>
			Airline ${flight.airlineId}, Flight #${flight.flightNo} <br>
			<fmt:formatDate value="${flight.departDate}" pattern="yyyy-MM-dd" />
			to 
			<fmt:formatDate value="${flight.arrivalDate}" pattern="yyyy-MM-dd" />
			<br>
			<fmt:formatDate value="${flight.departDate}" pattern="HH:mm:ss" />
			to
			<fmt:formatDate value="${flight.arrivalDate}" pattern="HH:mm:ss" />
			<br></td>
			<td><form method="post"><input type="text" name="bidAmount"/> 
			<input type="hidden" name="data" value="${flight.airlineId},${flight.flightNo},${flight.departAirport},${flight.arrivalAirport},${flight.hiddenFare}"/>
			<input type="submit" value="Make Bid"></form></td>
			
		</tr>
		
    	</c:forEach>
    </table>
    </div>
    </c:if>
   </body>
</html>