<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Make Reservation</title>
    <style>
        <%@include file="/WEB-INF/views/css/style.css"%>
    </style>
 </head>
 <body>
    <jsp:include page="_menu.jsp"></jsp:include>
 
    <h1>Make Reservation</h1>
 
    <p style="color: red;">${errorString}</p>
        
	You have chosen ${airline} airline, flight #${flight} <br>
	Your seat will be ${seatNum}. <br>
	You will depart from ${departAirport} and arrive at ${arriveAirport}. <br>
	Please make the following selections:
	<%-- class, meal, seat --%>
	<br>
	Class
	<select name="class">
		<option value="economy">Economy</option>
		<option value="business">Business</option>
		<option value="firstClass">First Class</option>
	</select>
	<br>
	Meal
	<select name="food">
		<option value="fishchips">Fish and Chips</option>
		<option value="salad">Salad</option>
		<option value="sushi">Sushi</option>
	</select>
	<br>
	

</body>
</html>