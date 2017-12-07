<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
 	
 	<h3>The customer who generated the most revenue is</h3>
 	${customerRevenue[0]} with a total of ${customerRevenue[1]}. <p>
 	
 	<h3>The customer representative who generated the most revenue is</h3>
 	${repRevenue[0]} with a total of ${repRevenue[1]}. <p>
 	
 	<h3>Sales Report for a particular month</h3>
 	<input type="text" name="monthyear" placeholder="mm/yyyy" style="text-align: center"> 
 	<div class="customButton">Generate</div>
</body>
</html>