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
        
	You have chosen airline ${airline}, flight #${flight} <br>
<%-- 	Your seat will be ${seatNum}. <br> --%>
	You will depart from ${departAirport} and arrive at ${arriveAirport}. <p>
	
	<h3>Please make the following selections</h3>
	
	Number of People <input type="number" name="numPeople" value="1" onchange="changePrice()"> <br>
	
	Trip Type: 
	<input type="radio" name="tripType" checked value="roundtrip" onclick="changePrice()">Round Trip 
	<input type="radio" name="tripType" value="oneway" onchange="changePrice()"> One Way <br>
	
	Class
	<input type="radio" name="classType" checked value="economy" onchange="changePrice()">Economy 
	<input type="radio" name="classType" value="business" onchange="changePrice()"> Business
	<input type="radio" name="classType" value="first" onchange="changePrice()"> First <br>
	
	Meal
	<input type="radio" name="food" checked value="fishchips"> Fish and Chips
	<input type="radio" name="food" value="chips"> Chips
	<input type="radio" name="food" value="sushi"> Sushi
	
	<h3>Your total is $<span id="fare" name="${fare}">${fare}</span></h3>
	<span id="originalFare" style="display: none">${fare}</span>
	<div class="customButton">Checkout</div>
	
	<script>
		function changePrice() {
			var fare = document.getElementById('originalFare').innerHTML;
			
			var fareSpan = document.getElementById('fare');
			console.log(fareSpan);
			var numPeople = document.getElementsByName('numPeople')[0].value;
			var tripType = document.getElementsByName('tripType')[0].checked ? 1 : 0.5;
			
			var classType;
			if (document.getElementsByName('classType')[0].checked)
				classType = 1;
			else if (document.getElementsByName('classType')[1].checked)
				classType = 2;
			else 
				classType = 3;
			
			fareSpan.innerHTML = numPeople * tripType * classType * fare;
		}
		
	</script>
</body>
</html>