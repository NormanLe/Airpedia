<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
   		<title> Register </title>
        <style>
        	<%@include file="/WEB-INF/views/css/style.css"%>
        </style>
    </head>
   <body>
      <jsp:include page="_menu.jsp"></jsp:include>
 
      <h1>Register</h1>
      <p style="color: red;">${errorString}</p>
 
 
      <form method="POST" action="${pageContext.request.contextPath}/registration">
        <table>
            <tr>
                <td colspan="3">
                    <input type="radio" name="userType" value="customer" checked onclick=changeForm()> Customer
                    <input type="radio" name="userType" value="employee" onclick=changeForm()> Employee 
                    <input type="radio" name="userType" value="manager" onclick=changeForm()> Manager
                </td>
            </tr>
            
            <!--  Customer is going to need an id and accountNo generated automatically, and rating updated later -->
            <tr>
                <td> First Name </td>
                <td> <input type="text" name="firstname"> </td>
            </tr>
            <tr>
                <td> Last Name </td>
                <td> <input type="text" name="lastname"> </td>
            </tr>
            <tr class="customer"> 
                <td> Email </td>
                <td> <input type="text" name="email"></td>
            </tr>
            <tr>
                <td> Password </td>
                <td> <input type="text" name="password"> </td>
            </tr>
            <tr class="customer">
                <td class="customer"> Phone </td>
                <td class="customer"> <input type="text" name="phone"> </td>
            </tr>
            <tr class="customer">
                <td> Credit Card </td>
                <td> <input type="text" name="creditcard"> </td>
            </tr>
            <!-- Employee -->
            <tr class="employee">
                <td> SSN </td>
                <td> <input type="text" name="ssn"> </td>
            </tr>
            <tr class="employee">
                <td>Hourly Rate</td>
                <td> <input type="text" name="hourlyrate"> </td>
            </tr>
            <tr>
                <td> Address </td>
                <td> <input type="text" name="address"> </td>
            </tr>
            <tr>
                <td> City </td>
                <td> <input type="text" name="city"> </td>
            </tr>
            <tr>
                <td> State </td>
                <td> <input type="text" name="State"> </td>
            </tr>
            <tr>
                <td> Zip Code </td>
                <td> <input type="text" name="zipcode"> </td>
            </tr>
            <tr>
                <td colspan=2 align="center"> <input type="submit" value="Register">  
                </td>
            </tr>
        </table>
        
       
        <script>
            changeForm();
            function changeForm() {
                var userTypes = document.getElementsByName('userType');
                var currentType = "";
                var customers = document.getElementsByClassName("customer");
                var employees = document.getElementsByClassName("employee");
                
                for (var i = 0; i < userTypes.length; i++) {
                    if (userTypes[i].checked) {
                        userTypes[i].value == 'customer' ?
                        changeFormHelper(customers, employees) :
                        changeFormHelper(employees, customers);
                    }
                   
                }
            }
            
            function changeFormHelper( current,  others) {
                for (var i = 0; i < others.length; i++) {
                    others[i].style.display = "none";
                } 
                for (var i = 0; i < current.length; i++) {
                    current[i].style.display = "";
                }
            }  
        </script>
      </form>
      
   </body>
</html>