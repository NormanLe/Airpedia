<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
   		<title> Airpedia </title>
        <style>
        	<%@include file="/WEB-INF/views/css/style.css"%>
        </style>
    </head>
  <body>
 
     <jsp:include page="_menu.jsp"></jsp:include>
      
      <h1>Plan Your Next Trip</h1>
      
      	<form method="POST" action="${pageContext.request.contextPath}/flights">
        <table>
            <tr>
                <td> <input type="radio" name="tripType" checked>Round-Trip </td>
                <td> <input type="radio" name="tripType">One-Way </td>
            </tr>
            
            <tr>
                <td> Flying From <br> <input type="text" name="tripFrom" 
                	placeholder="city or airport"></td>
                <td> Flying To <br> <input type="text" name="tripTo"
                	placeholder="city or airport"></td>
            </tr>
            
            <tr>
                <td> Departing Date <br> <input type="text" placeholder="mm-dd-yyyy"> </td>
                <td> Returning Date <br> <input type="text" placeholder="mm-dd-yyyy"> </td> 
                <!-- Disabled if one way-->
            </tr>
            
            <tr>
                <td> Number of People <br> <input type="number" name="numPeople" value="1"> </td>
                <td> Class <br> <input type="text" name="class" value="Economy"></td>
            </tr>
            
            <tr>
                <td colspan=2 align="center"> <input type="submit" value="Search For Flights"></td>
        </table>
        </form>

  </body>
</html>