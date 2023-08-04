<%-- 
    Document   : cart
    Created on : May 13, 2023, 5:35:52 PM
    Author     : Dell
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--<%@page import="java.util.ArrayList" %>
<%@page import="model.OrderItem" %>
<%@page import="model.Order" %>
<%@page import="model.Product" %>--%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <base href="http://localhost:9999/SE1706_SWP391_Group6_client/"/>
        <title>Order History</title>

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

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>
        <div id="header-area">
            <%@include file="header.jsp"%>
        </div>

        <c:set var="pr" value="Product" />
        <c:set var="ac" value="Purchase History" />
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
                            <h2>Purchase history</h2>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- End Page title area -->

        <div class="single-product-area">
            <div class="zigzag-bottom"></div>
            <div class="container">
                <div class="row">

                    <div class="col-md-8">
                        <div class="product-content-right">
                            <div class="woocommerce">
                                <c:if test="${oList.size() == 0}">
                                    <p style="text-align: center; font-size: 45px; color: grey; margin: 30px">Your haven't ordered anything yet.</p>
                                </c:if>
                                <c:if test="${oList.size() != 0}">
                                    <table cellspacing="0" class="table-responsive shop_table cart">
                                        <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>Total</th>
                                                <th>Created at</th>
                                                <th>Status</th>
                                                <th>View</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items='${requestScope.oList}' var='item'>
                                                <tr class="cart_item">
                                                    <td>${item.getOrderId()}</td>
                                                    <td>
                                                        <fmt:setLocale value = "vi_VN"/>
                                                        <fmt:formatNumber value = "${item.getGrandtotal()}" type = "currency"/>
                                                    </td>
                                                    <td>${item.getCreatedAt()}</td>
                                                    <td>${item.getStatus()}</td>
                                                    <td>
                                                        <a href="order/detail&${item.getOrderId()}" style="display: block; margin-bottom: 5px; padding: 5px; border: 1px solid darkorange">Detail</a>
                                                        <c:if test="${item.getStatusId() == 4}">
                                                            <a href="order/cancel&${item.getOrderId()}" style="display: block; padding: 5px; border: 1px solid darkorange">Cancel</a>
                                                        </c:if>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </c:if>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-4">
                        <div class="single-sidebar">
                            <h2 class="sidebar-title">Products</h2>
                            <c:forEach end='2' items="${pList}" var="item">
                                <div class="thubmnail-recent">
                                    <img src="${item.getImg().get(0)}" class="recent-thumb" alt="">
                                    <h2><a href="singleProduct?productId=${item.getPid()}">${item.getPname()}</a></h2>
                                    <div class="product-sidebar-price">
                                        <ins>
                                            <fmt:setLocale value = "vi_VN"/>
                                            <fmt:formatNumber value = "${item.getSellPrice()}" type = "currency"/>
                                        </ins>
                                    </div>                             
                                </div>
                            </c:forEach>
                        </div>

                        <div class="single-sidebar">
                            <h2 class="sidebar-title">Recent Posts</h2>
                            <ul>
                                <c:forEach end="2" var="ns" items="${requestScope.defendNews}">
                                    <li><a href="newsDetail?title=${ns.getTitle().toLowerCase().replaceAll(' ', '-').replaceAll('[^a-z0-9-]', '')}">${ns.getTitle()}</a></li>
                                    </c:forEach>
                            </ul>
                        </div>
                    </div>

                </div>
            </div>
        </div>

        <div id ="footer-area">
            <%@include file="footer.jsp"%>
        </div>

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
