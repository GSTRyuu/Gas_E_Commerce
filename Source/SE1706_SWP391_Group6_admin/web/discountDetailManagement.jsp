<%-- 
    Document   : newsManagement
    Created on : May 25, 2023, 8:57:49 PM
    Author     : Nam
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <title>Discount Detail Management</title>

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

        <link href="vendor/select2/select2.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/toastr.min.css">
        <!-- Main CSS-->
        <link href="css/theme.css" rel="stylesheet" media="all">

    </head>

    <body class="animsition" onload="${sessionScope.functionToast}">
        <%
            request.getSession().removeAttribute("functionToast");
        %>
        <div class="page-wrapper">
            <!-- HEADER MOBILE-->
            <header class="header-mobile d-block d-lg-none">
                <div class="header-mobile__bar">
                    <div class="container-fluid">
                        <div class="header-mobile-inner">
                            <a class="logo" href="home.jsp">
                                <img src="images/icon/logo.png" alt="CoolAdmin" />
                            </a>
                            <button class="hamburger hamburger--slider" type="button">
                                <span class="hamburger-box">
                                    <span class="hamburger-inner"></span>
                                </span>
                            </button>
                        </div>
                    </div>
                </div>    
                <nav class="navbar-mobile">
                    <div class="container-fluid">
                        <ul class="navbar-mobile__list list-unstyled">
                            <li class="has-sub">
                                <a class="js-arrow" href="#">
                                    <i class="fas fa-light fa-user"></i>User management</a>
                                <ul class="navbar-mobile-sub__list list-unstyled js-sub-list">
                                    <li>
                                        <a href="news.jsp">View user information</a>
                                    </li>
                                    <li>
                                        <a href="newsManagement.jsp">Delete user account</a>
                                    </li>
                                </ul>
                            </li>
                            <li class="has-sub">
                                <a class="js-arrow" href="#">
                                    <i class="fab fa-product-hunt"></i></i>Product management</a>
                                <ul class="navbar-mobile-sub__list list-unstyled js-sub-list">
                                    <li>
                                        <a href="view.html">View list of product</a>
                                    </li>
                                    <li>
                                        <a href="manage.html">Update product information</a>
                                    </li>
                                </ul>
                            </li>
                            <li class="has-sub">
                                <a class="js-arrow" href="#">
                                    <i class="fas fa-solid fa-newspaper"></i>News management</a>
                                <ul class="navbar-mobile-sub__list list-unstyled js-sub-list">
                                    <li>
                                        <a href="news">View list of news</a>
                                    </li>
                                    <li>
                                        <a href="newsManage">Update news information</a>
                                    </li>
                                </ul>
                            </li>
                            <li class="has-sub">
                                <a class="js-arrow" href="#">
                                    <i class="fas fa-shopping-cart"></i>Order management</a>
                                <ul class="navbar-mobile-sub__list list-unstyled js-sub-list">
                                    <li>
                                        <a href="view.html">View list of orders</a>
                                    </li>
                                    <li>
                                        <a href="view.html">View buying history</a>
                                    </li>
                                </ul>
                            </li>
                            <li class="has-sub">
                                <a class="js-arrow" href="#">
                                    <i class="fas fa-regular fa-comment"></i>Comment management</a>
                                <ul class="navbar-mobile-sub__list list-unstyled js-sub-list">
                                    <li>
                                        <a href="view.html">View list of comments</a>
                                    </li>
                                    <li>
                                        <a href="manage.html">Delete comment</a>
                                    </li>
                                </ul>
                            </li>
                            <li class="has-sub">
                                <a class="js-arrow" href="#">
                                    <i class="fas fa-tablet-alt"></i>Client management</a>
                                <ul class="navbar-mobile-sub__list list-unstyled js-sub-list">
                                </ul>
                            </li>
                        </ul>
                    </div>
                </nav>

            </header>
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
                                    <!-- DATA TABLE -->
                                    <div class="table-responsive table-responsive-data2">
                                        <h3 class="title-5 m-b-35">Add discount detail</h3>
                                        <c:if test="${requestScope.updateDiscount == null}">

                                            <form action="discountDetail" method="post">
                                                <h3 class="title-5 m-b-35">Add discount detail</h3>

                                                <div class="form-group">
                                                    <label class="mr-2">Code</label>
                                                    <input type="text" name="code" class="form-control" required="" value="${requestScope.code}" maxlength="14">
                                                </div>
                                                <h2 class="text-center" style="color: red">${sessionScope.errorCode}</h2>
                                                <div class="form-group">
                                                    <label class="mr-2">Name</label>
                                                    <input type="text" name="name" class="form-control" value="${requestScope.name}">
                                                </div>
                                                <div class="form-group">
                                                    <label class="mr-2">Amount (%)</label>                                                   
                                                    <input type="number" step="any" name="amount" class="form-control" required="" value="${requestScope.amount}">
                                                </div> 
                                                <h2 class="text-center" style="color: red">${sessionScope.errorAmount}</h2>
                                                <div class="form-group">
                                                    <label class="mr-2">Description:</label>
                                                    <textarea name="description" class="form-control">${requestScope.description}</textarea>
                                                </div>         
                                                <div class="form-group">
                                                    <label class="mr-2">Type:</label>
                                                    <input type="radio" name="type" value="User" onchange="show()" id="user" required> For the user &nbsp;
                                                    <input type="radio" name="type" value="Product" onchange="show()" id="product" required> For the product<br>                                            </div>         
                                                <!--
                                                                                                <div id="user-div" hidden>
                                                                                                    <div class="form-group" >
                                                                                                        <label class="mr-2">User Id:</label>
                                                                                                        <input type="number" name="userId" class="form-control" required id="userId">
                                                                                                    </div>
                                                                                                    <div class="form-group" >
                                                                                                        <label class="mr-2">Number:</label>
                                                                                                        <input type="number" name="number" class="form-control" required id="number" min="1">
                                                                                                    </div>
                                                                                                </div>-->
                                                <div id="product-div" hidden>
                                                    <div class="form-group" >
                                                        <label class="mr-2">Product model code(s):</label>
                                                        <select class="js-example-basic-multiple" name="models[]" multiple="multiple">
                                                            <c:forEach var="pro" items="${sessionScope.products}">
                                                                <option value="${pro.model}">${pro.model}</option>
                                                            </c:forEach>
                                                        </select>                                                        
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="mr-2">From: </label>
                                                        <input type="date" name="fromDate" class="form-control" required id="fromDate" value="${requestScope.fromDate}">
                                                    </div> 
                                                    <div class="form-group">
                                                        <label class="mr-2">To: </label>
                                                        <input type="date" name="toDate" class="form-control" required id="toDate" value="${requestScope.toDate}">
                                                    </div> 
                                                </div>
                                                <div class="d-flex justify-content-center align-items-center">
                                                    <button class="au-btn au-btn-icon au-btn--blue au-btn--small" type="submit" name="submit" value="add">
                                                        Submit</button>        
                                                </div> 
                                            </form>
                                        </c:if>   

                                        <c:if test="${requestScope.updateDiscount != null}">
                                            <c:set var="ud" value="${requestScope.updateDiscount}" />

                                            <form action="discountDetail" method="post">
                                                <h3 class="title-5 m-b-35">Update discount detail</h3>

                                                <div class="form-group">
                                                    <label class="mr-2">Code</label>
                                                    <input type="text" name="code" class="form-control" required="" value="${ud.code}" readonly>
                                                </div>

                                                <div class="form-group">
                                                    <label class="mr-2">Name</label>
                                                    <input type="text" name="name" class="form-control" value="${ud.name}">
                                                </div>
                                                <div class="form-group">
                                                    <label class="mr-2">Amount (%)</label>                                                   
                                                    <input type="number" step="any" name="amount" class="form-control" required="" value="${ud.amount}" ${(requestScope.checkUpdate == false)?'readonly':''}>
                                                </div> 
                                                <h2 class="text-center" style="color: red">${sessionScope.errorAmount}</h2>
                                                <div class="form-group">
                                                    <label class="mr-2">Description:</label>
                                                    <textarea name="description" class="form-control">${ud.description}</textarea>
                                                </div>         
                                                <div class="form-group">
                                                    <label class="mr-2">Type: ${ud.type}</label>                                                                                     
                                                </div>         
                                                <c:if test="${requestScope.updateProductDiscount != null}">
                                                    <c:set var="upd" value="${requestScope.updateProductDiscount}"/>

                                                    <c:if test="${(requestScope.checkUpdate == false)}">
                                                        <div class="form-group">
                                                            <label class="mr-2">Product model(s) of this discount: ${requestScope.updateModels}</label>   
                                                            <input type="hidden" value="0" name="canUpdate"/>                                                            
                                                        </div>                                                         
                                                    </c:if>
                                                    <c:if test="${(requestScope.checkUpdate == true)}">
                                                        <div class="form-group">
                                                            <label class="mr-2">Product model(s) of this discount before update: ${requestScope.updateModels}</label>                                                    
                                                        </div> 
                                                        <div class="form-group" >
                                                            <label class="mr-2">Product model code(s):</label>
                                                            <select class="js-example-basic-multiple" name="models[]" multiple="multiple">
                                                                <c:forEach var="pro" items="${sessionScope.products}">
                                                                    <option value="${pro.model}">${pro.model}</option>
                                                                </c:forEach>
                                                            </select>
                                                            <input type="hidden" value="1" name="canUpdate"/>
                                                        </div> 
                                                    </c:if>

                                                    <div class="form-group">
                                                        <label class="mr-2">From: </label>
                                                        <input type="date" name="fromDate" class="form-control" required value="${requestScope.updateFromDate}" ${(requestScope.checkUpdate == false)?'readonly':''}>
                                                    </div> 
                                                    <div class="form-group">
                                                        <label class="mr-2">To: </label>
                                                        <input type="date" name="toDate" class="form-control" required value="${requestScope.updateToDate}" ${(requestScope.checkUpdateTo == false)?'readonly':''}>
                                                    </div> 
                                                </c:if>
                                                <input type="hidden" name="checkUpdate" value="${requestScope.checkUpdate}"> 
                                                <div class="d-flex justify-content-center align-items-center">
                                                    <button class="au-btn au-btn-icon au-btn--blue au-btn--small" type="submit" name="submit" value="update">
                                                        Submit</button>        
                                                </div>
                                            </form>
                                        </c:if>  
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- CKEDITOR JS-->
        <script>
            function show() {
                document.getElementById('user').required = false;
                document.getElementById('product').required = false;
                if (document.getElementById('user').checked) {
//                        document.getElementById('user-div').hidden = false;
                    document.getElementById('product-div').hidden = true;

//                        document.getElementById('userId').required = true;
//                        document.getElementById('number').required = true;
                    document.getElementById('proId').required = false;
                    document.getElementById('fromDate').required = false;
                    document.getElementById('toDate').required = false;

                } else if (document.getElementById('product').checked) {
                    document.getElementById('product-div').hidden = false;
//                        document.getElementById('user-div').hidden = true;

//                        document.getElementById('userId').required = false;
//                        document.getElementById('number').required = false;
                    document.getElementById('proId').required = true;
                    document.getElementById('fromDate').required = true;
                    document.getElementById('toDate').required = true;
                }
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
                        toastr.warning(title, 'Notification');
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

        <script>
            $(document).ready(function () {
                $('.js-example-basic-multiple').select2();
            });
        </script>

        <!-- Main JS-->
        <script src="js/main.js"></script>

    </body>

</html>
<!-- end document-->

