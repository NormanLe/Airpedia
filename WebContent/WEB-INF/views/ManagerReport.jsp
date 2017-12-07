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
	<title>Manager Report</title>
</head>
<body>
	<jsp:include page="_menu.jsp"></jsp:include>
 	<h1> Manager Report </h1>
 	
 	<c:if test="${!empty NoPermission}">
 		${NoPermission}
 	</c:if>
 	
 	<c:if test="${empty NoPermission}">
 	<c:if test="${!empty customerRevenue}">
 	<h3>The customer who generated the most revenue is</h3>
 	${customerRevenue[0]} with a total of ${customerRevenue[1]}. <p>
 	
 	<h3>The customer representative who generated the most revenue is</h3>
 	${repRevenue[0]} with a total of ${repRevenue[1]}. <p>
 	
 	<h3>The most active flight is</h3>
 	${mostActiveFlight[0]} Flight# ${mostActiveFlight[1]} with a total of ${mostActiveFlight[2]} flights. <p>
 	
 	</c:if>
 	
 	<h3>Sales report for a particular month</h3>
 	<c:if test="${!empty salesReportByMonth}">
 	<table class="tableStyle">
 		<tr>
 			<th> Account Number </th>
 			<th> Booking Fee </th>
 			<th> Date </th>
 		</tr>
 		<c:forEach items="${salesReportByMonth}" var="report">
 			<tr>
 			<td> ${report[0]} </td>
 			<td> ${report[1]} </td>
 			<td> ${report[2]} </td>
 			</tr>
 		</c:forEach>
 	</table>
 	</c:if>
 	
 	<form method="post">
 	
 	<input type="text" name="monthyear" placeholder="mm/yyyy"> 
 	<br>
 	<input type="submit" class="customButton" value="Generate"></div>
 	
 	
 	
 	
 	<h3> Summary listing by</h3>
	
	<c:if test="${!empty summaryListing}">
 	<table class="tableStyle">
 		<tr>
 			<th> Reservation Number </th>
 			<th> Booking Fee </th>
 			<th> Date </th>
 		</tr>
 		<c:forEach items="${summaryListing}" var="report">
 			<tr>
 			<td> ${report[0]} </td>
 			<td> ${report[1]} </td>
 			<td> ${report[2]} </td>
 			</tr>
 		</c:forEach>
 	</table>
 	</c:if>
 	
 	<table>
 		<tr>
 			<td> AirlineId + FlightNo</td>
 			<td> <input type="text" name="airlineflight" placeholder="AirlineID-FlightNo"> </td>
 		</tr>
 		
 		<tr> 
 			<td align="center" colspan=2> or </td>
 		</tr>
 		<tr>
 			<td> Destination City </td>
 			<td> <input type="text" name="destinationCity" placeholder="City"> </td>
 		</tr>
 		
 		<tr> 
 			<td align="center" colspan=2> or </td>
 		</tr>
 		
 		<tr>
 			<td> Customer </td>
 			<td> <input type="text" name="customer" placeholder="Account Number"> </td>
 		</tr>
 	</table>
 	
 	<input type="submit" class="customButton" value="Generate"></div>
 	</form>
 	</c:if>
</body>
</html>