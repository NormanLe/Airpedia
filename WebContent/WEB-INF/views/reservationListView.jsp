<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Reservation List</title>
     <style>
        <%@include file="/WEB-INF/views/css/style.css"%>
       </style>
 </head>
 <body>
 
    <jsp:include page="_menu.jsp"></jsp:include>
 
    <h1>My Reservations</h1>
 
    <p style="color: red;">${errorString}</p>
 
 	<a href="${pageContext.request.contextPath}">Create Reservation</a>
    <c:if test="${not empty reservationList}">
    <table class="tableStyle">
       
       <tr>
          <th>ResrNo</th>
          <th>Date</th>
          <th>Fee</th>
          <th>Fare</th>
          <th>AccountNo</th>
          <th>Edit</th>
          <th>Delete</th>
       </tr>
       

       <c:forEach items="${reservationList}" var="reservation">
          <tr>
             <td>${reservation.resrNo}</td>
             <td>${reservation.resrDate}</td>
             <td>${reservation.bookingFee}</td>
             <td>${reservation.totalFare}</td>
             <td>${reservation.customer.accountNo}</td>
             <td>
                <a href="editReservation?code=${reservation.resrNo}">Edit</a>
             </td>
             <td>
                <a href="deleteReservation?code=${reservation.resrNo}">Delete</a>
             </td>
          </tr>
       </c:forEach>
    </table>
	</c:if>
	
    <c:if test="${empty reservationList}">
    	<br> You have made no reservations.
    </c:if>
   
 
 </body>
</html>