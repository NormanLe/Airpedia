<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>List of flights</title>
 </head>
 <body>
 
    <jsp:include page="_header.jsp"></jsp:include>
    <jsp:include page="_menu.jsp"></jsp:include>
 
    <h3>Flights List</h3>
 
    <p style="color: red;">${errorString}</p>
 
    <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>Airline Name</th>
          <th>Flight #</th>
          <th>Seats Remaining</th>
          <th>Days Operating</th>
          <th>Min length of stay</th>
          <th>Max length of stay</th>
       </tr>
       <c:forEach items="${flightList}" var="flight" >
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
 
    <jsp:include page="_footer.jsp"></jsp:include>
 
 </body>
</html>