<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Reservation List</title>
 </head>
 <body>
 
    <jsp:include page="_header.jsp"></jsp:include>
    <jsp:include page="_menu.jsp"></jsp:include>
 
    <h3>Reservation List</h3>
 
    <p style="color: red;">${errorString}</p>
 
    <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>ResrNo</th>
          <th>Date</th>
          <th>Fee</th>
          <th>Fare</th>
          <th>AccountNo</th>
          <th>RepSSN</th>
       </tr>
       <c:forEach items="${reservationList}" var="reservation" >
          <tr>
             <td>${reservation.resrNo}</td>
             <td>${reservation.resrDate}</td>
             <td>${reservation.bookingFee}</td>
             <td>${reservation.totalFare}</td>
             <td>${reservation.customer.accountNo}</td>
             <td>${reservation.employee.ssn}</td>
             <td>
                <a href="editReservation?code=${reservation.resrNo}">Edit</a>
             </td>
             <td>
                <a href="deleteReservation?code=${reservation.resrNo}">Delete</a>
             </td>
          </tr>
       </c:forEach>
    </table>
 
    <a href="createReservation" >Create Reservation</a>
 
    <jsp:include page="_footer.jsp"></jsp:include>
 
 </body>
</html>