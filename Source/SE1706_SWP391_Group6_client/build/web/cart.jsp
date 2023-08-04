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
        <base href="http://localhost:9999/SE1706_SWP391_Group6_client/"/>
        <title>Cart</title>

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

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body onload="${sessionScope.functionToast}">
        <%
            request.getSession().removeAttribute("functionToast");
        %>
        <div id="header-area">
            <%@include file="header.jsp"%>
        </div>

        <c:set var="pr" value="Product" />
        <c:set var="ac" value="Cart" />
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
                            <h2>Your Cart</h2>
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
                                <c:if test="${oiList.size() == 0}">
                                    <p style="text-align: center; font-size: 45px; color: grey; margin: 30px">Your cart is empty</p>
                                </c:if>
                                <c:if test="${oiList.size() != 0}">
                                    <form method="post" action="order">
                                        <table cellspacing="0" class="shop_table cart">
                                            <thead>
                                                <tr>
                                                    <th class="product-remove" style="width: 140px">
                                                        <input id="selectAll" type="checkbox" onchange="checkAll()">
                                                    </th>
                                                    <th class="product-thumbnail">Image</th>
                                                    <th class="product-name">Name</th>
                                                    <th class="product-price">Price</th>
                                                    <th class="product-quantity">Quantity</th>
                                                    <th class="product-subtotal">Total</th>
                                                    <th class="product-remove">Edit</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items='${requestScope.oiList}' var='item' varStatus='loop'>
                                                    <tr class="cart_item">
                                                        <td class="product-remove">
                                                            <input onchange="checkSingle()" type="checkbox" class='remove' name='cartIds[]' value='${item.getOrderItemId()}'/>
                                                        </td>

                                                        <td class="product-thumbnail">
                                                            <a href="singleProduct?productId=${item.getProId()}">
                                                                <img
                                                                    width="145"
                                                                    height="145"
                                                                    alt="poster_1_up"
                                                                    class="shop_thumbnail"
                                                                    src="${item.getProImg()}"
                                                                    /></a>
                                                        </td>

                                                        <td class="product-name">
                                                            <a href="singleProduct?productId=${item.getProId()}">${item.getProName()}</a>
                                                        </td>

                                                        <td class="product-price">
                                                            <span class="amount">
                                                                <fmt:setLocale value = "vi_VN"/>
                                                                <fmt:formatNumber value = "${item.getProPrice()}" type = "currency"/>
                                                            </span>
                                                        </td>

                                                        <td class="product-quantity">
                                                            ${item.getQuantity()}
                                                        </td>

                                                        <td class="product-subtotal">
                                                            <span class="amount">
                                                                <fmt:setLocale value = "vi_VN"/>
                                                                <fmt:formatNumber value = "${item.getOrderItemTotal()}" type = "currency"/>
                                                            </span>
                                                        </td>

                                                        <td class="product-remove">
                                                            <a href="order/remove&${item.getOrderItemId()}">X</a>
                                                        </td>
                                                    </tr>
                                                </c:forEach> 

                                                <tr id="btns" hidden>
                                                    <td>
                                                        <button id="btnDelete" class='button' style="width: fit-content" type='submit' name='action' value="delete">Delete</button>
                                                    </td>
                                                    <td class="actions" colspan="6">
                                                        <button class='button' style="width: fit-content" type='submit' name='action' value="checkout">Checkout</button>

                                                    </td>
                                                </tr>
                                                <c:if test="${missing == 'missing'}">
                                                    <tr>
                                                        <td>
                                                            <p style="color: red; margin: 5px">*No items selected!</p>
                                                        </td>
                                                        <td></td>
                                                    </tr>
                                                </c:if>
                                            </tbody>
                                        </table>
                                    </form>
                                </c:if>

                                <div class="cart-collaterals">
                                    <div class="cross-sells">
                                        <h2>You may be interested</h2>
                                        <ul class="products">
                                            <c:forEach end='1' items='${requestScope.pList}' var='item'>
                                                <li class="product">
                                                    <a href="singleProduct?productId=${item.getPid()}">
                                                        <img
                                                            width="325"
                                                            height="325"
                                                            alt="T_4_front"
                                                            class="attachment-shop_catalog wp-post-image"
                                                            src="${item.getImg().get(0)}"
                                                            />
                                                        <h3>${item.getPname()}</h3>
                                                        <span class="price">
                                                            <span class="amount">
                                                                <fmt:setLocale value = "vi_VN"/>
                                                                <fmt:formatNumber value = "${item.getSellPrice()}" type = "currency"/>
                                                            </span>
                                                        </span>
                                                    </a>
                                                    <a
                                                        class="add_to_cart_button"
                                                        data-quantity="1"
                                                        data-product_sku=""
                                                        data-product_id="22"
                                                        rel="nofollow"
                                                        href="singleProduct?productId=${item.getPid()}"
                                                        >View details
                                                    </a>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </div>
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

        <script>
            function checkAll() {
                var ele = document.getElementById("selectAll");
                var checkboxes = document.getElementsByTagName('input');
                if (ele.checked) {
                    for (var i = 0; i < checkboxes.length; i++) {
                        if (checkboxes[i].type === 'checkbox') {
                            checkboxes[i].checked = true;
                            checkSingle();
                        }
                    }
                } else {
                    for (var i = 0; i < checkboxes.length; i++) {
                        if (checkboxes[i].type === 'checkbox') {
                            checkboxes[i].checked = false;
                            checkSingle();
                        }
                    }
                }
            }

            function checkSingle() {
                var btns = document.getElementById("btns");
                var checkboxes = document.getElementsByTagName('input');
                var tmp = false;
                for (var i = 0; i < checkboxes.length; i++) {
                    if (checkboxes[i].type === 'checkbox') {
                        if (checkboxes[i].checked === true) {
                            tmp = true;
                        }
                    }
                }
                if (tmp === true)
                    btns.hidden = false;
                else
                    btns.hidden = true;
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
