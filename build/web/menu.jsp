<%-- 
    Document   : menu
    Created on : Feb 23, 2021, 8:32:30 PM
    Author     : SE140066
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu Fast Food Restaurant</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">  
        <meta name="viewport" content="width=device-width, initial-scale=1" />

        <!-- Site Metas -->
        <meta name="keywords" content="">
        <meta name="description" content="">
        <meta name="author" content="">

        <!-- Site Icons -->
        <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
        <link rel="apple-touch-icon" href="images/apple-touch-icon.png">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="css/bootstrap.min.css">    
        <!-- Site CSS -->
        <link rel="stylesheet" href="css/style_1.css">    
        <!-- Responsive CSS -->
        <link rel="stylesheet" href="css/responsive.css">
        <!-- Search CSS -->
        <link href="css/search.css" rel="stylesheet" type="text/css"/>
        <!--Custom-->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.1/css/all.css" integrity="sha384-vp86vTRFVJgpjF9jiIGPEEqYqlDwgyBgEF109VFjmqGmIY/Y4HV4d3Gp2irVfcrp" crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/css?family=Poppins" rel="stylesheet" />


        <style>
            .why-text{
                text-align: center;
            }
            .inner-menu-box{
                float: left;

            }
        </style>

    </head>
    <body>
        <!-- Start header -->
        <header class="top-navbar">
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <div class="container">
                    <a class="logo" href="welcome.jsp">
                        <img src="https://www.pngitem.com/pimgs/m/189-1898946_kfc-thc-thc-kfc-logo-hd-png-download.png" width="147" height="85" alt=""/>
                    </a>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbars-rs-food" aria-controls="navbars-rs-food" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbars-rs-food">
                        <ul class="navbar-nav ml-auto">
                            <li class="nav-item active"><a class="nav-link" href="welcome.jsp">Home</a></li>
                            <li class="nav-item"><a class="nav-link" href="MainController?btnAction=Menu">Menu</a></li>
                            <li class="nav-item"><a class="nav-link" href="contact.jsp">Contact</a></li>


                            <c:choose>
                                <c:when test="${empty sessionScope.USERID}">
                                    <li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>
                                    </c:when>

                                <c:when test="${not empty sessionScope.USERID}">
                                    <li class="nav-item"><a class="nav-link" href="LogoutController">Logout</a></li>
                                    </c:when>
                                </c:choose>
                        </ul>
                    </div>
                    <c:out value="Hello: ${sessionScope.FULLNAME}"/>
                </div>
            </nav>
        </header>
        <!-- End header -->

        <!-- Start Menu -->
        <div class="menu-box">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="heading-title text-center">
                            <h2>CAR RENTAL</h2>

                            <!--Search Bar-->
                            <div class="s002">
                                <form action="MenuController">
                                    <div class="inner-form">
                                        <div class="input-field first-wrap">
                                            <p>Car Name</p> <input id="search" type="text" name="txtSearch" value="${param.txtSearch}" />
                                        </div>
                                        <div class="input-field second-wrapp">
                                            <p>Quantity</p> <input id="search" type="number" name="txtQuantity" value="" />
                                        </div>
                                        <div class="input-field third-wrap">
                                            <p>Rental Date</p> <input type="date" name="startDay" value="${sessionScope.DAYSTART}" min="${sessionScope.START_DATE}" required/>
                                        </div>
                                        <div class="input-field third-wrap">
                                            <p>Return Date</p> <input type="date" name="endDay" value="${sessionScope.DAYEND}" min="${sessionScope.END_DATE}" required/>
                                        </div>
                                        <div class="input-field fouth-wrap">
                                            <p>Category</p> <c:if test="${not empty requestScope.LIST_CATEGORY}">
                                                <select data-trigger="" name="txtCategory">
                                                    <option selected>${param.txtCategory}</option>
                                                    <option disabled>---</option>
                                                    <c:forEach var="cate" varStatus="counter" items="${requestScope.LIST_CATEGORY}">
                                                        <option>${cate}</option>
                                                    </c:forEach>
                                                </select>
                                            </c:if>
                                        </div>
                                        <div class="input-field fifth-wrap">
                                            <button <input class="btn-search" type="submit" />SEARCH</button>
                                        </div>
                                    </div>
                                </form>
                            </div>                            
                        </div>


                        <!--Menu-->
                        <div class="row inner-menu-box">
                            <div class="col-12">
                                <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
                                    <a class="nav-link active" id="v-pills-home-tab" href="MenuController" role="tab" aria-controls="v-pills-home">Load</a>
                                    <a class="nav-link" id="v-pills-messages-tab" href="ShowCartController" role="tab"><i class="fas fa-cart-plus"></i> SHOW CART</a>
                                    <a class="nav-link" id="v-pills-messages-tab" href="HistoryController" role="tab"><i class="fas fa-store"></i> PURCHASE HISTORY</a>
                                </div>
                            </div>
                        </div>

                        <div class="container">
                            <div class="row">
                                <c:forEach var="car" varStatus="counter" items="${requestScope.LIST}">
                                    <div class="col-md-3 col-sm-6">
                                        <form action="OrderController">
                                            <div class="gallery-single fix">
                                                <img src="${car.image}" class="img-fluid" alt="Image">

                                                <div class="why-text">
                                                    <h4>${car.carBrand} ${car.carName}</h4>
                                                    <p>Color: ${car.color} | Slot: ${car.slot}</p>
                                                    <p>Category: ${car.categoryID}</p>
                                                    <p>Quantity: ${car.quantity}</p>
                                                    <h5>-$${car.price}- <button <input type="submit" /><i class="fas fa-shopping-cart"></i></button></h5>
                                                </div>
                                            </div>
                                            <input type="hidden" name="txtCarID" value="${car.carID}"/>
                                            <input type="hidden" name="txtCarBrand" value="${car.carBrand}"/>
                                            <input type="hidden" name="txtCarName" value="${car.carName}"/>
                                            <input type="hidden" name="txtColor" value="${car.color}"/>
                                            <input type="hidden" name="txtSlot" value="${car.slot}"/>
                                            <input type="hidden" name="txtImage" value="${car.image}"/>
                                            <input type="hidden" name="txtPrice" value="${car.price}"/>
                                            <input type="hidden" name="txtCategoryID" value="${car.categoryID}"/>
                                            <input type="hidden" name="txtQuantity" value="${car.quantity}"/>
                                        </form>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>

                        <c:forEach begin="1" end="${requestScope.NUMBER_PAGE}" var="i">
                            <c:url var="paging" value="MenuController?txtsearch=${param.txtSearch}&txtQuantity=${param.txtQuantity}&startDay=${param.startDay}&endDay=${param.endDay}&txtCategory=${param.txtCategory}">
                                <c:param name="index" value="${i}"/>
                            </c:url>
                            <a href="${paging}">${i}</a>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- End Menu -->

    <!-- Start Contact info -->
    <div class="contact-imfo-box">
        <div class="container">
            <div class="row">
                <div class="col-md-4">
                    <i class="fa fa-volume-control-phone"></i>
                    <div class="overflow-hidden">
                        <h4>Phone</h4>
                        <p class="lead">
                            +01 123-456-4590
                        </p>
                    </div>
                </div>
                <div class="col-md-4">
                    <i class="fa fa-envelope"></i>
                    <div class="overflow-hidden">
                        <h4>Email</h4>
                        <p class="lead">
                            yourmail@gmail.com
                        </p>
                    </div>
                </div>
                <div class="col-md-4">
                    <i class="fa fa-map-marker"></i>
                    <div class="overflow-hidden">
                        <h4>Location</h4>
                        <p class="lead">
                            800, Lorem Street, US
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- End Contact info -->
    <c:set var="message" value="${requestScope.MESSAGE}"/>
    <c:if test="${not empty message}">
        <script>
            alert("${message}");
        </script>
    </c:if>
    <!-- ALL JS FILES -->
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

    <!-- ALL PLUGINS -->
    <script src="js/jquery.superslides.min.js"></script>
    <script src="js/images-loded.min.js"></script>
    <script src="js/isotope.min.js"></script>
    <script src="js/baguetteBox.min.js"></script>
    <script src="js/form-validator.min.js"></script>
    <script src="js/contact-form-script.js"></script>
    <script src="js/custom.js"></script>

</body>
</html>

