<%-- 
    Document   : productManage
    Created on : 25-05-2023, 11:07:27
    Author     : fpt
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <title>Product Management</title>

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

                <!-- MAIN CONTENT-->
                <div class="main-content">
                    <div class="section__content section__content--p30">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-md-12">
                                    <!-- DATA TABLE -->
                                    <h3 class="title-5 m-b-35">product table</h3>
                                    <div class="table-data__tool">
                                        <form action="productManage" method="post" id="filter">
                                            <div class="table-data__tool-left">                                
                                                <input name="act" value="sort" hidden/>
                                                <input type="number" id="index" name="index" value="1" hidden/> 
                                                <input class="au-input au-input--xl" type="text" name="search" placeholder="Search for products..." value="${search eq null ? null : search}" style="margin: 0px 10px 10px 0px" />
                                                <div class="rs-select2--light rs-select2--md" style="margin-right: 10px">
                                                    <select class="js-select2" name="category">
                                                        <option value="all" ${cid eq "all" ? 'selected' : ''}>All Categories</option>
                                                        <c:forEach items="${cList}" var="cl">
                                                            <option value="${cl.getCategoryid()}" ${cid eq cl.getCategoryid().toString() ? 'selected' : ''}>${cl.getCname()}</option>
                                                        </c:forEach>
                                                    </select>
                                                    <div class="dropDownSelect2"></div>
                                                </div>
                                                <div class="rs-select2--light rs-select2--md" style="margin-right: 10px">
                                                    <select class="js-select2" name="sort">
                                                        <option value="all" ${sort eq "all" ? 'selected' : ''}>All Properties</option>
                                                        <option value="name" ${sort eq "name" ? 'selected' : ''}>Sort by name</option>
                                                        <option value="latest" ${sort eq "latest" ? 'selected' : ''}>Sort by latest</option>
                                                        <option value="view" ${sort eq "view" ? 'selected' : ''}>Sort by view</option>
                                                        <option value="price" ${sort eq "price" ? 'selected' : ''}>Sort by price</option>
                                                    </select>
                                                    <div class="dropDownSelect2"></div>
                                                </div>
                                                <div class="rs-select2--light rs-select2--md">
                                                    <select class="js-select2" name="type">
                                                        <option value="all" ${type eq "all" ? 'selected' : ''}>All Type</option>
                                                        <option value="lowToHigh" ${type eq "lowToHigh" ? 'selected' : ''}>High to low</option>
                                                        <option value="highToLow" ${type eq "highToLow" ? 'selected' : ''}>Low to high</option>
                                                    </select>
                                                    <div class="dropDownSelect2"></div>
                                                </div>
                                                <div style="margin-bottom: 5px">
                                                    Showing <input type="number" name="numberDisplay" value="${size}" style="min-width: 5%; width: 5%; margin-bottom: 5px" min="1" max="10"> entries from ${total} products
                                                    <button class="au-btn au-btn-icon au-btn--blue au-btn--small" style="padding-left: 50px; padding-right: 50px; margin-left: 100px">Apply</button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                    <a href="productDetail?act=add"><button class="au-btn au-btn-icon au-btn--green au-btn--small" style="margin-bottom: 10px">Add</button></a>
                                    <div class="table-responsive table--no-card m-b-30">
                                        <c:if test="${pList == null}"><h5>No results found!</h5></c:if>
                                        <c:if test="${pList != null}">
                                            <table class="table table-borderless table-striped table-earning">
                                                <thead>
                                                    <tr>
                                                        <!--<th>
                                                            <label class="au-checkbox">
                                                                <input type="checkbox">
                                                                <span class="au-checkmark"></span>
                                                            </label>
                                                        </th>-->
                                                        <th>Model</th>
                                                        <th>Name</th>                                                    
                                                        <th>Category</th>
                                                        <th>Price</th>
                                                        <th>Stock Quantity</th>
                                                        <th>Created At</th>
                                                        <th>Action</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${pList}" var="item">

                                                        <tr class="tr-shadow">
                                                            <!--<td>
                                                                <label class="au-checkbox">
                                                                    <input type="checkbox">
                                                                    <span class="au-checkmark"></span>
                                                                </label>
                                                            </td>-->
                                                            <td>${item.getModel()}</td>
                                                            <td>${item.getPname()}</td>

                                                            <td>${c.getCategoryByID(item.getCategoryid()).getCname()}</td>
                                                            <td><fmt:setLocale value = "vi_VN"/>
                                                                <fmt:formatNumber value = "${item.getSellPrice()}" type = "currency"/></td>         
                                                            <td>${item.getStockQuantity(item.getPid())}</td>
                                                            <td>${item.getCreatedAt()}</td>
                                                            <td>
                                                                <div class="table-data-feature">
                                                                    <form action="productDetail" method="post">
                                                                        <input type="hidden" name="act" value="addStock">
                                                                        <input type="hidden" name="id" value="${item.getPid()}">
                                                                        <button class="item" data-toggle="tooltip" data-placement="top" title="Add stock" type="submit">
                                                                            <i class="zmdi zmdi-plus"></i>
                                                                        </button>
                                                                    </form>
                                                                    <form action="productDetail" method="post">
                                                                        <input type="hidden" name="act" value="view">
                                                                        <input type="hidden" name="id" value="${item.getPid()}">
                                                                        <button class="item" data-toggle="tooltip" data-placement="top" title="View" type="submit">
                                                                            <i class="zmdi zmdi-eye"></i>
                                                                        </button>
                                                                    </form>
                                                                    <form action="productDetail" method="post">
                                                                        <input type="hidden" name="id" value="${item.getPid()}">
                                                                        <button class="item" data-toggle="tooltip" data-placement="top" title="Edit" type="submit">
                                                                            <i class="zmdi zmdi-edit"></i>
                                                                        </button>
                                                                    </form>  
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        <tr class="spacer"></tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </c:if>
                                    </div>
                                    <!-- END DATA TABLE -->
                                    <div class="d-flex justify-content-end">
                                        <span style="margin-right: 30px">Page ${requestScope.index} of ${count}</span>
                                        <ul class="pagination">
                                            <c:if test="${index != 1}">
                                                <li class="page-item">
                                                    <a href="#" aria-label="Previous" class="page-link" onclick="submitForm(${index - 1})">
                                                        <span aria-hidden="true">&laquo;</span>
                                                    </a>
                                                </li>
                                            </c:if>
                                            <c:forEach begin="1" end="${count}" var="i">                                                
                                                <li class="page-item ${requestScope.index == i ? 'active' : ''}" ><a class="page-link" onclick="submitForm(${i})">${i}</a></li>                                         
                                                </c:forEach>
                                                <c:if test="${index != 5}">
                                                <li class="page-item">
                                                    <a aria-label="After" class="page-link" onclick="submitForm(${index + 1})">
                                                        <span aria-hidden="true">&raquo;</span>
                                                    </a>
                                                </li>   
                                            </c:if>
                                        </ul>
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
        <!-- END MAIN CONTENT-->
        <!-- END PAGE CONTAINER-->
    </div>

</div>

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
<script>
                                                        function confirmDelete() {
                                                            if (confirm('Are you sure do delete this product?')) {
                                                                var formDelete = document.getElementById('productManage');
                                                                console.log('formDelete');
                                                                if (formDelete) {
                                                                    formDelete.submit();
                                                                }
                                                            }
                                                        }
                                                        function submitForm(index) {
                                                            var form = document.getElementById("filter");
                                                            var pageIndex = document.getElementById("index");
                                                            pageIndex.value = index;
                                                            form.submit();
                                                        }

</script> 

</body>

</html>
<!-- end document-->