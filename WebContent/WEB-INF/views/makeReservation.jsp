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
	<input type="text" name="${depLegNo}" style="display: none">
	<input type="text" name="${arrLegNo}" style="display: none">
	<form method="post" >
		<div class="customButton"><a id="checkoutLink" href="">Checkout</a></div>
		<a id="originalCheckoutLink" href="${pageContext.request.contextPath}/checkout
		?airline=${airline}&flight=${flight}&seat=${seatNum}&depLegNo=${depLegNo}&arrLegNo=${arrLegNo}"
		 style="display: none"></a>
	</form>
	
	<script>
		var numPeople;
		var className;
		var food;
		var tripName;
		
		changePrice();
		
		function changePrice() {
			var fare = document.getElementById('originalFare').innerHTML;
			
			var fareSpan = document.getElementById('fare');
			console.log(fareSpan);
			numPeople = document.getElementsByName('numPeople')[0].value;
			var tripType = document.getElementsByName('tripType')[0].checked ? 1 : 0.5;
			tripName = document.getElementsByName('tripType')[0].checked ? "Round-Trip" : "One-Way";
			
			var classType;
			if (document.getElementsByName('classType')[0].checked) {
				classType = 1;
				className = "Economy";
			} else if (document.getElementsByName('classType')[1].checked) {
				classType = 2;
				className = "Business";
			} else {
				classType = 3;
				className = "First";
			}
			
			if (document.getElementsByName('food')[0].checked) {
				food = "Fish and Chips";
			} else if (document.getElementsByName('food')[1].checked) {
				food = "Chips";
			} else {
				food = "Sushi";
			}
			
			fareSpan.innerHTML = numPeople * tripType * classType * fare;
			var val = document.getElementById('originalCheckoutLink').href;
			var append = "&fare=" + fareSpan.innerHTML + "&numPeople=" + numPeople + "&classType=" + className + "&food=" + food + "&tripType=" + tripName;
			document.getElementById('checkoutLink').href = val + append;
			
			console.log(document.getElementById('checkoutLink').href);
		}
		
		

		
	</script>
</body>
</html>