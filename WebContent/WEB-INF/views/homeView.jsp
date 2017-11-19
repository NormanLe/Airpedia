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
 
     <%-- <jsp:include page="_header.jsp"></jsp:include> --%>
     <jsp:include page="_menu.jsp"></jsp:include>
    
      <%-- <h3>Home Page</h3> --%>
      
      <h1>Book Your Reservation</h1>
        <table>
            <tr>
                <td> <input type="radio" name="tripType" checked>Round-Trip </td>
                <td> <input type="radio" name="tripType">One-Way </td>
            </tr>
            
            <tr>
                <td> Flying From <br> <input type="text" placeholder="city or airport"></td>
                <td> Flying To <br> <input type="text" placeholder="city or airport"></td>
            </tr>
            
            <tr>
                <td> Departing Date <br> <input type="text" placeholder="mm-dd-yyyy"> </td>
                <td> Returning Date <br> <input type="text" placeholder="mm-dd-yyyy"> </td> 
                <!-- Disabled if one way-->
            </tr>
            
            <tr>
                <td> Number of People <br> <input type="number" value="1"> </td>
                <td> Class <br> <input type="text" value="Economy"></td>
            </tr>
            
            <tr>
                <td colspan=2 align="center"> <input type="submit"></td>
        </table>
 
     <%-- <jsp:include page="_footer.jsp"></jsp:include> --%>
 	
  </body>
</html>