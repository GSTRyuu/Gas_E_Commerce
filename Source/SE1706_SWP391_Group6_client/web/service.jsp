<%-- 
    Document   : cart
    Created on : May 13, 2023, 5:35:52 PM
    Author     : Dell
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Services</title>

        <!-- Google Fonts -->
        <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,200,300,700,600' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Roboto+Condensed:400,700,300' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Raleway:400,100' rel='stylesheet' type='text/css'>

        <!-- Bootstrap -->
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

        <!-- Font Awesome -->
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">

        <!-- Custom CSS -->
        <link rel="stylesheet" href="css/owl.carousel.css">
        <link rel="stylesheet" href="style.css">
        <link rel="stylesheet" href="css/responsive.css">
        <link rel="stylesheet" href="css/toastr.min.css">

        <style>
            #input_serial {
                width: 50%;
            }
            #context {
                text-align: center;
                margin: 10px;
                font-size: large;
            }
        </style>
    </head>
    <body onload="${sessionScope.functionToast}">
        <%
            request.getSession().removeAttribute("functionToast");
        %>
        
        <div id="header-area">
            <%@include file="header.jsp"%>
        </div>

        <c:set var="pr" value="Product" />
        <c:set var="ac" value="Service" />
        <c:set var="n" value="1" />
        <div class="mainmenu-area">
            <div class="container">
                <div class="row">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                    </div> 
                    <div class="navbar-collapse collapse">
                        <ul class="nav navbar-nav">

                            <c:forEach items="${sessionScope.navbar}" var="nav">
                                <c:if test="${nav.title eq pr}">
                                    <li class="dropdown ${nav.title eq ac ? "active" : "" }">
                                        <a class="dropdown-toggle" data-toggle="dropdown">${nav.title}</a>
                                        <ul class="dropdown-menu">
                                            <li><a href="${nav.link}">All Products</a></li>
                                                <c:forEach items="${cList}" var="item">
                                                <li><a href="shop?categoryid=${item.getCategoryid()}" class="" data-id="76">${item.getCname()}</a></li>
                                                </c:forEach>
                                        </ul>
                                    </li>
                                </c:if>

                                <c:if test="${nav.title ne pr}">
                                    <li class="${nav.title eq ac ? "active" : "" }"><a href="${nav.link}"><i ></i>${nav.title}</a></li>
                                        </c:if>
                                    </c:forEach>

                        </ul>
                    </div>
                </div>
            </div>
        </div>

        <div class="product-big-title-area">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="product-bit-title text-center">
                            <h2>Services</h2>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- End Page title area -->

        <div class="single-product-area">
            <div class="zigzag-bottom"></div>
            <div class="container">
                <div class="row" style="text-align: center; min-width: 75%">
                    <div class="col-md-12">
                        <h3>Warranty search</h3>
                        <input id="input_serial" type="text" id="input_serial" name="serial" placeholder="Enter serial number of your product" oninput="clearField()">
                        <button type="submit" onclick="showWarranty()">Search</button>
                    </div>
                </div>
                <div class="row">
                    <div id="context"></div>
                </div>

                <c:if test="${tList.size() > 0 }">
                    <div class="row" style="text-align: center; min-width: 75%; margin-top: 3%">
                        <div>
                            <h3>All services</h3>
                            <div class="row">
                                <div class="col-md-2"></div>
                                <div class="col-md-8">
                                    <div class="row">
                                        <c:forEach items="${tList}" var="item">
                                            <div class="col-md-3 col-sm-6">
                                                <div class="single-promo" style="border: solid 1.5px darkorange; margin-bottom: 15px">
                                                    <input id="ofType-${item}" value="${item}" hidden>
                                                    <button onclick="showTable('${item}')" style="background-color: inherit; border: none">
                                                        <p>${item}</p>
                                                    </button>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>
                                    <div id="sTable" class="row" style="margin-top: 5%">
                                    </div>
                                </div>
                                <div class="col-md-2"></div>
                            </div>
                        </div>
                    </div>
                </c:if>
                <div class="row">
                    <div id="context1"></div>
                </div>
            </div>
        </div>
        <!-- End Page title area -->

        <div id ="footer-area">
            <%@include file="footer.jsp"%>
        </div>

        <script type="text/javascript">
            function showWarranty() {
                $.ajax({
                    type: "POST",
                    url: "service",
                    data: {
                        serial: document.getElementById("input_serial").value,
                        act: "search"
                    },
                    success: function (x) {
                        document.getElementById("context").innerHTML = x;
                    }
                });
            }

            function showTable(input) {
                var val = document.getElementById("ofType-" + input).value;
                $.ajax({
                    type: "POST",
                    url: "service",
                    data: {
                        ofType: val,
                        act: "show"
                    },
                    success: function (x) {
                        document.getElementById("sTable").innerHTML = x;
                    }
                });
            }

            function showLess() {
                document.getElementById("sTable").innerHTML = "";
            }

            function clearField() {
                document.getElementById("context").innerHTML = "";
            }
        </script>
        
        <script>
            function showToast(type, title) {
                switch (type) {
                    case 'success':
                        toastr.success(title, 'Notification');
                        break;
                    case 'info':
                        toastr.info(title, 'Notification');
                        break;
                    case 'warning':
                        toastr.warning(title, 'Warning');
                        break;
                    case 'error':
                        toastr.error(title, 'Notification');
                        break;
                    default:
                        break;
                }
            }
        </script>
        <script src="js/jquery.min.js"></script>
        <script src="js/toastr.min.js"></script>

        <!-- Latest jQuery form server -->
        <script src="https://code.jquery.com/jquery.min.js"></script>

        <!-- Bootstrap JS form CDN -->
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

        <!-- jQuery sticky menu -->
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/jquery.sticky.js"></script>

        <!-- jQuery easing -->
        <script src="js/jquery.easing.1.3.min.js"></script>

        <!-- Main Script -->
        <script src="js/main.js"></script>

    </body>
</html>
