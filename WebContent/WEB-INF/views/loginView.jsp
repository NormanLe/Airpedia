<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
   		<title> Login </title>
        <style>
        	<%@include file="/WEB-INF/views/css/style.css"%>
        </style>
    </head>
   <body>
      <jsp:include page="_menu.jsp"></jsp:include>
 
      <h1>Login</h1>
      <p style="color: red;">${errorString}</p>
 
 
      <form method="POST" action="${pageContext.request.contextPath}/login">
         <table>
            <tr>
                <td colspan="3">
                    <input type="radio" name="userType" value="customer" checked onclick=changeForm()> Customer
                    <input type="radio" name="userType" value="employee" onclick=changeForm()> Employee 
                    <input type="radio" name="userType" value="manager" onclick=changeForm()> Manager
                </td>
            </tr>
            <tr>
               <td>Username</td>
               <td><input type="text" name="email"/> </td>
            </tr>
            <tr>
               <td>Password</td>
               <td><input type="password" name="password"/> </td>
            </tr>
            <tr>
               <td colspan ="2" align="center">
                  <input type="submit" value= "Submit" />
				  <br> <a href="${pageContext.request.contextPath}/registration"> Register </a>
               </td>
            </tr>
         </table>
      </form>
      
   </body>
</html>