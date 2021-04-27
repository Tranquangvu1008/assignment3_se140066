<%-- 
    Document   : showcart
    Created on : Feb 24, 2021, 5:34:21 PM
    Author     : SE140066
--%>

<%@page import="dtos.CarDTO"%>
<%@page import="dtos.CartDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show Cart Page</title>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.1/css/all.css" integrity="sha384-vp86vTRFVJgpjF9jiIGPEEqYqlDwgyBgEF109VFjmqGmIY/Y4HV4d3Gp2irVfcrp" crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/css?family=Poppins" rel="stylesheet" />
        <link href="css/view.css" rel="stylesheet" type="text/css"/>
        <style>

        </style>
    </head>
    <body>
        <div class="container">
            <div class="heading">
                <h1>
                    <span class="shopper">RENT CART</span> 
                </h1> 
                <a href="MenuController" class="visibility-cart transition is-open"><i class="fas fa-long-arrow-alt-left"></i></a>   
            </div>

            <h1>Total day: ${sessionScope.totalDay}</h1>
            <div class="cart transition is-open">
                <div class="table">
                    <div class="layout-inline row th">
                        <div class="col col-pro">Car Brand</div>
                        <div class="col col-pro">Car Name</div>
                        <div class="col col-pro">Car Type</div>
                        <div class="col col-price align-center ">Price</div>
                        <div class="col col-qty align-center">Quantity</div>
                        <div class="col col-pro">Start Day</div>
                        <div class="col col-pro">End Day</div>
                        <div class="col">Total</div>
                        <div class="col col-pro">Update</div>
                        <div class="col col-pro">Delete</div>

                    </div>



                    <c:set var="listCart" value="${sessionScope.CART.cart}"/>
                    <c:if test="${not empty listCart}">
                        <c:forEach var="cart" items="${listCart}" varStatus="counter">
                            <c:set var="totalPrice" value="${sessionScope.totalDay * cart.value.quantity * cart.value.price}"/>
                            <form action="MainController">
                                <div class="layout-inline row">
                                    <div class="col col-pro layout-inline">
                                        <p>${cart.value.carBrand}</p>
                                    </div>
                                    <div class="col col-pro layout-inline">
                                        <p>${cart.value.carName}</p>
                                    </div>
                                    <div class="col col-pro layout-inline">
                                        <p>${cart.value.categoryID}</p>
                                    </div>

                                    <div class="col col-price col-numeric align-center ">
                                        <h2>
                                            <c:set var="discount" value="${sessionScope.DISCOUNT}"/>
                                            <c:if test="${not empty discount}">
                                                <c:if test="${cart.value.carBrand eq sessionScope.CARBRAND}">
                                                    <c:out value="${sessionScope.PRICE}"/>
                                                    <i class="fas fa-arrow-down"></i>
                                                    <c:out value="${sessionScope.DISCOUNT}"/>
                                                    <i class="fas fa-percent"></i>
                                                </c:if>
                                            </c:if>
                                        </h2>
                                        <p><div style="color: red; font-size: 20px;">${cart.value.price}</div></p> 
                                    </div>

                                    <div class="col col-qty layout-inline">
                                        <input type="number" name="txtQuantity" min="1" value="${cart.value.quantity}" required/>
                                    </div>
                                    <div class="col col-pro layout-inline">
                                        <p>${cart.value.startDay}</p>
                                    </div>
                                    <div class="col col-pro layout-inline">
                                        <p>${cart.value.endDay}</p>
                                    </div>
                                    <div class="col col-total col-numeric">               
                                        <p>${totalPrice}</p>
                                    </div> 
                                    <div class="col layout-inline">
                                        <input type="hidden" name="txtCarID" value="${cart.value.carID}"/>
                                        <input type="submit" name="btnAction" value="Update"/>  
                                    </div> 
                                    <div class="col layout-inline">
                                        <input type="hidden" name="txtCarBrand" value="${cart.value.carBrand}"/>
                                        <input type="hidden" name="txtCode" value="${cart.value.code}"/>
                                        <input type="submit" name= "btnAction" value="Delete" onclick="return confirm('Are you sure you want to Delete this car?');"/> 
                                    </div> 

                                    <c:set var="total" value="${total + totalPrice}"/>

                                </div>
                            </form>
                        </c:forEach>
                        <div class="tf">
                            <div class="row layout-inline">
                                <div class="col">
                                    <p>Total ${total}</p>
                                </div>
                                <div class="col"></div>
                            </div>
                        </div>   

                    </c:if>
                </div>
                <form action="ApplyController">
                    <h3>Discount Code</h3> <input type="text" name="txtCode" value="${sessionScope.CODE}" />
                    <input style="font-weight: bold; color: red; width: 40px;" type="submit" value="X" name="btnAction"/>  
                    <input style="width: 80px;"type="submit" value="Apply"/>  
                </form>
                <form action="ConfirmController">
                    <p>Rental Date</p> <input type="date" name="startDay" value="${sessionScope.DAYSTART}" min="${sessionScope.START_DATE}" readonly/>
                    <p>Return Date</p> <input type="date" name="endDay" value="${sessionScope.DAYEND}" min="${sessionScope.END_DATE}" readonly/>
                    
                    <c:forEach var="info"  items="${sessionScope.INFO}">
                        <p>Full name: </p>   <input type="text" name="txtFullName" value="${info.fullName}" required/>
                        <p>Phone number: </p>   <input type="text" name="txtPhoneNumber" minlength="10" maxlength="15" value="${info.phone}" required/>
                        <p>Email: </p>   <input type="email" name="txtEmail" value="${info.email}" required/>
                    </c:forEach> 

                    <h4>
                        <input type="radio" name="payment" value="cod" checked><p>COD</p>
                        <input type="radio" name="payment" value="card"><p>Credit Card/ Debit Card</p>
                    </h4>

                    <input class="btn btn-update" type="submit" value="Confirm"/>  
                </form>
            </div>
            <c:set var="confirm" value="${requestScope.CONFIRM}"/>
            <c:if test="${not empty confirm}">
                <script>
                    alert("${confirm}");
                </script>
            </c:if>
            <c:set var="warning" value="${requestScope.WARNING}"/>
            <c:if test="${not empty warning}">
                <script>
                    alert("you are applying code ${sessionScope.CODE}");
                </script>
            </c:if>
    </body>
</html>

