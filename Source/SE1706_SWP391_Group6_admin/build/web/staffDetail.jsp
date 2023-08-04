<%-- 
    Document   : newsManagement
    Created on : May 25, 2023, 8:57:49 PM
    Author     : Nam
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
        <title>Staff Detail Management</title>

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
                                        <h2 class="title-1">Useless context</h2>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- MAIN CONTENT-->
                    <div class="main-content">
                        <div class="section__content section__content--p30">
                            <div class="container-fluid">
                                <div class="row">
                                    <div class="col-md-12">
                                        <!-- DATA TABLE -->
                                        <div class="table-responsive table-responsive-data2">
                                            <a href="staffManage">Back to list</a>
                                            <form action="staffManage" method="POST">
                                                <input name="act" value="addDetail" hidden>
                                                <h3 class="title-5 m-b-35">Add New Staff</h3>                                                
                                                <div class="form-group">
                                                    <label class="mr-2">First Name</label>
                                                    <input type="text" name="firstName" value="${sessionScope.fname}" class="form-control" required>
                                                </div>
                                                <div class="form-group">
                                                    <label class="mr-2">Last Name</label>
                                                    <input type="text" name="lastName" value="${sessionScope.lname}" class="form-control" required>
                                                </div>
                                                <div class="form-group d-flex">
                                                    <label class="mr-2">Role</label>
                                                   
                                                    <select name="role">
                                                        <c:forEach var="stItem" items="${lrole}">
                                                            <option ${(sessionScope.role == stItem.role)?'selected':''} value="${stItem.roleId}">${stItem.role}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <div class="form-group">
                                                    <label class="mr-2">Mobile</label>
                                                    <input type="number" min="0" name="mobile" class="form-control" value="${sessionScope.mobile}" required>
                                                </div> 
                                                <div class="form-group">
                                                    <label class="mr-2">Email</label>
                                                    <input type="email" name="email" class="form-control" value="${sessionScope.email}" required>
                                                </div>    
                                                <div class="form-group">
                                                    <label class="mr-2">Password</label>
                                                    <input type="password" name="pass" class="form-control" required>
                                                </div> 
                                                <div class="form-group">
                                                    <label class="mr-2">Re-Password</label>
                                                    <input type="password" name="repass" class="form-control" required>
                                                </div>
                                                <p style="color: red" type="text-danger">${mess}</p>
                                                <div class="d-flex justify-content-center align-items-center">
                                                    <button class="au-btn au-btn-icon au-btn--blue au-btn--small" type="submit">
                                                        Submit</button>        
                                                </div>                          
                                            </form>
                                        </div>
                                    </div>
                                </div>
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
        </div>

        <!-- CKEDITOR JS-->
        <script>
            function showAmount() {
                var currentType = document.getElementById("serviceType").value;
                if (currentType === 'extend') {
                    document.getElementById("amountContainer").hidden = false;
                } else {
                    document.getElementById("amountContainer").hidden = true;
                }
            }

            function showNew() {
                var isHidden = document.getElementById("typeContainer").hidden;
                if (isHidden === true) {
                    document.getElementById("typeContainer").hidden = false;
                } else {
                    document.getElementById("typeContainer").hidden = true;
                }
            }
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

