<%-- 
    Document   : feedback
    Created on : Mar 5, 2021, 9:36:17 AM
    Author     : SE140066
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Feedback Page</title>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.1/css/all.css" integrity="sha384-vp86vTRFVJgpjF9jiIGPEEqYqlDwgyBgEF109VFjmqGmIY/Y4HV4d3Gp2irVfcrp" crossorigin="anonymous">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
    </head>
    <body>

        <form action="FeedbackController">
            Name: </br> <input type="text" name="txtUserID" value="${sessionScope.USERID}" readonly/></br>
            Rent ID: </br><input type="text" name="txtRentID" value="${sessionScope.rentID}" readonly/></br>
            Car ID:</br><input type="text" name="txtCarID" value="${param.txtCarID}" readonly/></br>
            Car Name:</br><input type="text" name="txtCarName" value="${param.txtCarBrand} ${param.txtCarName}" readonly/></br>
            Content: </br><input type="text" name="txtContent" <c:if test="${not empty requestScope.content}"> value="${requestScope.content}" readonly</c:if> <c:if test="${empty requestScope.content}"> value="${param.txtContent}" required </c:if> /></br>
            Rating: </br><input type="number" name="txtPoint" <c:if test="${not empty requestScope.point}">value="${requestScope.point}" readonly</c:if> <c:if test="${empty requestScope.point}">value="${param.txtPoint}" required</c:if> min="1" max="10" /></br>
            <c:if test="${empty requestScope.FEEDBACK}"><button <input type="submit" onclick="return confirm('Are you sure?');"/><i class="fas fa-paper-plane"></i></button></c:if>
        </form>
        <form action="HistoryController">
            <c:if test="${not empty requestScope.FEEDBACK}"><button <input type="submit"/><i class="fas fa-undo-alt"></i></button></c:if>
        </form>
    </body>
</html>
