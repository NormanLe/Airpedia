<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
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
 	
 		<%-- 	<th> AirlineId </th>
 			<th> FlightNo </th>
 			<th> Departing Airport </th>
 			<th> Arriving Airport </th>
 			<th> Arrival Time </th>
 			<th> Departing Time </th>
 			<th> Seat Number </th>
 			<th> Class </th>
 			<th> Meal </th>
 		</tr>
 		 --%>
 		<c:forEach items="${list}" var="detail">
 			
 			${detail[2]}  ${detail[5]} -> ${detail[3]} at ${detail[4]}. <br>
 			Seat number ${detail[6]}, ${detail[7]} class, with ${detail[8]} as your meal. <p>
 		</c:forEach>
 	</table>

 	
</body>
</html>