<%-- 
    Document   : news
    Created on : May 25, 2023, 8:57:40 PM
    Author     : Nam
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <!-- Required meta tags-->
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="au theme template">
        <meta name="author" content="Hau Nguyen">
        <meta name="keywords" content="au theme template">

        <!-- Title Page-->
        <title>Order Management</title>

        <!-- Fontfaces CSS-->
        <link href="css/font-face.css" rel="stylesheet" media="all">
        <link href="vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">
        <link href="vendor/font-awesome-5/css/fontawesome-all.min.css" rel="stylesheet" media="all">
        <link href="vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all">

        <!-- Bootstrap CSS-->
        <link href="vendor/bootstrap-4.1/bootstrap.min.css" rel="stylesheet" media="all">

        <!-- Vendor CSS-->
        <link href="vendor/animsition/animsition.min.css" rel="stylesheet" media="all">
        <link href="vendor/bootstrap-progressbar/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet" media="all">
        <link href="vendor/wow/animate.css" rel="stylesheet" media="all">
        <link href="vendor/css-hamburgers/hamburgers.min.css" rel="stylesheet" media="all">
        <link href="vendor/slick/slick.css" rel="stylesheet" media="all">
        <link href="vendor/select2/select2.min.css" rel="stylesheet" media="all">
        <link href="vendor/perfect-scrollbar/perfect-scrollbar.css" rel="stylesheet" media="all">

        <!-- Main CSS-->
        <link href="css/theme.css" rel="stylesheet" media="all">

    </head>

    <body class="animsition">
        <div class="page-wrapper">
            <!-- HEADER MOBILE-->
            <%@include file="headerMobile.jsp" %>
            <!-- END HEADER MOBILE-->

            <!-- MENU SIDEBAR-->
            <aside class="menu-sidebar d-none d-lg-block">
                <%@include file="sidebar.jsp" %>
            </aside>
            <!-- END MENU SIDEBAR-->

            <!-- PAGE CONTAINER-->
            <div class="page-container">
                <!-- HEADER DESKTOP-->
                <header class="header-desktop">
                    <%@include file="header.jsp" %>
                </header>
                <!-- END HEADER DESKTOP-->

                <div class="main-content">
                    <div class="section__content section__content--p30">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="overview-wrap">
                                        <h2 class="title-1">Statistics</h2>
                                    </div>
                                </div>
                            </div>
                            <div class="row m-t-25">
                                <div class="col-sm-6 col-lg-3">
                                    <div class="overview-item overview-item--c4">
                                        <div class="overview__inner">
                                            <div class="overview-box clearfix">
                                                <div class="text">
                                                    <h2>
                                                        <fmt:setLocale value = "vi_VN"/>
                                                        <fmt:formatNumber value = "${totalEarn}" type = "currency"/>
                                                    </h2>
                                                    <span>total earnings</span>
                                                </div>
                                            </div>
                                            <div class="overview-chart">
                                                <canvas id="widgetChart4"></canvas>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6 col-lg-3">
                                    <div class="overview-item overview-item--c2">
                                        <div class="overview__inner">
                                            <div class="overview-box clearfix">
                                                <div class="icon">
                                                    <i class="zmdi zmdi-shopping-cart"></i>
                                                </div>
                                                <div class="text">
                                                    <h2>${totalSold}</h2>
                                                    <span>items sold</span>
                                                </div>
                                            </div>
                                            <div class="overview-chart">
                                                <canvas id="widgetChart2"></canvas>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- MAIN CONTENT-->
                    <div class="main-content">
                        <div class="section__content section__content--p30">
                            <div class="container-fluid">
                                <div class="table-data__tool">
                                    <div class="table-data__tool-left">
                                        <form id="formSort" action="order" method="post">
                                            <div class="rs-select2--light rs-select2--md">
                                                <p>Sort:</p>
                                                <input name="act" value="sort" hidden/>
                                                <select class="js-select2" name="sortBy" onchange="submitFormSort()">
                                                    <c:forEach items="${sortBys}" var="item">
                                                        <option value="${item.getTitle()}" ${(item.getTitle() == sortBy)?'selected':''}>${item.getContent()}</option>
                                                    </c:forEach>
                                                </select>
                                                <div class="dropDownSelect2"></div>
                                            </div>

                                            <div class="rs-select2--light rs-select2--sm">
                                                <p>Filter:</p>
                                                <select class="js-select2" name="filterBy" onchange="submitFormSort()">
                                                    <option value="all">All</option>
                                                    <c:forEach items="${filterBys}" var="item">
                                                        <option value="${item[0]}" ${(item[0] == filterBy)?'selected':''}>${item[1]}</option>
                                                    </c:forEach>
                                                </select>
                                                <div class="dropDownSelect2"></div>
                                            </div>
                                        </form>
                                    </div>  
                                    <div class="table-data__tool-right">
                                        <p>Search:</p>
                                        <form class="form-header" action="order" method="POST">
                                            <input name="act" value="search" hidden/>
                                            <input class="au-input au-input--xl" value="${searchId}" type="number" name="orderId" placeholder="Enter an order ID" />
                                            <button class="au-btn--submit" type="submit">
                                                <i class="zmdi zmdi-search"></i>
                                            </button>
                                        </form>
                                    </div>
                                </div>
                                <c:if test="${oList.size() == 0}">
                                    <h4 class="title-5 m-b-35">No items found.</h4>
                                </c:if>
                                <c:if test="${oList.size() != 0}">
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="table-responsive table--no-card m-b-30">
                                                <table class="table table-borderless table-striped table-earning">
                                                    <thead>
                                                        <tr>
                                                            <th>Order ID</th>
                                                            <th>Created at</th>
                                                            <th>Total</th>
                                                            <th>Status</th>
                                                            <th>View</th>
                                                        </tr>
                                                    </thead>                                            
                                                    <tbody id="tbodyRow">
                                                        <c:forEach var="item" items="${requestScope.oList}">
                                                            <tr>
                                                                <td>${item.getOrderId()}</td>
                                                                <td>${item.getCreatedAt()}</td>
                                                                <td>
                                                                    <fmt:setLocale value = "vi_VN"/>
                                                                    <fmt:formatNumber value = "${item.getGrandtotal()}" type = "currency"/>
                                                                </td>
                                                                <td>${item.getStatus()}</td>
                                                                <td>
                                                                    <form action="order" method="post">
                                                                        <input name="orderId" value="${item.getOrderId()}" hidden>
                                                                        <button type="submit" name="act" value="view" class="actionButton">Detail</button>
                                                                    </form>
                                                                </td>
                                                            </tr>
                                                        </c:forEach>                                                
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </c:if>

                                <c:if test="${oList.size() > 1}">
                                    <div class="d-flex justify-content-end">
                                        <form id="paging" action="order" method="post">
                                            <input name="act" value="paging" hidden/>
                                            <input name="sortBy" value="${sortBy}" hidden>
                                            <input name="filterBy" value="${filterBy}" hidden>
                                            <input id="index" name="index" hidden>
                                            <ul class="pagination">
                                                <li class="page-item">
                                                    <a href="#" aria-label="Previous"class="page-link" onclick="submitFormPaging(${currentPage - 1})">
                                                        <span aria-hidden="true">&laquo;</span>
                                                    </a>
                                                </li>                                                
                                                <c:forEach begin="1" end="${endPage}" var="i">
                                                    <li class="page-item ${currentPage == i ? 'active' : ''}"><a class="page-link" onclick="submitFormPaging(${i})">${i}</a></li>  
                                                    </c:forEach>
                                                <li class="page-item">
                                                    <a href="#" aria-label="Previous"class="page-link" onclick="submitFormPaging(${currentPage + 1})">
                                                        <span aria-hidden="true">&raquo;</span>
                                                    </a>
                                                </li>                                                
                                            </ul>
                                        </form>
                                    </div>
                                </c:if>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="copyright">
                                            <p>Copyright © 2018 Colorlib. All rights reserved. Template by <a href="https://colorlib.com">Colorlib</a>.</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

            <script>
                function submitFormSort() {
                    document.getElementById("formSort").submit();
                }

                function submitFormPaging(page) {
                    document.getElementById("index").value = page;
                    document.getElementById("paging").submit();
                }
            </script>        
            <!--Jquery Ajax-->
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
            <!-- Jquery JS-->
            <script src="vendor/jquery-3.2.1.min.js"></script>
            <!-- Bootstrap JS-->
            <script src="vendor/bootstrap-4.1/popper.min.js"></script>
            <script src="vendor/bootstrap-4.1/bootstrap.min.js"></script>
            <!-- Vendor JS       -->
            <script src="vendor/slick/slick.min.js">
            </script>
            <script src="vendor/wow/wow.min.js"></script>
            <script src="vendor/animsition/animsition.min.js"></script>
            <script src="vendor/bootstrap-progressbar/bootstrap-progressbar.min.js">
            </script>
            <script src="vendor/counter-up/jquery.waypoints.min.js"></script>
            <script src="vendor/counter-up/jquery.counterup.min.js">
            </script>
            <script src="vendor/circle-progress/circle-progress.min.js"></script>
            <script src="vendor/perfect-scrollbar/perfect-scrollbar.js"></script>
            <script src="vendor/chartjs/Chart.bundle.min.js"></script>
            <script src="vendor/select2/select2.min.js">
            </script>

            <!-- Main JS-->
            <script src="js/main.js"></script>

    </body>

</html>
<!-- end document-->

