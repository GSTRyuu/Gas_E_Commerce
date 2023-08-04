<%-- 
    Document   : home
    Created on : 30-05-2023, 02:40:10
    Author     : fpt
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
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
        <title>Dashboard</title>

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
        <!-- JS Chart-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
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

                <!-- MAIN CONTENT-->
                <div class="main-content">
                    <div class="section__content section__content--p30">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="overview-wrap">
                                        <h2 class="title-1">Overview</h2>
                                    </div>
                                </div>
                            </div>
                            <div class="row m-t-25">
                                <div class="col-sm-6 col-lg-3">
                                    <div class="overview-item overview-item--c1">
                                        <div class="overview__inner">
                                            <div class="overview-box clearfix">
                                                <div class="icon">
                                                    <i class="zmdi zmdi-account-add"></i>
                                                </div>
                                                <div class="text">
                                                    <h2>${newU}</h2>
                                                    <span>Total User</span>
                                                </div>
                                            </div>
                                            <div class="overview-chart">
                                                <canvas id="widgetChart1"></canvas>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6 col-lg-3">
                                    <div class="overview-item overview-item--c2">
                                        <div class="overview__inner">
                                            <div class="overview-box clearfix">
                                                <div class="icon">
                                                    <i class="zmdi zmdi-accounts-alt"></i>
                                                </div>
                                                <div class="text">
                                                    <h2>${count}</h2>
                                                    <span>Online User</span>
                                                </div>
                                            </div>
                                            <div class="overview-chart">
                                                <canvas id="widgetChart2"></canvas>
                                            </div>
                                        </div>
                                    </div>
                                </div>
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
                                                <canvas id="widgetChart3"></canvas>
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
                                                <canvas id="widgetChart4"></canvas>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-lg-6">
                                    <div class="au-card recent-report">
                                        <div class="au-card-inner">
                                            <h3 class="title-2">Earning report</h3>
                                            <div class="chart-info">
                                                <div class="chart-info__left">
                                                    <div class="chart-note">
                                                        <span>Currency: VND</span>
                                                    </div>
                                                </div>
<!--                                                <div class="chart-info__right">
                                                    <div class="chart-statis">
                                                        <span class="index incre">
                                                            <i class="zmdi zmdi-long-arrow-up"></i>25%</span>
                                                        <span class="label">Gas tank</span>
                                                    </div>
                                                    <div class="chart-statis mr-0">
                                                        <span class="index decre">
                                                            <i class="zmdi zmdi-long-arrow-down"></i>10%</span>
                                                        <span class="label">Gas stove</span>
                                                    </div>
                                                    <div class="chart-statis mr-1">
                                                        <span class="index decre">
                                                            <i class="zmdi zmdi-long-arrow-down"></i>15%</span>
                                                        <span class="label">Gas accessary</span>
                                                    </div>
                                                </div>-->
                                            </div>
                                            <div class="recent-report__chart">
                                                <canvas id="earningChart" style="width:100%;max-width:600px"></canvas>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-6">
                                    <div class="au-card chart-percent-card">
                                        <div class="au-card-inner">
                                            <h3 class="title-2">Items sold chart</h3>
                                            <div class="row ">
                                                <div class="chart-info">
                                                    <div class="chart-info__left">
                                                        <div class="chart-note">
                                                            <span class="dot dot--red"></span>
                                                            <span>Gas Tank</span>
                                                        </div>
                                                        <div class="chart-note mr-0">
                                                            <span class="dot dot--green"></span>
                                                            <span>Gas Stove</span>
                                                        </div>
                                                        <div class="chart-note mr-1">
                                                            <span class="dot dot--blue"></span>
                                                            <span>Accessories</span>
                                                        </div>
                                                    </div>
<!--                                                    <div class="chart-info__right">
                                                        <div class="chart-statis">
                                                            <span class="index incre">
                                                                <i class="zmdi zmdi-long-arrow-up"></i>25%</span>
                                                            <span class="label">Gas Tank</span>
                                                        </div>
                                                        <div class="chart-statis mr-0">
                                                            <span class="index decre">
                                                                <i class="zmdi zmdi-long-arrow-down"></i>10%</span>
                                                            <span class="label">Gas Stove</span>
                                                        </div>
                                                        <div class="chart-statis mr-1">
                                                            <span class="index decre">
                                                                <i class="zmdi zmdi-long-arrow-down"></i>15%</span>
                                                            <span class="label">Accessories</span>
                                                        </div>
                                                    </div>-->
                                                </div>
                                                <div class="recent-report__chart">
                                                    <canvas id="itemChart" style="width:100%;max-width:600px"></canvas>
                                                </div>  
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-12">
                                    <div class="copyright">
                                        <p>Copyright © 2018 Colorlib. All rights reserved. Template by <a
                                                href="https://colorlib.com">Colorlib</a>.</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- END MAIN CONTENT-->
                <!-- END PAGE CONTAINER-->
            </div>

        </div>

        <script>
            var xValues = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
            var barColors = "darkorange";

            new Chart("earningChart", {
                type: "bar",
                data: {
                    labels: xValues,
                    datasets: [{
                            backgroundColor: barColors,
                            data: ${listEarning}
                        }]
                },
                options: {
                    legend: {display: false},
                    title: {
                        display: true
                    }
                }
            });
        </script>

        
        <script>
            const kValues = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];

            new Chart("itemChart", {
                type: "line",
                data: {
                    labels: kValues,
                    datasets: [{
                            data: ${listTank},
                            borderColor: "red",
                            fill: false
                        }, {
                            data: ${listStove},
                            borderColor: "green",
                            fill: false
                        }, {
                            data: ${listAccessories},
                            borderColor: "blue",
                            fill: false
                        }]
                },
                options: {
                    legend: {display: false}
                }
            });
        </script>

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
