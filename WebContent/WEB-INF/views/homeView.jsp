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
  <body id="home">
 
     <jsp:include page="_menu.jsp"></jsp:include>
      
      <h1>Plan Your Next Trip</h1>
      
      	<form method="POST">
        <table>
            <tr>

				<td> <input type="radio" name="tripType" checked value="Round-Trip" onclick="toggleOneWay()">Round-Trip </td>
                <td> <input type="radio" name="tripType" value="One-Way" onclick="toggleOneWay()">One-Way </td>
			</tr>
            
            <tr>
                <td> Flying From <br> <input type="text" name="tripFrom" 
                	placeholder="city or airport"></td>
                <td> Flying To <br> <input type="text" name="tripTo"
                	placeholder="city or airport"></td>
            </tr>
            
            <tr>
                <td> Departing Date <br> <input type="text" name="departDate" placeholder="yyyy-mm-dd"> </td>
                <td id="returningDateText"> Returning Date <br> <input type="text" name="returnDate" placeholder="yyyy-mm-dd" id="returningDate"> </td> 
            </tr>
             
            <tr>
                <td colspan=2 align="center"> <input class="customButton" type="submit" value="Search For Flights"></td>
            </tr>
        </table>
		<p>
		<div> Unsure what to search for? Click <a href="${pageContext.request.contextPath}/recommendations">here</a> for recommendations! </div>
		</form>
	<script>
		function toggleOneWay() {
			var tripTypes = document.getElementsByName("tripType");
			var returningDate = document.getElementById("returningDate");
			var returningDateText = document.getElementById("returningDateText");
			
			if (tripTypes[0].checked) {
				returningDateText.style.display = "block";
				returningDate.style.display = "block";
			} else {
				returningDateText.style.display = "none";
				returningDate.style.display = "none";
			}
		}
	</script>
</body>
</html>