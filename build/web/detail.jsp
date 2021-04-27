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
        <form action="DetailHistoryController">
            Car Name <input type="text" name="txtCarName" value="${param.txtCarName}"/>
            Date (Start date) <input type="date" name="txtStartDay" value="${param.txtStartDay}" required/>
            <button <input type="submit" /><i class="fas fa-search"></i></button>
        </form>
        <a class="nav-link" href="HistoryController">Return</a>
        <c:if test="${not empty requestScope.HISTORYDETAIL}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Car ID</th>
                        <th>Car Brand</th>
                        <th>Car Name</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Start Day</th>
                        <th>End Day</th>
                        <th>Discount Code</th>
                        <th>Feedback</th>

                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="car" varStatus="counter" items="${requestScope.HISTORYDETAIL}">
                    <form action="FeedbackController">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${car.carID}</td>
                            <td>${car.carBrand}</td>
                            <td>${car.carName}</td>
                            <td>${car.price}</td>
                            <td>${car.quantity}</td>
                            <td>${car.startDay}</td>
                            <td>${car.endDay}</td>
                            <td>${car.discountCode}</td>
                        <input type="hidden" name="txtCarBrand" value="${car.carBrand}"/>
                        <input type="hidden" name="txtCarID" value="${car.carID}"/>
                        <input type="hidden" name="txtCarName" value="${car.carName}"/>
                        <td><input type="submit" name="btnAction" value="Feedback"/></td>
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
