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
 
    <h1>Recommendations</h1>
 
    <p style="color: red;">${errorString}</p>
 	
 	<h2> Best Seller </h2>
    <table class="tableStyle">
       <tr>
          <th>Airline Name</th>
          <th>Flight #</th>
          <th>Seats Remaining</th>
          <th>Days Operating</th>
          <th>Min length of stay</th>
          <th>Max length of stay</th>
          <th>Reserve</th>
       </tr>
       
          <tr>
             <td>${bestSeller.airline.name}</td>
             <td>${bestSeller.flightNo}</td>
             <td>${bestSeller.noOfSeats}</td>
             <td>${bestSeller.daysOperating}</td>
             <td>${bestSeller.minLengthOfStay}</td>
             <td>${bestSeller.maxLengthOfStay}</td>
             <td>
             <a href="createReservation?code=${bestSeller.flightNo}">Make a reservation</a>
             </td>
          </tr>
       
    </table>
    
    <h2> Personalized Flight Suggestions</h2>
    <table class="tableStyle">
       <tr>
          <th>Airline Name</th>
          <th>Flight #</th>
          <th>Seats Remaining</th>
          <th>Days Operating</th>
          <th>Min length of stay</th>
          <th>Max length of stay</th>
          <th>Reserve</th>
       </tr>
       <c:forEach items="${personalizedFlights}" var="flight" >
          <tr>
             <td>${flight.airline.name}</td>
             <td>${flight.flightNo}</td>
             <td>${flight.noOfSeats}</td>
             <td>${flight.daysOperating}</td>
             <td>${flight.minLengthOfStay}</td>
             <td>${flight.maxLengthOfStay}</td>
             <td>
                <a href="createReservation?code=${flight.flightNo}">Make a reservation</a>
             </td>
          </tr>
       </c:forEach>
    </table>
 
 </body>
</html>