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
        <title>User Management</title>

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
        <c:set var="n" value="0" />
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
                                        <h2 class="title-1">User Manager</h2>
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
                                        
                                    </div>  
                                    <div class="table-data__tool-right">
                                           
                                    </div>
                                </div>
                                <c:if test="${aList.size() == 0}">
                                    <h4 class="title-5 m-b-35">No items found.</h4>
                                </c:if>
                                <c:if test="${aList.size() != 0}">
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="table-responsive table--no-card m-b-30">
                                                <table class="table table-borderless table-striped table-earning">
                                                    <thead>
                                                        <tr>
                                                            <th>Code</th>
                                                            <th>Mobile</th>
                                                            <th>Email</th>
                                                            <th>NumOfOrder</th>
                                                            <th>TotalPrice</th>
                                                            <th>RegisterAt</th>
                                                            <th>Edit</th>
                                                        </tr>
                                                    </thead>                                            
                                                    <tbody id="tbodyRow">
                                                        <c:forEach var="item" items="${aList}">
                                                            <tr>
                                                                <td>${item.id}</td>
                                                                <td>${item.mobile}</td>
                                                                <td>${item.email}</td>
                                                                <td>${item.numOfOrder}</td>
                                                                <td>${item.totalPrice}</td>
                                                                <td>${item.registerAt}</td>
                                                                <td>
                                                                    <div class="table-data-feature">
                                                                        <form action="userManage" id="form-delete-${item.id}" method="POST">
                                                                            <input name="deleteCode" value="${item.id}" hidden>
                                                                            <input name="act" value="delete" hidden>
                                                                            <input name="index" value="${currentPage}" hidden>
                                                                            <button type="button" class="item" data-toggle="tooltip" data-placement="top" 
                                                                                    title="Delete" type="submit" onclick="confirmDelete('${item.id}')">
                                                                                <i class="zmdi zmdi-delete"></i>
                                                                            </button>
                                                                        </form>
                                                                    </div>
                                                                </td>



                                                            </tr>
                                                        </c:forEach>                                                
                                                    </tbody>
                                                </table>
                                                <p style="color: red" ${(deny == true)?'':'hidden'}>*The item cannot be deleted.</p>
                                                <p style="color: green" ${(deny == false)?'':'hidden'}>*The item is deleted successfully.</p>
                                            </div>
                                        </div>
                                    </div>
                                </c:if>

                                <c:if test="${endPage ne n}">
                                    <div class="d-flex justify-content-end">
                                        <form id="paging" action="userManage" method="POST">
                                            <input name="act" value="paging" hidden/>

                                            <ul class="pagination">
                                                <li class="page-item">
                                                    <a aria-label="Previous" class="page-link" onclick="submitFormPaging(${currentPage - 1})">
                                                        <span aria-hidden="true">&laquo;</span>
                                                    </a>
                                                </li>     

                                                <c:forEach begin="1" end="${endPage}" var="i">
                                                    <li class="page-item ${currentPage == i ? 'active' : ''}"><a class="page-link" onclick="submitFormPaging(${i})">${i}</a></li>  
                                                    </c:forEach>

                                                <li class="page-item">
                                                    <a aria-label="Previous" class="page-link" onclick="submitFormPaging(${currentPage + 1})">
                                                        <span aria-hidden="true">&raquo;</span>
                                                    </a>
                                                </li>                                                
                                            </ul>
                                            <input id="index" name="index" value="${currentPage}" hidden>
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

                <script>
                    function confirmDelete(code) {
                        if (confirm('Are you sure do delete this item?')) {
                            var formDelete = document.getElementById('form-delete-' + code);
                            if (formDelete) {
                                formDelete.submit();
                            }
                        }
                    }

                    function submitStatus(code) {
                        var incode = "" + code;
                        document.getElementById("frmUpdateStatus-" + incode).submit();
                    }

                    function submitFormFilter() {
                        document.getElementById("formFilter").submit();
                    }

                    function submitFormPaging(page) {
                        var ind = document.getElementById("index");
                        ind.value = page;
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

