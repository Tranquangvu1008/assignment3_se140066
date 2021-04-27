<%-- 
    Document   : history
    Created on : Mar 4, 2021, 3:35:37 PM
    Author     : SE140066
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History Page</title>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.1/css/all.css" integrity="sha384-vp86vTRFVJgpjF9jiIGPEEqYqlDwgyBgEF109VFjmqGmIY/Y4HV4d3Gp2irVfcrp" crossorigin="anonymous">

    </head>
    <body>
        <form action="HistoryController">
            Date (Start date) <input type="date" name="txtStartDay" value="${param.txtStartDay}" required/>
            <button <input type="submit" /><i class="fas fa-search"></i></button>
        </form>
        <a class="nav-link" href="MenuController">Return</a>
        <a class="nav-link" href="LogoutController">Logout</a>
        <c:if test="${not empty requestScope.HISTORY}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Rent ID</th>
                        <th>Total Price</th>
                        <th>Create Date</th>
                        <th>Detail</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="car" varStatus="counter" items="${requestScope.HISTORY}">
                    <form action="MainController">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${car.rentID}</td>
                            <td>${car.totalPrice}</td>
                            <td>${car.createDate}</td>
                        <input type="hidden" name="txtRentID" value="${car.rentID}"/>
                        <td><input type="submit" name="btnAction" value="Detail"</td>
                        <td><input type="submit" name="btnAction" value="Delete Order"</td>
                        </tr>
                    </form>
                </c:forEach>
            </tbody>
        </table>
    </c:if>



    <c:set var="message" value="${requestScope.MESSAGE}"/>
    <c:if test="${not empty message}">
        <script>
            alert("${message}");
        </script>
    </c:if>
</body>
</html>
