<%-- 
    Document   : confirm
    Created on : Mar 5, 2021, 9:57:39 PM
    Author     : SE140066
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>You have successfully ordered !</h1>
        <h1>Total day: ${sessionScope.totalDay}</h1>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Car Brand</th>
                    <th>Car Name</th>
                    <th>Car Type</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Total</th>
                </tr>
            </thead>
            <tbody>
                <c:set var="listCart" value="${sessionScope.CART.cart}"/>
                <c:if test="${not empty listCart}">
                    <c:forEach var="cart" varStatus="counter" items="${listCart}">
                        <c:set var="totalPrice" value="${sessionScope.totalDay * cart.value.quantity * cart.value.price}"/>
                        <tr>
                            <td>${counter.count}</td>
                            <td>${cart.value.carBrand}</td>
                            <td>${cart.value.carName}</td>
                            <td>${cart.value.categoryID}</td>
                            <td>${cart.value.price}</td>
                            <td>${cart.value.quantity}</td>
                            <td>${totalPrice}</td>
                        </tr>
                        <c:set var="total" value="${total + totalPrice}"/>

                    </c:forEach>
                <p>Total ${total}</p>
            </c:if>
        </tbody>
    </table>
    <form action="ConfirmController">
        <input type="submit" value="Confirm"/>  
    </form>
</body>
</html>
