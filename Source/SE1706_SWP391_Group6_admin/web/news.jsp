<%-- 
    Document   : news
    Created on : May 25, 2023, 8:57:40 PM
    Author     : Nam
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <title>News Management</title>

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
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/toastr.min.css">

    </head>

    <body class="animsition" onload="${sessionScope.functionToast}">
        <%
            request.getSession().removeAttribute("functionToast");
        %>
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
                                    <h3 class="title-5 m-b-35">List of news</h3>                                    
                                    <div class="table-data__tool">
                                        <div class="table-data__tool-left">
                                            <form id="myForm" action="news" method="post">
                                                <div class="rs-select2--light rs-select2--md">
                                                    <select class="js-select2" name="sortBy" onchange="submitForm()">
                                                        <c:set var="so" value="${requestScope.sortBy}"/>
                                                        <option ${(so == 0)?'selected':''} value="0">Sort by</option>
<!--                                                        <option ${(so == 1)?'selected':''} value="1">Latest</option>
                                                        <option ${(so == 2)?'selected':''} value="2">Most viewed</option>-->
                                                        <c:forEach var="sos" items="${requestScope.sorts}">
                                                            <option ${(so == sos.id)?'selected':''} value="${sos.id}">${sos.title}</option>
                                                        </c:forEach>
                                                    </select>
                                                    <div class="dropDownSelect2"></div>
                                                </div>
                                                <div class="rs-select2--light rs-select2--md">
                                                    <select class="js-select2" name="groupBy" onchange="submitForm()">
                                                        <c:set var="gr" value="${requestScope.groupBy}"/>
                                                        <option ${(gr == 0)?'selected':''} value="0">Group by</option>
                                                        <c:forEach var="g" items="${requestScope.groups}">
                                                            <option ${(g.id == gr)?'selected':''} value="${g.id}">${g.name}</option>
                                                        </c:forEach>
                                                    </select>
                                                    <div class="dropDownSelect2"></div>
                                                </div>
                                                <input type="hidden" name="search" value="${requestScope.search}">     
                                            </form>
                                        </div>    
                                        <div class="table-data__tool">
                                            <form class="form-header" action="news" method="post">
                                                <input class="au-input au-input--xl" type="text" name="search" value="${requestScope.search}" placeholder="Search for title or author of the news" />
                                                <button class="au-btn--submit" type="submit">
                                                    <i class="zmdi zmdi-search"></i>
                                                </button>
                                                <input type="hidden" name="sortBy" value="${requestScope.sortBy}">
                                                <input type="hidden" name="groupBy" value="${requestScope.groupBy}"> 
                                            </form>
                                        </div>
                                        <div class="table-data__tool-right">
                                            <form action="newsDetail" method="get" style="display: inline-block">
                                                <button class="au-btn au-btn-icon au-btn--green au-btn--small" >
                                                    <i class="zmdi zmdi-plus"></i>Add news</button>                                            
                                            </form>  
                                        </div>
                                    </div>
                                    <c:if test="${requestScope.news.size() == 0}">
                                        <h3 class="title-5 m-b-35">Not available result!</h3> 
                                    </c:if>
                                    <c:if test="${requestScope.news.size() != 0}">
                                        <div class="table-responsive table--no-card m-b-30">
                                            <table class="table table-borderless table-striped table-earning">
                                                <thead>
                                                    <tr>
                                                        <th>Group name</th>
                                                        <th>Author</th>
                                                        <th>Title</th>
                                                        <th>Post date</th>
                                                        <th>View</th>
                                                        <th>Edit</th>
                                                        <th>In Slide</th>
                                                    </tr>
                                                </thead>                                            
                                                <tbody>
                                                    <c:forEach var="n" items="${requestScope.news}">
                                                        <tr>
                                                            <td>${n.groupName}</td>
                                                            <td>${n.author}</td>
                                                            <td>${n.title}</td>
                                                            <td>${n.createAt}</td>
                                                            <td>${n.stt}</td>
                                                            <td>
                                                                <div class="table-data-feature">
                                                                    <form action="newsDetail" method="post">
                                                                        <input type="hidden" name="updateNewsId" value="${n.id}">
                                                                        <button class="item" data-toggle="tooltip" data-placement="top" title="Edit" type="submit">
                                                                            <i class="zmdi zmdi-edit"></i>
                                                                        </button>
                                                                    </form>
                                                                    <form action="newsManage" method="post" id="${n.id}">
                                                                        <input name="act" value="delete" hidden/>
                                                                        <input type="hidden" name="newsId" value="${n.id}">
                                                                        <button type="button" class="item" data-toggle="tooltip" data-placement="top" 
                                                                                title="Delete" type="submit"  onclick="confirmDelete(${n.id})">
                                                                            <i class="zmdi zmdi-delete"></i>
                                                                        </button>
                                                                    </form>
                                                                </div>
                                                            </td>
                                                            <td>
                                                                <div class="table-data-feature">

                                                                    <c:if test="${n.stt != 1}">
                                                                        <form action="newsManage" method="post">
                                                                            <input name="act" value="isSlide" hidden/>
                                                                            <input type="hidden" name="newsId" value="${n.id}">
                                                                            <button class="item" data-toggle="tooltip" data-placement="top" title="inSlide" type="submit">
                                                                                <i class="zmdi zmdi-lock-open"></i>
                                                                            </button>
                                                                        </form>  
                                                                    </c:if>
                                                                    <c:if test="${n.stt == 1}">
                                                                        <form action="newsManage" method="post">
                                                                            <input name="act" value="notSlide" hidden/>
                                                                            <input type="hidden" name="newsId" value="${n.id}">
                                                                            <button class="item" data-toggle="tooltip" data-placement="top" title="notSlide" type="submit">
                                                                                <i class="zmdi zmdi-lock"></i>
                                                                            </button>
                                                                        </form>
                                                                    </c:if>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>                                                
                                                </tbody>
                                            </table>
                                        </div>


                                        <div class="product-pagination text-center">
                                            <form id="myForm1" action="news" method="post">
                                                <input type="hidden" name="sortBy" value="${requestScope.sortBy}">
                                                <input type="hidden" name="groupBy" value="${requestScope.groupBy}">     
                                                <input type="hidden" name="search" value="${requestScope.search}">     

                                                <div class="d-flex justify-content-end">
                                                    <ul class="pagination">
                                                        <li class="page-item">
                                                            <a href="#" aria-label="Previous"class="page-link" onclick="submitForm1(${requestScope.page - 1})">
                                                                <span aria-hidden="true">&laquo;</span>
                                                            </a>
                                                        </li>                                                
                                                        <c:forEach begin="1" end="${requestScope.count}" var="i">
                                                            <li class="page-item ${requestScope.page == i ? 'active' : ''}"><a class="page-link" onclick="submitForm1(${i})">${i}</a></li>                                         
                                                                <c:if test="${i == requestScope.page || requestScope.page - 1 == i || requestScope.page + 1 == i}">
                                                                </c:if>
                                                            </c:forEach>
                                                        <li class="page-item">
                                                            <a href="#" aria-label="Previous"class="page-link" onclick="submitForm1(${requestScope.page + 1})">
                                                                <span aria-hidden="true">&raquo;</span>
                                                            </a>
                                                        </li>                                                
                                                    </ul>
                                                </div>
                                                <input type="hidden" name="page" value="1" id="myPage">
                                            </form>                        
                                        </div>
                                    </c:if>           
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
        <script>
            function confirmDelete(id) {
                if (confirm('Are you sure do delete this news?')) {
                    var formDelete = document.getElementById(id);
                    if (formDelete) {
                        formDelete.submit();
                    }
                }
            }
        </script> 
        <script>
            function submitForm() {
                document.getElementById("myForm").submit();
            }
            function submitForm1(index) {
                var form1 = document.getElementById("myForm1");
                var pageInput = document.getElementById("myPage");
                pageInput.value = index;
                form1.submit();
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

        <!-- Main JS-->
        <script src="js/main.js"></script>

    </body>

</html>
<!-- end document-->

